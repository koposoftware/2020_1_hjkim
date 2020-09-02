package kr.ac.kopo.chat.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.chat.vo.ChatVO;

@Repository
public class ChatDAOImpl implements ChatDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Integer> selectCounselor() {
		List<Integer> counselorList = sqlSession.selectList("consulting.dao.consultingDAO.selectCounselor");
		return counselorList;
	}

	@Override
	public void updateUser(ChatVO chatVO) {
		sqlSession.update("consulting.dao.consultingDAO.updateUser", chatVO);
	}

	@Override
	public ChatVO selectTarget(int userNo) {
		ChatVO target = sqlSession.selectOne("consulting.dao.consultingDAO.selectTarget",userNo);
		return target;
	}
	
}
