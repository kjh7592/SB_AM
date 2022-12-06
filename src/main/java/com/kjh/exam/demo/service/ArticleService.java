package com.kjh.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.exam.demo.repository.ArticleRepository;
import com.kjh.exam.demo.vo.Article;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	// 생성자
	@Autowired
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}
	
	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public int writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);
		
		return articleRepository.getLastInsertId();
	}
	
}