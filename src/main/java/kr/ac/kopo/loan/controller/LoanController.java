package kr.ac.kopo.loan.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.loan.service.LoanService;
import kr.ac.kopo.loan.vo.LoanCalcVO;
import kr.ac.kopo.loan.vo.LoanVO;


@RequestMapping("/loan")
@Controller
public class LoanController {
	@Autowired
	private LoanService loanService;
	
	@RequestMapping
	@ResponseBody
	public ModelAndView loanPreview(@RequestParam("aptCode") String aptCode){
		ModelAndView mav = new ModelAndView("loan/loan");
		String aptName = loanService.selectAptName(aptCode);
		System.out.println(aptName);
		List<Double> aptArea = loanService.selectAptArea(aptCode);
		
		mav.addObject("aptCode", aptCode);
		mav.addObject("aptName", aptName);
		mav.addObject("aptAreaList", aptArea);
		return mav;
	}
	
	@RequestMapping("/priceGet")
	@ResponseBody
	public int priceGet(@RequestParam("floor") String floor, @RequestParam("area") String area, @RequestParam("aptCode") String aptCode) {
		LoanVO loanVO = new LoanVO();
		loanVO.setAptFloor(Integer.parseInt(floor));
		loanVO.setAptArea(Double.parseDouble(area));
		loanVO.setKaptCode(aptCode);
		
		int price = loanService.selectAptPrice(loanVO);
		System.out.println(price);
		return price;
	}
	
	@RequestMapping("/loanCalc")
	@ResponseBody
	public ModelAndView loanCalc(@RequestParam("aptCode") String aptCode, @RequestParam("price") String price, @RequestParam("userPriceInfo") String userType) {
		ModelAndView mav = new ModelAndView("loan/loanCalc");
		LoanCalcVO calcVO = new LoanCalcVO();
		LoanCalcVO resultVO = new LoanCalcVO();
		String division = loanService.selectLawDivision(aptCode);		// 투기지역인지, 조정대상지역인지
		List<LoanCalcVO> percentList = new ArrayList<>();
		
		calcVO.setAreaType(division);
		calcVO.setAptPrice(Integer.parseInt(price));
		calcVO.setLoanType(userType);
		int loanMaxPrice = 0;
		if(division.equals("투기지역") || division.equals("조정대상지역")) {
			percentList = loanService.selectLtvPercent(calcVO);
			// 9억원 이하
			if(percentList.size() == 1) {
				for(LoanCalcVO vo : percentList) {
					loanMaxPrice = (int) Math.round(Integer.parseInt(price) * vo.getAptLtv() * 0.01); 
				}
			}else {
				for(LoanCalcVO vo : percentList) {
					if(vo.getLoanType().equals("9억원 이하")) {
						loanMaxPrice += (int) Math.round(Integer.parseInt(price) * vo.getAptLtv() * 0.01);
					}else {
						loanMaxPrice += (int) Math.round((Integer.parseInt(price)-90000) * vo.getAptLtv() * 0.01);
					}
				}
			}
		}
		resultVO.setAptLtvList(percentList);
		resultVO.setAptPrice(Integer.parseInt(price));
		resultVO.setAreaType(division);
		resultVO.setLoanMaxPrice(loanMaxPrice);
		
		mav.addObject("resultVO", resultVO);
		return mav;
	}
}
