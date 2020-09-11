package kr.ac.kopo.loan.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.loan.service.LoanService;


@RequestMapping("/loan")
@Controller
public class LoanController {
	@Autowired
	private LoanService LoanService;
	
	@RequestMapping
	@ResponseBody
	public ModelAndView loanPreview(@RequestParam("aptCode") String aptCode){
		ModelAndView mav = new ModelAndView("loan/loan");
		
		return mav;
	}
}
