package egovframework.front.content.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import egovframework.front.content.service.FrontContentService;
import egovframework.front.content.service.FrontContentVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("frontContentService")
public class FrontContentServiceImpl extends EgovAbstractServiceImpl implements FrontContentService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontContentServiceImpl.class);
	
	@Resource(name = "frontContentMapper")
	private FrontContentMapper frontContentMapper;
	
	
	@Override
	public FrontContentVO getFrontContent(Map<String, Object> paramMap){
		return frontContentMapper.getFrontContent(paramMap);
	}
}
