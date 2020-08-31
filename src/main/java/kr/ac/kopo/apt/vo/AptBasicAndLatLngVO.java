package kr.ac.kopo.apt.vo;

public class AptBasicAndLatLngVO {
	private AptBasicVO aptBasicVO;
	private AptLatLngVO aptLatLngVO;
	
	public AptBasicVO getAptBasicVO() {
		return aptBasicVO;
	}
	public void setAptBasicVO(AptBasicVO aptBasicVO) {
		this.aptBasicVO = aptBasicVO;
	}
	public AptLatLngVO getAptLatLngVO() {
		return aptLatLngVO;
	}
	public void setAptLatLngVO(AptLatLngVO aptLatLngVO) {
		this.aptLatLngVO = aptLatLngVO;
	}
	@Override
	public String toString() {
		return "AptBaiscAndLatLngVO [aptBasicVO=" + aptBasicVO + ", aptLatLngVO=" + aptLatLngVO + "]";
	}
	
}
