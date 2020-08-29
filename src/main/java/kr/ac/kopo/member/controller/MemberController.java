package kr.ac.kopo.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

@SessionAttributes({ "loginVO" })
//modelAndView객체에다가 객체 등록을하게 되면 기본적으로 request영역에 등록이 된다.
//하지만, 등록하는 객체의 이름이 "loginVO"면 세션에다 등록시키라고 하는것 
@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/login")
	public String loginForm() {
		return "/member/login";
	}

	@PostMapping("/login")
	public ModelAndView login(MemberVO member, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String id = member.getId();
		if (id.startsWith("@k")) {
			System.out.println("카카오 체크");
			String idCheck = memberService.idCheck(id);
			// 회원가입을 한 사람
			if (idCheck == null) {
				mav.setViewName("redirect:/join");
				session.setAttribute("kakaoVO", member);
			} else {

				String dest = (String) session.getAttribute("dest");
				if (dest == null)
					mav.setViewName("redirect:/");
				else {
					mav.setViewName("redirect:" + dest);
					session.removeAttribute("dest");
					mav.addObject("loginVO", member);
				}
			}
		} else {
			MemberVO loginVO = memberService.login(member);
			System.out.println(loginVO);
			if (loginVO == null) {
				mav.setViewName("redirect:/login");
				mav.addObject("msg", "실패!");
			} else {
				// 로그인 성공
				String dest = (String) session.getAttribute("dest");
				if (dest == null)
					mav.setViewName("redirect:/");
				else {
					mav.setViewName("redirect:" + dest);
					session.removeAttribute("dest");
				}
				mav.addObject("loginVO", loginVO);
			}
		}
		return mav;
	}

	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
		System.out.println(status.isComplete());// false : 세션에 등록된 객체가 있다.
		status.setComplete(); // isComplete의 값이 true로 바뀐다. 세션에 등록된거 다 지워!
		return "redirect:/";
	}

	@RequestMapping("/join")
	public String join() {
		System.out.println("join들어옴");
		return "/member/join";
	}
}
