package only.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import only.model.Chat;

public class ChatDAO {

	DataSource dataSource;

	public ChatDAO() {

		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/projectonly");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
			System.out.println("DB연결에러");
		}

	}

	public ArrayList<Chat> getChatListByID(String fromID, String toID, String chatID) {
		ArrayList<Chat> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID > ? ORDER BY chatTime";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			rs = pstmt.executeQuery();
			chatList = new ArrayList<Chat>();
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("formID"));
				chat.setToID(rs.getString("toID"));
				chat.setChatContent(rs.getString("chatContent"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if (chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("SQL 쿼리문 에러");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("DB 닫기 에러");
			}
		}
		return chatList;
	}

	public ArrayList<Chat> getChatListByRecent(String fromID, String toID, int number) {
		ArrayList<Chat> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT) ORDER BY chatTime";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<Chat>();
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("formID"));
				chat.setToID(rs.getString("toID"));
				chat.setChatContent(rs.getString("chatContent"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if (chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("SQL 쿼리문 에러");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("DB 닫기 에러");
			}
		}
		return chatList;
	}

	public int submit(String fromID, String toID, String chatContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "INSERT INTO CHAT VALUES (NULL, ?, ?, ?, NOW())";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("SQL 쿼리문 에러");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("DB 닫기 에러");
			}
		}
		return -1;
	}

}
