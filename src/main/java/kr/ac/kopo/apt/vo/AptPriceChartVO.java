package kr.ac.kopo.apt.vo;

public class AptPriceChartVO {
	private String kaptCode;
	private double area;
	private int yymm;
	private int avgPrice;
	private int maxPrice;
	private int minPrice;
	
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
	public int getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	@Override
	public String toString() {
		return "AptPriceChartVO [kaptCode=" + kaptCode + ", area=" + area + ", yymm=" + yymm + ", avgPrice=" + avgPrice
				+ ", maxPrice=" + maxPrice + ", minPrice=" + minPrice + "]";
	}
	
}
