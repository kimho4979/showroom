package egovframework.front.stat.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface StatFourthService {
	
	List<EgovMap> sangJangCntList(Map<String, Object> paramMap) throws Exception;
}

