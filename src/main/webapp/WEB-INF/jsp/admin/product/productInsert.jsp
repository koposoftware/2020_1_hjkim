<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		location.href = "{ pageContext.request.contextPath }/board/write";
	}
	function doAction(boardNo) {
		location.href = "${ pageContext.request.contextPath }/board/" + boardNo;
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
			<hr>
			<h2>대출 상품 등록</h2>
			<hr>
			<br>
			<form:form commandName="loanVO" method="post" enctype="multipart/form-data">
				<table class="margin-bottom-20 table table-border">
					<tr>
						<th width="23%">상품명</th>
						<td><form:input path="productName" class="form-control"/> <form:errors path="productName" class="error" /></td>
					</tr>
					<tr>
						<th>상품 상세설명</th>
						<td><form:textarea path="productContent" rows="7" cols="50" class="form-control"/> <form:errors path="productContent" class="error" /></td>
					</tr>
					<tr>
						<th>상품 필수 서류</th>
						<td><form:textarea path="productNeedDoc" rows="3" cols="50" class="form-control"/></td>
					</tr>
					<tr>
						<th>상품설명서</th>
						<td>
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="customFile" name="file"> 
								<label class="custom-file-label" for="customFile">Choose file</label>
							</div> 
						</td>
					</tr>
				</table>
				<button type="submit" class="btn btn-outline-info float-right">등록하기</button>
				<button type="button" class="btn btn-outline-success float-right" onclick="location.href='${pageContext.request.contextPath}/admin/productList'">목록으로</button>
			</form:form>
		</div>
	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
</body>
</html>