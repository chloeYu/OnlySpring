package only.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {
	
	Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("유저아이디 입력성공");
		users.put(session.getId(), session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("유저아이디 지우기성공");
		users.remove(session.getId());
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("메세지 전송중...");
		// 전송되어 온 메시지
		String msg = message.getPayload();
		// 앞의 4글자를 제외한 부분을 가지고 메시지 만들기
		TextMessage mes = new TextMessage(msg.substring(4));
		// Map의 모든 Value를 가져오기
		Collection<WebSocketSession> set = users.values();
		// set의 모든 구성 요소에 mes를 전송
		for (WebSocketSession s : set) {
			s.sendMessage(mes);
		}
	}
}
