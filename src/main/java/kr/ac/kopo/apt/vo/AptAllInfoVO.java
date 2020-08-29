package kr.ac.kopo.apt.vo;

public class AptAllInfoVO {
	private AptBasicVO aptBasicVO;
	private AptDetailVO aptDetailVO;
	
	
	public AptBasicVO getAptBasicVO() {
		return aptBasicVO;
	}
	public void setAptBasicVO(AptBasicVO aptBasicVO) {
		this.aptBasicVO = aptBasicVO;
	}
	public AptDetailVO getAptDetailVO() {
		return aptDetailVO;
	}
	public void setAptDetailVO(AptDetailVO aptDetailVO) {
		this.aptDetailVO = aptDetailVO;
	}
	@Override
	public String toString() {
		return "AptAllInfoVO [aptBasicVO=" + aptBasicVO.toString() + ", aptDetailVO=" + aptDetailVO.toString()  + "]";
	}
	
	
}
