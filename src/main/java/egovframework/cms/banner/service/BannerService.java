package egovframework.cms.banner.service;

import java.util.List;
import java.util.Map;

import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.cmm.service.FileVO;

public interface BannerService {
	
	public Map<String, Object> selectBoardArticleList(BoardArticleVO paramBoardArticleVO) throws Exception;
	
	public void updateBoardArticle(BoardArticleVO paramBoardArticleVO, FileVO paramFileVO, List<FileVO> paramList) throws Exception;
	
	public List<BoardArticleVO> chkOrderNo(BoardArticleVO searchVO) throws Exception;
	
	public void insOrderNo(BoardArticleVO searchVO) throws Exception;
	
	public void uptOrderNo(BoardArticleVO searchVO) throws Exception;
	
	public void decOrderNo(BoardArticleVO searchVO) throws Exception;
	
	public void sortOrderNo(BoardArticleVO searchVO) throws Exception;

}
