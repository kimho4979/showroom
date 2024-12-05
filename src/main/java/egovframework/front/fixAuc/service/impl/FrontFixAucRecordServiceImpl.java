package egovframework.front.fixAuc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import egovframework.front.fixAuc.service.FrontFixAucRecordService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("FrontFixAucRecordService")
public class FrontFixAucRecordServiceImpl extends EgovAbstractServiceImpl implements FrontFixAucRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FrontFixAucRecordServiceImpl.class);
	
	@Resource(name = "FrontFixAucRecordMapper")
	private FrontFixAucRecordMapper FrontFixAucRecordMapper;
	

	@Override
	public int fixAucRegRecord(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return FrontFixAucRecordMapper.fixAucRegRecord(paramMap);
	}
	
	@Override
	public List<EgovMap> getFixRecordList(Map<String, Object> paramMap)  throws Exception{
		// TODO Auto-generated method stub
		return FrontFixAucRecordMapper.getFixRecordList(paramMap);
	}
	
	
	@Override
	public String getPreFixState(String dealSeq)  throws Exception{
		// TODO Auto-generated method stub
		return FrontFixAucRecordMapper.getPreFixState(dealSeq);
	}
	
	@Override
	public String getPreFixSmallState(String dealSeq)  throws Exception{
		// TODO Auto-generated method stub
		return FrontFixAucRecordMapper.getPreFixSmallState(dealSeq);
	}

	
	@Override
	public String getPreReqState(String dealSeq)  throws Exception{
		// TODO Auto-generated method stub
		return FrontFixAucRecordMapper.getPreReqState(dealSeq);
	}
	
}
