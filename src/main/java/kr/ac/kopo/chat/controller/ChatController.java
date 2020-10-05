package kr.ac.kopo.chat.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.apt.service.AptService;
import kr.ac.kopo.apt.vo.AptAllInfoVO;
import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptPriceVO;
import kr.ac.kopo.chat.service.ChatService;
import kr.ac.kopo.chat.vo.ChatHistoryVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.chat.vo.ExcelDownloadVO;
import kr.ac.kopo.common.Pagination;
import kr.ac.kopo.counselor.vo.LoanProductVO;

@Controller
public class ChatController {
	@Autowired
	private ChatService chatService;
	@Autowired
	private AptService aptService;
	
	
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

	@RequestMapping("/chat/downloadExcel")
	@ResponseBody
	public ModelAndView excelConvert(HttpServletRequest request) {
		String summary = request.getParameter("arr");
		String kaptCode = request.getParameter("kaptCode");
		System.out.println("kaptCode : " + kaptCode);
		AptBasicVO aptBasic = aptService.selectAptBasic(kaptCode);
		AptDetailVO aptDetail = aptService.selectAptDetailInOverlay(kaptCode);
		
		Gson gson = new Gson();
		ExcelDownloadVO[] excelData= gson.fromJson(summary, ExcelDownloadVO[].class);
		List<ExcelDownloadVO> excelList = Arrays.asList(excelData);
		ModelAndView mav = new ModelAndView("chatting/downloadExcel");
		mav.addObject("excelList", excelList);
		mav.addObject("aptBasic",aptBasic);
		mav.addObject("aptDetail",aptDetail);
		List<AptPriceVO> aptPrice = aptService.selectAptPrice(kaptCode);
		mav.addObject("aptPriceList", aptPrice);
		return mav;
	}
	
	@GetMapping("/chat/pdfLoad/{no}")
	@ResponseBody
	public ProductFileVO pdfLoad(@PathVariable("no") String fileNoStr) {
		int fileNo = Integer.parseInt(fileNoStr);
		ModelAndView mav = new ModelAndView();
		ProductFileVO loanFileVO = chatService.selectFile(fileNo);
		System.out.println(loanFileVO.toString());
		return loanFileVO;
	}
	
	@RequestMapping("/chat/online")
	public String onlineChat() {
		return "chatting/onlineConsulting";
	}
	
	@RequestMapping("/chat/chatHistoryDetail")
	@ResponseBody
	public List<ChatHistoryVO> chatHistoryDetail(@RequestParam("chatNo") int chatNo){
		System.out.println(chatNo);
		List<ChatHistoryVO> history = chatService.selectHistoryDetail(chatNo);
		return history;
	}
}
