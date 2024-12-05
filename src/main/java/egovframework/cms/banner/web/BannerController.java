package egovframework.cms.banner.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.banner.service.BannerService;
import egovframework.cms.board.service.BoardInfoService;
import egovframework.cms.board.service.BoardService;
import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.board.vo.BoardFileVO;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.login.service.LoginVO;
import net.coobird.thumbnailator.Thumbnails;

@Controller
public class BannerController {

	@Resource(name = "bannerService")
	private BannerService bannerService;
	
	@Resource(name="boardInfoService")
	private BoardInfoService boardInfoService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;	
	
	/* 리스트 페이지 */
	@RequestMapping(value="/admin/banner/list.do")
	public String list(@ModelAttribute BoardInfoVO infoVO
					 , @ModelAttribute BoardArticleVO searchVO
					 , @RequestParam Map<String, Object> paramMap
					 , ModelMap model) throws Exception {
		
		BoardInfoVO boardInfoVO = boardInfoService.selectBoardInfo(infoVO);
		  
	    model.addAttribute("paramMap", paramMap);
	    model.addAttribute("boardInfoVO", boardInfoVO);
		model.addAttribute("contentPath", "banner/list.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 리스트 */
	@RequestMapping(value="/admin/banner/bannerList.json")
	public String bannerList(@ModelAttribute BoardInfoVO infoVO
						   , @ModelAttribute BoardArticleVO searchVO
						   , @RequestParam Map<String, Object> paramMap
						   , ModelMap model) throws Exception {
		
		try {
			
			searchVO.setFirstIndex(Integer.parseInt(String.valueOf(paramMap.get("start"))));
		    searchVO.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("length"))));
			
		    Map map = bannerService.selectBoardArticleList(searchVO);
		    int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			
		    model.addAttribute("data",  map.get("resultList"));
			model.addAttribute("recordsTotal", totCnt);
			model.addAttribute("recordsFiltered", totCnt);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	/* 등록화면 */
	@RequestMapping(value="/admin/banner/create.do")
	public String create(@ModelAttribute BoardInfoVO infoVO
		               , @ModelAttribute BoardArticleVO searchVO
					   , @RequestParam Map<String, Object> paramMap
					   , ModelMap model) throws Exception {
		
		BoardInfoVO boardInfoVO = boardInfoService.selectBoardInfo(infoVO);
		
		model.addAttribute("boardInfoVO",boardInfoVO);
		model.addAttribute("contentPath", "banner/create.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 수정화면 */
	@RequestMapping(value="/admin/banner/update.do")
	public String update(@ModelAttribute BoardInfoVO infoVO
	           		   , @ModelAttribute BoardArticleVO searchVO
	           		   , @RequestParam Map<String, Object> paramMap
					   , ModelMap model) throws Exception {
		
		BoardInfoVO boardInfoVO = boardInfoService.selectBoardInfo(infoVO);
		
		BoardArticleVO result = boardService.selectBoardArticle(searchVO);
		
		model.addAttribute("boardFileList", getBoardFileList(result));
		model.addAttribute("boardInfoVO",boardInfoVO);
		model.addAttribute("result", result);
		model.addAttribute("contentPath", "banner/update.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 삭제 */
	@RequestMapping(value="/admin/banner/deleteProc.json")
	public String deleteProc(@ModelAttribute BoardArticleVO searchVO
						   , HttpServletRequest pRequest, HttpServletResponse pResponse
						   , @RequestParam Map<String
						   , Object> paramMap, ModelMap model) throws Exception {
		
		boardService.deleteBoardArticle(searchVO);
		bannerService.sortOrderNo(searchVO);
		
		model.addAttribute("result", 1);
		model.addAttribute("boardId", searchVO.getBoardId());
		return "jsonView";
		  	
	}
	
	
	/* 등록 */
	@RequestMapping(value="/admin/banner/insertProc.do")
	public String insertProc(@ModelAttribute BoardInfoVO infoVO
						   , @ModelAttribute BoardArticleVO searchVO
						   , HttpServletRequest pRequest, HttpServletResponse pResponse
						   , @RequestParam Map<String, Object> paramMap
						   , ModelMap model
						   , MultipartHttpServletRequest multiRequest) throws Exception {
		
			
		try {
			
			if(searchVO.getOrderNo() < 0 || multiRequest == null) return "/cms/banner/result";
			
			List<BoardArticleVO> chkOrderNo = bannerService.chkOrderNo(searchVO);
			if(chkOrderNo.size() > 0) {
				bannerService.insOrderNo(searchVO);
			}
			
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			List<FileVO> uploadFileList = uploadBoardArticleAtchFile(infoVO, model, multiRequest);

		    FileVO listThumbFile = uploadBoardArticleListThumbFile(infoVO, model, multiRequest);
		    
		    if (listThumbFile != null) {
		      File image = new File(listThumbFile.getFileStreCours() + listThumbFile.getStreFileNm());
		      if (image.exists()) {
		        try {
		          Thumbnails.of(new File[] { image }).size(300, 300).outputQuality(1.0F).toFile(image);
		        } catch (Exception ex) {
		          ex.fillInStackTrace();
		        }
		      }
		    }
		    searchVO.setWriterId(vo.getId());
		    searchVO.setWriterNm(vo.getName()); 
			boardService.insertBoardArticle("", searchVO, listThumbFile, uploadFileList);
			model.addAttribute("boardId", infoVO.getBoardId());
			model.addAttribute("result", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "/cms/banner/result";
		  	
	}
	
	
	/* 수정 */
	@RequestMapping(value="/admin/banner/updateProc.do")
	public String updateProc(@ModelAttribute BoardInfoVO infoVO
						   , @ModelAttribute BoardArticleVO searchVO, HttpServletRequest pRequest
						   , HttpServletResponse pResponse
						   , @RequestParam Map<String, Object> paramMap
						   , ModelMap model
						   , MultipartHttpServletRequest multiRequest) throws Exception{
		try {
			
			if(searchVO.getOrderNo() < 0 || multiRequest == null) return "/cms/banner/result";
			
			BoardArticleVO article = boardService.selectBoardArticle(searchVO);
			List<BoardArticleVO> chkPosition = bannerService.chkOrderNo(searchVO);
			
			if(chkPosition.size() > 0) {
				if(article.getOrderNo() > searchVO.getOrderNo()) {
					bannerService.insOrderNo(searchVO);
				} else {
					if(searchVO.getOrderNo() == 1) {
						bannerService.insOrderNo(searchVO);
					} else {
						bannerService.uptOrderNo(searchVO);
						bannerService.decOrderNo(searchVO);
					}
				}
			}
		
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			searchVO.setUpdateId(vo.getId());
			searchVO.setWriterId(vo.getId());
		    searchVO.setWriterNm(vo.getName()); 
			
			List<FileVO> uploadFileList = uploadBoardArticleAtchFile(infoVO, model, multiRequest);

		    FileVO listThumbFile = uploadBoardArticleListThumbFile(infoVO, model, multiRequest);
			
		    if (listThumbFile != null) {
		      File image = new File(listThumbFile.getFileStreCours() + listThumbFile.getStreFileNm());
		      if (image.exists()) {
		        try {
		          Thumbnails.of(new File[] { image }).size(300, 300).outputQuality(1.0F).toFile(image);
		        } catch (Exception ex) {
		          ex.fillInStackTrace();
		        }
		      }
		    }
		    
			this.bannerService.updateBoardArticle(searchVO, listThumbFile, uploadFileList);
			this.bannerService.sortOrderNo(searchVO);
			
			model.addAttribute("boardId", infoVO.getBoardId());
			model.addAttribute("result", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "/cms/banner/result";
		  	
	}
	
	
	private List<FileVO> uploadBoardArticleAtchFile(BoardInfoVO boardInfoVO, ModelMap model, MultipartHttpServletRequest multiRequest) throws Exception
	  {
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
	    String uploadPath = savePath + "/uploads/board/" + boardInfoVO.getBoardId() + "/";

	    String atchFileId = "-1";
	    List atchFile = multiRequest.getFiles("attachFile");

	    List atchFileList = null;
	    try {
	      atchFileList = egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, EgovStringUtil.nullConvert(boardInfoVO.getUploadExt()), boardInfoVO.getUploadSize());
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	      e.printStackTrace();
	    }

	    return atchFileList;
	  }
	
	
	 private FileVO uploadBoardArticleListThumbFile(BoardInfoVO boardInfoVO, ModelMap model, MultipartHttpServletRequest multiRequest) throws Exception
	  {
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
	    String uploadPath = savePath + "/uploads/board/" + boardInfoVO.getBoardId() + "/";
		
	    String atchFileId = "-1";
	    List atchFile = multiRequest.getFiles("attachThumbFile");

	    List atchFileList = null;
	    try {
	      atchFileList = this.egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, "gif,jpg,jpeg,png,bmp", 20);
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	      e.printStackTrace();
	    }

	    return (atchFileList != null) && (atchFileList.size() > 0) ? (FileVO)atchFileList.get(0) : null;
	  }
	
	
	private List<BoardFileVO> getBoardFileList(BoardArticleVO boardArticleVO) throws Exception
	  {
	    if (boardArticleVO == null) {
	      return null;
	    }

	    BoardFileVO boardFileVO = new BoardFileVO();
	    boardFileVO.setBoardId(boardArticleVO.getBoardId());
	    boardFileVO.setArticleId(boardArticleVO.getArticleId());

	    return boardService.selectBoardFileList(boardFileVO);
	  }
	
}
