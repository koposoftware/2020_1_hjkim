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
	AptBasicVO selectAptBasic(String aptNo);
	AptDetailVO selectAptDetailInOverlay(String aptNo);
}
