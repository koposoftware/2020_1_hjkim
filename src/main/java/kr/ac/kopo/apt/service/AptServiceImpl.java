package kr.ac.kopo.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.apt.dao.AptDAO;
import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;

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
	
}
