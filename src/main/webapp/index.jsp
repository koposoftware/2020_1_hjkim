<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/WEB-INF/jsp/include/link.jsp" />
</head>
<body class="boxed">

	<!-- Loader -->
	<div class="fh5co-loader"></div>

	<div id="wrap">
		<div id="fh5co-page">
			<!-- Header -->
			<jsp:include page="/WEB-INF/jsp/include/header.jsp" />

			<div class="fh5co-slider">
				<div class="container">
					<div class="owl-carousel owl-carousel-main">
						<div>
							<div>
								<img src="${ pageContext.request.contextPath }/resources/images/mainbg.jpg" alt="Free HTML5 Website Template">
							</div>
						</div>
						<div>
							<img src="${ pageContext.request.contextPath }/resources/images/mainbg.jpg" alt="Free HTML5 Website Template">
						</div>
						<div>
							<img src="${ pageContext.request.contextPath }/resources/images/mainbg.jpg" alt="Free HTML5 Website Template">
						</div>
					</div>
				</div>
			</div>
			<!-- Slider -->
			
			
			<section class="our-webcoderskull padding-lg">
				<div class="container">
					<!-- 			<ul class="row"> -->
					<ul class="row">
						<li class="col-12 col-lg-4">
							<div class="cnt-block equal-hight" style="height: 349px;" OnClick="location.href ='#'">
								<figure>
									<img src="${ pageContext.request.contextPath }/resources/images/mainbtn/search.png" class="img-responsive" alt="">
								</figure>
								<h3>아파트 검색</h3>
								<p>
									아파트 정보, 실거래가를<br>볼 수 있습니다.
								</p>
							</div>
						</li>
						<li class="col-12 col-lg-4">
							<div class="cnt-block equal-hight" style="height: 349px;" OnClick="location.href ='#'">
								<figure>
									<img src="${ pageContext.request.contextPath }/resources/images/mainbtn/save.png" class="img-responsive" alt="">
								</figure>
								<h3>My 하나방</h3>
								<p>
									자신이 저장한 아파트를 <br>볼 수 있습니다.
								</p>
							</div>
						</li>
						<li class="col-12 col-lg-4">
							<div class="cnt-block equal-hight" style="height: 349px;" OnClick="location.href ='#'">
								<figure>
									<img src="${ pageContext.request.contextPath }/resources/images/mainbtn/loan.png" class="img-responsive" alt="">
								</figure>
								<h3>대출 상담</h3>
								<p>
									모바일 상담/ 오프라인 상담예약을<br>할 수 있습니다.
								</p>
							</div>
						</li>
					</ul>
				</div>
			</section>
			
			<!-- footer -->
			<jsp:include page="/WEB-INF/jsp/include/footer.jsp" />
		</div>
	</div>


</body>
</html>