package kr.ac.kopo.apt.vo;

public class AptBjdCodeVO {
	private long bjdCode;
	private String bjdName;
	private double lat;
	private double lng;
	
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
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "AptBjdCodeVO [bjdCode=" + bjdCode + ", bjdName=" + bjdName + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
