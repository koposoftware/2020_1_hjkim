package kr.ac.kopo.admin.service;

import java.util.List;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;

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

}
