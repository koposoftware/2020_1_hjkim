package kr.ac.kopo.counselor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.counselor.service.CounselorService;
import kr.ac.kopo.counselor.vo.ChatAutoVO;
import kr.ac.kopo.counselor.vo.LoanProductVO;
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
		
		List<ProductFileVO> loanFileList = counselorService.selectFileList();
		mav.addObject("fileList", loanFileList);
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
	
	@GetMapping("/counselor/loadLoanProduct")
	@ResponseBody
	public List<LoanProductVO> loadLoanProduct(){
		System.out.println("loan");
		List<LoanProductVO> loanList = counselorService.selectLoanProduct();
		return loanList;
	}
	
	@PostMapping("/counselor/loadLoanProduct")
	@ResponseBody
	public LoanProductVO loadLoanProductOne(@RequestParam("productCode") String productCode) {
		LoanProductVO loan = counselorService.selectLoanProductOne(productCode);
		return loan;
	}
	
	@PostMapping("/counselor/autoWord")
	@ResponseBody
	@Transactional
	public ModelAndView counselorAuto(@RequestParam("word") String word, HttpSession session){
		ModelAndView mav = new ModelAndView("employee/addAutoWord");
		MemberVO loginVO = (MemberVO) session.getAttribute("loginVO"); 
		word = word.replace("\r\n", "<br>");
		ChatAutoVO autoWord = new ChatAutoVO();
		autoWord.setUserNo(loginVO.getUserNo());
		autoWord.setContent(word);
		counselorService.insertAutoWordCounselor(autoWord);
		List<ChatAutoVO> counselorAutoList = counselorService.selectAutoWord(loginVO.getUserNo());
		mav.addObject("counselorAutoList", counselorAutoList);
		return mav;
	}
}
