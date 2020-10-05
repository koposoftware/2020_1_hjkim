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
	<section class="margin-top-100 margin-bottom-60 container-fluid product">
		<div align="center">
			<hr>
			<h2>자동 문구 등록</h2>
			<hr>
			<br>
			<form action="${ pageContext.request.contextPath }/admin/autoWord">
				<table class="margin-bottom-20 table table-border">
					<tr>
						<th>자동문구</th>
						<td><textarea name="productContent" rows="7" cols="50" class="form-control"></textarea></td>
					</tr>
				</table>
				<button type="submit" class="btn btn-outline-info float-right">등록하기</button>
				<button type="button" class="btn btn-outline-success float-right" onclick="location.href='${pageContext.request.contextPath}/admin/productList'">목록으로</button>
			</form>
		</div>

	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
</body>
</html>