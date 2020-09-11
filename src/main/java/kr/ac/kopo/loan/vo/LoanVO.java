package kr.ac.kopo.loan.vo;

public class LoanVO {
	private long bjdCode;
	private String bjdName;
	public long getBjdCode() {
		return bjdCode;
	}
	public void setBjdCode(long bjdCode) {
		this.bjdCode = bjdCode;
	}
	public String getBjdName() {
		return bjdName;
	}
	public void setBjdName(String bjdName) {
		this.bjdName = bjdName;
	}
	@Override
	public String toString() {
		return "BjdCodeVO [bjdCode=" + bjdCode + ", bjdName=" + bjdName + "]";
	}
	
}
