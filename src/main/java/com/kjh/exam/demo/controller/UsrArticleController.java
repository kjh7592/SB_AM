package com.kjh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.exam.demo.Util.Utility;
import com.kjh.exam.demo.service.ArticleService;
import com.kjh.exam.demo.vo.Article;
import com.kjh.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	
	// 의존성 주입
	private ArticleService articleService;
	
	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
	// 액션매서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = articleService.writeArticle(title, body);
		
		Article artilce = articleService.getArticle(id);
		
		return artilce;
	}
	
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		
		articleService.deleteArticle(id);
		
		return id + "번 글이 삭제되었습니다";
	}
	
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return id + "번 글은 존재하지 않습니다";
		}
		
		articleService.modifyArticle(id, title, body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return ResultData.from("F-1", Utility.f("%d번 글은 존재하지 않습니다", id));
//			return id + "번 글은 존재하지 않습니다";
		}
		
		return ResultData.from("S-1", Utility.f("%d번 게시물 입니다", id), article);
	}

	
}
