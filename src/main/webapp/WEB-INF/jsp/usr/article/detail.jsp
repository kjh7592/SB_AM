<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="ARTICLE DETAIL"/>
<%@ include file="../common/head.jsp" %>
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<table>
				<colgroup>
					<col width="200" />
				</colgroup>
				
				<tbody>
						<tr>
							<th>번호</th>
							<th>${article.id}</th>
						</tr>
						<tr>
							<th>작성날짜</th>
							<th>${article.regDate}</th>
						</tr>
						<tr>
							<th>수정날짜</th>
							<th>${article.updateDate}</th>
						</tr>
						<tr>
							<th>작성자</th>
							<th>${article.writerName}</th>
						</tr>
						<tr>
							<th>제목</th>
							<th>${article.title}</th>
						</tr>
						<tr>
							<th>내용</th>
							<th>${article.body}</th>
						</tr>
				</tbody>
			</table>
		</div>	
		<div class="btns">
			<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
			<c:if test="${article.actorCanChangeData }">
				<a class="btn-text-link" href="modify?id=${article.id }">수정</a>
				<a class="btn-text-link" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;" href="doDelete?id=${article.id }">삭제</a>
			</c:if>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp" %>