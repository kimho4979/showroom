package egovframework.front.stat.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface StatService {
	
	List<EgovMap> marktorgBelongShprSelectList(Map<String, Object> paramMap) throws Exception;

	EgovMap marktorgBelongShprSelectListCnt(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> marktorgBelongShprSelectInfoList(Map<String, Object> paramMap) throws Exception;	
	
	EgovMap whslDealCalcMdateSelect(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> whslDealCalcSelectList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> whslDealCalcSumList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap whslDealCalcTotList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> whslDealBlotterBuryuList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap whslDealBlotterNameSelect(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> whslDealBlotterSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap whslDealBlotterSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transDateParticularsSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap transDateParticularsSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transDateParticularsSelectListForExcel(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transShprPartiDetailSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap transShprPartiDetailSumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> transShprPartiDetailSelectListForExcel(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> wholesalerShippingSelectList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> boxLoadingSelectList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getFloMemberCheck(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> aucPriceList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> maxBunChkPanDay(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> pumAucPriceList(Map<String, Object> paramMap) throws Exception;
}

