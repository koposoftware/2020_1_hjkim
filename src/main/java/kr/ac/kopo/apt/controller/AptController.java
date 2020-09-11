package kr.ac.kopo.apt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
			System.out.println("consulting");
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
}
