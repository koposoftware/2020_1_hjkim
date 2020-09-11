package kr.ac.kopo.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.admin.service.AdminService;
import kr.ac.kopo.admin.vo.BjdCodeVO;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping
	public String adminMain() {
		return "/admin/adminMain";
	}
	
	@RequestMapping("/ltvRegister")
	@ResponseBody
	public String ltvRegister(){
		return "/admin/ltvInput";
	}
	@RequestMapping("/{check}")
	@ResponseBody
	public List<BjdCodeVO> loadSigungu(@PathVariable("check") String check){
		List<BjdCodeVO> bjdCodeVO = new ArrayList<>();
		if(check.equalsIgnoreCase("sido")) {
			bjdCodeVO = adminService.selectSigungu();
		}
		return bjdCodeVO;
	}
}
