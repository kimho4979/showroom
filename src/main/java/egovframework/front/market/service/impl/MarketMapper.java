package egovframework.front.market.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("marketMapper")
public interface MarketMapper {

	public Integer getMarketInfoListCnt(MarketInfoVO marketInfoVO)
		throws Exception;
	
	public List<MarketInfoVO> getMarketInfoList(MarketInfoVO marketInfoVO)
		throws Exception;
	
	public MarketInfoVO getMarketInfo(MarketInfoVO marketInfoVO)
		throws Exception;
	
	public void regMarketReview(Map<String, Object> paramMap) 
		throws Exception;
	
	public List<EgovMap> getMarketReviewList(MarketReviewVO vo)
		throws Exception;

	public EgovMap getMarketReviewListCnt(MarketReviewVO marketReviewVO)
		throws Exception;

	public List<EgovMap> getMarketFileList(Map<String, Object> paramMap) throws Exception;

	public int getMarketReviewInfo(Map<String, Object> paramMap) throws Exception; 
}
