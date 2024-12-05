package egovframework.front.stat.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface StatSecondService {
	
	EgovMap marktorgInfoSelect(Map<String, Object> paramMap) throws Exception;
	
	EgovMap markSaleCalcTotSelectGroupInfo(Map<String, Object> paramMap) throws Exception;
	
    List<EgovMap> markSaleCalcTotSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap markSaleCalcTotSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> markSaleCalcTotSelect(Map<String, Object> paramMap) throws Exception;
	
	EgovMap markSaleCalcTotSelectInfoList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> markActualResultSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap markActualResultSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> markActualResultChulhajaList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> shprSaleCalcTotSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap shprSaleCalcTotSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> shprSaleCalcTotBuryuList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> shprSaleCalcTotSelectListForExcel(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> shprSaleCalcTotSelect(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> shprSaleCalcDetailBuryuList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap shprSaleCalcDetailSelectLatestDate(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> shprSaleCalcDetailSelect(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> shprActualResultSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap shprActualResultSumList(Map<String, Object> paramMap) throws Exception;

	EgovMap getFloMemberCheck(Map<String, Object> paramMap) throws Exception;

	EgovMap getDomeCodeToChulCodeCheck(Map<String, Object> paramMap) throws Exception;

	EgovMap getChulInfo(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> markShprBountySelectTermList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> markShprBountySelectList(Map<String, Object> paramMap) throws Exception;

	EgovMap markShprBountySelectInfoList(Map<String, Object> paramMap) throws Exception;

	EgovMap shprInfoSelect(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> marktorgBelongShprSelectList(Map<String, Object> paramMap) throws Exception;

	EgovMap marktorgBelongShprSelectInfoList(Map<String, Object> paramMap) throws Exception;

	EgovMap marktorgBelongShprSelectListCount(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> transDateParticularsSelectList(Map<String, Object> paramMap) throws Exception;

	EgovMap transDateParticularsSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transDateParticularsSelectListForExcel(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transShprPartiTotSelectList(Map<String, Object> paramMap) throws Exception;

	EgovMap transShprPartiTotSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transShprPartiTotSelectListForExcel(Map<String, Object> paramMap) throws Exception;

	EgovMap transShprPartiTotSelectMaxPanDate(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> transShprPartiDetailSelectList(Map<String, Object> paramMap) throws Exception;

	EgovMap transShprPartiDetailSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transShprPartiDetailSelectListForExcel(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> wholeSalerShippingSelectList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> boxLoadingSelectList(Map<String, Object> paramMap) throws Exception;
	
}
