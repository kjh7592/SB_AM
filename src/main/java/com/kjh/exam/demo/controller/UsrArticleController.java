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

import jakarta.servlet.http.HttpSession;

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
	public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {

		if(httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		
		if (Utility.empty(title)) {
			return ResultData.from("F-1", Utility.f("제목을 입력해주세요"));
		}

		if (Utility.empty(body)) {
			return ResultData.from("F-2", Utility.f("내용을 입력해주세요"));
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(loginedMemberId, title, body);

		Article article = articleService.getArticle((int) writeArticleRd.getData1());

		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {

		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "게시물 리스트", articles);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
		
		if(httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}

		Article article = articleService.getArticle(id);
		
//		if(article.getMemberId() != (int) httpSession.getAttribute("loginedMemberId")) {
//			return ResultData.from("F-2", "권한이 없습니다");
//		}

		if (article == null) {
			return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다", id));
			}

		articleService.deleteArticle(id);

		return ResultData.from("S-1", Utility.f("%d번 게시물을 삭제했습니다", id), id);		
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(HttpSession httpSession, int id, String title, String body) {

		if(httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다", id));
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-1", Utility.f("%d번 게시물을 수정습니다", id), id);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Utility.f("%d번 글은 존재하지 않습니다", id));
//			return id + "번 글은 존재하지 않습니다";
		}

		return ResultData.from("S-1", Utility.f("%d번 게시물 입니다", id), article);
	}

}
