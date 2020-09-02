<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Loader -->
<header id="fh5co-header" role="banner">
	<div class="container">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
		<div id="fh5co-logo">
			<a href="${ pageContext.request.contextPath }"><img src="${ pageContext.request.contextPath }/resources/images/hanabang-logo.png" alt="Free HTML5 Website Template"></a>
		</div>
		<nav id="fh5co-main-nav" role="navigation">
			<ul>
				<li><a href="${ pageContext.request.contextPath }/apt/aptMap">아파트 검색</a></li>
				<li><a href="${ pageContext.request.contextPath }/apt/bookMark">My하나방</a></li>
				<li class="has-sub">
					<div class="drop-down-menu">
						<a href="services.html">대출상담</a>
						<div class="dropdown-menu-wrap">
							<ul>
								<li><a href="${ pageContext.request.contextPath }/onlineConsulting">온라인상담</a></li>
								<li><a href="${ pageContext.request.contextPath }/offlineConsulting">오프라인상담예약</a></li>
							</ul>
						</div>
					</div>
				</li>
				<li><a href="${ pageContext.request.contextPath }/board">고객의소리</a></li>
				<c:choose>
					<c:when test="${ empty loginVO }">
						<li class="cta"><a href="${ pageContext.request.contextPath }/login">로그인</a></li>
						<li class="cta"><a href="${ pageContext.request.contextPath }/join">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="cta"><a href="${ pageContext.request.contextPath }/mypage">마이페이지</a></li>
						<li class="cta"><a href="${ pageContext.request.contextPath }/logout">logout</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
	</div>
</header>
<!-- Header -->