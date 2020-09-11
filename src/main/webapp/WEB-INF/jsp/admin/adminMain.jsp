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
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/vanilla-notify.css">
<script src="${ pageContext.request.contextPath }/resources/js/vanilla-notify.js"></script>
<script>
	var ws;
	var userNo = ${loginVO.userNo};
	function connect() {
		ws = new SockJS("<c:url value='/chatServer'/>");
		ws.onopen = function() {
			console.log("counselor.jsp에서 웹소켓 연결")
			register();
		};

		ws.onmessage = function(e) {
			var data = e.data;
			//웹소켓 세션이 연결되면 가장 먼저 kaptCode를 보낸다. 첫 글자가 kaptCode:로 시작하면 알림을 보내지 않음
			if(data.indexOf("kaptCode:") == -1){
				notification(data)
			}
		};
		/* ws.onclose = function(){
			console.log('연결끊김')
		}; */
	}

	function register() {
		var msg = {
			type : "counselor",
			userid : userNo
		};
		ws.send(JSON.stringify(msg))
	}

	$(function() {
		connect();
	})
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
	<script>
		function notification(data) {
			vNotify.info({
				text : data,
				title : '새 메시지가 도착했습니다.',
				click : '${pageContext.request.contextPath}/consulting/online',
				fadeInDuration : 1000,
				fadeOutDuration : 1000,
				fadeInterval : 50,
				visibleDuration : 5000, // auto close after 5 seconds
				postHoverVisibleDuration : 500,
				position : "bottomRight", // topLeft, bottomLeft, bottomRight, center
				sticky : false, // is sticky
				showClose : true
			// show close button

			});
		}
	</script>
</body>
</html>