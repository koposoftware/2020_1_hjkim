package kr.ac.kopo.counselor.vo;

public class ChatAutoVO {
	private int autoNo;
	private int userNo;
	private String content;
	
	
	public int getAutoNo() {
		return autoNo;
	}

	public void setAutoNo(int autoNo) {
		this.autoNo = autoNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ChatAutoVO [autoNo=" + autoNo + ", userNo=" + userNo + ", content=" + content + "]";
	}
	
}
