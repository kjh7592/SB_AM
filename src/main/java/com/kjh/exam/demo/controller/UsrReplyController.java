package com.kjh.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.exam.demo.Util.Utility;
import com.kjh.exam.demo.service.ArticleService;
import com.kjh.exam.demo.service.BoardService;
import com.kjh.exam.demo.service.ReactionPointService;
import com.kjh.exam.demo.service.ReplyService;
import com.kjh.exam.demo.vo.Article;
import com.kjh.exam.demo.vo.ReactionPoint;
import com.kjh.exam.demo.vo.ResultData;
import com.kjh.exam.demo.vo.Rq;

@Controller
public class UsrReplyController {

	// 의존성 주입
	private ReplyService replyService;
	private ArticleService articleService;
	private Rq rq;

	@Autowired
	public UsrReplyController(ReplyService replyService, ArticleService articleService, BoardService boardService, Rq rq) {
		this.replyService = replyService;
		this.articleService = articleService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public ResultData doWrite(int id, String relTypeCode) {
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return ResultData.from("F-1", "해당 게시물은 존재하지 않습니다");
		}
		
//		Reply reply = replyService.doWrite(rq.getLoginedMemberId(), relTypeCode, id);
		
		return ResultData.from("S-1", "댓글 가져오기 성공");
	}
	
	@RequestMapping("/usr/reply/doReply")
	@ResponseBody
	public String doReply(int id, String relTypeCode, int point) {
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return Utility.jsHistoryBack("해당 게시물은 존재하지 않습니다");
		}
		
		if(point == 1) {
			return Utility.jsReplace(Utility.f("%d번 글에 좋아요", id), Utility.f("../article/detail?id=%d", id));
		} else {
			return Utility.jsReplace(Utility.f("%d번 글에 싫어요", id), Utility.f("../article/detail?id=%d", id));
		}
	}
	
	@RequestMapping("/usr/reply/delReply")
	@ResponseBody
	public String delReply(int id, String relTypeCode, int point) {
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return Utility.jsHistoryBack("해당 게시물은 존재하지 않습니다");
		}
		
		if(point == 1) {
			return Utility.jsReplace(Utility.f("%d번 글에 좋아요 취소", id), Utility.f("../article/detail?id=%d", id));
		} else {
			return Utility.jsReplace(Utility.f("%d번 글에 싫어요 취소", id), Utility.f("../article/detail?id=%d", id));
		}
	}
}
