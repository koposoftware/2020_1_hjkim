package kr.ac.kopo.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.chat.service.ChatService;
import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.common.Pagination;

@Controller
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	@RequestMapping("/chat")
	@ResponseBody
	public ModelAndView chatForm(@RequestParam("kaptCode") String aptCode) {
		ModelAndView mav = new ModelAndView("chatting/chat");
		mav.addObject("kaptCode", aptCode);
		return mav;
	}
	
	@RequestMapping("/chat/loading")
	@ResponseBody
	public ModelAndView historyLoad(@RequestParam("userNo") int userNo){
		List<ChatHistoryVO> history = chatService.selectHistoryList(userNo);
		ModelAndView mav = new ModelAndView("employee/chatLoading");
		mav.addObject("history", history);
		mav.addObject("userNo", userNo);
		return mav;
	}

	/* 페이징처리 한것 */
	@RequestMapping("/chat/chatPagination")
	@ResponseBody
	public ModelAndView chatPagination(	@RequestParam("userNo") int userNo
										, @RequestParam(required = false, defaultValue = "1") int page
										, @RequestParam(required = false, defaultValue = "1") int range) 
	{
		int listCnt = chatService.selectChatListCnt(userNo);
		
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		Map<String, Object> pagingMap = new HashMap<>();
		pagingMap.put("pagination", pagination);
		pagingMap.put("userNo", userNo);
		
		List<ChatListUserNameVO> ChatListUserNameList = chatService.selectChatListPaging(pagingMap);
		
		ModelAndView mav = new ModelAndView("chatting/chatList");
		mav.addObject("chatListUserNameList", ChatListUserNameList);
		mav.addObject("pagination", pagination);
		return mav;
	}
}
