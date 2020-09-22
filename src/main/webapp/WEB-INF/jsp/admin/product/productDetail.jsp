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
	function post_to_url(path, params, method) {
	    method = method || "post"; // 전송 방식 기본값을 POST로
	 
	    
	    var form = document.createElement("form");
	    form.setAttribute("method", method);
	    form.setAttribute("action", path);
	 
	    //히든으로 값을 주입시킨다.
	    for(var key in params) {
	        var hiddenField = document.createElement("input");
	        hiddenField.setAttribute("type", "hidden");
	        hiddenField.setAttribute("name", key);
	        hiddenField.setAttribute("value", params[key]);
	 
	        form.appendChild(hiddenField);
	    }
	 
	    document.body.appendChild(form);
	    form.submit();
	}
	function fn_fileDown(fileNo){
		post_to_url('${pageContext.request.contextPath}/admin/fileDown',{'fileNo': fileNo})
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
			<h2>상품 상세정보</h2>
			<hr width="100%">
			<table class="table table-bordered product-table">
				<tr>
					<th>상품 번호</th>
					<td>${ loanProduct.productCode }</td>
				</tr>
				<tr>
					<th>상품 이름</th>
					<td>${ loanProduct.productName }</td>
				</tr>
				<tr>
					<th>상품 상세설명</th>
					<td>${ loanProduct.productContent }</td>
				</tr>
				<tr>
					<th>상품 필수서류</th>
					<td>${ loanProduct.productNeedDoc }</td>
				</tr>
				<tr>
					<th>파일 목록</th>
					<td>
						<a href="#" onclick="fn_fileDown('${loanFileVO.fileNo}'); return false;">${loanFileVO.orgFileName}</a>(${loanFileVO.fileSize}kb)<br>
					</td>
				</tr>
				
			</table>
			<br>
			<button type="button" class="btn btn-outline-success" onclick="location.href='${pageContext.request.contextPath}/admin/productList'">상품 목록으로</button>
			<button type="button" class="btn btn-outline-info">상품 수정하기</button>
		</div>
	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
</body>
</html>