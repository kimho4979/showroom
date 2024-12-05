package egovframework.flower;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Mapper("statThirdMapper")
public interface StatThirdMapper {
	
	List<EgovMap> getChulList(Map<String, Object> paramMap) throws Exception;
	List<EgovMap> shprSaleCalcDetailSelect(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> mafraMagamMasterListJson(Map<String, Object> paramMap) throws Exception;
}
