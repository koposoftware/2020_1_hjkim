package kr.ac.kopo.admin.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;
import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.counselor.vo.LoanProductVO;

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

	@Override
	public ProductFileVO selectFile(String productNo) {
		return sqlSession.selectOne("admin.dao.AdminDAO.selectFile", productNo);
	}

	@Override
	public void insertProduct(LoanProductVO productVO) {
		sqlSession.insert("admin.dao.AdminDAO.insertProduct", productVO);
	}

	@Override
	public void insertProductFile(Map<String, Object> map) {
		sqlSession.insert("admin.dao.AdminDAO.insertProductFile", map);
	}

	@Override
	public ProductFileVO selectFileInfo(int fileNo) {
		return sqlSession.selectOne("admin.dao.AdminDAO.selectFileInfo", fileNo);
	}
	
}
