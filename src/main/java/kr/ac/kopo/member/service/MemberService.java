package kr.ac.kopo.member.service;

import kr.ac.kopo.member.vo.MemberVO;

public interface MemberService {
	MemberVO login(MemberVO member);

	String idCheck(String id);
}
