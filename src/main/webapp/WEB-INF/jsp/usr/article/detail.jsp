<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="ARTICLE DETAIL" />
<%@ include file="../common/head.jsp" %>

<script>
	const params = {};
	params.id = parseInt('${param.id}')

	function ArticleDetail__increseHitCount(){
		
		const localStorageKey = 'article__' + params.id + '__alreadyView';
		
		if(localStorage.getItem(localStorageKey)){
			return;
		}
		localStorage.setItem(localStorageKey, true);
		
		$.get('doIncreseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data){
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');
	}
	
	function ReactionPoint__getReactionPoint(){
		
		$.get('../reactionPoint/getReactionPoint', {
			id : params.id,
			relTypeCode : 'article',
			ajaxMode : 'Y'
		}, function(data){
			if(data.data1.sumReactionPoint > 0){
				let goodBtn = $('#goodBtn');
				goodBtn.removeClass('btn-outline');
				goodBtn.prop('href', '../reactionPoint/delReactionPoint?id=${article.id}&relTypeCode=article&point=1');
			}else if(data.data1.sumReactionPoint < 0){
				let badBtn = $('#badBtn');
				badBtn.removeClass('btn-outline');
				badBtn.prop('href', '../reactionPoint/delReactionPoint?id=${article.id}&relTypeCode=article&point=-1');
			}
		}, 'json');
	}
	
	$(function(){
// 		실전코드
// 		ArticleDetail__increseHitCount();
		ReactionPoint__getReactionPoint();
		
// 		연습코드
		setTimeout(ArticleDetail__increseHitCount, 2000);
	})
	
</script>

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
							<th><span class="badge">${article.id}</span></th>
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
							<th>조회수</th>
							<th><span class="badge article-detail__hit-count">${article.hitCount}</span></th>
						</tr>
						<tr>
							<th>작성자</th>
							<th>${article.writerName}</th>
						</tr>
						<tr>
							<th>추천</th>
							<th>
								<c:if test="${rq.getLoginedMemberId() == 0 }">
<%-- 									<span class="badge">${article.sumReactionPoint}</span> --%>
									<span class="badge">좋아요 : ${article.goodReactionPoint}개</span>
									<br />
									<span class="badge">싫어요 : ${article.badReactionPoint * -1}개</span>
								</c:if>
								<c:if test="${rq.getLoginedMemberId() != 0 }">
									<a id="goodBtn" class="btn btn-xs btn-outline" href="../reactionPoint/doReactionPoint?id=${article.id }&relTypeCode=article&point=1">좋아요👍</a>
									<span class="badge">좋아요 : ${article.goodReactionPoint}개</span>
									<br />
									<a id="badBtn" class="btn btn-xs btn-outline" href="../reactionPoint/doReactionPoint?id=${article.id }&relTypeCode=article&point=-1">싫어요👎</a>
									<span class="badge">싫어요 : ${article.badReactionPoint * -1}개</span>
								</c:if>
							</th>
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
				<a class="btn-text-link btn btn-active btn-accent" href="modify?id=${article.id }">수정</a>
				<a class="btn-text-link btn btn-error" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;" href="doDelete?id=${article.id }">삭제</a>
			</c:if>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jsp" %>