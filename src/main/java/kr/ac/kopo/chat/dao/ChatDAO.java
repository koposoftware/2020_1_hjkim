package kr.ac.kopo.chat.dao;

import java.util.List;

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

}
