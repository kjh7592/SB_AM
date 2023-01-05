<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="APITest" />
<%@ include file="../common/head.jsp"%>

<script>
	const API_KEY = 'OyBCtv%2BjitcpoPqLZ0RPVbW5FkWnvGXyX4aCtLxIh2Acu3CqrQBYYMuC2RUZCp5SXxtHT4QVcsjD%2Fw%2BH2YmrIA%3D%3D';
	
	async function getData() {
		const url = 'http://apis.data.go.kr/1790387/covid19HospitalBedStatus/covid19HospitalBedStatusJson?serviceKey=' + API_KEY;
		
		// await를 쓰려면 함수명 앞에 async가 있어야 함
		// await은 요청해서 받아와야 하는 것들을 모두 받을 때까지 기다리겠다라는 것
		const response = await fetch(url);
		const data = await response.json();
		
		console.log(data);
		
		$('.APITest').html(data.response.result[0].itsv_bed_avlb);
	}
	
	getData();
	
	
</script>

<div class="APITest"></div>

<%@ include file="../common/foot.jsp"%>