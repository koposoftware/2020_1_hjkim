package kr.ac.kopo.chat.vo;

public class ChatListVO {
	private int chatNo;
	private int counselor;
	private int userNo;
	private String startDate;
	private String endDate;
	private int rn;		//rownum
	
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public int getCounselor() {
		return counselor;
	}
	public void setCounselor(int counselor) {
		this.counselor = counselor;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	@Override
	public String toString() {
		return "ChatListVO [chatNo=" + chatNo + ", counselor=" + counselor + ", userNo=" + userNo + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
}
