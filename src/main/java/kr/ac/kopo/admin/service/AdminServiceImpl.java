package kr.ac.kopo.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.admin.dao.AdminDAO;
import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public List<BjdCodeVO> selectSido() {
		List<BjdCodeVO> bjdCodeVO = adminDAO.selectSido();
		return bjdCodeVO;
	}
	@Override
	public List<BjdCodeVO> selectSigungu(String code) {
		List<BjdCodeVO> bjdCodeVO = adminDAO.selectSigungu(code);
		return bjdCodeVO;
	}
	@Override
	public List<BjdCodeVO> selectEupmyeondong(String code) {
		List<BjdCodeVO> bjdCodeVO = adminDAO.selectEupmyeondong(code);
		return bjdCodeVO;
	}
	@Override
	public String selectCodeInfo(String code) {
		String check = adminDAO.selectCodeInfo(code);
		return check;
	}
	@Override
	public void insertLawArea(LawAreaVO areaVO) {
		adminDAO.insertLawArea(areaVO);
	}
	@Override
	public void updateLawArea(LawAreaVO areaVO) {
		adminDAO.updateLawArea(areaVO);
	}
}
