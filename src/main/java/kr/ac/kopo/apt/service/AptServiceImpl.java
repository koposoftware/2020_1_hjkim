package kr.ac.kopo.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.apt.dao.AptDAO;
import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptBjdCodeVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;
import kr.ac.kopo.apt.vo.AptPriceChartVO;
import kr.ac.kopo.apt.vo.AptPriceVO;
import kr.ac.kopo.apt.vo.AptSearchVO;

@Service
public class AptServiceImpl implements AptService{

	@Autowired
	private AptDAO aptDAO;
	
	@Override
	public List<AptLatLngVO> selectLatLng(List<AptLatLngVO> bounds) {
		List<AptLatLngVO> aptLatLng = aptDAO.selectLatLng(bounds); 
		return aptLatLng;
	}

	@Override
	public AptBasicVO selectAptBasic(String aptNo) {
		AptBasicVO aptBasic = aptDAO.selectAptBasic(aptNo);
		return aptBasic;
	}

	@Override
	public AptDetailVO selectAptDetailInOverlay(String aptNo) {
		AptDetailVO aptDetail = aptDAO.selectAptDetailInOverlay(aptNo);
		return aptDetail;
	}

	@Override
	public AptDetailVO selectAptDetail(String aptNo) {
		AptDetailVO aptDetail = aptDAO.selectAptDetailInfo(aptNo);
		return aptDetail;
	}

	@Override
	public List<AptSearchVO> selectAptSearch(String str) {
		List<AptSearchVO> aptSearch = aptDAO.selectAptSearch(str);
		return aptSearch;
	}

	@Override
	public AptBjdCodeVO selectBjdCode(long bjdCode) {
		AptBjdCodeVO aptBjdCode = aptDAO.selectAptBjdCode(bjdCode);
		return aptBjdCode;
	}

	@Override
	public AptLatLngVO selectLatLng(String code) {
		AptLatLngVO aptLatLng = aptDAO.selectAptLatLng(code);
		return aptLatLng;
	}

	@Override
	public List<AptPriceVO> selectAptPrice(String kaptCode) {
		List<AptPriceVO> aptPrice = aptDAO.selectAptPrice(kaptCode);
		return aptPrice;
	}

	@Override
	public List<AptPriceChartVO> selectAptPriceChartArea(String kaptCode) {
		List<AptPriceChartVO> aptPriceChartArea = aptDAO.selectAptPriceChartArea(kaptCode);
		return aptPriceChartArea;
	}

	@Override
	public List<AptPriceChartVO> selectAptPriceChartAreaYYMM(AptPriceChartVO vo) {
		List<AptPriceChartVO> aptPriceChartArea = aptDAO.selectAptPriceChartArea(vo);
		return aptPriceChartArea;
	}
}
