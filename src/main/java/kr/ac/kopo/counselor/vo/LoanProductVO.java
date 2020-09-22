package kr.ac.kopo.counselor.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class LoanProductVO {
	private String productCode;
	@Length(min = 2, message = "2글자 이상 입력하셔야 합니다.")
	@NotEmpty(message = "필수항목입니다.")
	private String productName;
	@NotEmpty(message = "필수항목입니다.")
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
