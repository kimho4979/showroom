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

@Service("boardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {

	@Resource(name="boardMapper")
	  private BoardMapper boardMapper;

	  @Resource(name="egovBoardArticleIdGnrService")
	  private EgovIdGnrService egovBoardArticleIdGnrService;

	  @Resource(name="egovBoardCommentIdGnrService")
	  private EgovIdGnrService egovBoardCommentIdGnrService;

	  @Resource(name="egovBoardAnswerIdGnrService")
	  private EgovIdGnrService egovBoardAnswerIdGnrService;

	  public List<BoardInfoVO> selectBoardInfoListAll(BoardInfoVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardInfoListAll(vo);
	  }

	  public BoardInfoVO selectBoardInfo(BoardInfoVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardInfo(vo);
	  }

	  public List<BoardCategoryVO> selectBoardCategoryList(BoardCategoryVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardCategoryList(vo);
	  }

	  public List<BoardFileVO> selectBoardFileList(BoardFileVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardFileList(vo);
	  }

	  public int selectBoardArticleListCnt(BoardArticleVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardArticleListCnt(vo).intValue();
	  }

	  public Map<String, Object> selectBoardArticleList(BoardArticleVO vo)
	    throws Exception
	  {
	    List result = new ArrayList();
	    int cnt = this.boardMapper.selectBoardArticleListCnt(vo).intValue();
	    if (cnt > 0) {
	      result = this.boardMapper.selectBoardArticleList(vo);
	    }

	    Map map = new HashMap();

	    map.put("resultList", result);
	    map.put("resultCnt", Integer.toString(cnt));

	    return map;
	  }

	  public List<BoardArticleVO> selectBoardArticleListOnly(BoardArticleVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardArticleList(vo);
	  }

	  public BoardArticleVO selectBoardArticle(BoardArticleVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardArticle(vo);
	  }

	  public int selectBoardArticleChildCnt(BoardArticleVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardArticleChildCnt(vo).intValue();
	  }

	  public void insertBoardArticle(String mode, BoardArticleVO vo, FileVO listThumbFile, List<FileVO> uploadFileList)
	    throws Exception
	  {
	    if ("reply".equals(mode))
	    {
	      BoardArticleVO parentArticleVO = selectBoardArticle(vo);
	      if (parentArticleVO == null)
	      {
	        throw new Exception("board.article.parnts.nodata");
	      }

	      int groupNo = parentArticleVO.getGroupNo();
	      int depthNo = parentArticleVO.getDepthNo() + 1;
	      int orderNo = parentArticleVO.getOrderNo() + 1;

	      BoardArticleVO updateArticleVO = new BoardArticleVO();
	      updateArticleVO.setGroupNo(groupNo);
	      updateArticleVO.setOrderNo(orderNo);
	      this.boardMapper.updateBoardArticleChildOrderNo(updateArticleVO);

	      vo.setGroupNo(groupNo);
	      vo.setDepthNo(depthNo);
	      vo.setOrderNo(orderNo);

	      if ("Y".equals(parentArticleVO.getSecretAt())) {
	        vo.setPassword(parentArticleVO.getPassword());
	      }

	      
	    }
	    else
	    {
	      int minGroupNo = this.boardMapper.selectBoardArticleMinGroupNo().intValue();
	      int groupNo = minGroupNo == 0 ? -1 : minGroupNo - 1;

	      

	      vo.setGroupNo(groupNo);
	    }

	    vo.setArticleId(this.egovBoardArticleIdGnrService.getNextIntegerId());

	    if (listThumbFile != null) {
	      vo.setListThumbFile(listThumbFile.getStreFileNm());
	    }

	    this.boardMapper.insertBoardArticle(vo);

	    if ((uploadFileList != null) && (uploadFileList.size() > 0))
	    {
	      BoardFileVO vo2 = null;
	      for (int i = 0; i < uploadFileList.size(); i++)
	      {
	        vo2 = new BoardFileVO();
	        vo2.setBoardId(vo.getBoardId());
	        vo2.setArticleId(vo.getArticleId());
	        vo2.setFileSn(((FileVO)uploadFileList.get(i)).getFileSn());
	        vo2.setStreFileNm(((FileVO)uploadFileList.get(i)).getStreFileNm());
	        vo2.setOrignlFileNm(((FileVO)uploadFileList.get(i)).getOrignlFileNm());
	        vo2.setFileExtsn(((FileVO)uploadFileList.get(i)).getFileExtsn());
	        vo2.setFileCn(((FileVO)uploadFileList.get(i)).getFileCn());
	        vo2.setFileSize(((FileVO)uploadFileList.get(i)).getFileMg());

	        this.boardMapper.insertBoardFile(vo2);
	      }

	      this.boardMapper.updateBoardArticleUploadCnt(vo);
	    }
	  }

	  public void updateBoardArticle(BoardArticleVO vo, FileVO listThumbFile, List<FileVO> uploadFileList)
	    throws Exception
	  {
	   
		  if ("Y".equals(EgovStringUtil.nullConvert(vo.getDeleteListThumbFile()))) {
		      vo.setListThumbFile("");
		    }
		  	
	    if (listThumbFile != null) {
	      vo.setListThumbFile(listThumbFile.getStreFileNm());
	    }

	    this.boardMapper.updateBoardArticle(vo);

	    if ((vo.getDeleteAtchFile() != null) && (vo.getDeleteAtchFile().length > 0))
	    {
	      BoardFileVO vo2 = null;

	      for (int i = 0; i < vo.getDeleteAtchFile().length; i++)
	      {
	        vo2 = new BoardFileVO();
	        vo2.setBoardId(vo.getBoardId());
	        vo2.setArticleId(vo.getArticleId());
	        vo2.setFileSn(vo.getDeleteAtchFile()[i]);
	        this.boardMapper.updateBoardFileDeletedAt(vo2);
	      }
	    }

	    if ((uploadFileList != null) && (uploadFileList.size() > 0))
	    {
	      BoardFileVO vo2 = null;
	      for (int i = 0; i < uploadFileList.size(); i++)
	      {
	        vo2 = new BoardFileVO();
	        vo2.setBoardId(vo.getBoardId());
	        vo2.setArticleId(vo.getArticleId());
	        vo2.setFileSn(((FileVO)uploadFileList.get(i)).getFileSn());
	        vo2 = this.boardMapper.selectBoardFile(vo2);

	        if (vo2 != null) {
	          vo2.setDeleteId(vo.getUpdateId());
	          vo2.setDeleteIp(vo.getUpdateIp());
	          this.boardMapper.updateBoardFileDeletedAt(vo2);
	        }

	        vo2 = new BoardFileVO();
	        vo2.setBoardId(vo.getBoardId());
	        vo2.setArticleId(vo.getArticleId());
	        vo2.setFileSn(((FileVO)uploadFileList.get(i)).getFileSn());
	        vo2.setStreFileNm(((FileVO)uploadFileList.get(i)).getStreFileNm());
	        vo2.setOrignlFileNm(((FileVO)uploadFileList.get(i)).getOrignlFileNm());
	        vo2.setFileExtsn(((FileVO)uploadFileList.get(i)).getFileExtsn());
	        vo2.setFileCn(((FileVO)uploadFileList.get(i)).getFileCn());
	        vo2.setFileSize(((FileVO)uploadFileList.get(i)).getFileMg());

	        this.boardMapper.insertBoardFile(vo2);
	      }
	    }

	    this.boardMapper.updateBoardArticleUploadCnt(vo);
	  }

	  public void updateBoardArticleHits(BoardArticleVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardArticleHits(vo);
	  }

	  public void updateBoardArticleDeletedAt(BoardArticleVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardArticleDeletedAt(vo);
	  }

	  public void deleteBoardArticle(BoardArticleVO vo)
	    throws Exception
	  {
	    this.boardMapper.deleteBoardArticle(vo);

	    BoardFileVO vo1 = new BoardFileVO();
	    vo1.setBoardId(vo.getBoardId());
	    vo1.setArticleId(vo.getArticleId());
	    this.boardMapper.deleteBoardFile(vo1);

	    BoardCommentVO vo2 = new BoardCommentVO();
	    vo2.setBoardId(vo.getBoardId());
	    vo2.setArticleId(vo.getArticleId());
	    this.boardMapper.deleteBoardComment(vo2);
	  }

	  public BoardFileVO selectBoardFile(BoardFileVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardFile(vo);
	  }

	  public BoardArticleVO selectPrevBoardArticle(BoardArticleVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectPrevBoardArticle(vo);
	  }

	  public BoardArticleVO selectNextBoardArticle(BoardArticleVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectNextBoardArticle(vo);
	  }

	  public void updateMoveBoardArticle(String origBoardId, String targetBoardId, String[] articleIdArr)
	    throws Exception
	  {
	    String originalDirPath = "/uploads/board/" + origBoardId;
	    String targetDirPath = "/uploads/board/" + targetBoardId;

	    HashMap map = new HashMap();
	    map.put("origBoardId", origBoardId);
	    map.put("targetBoardId", targetBoardId);
	    map.put("articleIdArr", articleIdArr);

	    this.boardMapper.updateMoveBoardArticle(map);
	    this.boardMapper.updateMoveBoardComment(map);
	    this.boardMapper.updateMoveBoardAnswer(map);

	    ArrayList fileListAll = new ArrayList();

	    BoardFileVO searchBoardFileVO = new BoardFileVO();
	    searchBoardFileVO.setBoardId(origBoardId);
	    for (int i = 0; i < articleIdArr.length; i++) {
	      searchBoardFileVO.setArticleId(Integer.parseInt(articleIdArr[i]));
	      List flist = this.boardMapper.selectBoardFileList(searchBoardFileVO);
	      if ((flist != null) && (flist.size() > 0)) {
	        for (int j = 0; j < flist.size(); j++) {
	          fileListAll.add((BoardFileVO)flist.get(j));
	        }
	      }
	    }

	    this.boardMapper.updateMoveBoardFile(map);

	    File f = new File(targetDirPath);
	    if (!f.exists()) {
	      f.mkdir();
	    }

	    BoardArticleVO searchBoardArticleVO = new BoardArticleVO();
	    searchBoardArticleVO.setBoardId(targetBoardId);
	    for (int i = 0; i < articleIdArr.length; i++) {
	      searchBoardArticleVO.setArticleId(Integer.parseInt(articleIdArr[i]));
	      BoardArticleVO avo = this.boardMapper.selectBoardArticle(searchBoardArticleVO);
	      if ((avo != null) && 
	        (!"".equals(avo.getListThumbFile()))) {
	        String fileNm = avo.getListThumbFile();
	        File tfile = new File(targetDirPath + "/" + fileNm);
	        File ofile = new File(originalDirPath + "/" + fileNm);
	        ofile.renameTo(tfile);
	      }

	    }

	    for (int i = 0; i < fileListAll.size(); i++) {
	      String fileNm = ((BoardFileVO)fileListAll.get(i)).getStreFileNm();
	      File tfile = new File(targetDirPath + "/" + fileNm);
	      File ofile = new File(originalDirPath + "/" + fileNm);
	      ofile.renameTo(tfile);
	    }
	  }

	  public void updateCheckedBoardArticleDeletedAt(String boardId, String deletedAt, String[] articleIdArr, BoardArticleVO vo)
	    throws Exception
	  {
	    HashMap map = new HashMap();
	    map.put("boardId", boardId);
	    map.put("deletedAt", deletedAt);
	    map.put("articleIdArr", articleIdArr);
	    map.put("deleteMemo", vo.getDeleteMemo());
	    map.put("deleteId", vo.getDeleteId());
	    map.put("deleteIp", vo.getDeleteIp());

	    this.boardMapper.updateCheckedBoardArticleDeletedAt(map);
	  }

	  public void deleteCheckedBoardArticle(String boardId, String[] articleIdArr)
	    throws Exception
	  {
	    if ((articleIdArr != null) && (articleIdArr.length > 0))
	      for (int i = 0; i < articleIdArr.length; i++) {
	        BoardArticleVO vo = new BoardArticleVO();
	        vo.setBoardId(boardId);
	        vo.setArticleId(Integer.parseInt(articleIdArr[i]));
	        deleteBoardArticle(vo);
	      }
	  }

	  public Map<String, Object> selectBoardCommentList(BoardCommentVO vo)
	    throws Exception
	  {
	    List result = new ArrayList();
	    int cnt = this.boardMapper.selectBoardCommentListCnt(vo).intValue();
	    if (cnt > 0) {
	      result = this.boardMapper.selectBoardCommentList(vo);
	    }

	    Map map = new HashMap();

	    map.put("resultList", result);
	    map.put("resultCnt", Integer.toString(cnt));

	    return map;
	  }

	  public BoardCommentVO selectBoardComment(BoardCommentVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardComment(vo);
	  }

	  public String selectBoardCommentMaxReply(BoardCommentVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardCommentMaxReply(vo);
	  }

	  public void insertBoardComment(String mode, BoardCommentVO vo)
	    throws Exception
	  {
	    vo.setCommentId(this.egovBoardCommentIdGnrService.getNextIntegerId());
	    if (!"reply".equals(mode)) {
	      vo.setParntsCommentId(vo.getCommentId());
	    }

	    this.boardMapper.insertBoardComment(vo);
	    this.boardMapper.updateBoardArticleCommentCnt(vo);
	  }

	  public void updateBoardCommentDeletedAt(BoardCommentVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardCommentDeletedAt(vo);
	    this.boardMapper.updateBoardArticleCommentCnt(vo);
	  }

	  public int selectBoardCommentChildCnt(BoardCommentVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardCommentChildCnt(vo).intValue();
	  }

	  public int selectBoardArticleListTodayCnt(BoardArticleVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardArticleListTodayCnt(vo).intValue();
	  }

	  public int selectBoardAnswerListCnt(BoardAnswerVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardAnswerListCnt(vo).intValue();
	  }

	  public List<BoardAnswerVO> selectBoardAnswerList(BoardAnswerVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardAnswerList(vo);
	  }

	  public BoardAnswerVO selectBoardAnswer(BoardAnswerVO vo)
	    throws Exception
	  {
	    return this.boardMapper.selectBoardAnswer(vo);
	  }

	  public void insertBoardAnswer(BoardAnswerVO vo)
	    throws Exception
	  {
	    vo.setAnswerId(this.egovBoardAnswerIdGnrService.getNextIntegerId());
	    this.boardMapper.insertBoardAnswer(vo);
	  }

	  public void updateBoardAnswer(BoardAnswerVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardAnswer(vo);
	  }

	  public void updateBoardAnswerDeletedAt(BoardAnswerVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardAnswerDeletedAt(vo);
	  }

	  public void updateBoardArticleProgressCd(BoardArticleVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardArticleProgressCd(vo);
	  }

	  public void updateBoardArticleDeptId(BoardArticleVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardArticleDeptId(vo);
	  }

	  public int selectBoardArticleDeptAppointAt(String dept1, String dept2)
	    throws Exception
	  {
	    HashMap map = new HashMap();
	    map.put("dept1", dept1);
	    map.put("dept2", dept2);

	    return this.boardMapper.selectBoardArticleDeptAppointAt(map).intValue();
	  }

	  public void updateBoardFileHits(BoardFileVO vo)
	    throws Exception
	  {
	    this.boardMapper.updateBoardFileHits(vo);
	  }

	

	

}
