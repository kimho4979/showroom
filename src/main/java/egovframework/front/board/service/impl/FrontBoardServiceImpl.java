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
package egovframework.front.board.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.cms.board.service.BoardService;
import egovframework.cms.board.vo.BoardAnswerVO;
import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.board.vo.BoardCategoryVO;
import egovframework.cms.board.vo.BoardCommentVO;
import egovframework.cms.board.vo.BoardFileVO;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.login.service.LoginService;
import egovframework.cms.login.service.LoginVO;
import egovframework.front.board.service.FrontBoardService;
import egovframework.front.board.vo.FrontBoardArticleVO;
import egovframework.front.board.vo.FrontBoardFileVO;
import egovframework.front.board.vo.FrontBoardInfoVO;
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

@Service("frontBoardService")
public class FrontBoardServiceImpl extends EgovAbstractServiceImpl implements FrontBoardService {

	@Resource(name="frontBoardMapper")
	  private FrontBoardMapper frontBoardMapper;

	  @Resource(name="egovBoardArticleIdGnrService")
	  private EgovIdGnrService egovBoardArticleIdGnrService;

	  @Resource(name="egovBoardCommentIdGnrService")
	  private EgovIdGnrService egovBoardCommentIdGnrService;

	  @Resource(name="egovBoardAnswerIdGnrService")
	  private EgovIdGnrService egovBoardAnswerIdGnrService;

	  
	  public FrontBoardInfoVO getFrontBoardInfo(FrontBoardInfoVO vo)
		throws Exception
	  	{
		  return this.frontBoardMapper.getFrontBoardInfo(vo);
	  	}
	  
	  public int getFrontBoardArticleListCnt(FrontBoardArticleVO vo)
		throws Exception
	  	{
		  try {
			  return this.frontBoardMapper.getFrontBoardArticleListCnt(vo).intValue();
		  } catch (Exception e) {
			  return 0;
		  }
	  	}

	  public Map<String, Object> getFrontBoardArticleList(FrontBoardArticleVO vo)
		throws Exception
		{
		  List result = new ArrayList();
		  int cnt = 0;
		  try {
			  cnt = this.frontBoardMapper.getFrontBoardArticleListCnt(vo).intValue();
		  } catch (Exception e) {
		  }
		  if (cnt > 0) {
			  result = this.frontBoardMapper.getFrontBoardArticleList(vo);
		  	}

		  Map map = new HashMap();

		  map.put("resultList", result);
		  map.put("resultCnt", Integer.toString(cnt));

		  return map;
		}
	  
	  public FrontBoardArticleVO getFrontBoardArticle(FrontBoardArticleVO vo)
	  	throws Exception
	  	{
		  return this.frontBoardMapper.getFrontBoardArticle(vo);
	  	}


	  public void updateFrontBoardArticleHits(FrontBoardArticleVO vo)
	  	throws Exception
	  	{
		  this.frontBoardMapper.updateFrontBoardArticleHits(vo);
	  	}

	@Override
	public List<FrontBoardFileVO> getFrontBoardFileList(
			Map<String, Object> paramMap) throws Exception {
		  return this.frontBoardMapper.getFrontBoardFileList(paramMap);
	}

	public FrontBoardFileVO getFrontBoardFile(FrontBoardFileVO vo)
		throws Exception
	{
		return this.frontBoardMapper.getFrontBoardFile(vo);
	}

	 

}
