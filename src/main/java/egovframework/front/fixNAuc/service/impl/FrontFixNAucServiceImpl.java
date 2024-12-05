package egovframework.front.fixNAuc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.at.LoginTiberoMapper;
import egovframework.cms.cmm.service.FileVO;
import egovframework.flower.FixNAucMapper;
import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.fixNAuc.service.FrontFixNAucService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("frontFixNAucService")
public class FrontFixNAucServiceImpl extends EgovAbstractServiceImpl implements FrontFixNAucService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixNAucServiceImpl.class);
	
	@Resource(name = "frontFixNAucMapper")
	private FrontFixNAucMapper frontFixNAucMapper;
	
	@Resource(name = "fixNAucMapper")
	private FixNAucMapper fixNAucMapper;
	
	@Resource(name="loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;

	@Override
	public List<EgovMap> getRecentList(Map<String, Object> paramMap)  throws Exception{
		// TODO Auto-generated method stub
		return fixNAucMapper.getRecentList(paramMap);
	}

	@Override
	public int fixSellRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixSellRegProc(paramMap);
	}

	@Override
	public void fixSellFileProc(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucMapper.fixSellFileProc(fileMap);
	}

	@Override
	public EgovMap getFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFileOne(fileMap);
	}

	@Override
	public void deleteFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucMapper.deleteFileOne(fileMap);
		
	}

	@Override
	public int fixSellUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixSellUptProc(paramMap);
	}

	@Override
	public List<EgovMap> getFixList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixList(paramMap);
	}

	@Override
	public EgovMap getFixArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixArticle(paramMap);
	}

	@Override
	public List<EgovMap> getFixFileList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixFileList(paramMap);
	}

	@Override
	public int fixSellDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixSellDelProc(paramMap);
	}

	@Override
	public void fixSellFileDelProc(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucMapper.fixSellFileDelProc(paramMap);
	}

	@Override
	public List<EgovMap> getMokList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getMokList(paramMap);
	}

	@Override
	public List<EgovMap> getPumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getPumList(paramMap);
	}

	@Override
	public int getFixListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixListCnt(paramMap);
	}

	@Override
	public EgovMap getTime(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getTime(paramMap);
	}

	@Override
	public EgovMap getCodeToLoginInfo(String memberId) throws Exception {
		// TODO Auto-generated method stub
		return loginTiberoMapper.getCodeToLoginInfo(memberId) ;
	}

	@Override
	public List<EgovMap> getPrePrice(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getPrePrice(paramMap);
	}

	@Override
	public List<EgovMap> getBidList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getBidList(paramMap);
	}

	@Override
	public List<EgovMap> getLevelList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getLevelList(paramMap);
	}

	@Override
	public EgovMap getChulCodeToChulInfo(String chulCode) throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getChulCodeToChulInfo(chulCode);
	}

	@Override
	public List<EgovMap> getFixBuyList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixBuyList(paramMap);
	}

	@Override
	public int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixBuyListCnt(paramMap);
	}

	@Override
	public EgovMap getFixBuyArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixBuyArticle(paramMap);
	}

	@Override
	public EgovMap getJCodeToJInfo(String jCode) throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getJCodeToJInfo(jCode);
	}

	@Override
	public EgovMap getFloMemberCheck(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return loginTiberoMapper.getFloMemberCheck(paramMap);
	}

	@Override
	public EgovMap getLimitAmt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getLimitAmt(paramMap);
	}

	@Override
	public EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getBidBuyInfo(paramMap);
	}

	@Override
	public void updateBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucMapper.updateBid(paramMap);
	}

	@Override
	public void insertBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixNAucMapper.insertBid(paramMap);
	}

	@Override
	public List<EgovMap> getFixAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixAdmList(paramMap);
	}

	@Override
	public int getFixAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixAdmListCnt(paramMap);
	}

	@Override
	public int fixAdmApply(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmApply(paramMap);
	}

	@Override
	public int fixAdmAppCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmAppCancel(paramMap);
	}

	@Override
	public List<EgovMap> getChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getChulList(paramMap);
	}

	@Override
	public EgovMap getCodeToLoginId(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return loginTiberoMapper.getCodeToLoginId(paramMap);
	}

	@Override
	public int fixAdmRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmRegProc(paramMap);
	}

	@Override
	public int fixAdmUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmUptProc(paramMap);
	}

	@Override
	public EgovMap getFixAdmArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixAdmArticle(paramMap);
	}

	@Override
	public int fixAdmBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBan(paramMap);
	}

	@Override
	public int fixAdmDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmDelProc(paramMap);
	}

	
	@Override
	public List<EgovMap> getFixAucTime(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixAucTime(paramMap);
	}

	@Override
	public void updatetime(Map<String, Object> setParam) throws Exception {
		frontFixNAucMapper.updatetime(setParam);
	}

	@Override
	public List<EgovMap> getJRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getJRecentList(paramMap);
	}
	
	@Override
	public List<EgovMap> getCRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getCRecentList(paramMap);
	}

	@Override
	public int reqBuyRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqBuyRegProc(paramMap);
	}

	@Override
	public EgovMap getReqArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqArticle(paramMap);
	}

	@Override
	public int reqBuyUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqBuyUptProc(paramMap);
	}

	@Override
	public int fixAdmComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmComp(paramMap);
	}

	@Override
	public List<EgovMap> getReqList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqList(paramMap);
	}

	@Override
	public int getReqListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqListCnt(paramMap);
	}

	@Override
	public int reqBuyDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqBuyDelProc(paramMap);
	}

	@Override
	public List<EgovMap> getReqAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqAdmList(paramMap);
	}

	@Override
	public int getReqAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqAdmListCnt(paramMap);
	}

	@Override
	public int reqAdmRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqAdmRegProc(paramMap);
	}

	@Override
	public int reqAdmUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqAdmUptProc(paramMap);
	}

	@Override
	public EgovMap getReqAdmArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqAdmArticle(paramMap);
	}

	@Override
	public int reqAdmDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqAdmDelProc(paramMap);
	}

	@Override
	public int reqAdmApply(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqAdmApply(paramMap);
	}

	@Override
	public int reqAdmAppCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqAdmAppCancel(paramMap);
	}

	@Override
	public int reqAdmBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqAdmBan(paramMap);
	}
	
	@Override
	public int reqAdmComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqAdmComp(paramMap);
	}

	@Override
	public List<EgovMap> getJList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getJList(paramMap);
	}

	@Override
	public List<EgovMap> getReqChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqChulList(paramMap);
	}

	@Override
	public int getReqChulListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqChulListCnt(paramMap);
	}
	
	@Override
	public EgovMap getReqChulArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getReqChulArticle(paramMap);
	}
	
	@Override
	public int reqChulBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqChulBan(paramMap);
	}
	
	@Override
	public int reqChulComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqChulComp(paramMap);
	}

	@Override
	public int getBidTotSokCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getBidTotSokCnt(paramMap);
	}

	@Override
	public int fixAdmUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmUChal(paramMap);
	}

	@Override
	public int fixAdmPartUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmPartUChal(paramMap);
	}
	
	@Override
	public int fixAdmBidTotSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBidTotSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBidSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidFail(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBidFail(paramMap);
	}
	
	
	@Override
	public int fixAdmBidPartSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBidPartSuc(paramMap);
	}

	@Override
	public int fixAdmBidAdmin(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBidAdmin(paramMap);
	}

	@Override
	public int fixAdmBidCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBidCancel(paramMap);
	}

	@Override
	public String fixSmsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixSmsProc(paramMap);
	}

	@Override
	public int fixAdmBidUpdate(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.fixAdmBidUpdate(paramMap);
	}

	@Override
	public List<EgovMap> getBidCancelList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getBidCancelList(paramMap);
	}

	@Override
	public String reqSmsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.reqSmsProc(paramMap);
	}

	@Override
	public int messageBiteLength(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.messageBiteLength(paramMap);
	}

	@Override
	public int sendSms(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.sendSms(paramMap);
	}

	@Override
	public int sendMms(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.sendMms(paramMap);
	}
	/*정산관리*/
	@Override
	public String selectMaxChulDate(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.selectMaxChulDate(paramMap);
	}

	@Override
	public EgovMap getTranAdmInfo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getTranAdmInfo(paramMap);
	}

	@Override
	public List<EgovMap> getTranAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getTranAdmList(paramMap);
	}

	@Override
	public int getTranAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getTranAdmListCnt(paramMap);
	}

	@Override
	public int tranMagamInsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.tranMagamInsProc(paramMap);
	}
	
	@Override
	public void tranMagamDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		 frontFixNAucMapper.tranMagamDelProc(paramMap);
	}
	
	@Override
	public EgovMap tranMagamCompCheck(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.tranMagamCompCheck(paramMap);
	}
	
	@Override
	public int tranMagamComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		frontFixNAucMapper.tranMagamCompSangjang(paramMap);
		
		frontFixNAucMapper.tranMagamCompPanMae(paramMap);
		
		return frontFixNAucMapper.tranMagamCompStatusUpt(paramMap);
	}

	@Override
	public int tranMagamTranCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.tranMagamTranCnt(paramMap);
	}

	@Override
	public List<Map<String,Object>> getTranCompListSangJang(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getTranCompListSangJang(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getTranCompListPanMae(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getTranCompListPanMae(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getTranListMagam(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getTranListMagam(paramMap);
	}

	@Override
	public List<EgovMap> getFixAucCompChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixAucCompChulList(paramMap);
	}

	@Override
	public List<EgovMap> getFixAucCompDomeList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.getFixAucCompDomeList(paramMap);
	}

	@Override
	public int updateFixAucCompChulBigo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixNAucMapper.updateFixAucCompChulBigo(paramMap);
	}

	@Override
	public List<EgovMap> getAdmJRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixNAucMapper.getAdmJRecentList(paramMap);
	}

	
	
	
	
	@Override
	public List<EgovMap> getBoxList(Map<String, Object> paramMap) throws Exception {
		return fixNAucMapper.getBoxList(paramMap);
	}
}
