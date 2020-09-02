package kr.ac.kopo.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.chat.service.ChatService;
import kr.ac.kopo.chat.vo.ChatVO;

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
	
	@RequestMapping("/chat/targetCheck")
	@ResponseBody
	public ChatVO targetCheck(@RequestParam("checkNo") int userNo) {
		System.out.println("targetCheck 들어옴");
		ChatVO target = chatService.selectTarget(userNo);
		return target;
	}
}
