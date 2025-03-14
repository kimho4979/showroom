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
package egovframework.cms.marketrent.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.cms.content.service.ContentService;
import egovframework.cms.content.service.ContentVO;
import egovframework.cms.login.service.LoginService;
import egovframework.cms.login.service.LoginVO;
import egovframework.cms.marketrent.service.MarketRentService;
import egovframework.cms.marketrent.vo.MarketFileVO;
import egovframework.marketrent.AtMarketRentMapper;
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

@Service("marketRentService")
public class MarketRentServiceImpl extends EgovAbstractServiceImpl implements MarketRentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarketRentServiceImpl.class);

	/** SampleDAO */
	
	
	@Resource(name="marketRentMapper")
	private MarketRentMapper marketRentMapper;
	
	@Resource(name="atMarketRentMapper")
	private AtMarketRentMapper atMarketRentMapper;

	@Override
	public int delete(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.delete(paramMap);
	}

	@Override
	public int save(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.save(paramMap);
	}

	@Override
	public int insert(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.insert(paramMap);
	}

	@Override
	public int update(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.update(paramMap);
	}

	@Override
	public List<EgovMap> getMarketRentList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return atMarketRentMapper.selectMarketList(paramMap);
	}

	@Override
	public int getMarketRentListCnt(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return atMarketRentMapper.selectMarketListCnt(paramMap);
	}

	@Override
	public EgovMap getMarketRent(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.getMarketRent(paramMap);
	}

	@Override
	public EgovMap getMarketRentAddInfo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.getMarketRentAddInfo(paramMap);
	}

	@Override
	public String getMarketGroupType(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.getMarketGroupType(paramMap);
	}

	@Override
	public List<EgovMap> getMarketGroupTypeList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.getMarketGroupTypeList(paramMap);
	}

	@Override
	public EgovMap selectMarketInfo(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return atMarketRentMapper.selectMarketInfo(paramMap);
	}

	@Override
	public void marketFileProc(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		marketRentMapper.marketFileProc(fileMap);
	}

	@Override
	public EgovMap getFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		return marketRentMapper.getFileOne(fileMap);
	}

	@Override
	public void deleteFileOne(Map<String, Object> fileMap) throws Exception {
		// TODO Auto-generated method stub
		marketRentMapper.deleteFileOne(fileMap);
	}

	@Override
	public List<MarketFileVO> selectMarketFileList(MarketFileVO vo) throws Exception
	{
		return this.marketRentMapper.selectMarketFileList(vo);
	}

	

}
