package kr.ac.kopo.counselor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.counselor.dao.CounselorDAO;
import kr.ac.kopo.counselor.vo.ChatAutoVO;
import kr.ac.kopo.counselor.vo.LoanProductVO;

@Service
public class CounselorServiceImpl implements CounselorService {
	@Autowired
	private CounselorDAO counselorDAO;

	@Override
	public List<ChatAutoVO> selectAutoWord(int userNo) {
		return counselorDAO.selectAutoWord(userNo);
	}

	@Override
	public List<LoanProductVO> selectLoanProduct() {
		return counselorDAO.selectLoanProduct();
	}

	@Override
	public LoanProductVO selectLoanProductOne(String productCode) {
		return counselorDAO.selectLoanProductOne(productCode);
	}

	@Override
	public List<ProductFileVO> selectFileList() {
		return counselorDAO.selectFileList();
	}

	@Override
	public void insertAutoWordCounselor(ChatAutoVO autoWord) {
		counselorDAO.insertAutoWordCounselor(autoWord);
	} 
	
	
}
