package com.kjh.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.ReplyRepository;
import com.kjh.exam.demo.vo.Rq;

@Service
public class ReplyService {
	
	private ReplyRepository replyRepository;
	private ArticleService articleService;
	private Rq rq;
	
	@Autowired
	public ReplyService(ReplyRepository replyRepository, ArticleService articleService, Rq rq) {
		super();
		this.replyRepository = replyRepository;
		this.articleService = articleService;
		this.rq = rq;
	}
	
	
	
}
