package kr.ac.kopo.admin.vo;

public class LawAreaVO {
	private String lawBjdCode;
	private String lawDivision;
	public String getLawBjdCode() {
		return lawBjdCode;
	}
	public void setLawBjdCode(String lawBjdCode) {
		this.lawBjdCode = lawBjdCode;
	}
	public String getLawDivision() {
		return lawDivision;
	}
	public void setLawDivision(String lawDivision) {
		this.lawDivision = lawDivision;
	}
	@Override
	public String toString() {
		return "LawAreaVO [lawBjdCode=" + lawBjdCode + ", lawDivision=" + lawDivision + "]";
	}
}
