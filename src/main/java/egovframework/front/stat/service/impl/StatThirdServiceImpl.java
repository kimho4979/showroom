package egovframework.front.stat.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.at.LoginTiberoMapper;
import egovframework.front.stat.service.StatThirdService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.flower.StatThirdMapper;

@Service("statThirdService")
public class StatThirdServiceImpl extends EgovAbstractServiceImpl implements StatThirdService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StatThirdServiceImpl.class);

	@Resource(name = "statThirdMapper")
	private StatThirdMapper statThirdMapper;
	
	@Resource(name = "loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;
	
	@Override
	public List<EgovMap> getChulList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statThirdMapper.getChulList(paramMap);
	}
	
	@Override
	public List<EgovMap> shprSaleCalcDetailSelect(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return statThirdMapper.shprSaleCalcDetailSelect(paramMap);
	}

	@Override
	public List<EgovMap> mafraMagamMasterListJson(Map<String, Object> paramMap) throws Exception {

		return statThirdMapper.mafraMagamMasterListJson(paramMap);
	}
}
