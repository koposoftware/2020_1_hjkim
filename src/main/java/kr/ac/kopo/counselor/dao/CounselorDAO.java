package kr.ac.kopo.counselor.dao;

import java.util.List;

import kr.ac.kopo.counselor.vo.ChatAutoVO;
import kr.ac.kopo.counselor.vo.LoanProductVO;

public interface CounselorDAO {

	List<ChatAutoVO> selectAutoWord(int userNo);

	List<LoanProductVO> selectLoanProduct();

	LoanProductVO selectLoanProductOne(String productCode);
}
