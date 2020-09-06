package kr.ac.kopo.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.chat.service.ChatService;
import kr.ac.kopo.chat.vo.ChatHistoryVO;

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
	
	@RequestMapping("/chat/loadHistory")
	@ResponseBody
	public ModelAndView historyLoad(@RequestParam("userNo") int userNo){
		List<ChatHistoryVO> history = chatService.selectHistoryList(userNo);
		ModelAndView mav = new ModelAndView("employee/chatHistory");
		mav.addObject("history", history);
		mav.addObject("userNo", userNo);
		return mav;
	}
}
