package kr.ac.kopo.loan.service;

import java.util.List;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.loan.vo.LoanCalcVO;
import kr.ac.kopo.loan.vo.LoanVO;

public interface LoanService {

	/**
	 * 아파트 이름을 가져오는 메소드
	 * @param string aptCode
	 * @return
	 */
	String selectAptName(String aptCode);

	/**
	 * 아파트 면적을 가져오는 메소드
	 * @param string
	 * @return
	 */
	List<Double> selectAptArea(String aptCode);
	/**
	 * 아파트 면적, 아파트 층수에 근거하여 실거래가를 가져온다.
	 * @param loanVO
	 * @return
	 */
	int selectAptPrice(LoanVO loanVO);
	/**
	 * 투기지역, 조정대상지역, 그외 수도권, 비규제 지역 정보를 가져온다.
	 * @param aptCode
	 * @return
	 */
	String selectLawDivision(String aptCode);
	/**
	 * 9억원 이하 [userType에 따른 LTV한개를 가져온다] 9억원 초과 [9억원 이하LTV, 9억원 초과 LTV를 가져온다.]
	 * @param calcVO
	 * @return
	 */
	List<LoanCalcVO> selectLtvPercent(LoanCalcVO calcVO);

}
