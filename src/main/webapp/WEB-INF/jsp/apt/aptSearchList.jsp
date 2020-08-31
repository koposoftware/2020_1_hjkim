<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="header-menu">
	<span>검색 목록</span>
</div>

<div class="search-box">
	<c:if test="${ not empty aptBasicAndLatLngList }">
		<c:forEach var="aptBasicAndLatLng" items="${ aptBasicAndLatLngList }">
			<div class="apt-search-one" onclick="aptDetailInfo('${ aptBasicAndLatLng.aptBasicVO.kaptCode }');aptMoveCenter('${ aptBasicAndLatLng.aptLatLngVO.lat }','${ aptBasicAndLatLng.aptLatLngVO.lng }')">
				<div class="apt-click-one-title">아파트 | ${ aptBasicAndLatLng.aptBasicVO.kaptName }</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${ not empty aptBjdCodeList }">
		<c:forEach var="aptBjdCode" items="${ aptBjdCodeList }">
			<div class="apt-search-one" onclick="aptMoveCenter('${ aptBjdCode.lat }','${ aptBjdCode.lng }')">
				<div class="apt-click-one-title">지역 | ${ aptBjdCode.bjdName }</div>
			</div>
		</c:forEach>
	</c:if>
</div>