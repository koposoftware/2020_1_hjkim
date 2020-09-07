package kr.ac.kopo.chat.vo;

import kr.ac.kopo.common.Pagination;

public class ChatPaginationVO {
	private Pagination pagination;
	private int userNo;
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "ChatPaginationVO [pagination=" + pagination + ", userNo=" + userNo + "]";
	}
	
}
