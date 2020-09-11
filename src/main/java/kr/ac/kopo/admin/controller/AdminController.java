package kr.ac.kopo.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo.admin.service.AdminService;
import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping
	public String adminMain() {
		return "/admin/adminMain";
	}
	
	@GetMapping("/ltvRegister")
	public String ltvRegister(){
		return "/admin/ltvInput";
	}
	@PostMapping("/ltvRegister")
	@ResponseBody
	public void ltvRegisterDB(@RequestParam("bjdCode") String code, @RequestParam("areaInfo") String areaInfo) {
		LawAreaVO areaVO = new LawAreaVO();
		String checkData = adminService.selectCodeInfo(code);
		areaVO.setLawBjdCode(code);
		areaVO.setLawDivision(areaInfo);
		if(checkData == null) {
			adminService.insertLawArea(areaVO);
		}else {
			adminService.updateLawArea(areaVO);
		}
	}
	
	@RequestMapping("/{check}")
	@ResponseBody
	public List<BjdCodeVO> loadSigungu(@PathVariable("check") String check, @RequestParam("code") String code){
		List<BjdCodeVO> bjdCodeVO = new ArrayList<>();
		if(check.equalsIgnoreCase("sido")) {
			bjdCodeVO = adminService.selectSido();
		}else if(check.equalsIgnoreCase("sigungu")) {
			bjdCodeVO = adminService.selectSigungu(code);
		}else if(check.equalsIgnoreCase("eupmyeondong")) {
			bjdCodeVO = adminService.selectEupmyeondong(code);
		}
		return bjdCodeVO;
	}
}
