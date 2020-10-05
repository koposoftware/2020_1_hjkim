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
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
	var key = '<spring:eval expression="@property['kakaoJavaScrpitkey']"></spring:eval>'
	Kakao.init(key)
	function loginFormWithKakao() {
		Kakao.Auth.loginForm({
			success : function(authObj) {
				Kakao.API.request({
					url : '/v2/user/me',
					success : function(res) {
						res.id = '@k' + res.id;
						var params = {
								id : res.id,
								email : res.kakao_account.email,
								name : res.properties.nickname
						}
						
						post_to_url("<%=request.getContextPath()%>/login", params, "post")
					}
				})
			}
		})
	}
	// 카카오 request에서 세팅한 params를 JSON 형태로 넘긴다. form을 이용해 값을 넘긴다.
	function post_to_url(path, params, method) {

		method = method || "post";
		var form = document.createElement("form");
		form.setAttribute("method", method);
		form.setAttribute("action", path);
		for ( var key in params) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);
			form.appendChild(hiddenField);
		}
		document.body.appendChild(form);
		form.submit();
	}

	<c:if test="${ not empty param.msg }">
		alert('${ param.msg }');
	</c:if>
</script>
</head>
<body class="boxed">
	<!-- Loader -->
	<div class="fh5co-loader"></div>
	<div class="check">
		<div id="wrap">
			<div id="fh5co-page">
				<header>
					<jsp:include page="/WEB-INF/jsp/include/header.jsp" />
				</header>
				<!-- Home Section -->
			</div>
		</div>
	</div>
	<section class="margin-top-120 container-fluid">
		<div class="container margin-bottom-100">
			<ul class="nav justify-content-end sub-nav">
				<li class="nav-item"><a class="nav-link" href="#">로그인</a></li>
			</ul>
			<div class="row justify-content-md-center">
				<div class="col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<strong> 하나방에 오신것을 환영합니다.</strong>
						</div>
						<div class="panel-body">
							<form role="form" action="${ pageContext.request.contextPath }/login" method="POST">
								<div>
									<div class="row">
										<div class="center-block">
											<img class="profile-img" src="${ pageContext.request.contextPath }/resources/images/member/person.png" alt="">
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12 col-md-10  col-md-offset-1 ">
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"> <i class="glyphicon glyphicon-user"></i>
													</span> <input class="form-control login-input-form" placeholder="Username" name="id" type="text" autofocus autocomplete="off">
												</div>
											</div>
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"> <i class="glyphicon glyphicon-lock"></i>
													</span> <input class="form-control login-input-form" placeholder="Password" name="password" type="password" value="">
												</div>
											</div>
											<div class="form-group">
												<input type="submit" class="btn btn-outline-info sign-in" value="Sign in">

											</div>
										</div>
									</div>
								</div>
							</form>
							<div class="row">
								<div class="form-group col-sm-12 col-md-10  col-md-offset-1 ">
									<button onclick="javascript:loginFormWithKakao()" class="btn btn-outline-warning sign-in">카카오로그인</button>

								</div>
							</div>
						</div>
						<div class="panel-footer login-footer">
							 <a href="#" onClick="" class="float-right"> 회원가입 </a>
						</div>
					</div>
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