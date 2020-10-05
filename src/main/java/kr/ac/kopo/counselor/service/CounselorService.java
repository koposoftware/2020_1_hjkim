package kr.ac.kopo.counselor.service;

import java.util.List;

import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.counselor.vo.ChatAutoVO;
import kr.ac.kopo.counselor.vo.LoanProductVO;

public interface CounselorService {
	/**
	 * userNo가 0 이면 admin이 등록한 것
	 * @param userNo
	 * @return
	 */
	List<ChatAutoVO> selectAutoWord(int userNo);
	/**
	 * 주택 담보 대출 상품 정보를 가져옴
	 * @return
	 */
	List<LoanProductVO> selectLoanProduct();
	/**
	 * 상품 코드에 맞는 주택 담보 대출 상품 정보를 가져옴
	 * @return
	 */
	LoanProductVO selectLoanProductOne(String productCode);
	/**
	 * pdf 목록 가져옴
	 * @return
	 */
	List<ProductFileVO> selectFileList();
	/**
	 * 상담사 자동문구 등록
	 * @param autoWord
	 */
	void insertAutoWordCounselor(ChatAutoVO autoWord);
	

}
