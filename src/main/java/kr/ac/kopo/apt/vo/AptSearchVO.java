package kr.ac.kopo.apt.vo;

public class AptSearchVO {
	private String code;
	private double lat;
	private double lng;
	private String type;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AptSearchVO [code=" + code + ", lat=" + lat + ", lng=" + lng + ", type=" + type + "]";
	}
	
}
