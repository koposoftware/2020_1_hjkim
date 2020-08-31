package kr.ac.kopo.apt.vo;

public class AptPriceVO {

	private String kaptCode;
	private double area;
	private int yymm;
	private int dd;
	private String price;
	private int floor;
	public String getKaptCode() {
		return kaptCode;
	}
	public void setKaptCode(String kaptCode) {
		this.kaptCode = kaptCode;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public int getYymm() {
		return yymm;
	}
	public void setYymm(int yymm) {
		this.yymm = yymm;
	}
	public int getDd() {
		return dd;
	}
	public void setDd(int dd) {
		this.dd = dd;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "AptPriceVO [kaptCode=" + kaptCode + ", area=" + area + ", yymm=" + yymm + ", dd=" + dd + ", price="
				+ price + ", floor=" + floor + "]";
	}
	
}
