<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	var ws;
	var userNo = ${loginVO.userNo};
	var targetNo;
	var summaryJson;
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
			if(data.indexOf("@connect")!= -1){
				addConnectMsg(data.substr("@connect : ".length))
			}else if(data.indexOf("@summary")!= -1){
				addSummary(data.substr("@summary : ".length))
				summaryJson = data.substr("@summary : ".length);
			}else if(data.indexOf("@pdfSend") != -1){
				var pdfCode = data.substr("@pdfSend : ".length);
				pdfAddMsg(pdfCode);
			}else {
				addMsg(data);
			}
		};
		ws.onclose = function() {
			console.log('연결 끊김');
		}
	}
	
	
	/* pdf파일을 상담에 보내는 함수 */
	function pdfAddMsg(pdfCode){
		$.ajax({
			url: '${pageContext.request.contextPath}/chat/pdfLoad/' + pdfCode,
			type: 'get',
			success: function(data){
				console.log('ajax들어옴')
				var pdfContent = '';
	            pdfContent += "<div class='pdf-msg'>"
	            pdfContent += "   <p><a href='#' onclick='fn_fileDown("+ data.fileNo +"); return false;'>" + data.orgFileName + "</a>" + data.fileSize + "(kb)</p>"
	            pdfContent += "</div>";
	            
	            addMsg(pdfContent);
			}
		})
	}
	
	/* 상담내용요약내용을 받는 함수 */
	function addSummary(data){
		var summaryData = JSON.parse(data)
		console.log(summaryData)
		console.log(summaryData.length)
		$('#consultingSummary').empty();
		for(var i = 0; i < summaryData.length; i++){
			var tableContent = '';
			tableContent += '<tr>'
			tableContent += '	<td>' + summaryData[i].title + '</td>'
			tableContent += '	<td>' + summaryData[i].content + '</td>'
			tableContent += '</tr>'
			$('#consultingSummary').append(tableContent)
		}
	}
	function addConnectMsg(msg){
		var connectMsg = '';
		connectMsg += '<li class="left clearfix">'
		connectMsg += '		<span class="chat-img pull-left">'
		connectMsg += '			<img src="http://placehold.it/50/FF0000/fff&text=@" class="img-circle" />'
		connectMsg += '		</span>'
		connectMsg += '		<div class="chat-body clearfix">'
		connectMsg += '			<div class="header">'
		connectMsg += '				<strong class="primary-font">알림</strong>'
		connectMsg += '		</div>'
		connectMsg += '		<p>'+ msg + '</p>'
		connectMsg += '	</div>'
		connectMsg += '</li>'
		$('.chat').html(connectMsg)
	}
	function addMsg(msg) {
		var content = '';
		content += '<li class="left clearfix">'
		content += '	<span class="chat-img pull-left">'
		content += '		<img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />'
		content += '	</span>'
		content += '	<div class="chat-body clearfix">'
		content += '		<div class="header">'
		content += '			<strong class="primary-font">상담사</strong>'
		content += '		</div>'
		content += '		<p>' + msg + '</p>'
		content += '	</div>'
		content += '</li>'

		$('.chat').append(content);
		$('.chat-body').scrollTop($('.chat-body')[0].scrollHeight);
	}
	
	//메시지 수신을 위한 서버에 id 등록
	function register() {
		var msg = {
			type : "user",
			kaptCode : '${ kaptCode }',
			userid : userNo
		};
		ws.send(JSON.stringify(msg))
	}
	function sendMsg() {
		var msg = {
			type : 'chat', //메시지를 구분하는 구분자
			userid : userNo,
			message : $('#chatMsg').val()
		};
		ws.send(JSON.stringify(msg));
	};

	$(function() {
		connect();
		$('#btnSend').on("click", function() {
			var content = '';
			content += '<li class="right clearfix">'
			content += '	<span class="chat-img pull-right">'
			content += '		<img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />'
			content += '	</span>'
			content += '	<div class="chat-body clearfix">'
			content += '		<div class="header">'
			content += '			<strong class="pull-right primary-font">나</strong>'
			content += '		</div>'
			content += '		<p>' + $('#chatMsg').val();
			+'</p>'
			content += '	</div>'
			content += '</li>'
			$('.chat').append(content);
			$('.chat-body').scrollTop($('.chat-body')[0].scrollHeight);
			sendMsg();
			$('#chatMsg').val("");
		});
		$('#toExcelBtn').on("click", function(){
			post_to_url('${pageContext.request.contextPath}/chat/downloadExcel',{'arr': summaryJson, 'kaptCode': '${ kaptCode }'})
		})
	})

	function closeSocket() {
		ws.close();
	}
	function fn_fileDown(fileNo){
		post_to_url('${pageContext.request.contextPath}/admin/fileDown',{'fileNo': fileNo})
	}
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
</script>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-6">
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
		<div class="col-md-6">
			<div class="card-body">
				<h3 class="card-title">고객 지원</h3>
				<div class="row auto-word-row">
					<div class="card col-12">
						<div class="card-title">
							<h4 class="auto-title">온라인 상담 요약</h4>
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
					<button class="btn btn-outline-info btn-block" id="toExcelBtn">Excel다운로드 받기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="https://unpkg.com/@popperjs/core@2"></script>