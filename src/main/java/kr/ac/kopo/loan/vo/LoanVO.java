package kr.ac.kopo.loan.vo;

public class LoanVO {

	private String kaptCode;
	private String aptName;
	private double aptArea;
	private int aptFloor;
	private int bang;
	public String getKaptCode() {
		return kaptCode;
	}
	public void setKaptCode(String kaptCode) {
		this.kaptCode = kaptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public double getAptArea() {
		return aptArea;
	}
	public void setAptArea(double aptArea) {
		this.aptArea = aptArea;
	}
	public int getAptFloor() {
		return aptFloor;
	}
	public void setAptFloor(int aptFloor) {
		this.aptFloor = aptFloor;
	}
	public int getBang() {
		return bang;
	}
	public void setBang(int bang) {
		this.bang = bang;
	}
	@Override
	public String toString() {
		return "LoanVO [kaptCode=" + kaptCode + ", aptName=" + aptName + ", aptArea=" + aptArea + ", aptFloor="
				+ aptFloor + ", bang=" + bang + "]";
	}
	
	
}
