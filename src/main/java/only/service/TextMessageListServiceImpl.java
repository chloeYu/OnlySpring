package only.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import only.dao.TextMessageListDao;
import only.model.Chat;

@Service
public class TextMessageListServiceImpl implements TextMessageListService {

	@Autowired
	TextMessageListDao tml;
	
	@Override
	public List<Chat> getChatMessageList() {
		return tml.getChatMessageList();
	}

}
