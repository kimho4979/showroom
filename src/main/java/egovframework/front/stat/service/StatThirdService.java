package egovframework.front.stat.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface StatThirdService {
	
	List<EgovMap> getChulList(Map<String, Object> paramMap) throws Exception;
	List<EgovMap> shprSaleCalcDetailSelect(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> mafraMagamMasterListJson(Map<String, Object> paramMap) throws Exception;
}
