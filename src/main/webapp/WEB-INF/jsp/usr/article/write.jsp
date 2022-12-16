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