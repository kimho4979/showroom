package egovframework.front.fixAuc.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import egovframework.front.fixAuc.service.FrontFixAucTimeService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Service("frontFixAucTimeService")
public class FrontFixAucTimeServiceImpl extends EgovAbstractServiceImpl implements FrontFixAucTimeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixAucTimeServiceImpl.class);
	
	@Resource(name = "frontFixAucTimeMapper")
	private FrontFixAucTimeMapper FrontFixAucTimeMapper;
	
	@Override
	public List<EgovMap> getFixAucTime(String bunChk) throws Exception{
		// TODO Auto-generated method stub
		return FrontFixAucTimeMapper.getFixAucTime(bunChk);
	}
	
	@Override
	public List<EgovMap> getFixAucTimeBid(String bunChk) throws Exception{
		// TODO Auto-generated method stub
		return FrontFixAucTimeMapper.getFixAucTimeBid(bunChk);
	}
	
	@Override
	public List<EgovMap> getFixAucTimeWriteBid(String bunChk) throws Exception{
		// TODO Auto-generated method stub
		return FrontFixAucTimeMapper.getFixAucTimeWriteBid(bunChk);
	}
	
	@Override
	public void updatetime(Map<String, Object> setParam) throws Exception {
		FrontFixAucTimeMapper.updatetime(setParam);
	}
	
	@Override
	public List<EgovMap> getCurrentTime() throws Exception {
		return FrontFixAucTimeMapper.getCurrentTime();
	}

}
