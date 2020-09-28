package kr.ac.kopo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.ac.kopo.admin.vo.BjdCodeVO;
import kr.ac.kopo.admin.vo.ProductFileVO;
import kr.ac.kopo.apt.dao.AptDAO;
import kr.ac.kopo.apt.service.AptService;
import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;
import kr.ac.kopo.apt.vo.AptPriceChartVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.common.Pagination;
import kr.ac.kopo.counselor.vo.ChatAutoVO;
import kr.ac.kopo.loan.dao.LoanDAO;
import kr.ac.kopo.loan.vo.LoanVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring/spring-mvc.xml"})
public class AptTest {
	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private AptDAO aptDAO;
	@Autowired
	private LoanDAO loanDAO;
	@Autowired
	private AptService aptService;
	
	@Ignore
	@Test
	public void 위경도조회테스트() throws Exception {
		AptLatLngVO minLatLng = new AptLatLngVO();
		AptLatLngVO maxLatLng = new AptLatLngVO();
		
		minLatLng.setLat(37.53602394803729);
		minLatLng.setLng(126.56807209510013);

		maxLatLng.setLat(37.5834726784227);
		maxLatLng.setLng(126.68915517364401);
		
		List<AptLatLngVO> bounds = new ArrayList<>();
		
		bounds.add(minLatLng);
		bounds.add(maxLatLng);
		
		List<AptLatLngVO> aptLatLng = aptDAO.selectLatLng(bounds);
		for(AptLatLngVO vo : aptLatLng) {
			System.out.println(vo);
		}
	}
	
	@Ignore
	@Test
	public void 아파트기본정보테스트() throws Exception{
		AptBasicVO aptLatLng = session.selectOne("apt.dao.AptDAO.selectBasicInfo","A13286109");
		/* AptBasicVO aptLatLng = aptDAO.selectAptBasic("A13286109"); */
		System.out.println(aptLatLng);
		
	}
	
	@Ignore
	@Test
	public void 채팅내역테스트() throws Exception{
		Pagination pagination = new Pagination();
		pagination.pageInfo(1, 1, 10);
		Map<String, Object> pagingMap = new HashMap<>();
		pagingMap.put("pagination", pagination);
		pagingMap.put("userNo", 2);
		List<ChatListUserNameVO> list = session.selectList("consulting.dao.consultingDAO.selectChatListPaging",pagingMap);
		for(ChatListUserNameVO test : list) {
			System.out.println(test.toString());
		}
	}
	@Ignore
	@Test
	public void 아파트면적테스트() throws Exception{
		List<AptPriceChartVO> aptPriceChart = session.selectList("apt.dao.AptDAO.selectAptPriceChartArea", "A14272304");
		Map<String,List<AptPriceChartVO>> areaMap = new HashMap<String, List<AptPriceChartVO>>();
		for(AptPriceChartVO vo : aptPriceChart) {
			System.out.println("면적 : " + vo.getArea());
			List<AptPriceChartVO> aptPriceToArea = session.selectList("apt.dao.AptDAO.selectAptPriceChartAreaYYMM", vo);
			areaMap.put(Double.toString(vo.getArea()),aptPriceToArea);
			
		}
	}
	
	@Ignore
	@Test
	public void 법정동코드테스트() throws Exception{
		List<BjdCodeVO> bjdCodeList = session.selectList("admin.dao.AdminDAO.sigungu", "11");
		for(BjdCodeVO test : bjdCodeList) {
			System.out.println(test.toString());
		}
	}
	@Ignore
	@Test
	public void 아파트이름테스트() throws Exception{
		String aptName = session.selectOne("loan.dao.LoanDAO.selectAptName","A13084802");
		System.out.println(aptName);
	}
	@Ignore
	@Test
	public void 아파트면적테스트1() throws Exception{
		String aptName = session.selectOne("loan.dao.LoanDAO.selectAptName","A13084802");
		List<Double> aptArea = session.selectList("loan.dao.LoanDAO.selectAptArea","A13084802");
		for(Double vo : aptArea) {
			System.out.println(vo);
		}
	}
	@Ignore
	@Test
	public void 가격불러오기() throws Exception{
		LoanVO loan = new LoanVO();
		String floor = "2";
		String area = "84.5496";
		String aptCode = "A40471902";
		loan.setAptArea(Double.parseDouble(area));
		loan.setAptFloor(Integer.parseInt(floor));
		loan.setKaptCode(aptCode);
		int aptPrice = loanDAO.selectAptPrice(loan);
		System.out.println(aptPrice);
//		int aptName = session.selectOne("loan.dao.LoanDAO.selectAptPrice",loan);
//		System.out.println(aptName);
	}
	
	@Test
	public void 어드민자동문구() throws Exception{
		List<ChatAutoVO> list = session.selectList("counselor.dao.counselorDAO.selectAutoWord",0);
		for(ChatAutoVO vo : list) {
			System.out.println(vo);
		}
	}
	@Ignore
	@Test
	public void pdf테스트() throws Exception{
		ProductFileVO vo = session.selectOne("consulting.dao.consultingDAO.selectFile", 1);
		System.out.println(vo);
	}
}
