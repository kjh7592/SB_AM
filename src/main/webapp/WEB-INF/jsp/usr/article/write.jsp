<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="ARTICLE WRITE" />
<%@ include file="../common/head.jsp" %>

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<form action="doWrite" method="POST">
			<div class="table table-zebra w-full">
				<table>
					<colgroup>
						<col width="200" />
					</colgroup>
					
					<tbody>
						<tr>
							<th>게시판</th>
							<th>
<!-- 								<select name="boardId" > -->
<!-- 									<option>게시판을 선택해주세요</option> -->
<!-- 									<option value="1">공지사항</option> -->
<!-- 									<option value="2">자유</option> -->
<!-- 								</select> -->
								<label>
									<input type="radio" name="boardId" value="1" checked/>&nbsp;공지사항
								</label>
								&nbsp;&nbsp;&nbsp;
								<label>
									<input type="radio" name="boardId" value="2" />&nbsp;자유
								</label>
							</th>
						</tr>
						<tr>
							<th>제목</th>
							<th><input class="input input-bordered input-error w-full max-w-xs" type="text" name="title" placeholder="제목을 입력해주세요" /></th>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="textarea textarea-error" name="body" placeholder="내용을 입력해주세요" ></textarea></td>
						</tr>
						<tr>
							<th colspan="2"><button class="btn btn-outline btn-primary">작성</button></th>
						</tr>
					</tbody>
				</table>
			</div>	
		</form>
		<div class="btns mt-2">
			<button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp" %>