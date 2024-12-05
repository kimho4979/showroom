package egovframework.front.fixNAuc.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface FrontFixNAucSmallService {

	List<EgovMap> getFixAdmList(Map<String, Object> paramMap) throws Exception;
	
	int getFixAdmListCnt(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getTime(Map<String, Object> paramMap) throws Exception;
	
	int fixSmallDealRegProc(Map<String, Object> paramMap) throws Exception;
	
	int fixSmallDealRegProcDel(Map<String, Object> paramMap) throws Exception;
	
	void fixSellFileDelProc(Map<String, Object> paramMap) throws Exception;
	
	int getFixSmallDealCount(Map<String, Object> paramMap) throws Exception;
	
	int getFixFileCount(Map<String, Object> paramMap) throws Exception;
	
	void deleteFileOne(Map<String, Object> fileMap) throws Exception;

	EgovMap getFileOne(Map<String, Object> fileMap) throws Exception;
	
	List<EgovMap> getFixSmallDealSeq(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getFixBuyList(Map<String, Object> paramMap) throws Exception;
	int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getFixBuyArticle(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getBidList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getBidCancelList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getFixAdmArticle(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmApply(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmAppCancel(Map<String, Object> paramMap) throws Exception;
	
	int getBidTotSokCnt(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmUChal(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmPartUChal(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmComp(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidTotSuc(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidSuc(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidPartSuc(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidFail(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBan(Map<String, Object> paramMap) throws Exception;
	
	void fixSellFileProc(Map<String, Object> fileMap) throws Exception;
	
	List<EgovMap> getFixFileList(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidUpdate(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidCancel(Map<String, Object> paramMap) throws Exception;
	
	int fixAdmBidAdmin(Map<String, Object> paramMap) throws Exception;
}
