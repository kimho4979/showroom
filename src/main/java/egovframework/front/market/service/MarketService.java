package egovframework.front.market.service;

import java.util.List;
import java.util.Map;

import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface MarketService {

	public int getMarketInfoListCnt(MarketInfoVO paramMarketInfoVO)
		throws Exception;
	
	public Map<String, Object> getMarketInfoList (MarketInfoVO paramMarketInfoVO)
		throws Exception;
	
	public MarketInfoVO getMarketInfo(MarketInfoVO paramMarketInfoVO)
		throws Exception;
	
	public void regMarketReview(Map<String, Object> paramMap) 
		throws Exception;
	
	public List<MarketReviewVO> getMarketReviewList(MarketReviewVO paramMarketReviewVO)
		throws Exception;
	
	public EgovMap getMarketReviewListCnt(MarketReviewVO paramMarketReviewVO)
		throws Exception;
	
	EgovMap selectMarketInfo(Map<String, Object> paramMap) throws Exception;

	public List<EgovMap> getMarketFileList(Map<String, Object> paramMap) throws Exception;

	public int getMarketReviewInfo(Map<String, Object> paramMap) throws Exception;
	
}
