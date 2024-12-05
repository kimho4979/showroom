package egovframework.front.stat.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.at.LoginTiberoMapper;
import egovframework.front.stat.service.StatService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.flower.StatMapper;

@Service("statService")
public class StatServiceImpl extends EgovAbstractServiceImpl implements StatService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StatServiceImpl.class);

	@Resource(name = "statMapper")
	private StatMapper statMapper;
	
	@Resource(name = "loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;

	@Override
	public List<EgovMap> marktorgBelongShprSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statMapper.marktorgBelongShprSelectList(paramMap);
	}

	@Override
	public EgovMap marktorgBelongShprSelectListCnt(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.marktorgBelongShprSelectListCnt(paramMap);
	}

	@Override
	public List<EgovMap> marktorgBelongShprSelectInfoList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statMapper.marktorgBelongShprSelectInfoList(paramMap);
	}

	@Override
	public EgovMap whslDealCalcMdateSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealCalcMdateSelect(paramMap);
	}
	
	@Override
	public List<EgovMap> whslDealCalcSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealCalcSelectList(paramMap);
	}

	@Override
	public List<EgovMap> whslDealCalcSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealCalcSumList(paramMap);
	}

	@Override
	public EgovMap whslDealCalcTotList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealCalcTotList(paramMap);
	}

	@Override
	public List<EgovMap> whslDealBlotterBuryuList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealBlotterBuryuList(paramMap);
	}

	@Override
	public List<EgovMap> whslDealBlotterSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealBlotterSelectList(paramMap);
	}

	@Override
	public EgovMap whslDealBlotterSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealBlotterSumList(paramMap);
	}

	@Override
	public List<EgovMap> transDateParticularsSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statMapper.transDateParticularsSelectList(paramMap);
	}

	@Override
	public EgovMap transDateParticularsSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.transDateParticularsSumList(paramMap);
	}

	@Override
	public List<EgovMap> transDateParticularsSelectListForExcel(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statMapper.transDateParticularsSelectListForExcel(paramMap);
	}

	@Override
	public List<EgovMap> transShprPartiDetailSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statMapper.transShprPartiDetailSelectList(paramMap);
	}

	@Override
	public EgovMap transShprPartiDetailSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.transShprPartiDetailSumList(paramMap);
	}

	@Override
	public List<EgovMap> transShprPartiDetailSelectListForExcel(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statMapper.transShprPartiDetailSelectListForExcel(paramMap);
	}

	@Override
	public List<EgovMap> wholesalerShippingSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statMapper.wholesalerShippingSelectList(paramMap);
	}

	@Override
	public List<EgovMap> boxLoadingSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.boxLoadingSelectList(paramMap);
	}

	@Override
	public EgovMap getFloMemberCheck(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return loginTiberoMapper.getFloMemberCheck(paramMap);
	}

	@Override
	public EgovMap whslDealBlotterNameSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.whslDealBlotterNameSelect(paramMap);
	}

	@Override
	public List<EgovMap> aucPriceList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.aucPriceList(paramMap);
	}

	@Override
	public List<EgovMap> maxBunChkPanDay(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.maxBunChkPanDay(paramMap);
	}

	@Override
	public List<EgovMap> pumAucPriceList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statMapper.pumAucPriceList(paramMap);
	}
	



}
