package kr.ac.kopo.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.ac.kopo.chat.service.ChatService;
import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.member.service.MemberService;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
	@Autowired
	private ChatService service;

	@Autowired
	private MemberService memberService;

	private List<WebSocketSession> users; // 세션리스트
	private Map<Integer, Object> userMap; // userNo와 세션 연결
	private Map<Integer, Integer> chatMap; // <userNo, 채팅방번호>
	private Map<Object, Integer> userFindMap; // type이 user인 것만
	private Map<Integer, String> chatAptMappingMap; // <채팅방번호, kaptCode>

	public WebSocketHandler() {
		users = new ArrayList<>();
		userMap = new HashMap<>();
		chatMap = new HashMap<>();
		userFindMap = new HashMap<>();
		chatAptMappingMap = new HashMap<>();
	}

	// 클라이언트 연결
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session); // 세션을 등록한다.
	}

	// 클라이언트 메시지 처리
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		JSONObject object = new JSONObject(message.getPayload());
		System.out.println("handleTextMessage : " + object);

		String type = (String) object.get("type");
		/*-------------------------------------------------
		 *  상담사 1. userMap에 userNo와 세션등록
		 *  	 2. userNo, 채팅방번호 연결
		 * 
		 *  사용자 
		 *  1. userMap에 userNo와 세션 등록 
		 *  2. userNo, 채팅방번호 연결 
		 *  3. user의 session이 종료되면 end_date를 update하기 위해 userFindMap에 user넣음
		 * 
		 *  타켓 구하기 ( 방번호로 ) 
		 *  1. userNo의 방번호를 찾는다. 
		 *  2. 같은 방번호를 가지고 있는 다른 userNo를 찾는다. 
		 *  3.userNo로 세션을 구한다. 
		 *  ----------------------------------------------------*/
		if (type != null && type.equalsIgnoreCase("counselor")) {
			int userNo = object.getInt("userid");
			userMap.put(userNo, session);

			int chatNo = service.selectChatNo(userNo);
			chatMap.put(userNo, chatNo);
			if (chatAptMappingMap.containsKey(chatNo)) {
				String kaptCode = chatAptMappingMap.get(chatNo);
				if (kaptCode != null) {
					System.out.println(kaptCode);
					session.sendMessage(new TextMessage("kaptCode:" + kaptCode));
				}
			}
		} else if (type != null && type.equalsIgnoreCase("user")) {
			int userNo = object.getInt("userid");
			service.updateChatListUser(userNo);
			userMap.put(userNo, session);

			int chatNo = service.selectChatNo(userNo);
			chatMap.put(userNo, chatNo);

			userFindMap.put(session, userNo);
			//상담사 연결이 됐을 경우 상담사와 연결이 됐다는 것을 알림
			session.sendMessage(new TextMessage("@connect : 상담사와 연결되었습니다."));
			int findChatNo = chatMap.get(userNo);
			WebSocketSession ws = null;
			for (Map.Entry<Integer, Integer> element : chatMap.entrySet()) {
				int key = element.getKey();
				int value = element.getValue();
				if (findChatNo == value && key != userNo) {
					ws = (WebSocketSession) userMap.get(key);
					ws.sendMessage(new TextMessage("@connect : 사용자와 연결이 되었습니다."));
				}
			}
			// 아파트 번호를 방번호와 매칭시킨다.
			String aptCode = object.getString("kaptCode");
			if(aptCode != null) {
				chatAptMappingMap.put(chatNo, aptCode);
			}
		} else if(type != null && type.equalsIgnoreCase("table")){
			int userNo = object.getInt("userid");
			int findChatNo = chatMap.get(userNo);
			String msg = object.getString("msg");
			WebSocketSession ws = null;
			for (Map.Entry<Integer, Integer> element : chatMap.entrySet()) {
				int key = element.getKey();
				int value = element.getValue();
				if (findChatNo == value && key != userNo) {
					ws = (WebSocketSession) userMap.get(key);
				}
			}
			if (ws != null) {
				ws.sendMessage(new TextMessage("@summary : " + msg));
			}
		} else if(type != null && type.equalsIgnoreCase("pdf")){
			int userNo = object.getInt("userid");
			int findChatNo = chatMap.get(userNo);
			String msg = object.getString("message");
			WebSocketSession ws = null;
			for (Map.Entry<Integer, Integer> element : chatMap.entrySet()) {
				int key = element.getKey();
				int value = element.getValue();
				if (findChatNo == value && key != userNo) {
					ws = (WebSocketSession) userMap.get(key);
				}
			}
			if (ws != null) {
				ws.sendMessage(new TextMessage("@pdfSend : " + msg));
			}
		} else {
			ChatHistoryVO historyVO = new ChatHistoryVO();

			int userNo = object.getInt("userid");
			int findChatNo = chatMap.get(userNo);
			String msg = object.getString("message");
			WebSocketSession ws = null;
			for (Map.Entry<Integer, Integer> element : chatMap.entrySet()) {
				int key = element.getKey();
				int value = element.getValue();
				if (findChatNo == value && key != userNo) {
					ws = (WebSocketSession) userMap.get(key);

					historyVO.setChatNo(findChatNo);
					historyVO.setReceiver(key);
					historyVO.setSender(userNo);
					historyVO.setContent(msg);
					break;
				}
			}
			if (ws != null) {
				service.insertHistory(historyVO);
				ws.sendMessage(new TextMessage(msg));
			}
		}
	}

	// 클라이언트 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		if (userFindMap.containsKey(session)) {
			int userNo = userFindMap.get(session);
			service.updateEndDate(userNo);
			// 같은 채팅방을 쓰고 있는 상담사는 다시 방을 insert한다.
			int chatNo = chatMap.get(userNo);
			for (Map.Entry<Integer, Integer> element : chatMap.entrySet()) {
				int key = element.getKey();
				int value = element.getValue();
				if (chatNo == value && key != userNo) {
					memberService.insertCounselor(key);
					System.out.println("memberService insertCounselor");
					break;
				}
			}
			userFindMap.remove(session);
		}
		users.remove(session);
		System.out.println("연결끊김");
	}

}
