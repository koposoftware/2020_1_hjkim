package kr.ac.kopo.chat.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.chat.vo.ChatListVO;
import kr.ac.kopo.member.vo.MemberVO;

public interface ChatService {
	/**
	 * 유저가 채팅시작했을 때, 매칭할 상담사를 고른다.
	 * @return 매칭된 상담사의 user_no
	 */
	int selectCounselor();
	
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
	/**
	 * userNo로 상담했던 상담 리스트을 불러온다.
	 * @param userNo
	 * @return 채팅 리스트 [채팅번호, 상담사번호, 유저번호, 시작시간, 종료시간]
	 */
	List<ChatListVO> selectChatList(int userNo);
	/**
	 * 상담관련 테이블에 있는 userNo와 이름을 매핑시키기 위함
	 * @param userNo
	 * @return userNo name type
	 */
	MemberVO selectMemberName(int userNo);
	
	/**
	 * 페이징 처리
	 */
	int selectChatListCnt(int userNo);
	/**
	 * 페이징 처리된 상담 리스트
	 * @param pagingMap paging 정보와 userNo
	 * @return
	 */
	List<ChatListUserNameVO> selectChatListPaging(Map<String, Object> pagingMap);
}
