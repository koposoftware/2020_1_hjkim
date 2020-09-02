package kr.ac.kopo.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberVO login(MemberVO member) {
		MemberVO loginVO = sqlSession.selectOne("member.dao.MemberDAO.login", member);
		return loginVO;
	}

	@Override
	public String idCheck(String id) {
		String idCheck = sqlSession.selectOne("member.dao.MemberDAO.idCheck", id);
		return idCheck;
	}

	@Override
	public void insertCounselor(int userNo) {
		sqlSession.insert("consulting.dao.consultingDAO.insertCounselor", userNo);
	}

	@Override
	public void deleteCounselor(int userNo) {
		sqlSession.delete("consulting.dao.consultingDAO.deleteCounselor", userNo);
	}
	
}
