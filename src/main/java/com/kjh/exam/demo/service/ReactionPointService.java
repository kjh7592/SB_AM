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

	public ReactionPoint getReactionPoint(int loginedMemberId, int id) {
		return reationPointRepository.getReactionPoint(loginedMemberId, id);
	}

	public int doGoodReactionPoint(int loginedMemberId, int id) {
		return reationPointRepository.doGoodReactionPoint(loginedMemberId, id);
	}

	public int doBadReactionPoint(int loginedMemberId, int id) {
		return reationPointRepository.doBadReactionPoint(loginedMemberId, id);
	}
	
}
