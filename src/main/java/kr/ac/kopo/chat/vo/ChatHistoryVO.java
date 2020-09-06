package kr.ac.kopo.chat.vo;

public class ChatHistoryVO {
	private int chatNo;
	private int sender;
	private int receiver;
	private String content;
	private String sendDate;
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	@Override
	public String toString() {
		return "ChatHistoryVO [chatNo=" + chatNo + ", sender=" + sender + ", receiver=" + receiver + ", content="
				+ content + ", sendDate=" + sendDate + "]";
	}
	
}
