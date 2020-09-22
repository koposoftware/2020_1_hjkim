package kr.ac.kopo.admin.vo;

public class ProductFileVO {
	private int fileNo;
	private String productCode;
	private String orgFileName;
	private String storedFileName;
	private int fileSize;
	private String regDate;
	private String delGb;
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getStoredFileName() {
		return storedFileName;
	}
	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getDelGb() {
		return delGb;
	}
	public void setDelGb(String delGb) {
		this.delGb = delGb;
	}
	@Override
	public String toString() {
		return "ProductFileVO [fileNo=" + fileNo + ", productCode=" + productCode + ", orgFileName=" + orgFileName
				+ ", storedFileName=" + storedFileName + ", fileSize=" + fileSize + ", regDate=" + regDate + ", delGb="
				+ delGb + "]";
	}
	
}
