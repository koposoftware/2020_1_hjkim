<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	var ws;
	var userNo = ${ loginVO.userNo };
	var targetNo;
	
	function connect(){
		//웹소켓 객체 생성하는 부분
		//핸들러 등록 (연결 생성, 메시지 수신, 연결종료)
		
		//url 연결할 서버의 경로
		ws = new WebSocket('ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/chatServer');
		ws.onopen = function(){
			console.log('연결생성');
			register();
			targetCheck();
		};
		
		ws.onmessage = function(e){
			console.log('메시지 받음');
			var data = e.data;
			addMsg(data);
		};
		ws.onclose = function() {
			console.log('연결 끊김');
		}
	}
	
	function addMsg(msg){
		var content = '';
		content += '<li class="left clearfix">'
		content += '	<span class="chat-img pull-left">' 
		content += '		<img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />'
		content += '	</span>'
		content += '	<div class="chat-body clearfix">'
		content += '		<div class="header">'
		content += '			<strong class="primary-font">상담사</strong>'
		content += '		</div>'
		content += '		<p>'+ msg + '</p>'
		content += '	</div>'
		content += '</li>'
	
		$('.chat').append(content);
	}
	
	//메시지 수신을 위한 서버에 id 등록
	function register(){
		console.log(userNo)
		var msg = {
			type : "user",
			userid : userNo
		};
		ws.send(JSON.stringify(msg))
	}
	
	function sendMsg(){
		var msg = {
			type : 'chat', //메시지를 구분하는 구분자 - 상대방 아이디와 메시지 포함해서 보냄
			target : targetNo,
			message : $('#chatMsg').val()
		};
		ws.send(JSON.stringify(msg));
	};
	
	$(function(){
		connect();
		$('#btnSend').on("click", function(){
			var content = '';
			content += '<li class="right clearfix">'
			content += '	<span class="chat-img pull-right">' 
			content += '		<img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />'
			content += '	</span>'
			content += '	<div class="chat-body clearfix">'
			content += '		<div class="header">'
			content += '			<strong class="pull-right primary-font">나</strong>'
			content += '		</div>'
			content += '		<p>'+ $('#chatMsg').val(); + '</p>'
			content += '	</div>'
			content += '</li>'
			$('.chat').append(content);
			sendMsg();
			$('#chatMsg').val("");
		})
	})
	function targetCheck(){
		var target;
		console.log('target')
		$.ajax({
			url: '${ pageContext.request.contextPath }/chat/targetCheck',
			type: 'post',
			data: {
				checkNo: userNo
			},
			success : function(check){
				targetNo = check.counselorNo
				console.log(targetNo)
			}
		})
	}
	function closeSocket(){
		ws.close();
	}
</script>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-8">
			<div class="card card-info card-all">
				<div class="panel-heading chat-heading">
					<span class="glyphicon glyphicon-comment"></span> 온라인 상담
					<div class="btn-group pull-right">
						<div class="btn-group">
							<button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Action</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a> <a class="dropdown-item" href="#">Something else here</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Separated link</a>
							</div>
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
	</div>
</div>
<script src="https://unpkg.com/@popperjs/core@2"></script>