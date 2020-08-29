package kr.ac.kopo.apt.vo;

public class AptBasicVO {
	private long bjdCode;
	private String kaptCode;
	private String kaptName;

	
	public long getBjdCode() {
		return bjdCode;
	}

	public void setBjdCode(long bjdCode) {
		this.bjdCode = bjdCode;
	}

	public String getKaptCode() {
		return kaptCode;
	}


	public void setKaptCode(String kaptCode) {
		this.kaptCode = kaptCode;
	}

	public String getKaptName() {
		return kaptName;
	}

	public void setKaptName(String kaptName) {
		this.kaptName = kaptName;
	}

	@Override
	public String toString() {
		return "AptBasicVO [bjdCode=" + bjdCode + ", kaptCode=" + kaptCode + ", kaptName=" + kaptName + "]";
	}
	
}
