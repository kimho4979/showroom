package egovframework.front.fixAuc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.front.fixAuc.service.FrontFixAucService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("frontFixAucService")
public class FrontFixAucServiceImpl extends EgovAbstractServiceImpl implements FrontFixAucService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixAucServiceImpl.class);
	
	@Resource(name = "frontFixAucMapper")
	private FrontFixAucMapper frontFixAucMapper;
	
	/*
	@Override
	public FrontContentVO getFrontContent(Map<String, Object> paramMap){
		return frontContentMapper.getFrontContent(paramMap);
	}*/
}
