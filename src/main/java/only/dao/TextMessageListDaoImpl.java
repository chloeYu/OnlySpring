package only.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import only.model.Chat;

@Repository
public class TextMessageListDaoImpl implements TextMessageListDao {

	@Autowired
	SqlSession ss;

	@Override
	public List<Chat> getChatMessageList(String userid) {
		return ss.selectList("messages.messageList", userid);
	}

	@Override
	public int getUnreadMessageCount(String userid) {
		return ss.selectOne("messages.messageCountAll", userid);
	}

	@Override
	public List<Chat> UnreadRoomMessageCount(String userid) {
		return ss.selectList("messages.messageCount", userid);
	}

}
