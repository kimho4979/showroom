package egovframework.front.menu.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.front.content.service.FrontContentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("frontMenuMapper")
public interface FrontMenuMapper {

	List<EgovMap> getMenuList() throws Exception;

	EgovMap getMenuInfo(Map<String, Object> paramMap) throws Exception;
}
