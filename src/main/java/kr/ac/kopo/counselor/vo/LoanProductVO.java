package kr.ac.kopo.counselor.vo;

public class LoanProductVO {
	private String productCode;
	private String productName;
	private String productContent;
	private String productNeedDoc;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getProductNeedDoc() {
		return productNeedDoc;
	}
	public void setProductNeedDoc(String productNeedDoc) {
		this.productNeedDoc = productNeedDoc;
	}
	@Override
	public String toString() {
		return "LoanProductVO [productCode=" + productCode + ", productName=" + productName + ", productContent="
				+ productContent + ", productNeedDoc=" + productNeedDoc + "]";
	}
	
}
