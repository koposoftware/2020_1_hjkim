package kr.ac.kopo.chat.service;

import kr.ac.kopo.chat.vo.ChatVO;

public interface ChatService {
	/**
	 * 유저가 채팅시작했을 때, 매칭할 상담사를 고른다.
	 * @return 매칭된 상담사의 user_no
	 */
	int selectCounselor();
	/**
	 * 유저가 채팅을 시작했을 때, 매칭된 상담사가 있는 행에 user_no를 update한다.
	 * @param counselor 매칭된 상담사와 userNo
	 */
	void updateUser(ChatVO chatVO);
	/**
	 * f_consulting을 보고 매칭된 사용자의 userNo를 리턴한다.
	 * @param userNo
	 */
	ChatVO selectTarget(int userNo);
}
