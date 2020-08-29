package kr.ac.kopo.apt.dao;

import java.util.List;

import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;

public interface AptDAO {
	/**
	 * 아파트 위경도 조회 서비스
	 * @param bounds 
	 */
	public List<AptLatLngVO> selectLatLng(List<AptLatLngVO> bounds);
	/**
	 * 아파트 기본정보
	 * @param 클릭한 아파트의 번호
	 * @return 클릭한 아파트의 법정동코드, 아파트 코드, 아파트 이름이 들어있음
	 */
	public AptBasicVO selectAptBasic(String aptNo);
	public AptDetailVO selectAptDetailInOverlay(String aptNo);
}
