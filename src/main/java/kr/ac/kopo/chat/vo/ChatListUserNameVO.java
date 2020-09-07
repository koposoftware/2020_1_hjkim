package kr.ac.kopo.chat.vo;

import kr.ac.kopo.member.vo.MemberVO;

public class ChatListUserNameVO {
	private ChatListVO chatListVO;
	private MemberVO userVO;
	private MemberVO counselorVO;
	public ChatListVO getChatListVO() {
		return chatListVO;
	}
	public void setChatListVO(ChatListVO chatListVO) {
		this.chatListVO = chatListVO;
	}
	
	public MemberVO getUserVO() {
		return userVO;
	}
	public void setUserVO(MemberVO userVO) {
		this.userVO = userVO;
	}
	public MemberVO getCounselorVO() {
		return counselorVO;
	}
	public void setCounselorVO(MemberVO counselorVO) {
		this.counselorVO = counselorVO;
	}
	@Override
	public String toString() {
		return "ChatListUserNameVO [chatListVO=" + chatListVO + ", userVO=" + userVO + ", counselorVO=" + counselorVO
				+ "]";
	}
	
}
