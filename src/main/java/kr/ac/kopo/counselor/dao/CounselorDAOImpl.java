package kr.ac.kopo.counselor.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.counselor.vo.ChatAutoVO;
import kr.ac.kopo.counselor.vo.LoanProductVO;

@Repository
public class CounselorDAOImpl implements CounselorDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ChatAutoVO> selectAutoWord(int userNo) {
		return sqlSession.selectList("counselor.dao.counselorDAO.selectAutoWord",userNo);
	}

	@Override
	public List<LoanProductVO> selectLoanProduct() {
		return sqlSession.selectList("counselor.dao.counselorDAO.selectLoanProduct");
	}

	@Override
	public LoanProductVO selectLoanProductOne(String productCode) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("counselor.dao.counselorDAO.selectLoanProductOne", productCode);
	}

	@Override
	public List<ProductFileVO> selectFileList() {
		return sqlSession.selectList("counselor.dao.counselorDAO.selectFileList");
	}

	@Override
	public void insertAutoWordCounselor(ChatAutoVO autoWord) {
		sqlSession.insert("counselor.dao.counselorDAO.insertAutoWordCounselor", autoWord);
	}
	
}
