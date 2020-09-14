package kr.ac.kopo.loan.dao;

import java.util.List;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.loan.vo.LoanCalcVO;
import kr.ac.kopo.loan.vo.LoanVO;

public interface LoanDAO {

	String selectAptName(String aptCode);

	List<Double> selectAptArea(String aptCode);

	int selectAptPrice(LoanVO loanVO);

	String selectLawDivision(String aptCode);

	List<LoanCalcVO> selectLtvPercent(LoanCalcVO calcVO);

}
