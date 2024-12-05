package egovframework.front.fixAuc.service;

import java.util.Map;

public interface FrontFixAucMsgService {
	
	String fixSmsProcChul(Map<String, Object> paramMap) throws Exception;
	
	String fixSmsProcJ(Map<String, Object> paramMap) throws Exception;
	
	String fixSmallSmsProcJ(Map<String, Object> paramMap) throws Exception;

	String reqSmsProcChul(Map<String, Object> paramMap) throws Exception;
	
	String reqSmsProcJ(Map<String, Object> paramMap) throws Exception;
	
}
