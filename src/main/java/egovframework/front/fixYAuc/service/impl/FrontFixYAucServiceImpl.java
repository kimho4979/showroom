package egovframework.front.fixYAuc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.at.LoginTiberoMapper;
import egovframework.cms.cmm.service.FileVO;
import egovframework.flower.FixYAucMapper;
import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.fixYAuc.service.FrontFixYAucService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("frontFixYAucService")
public class FrontFixYAucServiceImpl extends EgovAbstractServiceImpl implements FrontFixYAucService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixYAucServiceImpl.class);
	
	@Resource(name = "frontFixYAucMapper")
	private FrontFixYAucMapper frontFixYAucMapper;
	
	@Resource(name = "fixYAucMapper")
	private FixYAucMapper fixYAucMapper;
	
	@Resource(name="loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;

	@Override
	public List<EgovMap> getRecentList(Map<String, Object> paramMap)  throws Exception{
		// TODO Auto-generated method stub
		return fixYAucMapper.getRecentList(paramMap);
	}

	@Override
	public int fixSellRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixSellRegProc(paramMap);
	}

	@Override
	public void fixSellFileProc(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixYAucMapper.fixSellFileProc(fileMap);
	}

	@Override
	public EgovMap getFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFileOne(fileMap);
	}

	@Override
	public void deleteFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixYAucMapper.deleteFileOne(fileMap);
		
	}

	@Override
	public int fixSellUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixSellUptProc(paramMap);
	}

	@Override
	public List<EgovMap> getFixList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixList(paramMap);
	}

	@Override
	public EgovMap getFixArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixArticle(paramMap);
	}

	@Override
	public List<EgovMap> getFixFileList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixFileList(paramMap);
	}

	@Override
	public int fixSellDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixSellDelProc(paramMap);
	}

	@Override
	public void fixSellFileDelProc(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		frontFixYAucMapper.fixSellFileDelProc(paramMap);
	}

	@Override
	public List<EgovMap> getMokList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getMokList(paramMap);
	}

	@Override
	public List<EgovMap> getPumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getPumList(paramMap);
	}

	@Override
	public int getFixListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixListCnt(paramMap);
	}

	@Override
	public EgovMap getTime(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getTime(paramMap);
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
		return fixYAucMapper.getPrePrice(paramMap);
	}

	@Override
	public List<EgovMap> getBidList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getBidList(paramMap);
	}

	@Override
	public List<EgovMap> getLevelList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getLevelList(paramMap);
	}

	@Override
	public EgovMap getChulCodeToChulInfo(String chulCode) throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getChulCodeToChulInfo(chulCode);
	}

	@Override
	public List<EgovMap> getFixBuyList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixBuyList(paramMap);
	}

	@Override
	public int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixBuyListCnt(paramMap);
	}

	@Override
	public EgovMap getFixBuyArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixBuyArticle(paramMap);
	}

	@Override
	public EgovMap getJCodeToJInfo(String jCode) throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getJCodeToJInfo(jCode);
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
		return frontFixYAucMapper.getLimitAmt(paramMap);
	}

	@Override
	public EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getBidBuyInfo(paramMap);
	}

	@Override
	public void updateBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixYAucMapper.updateBid(paramMap);
	}

	@Override
	public void insertBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixYAucMapper.insertBid(paramMap);
	}

	@Override
	public List<EgovMap> getFixAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixAdmList(paramMap);
	}

	@Override
	public int getFixAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixAdmListCnt(paramMap);
	}

	@Override
	public int fixAdmApply(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmApply(paramMap);
	}

	@Override
	public int fixAdmAppCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmAppCancel(paramMap);
	}

	@Override
	public List<EgovMap> getChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getChulList(paramMap);
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
		return frontFixYAucMapper.fixAdmRegProc(paramMap);
	}

	@Override
	public int fixAdmUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmUptProc(paramMap);
	}

	@Override
	public EgovMap getFixAdmArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixAdmArticle(paramMap);
	}

	@Override
	public int fixAdmBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBan(paramMap);
	}

	@Override
	public int fixAdmDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmDelProc(paramMap);
	}

	
	@Override
	public List<EgovMap> getFixAucTime(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixAucTime(paramMap);
	}

	@Override
	public void updatetime(Map<String, Object> setParam) throws Exception {
		frontFixYAucMapper.updatetime(setParam);
	}

	@Override
	public List<EgovMap> getJRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getJRecentList(paramMap);
	}
	
	@Override
	public List<EgovMap> getCRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getCRecentList(paramMap);
	}

	@Override
	public int reqBuyRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqBuyRegProc(paramMap);
	}

	@Override
	public EgovMap getReqArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqArticle(paramMap);
	}

	@Override
	public int reqBuyUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqBuyUptProc(paramMap);
	}

	@Override
	public int fixAdmComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmComp(paramMap);
	}

	@Override
	public List<EgovMap> getReqList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqList(paramMap);
	}

	@Override
	public int getReqListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqListCnt(paramMap);
	}

	@Override
	public int reqBuyDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqBuyDelProc(paramMap);
	}

	@Override
	public List<EgovMap> getReqAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqAdmList(paramMap);
	}

	@Override
	public int getReqAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqAdmListCnt(paramMap);
	}

	@Override
	public int reqAdmRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqAdmRegProc(paramMap);
	}

	@Override
	public int reqAdmUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqAdmUptProc(paramMap);
	}

	@Override
	public EgovMap getReqAdmArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqAdmArticle(paramMap);
	}

	@Override
	public int reqAdmDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqAdmDelProc(paramMap);
	}

	@Override
	public int reqAdmApply(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqAdmApply(paramMap);
	}

	@Override
	public int reqAdmAppCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqAdmAppCancel(paramMap);
	}

	@Override
	public int reqAdmBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqAdmBan(paramMap);
	}
	
	@Override
	public int reqAdmComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqAdmComp(paramMap);
	}

	@Override
	public List<EgovMap> getJList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getJList(paramMap);
	}

	@Override
	public List<EgovMap> getReqChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqChulList(paramMap);
	}

	@Override
	public int getReqChulListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqChulListCnt(paramMap);
	}
	
	@Override
	public EgovMap getReqChulArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getReqChulArticle(paramMap);
	}
	
	@Override
	public int reqChulBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqChulBan(paramMap);
	}
	
	@Override
	public int reqChulComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqChulComp(paramMap);
	}

	@Override
	public int getBidTotSokCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getBidTotSokCnt(paramMap);
	}

	@Override
	public int fixAdmUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmUChal(paramMap);
	}

	@Override
	public int fixAdmPartUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmPartUChal(paramMap);
	}
	
	@Override
	public int fixAdmBidTotSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBidTotSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBidSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidFail(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBidFail(paramMap);
	}
	
	
	@Override
	public int fixAdmBidPartSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBidPartSuc(paramMap);
	}

	@Override
	public int fixAdmBidAdmin(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBidAdmin(paramMap);
	}

	@Override
	public int fixAdmBidCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBidCancel(paramMap);
	}

	@Override
	public String fixSmsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixSmsProc(paramMap);
	}

	@Override
	public int fixAdmBidUpdate(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.fixAdmBidUpdate(paramMap);
	}

	@Override
	public List<EgovMap> getBidCancelList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getBidCancelList(paramMap);
	}

	@Override
	public String reqSmsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.reqSmsProc(paramMap);
	}

	@Override
	public int messageBiteLength(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.messageBiteLength(paramMap);
	}

	@Override
	public int sendSms(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.sendSms(paramMap);
	}

	@Override
	public int sendMms(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.sendMms(paramMap);
	}
	
	/*정산관리*/
	@Override
	public String selectMaxChulDate(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.selectMaxChulDate(paramMap);
	}

	@Override
	public EgovMap getTranAdmInfo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getTranAdmInfo(paramMap);
	}

	@Override
	public List<EgovMap> getTranAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getTranAdmList(paramMap);
	}

	@Override
	public int getTranAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getTranAdmListCnt(paramMap);
	}

	@Override
	public int tranMagamInsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.tranMagamInsProc(paramMap);
	}
	
	@Override
	public void tranMagamDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		 frontFixYAucMapper.tranMagamDelProc(paramMap);
	}
	
	@Override
	public EgovMap tranMagamCompCheck(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.tranMagamCompCheck(paramMap);
	}
	
	@Override
	public int tranMagamComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		frontFixYAucMapper.tranMagamCompSangjang(paramMap);
		
		frontFixYAucMapper.tranMagamCompPanMae(paramMap);
		
		return frontFixYAucMapper.tranMagamCompStatusUpt(paramMap);
	}

	@Override
	public int tranMagamTranCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.tranMagamTranCnt(paramMap);
	}

	@Override
	public List<Map<String,Object>> getTranCompListSangJang(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getTranCompListSangJang(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getTranCompListPanMae(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getTranCompListPanMae(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getTranListMagam(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getTranListMagam(paramMap);
	}
	
	@Override
	public List<EgovMap> getFixAucCompChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixAucCompChulList(paramMap);
	}

	@Override
	public List<EgovMap> getFixAucCompDomeList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.getFixAucCompDomeList(paramMap);
	}

	@Override
	public int updateFixAucCompChulBigo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixYAucMapper.updateFixAucCompChulBigo(paramMap);
	}

	@Override
	public List<EgovMap> getAdmJRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixYAucMapper.getAdmJRecentList(paramMap);
	}
	
	
}
