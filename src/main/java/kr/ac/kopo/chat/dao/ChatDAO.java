package kr.ac.kopo.chat.dao;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.chat.vo.ChatListVO;
import kr.ac.kopo.member.vo.MemberVO;

public interface ChatDAO {
	/**
	 * 유저가 매칭되지 않은 상담사의 userNo를 가져온다
	 * @return 매칭되지 않은 상담사의 userNo 리스트
	 */
	List<Integer> selectCounselor();
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
	/**
	 * 유저 번호로 상담리스트를 불러온다
	 * @param userNo
	 * @return 상담 내역 [상담번호, 상담사번호, 유저번호, 시작일, 종료일]
	 */
	List<ChatListVO> selectChatList(int userNo);
	/**
	 * 상담 테이블에 있는 userNo와 이름을 매핑시키기 위함
	 * @param userNo
	 * @return userNo, name, type
	 */
	MemberVO selectMemberName(int userNo);
	/**
	 * 페이징 처리하기 위해 전체 상담 개수
	 * @param userNo 
	 * @return 전체 개수
	 */
	int selectChatListCnt(int userNo);
	/**
	 * 페이징 처리된 상담 내역 list
	 */
	List<ChatListUserNameVO> selectChatListPaging(Map<String, Object> paging);
	/**
	 * 상품 pdf 가져옴
	 * @param productNo
	 * @return
	 */
	ProductFileVO selectFile(int fileNo);
}
