<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="결제 성공" />
<%@ include file="../common/head.jsp"%>

<c:set var="paymentKey" value="${paymentKey}" />
<c:set var="orderId" value="${orderId}" />
<c:set var="amount" value="${amount}" />

<script src="https://js.tosspayments.com/v1/payment"></script>

<script>
	
</script>

<section class="my-20">
		<div class="con-3 mx-auto px-3">
			<h1> 결제 성공! </h1>
			<div>결제 키 : ${paymentKey}</div>
			<div>주문번호 : ${orderId}</div>
			<div>가격 : ${amount}</div>
		</div>
</section>






<%@ include file="../common/foot.jsp"%>