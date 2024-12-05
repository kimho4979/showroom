package egovframework.front.fixAuc.service.impl;

import java.util.List;
import java.util.Map;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("FrontFixAucRecordMapper")
public interface FrontFixAucRecordMapper {
	
	int fixAucRegRecord(Map<String, Object> parmaMap) throws Exception;
	
	List<EgovMap> getFixRecordList(Map<String, Object> paramMap) throws Exception;
	
	String getPreFixState(String dealSeq) throws Exception;
	
	String getPreFixSmallState(String dealSeq) throws Exception;
	
	String getPreReqState(String dealSeq) throws Exception;
}
