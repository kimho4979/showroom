package egovframework.front.market.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("frontMarketListMapper")
public interface FrontMarketListMapper {
	
	public EgovMap getMarketTypeGroupInfo(Map<String, Object> paramMap) throws Exception;

	public EgovMap getOraMarketInfo(Map<String, Object> paramMap) throws Exception;
	
	public List<EgovMap> getOraMarketList(Map<String, Object> paramMap) throws Exception;
}
