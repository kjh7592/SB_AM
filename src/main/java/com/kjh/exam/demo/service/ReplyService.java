package com.kjh.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.Util.Utility;
import com.kjh.exam.demo.repository.ReplyRepository;
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

	public List<Reply> getForPrintReplies(String relTypeCode, int id) {
		return replyRepository.getForPrintReplies(relTypeCode, id);
	}
	
	
	
}
