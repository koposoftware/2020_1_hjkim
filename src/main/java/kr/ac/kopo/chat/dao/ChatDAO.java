package kr.ac.kopo.chat.dao;

import java.util.List;

import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.chat.vo.ChatVO;

public interface ChatDAO {
	/**
	 * 유저가 매칭되지 않은 상담사의 userNo를 가져온다
	 * @return 매칭되지 않은 상담사의 userNo 리스트
	 */
	List<Integer> selectCounselor();
	/**
	 * 유저가 채팅을 시도하면, 상담사가 있는 행에 userNo를 삽입한다.
	 * @param counselor 매칭된 상담사와 userNo
	 */
	void updateUser(ChatVO chatVO);
	/**
	 * f_consulting을 보고 매칭된 사용자의 userNo를 리턴한다.
	 * @param userNo
	 * @return
	 */
	ChatVO selectTarget(int userNo);
	/**
	 * userNo을 가지고 채팅방번호를 알아낸다.
	 * @param userNo
	 * @return
	 */
	int selectChatNo(int userNo);
	/**
	 * 채팅내용 저장
	 * @param historyVO
	 */
	void insertHistory(ChatHistoryVO historyVO);
	/**
	 * 상담사 배치 update
	 * @param userNo
	 */
	void updateChatListUser(int userNo);
	/**
	 * 채팅을 하기 전에 이전채팅을 먼저 load한다
	 * @param userNo
	 * @return
	 */
	List<ChatHistoryVO> selectHistoryList(int userNo);
	/**
	 * 채팅이 종료되면 end_date를 찍음
	 * @param userNo
	 */
	void updateEndDate(int userNo);

}
