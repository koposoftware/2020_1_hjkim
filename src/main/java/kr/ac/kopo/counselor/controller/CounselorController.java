package kr.ac.kopo.counselor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.counselor.service.CounselorService;
import kr.ac.kopo.counselor.vo.ChatAutoVO;
import kr.ac.kopo.member.vo.MemberVO;

@Controller
public class CounselorController {
	@Autowired
	private CounselorService counselorService;
	
	@GetMapping("/counselor")
	public String counselorMain() {
		return "/employee/counselor";
	}
	
	@RequestMapping("/consulting/online")
	public ModelAndView consultingOnline(HttpSession session) {
		MemberVO loginVO = (MemberVO) session.getAttribute("loginVO"); 
		ModelAndView mav = new ModelAndView("employee/online");
		List<ChatAutoVO> adminAutoList = counselorService.selectAutoWord(0);
		List<ChatAutoVO> counselorAutoList = counselorService.selectAutoWord(loginVO.getUserNo());
		mav.addObject("adminAutoList", adminAutoList);
		mav.addObject("counselorAutoList", counselorAutoList);
		return mav;
	}

	@RequestMapping("/consulting/offline")
	public String consultingOffline() {
		return "/employee/offline";
	}
	
	@RequestMapping("/consulting/history")
	public String history() {
		return "/employee/history";
	}
}
