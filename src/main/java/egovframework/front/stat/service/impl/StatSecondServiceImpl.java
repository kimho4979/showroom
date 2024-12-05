package egovframework.front.stat.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.at.LoginTiberoMapper;
import egovframework.front.stat.service.StatSecondService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.flower.StatSecondMapper;

@Service("statSecondService")
public class StatSecondServiceImpl extends EgovAbstractServiceImpl implements StatSecondService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StatSecondServiceImpl.class);

	@Resource(name = "statSecondMapper")
	private StatSecondMapper statSecondMapper;
	
	@Resource(name = "loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;
	
	@Override
	public EgovMap marktorgInfoSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.marktorgInfoSelect(paramMap);
	}
	
	
	@Override
	public EgovMap markSaleCalcTotSelectGroupInfo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markSaleCalcTotSelectGroupInfo(paramMap);
	}

	@Override
	public List<EgovMap> markSaleCalcTotSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markSaleCalcTotSelectList(paramMap);
	}

	@Override
	public EgovMap markSaleCalcTotSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markSaleCalcTotSumList(paramMap);
	}

	@Override
	public List<EgovMap> markSaleCalcTotSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markSaleCalcTotSelect(paramMap);
	}

	@Override
	public EgovMap markSaleCalcTotSelectInfoList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markSaleCalcTotSelectInfoList(paramMap);
	}

	@Override
	public List<EgovMap> markActualResultSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markActualResultSelectList(paramMap);
	}

	@Override
	public EgovMap markActualResultSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markActualResultSumList(paramMap);
	}

	@Override
	public List<EgovMap> markActualResultChulhajaList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markActualResultChulhajaList(paramMap);
	}

	@Override
	public List<EgovMap> shprSaleCalcTotSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcTotSelectList(paramMap);
	}

	@Override
	public EgovMap shprSaleCalcTotSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcTotSumList(paramMap);
	}

	@Override
	public List<EgovMap> shprSaleCalcTotBuryuList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcTotBuryuList(paramMap);
	}

	@Override
	public List<EgovMap> shprSaleCalcTotSelectListForExcel(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcTotSelectListForExcel(paramMap);
	}

	@Override
	public List<EgovMap> shprSaleCalcTotSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcTotSelect(paramMap);
	}
	
	
	@Override
	public List<EgovMap> shprSaleCalcDetailBuryuList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcDetailBuryuList(paramMap);
	}

	@Override
	public EgovMap shprSaleCalcDetailSelectLatestDate(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcDetailSelectLatestDate(paramMap);
	}

	@Override
	public List<EgovMap> shprSaleCalcDetailSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprSaleCalcDetailSelect(paramMap);
	}
	
	@Override
	public List<EgovMap> shprActualResultSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprActualResultSelectList(paramMap);
	}

	@Override
	public EgovMap shprActualResultSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprActualResultSumList(paramMap);
	}

	@Override
	public EgovMap getFloMemberCheck(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return loginTiberoMapper.getFloMemberCheck(paramMap);
	}

	@Override
	public EgovMap getDomeCodeToChulCodeCheck(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.getDomeCodeToChulCodeCheck(paramMap);
	}

	@Override
	public EgovMap getChulInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.getChulInfo(paramMap);
	}


	@Override
	public List<EgovMap> markShprBountySelectTermList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markShprBountySelectTermList(paramMap);
	}


	@Override
	public List<EgovMap> markShprBountySelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markShprBountySelectList(paramMap);
	}


	@Override
	public EgovMap markShprBountySelectInfoList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.markShprBountySelectInfoList(paramMap);
	}


	@Override
	public EgovMap shprInfoSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.shprInfoSelect(paramMap);
	}


	@Override
	public List<EgovMap> marktorgBelongShprSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.marktorgBelongShprSelectList(paramMap);
	}


	@Override
	public EgovMap marktorgBelongShprSelectInfoList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.marktorgBelongShprSelectInfoList(paramMap);
	}


	@Override
	public EgovMap marktorgBelongShprSelectListCount(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.marktorgBelongShprSelectListCount(paramMap);
	}


	@Override
	public List<EgovMap> transDateParticularsSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transDateParticularsSelectList(paramMap);
	}


	@Override
	public EgovMap transDateParticularsSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transDateParticularsSumList(paramMap);
	}
	
	@Override
	public List<EgovMap> transDateParticularsSelectListForExcel(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transDateParticularsSelectListForExcel(paramMap);
	}
	
	@Override
	public List<EgovMap> transShprPartiTotSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transShprPartiTotSelectList(paramMap);
	}


	@Override
	public EgovMap transShprPartiTotSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transShprPartiTotSumList(paramMap);
	}
	
	@Override
	public List<EgovMap> transShprPartiTotSelectListForExcel(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transShprPartiTotSelectListForExcel(paramMap);
	}


	@Override
	public EgovMap transShprPartiTotSelectMaxPanDate(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transShprPartiTotSelectMaxPanDate(paramMap);
	}


	@Override
	public List<EgovMap> transShprPartiDetailSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transShprPartiDetailSelectList(paramMap);
	}


	@Override
	public EgovMap transShprPartiDetailSumList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transShprPartiDetailSumList(paramMap);
	}
	
	@Override
	public List<EgovMap> transShprPartiDetailSelectListForExcel(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.transShprPartiDetailSelectListForExcel(paramMap);
	}


	@Override
	public List<EgovMap> wholeSalerShippingSelectList(
			Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.wholeSalerShippingSelectList(paramMap);
	}


	@Override
	public List<EgovMap> boxLoadingSelectList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statSecondMapper.boxLoadingSelectList(paramMap);
	}


}
