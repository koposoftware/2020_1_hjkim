package kr.ac.kopo.counselor.dao;

import java.util.List;

import kr.ac.kopo.counselor.vo.ChatAutoVO;

public interface CounselorDAO {

	List<ChatAutoVO> selectAutoWord(int userNo);
}
