package kr.ac.kopo.apt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.apt.service.AptService;
import kr.ac.kopo.apt.vo.AptAllInfoVO;
import kr.ac.kopo.apt.vo.AptBasicAndLatLngVO;
import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptBjdCodeVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;
import kr.ac.kopo.apt.vo.AptPriceChartVO;
import kr.ac.kopo.apt.vo.AptPriceVO;
import kr.ac.kopo.apt.vo.AptSearchVO;
import kr.ac.kopo.apt.vo.BasketVO;
import kr.ac.kopo.common.Pagination;
import kr.ac.kopo.member.vo.MemberVO;

@RequestMapping("/apt")
@Controller
public class AptController {

	@Autowired
	private AptService aptService;

	@RequestMapping("/aptMap")
	public String aptMap() {
		return "/apt/aptMap";
	}

	@RequestMapping("/{kaptCode}/{type}")
	public ModelAndView detail(@PathVariable("kaptCode") String kaptCode, @PathVariable("type") String type) {
		ModelAndView mav = new ModelAndView();
		if (type.equalsIgnoreCase("detailinfo")) {
			AptBasicVO aptBasic = aptService.selectAptBasic(kaptCode);
			AptDetailVO aptDetail = aptService.selectAptDetail(kaptCode);
			AptAllInfoVO aptAllInfo = new AptAllInfoVO();
			aptAllInfo.setAptBasicVO(aptBasic);
			aptAllInfo.setAptDetailVO(aptDetail);
			mav.setViewName("/apt/aptDetailInfo");
			mav.addObject("detailVO", aptAllInfo);
		} else if (type.equalsIgnoreCase("detailPrice")) {
			List<AptPriceVO> aptPrice = aptService.selectAptPrice(kaptCode);
			mav.addObject("aptPriceList", aptPrice);
			mav.addObject("aptCode", kaptCode);
			mav.setViewName("/apt/aptDetailPrice");
		} else if (type.equalsIgnoreCase("consulting")) {
			mav.setViewName("/apt/consultingMenu");
			mav.addObject("aptCode", kaptCode);
		}

		return mav;
	}

	@RequestMapping("/aptLatLng.json")
	@ResponseBody
	public Map<String, List<AptLatLngVO>> resJsonBody(@RequestParam("da") double da, @RequestParam("ia") double ia,
			@RequestParam("ka") double ka, @RequestParam("ja") double ja) {
		AptLatLngVO minLatLng = new AptLatLngVO();
		AptLatLngVO maxLatLng = new AptLatLngVO();

		minLatLng.setLat(ka);
		minLatLng.setLng(da);

		maxLatLng.setLat(ja);
		maxLatLng.setLng(ia);

		List<AptLatLngVO> bounds = new ArrayList<>();
		bounds.add(minLatLng);
		bounds.add(maxLatLng);

		Map<String, List<AptLatLngVO>> aptLatLng = new HashMap<>();
		List<AptLatLngVO> result = aptService.selectLatLng(bounds);// 위경도 리스트 가져오기
		aptLatLng.put("positions", result);
		return aptLatLng;
	}

	@RequestMapping("/aptBasic/{aptNo}")
	@ResponseBody
	public AptAllInfoVO aptBasic(@PathVariable("aptNo") String aptNo) {
		AptBasicVO aptBasic = aptService.selectAptBasic(aptNo);
		AptDetailVO aptDetail = aptService.selectAptDetailInOverlay(aptNo);
		AptAllInfoVO aptOverlay = new AptAllInfoVO();
		aptOverlay.setAptBasicVO(aptBasic);
		aptOverlay.setAptDetailVO(aptDetail);
		
		return aptOverlay;
	}

	@RequestMapping("/search")
	@ResponseBody
	public ModelAndView aptSearch(@RequestParam("searchText") String searchText) {
		ModelAndView mav = new ModelAndView();
		String str = "";
		str += "%";
		for (int i = 0; i < searchText.length(); i++) {
			str += searchText.substring(i, i + 1);
			str += "%";
		}
		List<AptSearchVO> aptSearchList = aptService.selectAptSearch(str);
		List<AptBasicAndLatLngVO> aptBasicAndLatLngList = new ArrayList<>();
		List<AptBjdCodeVO> aptBjdCodeList = new ArrayList<>();
		for (AptSearchVO vo : aptSearchList) {
			if (vo.getType().equalsIgnoreCase("a")) {
				AptBasicAndLatLngVO aptBasicAndLatLng = new AptBasicAndLatLngVO();
				AptBasicVO aptBasic = aptService.selectAptBasic(vo.getCode());
				AptLatLngVO aptLatLng = aptService.selectLatLng(vo.getCode());
				aptBasicAndLatLng.setAptBasicVO(aptBasic);
				aptBasicAndLatLng.setAptLatLngVO(aptLatLng);
				aptBasicAndLatLngList.add(aptBasicAndLatLng);
			} else if (vo.getType().equals("b")) {
				AptBjdCodeVO aptBjdCode = aptService.selectBjdCode(Long.parseLong(vo.getCode()));
				aptBjdCodeList.add(aptBjdCode);
			}
		}
		mav.setViewName("/apt/aptSearchList");
		mav.addObject("aptBasicAndLatLngList", aptBasicAndLatLngList);
		mav.addObject("aptBjdCodeList", aptBjdCodeList);
		return mav;
	}
	
