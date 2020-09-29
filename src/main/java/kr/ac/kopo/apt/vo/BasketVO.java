package kr.ac.kopo.apt.vo;

public class BasketVO {
	private int basketNo;
	private String kaptCode;
	private int userNo;
	public int getBasketNo() {
		return basketNo;
	}
	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}
	public String getKaptCode() {
		return kaptCode;
	}
	public void setKaptCode(String kaptCode) {
		this.kaptCode = kaptCode;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "BasketVO [basketNo=" + basketNo + ", kaptCode=" + kaptCode + ", userNo=" + userNo + "]";
	}
	
	
}
