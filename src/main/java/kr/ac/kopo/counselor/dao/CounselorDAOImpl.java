package kr.ac.kopo.counselor.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.counselor.vo.ChatAutoVO;

@Repository
public class CounselorDAOImpl implements CounselorDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ChatAutoVO> selectAutoWord(int userNo) {
		return sqlSession.selectList("counselor.dao.counselorDAO.selectAutoWord",userNo);
	}
	
}
