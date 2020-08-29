package kr.ac.kopo.apt.vo;

public class AptDetailVO {
	private String katpCode; 		// 아파트 코드
	private String kaptAddr;		// 아파트 법정동주소
	private String codeSaleNm;		// 분양형태
	private String codeHeatNm;		// 난방방식
	private String codeHallNm;		// 복도유형
	private double kapttarea;		// 건축물대장상 연면적
	private double kaptDongCnt;		// 동수
	private double kaptDaCnt;		// 세대수
	private String kaptBCompany;	// 시공사
	private String kaptACompany;	// 시행사
	private String kaptTel;			// 관리사무소연락처
	private String kaptFax;			// 관리사무소 팩스
	private String katpUrl;			// 홈페이지주소
	private String codeAptNm;		// 단지분류
	private String doroJuso;		// 도로명주소
	private long hocnt;				// 호수
	private String codeMgrNm;		// 관리방식
	private String kaptUseDate;		// 사용승인일
	private double kaptMArea;		// 관리비부과면적
	private long kaptMPArea60;		// 전용면적별 세대현황 60㎡ 이하
	private long kaptMPArea85;		// 전용면적별 세대현황 60㎡ ~ 85㎡ 이하
	private long kaptMPArea135;		// 전용면적별 세대현황 85㎡ ~ 135㎡ 이하
	private long kaptMPArea136;		// 전용면적별 세대현황 135㎡ 초과
	private double privArea;		// 단지 전용면적합(㎡)
	public String getKatpCode() {
		return katpCode;
	}
	public void setKatpCode(String katpCode) {
		this.katpCode = katpCode;
	}
	public String getKaptAddr() {
		return kaptAddr;
	}
	public void setKaptAddr(String kaptAddr) {
		this.kaptAddr = kaptAddr;
	}
	public String getCodeSaleNm() {
		return codeSaleNm;
	}
	public void setCodeSaleNm(String codeSaleNm) {
		this.codeSaleNm = codeSaleNm;
	}
	public String getCodeHeatNm() {
		return codeHeatNm;
	}
	public void setCodeHeatNm(String codeHeatNm) {
		this.codeHeatNm = codeHeatNm;
	}
	public String getCodeHallNm() {
		return codeHallNm;
	}
	public void setCodeHallNm(String codeHallNm) {
		this.codeHallNm = codeHallNm;
	}
	public double getKapttarea() {
		return kapttarea;
	}
	public void setKapttarea(double kapttarea) {
		this.kapttarea = kapttarea;
	}
	public double getKaptDongCnt() {
		return kaptDongCnt;
	}
	public void setKaptDongCnt(double kaptDongCnt) {
		this.kaptDongCnt = kaptDongCnt;
	}
	public double getKaptDaCnt() {
		return kaptDaCnt;
	}
	public void setKaptDaCnt(double kaptDaCnt) {
		this.kaptDaCnt = kaptDaCnt;
	}
	public String getKaptBCompany() {
		return kaptBCompany;
	}
	public void setKaptBCompany(String kaptBCompany) {
		this.kaptBCompany = kaptBCompany;
	}
	public String getKaptACompany() {
		return kaptACompany;
	}
	public void setKaptACompany(String kaptACompany) {
		this.kaptACompany = kaptACompany;
	}
	public String getKaptTel() {
		return kaptTel;
	}
	public void setKaptTel(String kaptTel) {
		this.kaptTel = kaptTel;
	}
	public String getKaptFax() {
		return kaptFax;
	}
	public void setKaptFax(String kaptFax) {
		this.kaptFax = kaptFax;
	}
	public String getKatpUrl() {
		return katpUrl;
	}
	public void setKatpUrl(String katpUrl) {
		this.katpUrl = katpUrl;
	}
	public String getCodeAptNm() {
		return codeAptNm;
	}
	public void setCodeAptNm(String codeAptNm) {
		this.codeAptNm = codeAptNm;
	}
	public String getDoroJuso() {
		return doroJuso;
	}
	public void setDoroJuso(String doroJuso) {
		this.doroJuso = doroJuso;
	}
	public long getHocnt() {
		return hocnt;
	}
	public void setHocnt(long hocnt) {
		this.hocnt = hocnt;
	}
	public String getCodeMgrNm() {
		return codeMgrNm;
	}
	public void setCodeMgrNm(String codeMgrNm) {
		this.codeMgrNm = codeMgrNm;
	}
	public String getKaptUseDate() {
		return kaptUseDate;
	}
	public void setKaptUseDate(String kaptUseDate) {
		this.kaptUseDate = kaptUseDate;
	}
	public double getKaptMArea() {
		return kaptMArea;
	}
	public void setKaptMArea(double kaptMArea) {
		this.kaptMArea = kaptMArea;
	}
	public long getKaptMPArea60() {
		return kaptMPArea60;
	}
	public void setKaptMPArea60(long kaptMPArea60) {
		this.kaptMPArea60 = kaptMPArea60;
	}
	public long getKaptMPArea85() {
		return kaptMPArea85;
	}
	public void setKaptMPArea85(long kaptMPArea85) {
		this.kaptMPArea85 = kaptMPArea85;
	}
	public long getKaptMPArea135() {
		return kaptMPArea135;
	}
	public void setKaptMPArea135(long kaptMPArea135) {
		this.kaptMPArea135 = kaptMPArea135;
	}
	public long getKaptMPArea136() {
		return kaptMPArea136;
	}
	public void setKaptMPArea136(long kaptMPArea136) {
		this.kaptMPArea136 = kaptMPArea136;
	}
	public double getPrivArea() {
		return privArea;
	}
	public void setPrivArea(double privArea) {
		this.privArea = privArea;
	}
	@Override
	public String toString() {
		return "AptDetailVO [katpCode=" + katpCode + ", kaptAddr=" + kaptAddr + ", codeSaleNm=" + codeSaleNm
				+ ", codeHeatNm=" + codeHeatNm + ", codeHallNm=" + codeHallNm + ", kapttarea=" + kapttarea
				+ ", kaptDongCnt=" + kaptDongCnt + ", kaptDaCnt=" + kaptDaCnt + ", kaptBCompany=" + kaptBCompany
				+ ", kaptACompany=" + kaptACompany + ", kaptTel=" + kaptTel + ", kaptFax=" + kaptFax + ", katpUrl="
				+ katpUrl + ", codeAptNm=" + codeAptNm + ", doroJuso=" + doroJuso + ", hocnt=" + hocnt + ", codeMgrNm="
				+ codeMgrNm + ", kaptUseDate=" + kaptUseDate + ", kaptMArea=" + kaptMArea + ", kaptMPArea60="
				+ kaptMPArea60 + ", kaptMPArea85=" + kaptMPArea85 + ", kaptMPArea135=" + kaptMPArea135
				+ ", kaptMPArea136=" + kaptMPArea136 + ", privArea=" + privArea + "]";
	}
	
}
