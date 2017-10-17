package only.dao;

import java.util.List;

import only.model.Chat;

public interface TextMessageListDao {
	
	public List<Chat> getChatMessageList(String userid);
}
