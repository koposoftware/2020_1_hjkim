package kr.ac.kopo.admin.dao;

import java.util.List;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;

public interface AdminDAO {
	/**
	 * 시/도를 가지고 온다.
	 * @return
	 */
	List<BjdCodeVO> selectSido();
	/**
	 * 선택한 시/도에 맞는 시/군/구를 가지고 온다.
	 * @param code
	 * @return
	 */
	List<BjdCodeVO> selectSigungu(String code);
	/**
	 * 선택한 시/군/구에 맞는 읍면동을 가지고 온다.
	 * @param code
	 * @return
	 */
	List<BjdCodeVO> selectEupmyeondong(String code);
	/**
	 * 내가 선택한 코드가 데이터에 있는지 없는지 확인
	 * @param code
	 * @return
	 */
	String selectCodeInfo(String code);
	/**
	 * 법정동코드와 유형 테이블에 삽입
	 * @param areaVO
	 */
	void insertLawArea(LawAreaVO areaVO);
	/**
	 * 입력한 코드가 이미 있다면 update실행
	 * @param areaVO
	 */
	void updateLawArea(LawAreaVO areaVO);
}
