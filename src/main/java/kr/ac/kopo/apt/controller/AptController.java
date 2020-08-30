package kr.ac.kopo.apt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.apt.service.AptService;
import kr.ac.kopo.apt.vo.AptAllInfoVO;
import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptDetailVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;

@RequestMapping("/apt")
@Controller
public class AptController {
	
	@Autowired
	private AptService aptService;
	
	@RequestMapping("/aptMap")
	public String aptMap() {
		return "/apt/aptMap";
	}
	
	@RequestMapping("/{kaptCode}")
	public ModelAndView detail(@PathVariable("kaptCode") String kaptCode) {
		AptBasicVO aptBasic = aptService.selectAptBasic(kaptCode);
		AptDetailVO aptDetail = aptService.selectAptDetail(kaptCode);
		
		AptAllInfoVO aptAllInfo = new AptAllInfoVO();
		aptAllInfo.setAptBasicVO(aptBasic);
		aptAllInfo.setAptDetailVO(aptDetail);
		
		ModelAndView mav = new ModelAndView();
		/* mav.setViewName("/apt/aptMap"); */
		mav.setViewName("/apt/aptDetailInfo");
		mav.addObject("detailVO", aptAllInfo);
		return mav;
	}
	
	@RequestMapping("/aptLatLng.json")
	@ResponseBody
	public Map<String, List<AptLatLngVO>> resJsonBody(@RequestParam("da")double da, @RequestParam("ia") double ia, @RequestParam("ka") double ka, @RequestParam("ja") double ja){
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
		List<AptLatLngVO> result = aptService.selectLatLng(bounds);//위경도 리스트 가져오기
		aptLatLng.put("positions",result);
		return aptLatLng;
	}
	
	@RequestMapping("/aptBasic/{aptNo}")
	@ResponseBody
	public AptAllInfoVO aptBasic(@PathVariable("aptNo") String aptNo){
		AptBasicVO aptBasic = aptService.selectAptBasic(aptNo);
		AptDetailVO aptDetail = aptService.selectAptDetailInOverlay(aptNo);
		AptAllInfoVO aptOverlay = new AptAllInfoVO();
		aptOverlay.setAptBasicVO(aptBasic);
		aptOverlay.setAptDetailVO(aptDetail);
		
		return aptOverlay;
	}
}
