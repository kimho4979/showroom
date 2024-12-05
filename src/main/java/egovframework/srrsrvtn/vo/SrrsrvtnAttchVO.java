package egovframework.srrsrvtn.vo;

public class SrrsrvtnAttchVO {
	
	private int seq;
	private String userId;
	private String fileNm;
	private String filePath;
	private String gbn;
	private double fileSize;
	private String orgnNm;
	private String fileType;
	private String fileSeq;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getGbn() {
		return gbn;
	}
	public void setGbn(String gbn) {
		this.gbn = gbn;
	}
	public double getFileSize() {
		return fileSize;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	public String getOrgnNm() {
		return orgnNm;
	}
	public void setOrgnNm(String orgnNm) {
		this.orgnNm = orgnNm;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}
	@Override
	public String toString() {
		return "SrrsrvtnAttchVO [seq=" + seq + ", userId=" + userId
				+ ", fileNm=" + fileNm + ", filePath=" + filePath + ", gbn="
				+ gbn + ", fileSize=" + fileSize + ", orgnNm=" + orgnNm
				+ ", fileType=" + fileType + ", fileSeq=" + fileSeq + "]";
	}
}
