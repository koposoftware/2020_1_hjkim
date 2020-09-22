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
	function goWriteForm() {
		//location.href = "writeForm.jsp";
		location.href = "${ pageContext.request.contextPath }/admin/insertProduct";
	}
	function doAction(productNo) {
		console.log('productNo' + productNo)
		location.href = "${ pageContext.request.contextPath }/admin/product/" + productNo;
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
	<section class="margin-top-100 container-fluid margin-bottom-60 product">
		<div align="center">
			<hr width="100%">
			<h2>상품 목록</h2>
			<hr width="100%">
			<table class="table table-hover product-table">
				<tr>
					<th>번호</th>
					<th>상품 이름</th>
				</tr>
				<c:forEach items="${ loanList }" var="loan" varStatus="loop">
					<tr onclick="javascript:doAction('${ loan.productCode }')">
						<td>${ loan.productCode }</td>
						<td>
							<c:out value="${ loan.productName }" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<button type="button" class="btn btn-outline-success float-right" onclick="goWriteForm()">상품 등록하기</button>
		</div>
	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
</body>
</html>