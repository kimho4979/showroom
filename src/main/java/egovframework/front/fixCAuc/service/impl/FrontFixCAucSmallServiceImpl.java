package egovframework.front.fixCAuc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.front.fixCAuc.service.FrontFixCAucSmallService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("FrontFixCAucSmallService")
public class FrontFixCAucSmallServiceImpl extends EgovAbstractServiceImpl implements FrontFixCAucSmallService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixCAucSmallServiceImpl.class);
	
	@Resource(name = "FrontFixCAucSmallMapper")
	private FrontFixCAucSmallMapper frontFixNAucSmallMapper;
	
	
	
	@Override
	public List<EgovMap> getFixAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFixAdmList(paramMap);
	}
	
	@Override
	public int getFixAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFixAdmListCnt(paramMap);
	}
	
	@Override
	public EgovMap getTime(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getTime(paramMap);
	}
	
	
	@Override
	public int fixSmallDealRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixSmallDealRegProc(paramMap);
	}
	
	
	@Override
	public int fixSmallDealRegProcDel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixSmallDealRegProcDel(paramMap);
	}
	
	@Override
	public void fixSellFileDelProc(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucSmallMapper.fixSellFileDelProc(paramMap);
	}
	
	@Override
	public int fixAdmApply(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmApply(paramMap);
	}
	
	@Override
	public int fixAdmBidUpdate(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBidUpdate(paramMap);
	}

	@Override
	public int getFixSmallDealCount(Map<String, Object> paramMap) throws Exception {
		return frontFixNAucSmallMapper.getFixSmallDealCount(paramMap);
	}
	
	@Override
	public int getFixFileCount(Map<String, Object> paramMap) throws Exception {
		return frontFixNAucSmallMapper.getFixFileCount(paramMap);
	}
	
	@Override
	public void deleteFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucSmallMapper.deleteFileOne(fileMap);
		
	}
	
	@Override
	public EgovMap getFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFileOne(fileMap);
	}
	
	@Override
	public List<EgovMap> getFixSmallDealSeq(Map<String, Object> paramMap) throws Exception {
		return frontFixNAucSmallMapper.getFixSmallDealSeq(paramMap);
	}
	
	@Override
	public List<EgovMap> getFixFileList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFixFileList(paramMap);
	}
	
	@Override
	public void fixSellFileProc(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucSmallMapper.fixSellFileProc(fileMap);
	}
	
	@Override
	public List<EgovMap> getFixBuyList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFixBuyList(paramMap);
	}
	
	@Override
	public int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFixBuyListCnt(paramMap);
	}
	
	@Override
	public EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getBidBuyInfo(paramMap);
	}
	
	@Override
	public EgovMap getFixBuyArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFixBuyArticle(paramMap);
	}
	
	@Override
	public EgovMap getFixAdmArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getFixAdmArticle(paramMap);
	}
	
	
	@Override
	public List<EgovMap> getBidList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getBidList(paramMap);
	}
	
	@Override
	public List<EgovMap> getBidCancelList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getBidCancelList(paramMap);
	}
	
	@Override
	public int fixAdmAppCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmAppCancel(paramMap);
	}
	
	@Override
	public int getBidTotSokCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.getBidTotSokCnt(paramMap);
	}
	
	@Override
	public int fixAdmUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmUChal(paramMap);
	}
	
	@Override
	public int fixAdmPartUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmPartUChal(paramMap);
	}
	
	@Override
	public int fixAdmComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmComp(paramMap);
	}
	
	@Override
	public int fixAdmBidTotSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBidTotSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBidSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidPartSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBidPartSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidFail(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBidFail(paramMap);
	}
	
	@Override
	public int fixAdmBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBan(paramMap);
	}
	
	@Override
	public int fixAdmBidCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBidCancel(paramMap);
	}
	
	@Override
	public int fixAdmBidAdmin(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmBidAdmin(paramMap);
	}

	@Override
	public int fixAdmStdPrice(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucSmallMapper.fixAdmStdPrice(paramMap);
	}

}
