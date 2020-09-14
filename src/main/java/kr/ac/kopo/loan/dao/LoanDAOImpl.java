package kr.ac.kopo.loan.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.loan.vo.LoanCalcVO;
import kr.ac.kopo.loan.vo.LoanVO;

@Repository
public class LoanDAOImpl implements LoanDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public String selectAptName(String aptCode) {
		String aptName = sqlSession.selectOne("loan.dao.LoanDAO.selectAptName",aptCode);
		return aptName;
	}

	@Override
	public List<Double> selectAptArea(String aptCode) {
		List<Double> aptArea = sqlSession.selectList("loan.dao.LoanDAO.selectAptArea",aptCode);
		return aptArea;
	}

	@Override
	public int selectAptPrice(LoanVO loanVO) {
		int aptPrice = 0;
		try {
			aptPrice = sqlSession.selectOne("loan.dao.LoanDAO.selectAptPrice", loanVO);
			System.out.println(aptPrice);
		}catch (Exception e) {
			aptPrice = 0;
		}
		return aptPrice;
	}

	@Override
	public String selectLawDivision(String aptCode) {
		String division = sqlSession.selectOne("loan.dao.LoanDAO.selectLawDivision", aptCode);
		return division;
	}

	@Override
	public List<LoanCalcVO> selectLtvPercent(LoanCalcVO calcVO) {
		List<LoanCalcVO> percentList = sqlSession.selectList("loan.dao.LoanDAO.selectLtvPercent",calcVO);
		return percentList;
	}

}
