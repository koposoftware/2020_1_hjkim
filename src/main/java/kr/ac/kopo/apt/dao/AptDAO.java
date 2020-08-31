package kr.ac.kopo.apt.dao;

import java.util.List;

import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptBjdCodeVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;
import kr.ac.kopo.apt.vo.AptPriceVO;
import kr.ac.kopo.apt.vo.AptSearchVO;

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
	
	/**
	 * 아파트 상세정보 (오버레이에 띄울 정보)
	 * @param aptNo 클릭한 아파트의 번호
	 * @return 클릭한 아파트의 주소, 동수, 세대수, 도로명주소
	 */
	public AptDetailVO selectAptDetailInOverlay(String aptNo);
	/**
	 * 아파트 상세정보 (모든 정보)
	 * @param aptNo 클릭한 아파트의 번호
	 * @return 해당아파트의 모든정보 출력
	 */
	public AptDetailVO selectAptDetailInfo(String aptNo);
	
	/**
	 * 검색한 아파트 기본정보
	 * @param str 검색어
	 * @return
	 */
	public List<AptSearchVO> selectAptSearch(String str);
	/**
	 * 법정동 코드로 지역 이름 lat,lng값 구해옴
	 * @param bjdCode 검색할 법정동코드
	 * @return 지역이름, lat, lng
	 */
	public AptBjdCodeVO selectAptBjdCode(long bjdCode);
	
	/**
	 * 아파트 코드로 lat,lng 구해옴
	 * @param code
	 * @return
	 */
	public AptLatLngVO selectAptLatLng(String code);
	
	/**
	 * 아파트 코드로 해당 아파트의 거래내역 가져오기
	 * @param kaptCode 
	 * @return 아파트 거래내역 리스트
	 */
	public List<AptPriceVO> selectAptPrice(String kaptCode);
}
