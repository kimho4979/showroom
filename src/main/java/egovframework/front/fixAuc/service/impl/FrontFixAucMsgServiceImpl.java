package egovframework.front.fixAuc.service.impl;

import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import egovframework.front.fixAuc.service.FrontFixAucMsgService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("frontFixAucMsgService")
public class FrontFixAucMsgServiceImpl extends EgovAbstractServiceImpl implements FrontFixAucMsgService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixAucMsgServiceImpl.class);
	
	@Resource(name = "frontFixAucMsgMapper")
	private FrontFixAucMsgMapper frontFixAucMsgMapper;
	
	@Override
	public String fixSmsProcChul(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixAucMsgMapper.fixSmsProcChul(paramMap);
	}
	
	@Override
	public String fixSmsProcJ(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixAucMsgMapper.fixSmsProcJ(paramMap);
	}
	
	@Override
	public String fixSmallSmsProcJ(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixAucMsgMapper.fixSmallSmsProcJ(paramMap);
	}
	
	@Override
	public String reqSmsProcChul(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixAucMsgMapper.reqSmsProcChul(paramMap);
	}
	
	@Override
	public String reqSmsProcJ(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontFixAucMsgMapper.reqSmsProcJ(paramMap);
	}

}
