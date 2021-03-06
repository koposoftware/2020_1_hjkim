<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Loader -->
<header id="fh5co-header" role="banner">
	<div class="container">
		<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
		<div id="fh5co-logo">
			<c:choose>
				<c:when test="${ loginVO.type eq 'a' or loginVO.type eq 'A'}">
					<a href="${ pageContext.request.contextPath }/admin"><img src="${ pageContext.request.contextPath }/resources/images/hanabang-logo-admin.png" alt="Free HTML5 Website Template"></a>
				</c:when>
				<c:when test="${ loginVO.type eq 'c' or loginVO.type eq 'C'}">
					<a href="${ pageContext.request.contextPath }/counselor"><img src="${ pageContext.request.contextPath }/resources/images/hanabang-logo-employee.png" alt="Free HTML5 Website Template"></a>
				</c:when>
				<c:otherwise>
					<a href="${ pageContext.request.contextPath }"><img src="${ pageContext.request.contextPath }/resources/images/hanabang-logo.png" alt="Free HTML5 Website Template"></a>
				</c:otherwise>
			</c:choose>
		</div>
		<nav id="fh5co-main-nav" role="navigation" class="my-auto">
			<c:choose>
				<c:when test="${ loginVO.type eq 'a' or loginVO.type eq 'A'}">
					<ul>
						<li><a href="${ pageContext.request.contextPath }/admin/ltvRegister">LTV등록</a></li>
						<li><a href="${ pageContext.request.contextPath }/admin/productList">대출상품</a></li>
						<li><a href="${ pageContext.request.contextPath }/admin/autoWord">자동문구</a></li>
						<li><a href="${ pageContext.request.contextPath }/board">상담사등록</a></li>
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
				</c:when>
				<c:when test="${ loginVO.type eq 'c' or loginVO.type eq 'C'}">
					<ul>
						<li><a href="${ pageContext.request.contextPath }/consulting/online">온라인상담</a></li>
						<li><a href="${ pageContext.request.contextPath }/consulting/offline">자동문구</a></li>
						<li><a href="${ pageContext.request.contextPath }/consulting/history">History</a></li>
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
				</c:when>
				<c:otherwise>
					<ul>
						<li><a href="${ pageContext.request.contextPath }/apt/aptMap">아파트 검색</a></li>
						<li><a href="${ pageContext.request.contextPath }/apt/bookMark">My하나방</a></li>
						<li><a href="${ pageContext.request.contextPath }/chat/online">온라인 상담</a></li>
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
				</c:otherwise>
			</c:choose>
		</nav>
	</div>
</header>
<!-- Header -->