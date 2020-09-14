package kr.ac.kopo.loan.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.loan.dao.LoanDAO;
import kr.ac.kopo.loan.vo.LoanCalcVO;
import kr.ac.kopo.loan.vo.LoanVO;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanDAO loanDAO;

	@Override
	public String selectAptName(String aptCode) {
		String aptName = loanDAO.selectAptName(aptCode);
		return aptName;
	}

	@Override
	public List<Double> selectAptArea(String aptCode) {
		List<Double> aptArea = loanDAO.selectAptArea(aptCode);
		return aptArea;
	}

	@Override
	public int selectAptPrice(LoanVO loanVO) {
		int aptPrice = loanDAO.selectAptPrice(loanVO);
		return aptPrice;
	}

	@Override
	public String selectLawDivision(String aptCode) {
		String division = loanDAO.selectLawDivision(aptCode);
		return division;
	}

	@Override
	public List<LoanCalcVO> selectLtvPercent(LoanCalcVO calcVO) {
		List<LoanCalcVO> percentList = loanDAO.selectLtvPercent(calcVO);
		return percentList;
	}
}
