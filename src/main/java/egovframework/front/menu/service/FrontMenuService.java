package egovframework.front.menu.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface FrontMenuService {

	 List<EgovMap> getMenuList() throws Exception;
	 
	 EgovMap getMenuInfo(Map<String, Object> paramMap) throws Exception;
}
