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
<script src="${ pageContext.request.contextPath }/resources/js/paging.js"></script>

<script>

	$(function() {
		selectList(1,1);
	})
	function selectList(page,range) {
		$.ajax({
			url : '${ pageContext.request.contextPath }/chat/chatPagination',
			type : 'post',
			data : {
				userNo : '${ loginVO.userNo }',
				page : page,
				range : range
			},
			success : function(data) {
				console.log(page, range)
				$('#chatList').html(data)
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
		<ul class="nav justify-content-end sub-nav">
			<li class="nav-item"><a class="nav-link" href="#">History</a></li>
		</ul>
		<div class="container">
			<div class="row justify-content-md-center">
				<div id="chatList" class="col-9"></div>
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