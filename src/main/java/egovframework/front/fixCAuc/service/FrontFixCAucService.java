package egovframework.front.fixCAuc.service;

import java.util.List;
import java.util.Map;

import egovframework.cms.cmm.service.FileVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface FrontFixCAucService {

	List<EgovMap> getRecentList(Map<String, Object> paramMap) throws Exception;

	int fixSellRegProc(Map<String, Object> paramMap) throws Exception;

	void fixSellFileProc(Map<String, Object> fileMap) throws Exception;

	EgovMap getFileOne(Map<String, Object> fileMap) throws Exception;

	void deleteFileOne(Map<String, Object> fileMap) throws Exception;

	int fixSellUptProc(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getFixList(Map<String, Object> paramMap) throws Exception;

	EgovMap getFixArticle(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getFixFileList(Map<String, Object> paramMap) throws Exception;

	int fixSellDelProc(Map<String, Object> paramMap) throws Exception;

	void fixSellFileDelProc(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getMokList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getPumList(Map<String, Object> paramMap) throws Exception;

	int getFixListCnt(Map<String, Object> paramMap) throws Exception;

	EgovMap getTime(Map<String, Object> paramMap) throws Exception;

	EgovMap getCodeToLoginInfo(String memberId) throws Exception;

	List<EgovMap> getPrePrice(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getBidList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getLevelList(Map<String, Object> paramMap) throws Exception;

	EgovMap getChulCodeToChulInfo(String chulCode) throws Exception;

	List<EgovMap> getFixBuyList(Map<String, Object> paramMap) throws Exception;

	int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception;

	EgovMap getFixBuyArticle(Map<String, Object> paramMap) throws Exception;

	EgovMap getJCodeToJInfo(String jCode) throws Exception;

	EgovMap getFloMemberCheck(Map<String, Object> paramMap) throws Exception;

	EgovMap getLimitAmt(Map<String, Object> paramMap) throws Exception;

	EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception;

	void updateBid(Map<String, Object> paramMap) throws Exception;

	void insertBid(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getFixAdmList(Map<String, Object> paramMap) throws Exception;

	int getFixAdmListCnt(Map<String, Object> paramMap) throws Exception;

	int fixAdmApply(Map<String, Object> paramMap) throws Exception;

	int fixAdmAppCancel(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getChulList(Map<String, Object> paramMap) throws Exception;

	EgovMap getCodeToLoginId(Map<String, Object> paramMap) throws Exception;

	int fixAdmRegProc(Map<String, Object> paramMap) throws Exception;

	int fixAdmUptProc(Map<String, Object> paramMap) throws Exception;

	EgovMap getFixAdmArticle(Map<String, Object> paramMap) throws Exception;

	int fixAdmBan(Map<String, Object> paramMap) throws Exception;

	int fixAdmDelProc(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getFixAucTime(Map<String, Object> paramMap) throws Exception;

	void updatetime(Map<String, Object> setParam) throws Exception;

	List<EgovMap> getJRecentList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getCRecentList(Map<String, Object> paramMap) throws Exception;

	int reqBuyRegProc(Map<String, Object> paramMap) throws Exception;

	EgovMap getReqArticle(Map<String, Object> paramMap) throws Exception;

	int reqBuyUptProc(Map<String, Object> paramMap) throws Exception;

	int fixAdmComp(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getReqList(Map<String, Object> paramMap) throws Exception;

	int getReqListCnt(Map<String, Object> paramMap) throws Exception;

	int reqBuyDelProc(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getReqAdmList(Map<String, Object> paramMap) throws Exception;

	int getReqAdmListCnt(Map<String, Object> paramMap) throws Exception;
	
	int reqAdmRegProc(Map<String, Object> paramMap) throws Exception;

	int reqAdmUptProc(Map<String, Object> paramMap) throws Exception;

	EgovMap getReqAdmArticle(Map<String, Object> paramMap) throws Exception;
	
	int reqAdmDelProc(Map<String, Object> paramMap) throws Exception;
	
	int reqAdmApply(Map<String, Object> paramMap) throws Exception;

	int reqAdmAppCancel(Map<String, Object> paramMap) throws Exception;

	int reqAdmBan(Map<String, Object> paramMap) throws Exception;
	
	int reqAdmComp(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getJList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getReqChulList(Map<String, Object> paramMap) throws Exception;

	int getReqChulListCnt(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getReqChulArticle(Map<String, Object> paramMap) throws Exception;
	
	int reqChulBan(Map<String, Object> paramMap) throws Exception;

	int reqChulComp(Map<String, Object> paramMap) throws Exception;

	int getBidTotSokCnt(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmUChal(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmPartUChal(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidTotSuc(Map<String, Object> paramMap) throws Exception;
	
    int fixAdmBidSuc(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidFail(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidPartSuc(Map<String, Object> paramMap) throws Exception;

	int fixAdmBidAdmin(Map<String, Object> paramMap) throws Exception;

	int fixAdmBidCancel(Map<String, Object> paramMap) throws Exception;

	String fixSmsProc(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getReqAdmTable(Map<String, Object> paramMap) throws Exception;

	int getReqAdmTableCnt(Map<String, Object> paramMap) throws Exception;

	int updateReaDealTable(Map<String, Object> paramMap) throws Exception;

	int updateReaDealTableComp(Map<String, Object> paramMap) throws Exception;

	int updateReaDealTableDisComp(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getReqAdmTableExcellComp(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidUpdate(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getBidCancelList(Map<String, Object> paramMap) throws Exception;

	EgovMap getMokInfo(Map<String, Object> reqParam) throws Exception;

	EgovMap getPumInfo(Map<String, Object> reqParam) throws Exception;

	EgovMap getLevelInfo(Map<String, Object> reqParam) throws Exception;

	int getfixSubChulInfoCnt(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> fixSellSubList(Map<String, Object> paramMap) throws Exception;

	int fixSellSubListCnt(Map<String, Object> paramMap) throws Exception;

	EgovMap getFixSubArticle(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getFixSubFileList(Map<String, Object> paramMap) throws Exception;

	EgovMap getfixSubChulInfo(String chulCode) throws Exception;

	int fixSellSubRegProc(Map<String, Object> paramMap) throws Exception;

	void fixSellSubFileProc(Map<String, Object> fileMap) throws Exception;

	int fixSellSubUptProc(Map<String, Object> paramMap) throws Exception;

	void deleteSubFileOne(Map<String, Object> paramMap) throws Exception;

	EgovMap getSubFileOne(Map<String, Object> fileMap) throws Exception;

	int fixSellSubDelProc(Map<String, Object> paramMap) throws Exception;

	void fixSellSubFileDelProc(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> fixBuySubList(Map<String, Object> paramMap) throws Exception;

	int fixBuySubListCnt(Map<String, Object> paramMap) throws Exception;

	EgovMap getFixBuySubArticle(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getfixSubChulList(Map<String, Object> paramMap) throws Exception;

	int fixAdmSubChulRegProc(Map<String, Object> paramMap) throws Exception;

	int fixAdmSubChulDelProc(Map<String, Object> paramMap) throws Exception;

	int reqBuySubRegProc(Map<String, Object> paramMap) throws Exception;

	String reqSmsProc(Map<String, Object> paramMap) throws Exception;
	
	int messageBiteLength(Map<String, Object> paramMap) throws Exception;

	int sendSms(Map<String, Object> paramMap) throws Exception;

	int sendMms(Map<String, Object> paramMap) throws Exception;
	
	/*정산관리*/
	String selectMaxChulDate(Map<String, Object> paramMap) throws Exception;

	EgovMap getTranAdmInfo(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getTranAdmList(Map<String, Object> paramMap) throws Exception;

	int getTranAdmListCnt(Map<String, Object> paramMap) throws Exception;

	int tranMagamInsProc(Map<String, Object> paramMap) throws Exception;

	void tranMagamDelProc(Map<String, Object> paramMap) throws Exception;

	EgovMap tranMagamCompCheck(Map<String, Object> paramMap) throws Exception;
	
	int tranMagamComp(Map<String, Object> paramMap) throws Exception;

	int tranMagamTranCnt(Map<String, Object> paramMap) throws Exception;

	List<Map<String,Object>> getTranCompListSangJang(Map<String, Object> paramMap) throws Exception;

	List<Map<String,Object>> getTranCompListPanMae(Map<String, Object> paramMap) throws Exception;
	
	List<Map<String,Object>> getTranListMagam(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getFixAucCompChulList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getFixAucCompDomeList(Map<String, Object> paramMap) throws Exception;

	int updateFixAucCompChulBigo(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getAdmJRecentList(Map<String, Object> paramMap) throws Exception;

	EgovMap getFixAdmSubArticle(Map<String, Object> paramMap) throws Exception;

	int fixAdmSubUptProc(Map<String, Object> paramMap) throws Exception;

	int fixAdmSubDelProc(Map<String, Object> paramMap) throws Exception;
	
	
	int updateMagamTrans(Map<String, Object> paramMap) throws Exception;
	
	int updateTransReq(Map<String, Object> paramMap) throws Exception;
	int updateTransFix(Map<String, Object> paramMap) throws Exception;
}
