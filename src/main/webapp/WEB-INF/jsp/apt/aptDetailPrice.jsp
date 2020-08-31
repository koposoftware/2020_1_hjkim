<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered">
	<tr>
		<th>거래년월</th>
		<th>거래일</th>
		<th>가격</th>
		<th>면적</th>
		<th>층수</th>
	</tr>
	<c:forEach var="aptPrice" items="${ aptPriceList }">
		<tr>
			<td>${ aptPrice.yymm }</td>
			<td>${ aptPrice.dd }</td>
			<td>${ aptPrice.price }</td>
			<td>${ aptPrice.area }</td>
			<td>${ aptPrice.floor }</td>
		</tr>
	</c:forEach>
</table>
<button onclick="goBackToMap()" class="btn btn-outline-info">지도로 돌아가기</button>