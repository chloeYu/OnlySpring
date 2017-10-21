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
		System.out.println("�쑀���븘�씠�뵒 �엯�젰�꽦怨�");
		users.put(session.getId(), session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("�쑀���븘�씠�뵒 吏��슦湲곗꽦怨�");
		users.remove(session.getId());
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage");
		// �쟾�넚�릺�뼱 �삩 硫붿떆吏�
		String msg = message.getPayload();
		// �븵�쓽 4湲��옄瑜� �젣�쇅�븳 遺�遺꾩쓣 媛�吏�怨� 硫붿떆吏� 留뚮뱾湲�
		TextMessage mes = new TextMessage(msg);
		System.out.println(mes);
		// Map�쓽 紐⑤뱺 Value瑜� 媛��졇�삤湲�
		Collection<WebSocketSession> set = users.values();
		// set�쓽 紐⑤뱺 援ъ꽦 �슂�냼�뿉 mes瑜� �쟾�넚
		for (WebSocketSession s : set) {
			s.sendMessage(mes);
		}
	}
}
