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
		sido();
	})
	function sido() {
		$.ajax({
			url : "${ pageContext.request.contextPath }/admin/sido",
			type : 'get',
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					$('#sido').append('<option value="'+data[i].bjdCode.substr(0,2)+'">' + data[i].bjdName + '</option>') 
				}
			}
		})
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
	<section class="margin-top-100 container-fluid">
		<div class="conatiner">
			<div class="row justify-content-center">
				<div class="col-3">
					<select id="sido">
						<option value="00" selected="selected">시/도</option>
					</select>
				</div>
				<div class="col-3">
					<select id="sigungu">
						<option value="000" selected="selected">시/군/구</option>
					</select>
				</div>
				<div class="col-3">
					<select id="eupmyeondong">
						<option value="000" selected="selected">읍/면/동</option>
					</select>
				</div>
			</div>
		</div>
	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
</body>
</html>