package kr.ac.kopo.counselor.service;

import java.util.List;

import kr.ac.kopo.counselor.vo.ChatAutoVO;

public interface CounselorService {
	/**
	 * userNo가 0 이면 admin이 등록한 것
	 * @param userNo
	 * @return
	 */
	List<ChatAutoVO> selectAutoWord(int userNo);
	

}
