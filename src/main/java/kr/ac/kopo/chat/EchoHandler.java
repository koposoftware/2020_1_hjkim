package kr.ac.kopo.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.ac.kopo.chat.service.ChatService;
import kr.ac.kopo.chat.vo.ChatVO;

@Component
public class EchoHandler extends TextWebSocketHandler {
	@Autowired
	private ChatService service;
	private List<WebSocketSession> users;
	private Map<Integer, Object> userMap;
	private ChatVO chatVO;

	public EchoHandler() {
		users = new ArrayList<>();
		userMap = new HashMap<>();
		chatVO = new ChatVO();
	}

	// 로그남기는것
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	// 클라이언트가 연결되었을 때 실행

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("TextWebSocketHandler : 연결 생성 !");
		users.add(session);
		System.out.println("session.getId()" + session);
	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message.getPayload());

		JSONObject object = new JSONObject(message.getPayload());

		String type = (String) object.get("type");
		/*---------------------------------------------- 
		 만약 type 이 user일 경우 
		 1. 상담사를 매칭시킨다. 
		-------------------------------------------------*/
		if (type != null && type.equals("user")) {
			System.out.println("user입니다.");
			
			int counselor = service.selectCounselor();
			System.out.println("배치된 상담사 : " + counselor);
			int user = object.getInt("userid");
			userMap.put(user, session);
			chatVO.setCounselorNo(counselor);
			chatVO.setUserNo(user);
			service.updateUser(chatVO);
		} else if (type != null && type.equals("con")) {
			int user = object.getInt("userid");
			userMap.put(user, session);
		} else {
			System.out.println("sendmsg");
			int target = (int) object.get("target");
			WebSocketSession ws = (WebSocketSession) userMap.get(target);
			String msg = object.getString("message");
			if (ws != null) {
				ws.sendMessage(new TextMessage(msg));
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session);
		logger.info("{} 연결 끊김.", session.getId());
	}

}
