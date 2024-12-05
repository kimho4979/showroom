package egovframework.front.fixAuc.service.impl;

import java.util.List;
import java.util.Map;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("frontFixAucTimeMapper")
public interface FrontFixAucTimeMapper {
	
	List<EgovMap> getFixAucTime(String bunChk) throws Exception;
	
	List<EgovMap> getFixAucTimeBid(String bunChk) throws Exception;
	
	List<EgovMap> getFixAucTimeWriteBid(String bunChk) throws Exception;

	void updatetime(Map<String, Object> parmaMap) throws Exception;

	List<EgovMap> getCurrentTime() throws Exception;
}
