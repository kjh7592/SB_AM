package com.kjh.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kjh.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
		
		public void writeArticle(int memberId, int boardId, String title, String body);
		
		public Article getArticle(int id);
		
		public List<Article> getArticles(int boardId, int limitStart, int itemInAPage);
		
		public void deleteArticle(int id);
		
		public void modifyArticle(int id, String title, String body);

		public int getLastInsertId();

		public Article getForPrintArticle(int id);

		public int getArticlesCount(int boardId);
}
