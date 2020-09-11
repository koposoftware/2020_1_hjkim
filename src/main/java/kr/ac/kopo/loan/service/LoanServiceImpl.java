package kr.ac.kopo.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.admin.dao.AdminDAO;
import kr.ac.kopo.admin.vo.BjdCodeVO;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List<BjdCodeVO> selectSido() {
		List<BjdCodeVO> bjdCodeVO = adminDAO.selectSido();
		return bjdCodeVO;
	}
	
}
