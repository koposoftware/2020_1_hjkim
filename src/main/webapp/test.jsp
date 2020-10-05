<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/bootstrap.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/owl.carousel.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/owl.theme.default.min.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/modal.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/style.css">
<script src="${ pageContext.request.contextPath }/resources/js/jquery.min.js"></script>
<script src="${ pageContext.request.contextPath }/resources/js/modal.min.js"></script>
</head>
<body>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Launch demo modal</button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>