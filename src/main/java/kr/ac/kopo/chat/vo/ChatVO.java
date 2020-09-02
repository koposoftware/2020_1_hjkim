package kr.ac.kopo.chat.vo;

public class ChatVO {
	private int counselorNo;
	private int userNo;
	public int getCounselorNo() {
		return counselorNo;
	}
	public void setCounselorNo(int counselorNo) {
		this.counselorNo = counselorNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "ChatVO [counselorNo=" + counselorNo + ", userNo=" + userNo + "]";
	}
	
}
