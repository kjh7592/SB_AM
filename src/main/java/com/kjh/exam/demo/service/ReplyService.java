package com.kjh.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.Util.Utility;
import com.kjh.exam.demo.repository.ReplyRepository;
import com.kjh.exam.demo.vo.Article;
import com.kjh.exam.demo.vo.Reply;
import com.kjh.exam.demo.vo.ResultData;
import com.kjh.exam.demo.vo.Rq;

@Service
public class ReplyService {
	
	private ReplyRepository replyRepository;
	
	@Autowired
	public ReplyService(ReplyRepository replyRepository, Rq rq) {
		this.replyRepository = replyRepository;
	}

	public ResultData<Integer> writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {
		replyRepository.writeReply(loginedMemberId, relTypeCode, relId, body);
		int id = replyRepository.getLastInsertId();
		return ResultData.from("S-1", Utility.f("%d번 댓글이 생성되었습니다", id), "id", id);
	}

	public List<Reply> getForPrintReplies(int loginedMemberId, String relTypeCode, int id) {
		List<Reply> replies = replyRepository.getForPrintReplies(relTypeCode, id);
		
		for(Reply reply : replies) {
			actorCanChangeData(loginedMemberId, reply);
		}
		
		
		return replies;
	}
	
	private void actorCanChangeData(int loginedMemberId, Reply reply) {
		if(reply == null) {
			return;
		}
		
		ResultData actorCanChangeDataRd = actorCanMD(loginedMemberId, reply);
		reply.setActorCanChangeData(actorCanChangeDataRd.isSuccess());
	}
	
	public ResultData actorCanMD(int loginedMemberId, Reply reply) {
		
		if(reply == null) {
			return ResultData.from("F-1", Utility.f("해당 댓글은 존재하지 않습니다"));
		}
		
		if (loginedMemberId != reply.getMemberId()) {
			return ResultData.from("F-B", "해당 댓글에 대한 권한이 없습니다");
		}
		
		return ResultData.from("S-1", "가능");
	}

	public Reply getReply(int id) {
		return replyRepository.getReply(id);
	}

	public void deleteArticle(int id) {
		replyRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String body) {
		replyRepository.modifyArticle(id, body);
	}
	
	public Reply getForPrintReply(int id) {
		return replyRepository.getForPrintReply(id);
	}

}
