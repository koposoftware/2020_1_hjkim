package kr.ac.kopo.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;
import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.counselor.vo.LoanProductVO;

public interface AdminService {

	List<BjdCodeVO> selectSido();

	List<BjdCodeVO> selectSigungu(String code);

	List<BjdCodeVO> selectEupmyeondong(String code);
	/**
	 * 코드를 select하여 , f_law_area테이블에 데이터가 있는지 없는 지 확인
	 * @param code
	 * @return
	 */
	String selectCodeInfo(String code);
	/**
	 * ltv 영향 지역 삽입
	 * @param areaVO
	 */
	void insertLawArea(LawAreaVO areaVO);
	/**
	 * ltv테이블에 내가 입력한 법정동 코드 이미 있다면 update실행
	 * @param areaVO
	 */
	void updateLawArea(LawAreaVO areaVO);
	/**
	 * 대출 상품에 포함되어 있는 pdf 
	 * @param productNo
	 * @return
	 */
	ProductFileVO selectFile(String productNo);
	/**
	 * 상품등록함
	 * @param productVO
	 * @param mpRequest
	 * @throws Exception 
	 */
	void insertProduct(LoanProductVO productVO, MultipartHttpServletRequest mpRequest) throws Exception;
	/**
	 * 상품 pdf 다운로드
	 * @param fileNo
	 * @return
	 */
	ProductFileVO selectFileInfo(int fileNo);

}
