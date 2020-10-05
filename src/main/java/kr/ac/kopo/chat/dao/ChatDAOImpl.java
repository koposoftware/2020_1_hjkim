package kr.ac.kopo.chat.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.chat.vo.ChatListVO;
import kr.ac.kopo.member.vo.MemberVO;

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
	public int selectChatNo(int userNo) {
		int chatNo = sqlSession.selectOne("consulting.dao.consultingDAO.selectChatNo", userNo);
		return chatNo;
	}

	@Override
	public void insertHistory(ChatHistoryVO historyVO) {
		sqlSession.insert("consulting.dao.consultingDAO.insertHistory", historyVO);
	}

	@Override
	public void updateChatListUser(int userNo) {
		sqlSession.update("consulting.dao.consultingDAO.updateChatListUser", userNo);
	}

	@Override
	public List<ChatHistoryVO> selectHistoryList(int userNo) {
		List<ChatHistoryVO> history = sqlSession.selectList("consulting.dao.consultingDAO.selectHistoryList", userNo);
		return history;
	}

	@Override
	public void updateEndDate(int userNo) {
		sqlSession.update("consulting.dao.consultingDAO.updateEndDate", userNo);
	}

	@Override
	public List<ChatListVO> selectChatList(int userNo) {
		List<ChatListVO> chatList = sqlSession.selectList("consulting.dao.consultingDAO.selectChatList", userNo);
		return chatList;
	}

	@Override
	public MemberVO selectMemberName(int userNo) {
		MemberVO member = sqlSession.selectOne("consulting.dao.consultingDAO.selectMemberName",userNo);
		return member;
	}

	@Override
	public int selectChatListCnt(int userNo) {
		int listCnt = sqlSession.selectOne("consulting.dao.consultingDAO.selectChatListCnt", userNo);
		return listCnt;
	}

	@Override
	public List<ChatListUserNameVO> selectChatListPaging(Map<String, Object> map) {
		List<ChatListUserNameVO> chatList= sqlSession.selectList("consulting.dao.consultingDAO.selectChatListPaging", map);
		return chatList;
	}

	@Override
	public ProductFileVO selectFile(int fileNo) {
		return sqlSession.selectOne("consulting.dao.consultingDAO.selectFile", fileNo);
	}

	@Override
	public List<ChatHistoryVO> selectHistoryDetail(int chatNo) {
		return sqlSession.selectList("consulting.dao.consultingDAO.selectHistoryDetail", chatNo);
	}
	
}
