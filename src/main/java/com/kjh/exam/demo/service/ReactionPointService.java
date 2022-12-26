package com.kjh.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.ReationPointRepository;
import com.kjh.exam.demo.vo.ReactionPoint;

@Service
public class ReactionPointService {
	private ReationPointRepository reationPointRepository;

	@Autowired
	public ReactionPointService(ReationPointRepository reationPointRepository) {
		this.reationPointRepository = reationPointRepository;
	}

	public ReactionPoint getReactionPoint(int loginedMemberId, String relTypeCode, int id) {
		return reationPointRepository.getReactionPoint(loginedMemberId, relTypeCode, id);
	}

	public void doReactionPoint(int loginedMemberId, int id, String relTypeCode, int point) {
		reationPointRepository.doReactionPoint(loginedMemberId, id, relTypeCode, point);
	}

	public void delReactionPoint(int loginedMemberId, String relTypeCode, int id) {
		reationPointRepository.delReactionPoint(loginedMemberId, relTypeCode, id);
	}
	
}
