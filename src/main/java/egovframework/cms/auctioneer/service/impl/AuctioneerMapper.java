package egovframework.cms.auctioneer.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("auctioneerMapper")
public interface AuctioneerMapper {
	
	List<EgovMap> getAuctioneerList(Map<String, Object> paramMap) throws Exception;
	
	int getAuctioneerListCnt(Map<String, Object> paramMap) throws Exception;
	
	int idValidCheck(String id) throws Exception;
	
	int insert(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getAuctioneer(Long memberId) throws Exception;
	
	int update(Map<String, Object> paramMap) throws Exception;
	
	int delete(Long memberId) throws Exception;
	
	EgovMap getAucMember(Map<String, Object> paramMap) throws Exception;

}
