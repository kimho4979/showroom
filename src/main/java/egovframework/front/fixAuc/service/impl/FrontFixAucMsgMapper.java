package egovframework.front.fixAuc.service.impl;

import java.util.Map;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("frontFixAucMsgMapper")
public interface FrontFixAucMsgMapper {
	
	String fixSmsProcChul(Map<String, Object> paramMap) throws Exception;
	
	String fixSmsProcJ(Map<String, Object> paramMap) throws Exception;
	
	String fixSmallSmsProcJ(Map<String, Object> paramMap) throws Exception;

	String reqSmsProcChul(Map<String, Object> paramMap) throws Exception;
	
	String reqSmsProcJ(Map<String, Object> paramMap) throws Exception;
	
}
