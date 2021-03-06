package only.model;

public class Chat {
	int chatID;
	String fromID;
	String toID;
	String chatContent;
	String chatTime;
	String chatRoom;
	int chatRead;
	int chatCount;
	
	public int getChatCount() {
		return chatCount;
	}

	public void setChatCount(int chatCount) {
		this.chatCount = chatCount;
	}

	public int getChatRead() {
		return chatRead;
	}

	public void setChatRead(int chatRead) {
		this.chatRead = chatRead;
	}

	public String getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

	@Override
	public String toString() {
		return "Chat [chatID=" + chatID + ", fromID=" + fromID + ", toID=" + toID + ", chatContent=" + chatContent
				+ ", chatTime=" + chatTime + ", chatRoom=" + chatRoom + ", chatRead=" + chatRead +", chatCount=" + chatCount + "]";
	}
}
