package egovframework.front.fixCAuc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.at.LoginTiberoMapper;
import egovframework.cms.cmm.service.FileVO;
import egovframework.flower.FixCAucMapper;
import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.fixCAuc.service.FrontFixCAucService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("frontFixCAucService")
public class FrontFixCAucServiceImpl extends EgovAbstractServiceImpl implements FrontFixCAucService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixCAucServiceImpl.class);
	
	@Resource(name = "frontFixCAucMapper")
	private FrontFixCAucMapper frontFixCAucMapper;
	
	@Resource(name = "fixCAucMapper")
	private FixCAucMapper fixCAucMapper;
	
	@Resource(name="loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;

	@Override
	public List<EgovMap> getRecentList(Map<String, Object> paramMap)  throws Exception{
		// TODO Auto-generated method stub
		return fixCAucMapper.getRecentList(paramMap);
	}

	@Override
	public int fixSellRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellRegProc(paramMap);
	}

	@Override
	public void fixSellFileProc(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.fixSellFileProc(fileMap);
	}

	@Override
	public EgovMap getFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFileOne(fileMap);
	}

	@Override
	public void deleteFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.deleteFileOne(fileMap);
		
	}

	@Override
	public int fixSellUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellUptProc(paramMap);
	}

	@Override
	public List<EgovMap> getFixList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixList(paramMap);
	}

	@Override
	public EgovMap getFixArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixArticle(paramMap);
	}

	@Override
	public List<EgovMap> getFixFileList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixFileList(paramMap);
	}

	@Override
	public int fixSellDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellDelProc(paramMap);
	}

	@Override
	public void fixSellFileDelProc(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.fixSellFileDelProc(paramMap);
	}

	@Override
	public List<EgovMap> getMokList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getMokList(paramMap);
	}

	@Override
	public List<EgovMap> getPumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getPumList(paramMap);
	}

	@Override
	public int getFixListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixListCnt(paramMap);
	}

	@Override
	public EgovMap getTime(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getTime(paramMap);
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
		return fixCAucMapper.getPrePrice(paramMap);
	}

	@Override
	public List<EgovMap> getBidList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getBidList(paramMap);
	}

	@Override
	public List<EgovMap> getLevelList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getLevelList(paramMap);
	}

	@Override
	public EgovMap getChulCodeToChulInfo(String chulCode) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getChulCodeToChulInfo(chulCode);
	}

	@Override
	public List<EgovMap> getFixBuyList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixBuyList(paramMap);
	}

	@Override
	public int getFixBuyListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixBuyListCnt(paramMap);
	}

	@Override
	public EgovMap getFixBuyArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixBuyArticle(paramMap);
	}

	@Override
	public EgovMap getJCodeToJInfo(String jCode) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getJCodeToJInfo(jCode);
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
		return frontFixCAucMapper.getLimitAmt(paramMap);
	}

	@Override
	public EgovMap getBidBuyInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getBidBuyInfo(paramMap);
	}

	@Override
	public void updateBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.updateBid(paramMap);
	}

	@Override
	public void insertBid(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.insertBid(paramMap);
	}

	@Override
	public List<EgovMap> getFixAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixAdmList(paramMap);
	}

	@Override
	public int getFixAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixAdmListCnt(paramMap);
	}

	@Override
	public int fixAdmApply(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmApply(paramMap);
	}

	@Override
	public int fixAdmAppCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmAppCancel(paramMap);
	}

	@Override
	public List<EgovMap> getChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getChulList(paramMap);
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
		return frontFixCAucMapper.fixAdmRegProc(paramMap);
	}

	@Override
	public int fixAdmUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmUptProc(paramMap);
	}

	@Override
	public EgovMap getFixAdmArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixAdmArticle(paramMap);
	}

	@Override
	public int fixAdmBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBan(paramMap);
	}

	@Override
	public int fixAdmDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmDelProc(paramMap);
	}

	
	@Override
	public List<EgovMap> getFixAucTime(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixAucTime(paramMap);
	}

	@Override
	public void updatetime(Map<String, Object> setParam) throws Exception {
		frontFixCAucMapper.updatetime(setParam);
	}

	@Override
	public List<EgovMap> getJRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getJRecentList(paramMap);
	}
	
	@Override
	public List<EgovMap> getCRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getCRecentList(paramMap);
	}

	@Override
	public int reqBuyRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqBuyRegProc(paramMap);
	}

	@Override
	public EgovMap getReqArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqArticle(paramMap);
	}

	@Override
	public int reqBuyUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqBuyUptProc(paramMap);
	}

	@Override
	public int fixAdmComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmComp(paramMap);
	}

	@Override
	public List<EgovMap> getReqList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqList(paramMap);
	}

	@Override
	public int getReqListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqListCnt(paramMap);
	}

	@Override
	public int reqBuyDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqBuyDelProc(paramMap);
	}

	@Override
	public List<EgovMap> getReqAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqAdmList(paramMap);
	}

	@Override
	public int getReqAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqAdmListCnt(paramMap);
	}

	@Override
	public int reqAdmRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqAdmRegProc(paramMap);
	}

	@Override
	public int reqAdmUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqAdmUptProc(paramMap);
	}

	@Override
	public EgovMap getReqAdmArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqAdmArticle(paramMap);
	}

	@Override
	public int reqAdmDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqAdmDelProc(paramMap);
	}

	@Override
	public int reqAdmApply(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqAdmApply(paramMap);
	}

	@Override
	public int reqAdmAppCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqAdmAppCancel(paramMap);
	}

	@Override
	public int reqAdmBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqAdmBan(paramMap);
	}
	
	@Override
	public int reqAdmComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqAdmComp(paramMap);
	}

	@Override
	public List<EgovMap> getJList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getJList(paramMap);
	}

	@Override
	public List<EgovMap> getReqChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqChulList(paramMap);
	}

	@Override
	public int getReqChulListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqChulListCnt(paramMap);
	}
	
	@Override
	public EgovMap getReqChulArticle(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqChulArticle(paramMap);
	}
	
	@Override
	public int reqChulBan(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqChulBan(paramMap);
	}
	
	@Override
	public int reqChulComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqChulComp(paramMap);
	}

	@Override
	public int getBidTotSokCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getBidTotSokCnt(paramMap);
	}

	@Override
	public int fixAdmUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmUChal(paramMap);
	}

	@Override
	public int fixAdmPartUChal(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmPartUChal(paramMap);
	}
	
	@Override
	public int fixAdmBidTotSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBidTotSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBidSuc(paramMap);
	}
	
	@Override
	public int fixAdmBidFail(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBidFail(paramMap);
	}
	
	
	@Override
	public int fixAdmBidPartSuc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBidPartSuc(paramMap);
	}

	@Override
	public int fixAdmBidAdmin(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBidAdmin(paramMap);
	}

	@Override
	public int fixAdmBidCancel(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBidCancel(paramMap);
	}

	@Override
	public String fixSmsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSmsProc(paramMap);
	}

	@Override
	public List<EgovMap> getReqAdmTable(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqAdmTable(paramMap);
	}

	@Override
	public int getReqAdmTableCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqAdmTableCnt(paramMap);
	}
	
	@Override
	public int updateReaDealTable(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.updateReaDealTable(paramMap);
	}

	@Override
	public int updateReaDealTableComp(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.updateReaDealTableComp(paramMap);
	}

	@Override
	public int updateReaDealTableDisComp(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.updateReaDealTableDisComp(paramMap);
	}

	@Override
	public List<EgovMap> getReqAdmTableExcellComp(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getReqAdmTableExcellComp(paramMap);
	}
	
	@Override
	public int fixAdmBidUpdate(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmBidUpdate(paramMap);
	}
	
	@Override
	public List<EgovMap> getBidCancelList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getBidCancelList(paramMap);
	}

	@Override
	public EgovMap getMokInfo(Map<String, Object> reqParam) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getMokInfo(reqParam);
	}

	@Override
	public EgovMap getPumInfo(Map<String, Object> reqParam) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getPumInfo(reqParam);
	}

	@Override
	public EgovMap getLevelInfo(Map<String, Object> reqParam) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getLevelInfo(reqParam);
	}

	@Override
	public int getfixSubChulInfoCnt(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getfixSubChulInfoCnt(paramMap);
	}

	@Override
	public List<EgovMap> fixSellSubList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellSubList(paramMap);
	}

	@Override
	public int fixSellSubListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellSubListCnt(paramMap);
	}

	@Override
	public EgovMap getFixSubArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixSubArticle(paramMap);
	}

	@Override
	public List<EgovMap> getFixSubFileList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixSubFileList(paramMap);
	}
	
	@Override
	public EgovMap getfixSubChulInfo(String chulCode)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getfixSubChulInfo(chulCode);
	}

	@Override
	public int fixSellSubRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellSubRegProc(paramMap);
	}

	@Override
	public void fixSellSubFileProc(Map<String, Object> fileMap)
			throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.fixSellSubFileProc(fileMap);
	}

	@Override
	public int fixSellSubUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellSubUptProc(paramMap);
	}

	@Override
	public void deleteSubFileOne(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.deleteSubFileOne(paramMap);
	}

	@Override
	public EgovMap getSubFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getSubFileOne(fileMap);
	}

	@Override
	public int fixSellSubDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixSellSubDelProc(paramMap);
	}

	@Override
	public void fixSellSubFileDelProc(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		frontFixCAucMapper.fixSellSubFileDelProc(paramMap);
	}

	@Override
	public List<EgovMap> fixBuySubList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixBuySubList(paramMap);
	}

	@Override
	public int fixBuySubListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixBuySubListCnt(paramMap);
	}

	@Override
	public EgovMap getFixBuySubArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixBuySubArticle(paramMap);
	}
	
	@Override
	public List<EgovMap> getfixSubChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getfixSubChulList(paramMap);
	}

	@Override
	public int fixAdmSubChulRegProc(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmSubChulRegProc(paramMap);
	}

	@Override
	public int fixAdmSubChulDelProc(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmSubChulDelProc(paramMap);
	}

	@Override
	public int reqBuySubRegProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqBuySubRegProc(paramMap);
	}

	@Override
	public String reqSmsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.reqSmsProc(paramMap);
	}
	
	@Override
	public int messageBiteLength(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.messageBiteLength(paramMap);
	}

	@Override
	public int sendSms(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.sendSms(paramMap);
	}

	@Override
	public int sendMms(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.sendMms(paramMap);
	}
	
	/*정산관리*/
	@Override
	public String selectMaxChulDate(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.selectMaxChulDate(paramMap);
	}

	@Override
	public EgovMap getTranAdmInfo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getTranAdmInfo(paramMap);
	}

	@Override
	public List<EgovMap> getTranAdmList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getTranAdmList(paramMap);
	}

	@Override
	public int getTranAdmListCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getTranAdmListCnt(paramMap);
	}

	@Override
	public int tranMagamInsProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.tranMagamInsProc(paramMap);
	}
	
	@Override
	public void tranMagamDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		 frontFixCAucMapper.tranMagamDelProc(paramMap);
	}
	
	@Override
	public EgovMap tranMagamCompCheck(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.tranMagamCompCheck(paramMap);
	}
	
	@Override
	public int tranMagamComp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		
		frontFixCAucMapper.tranMagamCompSangjang(paramMap);
		
		frontFixCAucMapper.tranMagamCompPanMae(paramMap);
		
		return frontFixCAucMapper.tranMagamCompStatusUpt(paramMap);
	}

	@Override
	public int tranMagamTranCnt(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.tranMagamTranCnt(paramMap);
	}

	@Override
	public List<Map<String,Object>> getTranCompListSangJang(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getTranCompListSangJang(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getTranCompListPanMae(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getTranCompListPanMae(paramMap);
	}
	
	@Override
	public List<Map<String,Object>> getTranListMagam(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getTranListMagam(paramMap);
	}
	
	@Override
	public List<EgovMap> getFixAucCompChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixAucCompChulList(paramMap);
	}

	@Override
	public List<EgovMap> getFixAucCompDomeList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixAucCompDomeList(paramMap);
	}

	@Override
	public int updateFixAucCompChulBigo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.updateFixAucCompChulBigo(paramMap);
	}

	@Override
	public List<EgovMap> getAdmJRecentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return fixCAucMapper.getAdmJRecentList(paramMap);
	}

	@Override
	public EgovMap getFixAdmSubArticle(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.getFixAdmSubArticle(paramMap);
	}

	@Override
	public int fixAdmSubUptProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmSubUptProc(paramMap);
	}

	@Override
	public int fixAdmSubDelProc(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixCAucMapper.fixAdmSubDelProc(paramMap);
	}

	
	@Override
	public int updateMagamTrans(Map<String, Object> paramMap) throws Exception {
		return frontFixCAucMapper.updateMagamTrans(paramMap);
	}
	
	@Override
	public int updateTransReq(Map<String, Object> paramMap) throws Exception {
		return frontFixCAucMapper.updateTransReq(paramMap);
	}
	
	@Override
	public int updateTransFix(Map<String, Object> paramMap) throws Exception {
		return frontFixCAucMapper.updateTransFix(paramMap);
	}
}
