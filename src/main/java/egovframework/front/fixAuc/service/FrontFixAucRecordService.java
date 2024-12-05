package egovframework.front.fixAuc.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface FrontFixAucRecordService {

	int fixAucRegRecord(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getFixRecordList(Map<String, Object> paramMap) throws Exception;
	
	String getPreFixState(String dealSeq) throws Exception;
	
	String getPreReqState(String dealSeq) throws Exception;
	
	String getPreFixSmallState(String dealSeq) throws Exception;

}
