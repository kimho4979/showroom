package egovframework.front.board.service;

import java.util.List;
import java.util.Map;





import egovframework.front.board.vo.FrontBoardArticleVO;
import egovframework.front.board.vo.FrontBoardFileVO;
import egovframework.front.board.vo.FrontBoardInfo;
import egovframework.front.board.vo.FrontBoardInfoVO;

public interface FrontBoardService {
	public FrontBoardInfoVO getFrontBoardInfo(FrontBoardInfoVO paramFrontBoardInfoVO)
		throws Exception;
	
	public Map<String, Object> getFrontBoardArticleList(FrontBoardArticleVO paramFrontBoardArticleVO)
		throws Exception;

	public int getFrontBoardArticleListCnt(FrontBoardArticleVO paramFrontBoardArticleVO)
		throws Exception;

	public FrontBoardArticleVO getFrontBoardArticle(FrontBoardArticleVO paramFrontBoardArticleVO)
		throws Exception;
	
	public List<FrontBoardFileVO> getFrontBoardFileList(Map<String, Object> paramMap)
		throws Exception;

	public FrontBoardFileVO getFrontBoardFile(FrontBoardFileVO paramFrontBoardFileVO)
		throws Exception;
	
	public void updateFrontBoardArticleHits(FrontBoardArticleVO paramFrontBoardArticleVO)
		throws Exception;
}
