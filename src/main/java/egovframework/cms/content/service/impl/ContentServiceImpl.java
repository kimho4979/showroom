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
package egovframework.cms.content.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.cms.content.service.ContentService;
import egovframework.cms.content.service.ContentVO;
import egovframework.cms.login.service.LoginService;
import egovframework.cms.login.service.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

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

@Service("contentService")
public class ContentServiceImpl extends EgovAbstractServiceImpl implements ContentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);

	/** SampleDAO */
	
	// TODO mybatis 사용
	@Resource(name="contentMapper")
	private ContentMapper contentMapper;

	@Override
	public List<ContentVO> getContentList(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return contentMapper.getContentList(paramMap);
	}

	@Override
	public int getContentListCnt(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return contentMapper.getContentListCnt(paramMap);
	}

	@Override
	public ContentVO getContent(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return contentMapper.getContent(paramMap);
	}

	@Override
	public int delete(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return contentMapper.delete(paramMap);
	}

	@Override
	public int save(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return contentMapper.save(paramMap);
	}

	@Override
	public int insert(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return contentMapper.insert(paramMap);
	}

	@Override
	public int update(Map<String, Object> paramMap) throws Exception{
		// TODO Auto-generated method stub
		return contentMapper.update(paramMap);
	}

	

	

}
