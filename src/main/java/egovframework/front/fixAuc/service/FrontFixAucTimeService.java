package egovframework.front.fixAuc.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface FrontFixAucTimeService {
	
	List<EgovMap> getFixAucTime(String bunChk) throws Exception;
	
	List<EgovMap> getFixAucTimeBid(String bunChk) throws Exception;
	
	List<EgovMap> getFixAucTimeWriteBid(String bunChk) throws Exception;
	
	void updatetime(Map<String, Object> setParam) throws Exception;
	
	List<EgovMap> getCurrentTime() throws Exception;	
}
