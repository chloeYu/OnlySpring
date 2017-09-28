package only.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import only.dao.ChatDAO;

@WebServlet(name = "ChatSubmitServlet", urlPatterns = {"/chatSubmitServlet"})
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ChatSubmitServlet 작동중...");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html);charset=utf-8");
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");
		System.out.println("Chat 파라미터 값 불러옴...");
		if (fromID == null || fromID.equals("") || toID == null || toID.equals("") || chatContent == null
				|| chatContent.equals("")) {
			response.getWriter().write("0");
		} else {
			fromID = URLDecoder.decode(fromID, "utf-8");
			toID = URLDecoder.decode(toID, "utf-8");
			chatContent = URLDecoder.decode(chatContent, "utf-8");
			response.getWriter().write(new ChatDAO().submit(fromID, toID, chatContent) + "");
		}
	}

}
