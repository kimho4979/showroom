package egovframework.front.market.vo;

public class MarketInfo {
	//점포 번호
	private int marketSeq;
	
	//매장구분
	private String marketType;
	
	//매장구분코드
	private String tyGroupCode;
	
	//매장호수
	private String marketNo;
	
	//상호명
	private String marketName;
	
	//대표자
	private String owner;
	
	//전화번호
	private String tel;
	
	//취급품목
	private String items;
	
	//영업시간
	private String saleTime;
	
	//휴무
	private String cloDay;
	
	//택배 
	private String deliText;
	
	//해시태그
	private String hashText;
	
	//업체 소개
	private String content;
	
	//중도매인코드
	private String jCode;
	
	//배치도 영역 좌표
	private String areaCoords;
	
	//사용 여부
	private String useYn;
	
	//등록자 아이디
	private String insertId;
	
	//등록일자
	private String insertDate;
	
	//수정자 아이디
	private String updateId;
	
	//수정 일자
	private String updateDate;

	public int getMarketSeq() {
		return marketSeq;
	}

	public void setMarketSeq(int marketSeq) {
		this.marketSeq = marketSeq;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public String getTyGroupCode() {
		return tyGroupCode;
	}

	public void setTyGroupCode(String tyGroupCode) {
		this.tyGroupCode = tyGroupCode;
	}

	public String getMarketNo() {
		return marketNo;
	}

	public void setMarketNo(String marketNo) {
		this.marketNo = marketNo;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}

	public String getCloDay() {
		return cloDay;
	}

	public void setCloDay(String cloDay) {
		this.cloDay = cloDay;
	}

	public String getDeliText() {
		return deliText;
	}

	public void setDeliText(String deliText) {
		this.deliText = deliText;
	}

	public String getHashText() {
		return hashText;
	}

	public void setHashText(String hashText) {
		this.hashText = hashText;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getjCode() {
		return jCode;
	}

	public void setjCode(String jCode) {
		this.jCode = jCode;
	}

	public String getAreaCoords() {
		return areaCoords;
	}

	public void setAreaCoords(String areaCoords) {
		this.areaCoords = areaCoords;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getInsertId() {
		return insertId;
	}

	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "MarketInfo [marketSeq=" + marketSeq + ", marketType="
				+ marketType + ", marketNo=" + marketNo + ", marketName="
				+ marketName + ", owner=" + owner + ", tel=" + tel + ", items="
				+ items + ", content=" + content + ", jCode=" + jCode
				+ ", useYn=" + useYn + ", insertId=" + insertId
				+ ", insertDate=" + insertDate + ", updateId=" + updateId
				+ ", updateDate=" + updateDate + "]";
	}
	
	
}
