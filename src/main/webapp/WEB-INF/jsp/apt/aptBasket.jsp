<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>하나방</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/WEB-INF/jsp/include/link.jsp" />
<script>
	$(function() {
		selectList(1,1);
	})
	function selectList(page,range) {
		$.ajax({
			url : '${ pageContext.request.contextPath }/apt/bookMark',
			type : 'post',
			data : {
				page : page,
				range : range
			}
		})
	}
	function goAptDetail(kaptCode){
		location.href="${pageContext.request.contextPath}/apt/bookMark/" + kaptCode
	}
</script>
</head>
<body class="boxed">
	<!-- Loader -->
	<div class="fh5co-loader"></div>
	<div id="wrap">
		<div id="fh5co-page">
			<header>
				<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
			</header>
			<!-- Home Section -->
		</div>
	</div>
	<section class="margin-top-120 container-fluid margin-bottom-40">
		<ul class="nav margin-top-100 navbar fixed-top justify-content-end sub-nav">
			<li class="nav-item"><a class="nav-link" href="#">My 하나방</a></li>
		</ul>
		<div class="row margin-top-60 justify-content-center basket-list">
			<c:if test="${ empty aptInfo}">
				<div class="jumbotron">
					<h1 class="display-4">아직 관심 아파트가 없어요!</h1>
					<p class="lead">하나방은 아파트 실거래가 정보 제공/대출가능금액조회/온라인 상담 을 제공하는 서비스입니다.</p>
					<hr class="my-4">
					<p>관심 아파트를 보기 위해서는 먼저 아파트 즐겨찾기를 해주세요.</p>
					<a class="btn btn-info btn-lg" href="${ pageContext.request.contextPath }/apt/aptMap" role="button">아파트 보러가기</a>
				</div>
			</c:if>
			<c:if test="${ not empty aptInfo }">
				<div class="col-md-9">
					<c:forEach items="${ aptInfo }" var="apt">
						<div class="col-md-4 baseket-content-parent">
							<div class="contact-box center-version basket-content" onclick="goAptDetail('${apt.aptBasicVO.kaptCode}')">
								<h3 class="m-b-xs basket-title">
									<strong>${ apt.aptBasicVO.kaptName }</strong>
								</h3>

								<div class="m-t-md">
									<div class="basket-sub-title">주소</div>
									${ apt.aptDetailVO.kaptAddr }<br> 
									<div class="basket-sub-title">도로명</div>
									${ apt.aptDetailVO.doroJuso }<br> | 동수 |${ apt.aptDetailVO.kaptDongCnt } | 세대수 | ${ apt.aptDetailVO.kaptDaCnt }<br>
								</div>

							</div>
						</div>
					</c:forEach>
					
				</div>
				
			</c:if>
		</div>
		<c:if test="${ not empty aptInfo }">
			<div id="paginationBox" class="row justify-content-center">
				<ul class="pagination col-9 justify-content-end">
					<c:if test="${pagination.prev}">
						<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a></li>
					</c:if>
					<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
						<li class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> "><a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a></li>
					</c:forEach>
					<c:if test="${pagination.next}">
						<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.range}', '${pagination.range}', '${pagination.rangeSize}')">Next</a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
</body>
</html>