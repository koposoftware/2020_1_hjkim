package kr.ac.kopo.chat.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.chat.dao.ChatDAO;
import kr.ac.kopo.chat.vo.ChatVO;

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
	public void updateUser(ChatVO chatVO) {
		chatDAO.updateUser(chatVO);
	}

	@Override
	public ChatVO selectTarget(int userNo) {
		ChatVO target = chatDAO.selectTarget(userNo);
		return target;
	}
	
}
