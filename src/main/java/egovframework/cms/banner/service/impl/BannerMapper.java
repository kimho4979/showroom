package egovframework.cms.banner.service.impl;

import java.util.List;

import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("bannerMapper")
public interface BannerMapper
{
  public List<BoardArticleVO> selectBoardArticleList(BoardArticleVO paramBoardArticleVO)
	throws Exception;
			  
  public void updateBoardArticle(BoardArticleVO paramBoardArticleVO)
	throws Exception;
  
  public List<BoardArticleVO> chkOrderNo(BoardArticleVO searchVO)
  	throws Exception;
  
  public void insOrderNo(BoardArticleVO searchVO) 
	throws Exception;
  
  public void uptOrderNo(BoardArticleVO searchVO) 
	throws Exception;
  
  public void decOrderNo(BoardArticleVO searchVO) 
	throws Exception;
  
  public void sortOrderNo(BoardArticleVO searchVO) 
	throws Exception;
  

}
