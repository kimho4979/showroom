/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.front.login.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.at.LoginTiberoMapper;
import egovframework.cms.login.service.LoginService;
import egovframework.cms.login.service.LoginVO;
import egovframework.flower.SmsMapper;
import egovframework.front.login.service.FrontLoginService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("frontLoginService")
public class FrontLoginServiceImpl extends EgovAbstractServiceImpl implements FrontLoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FrontLoginServiceImpl.class);

	/** SampleDAO */
	
	// TODO mybatis 사용
	@Resource(name="frontLoginMapper")
	private FrontLoginMapper frontLoginMapper;
	
	@Resource(name="smsMapper")
	private SmsMapper smsMapper;
	
	@Resource(name="loginTiberoMapper")
	private LoginTiberoMapper loginTiberoMapper;

	

	@Override
	public EgovMap getAtMember(Map<String, Object> paramMap) throws Exception {
		EgovMap map = new EgovMap();
		map.put("loginId", "chulsu");
		map.put("name", "김철수");
		map.put("email", "test@test.test2");
		map.put("phone", "010-0000-0000");
		return map;
		//return loginTiberoMapper.getAtMember(paramMap);
	}

	@Override
	public List<EgovMap> getAtMembers() throws Exception {
		List<EgovMap> list = new ArrayList<>();
		EgovMap map = new EgovMap();
		EgovMap map2 = new EgovMap();
		map.put("name", "홍길동");
		map.put("email", "test@test.com");
		map.put("phone", "010-1234-5678");
		map.put("loginId", "gildong");
		map2.put("email", "test@test.test2");
		map2.put("name", "김철수");
		map2.put("phone", "010-0000-0000");
		map2.put("loginId", "chulsu");
		list.add(map);
		list.add(map2);
		return list;
		//return loginTiberoMapper.getAtMembers();
	}



	@Override
	public List<EgovMap> getFloMember(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return loginTiberoMapper.getFloMember(paramMap);
	}



	@Override
	public EgovMap getSnsCheckVO(Map<String, Object> snsLoginVO)
			throws Exception {
		// TODO Auto-generated method stub
		return frontLoginMapper.getSnsCheckVO(snsLoginVO);
	}



	@Override
	public void insertAtSns(Map<String, Object> snsLoginVO) throws Exception {
		// TODO Auto-generated method stub
		frontLoginMapper.insertAtSns(snsLoginVO);
	}



	@Override
	public EgovMap getAtIdLogin(EgovMap atSnsLoginVO) throws Exception {
		// TODO Auto-generated method stub
		return loginTiberoMapper.getAtIdLogin(atSnsLoginVO);
	}



	@Override
	public EgovMap getNaverInfo(EgovMap atLoginVO) throws Exception {
		// TODO Auto-generated method stub
		return frontLoginMapper.getNaverInfo(atLoginVO);
	}



	@Override
	public EgovMap getKakaoInfo(EgovMap atLoginVO) throws Exception {
		// TODO Auto-generated method stub
		return frontLoginMapper.getKakaoInfo(atLoginVO);
	}



	@Override
	public EgovMap getFacebookInfo(EgovMap atLoginVO) throws Exception {
		// TODO Auto-generated method stub
		return frontLoginMapper.getFacebookInfo(atLoginVO);
	}



	@Override
	public void deleteSns(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		frontLoginMapper.deleteSns( paramMap);
	}



	@Override
	public EgovMap getFloDef(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontLoginMapper.getFloDef( paramMap);
	}



	@Override
	public int insertFloDef(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontLoginMapper.insertFloDef( paramMap);
	}



	@Override
	public int updateFloDef(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return frontLoginMapper.updateFloDef( paramMap);
	}



	@Override
	public List<EgovMap> smsList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return smsMapper.smsList( paramMap);
	}



	@Override
	public List<EgovMap> mmsList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return smsMapper.mmsList( paramMap);
	}



	@Override
	public EgovMap getSmsApp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return smsMapper.getSmsApp( paramMap);
	}



	@Override
	public void insertSmsApp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		smsMapper.insertSmsApp( paramMap);
	}



	@Override
	public void deleteSmsApp(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		smsMapper.deleteSmsApp( paramMap);
	}

	

}
