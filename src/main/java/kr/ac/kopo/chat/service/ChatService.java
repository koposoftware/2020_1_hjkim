package kr.ac.kopo.chat.service;

import java.util.List;

import kr.ac.kopo.chat.vo.ChatHistoryVO;
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
	/**
	 * userNo를 가지고 채팅방번호를 알아낸다.
	 * @return 채팅방번호
	 */
	int selectChatNo(int userNo);
	/**
	 * 채팅 내용을 넣는다.
	 * @param historyVO
	 */
	void insertHistory(ChatHistoryVO historyVO);
	/**
	 * 상담사를 배치받는다.
	 * @param userNo
	 */
	void updateChatListUser(int userNo);
	/**
	 * 상담사가 다른페이지에서 돌아와도 채팅이 보일 수 있도록 load한다.
	 * @param userNo 상담사 번호
	 * @return
	 */
	List<ChatHistoryVO> selectHistoryList(int userNo);
	/**
	 * user의 웹소켓이 종료되면 end_date를 찍음
	 * @param userNo
	 */
	void updateEndDate(int userNo);
}
