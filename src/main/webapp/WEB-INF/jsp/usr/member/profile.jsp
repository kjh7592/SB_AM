<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="PROFILE"/>
<%@ include file="../common/head.jsp" %>
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doLogin" method="POST">
			<div class="table-box-type-1">
				<table>
					<colgroup>
						<col width="200" />
					</colgroup>
					
					<tbody>
						<tr>
							<th>가입일</th>
							<th>${member.regDate}</th>
						</tr>
						<tr>
							<th>로그인아이디</th>
							<th>${member.loginId}</th>
						</tr>
						<tr>
							<th>이름</th>
							<th>${member.nickname}</th>
						</tr>
						<tr>
							<th>전화번호</th>
							<th>${member.phoneNumber}</th>
						</tr>
						<tr>
							<th>작성자</th>
							<th>${article.writerName}</th>
						</tr>
					</tbody>
				</table>
			</div>	
		</form>
		<div class="btns">
			<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp" %>