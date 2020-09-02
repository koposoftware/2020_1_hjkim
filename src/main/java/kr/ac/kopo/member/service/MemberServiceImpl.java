package kr.ac.kopo.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO login(MemberVO member) {
		return memberDAO.login(member);
	}

	@Override
	public String idCheck(String id) {
		String idCheck = memberDAO.idCheck(id);
		return idCheck;
	}

	@Override
	public void insertCounselor(int userNo) {
		memberDAO.insertCounselor(userNo);
	}

	@Override
	public void deleteCounselor(int userNo) {
		memberDAO.deleteCounselor(userNo);
	}
	
}
