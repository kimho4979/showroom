package egovframework.cms.market.vo;

public class MarketReviewVO {

	private int reviewSeq;
	
	private int marketSeq;
	
	private String content;
	
	private String userId;
	
	private String stPoint;
	
	private String nickName;
	
	private String insertId;
	
	private String insertDate;
	
	private String updateId;
	
	private String updateDate;
	
	private int fileCnt;
	
	private int totCnt;
	
	private double stAvg;

	public int getReviewSeq() {
		return reviewSeq;
	}

	public void setReviewSeq(int reviewSeq) {
		this.reviewSeq = reviewSeq;
	}

	public int getMarketSeq() {
		return marketSeq;
	}

	public void setMarketSeq(int marketSeq) {
		this.marketSeq = marketSeq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStPoint() {
		return stPoint;
	}

	public void setStPoint(String stPoint) {
		this.stPoint = stPoint;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public double getStAvg() {
		return stAvg;
	}

	public void setStAvg(double stAvg) {
		this.stAvg = stAvg;
	}
}
