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
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/modal.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/style.css">
<script src="${ pageContext.request.contextPath }/resources/js/jquery.min.js"></script>
<script src="${ pageContext.request.contextPath }/resources/js/modal.min.js"></script>
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
				$('#chatList').html(data)
			}
		})
	}
	
	/* 클릭시 모달팝업 */
	function chatDetail(chatNo){
		console.log(chatNo)
		$.ajax({
			url : '${ pageContext.request.contextPath }/chat/chatHistoryDetail',
			type : 'post',
			data : {
				chatNo : chatNo
			},
			success : function(data){
				var historyContent = '';
				for(let i = 0; i < data.length; i++){
					historyContent += '<tr>'
					if(data[i].sender == '${loginVO.userNo}'){
						historyContent += '		<th scope="row"> 나 </th>'
					}else {
						historyContent += '		<th scope="row"> 손님 </th>'
					}
					historyContent += '		<td>' + data[i].content +'</td>'
					historyContent += '</tr>'
				}
				$('.historyContent').html(historyContent);
			}
		}) 
		$('#historyDetail').modal('show')
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
	<!-- Modal -->
	<div class="modal fade" id="historyDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="opacity: 2; z-index: 1050; overflow: hidden;">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">상담 대화 내역</h3>
				</div>
				<div class="modal-body">
					<table class="table">
						<tbody class="historyContent">
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-default auto-word-btn" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>