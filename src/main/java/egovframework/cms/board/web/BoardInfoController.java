
package egovframework.cms.board.web;


import egovframework.cms.board.service.BoardInfoService;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.login.service.LoginVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import java.util.HashMap;
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

@Controller
public class BoardInfoController {

  @Resource(name="propertiesService")
  protected EgovPropertyService propertyService;

  @Resource(name="boardInfoService")
  private BoardInfoService boardInfoService;
  
  @Resource(name="egovBoardIdGnrService")
  private EgovIdGnrService egovBoardIdGnrService;

  
  /* 리스트 페이지  */
  @RequestMapping(value = "/admin/boardInfo/list.do")
  public String list(@ModelAttribute BoardInfoVO searchVO, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		model.addAttribute("contentPath","boardInfo/list.jsp");
		
		return "/cms/layout/defaultLayout";
  }

 
  @RequestMapping(value = "/admin/boardInfo/boardInfoList.json")
  public String boardInfoList(@ModelAttribute BoardInfoVO searchVO, @RequestParam Map<String, Object> paramMap, ModelMap model)
    throws Exception
  {
    
	  try {
		  
		  searchVO.setFirstIndex(Integer.parseInt(String.valueOf(paramMap.get("start"))));
		    searchVO.setRecordCountPerPage(Integer.parseInt(String.valueOf(paramMap.get("length"))));
		 
			Map map = boardInfoService.selectBoardInfoList(searchVO);
		    int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		    
		    model.addAttribute("data",  map.get("resultList"));
			model.addAttribute("recordsTotal", totCnt);
			model.addAttribute("recordsFiltered", totCnt);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
   
	
	return "jsonView";
  }

  @RequestMapping(value = "/admin/boardInfo/create.do")
  public String create(@ModelAttribute BoardInfoVO searchVO, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
    
	  model.addAttribute("contentPath","boardInfo/create.jsp");

	  return "/cms/layout/defaultLayout";
  }
  
  
  /* 수정화면 */
	@RequestMapping(value = "/admin/boardInfo/update.do")
	public String update(@ModelAttribute BoardInfoVO searchVO,  @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		BoardInfoVO result = boardInfoService.selectBoardInfo(searchVO);
		
		model.addAttribute("result", result);
		
		model.addAttribute("contentPath","boardInfo/update.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	/* 삭제 */
	@RequestMapping(value="/admin/boardInfo/deleteProc.json")
	public String deleteProc(@ModelAttribute BoardInfoVO searchVO,  HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		
		int result = boardInfoService.deleteBoardInfo(searchVO);
		
		model.addAttribute("result", result);
		return "jsonView";
		  	
	}
	
	
	/* 등록 */
	@RequestMapping(value="/admin/boardInfo/insertProc.json")
	public String insertProc(@ModelAttribute BoardInfoVO searchVO,  HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		try {
			
		
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			
			searchVO.setRegistId(vo.getId());
			searchVO.setBoardId(String.valueOf(egovBoardIdGnrService.getNextIntegerId()));
			
			int result = boardInfoService.insertBoardInfo(searchVO);
			model.addAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "jsonView";
		  	
	}
	
	
	/* 수정 */
	@RequestMapping(value="/admin/boardInfo/updateProc.json")
	public String updateProc(HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model, @ModelAttribute BoardInfoVO searchVO) throws Exception{
		try {
			
		
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			searchVO.setUpdateId(vo.getId());
			
			int result = boardInfoService.updateBoardInfo(searchVO);
			model.addAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "jsonView";
		  	
	}
}

