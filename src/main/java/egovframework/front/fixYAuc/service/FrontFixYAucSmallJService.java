package egovframework.front.fixYAuc.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface FrontFixYAucSmallJService {
	
	EgovMap getTime(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getFixBuyList(Map<String, Object> paramMap) throws Exception;
	
	int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getFixBuyArticle(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getFixFileList(Map<String, Object> paramMap) throws Exception;
	
	void updateBid(Map<String, Object> paramMap) throws Exception;

	void insertBid(Map<String, Object> paramMap) throws Exception;
	
	List<Map<String,Object>> getFixSmallSangJangList(Map<String, Object> paramMap) throws Exception;
	
	List<Map<String,Object>> getFixSmallBidList(Map<String, Object> paramMap) throws Exception;
}
