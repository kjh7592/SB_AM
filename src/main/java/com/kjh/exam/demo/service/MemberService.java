package com.kjh.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.MemberRepository;
import com.kjh.exam.demo.vo.Member;

@Service
public class MemberService {
	
	private MemberRepository memberRepository;
	
	// 생성자
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		Member existsMember = getMemberByLoginId(loginId);
		
		if(existsMember != null) {
			return -1;
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return memberRepository.getLastInssertId();
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
	public Member getMemberByLoginId(String LoginId) {
		return memberRepository.getMemberByLoginId(LoginId);
	}
	
}