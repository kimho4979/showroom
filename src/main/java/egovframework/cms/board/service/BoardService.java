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
package egovframework.cms.board.service;

import java.util.List;
import java.util.Map;

import egovframework.cms.board.vo.BoardAnswerVO;
import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.board.vo.BoardCategoryVO;
import egovframework.cms.board.vo.BoardCommentVO;
import egovframework.cms.board.vo.BoardFileVO;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.cmm.service.FileVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : EgovSampleService.java
 * @Description : EgovSampleService Class
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
public interface BoardService {

	
	public List<BoardInfoVO> selectBoardInfoListAll(BoardInfoVO paramBoardInfoVO)
		    throws Exception;

		  public BoardInfoVO selectBoardInfo(BoardInfoVO paramBoardInfoVO)
		    throws Exception;

		  public List<BoardCategoryVO> selectBoardCategoryList(BoardCategoryVO paramBoardCategoryVO)
		    throws Exception;

		  public List<BoardFileVO> selectBoardFileList(BoardFileVO paramBoardFileVO)
		    throws Exception;

		  public int selectBoardArticleListCnt(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public int selectBoardArticleListTodayCnt(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public int selectBoardArticleDeptAppointAt(String paramString1, String paramString2)
		    throws Exception;

		  public Map<String, Object> selectBoardArticleList(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public List<BoardArticleVO> selectBoardArticleListOnly(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public BoardArticleVO selectBoardArticle(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public BoardArticleVO selectPrevBoardArticle(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public BoardArticleVO selectNextBoardArticle(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public int selectBoardArticleChildCnt(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public void insertBoardArticle(String paramString, BoardArticleVO paramBoardArticleVO, FileVO paramFileVO, List<FileVO> paramList)
		    throws Exception;

		  public void updateBoardArticle(BoardArticleVO paramBoardArticleVO, FileVO paramFileVO, List<FileVO> paramList)
		    throws Exception;

		  public void updateBoardArticleHits(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public void updateBoardArticleDeletedAt(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public void updateBoardArticleProgressCd(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public void updateBoardArticleDeptId(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public void deleteBoardArticle(BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public BoardFileVO selectBoardFile(BoardFileVO paramBoardFileVO)
		    throws Exception;

		  public void updateBoardFileHits(BoardFileVO paramBoardFileVO)
		    throws Exception;

		  public void updateMoveBoardArticle(String paramString1, String paramString2, String[] paramArrayOfString)
		    throws Exception;

		  public void updateCheckedBoardArticleDeletedAt(String paramString1, String paramString2, String[] paramArrayOfString, BoardArticleVO paramBoardArticleVO)
		    throws Exception;

		  public void deleteCheckedBoardArticle(String paramString, String[] paramArrayOfString)
		    throws Exception;

		  public Map<String, Object> selectBoardCommentList(BoardCommentVO paramBoardCommentVO)
		    throws Exception;

		  public BoardCommentVO selectBoardComment(BoardCommentVO paramBoardCommentVO)
		    throws Exception;

		  public String selectBoardCommentMaxReply(BoardCommentVO paramBoardCommentVO)
		    throws Exception;

		  public int selectBoardCommentChildCnt(BoardCommentVO paramBoardCommentVO)
		    throws Exception;

		  public void insertBoardComment(String paramString, BoardCommentVO paramBoardCommentVO)
		    throws Exception;

		  public void updateBoardCommentDeletedAt(BoardCommentVO paramBoardCommentVO)
		    throws Exception;

		  public int selectBoardAnswerListCnt(BoardAnswerVO paramBoardAnswerVO)
		    throws Exception;

		  public List<BoardAnswerVO> selectBoardAnswerList(BoardAnswerVO paramBoardAnswerVO)
		    throws Exception;

		  public BoardAnswerVO selectBoardAnswer(BoardAnswerVO paramBoardAnswerVO)
		    throws Exception;

		  public void insertBoardAnswer(BoardAnswerVO paramBoardAnswerVO)
		    throws Exception;

		  public void updateBoardAnswer(BoardAnswerVO paramBoardAnswerVO)
		    throws Exception;

		  public void updateBoardAnswerDeletedAt(BoardAnswerVO paramBoardAnswerVO)
		    throws Exception;

	

}
