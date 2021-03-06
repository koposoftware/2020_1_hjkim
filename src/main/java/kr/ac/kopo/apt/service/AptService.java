package kr.ac.kopo.apt.service;

import java.util.List;
import java.util.Map;

import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptBjdCodeVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;
import kr.ac.kopo.apt.vo.AptPriceChartVO;
import kr.ac.kopo.apt.vo.AptPriceVO;
import kr.ac.kopo.apt.vo.AptSearchVO;
import kr.ac.kopo.apt.vo.BasketVO;

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
	/**
	 * 검색한 아파트 정보
	 * @param str 검색한 keyword
	 * @return 
	 */

	List<AptSearchVO> selectAptSearch(String str);
	/**
	 * 법정동 코드로 지역 정보 select
	 * @param bjdCode
	 * @return AptBjdCodeVO
	 */
	AptBjdCodeVO selectBjdCode(long bjdCode);
	/**
	 * 아파트 코드로 lat, lng값 가져오기
	 * @param code 검색할 아파트 코드
	 * @return
	 */
	AptLatLngVO selectLatLng(String code);
	/**
	 * 아파트 코드로 거래 내역 조회
	 * @param kaptCode
	 * @return 해당 아파트의 거래내역 리스트
	 */
	List<AptPriceVO> selectAptPrice(String kaptCode);

	/**
	 * 차트를 그리기 위해, 해당 아파트의 면적을 가져온다.
	 * @param kaptCode
	 * @return
	 */
	List<AptPriceChartVO> selectAptPriceChartArea(String kaptCode);
	/**
	 * 월별 avg, max, min 
	 * @param vo
	 * @return
	 */
	List<AptPriceChartVO> selectAptPriceChartAreaYYMM(AptPriceChartVO vo);
	/**
	 * 장바구니 되어 있는지 확인
	 * @param basket
	 * @return
	 */
	BasketVO selectBasketOne(BasketVO basket);
	/**
	 * 장바구니 추가
	 * @param basket
	 */
	void insertBasket(BasketVO basket);
	/**
	 * 장바구니 삭제
	 * @param basket
	 */
	void deleteBasket(BasketVO basket);
	/**
	 * 장바구니 개수
	 * @param userNo
	 * @return
	 */
	int selectBasketCnt(int userNo);
	/**
	 * 장바구니 목록가져오기
	 * @param pagingMap
	 * @return
	 */
	List<BasketVO> selectBasketAll(Map<String, Object> pagingMap);
}
