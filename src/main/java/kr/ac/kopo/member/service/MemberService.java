package kr.ac.kopo.member.service;

import kr.ac.kopo.member.vo.MemberVO;

public interface MemberService {
	MemberVO login(MemberVO member);

	String idCheck(String id);

	void insertCounselor(int userNo);
	
	void deleteCounselor(int userNo);
}
