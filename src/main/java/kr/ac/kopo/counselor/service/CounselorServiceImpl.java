package kr.ac.kopo.counselor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.counselor.dao.CounselorDAO;
import kr.ac.kopo.counselor.vo.ChatAutoVO;

@Service
public class CounselorServiceImpl implements CounselorService {
	@Autowired
	private CounselorDAO counselorDAO;

	@Override
	public List<ChatAutoVO> selectAutoWord(int userNo) {
		return counselorDAO.selectAutoWord(userNo);
	} 
	
}
