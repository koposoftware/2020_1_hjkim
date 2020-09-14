package kr.ac.kopo.loan.vo;

import java.util.List;

/**
 * 아파트 대출한도를 계산할 때 필요한 vo
 * @author kimhyeju
 *
 */
public class LoanCalcVO {
	private String areaType;		//투기지역, 조정대상지역 등
	private String loanType; 		//1주택자, 2주택자
	private int aptPrice;		//아파트 가격
	private int aptLtv;			//ltv
	private List<LoanCalcVO> aptLtvList;
	private int loanMaxPrice;		//대출가능금액
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getAptPrice() {
		return aptPrice;
	}
	public void setAptPrice(int aptPrice) {
		this.aptPrice = aptPrice;
	}
	public int getAptLtv() {
		return aptLtv;
	}
	public void setAptLtv(int aptLtv) {
		this.aptLtv = aptLtv;
	}
	public List<LoanCalcVO> getAptLtvList() {
		return aptLtvList;
	}
	public void setAptLtvList(List<LoanCalcVO> aptLtvList) {
		this.aptLtvList = aptLtvList;
	}
	
	public int getLoanMaxPrice() {
		return loanMaxPrice;
	}
	public void setLoanMaxPrice(int loanMaxPrice) {
		this.loanMaxPrice = loanMaxPrice;
	}
	@Override
	public String toString() {
		return "LoanCalcVO [areaType=" + areaType + ", loanType=" + loanType + ", aptPrice=" + aptPrice + ", aptLtv="
				+ aptLtv + "]";
	}
	
	
}
