package kr.ac.kopo.admin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.kopo.admin.dao.AdminDAO;
import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;
import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.common.FileUtils;
import kr.ac.kopo.counselor.vo.LoanProductVO;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDAO;
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
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
	@Override
	public ProductFileVO selectFile(String productNo) {
		return adminDAO.selectFile(productNo);
	}
	@Override
	public void insertProduct(LoanProductVO productVO, MultipartHttpServletRequest mpRequest) throws Exception {
		adminDAO.insertProduct(productVO);
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(productVO, mpRequest);
		int size = list.size();
		for(int i = 0; i < size; i++) {
			adminDAO.insertProductFile(list.get(i));
		}
		
	}
	@Override
	public ProductFileVO selectFileInfo(int fileNo) {
		return adminDAO.selectFileInfo(fileNo);
	}
	
}
