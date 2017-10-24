package only.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	@Override
	public void onApplicationEvent(SessionDestroyedEvent  event) {
		 List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
	        for (SecurityContext securityContext : lstSecurityContext)
	        {
	        	Authentication authentication = securityContext.getAuthentication();
	            String user = (String) authentication.getPrincipal();
	            Collection<WebSocketSession> set = ChatWebSocketHandler.users.values();
	            TextMessage mes = new TextMessage("{\"type\":\"contacts\"}");
	            System.out.println("logout send msg from " + user+ ":" + mes);
	            for (WebSocketSession s : set) {
	    			try {
						s.sendMessage(mes);
					} catch (IOException e) {
						e.printStackTrace();
					}	    		
	    		}
	        }
		
	}

}
