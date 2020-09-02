package kr.ac.kopo.counselor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo.counselor.service.CounselorService;

@Controller
public class CounselorController {
	@Autowired
	private CounselorService counselorService;
	
	@GetMapping("/counselor")
	public String counselorMain() {
		return "/employee/counselor";
	}
	
	@RequestMapping("/consulting/online")
	public String consultingOnline() {
		return "/employee/online";
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
