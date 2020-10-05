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
<script>
	var ws;
	var userNo = ${loginVO.userNo};
	
	var targetNo;
	var bool = false;
	function connect() {
		//웹소켓 객체 생성하는 부분
		//핸들러 등록 (연결 생성, 메시지 수신, 연결종료)

		//url 연결할 서버의 경로
		ws = new SockJS("<c:url value="/chatServer"/>");
		ws.onopen = function() {
			console.log('연결생성');
			register();
		};

		ws.onmessage = function(e) {
			console.log('메시지 받음');
			var data = e.data;
			console.log(data.indexOf("@connect"))
			if (data.indexOf("kaptCode:") != -1) {
				addApt(data.substr("kaptCode:".length));
			} else if (data.indexOf("@connect") != -1) {
				console.log("@Connect")
				addConnectMsg(data.substr("@connect : ".length))
			} else {
				addMsg(data);
			}
		};
		ws.onclose = function() {
			console.log('연결 끊김');
		}
	}

	function addMsg(msg) {
		var content = '';
		content += '<li class="left clearfix">'
		content += '	<span class="chat-img pull-left">'
		content += '		<img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />'
		content += '	</span>'
		content += '	<div class="chat-body clearfix">'
		content += '		<div class="header">'
		content += '			<strong class="primary-font">고객</strong>'
		content += '		</div>'
		content += '		<p>' + msg + '</p>'
		content += '	</div>'
		content += '</li>'

		$('.chat').append(content);
	}
	function addApt(apt) {
		if ($(".card-text").html() == '') {
			$.ajax({
				url : '${pageContext.request.contextPath}/apt/' + apt + '/detailInfo',
				type : 'get',
				success : function(data) {
					$(".card-text").html(data)
					addAptNameSummary(apt);
				}
			})
		}
	}
	function addConsultingSummary(title, content){
		if(content == 'nowday'){
			var year = (new Date()).getFullYear();
			var month = (new Date()).getMonth() + 1 ;
			var day = (new Date()).getDate();
			content = year + '년 ' + month +'월 ' + day +"일"
		}
		var innerHtmlContent = ''
		innerHtmlContent +='<tr>'
		innerHtmlContent +='	<td class="title">' + title +'</td>'
		innerHtmlContent +='	<td><input type="text" size="20" style="width:100%; border: 0;" value="' + content + '"></td>'
		innerHtmlContent +='</tr>'
		$('#consultingSummary').append(innerHtmlContent);
		
	}
	function addConsultingSummaryTextArea(title, content){
		if(content == 'nowday'){
			var year = (new Date()).getFullYear();
			var month = (new Date()).getMonth() + 1 ;
			var day = (new Date()).getDate();
			content = year + '년 ' + month +'월 ' + day +"일"
		}
		var innerHtmlContent = ''
		innerHtmlContent +='<tr>'
		innerHtmlContent +='	<td class="title">' + title +'</td>'
		innerHtmlContent +='	<td><textarea size="20" style="width:100%; border: 0;">'+ content +'</textarea></td>'
		innerHtmlContent +='</tr>'
		$('#consultingSummary').append(innerHtmlContent);
		
	}
	/* hanabang 온라인 상담 요약 중 아파트 정보를 출력하기 위한 함수 */
	function addAptNameSummary(apt){
		$.ajax({
			url : '${pageContext.request.contextPath}/apt/aptBasic/' + apt,
			type : 'get',
			success : function(data) { 
				addConsultingSummary('선택하신 아파트', data.aptBasicVO.kaptName);
				addConsultingSummary('아파트 주소', data.aptDetailVO.kaptAddr);
			}
		})
	}
	function addConnectMsg(msg) {
		var content = '';
		content += '<li class="left clearfix">'
		content += '		<span class="chat-img pull-left">'
		content += '			<img src="http://placehold.it/50/FF0000/fff&text=@" class="img-circle" />'
		content += '		</span>'
		content += '	<div class="chat-body clearfix">'
		content += '		<div class="header">'
		content += '			<strong class="primary-font">알림</strong>'
		content += '		</div>'
		content += '		<p>' + msg + '</p>'
		content += '	</div>'
		content += '</li>'
		$('.chat').append(content)
	}
	//메시지 수신을 위한 서버에 id 등록
	function register() {
		var msg = {
			type : "counselor",
			userid : userNo
		};
		console.log(userNo)
		ws.send(JSON.stringify(msg))
	}

	function sendMsg() {
		console.log("sendMsg에서 targetNo" + targetNo)
		var msg = {
			type : 'chat', //메시지를 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
			userid : userNo,
			message : $('#chatMsg').val()
		};
		ws.send(JSON.stringify(msg));
		
	};

	function sendNoticeMsg(noticeMsg) {
		var msg = {
			type : 'chat', //메시지를 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
			userid : userNo,
			message : noticeMsg
		};
		ws.send(JSON.stringify(msg));
		
	};
	


	$(function() {
		connect();
		loadHistory();
		$('#consultingSummary').empty(); // 상담 요약 테이블을 비운다.
		addConsultingSummary('상담일자', 'nowday');// 상담 요약 테이블에 상담 일자를 등록한다.
		addRecommendLoan(); // 상담 요약 테이블에서 사용할 대출 추천 상품 리스트를 불러온다.
		$('#btnSend').on("click", function() {

			console.log("btnSend클릭")
			var content = '';
			content += '<li class="right clearfix">'
			content += '	<span class="chat-img pull-right">'
			content += '		<img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />'
			content += '	</span>'
			content += '	<div class="chat-body clearfix">'
			content += '		<div class="header">'
			content += '			<strong class="pull-right primary-font">나</strong>'
			content += '		</div>'
			content += '		<p>' + $('#chatMsg').val() +'</p>'
			content += '	</div>'
			content += '</li>'
			$('.chat').append(content);
			sendMsg();
			$('#chatMsg').val("");
		})
		
		/* 대출 추천 상품 선택시 해당 대출상품 출력 */
		$('#recommendLoan').on("change",function(){
			var code = $('#recommendLoan option:selected').attr("id");
			if(code != '0'){
				$.ajax({
					url: '${pageContext.request.contextPath}/counselor/loadLoanProduct',
					type: 'post',
					data : {
						productCode : code
					},
					success: function(data){
						addConsultingSummary('대출 추천 상품', data.productName);
						addConsultingSummary('가입시 필요 서류', data.productNeedDoc);
						addConsultingSummaryTextArea('대출 상세 내용', data.productContent);
						addConsultingSummaryTextArea('대출 부가 설명', '');
					}
				})
			}
		})
		
		/* 고객에게 요약 데이터 전송 */
		$('#summaryBtn').click(function() {
			var dataArrayToSend = [];
			$("#summaryTable tbody tr").each(function(){
				var tableData = {'title' : $(this).find(".title").text() , 'content' : $(this).find("td input[type='text'], td textarea").val()};
				dataArrayToSend.push(tableData);
			})
			var msg = {
				type : 'table', //메시지를 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
				userid : userNo,
				msg : JSON.stringify(dataArrayToSend)
			};
			ws.send(JSON.stringify(msg));
		})
		
		$('#productPDFBtn').click(function(){
			console.log("productPDFBtn클릭")
			var product = $('#productPdfSelect option:selected').attr("value");
			if(product != "0"){
				sendPdf(product);
			}
		})
	})
	/* pdf전송 */
	function sendPdf(productCode){
		var msg = {
				type : 'pdf', //메시지를 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
				userid : userNo,
				message : productCode
			};
			ws.send(JSON.stringify(msg))
			
			addMsgForMe("PDF를 전송하였습니다. 하단을 확인해주세요 :-)")
			sendNoticeMsg("PDF를 전송하였습니다. 하단을 확인해주세요 :-)")
	}
	
	function closeSocket() {
		ws.close();
	}
	function loadHistory() {
		$.ajax({
			url : '${pageContext.request.contextPath}/chat/loading',
			type : 'post',
			data : {
				userNo : userNo
			},
			success : function(data) {
				$('.chat').append(data)
			}
		})
	}
	/* 자동완성 send */
	function autoWordSend(autoNo){
		var autoWord = $('#auto'+autoNo).text()
		var msg = {
			type : 'chat', //메시지를 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
			userid : userNo,
			message : autoWord
		};
		addMsgForMe(autoWord)
		ws.send(JSON.stringify(msg))
	}
	
	function addMsgForMe(msg){
		var content = '';
		content += '<li class="right clearfix">'
		content += '	<span class="chat-img pull-right">'
		content += '		<img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />'
		content += '	</span>'
		content += '	<div class="chat-body clearfix">'
		content += '		<div class="header">'
		content += '			<strong class="pull-right primary-font">나</strong>'
		content += '		</div>'
		content += '		<p>' + msg +'</p>'
		content += '	</div>'
		content += '</li>'
		$('.chat').append(content);
	}
	
	function addRecommendLoan(){
		$.ajax({
			url: '${pageContext.request.contextPath}/counselor/loadLoanProduct',
			type: 'get',
			success: function(data){
				$('#recommendLoan').empty();
				$('#recommendLoan').html("<option id='0'>대출 추천 상품을 선택하세요.</option>")
				for(let i = 0; i < data.length; i++){
					var content = '';
					content += '<option id="' + data[i].productCode + '">' + data[i].productName +'</option>'
					$('#recommendLoan').append(content);
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
		<div class="container">
			<ul class="nav justify-content-end sub-nav">
				<li class="nav-item"><a class="nav-link" href="#">온라인 상담</a></li>
			</ul>
			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="card card-info card-all">
						<div class="panel-heading chat-heading">
							<span class="glyphicon glyphicon-comment"></span> 온라인 상담
							<div class="btn-group pull-right">
								<div class="btn-group">
									<button type="button" class="btn btn-danger" onclick="closeSocket()">채팅 종료</button>
								</div>
							</div>
						</div>
						<div class="card-body chat-body">
							<ul class="chat">
								<!-- 채팅이 들어간다. -->
							</ul>
						</div>
						<div class="card-footer chat-footer">
							<div class="input-group mb-3">
								<input type="text" id="chatMsg" class="form-control chat-input-form" placeholder="채팅을 입력해주세요" aria-label="Recipient's username" aria-describedby="btnSend">
								<div class="input-group-append">
									<button class="btn btn-info chat-btn" type="button" id="btnSend">보내기</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card col-md-5">
					<div class="card-body">
						<h5 class="card-title">고객이 선택한 아파트</h5>
						<p class="card-text apt-card-detail"></p>
					</div>
				</div>
			</div>
			<div class="row justify-content-center margin-top-20">
				<div class="card col-md-10">
					<div class="card-body">
						<h3 class="card-title">대출 상품 pdf</h3>
						<div class="row auto-word-row">
							<div class="col-12">
								<div class="card-body">
									<div class="container mt-3 admin-auto-content">
										<div class="input-group">
											<select class="custom-select" id="productPdfSelect" aria-label="Example select with button addon">
												<option selected value="0">상품을 선택하세요</option>
												<c:forEach var="file" items="${ fileList }">
													<option value="${ file.fileNo }">${ file.orgFileName }&nbsp;&nbsp;&nbsp;${ file.fileSize }(kb)</option>
												</c:forEach>
											</select>
											<div class="input-group-append">
												<button class="btn btn-outline-secondary" type="button" id="productPDFBtn">상품정보 보내기</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row justify-content-center margin-top-20">
				<div class="card col-md-10">
					<div class="card-body">
						<h3 class="card-title">자동완성문구</h3>
						<div class="row auto-word-row">

							<div class="card col-6">
								<div class="card-title">
									<h4 class="auto-title">공통 문구</h4>
									<input class="form-control" id="searchAdmin" type="text" placeholder="Search..">
								</div>
								<div class="card-body">
									<div class="container mt-3 admin-auto-content">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>no</th>
													<th>content</th>
												</tr>
											</thead>
											<tbody id="adminTable">
												<c:forEach items="${ adminAutoList }" var="adminAuto" varStatus="list">
													<tr onclick="autoWordSend(${ adminAuto.autoNo })">
														<td>${ list.count }</td>
														<td id="auto${ adminAuto.autoNo }">${ adminAuto.content }</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="card col-6">
								<div class="card-title">
									<h4 class="auto-title">
										My 문구<i class="fas fa-plus-circle float-right" data-toggle="modal" data-target="#myModal"></i>
									</h4>
									<input class="form-control" id="searchCounselor" type="text" placeholder="Search..">
								</div>
								<div class="card-body">
									<div class="container mt-3 admin-auto-content">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>no</th>
													<th>content</th>
												</tr>
											</thead>
											<tbody id="counselorTable">
												<c:forEach items="${ counselorAutoList }" var="counselorAuto" varStatus="list">
													<tr onclick="autoWordSend(${ counselorAuto.autoNo })">
														<td>${ list.count }</td>
														<td id="auto${ counselorAuto.autoNo }">${ counselorAuto.content }</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row justify-content-center margin-top-20">
				<div class="card col-md-10">
					<div class="card-body">
						<h3 class="card-title">고객 지원</h3>
						<div class="row auto-word-row">
							<div class="card col-12">
								<div class="card-title">
									<h4 class="auto-title">온라인 상담 요약</h4>
									<select id="recommendLoan">
									</select>
								</div>
								<div class="card-body">
									<div class="container mt-3 admin-auto-content">
										<table class="table table-bordered" id="summaryTable">
											<thead>
												<tr>
													<th colspan="2">HanaBang 온라인 상담 요약</th>
												</tr>
											</thead>
											<tbody id="consultingSummary">
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<button class="btn btn-outline-info btn-block" id="summaryBtn">고객에게 전송하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- footer Section -->
	<footer id="footer" class="footer margin-top-60">
		<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
	</footer>
	<!-- End of footer -->
	<script>
		$(document).ready(function() {
			$("#searchAdmin").on("keyup", function() {
				var value = $(this).val().toLowerCase();
				$("#adminTable tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});
			$("#searchCounselor").on("keyup", function() {
				var value = $(this).val().toLowerCase();
				$("#counselorTable tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});
			
		});
		function registerWord(){
			console.log($('.auto-word').val())
			$.ajax({
				url: '${pageContext.request.contextPath}/counselor/autoWord',
				type: 'POST',
				data: {
					word: $('.auto-word').val()
				},success: function(data){
					console.log('등록성공')
					$('#counselorTable').html(data);
				}
			})	
		}
		
	</script>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="opacity: 2; z-index: 1050; overflow: hidden;">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">자동문구 등록</h3>
				</div>
				<div class="modal-body">
					<div>자동문구를 입력해주세요.</div>
					<textarea rows="5" class="auto-word"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-default auto-word-btn" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-sm btn-primary auto-word-btn" onclick="registerWord()" data-dismiss="modal">등록</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>