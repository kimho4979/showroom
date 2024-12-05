package egovframework.front.market.service.impl;

import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.front.market.service.MarketService;
import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.marketrent.AtFrontMarketRentMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("marketService")
public class MarketServiceImpl extends EgovAbstractServiceImpl implements MarketService{

	@Resource(name="marketMapper")
	private MarketMapper marketMapper;
	
	@Resource(name="atFrontMarketRentMapper")
	private AtFrontMarketRentMapper atFrontMarketRentMapper;
	
	@Resource(name="egovBoardArticleIdGnrService")
	private EgovIdGnrService egovBoardArticleIdGnrService;

	@Resource(name="egovBoardCommentIdGnrService")
	private EgovIdGnrService egovBoardCommentIdGnrService;

	@Resource(name="egovBoardAnswerIdGnrService")
	private EgovIdGnrService egovBoardAnswerIdGnrService;
	

	public int getMarketInfoListCnt(MarketInfoVO vo)
		throws Exception
	{
		return this.marketMapper.getMarketInfoListCnt(vo).intValue();
	}
	
	public Map<String, Object> getMarketInfoList(MarketInfoVO vo)
			throws Exception {
		List result = new ArrayList();
		int cnt = this.marketMapper.getMarketInfoListCnt(vo).intValue();
		if(cnt > 0) {
			result = this.marketMapper.getMarketInfoList(vo);
		}
		
		Map map = new HashMap();
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));
	
		return map;
	}
	
	public MarketInfoVO getMarketInfo(MarketInfoVO vo)
		throws Exception
		{
			return this.marketMapper.getMarketInfo(vo);
		}
	
	public void regMarketReview(Map<String, Object> paramMap) 
		throws Exception
		{
			marketMapper.regMarketReview(paramMap);
		}

	public List<MarketReviewVO> getMarketReviewList(MarketReviewVO vo)
		throws Exception
		{
			List result = new ArrayList();
			result = this.marketMapper.getMarketReviewList(vo);
			Map map = new HashMap();
			map.put("resultList", result);
			
			
			return result;
		}
	
	public EgovMap getMarketReviewListCnt(MarketReviewVO vo)
		throws Exception{
		return this.marketMapper.getMarketReviewListCnt(vo); 
	}

	@Override
	public EgovMap selectMarketInfo(Map<String, Object> paramMap)
		throws Exception{
		return atFrontMarketRentMapper.selectMarketInfo(paramMap);
	}

	@Override
	public List<EgovMap> getMarketFileList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return marketMapper.getMarketFileList(paramMap); 
	}

	@Override
	public int getMarketReviewInfo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return marketMapper.getMarketReviewInfo(paramMap); 
	}



}
