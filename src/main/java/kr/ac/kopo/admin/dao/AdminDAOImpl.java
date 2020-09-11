package kr.ac.kopo.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BjdCodeVO> selectSido() {
		List<BjdCodeVO> bjdCodeList = sqlSession.selectList("admin.dao.AdminDAO.sido");
		return bjdCodeList;
	}

	@Override
	public List<BjdCodeVO> selectSigungu(String code) {
		List<BjdCodeVO> bjdCodeList = sqlSession.selectList("admin.dao.AdminDAO.sigungu", code);
		return bjdCodeList;
	}

	@Override
	public List<BjdCodeVO> selectEupmyeondong(String code) {
		List<BjdCodeVO> bjdCodeList = sqlSession.selectList("admin.dao.AdminDAO.eupmyeondong", code);
		return bjdCodeList;
	}

	@Override
	public String selectCodeInfo(String code) {
		String check = sqlSession.selectOne("admin.dao.AdminDAO.selectCodeInfo", code );
		return check;
	}

	@Override
	public void insertLawArea(LawAreaVO areaVO) {
		sqlSession.insert("admin.dao.AdminDAO.insertLawArea", areaVO );
	}

	@Override
	public void updateLawArea(LawAreaVO areaVO) {
		sqlSession.update("admin.dao.AdminDAO.updateLawArea", areaVO);
	}
}
