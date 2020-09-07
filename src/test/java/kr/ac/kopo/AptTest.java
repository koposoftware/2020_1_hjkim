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

import kr.ac.kopo.apt.dao.AptDAO;
import kr.ac.kopo.apt.service.AptService;
import kr.ac.kopo.apt.vo.AptBasicVO;
import kr.ac.kopo.apt.vo.AptLatLngVO;
import kr.ac.kopo.chat.vo.ChatListUserNameVO;
import kr.ac.kopo.common.Pagination;

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
}
