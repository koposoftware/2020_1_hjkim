package kr.ac.kopo.chat.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.chat.dao.ChatDAO;
import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.chat.vo.ChatListVO;
import kr.ac.kopo.member.vo.MemberVO;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatDAO chatDAO;
	
	@Override
	public int selectCounselor() {
		List<Integer> counselorList = chatDAO.selectCounselor();
		for(Integer test : counselorList) {
			System.out.println(test);
		}
		Random r = new Random();
		int counselorNo = counselorList.get(r.nextInt(counselorList.size()));
		return counselorNo;
	}

	@Override
	public int selectChatNo(int userNo) {
		int chatNo = chatDAO.selectChatNo(userNo);
		return chatNo;
	}

	@Override
	public void insertHistory(ChatHistoryVO historyVO) {
		chatDAO.insertHistory(historyVO);
	}

	@Override
	public void updateChatListUser(int userNo) {
		chatDAO.updateChatListUser(userNo);
	}

	@Override
	public List<ChatHistoryVO> selectHistoryList(int userNo) {
		List<ChatHistoryVO> history = chatDAO.selectHistoryList(userNo);
		return history;
	}

	@Override
	public void updateEndDate(int userNo) {
		chatDAO.updateEndDate(userNo);
	}

	@Override
	public List<ChatListVO> selectChatList(int userNo) {
		List<ChatListVO> chatList = chatDAO.selectChatList(userNo);
		return chatList;
	}

	@Override
	public MemberVO selectMemberName(int userNo) {
		MemberVO member = chatDAO.selectMemberName(userNo);
		return member;
	}

	@Override
	public int selectChatListCnt(int userNo) {
		int listCnt = chatDAO.selectChatListCnt(userNo);
		return listCnt;
	}

	@Override
	public List<ChatListUserNameVO> selectChatListPaging(Map<String, Object> paging) {
		List<ChatListUserNameVO> chatList = chatDAO.selectChatListPaging(paging);
		return chatList;
	}
	
}
