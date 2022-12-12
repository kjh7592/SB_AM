<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="DETAIL"/>
<%@ include file="../common/head.jsp" %>
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<table>
				<colgroup>
					<col width="200"/>
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
							<th>${article.memberId}</th>
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
			<button type="button" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp" %>