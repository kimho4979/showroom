package egovframework.front.fixNAuc.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("frontFixNAucSmallJMapper")
public interface FrontFixNAucSmallJMapper {

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
