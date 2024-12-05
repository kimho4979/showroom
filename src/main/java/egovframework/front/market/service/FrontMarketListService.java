package egovframework.front.market.service;

import java.util.List;
import java.util.Map;

import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface FrontMarketListService {

	public EgovMap getMarketTypeGroupInfo(Map<String, Object> paramMap) throws Exception;

	public List<EgovMap> getMarketAtList(Map<String, Object> paramMap) throws Exception;

	public EgovMap getOraMarketInfo(Map<String, Object> paramMap) throws Exception;

	public EgovMap getAtMarketInfo(Map<String, Object> paramMap) throws Exception;
	
	public List<EgovMap> getOraMarketList(Map<String, Object> paramMap) throws Exception;
	
}
