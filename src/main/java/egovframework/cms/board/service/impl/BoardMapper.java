/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.cms.board.vo.BoardAnswerVO;
import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.board.vo.BoardCategoryVO;
import egovframework.cms.board.vo.BoardCommentVO;
import egovframework.cms.board.vo.BoardFileVO;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.login.service.LoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * sample에 관한 데이터처리 매퍼 클래스
 *
 * @author  표준프레임워크센터
 * @since 2014.01.24
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2014.01.24        표준프레임워크센터          최초 생성
 *
 * </pre>
 */
@Mapper("boardMapper")
public interface BoardMapper
{
  public List<BoardInfoVO> selectBoardInfoListAll(BoardInfoVO paramBoardInfoVO)
    throws Exception;

  public BoardInfoVO selectBoardInfo(BoardInfoVO paramBoardInfoVO)
    throws Exception;

  public List<BoardCategoryVO> selectBoardCategoryList(BoardCategoryVO paramBoardCategoryVO)
    throws Exception;

  public Integer selectBoardArticleListCnt(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public Integer selectBoardArticleListTodayCnt(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public List<BoardArticleVO> selectBoardArticleList(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public BoardArticleVO selectBoardArticle(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public BoardArticleVO selectPrevBoardArticle(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public BoardArticleVO selectNextBoardArticle(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public Integer selectBoardArticleMinGroupNo()
    throws Exception;

  public Integer selectBoardArticleChildCnt(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public Integer selectBoardArticleReplyCnt(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public Integer selectBoardArticleDeptAppointAt(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void insertBoardArticle(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void updateBoardArticle(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void updateBoardArticleUploadCnt(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void updateBoardArticleCommentCnt(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public void updateBoardArticleHits(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void updateBoardArticleDeletedAt(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void updateBoardArticleChildOrderNo(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void updateBoardArticleProgressCd(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void updateBoardArticleDeptId(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public void deleteBoardArticle(BoardArticleVO paramBoardArticleVO)
    throws Exception;

  public List<BoardFileVO> selectBoardFileList(BoardFileVO paramBoardFileVO)
    throws Exception;

  public BoardFileVO selectBoardFile(BoardFileVO paramBoardFileVO)
    throws Exception;

  public void insertBoardFile(BoardFileVO paramBoardFileVO)
    throws Exception;

  public void updateBoardFile(BoardFileVO paramBoardFileVO)
    throws Exception;

  public void updateBoardFileDeletedAt(BoardFileVO paramBoardFileVO)
    throws Exception;

  public void deleteBoardFile(BoardFileVO paramBoardFileVO)
    throws Exception;

  public void updateMoveBoardArticle(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void updateMoveBoardFile(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void updateMoveBoardComment(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void updateMoveBoardAnswer(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void updateCheckedBoardArticleDeletedAt(HashMap<String, Object> paramHashMap)
    throws Exception;

  public Integer selectBoardCommentListCnt(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public List<BoardCommentVO> selectBoardCommentList(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public BoardCommentVO selectBoardComment(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public String selectBoardCommentMaxReply(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public Integer selectBoardCommentChildCnt(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public void insertBoardComment(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public void updateBoardCommentDeletedAt(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public void deleteBoardComment(BoardCommentVO paramBoardCommentVO)
    throws Exception;

  public Integer selectBoardAnswerListCnt(BoardAnswerVO paramBoardAnswerVO)
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

  public void updateBoardFileHits(BoardFileVO paramBoardFileVO)
    throws Exception;
}
