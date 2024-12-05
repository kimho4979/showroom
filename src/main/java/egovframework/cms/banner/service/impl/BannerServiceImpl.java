package egovframework.cms.banner.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cms.banner.service.BannerService;
import egovframework.cms.board.service.impl.BoardMapper;
import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.board.vo.BoardFileVO;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.FileVO;

@Service("bannerService")
public class BannerServiceImpl implements BannerService {
	
	@Resource(name="bannerMapper")
	private BannerMapper bannerMapper;
	
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	
	
	@Override
	public Map<String, Object> selectBoardArticleList(BoardArticleVO vo)
		    throws Exception
		  {
		    List result = new ArrayList();
		    int cnt = 0;
		    try {
		    	cnt = this.boardMapper.selectBoardArticleListCnt(vo).intValue();
		    } catch (Exception e) {
		    	
		    }
		    
		    if (cnt > 0) {
		      result = this.bannerMapper.selectBoardArticleList(vo);
		    }

		    Map map = new HashMap();

		    map.put("resultList", result);
		    map.put("resultCnt", Integer.toString(cnt));

		    return map;
		  }
	
	@Override
	public void updateBoardArticle(BoardArticleVO vo, FileVO listThumbFile, List<FileVO> uploadFileList)
		    throws Exception
		  {
		   
			  if ("Y".equals(EgovStringUtil.nullConvert(vo.getDeleteListThumbFile()))) {
			      vo.setListThumbFile("");
			    }
			  	
		    if (listThumbFile != null) {
		      vo.setListThumbFile(listThumbFile.getStreFileNm());
		    }

		    this.bannerMapper.updateBoardArticle(vo);

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
	
	
	@Override
	public List<BoardArticleVO> chkOrderNo(BoardArticleVO searchVO) throws Exception {
		return this.bannerMapper.chkOrderNo(searchVO);
	}
	
	
	@Override
	public void insOrderNo(BoardArticleVO searchVO) throws Exception {
		this.bannerMapper.insOrderNo(searchVO);
	}
	
	
	@Override
	public void uptOrderNo(BoardArticleVO searchVO) throws Exception {
		this.bannerMapper.uptOrderNo(searchVO);
	}
	
	
	@Override
	public void decOrderNo(BoardArticleVO searchVO) throws Exception {
		this.bannerMapper.decOrderNo(searchVO);
	}
	
	
	@Override
	public void sortOrderNo(BoardArticleVO searchVO) throws Exception {
		this.bannerMapper.sortOrderNo(searchVO);
	}
	
}
