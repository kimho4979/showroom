package egovframework.cms.market.vo;

public class MarketFile {

	//파일번호
	private int fileSeq;
	
	//점포 번호
	private int marketSeq;
	
	//저장파일 경로
	private String streFilePath;
	
	//저장파일 이름
	private String streFileNm;
	
	//원본파일 이름
	private String orignlFileNm;
	
	//썸네일 저장파일 이름
	private String thumbStreFileNm;
	
	//파일 확장자
	private String fileExtsn;
	
	//파일 내용
	private String fileCn;
	
	//파일 크기
	private String fileSize;
	
	//등록자 아이디
	private String insertId;
	
	//등록일자
	private String insertDate;
	
	//수정자 아이디
	private String updateId;
	
	//수정 일자
	private String updateDate;

	public int getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}

	public int getMarketSeq() {
		return marketSeq;
	}

	public void setMarketSeq(int marketSeq) {
		this.marketSeq = marketSeq;
	}

	public String getStreFilePath() {
		return streFilePath;
	}

	public void setStreFilePath(String streFilePath) {
		this.streFilePath = streFilePath;
	}

	public String getStreFileNm() {
		return streFileNm;
	}

	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}

	public String getOrignlFileNm() {
		return orignlFileNm;
	}

	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	public String getThumbStreFileNm() {
		return thumbStreFileNm;
	}

	public void setThumbStreFileNm(String thumbStreFileNm) {
		this.thumbStreFileNm = thumbStreFileNm;
	}

	public String getFileExtsn() {
		return fileExtsn;
	}

	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	public String getFileCn() {
		return fileCn;
	}

	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
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
		return "MarketFile [fileSeq=" + fileSeq + ", marketSeq=" + marketSeq
				+ ", streFilePath=" + streFilePath + ", streFileNm="
				+ streFileNm + ", orignlFileNm=" + orignlFileNm
				+ ", thumbStreFileNm=" + thumbStreFileNm + ", fileExtsn="
				+ fileExtsn + ", fileCn=" + fileCn + ", fileSize=" + fileSize
				+ ", insertId=" + insertId + ", insertDate=" + insertDate
				+ ", updateId=" + updateId + ", updateDate=" + updateDate + "]";
	}
}
