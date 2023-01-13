package com.kjh.exam.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.kjh.exam.demo.Util.Utility;
import com.kjh.exam.demo.service.GenFileService;
import com.kjh.exam.demo.service.MemberService;
import com.kjh.exam.demo.vo.Member;
import com.kjh.exam.demo.vo.ResultData;
import com.kjh.exam.demo.vo.Rq;

@Controller
public class UsrMemberController {
	
	// 의존성 주입
	private MemberService memberService;
	private Rq rq;
	private GenFileService genFileService;
	
	@Autowired
	public UsrMemberController(MemberService memberService, Rq rq, GenFileService genFileService) {
		this.memberService = memberService;
		this.rq = rq;
		this.genFileService = genFileService;
	}
	
	
	// 액션매서드
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email, MultipartRequest multipartRequest) {
		
		if(Utility.empty(loginId)) {
			return Utility.jsHistoryBack("아이디를 입력해주세요");
		}
		if(Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("비밀번호를 입력해주세요");
		}
		if(Utility.empty(name)) {
			return Utility.jsHistoryBack("이름을 입력해주세요");
		}
		if(Utility.empty(nickname)) {
			return Utility.jsHistoryBack("닉네임을 입력해주세요");
		}
		if(Utility.empty(cellphoneNum)) {
			return Utility.jsHistoryBack("전화번호를 입력해주세요");
		}
		if(Utility.empty(email)) {
			return Utility.jsHistoryBack("이메일을 입력해주세요");
		}
		
		ResultData<Integer> doJoinRd = memberService.doJoin(loginId, Utility.sha256(loginPw), name, nickname, cellphoneNum, email);
		
		if(doJoinRd.isFail()) {
			return Utility.jsHistoryBack(doJoinRd.getMsg());
		}
		
		int newMemberId = (int) doJoinRd.getBody().get("id");

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);

			if (multipartFile.isEmpty() == false) {
				genFileService.save(multipartFile, newMemberId);
			}
		}
		
		return Utility.jsReplace(doJoinRd.getMsg(), "/");
	}
	
	@RequestMapping("/usr/member/getLoginIdDup")
	@ResponseBody
	public ResultData<String> getLoginIdDup(String loginId) {

		if(Utility.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member != null) {
			return ResultData.from("F-2", "이미 사용중인 아이디 입니다", "loginId", loginId);
		}

		return ResultData.from("S-1", "사용 가능한 아이디 입니다", "loginId", loginId);
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		
		if(Utility.empty(loginId)) {
			return Utility.jsHistoryBack("아이디를 입력해주세요");
		}
		
		if(Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Utility.jsHistoryBack("존재하지 않는 아이디입니다");
		}
		
		if(member.getLoginPw().equals(Utility.sha256(loginPw)) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다");
		}
		
		rq.login(member);
		
		return Utility.jsReplace(Utility.f("%s님 환영합니다", member.getNickname()), "/");
	}
	
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		
		rq.logout();
		
		return Utility.jsReplace("로그아웃 되었습니다", "/");
	}
	
	@RequestMapping("/usr/member/myPage")
	public String showMyPage() {
		return "usr/member/myPage";
	}
	
	@RequestMapping("/usr/member/checkPassword")
	public String showCheckPassword() {
		return "usr/member/checkPassword";
	}
	
	@RequestMapping("/usr/member/doCheckPassword")
	@ResponseBody
	public String doCheckPassword(String loginPw) {
		
		if(Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		if(rq.getLoginedMember().getLoginPw().equals(Utility.sha256(loginPw)) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다");
		}
		
		
		String memberModifyAuthKey = memberService.genMemberModifyAuthKey(rq.getLoginedMemberId());

		return Utility.jsReplace("비밀번호가 확인되었습니다", Utility.f("modify?memberModifyAuthKey=%s", memberModifyAuthKey));
	}
	
	@RequestMapping("/usr/member/modify")
	public String showModify(String memberModifyAuthKey) {
		
		if (Utility.empty(memberModifyAuthKey)) {
			return rq.jsReturnOnView("회원 수정 인증코드가 필요합니다", true);
		}

		ResultData checkMemberModifyAuthKeyRd = memberService.checkMemberModifyAuthKey(rq.getLoginedMemberId(), memberModifyAuthKey);

		if (checkMemberModifyAuthKeyRd.isFail()) {
			return rq.jsReturnOnView(checkMemberModifyAuthKeyRd.getMsg(), true);
		}
		
		return "usr/member/modify";
	}
	
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, String memberModifyAuthKey, String nickname, String cellphoneNum, String email, MultipartRequest multipartRequest) {
		
		if (Utility.empty(memberModifyAuthKey)) {
			return Utility.jsHistoryBack("회원 수정 인증코드가 필요합니다");
		}

		ResultData checkMemberModifyAuthKeyRd = memberService.checkMemberModifyAuthKey(rq.getLoginedMemberId(), memberModifyAuthKey);

		if (checkMemberModifyAuthKeyRd.isFail()) {
			return Utility.jsHistoryBack(checkMemberModifyAuthKeyRd.getMsg());
		}
		
		if(Utility.empty(nickname)) {
			return Utility.jsHistoryBack("닉네임을 입력해주세요");
		}
		
		if(Utility.empty(cellphoneNum)) {
			return Utility.jsHistoryBack("전화번호를 입력해주세요");
		}
		
		if(Utility.empty(email)) {
			return Utility.jsHistoryBack("이메일을 입력해주세요");
		}
		
		if (req.getParameter("deleteFile__member__0__extra__profileImg__1") != null) {
			genFileService.deleteGenFiles("member", rq.getLoginedMemberId(), "extra", "profileImg", 1);
		}

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		for (String fileInputName : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(fileInputName);

			if (multipartFile.isEmpty() == false) {
				genFileService.save(multipartFile, rq.getLoginedMemberId());
			}
		}
		
		memberService.doModify(rq.getLoginedMemberId(), nickname, cellphoneNum, email);
		
		return Utility.jsReplace("회원정보가 수정되었습니다", "myPage");
	}
	
	@RequestMapping("/usr/member/passWordModify")
	public String passWordModify(String memberModifyAuthKey) {
		
		if (Utility.empty(memberModifyAuthKey)) {
			return rq.jsReturnOnView("회원 수정 인증코드가 필요합니다", true);
		}

		ResultData checkMemberModifyAuthKeyRd = memberService.checkMemberModifyAuthKey(rq.getLoginedMemberId(), memberModifyAuthKey);

		if (checkMemberModifyAuthKeyRd.isFail()) {
			return rq.jsReturnOnView(checkMemberModifyAuthKeyRd.getMsg(), true);
		}
		
		return "usr/member/passWordModify";
	}
	
	@RequestMapping("/usr/member/doPassWordModify")
	@ResponseBody
	public String doPassWordModify(String memberModifyAuthKey, String loginPw, String loginPwConfirm) {
		
		if (Utility.empty(memberModifyAuthKey)) {
			return Utility.jsHistoryBack("회원 수정 인증코드가 필요합니다");
		}

		ResultData checkMemberModifyAuthKeyRd = memberService.checkMemberModifyAuthKey(rq.getLoginedMemberId(), memberModifyAuthKey);

		if (checkMemberModifyAuthKeyRd.isFail()) {
			return Utility.jsHistoryBack(checkMemberModifyAuthKeyRd.getMsg());
		}
		
		if(Utility.empty(loginPw)) {
			return Utility.jsHistoryBack("새 비밀번호를 입력해주세요");
		}
		
		if(Utility.empty(loginPwConfirm)) {
			return Utility.jsHistoryBack("새 비밀번호 확인을 입력해주세요");
		}
		
		if(loginPw.equals(loginPwConfirm) == false) {
			return Utility.jsHistoryBack("비밀번호가 일치하지 않습니다");
		}
		
		memberService.doPassWordModify(rq.getLoginedMemberId(), Utility.sha256(loginPw));
		
		return Utility.jsReplace("비밀번호가 수정되었습니다", "myPage");
	}
	
	@RequestMapping("/usr/member/findLoginId")
	public String findLoginId() {
		return "usr/member/findLoginId";
	}

	@RequestMapping("/usr/member/doFindLoginId")
	@ResponseBody
	public String doFindLoginId(String name, String email) {
		if (Utility.empty(name)) {
			return Utility.jsHistoryBack("이름을 입력해주세요");
		}
		if (Utility.empty(email)) {
			return Utility.jsHistoryBack("이메일을 입력해주세요");
		}

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return Utility.jsHistoryBack("입력하신 정보와 일치하는 회원이 없습니다");
		}

		return Utility.jsReplace(Utility.f("회원님의 아이디는 [ %s ] 입니다", member.getLoginId()), "login");
	}
	
	@RequestMapping("/usr/member/findLoginPw")
	public String findLoginPw() {
		return "usr/member/findLoginPw";
	}

	@RequestMapping("/usr/member/doFindLoginPw")
	@ResponseBody
	public String doFindLoginPw(String loginId, String name, String email) {
		if (Utility.empty(loginId)) {
			return Utility.jsHistoryBack("아이디를 입력해주세요");
		}
		if (Utility.empty(name)) {
			return Utility.jsHistoryBack("이름을 입력해주세요");
		}
		if (Utility.empty(email)) {
			return Utility.jsHistoryBack("이메일을 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Utility.jsHistoryBack("입력하신 정보와 일치하는 회원이 없습니다");
		}

		if (member.getName().equals(name) == false) {
			return Utility.jsHistoryBack("이름이 일치하지 않습니다");
		}

		if (member.getEmail().equals(email) == false) {
			return Utility.jsHistoryBack("이메일이 일치하지 않습니다");
		}

		ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempLoginPwByEmail(member);

		return Utility.jsReplace(notifyTempLoginPwByEmailRd.getMsg(), "login");
	}
}
