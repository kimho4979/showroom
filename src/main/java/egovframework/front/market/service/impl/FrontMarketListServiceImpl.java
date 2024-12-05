package egovframework.front.market.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.front.market.service.FrontMarketListService;
import egovframework.front.market.service.MarketService;
import egovframework.front.market.vo.MarketInfoVO;
import egovframework.front.market.vo.MarketReviewVO;
import egovframework.marketrent.AtMarketRentMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("frontMarketListService")
public class FrontMarketListServiceImpl extends EgovAbstractServiceImpl implements FrontMarketListService{

	@Resource(name="frontMarketListMapper")
	private FrontMarketListMapper frontMarketListMapper;
	
	@Resource(name="atMarketRentMapper")
	private AtMarketRentMapper atMarketRentMapper;
	
	
	@Override
	public EgovMap getMarketTypeGroupInfo(Map<String, Object> paramMap)
			throws Exception {
		return frontMarketListMapper.getMarketTypeGroupInfo(paramMap); 
	}

	@Override
	public List<EgovMap> getMarketAtList(Map<String, Object> paramMap)
			throws Exception {
		return atMarketRentMapper.getMarketAtList(paramMap);
	}

	@Override
	public EgovMap getOraMarketInfo(Map<String, Object> paramMap)
			throws Exception {
		return frontMarketListMapper.getOraMarketInfo(paramMap);
	}

	@Override
	public EgovMap getAtMarketInfo(Map<String, Object> paramMap)
			throws Exception {
		return atMarketRentMapper.getAtMarketInfo(paramMap);
	}

	@Override
	public List<EgovMap> getOraMarketList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontMarketListMapper.getOraMarketList(paramMap);
	}




}
