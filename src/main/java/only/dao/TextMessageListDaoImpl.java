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
	public List<Chat> getChatMessageList() {
		return ss.selectList("messages.messageList");
	}

}