	@RequestMapping("/priceChart.json")
	@ResponseBody
	public Map<String,List<AptPriceChartVO>> resJsonChart(@RequestParam("aptCode") String aptCode) {
		Map<String,List<AptPriceChartVO>> json = new HashMap<String, List<AptPriceChartVO>>();
		List<AptPriceChartVO> aptPrice = aptService.selectAptPriceChartArea(aptCode);
		for(AptPriceChartVO vo : aptPrice) {
			List<AptPriceChartVO> aptPriceToArea = aptService.selectAptPriceChartAreaYYMM(vo);
			json.put(Double.toString(vo.getArea()),aptPriceToArea);
		}
		return json;
	}
	
	@RequestMapping("/basketCheck")
	@ResponseBody
	public BasketVO basketCheck(@RequestParam("kaptCode") String kaptCode, @RequestParam("userNo") String userNo) {
		BasketVO basket = new BasketVO();
		basket.setKaptCode(kaptCode);
		basket.setUserNo(Integer.parseInt(userNo));
		BasketVO basketCheck = aptService.selectBasketOne(basket);
		return basketCheck;
	}
	
	@RequestMapping("/addBasket")
	@ResponseBody
	public void basketInsert(@RequestParam("kaptCode") String kaptCode, @RequestParam("userNo") String userNo) {
		BasketVO basket = new BasketVO();
		basket.setKaptCode(kaptCode);
		basket.setUserNo(Integer.parseInt(userNo));
		aptService.insertBasket(basket);
	}
	
	@RequestMapping("/removeBasket")
	@ResponseBody
	public void basketDelete(@RequestParam("kaptCode") String kaptCode, @RequestParam("userNo") String userNo) {
		BasketVO basket = new BasketVO();
		basket.setKaptCode(kaptCode);
		basket.setUserNo(Integer.parseInt(userNo));
		aptService.deleteBasket(basket);
	}
	
	@RequestMapping("/bookMark")
	public ModelAndView aptBasket(HttpSession session
								, @RequestParam(required = false, defaultValue = "1") int page
								, @RequestParam(required = false, defaultValue = "1") int range) {
		ModelAndView mav = new ModelAndView("apt/aptBasket");

		MemberVO loginVO = (MemberVO)session.getAttribute("loginVO");
		//1. 로그인한 user의 basket count를 가져온다.
		int listCnt = aptService.selectBasketCnt(loginVO.getUserNo());
		Pagination pagination = new Pagination(15, 10);
		pagination.pageInfo(page, range, listCnt);
		Map<String, Object> pagingMap = new HashMap<>();
		pagingMap.put("pagination", pagination);
		pagingMap.put("userNo", loginVO.getUserNo());
		
		List<BasketVO> basket = aptService.selectBasketAll(pagingMap);
		List<AptAllInfoVO> aptInfo = new ArrayList<>();
		for(BasketVO vo : basket) {
			AptBasicVO aptBasic = aptService.selectAptBasic(vo.getKaptCode());
			AptDetailVO aptDetail = aptService.selectAptDetailInOverlay(vo.getKaptCode());
			AptAllInfoVO aptAllInfo = new AptAllInfoVO();
			aptAllInfo.setAptBasicVO(aptBasic);
			aptAllInfo.setAptDetailVO(aptDetail);
			aptInfo.add(aptAllInfo);
		}
		mav.addObject("aptInfo", aptInfo);
		mav.addObject("pagination", pagination);
		return mav;
	}
	@RequestMapping("/bookMark/{kaptCode}")
	public ModelAndView basketDetail(@PathVariable("kaptCode") String kaptCode) {
		ModelAndView mav = new ModelAndView("apt/aptMap");
		Map<String, Object> map = new HashMap<>();
		AptLatLngVO aptLatLng = aptService.selectLatLng(kaptCode);
		map.put(kaptCode, aptLatLng);
		mav.addObject("basketMove", map);
		return mav;
	}
}
