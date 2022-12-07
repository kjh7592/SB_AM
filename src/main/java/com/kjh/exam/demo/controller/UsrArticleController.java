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
	public ResultData doAdd(String title, String body) {

		if (Utility.empty(title)) {
			return ResultData.from("F-1", Utility.f("제목을 입력해주세요"));
		}

		if (Utility.empty(body)) {
			return ResultData.from("F-2", Utility.f("내용을 입력해주세요"));
		}

		ResultData writeArticleRd = articleService.writeArticle(title, body);

		Article article = articleService.getArticle((int) writeArticleRd.getData1());

		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {

		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "게시물 리스트", articles);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 글은 존재하지 않습니다";
		}

		articleService.deleteArticle(id);

		return id + "번 글이 삭제되었습니다";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 글은 존재하지 않습니다";
		}

		articleService.modifyArticle(id, title, body);

		return article;
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Utility.f("%d번 글은 존재하지 않습니다", id));
//			return id + "번 글은 존재하지 않습니다";
		}

		return ResultData.from("S-1", Utility.f("%d번 게시물 입니다", id), article);
	}

}
