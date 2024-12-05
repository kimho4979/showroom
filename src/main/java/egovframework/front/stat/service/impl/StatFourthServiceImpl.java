package egovframework.front.stat.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.at.LoginTiberoMapper;
import egovframework.front.stat.service.StatFourthService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.flower.StatFourthMapper;

@Service("statFourthService")
public class StatFourthServiceImpl extends EgovAbstractServiceImpl implements StatFourthService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StatFourthServiceImpl.class);

	@Resource(name = "statFourthMapper")
	private StatFourthMapper statFourthMapper;
	
	@Resource(name = "loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;
	
	@Override
	public List<EgovMap> sangJangCntList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statFourthMapper.sangJangCntList(paramMap);
	}
	
}
