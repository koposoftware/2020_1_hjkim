package kr.ac.kopo.apt.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptBjdCodeVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;
import kr.ac.kopo.apt.vo.AptPriceVO;
import kr.ac.kopo.apt.vo.AptSearchVO;

@Repository
public class AptDAOImpl implements AptDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
		
	@Override
	public List<AptLatLngVO> selectLatLng(List<AptLatLngVO> bounds) {
		List<AptLatLngVO> aptLatLng = sqlSession.selectList("apt.dao.AptDAO.selectLatLng",bounds);
		return aptLatLng;
	}

	@Override
	public AptBasicVO selectAptBasic(String aptNo) {
		AptBasicVO aptBasic = sqlSession.selectOne("apt.dao.AptDAO.selectBasicInfo", aptNo);
		return aptBasic;
	}

	@Override
	public AptDetailVO selectAptDetailInOverlay(String aptNo) {
		AptDetailVO aptDetail = sqlSession.selectOne("apt.dao.AptDAO.selectDetailInOverlay", aptNo);
		return aptDetail;
	}

	@Override
	public AptDetailVO selectAptDetailInfo(String aptNo) {
		AptDetailVO aptDetail = sqlSession.selectOne("apt.dao.AptDAO.selectDetailInfo", aptNo);
		return aptDetail;
	}

	@Override
	public List<AptSearchVO> selectAptSearch(String str) {
		List<AptSearchVO> aptSearch = sqlSession.selectList("apt.dao.AptDAO.selectAptSearch", str);
		return aptSearch;
	}

	@Override
	public AptBjdCodeVO selectAptBjdCode(long bjdCode) {
		AptBjdCodeVO aptBjdCode = sqlSession.selectOne("apt.dao.AptDAO.selectBjdCode", bjdCode);
		return aptBjdCode;
	}

	@Override
	public AptLatLngVO selectAptLatLng(String code) {
		AptLatLngVO aptLatLng = sqlSession.selectOne("apt.dao.AptDAO.selectAptLatLng", code);
		return aptLatLng;
	}

	@Override
	public List<AptPriceVO> selectAptPrice(String kaptCode) {
		List<AptPriceVO> aptPrice = sqlSession.selectList("apt.dao.AptDAO.selectAptPrice", kaptCode);
		return aptPrice;
	}
	
}
