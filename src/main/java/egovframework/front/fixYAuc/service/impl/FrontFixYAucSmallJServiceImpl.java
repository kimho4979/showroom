package egovframework.front.fixYAuc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.front.fixYAuc.service.FrontFixYAucSmallJService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("FrontFixYAucSmallJService")
public class FrontFixYAucSmallJServiceImpl extends EgovAbstractServiceImpl implements FrontFixYAucSmallJService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixYAucSmallJServiceImpl.class);
	
	@Resource(name = "FrontFixYAucSmallJMapper")
	private FrontFixYAucSmallJMapper frontFixNAucSmallJMapper;
	
	
	
	@Override
	public EgovMap getTime(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getTime(paramMap);
	}
	
	@Override
	public List<EgovMap> getFixBuyList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getFixBuyList(paramMap);
	}
	
	@Override
	public int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getFixBuyListCnt(paramMap);
	}
	
	@Override
	public EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getBidBuyInfo(paramMap);
	}
	
	@Override
	public EgovMap getFixBuyArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getFixBuyArticle(paramMap);
	}
	
	@Override
	public List<EgovMap> getFixFileList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getFixFileList(paramMap);
	}
	
	@Override
	public void updateBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucSmallJMapper.updateBid(paramMap);
	}

	@Override
	public void insertBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucSmallJMapper.insertBid(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getFixSmallSangJangList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getFixSmallSangJangList(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getFixSmallBidList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallJMapper.getFixSmallBidList(paramMap);
	}
}
