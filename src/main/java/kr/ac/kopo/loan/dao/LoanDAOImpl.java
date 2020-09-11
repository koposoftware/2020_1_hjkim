package kr.ac.kopo.loan.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.vo.BjdCodeVO;

@Repository
public class LoanDAOImpl implements LoanDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BjdCodeVO> selectSigungu() {
		List<BjdCodeVO> bjdCodeList = sqlSession.selectList("admin.dao.AdminDAO.sido");
		return bjdCodeList;
	}

}
