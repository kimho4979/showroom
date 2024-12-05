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
package egovframework.cms.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.cms.board.service.BoardInfoService;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.login.service.LoginService;
import egovframework.cms.login.service.LoginVO;
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

@Service("boardInfoService")
public class BoardInfoServiceImpl extends EgovAbstractServiceImpl implements BoardInfoService {

	@Resource(name="boardInfoMapper")
	  private BoardInfoMapper boardInfoMapper;

	  @Resource(name="egovBoardIdGnrService")
	  private EgovIdGnrService egovBoardIdGnrService;

	  public List<BoardInfoVO> selectBoardInfoListAll(BoardInfoVO vo)
	    throws Exception
	  {
	    return boardInfoMapper.selectBoardInfoListAll(vo);
	  }

	  public Map<String, Object> selectBoardInfoList(BoardInfoVO vo)
	    throws Exception
	  {
	    List result = new ArrayList();
	    int cnt = boardInfoMapper.selectBoardInfoListCnt(vo).intValue();
	    if (cnt > 0) {
	      result = boardInfoMapper.selectBoardInfoList(vo);
	    }

	    Map map = new HashMap();

	    map.put("resultList", result);
	    map.put("resultCnt", Integer.toString(cnt));

	    return map;
	  }

	  public BoardInfoVO selectBoardInfo(BoardInfoVO vo)
	    throws Exception
	  {
	    return boardInfoMapper.selectBoardInfo(vo);
	  }

	  public int insertBoardInfo(BoardInfoVO vo)
	    throws Exception
	  {
	    vo.setBoardId(egovBoardIdGnrService.getNextStringId());

	    return boardInfoMapper.insertBoardInfo(vo);
	  }

	  public int updateBoardInfo(BoardInfoVO vo)
	    throws Exception
	  {
		  return boardInfoMapper.updateBoardInfo(vo);
	  }

	  public int deleteBoardInfo(BoardInfoVO vo)
	    throws Exception
	  {
		  return boardInfoMapper.deleteBoardInfo(vo);
	  }

	

	

}
