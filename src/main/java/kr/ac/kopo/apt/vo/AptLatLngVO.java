package kr.ac.kopo.apt.vo;

public class AptLatLngVO {
	String kaptCode;	//아파트 코드
	double lat;			//위도
	double lng;			//경도
	
	public String getKaptCode() {
		return kaptCode;
	}
	public void setKaptCode(String kaptCode) {
		this.kaptCode = kaptCode;
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
		return "AptLatLngVO [kaptCode=" + kaptCode + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
