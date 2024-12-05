package egovframework.front.menu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.front.menu.service.FrontMenuService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("frontMenuService")
public class FrontMenuServiceImpl extends EgovAbstractServiceImpl implements FrontMenuService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontMenuServiceImpl.class);
	
	@Resource(name = "frontMenuMapper")
	private FrontMenuMapper frontMenuMapper;

	@Override
	public List<EgovMap> getMenuList() throws Exception {
		// TODO Auto-generated method stub
		//return null;
		return frontMenuMapper.getMenuList();
	}

	@Override
	public EgovMap getMenuInfo(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontMenuMapper.getMenuInfo(paramMap);
	}
	
	
	
}
