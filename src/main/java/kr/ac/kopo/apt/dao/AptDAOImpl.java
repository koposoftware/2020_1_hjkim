package kr.ac.kopo.apt.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;

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
		AptDetailVO aptBasic = sqlSession.selectOne("apt.dao.AptDAO.selectDetailInOverlay", aptNo);
		return aptBasic;
	}
	
}
