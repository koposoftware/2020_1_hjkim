package kr.ac.kopo.admin.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.admin.service.AdminService;
import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.LawAreaVO;
import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.counselor.service.CounselorService;
import kr.ac.kopo.counselor.vo.LoanProductVO;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private CounselorService counselorService;
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping
	public String adminMain() {
		return "/admin/adminMain";
	}
	
	@GetMapping("/ltvRegister")
	public String ltvRegister(){
		return "admin/calcLoan/ltvInput";
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
	
	/**
		상품 설명 페이지 등록
	 */
	
	@GetMapping("/productList")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("admin/product/product");
		List<LoanProductVO> loanList = counselorService.selectLoanProduct();
		mav.addObject("loanList", loanList);
		
		return mav;
	}
	
	@RequestMapping("/product/{no}")
	public ModelAndView productDetail(@PathVariable("no") String productNo) {
		ModelAndView mav = new ModelAndView("admin/product/productDetail");
		LoanProductVO loanVO = counselorService.selectLoanProductOne(productNo);
		mav.addObject("loanProduct", loanVO);
		
		ProductFileVO loanFileVO = adminService.selectFile(productNo);
		mav.addObject("loanFileVO", loanFileVO);
		System.out.println(loanFileVO);
		return mav;
	}
	@GetMapping("/insertProduct")
	public String productInsertGet(Model model) {
		System.out.println("productInsertGet");
		LoanProductVO loanVO = new LoanProductVO();
		
		model.addAttribute("loanVO",loanVO);
		
		return "admin/product/productInsert";
	}
	@PostMapping("/insertProduct")
	public String productInsert(@Valid LoanProductVO productVO, MultipartHttpServletRequest mpRequest) throws Exception {
		adminService.insertProduct(productVO, mpRequest);
		return "redirect:/admin/productList";
	}
	
	@RequestMapping("/fileDown")
	public void fileDown(@RequestParam("fileNo") int fileNo, HttpServletResponse response) throws IOException {
		
		ProductFileVO loanFileVO = adminService.selectFileInfo(fileNo);
		System.out.println(fileNo);
		String storedFileName = loanFileVO.getStoredFileName();
		String originalFileName = loanFileVO.getOrgFileName();
		
		String uploadDir = servletContext.getRealPath("/upload/");
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File(uploadDir + storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@GetMapping("/autoWord")
	public String goAutoWord() {
		return "admin/addAutoWord";
	}
}
