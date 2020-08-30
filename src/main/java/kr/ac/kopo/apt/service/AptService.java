package kr.ac.kopo.apt.service;

import java.util.List;

import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;

public interface AptService {
	
	/**
	 * 위경도를 얻어와서 맵에 띄운다.
	 * @param bounds 
	 * @param maxLatLng 
	 * @param minLatLng 
	 * @return apt의 위경도 리스트
	 */
	List<AptLatLngVO> selectLatLng(List<AptLatLngVO> bounds);
	
	/**
	 * 아파트 기본정보 
	 * @param aptNo 출력할 아파트 code
	 * @return 아파트 이름, 아파트 코드
	 */
	AptBasicVO selectAptBasic(String aptNo);
	
	/**
	 * 아파트 상세정보 (오버레이에 출력될 정보들만)
	 * @param aptNo 출력할 아파트 code
	 * @return kaptAddr, kaptDongCnt, kaptDaCnt, doroJuso
	 */
	AptDetailVO selectAptDetailInOverlay(String aptNo);
	
	/**
	 * 아파트 상세정보 (모든 정보)
	 * @param aptNo 출력할 아파트 code
	 * @return 
	 */
	AptDetailVO selectAptDetail(String aptNo);
}
