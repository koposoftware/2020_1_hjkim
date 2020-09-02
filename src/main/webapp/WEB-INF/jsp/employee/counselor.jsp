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
	<div class="fh5co-slider">
				<div class="container">
					<div class="owl-carousel owl-carousel-main margin-top-100">
						<div>
							<div>
								<img src="${ pageContext.request.contextPath }/resources/images/mainbg.jpg" alt="Free HTML5 Website Template">
							</div>
						</div>
						<div>
							<img src="${ pageContext.request.contextPath }/resources/images/mainbg2.png" alt="Free HTML5 Website Template">
						</div>
					</div>
				</div>
			</div>
			<!-- Slider -->
	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
</body>
</html>