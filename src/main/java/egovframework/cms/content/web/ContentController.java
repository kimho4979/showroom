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
package egovframework.cms.content.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.cms.content.service.ContentService;
import egovframework.cms.content.service.ContentVO;
import egovframework.cms.login.service.LoginVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
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

@Controller
public class ContentController {

	/** EgovSampleService */
	@Resource(name = "contentService")
	private ContentService contentService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name="egovContentIdGnrService")
	private EgovIdGnrService egovContentIdGnrService;

	/* 리스트 페이지  */
	@RequestMapping(value = "/admin/content/list.do")
	public String list(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		/*페이징*/
		
		model.addAttribute("contentPath","content/list.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 리스트 */
	@RequestMapping(value="/admin/content/contentList.json")
	public String contentList(HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		
		
		System.out.println(paramMap.toString());
		
		paramMap.put("firstIndex", paramMap.get("start"));
		paramMap.put("recordCountPerPage", paramMap.get("length"));
		
		List<ContentVO> resultList =  contentService.getContentList(paramMap);
		int totCnt = contentService.getContentListCnt(paramMap);
		
		
		
		/*
		model.addAttribute("resultList", resultList);
		model.addAttribute("totCnt", totCnt);*/
		model.addAttribute("data", resultList);
		model.addAttribute("recordsTotal", totCnt);
		model.addAttribute("recordsFiltered", totCnt);
		
		return "jsonView";
		  	
	}
	
	/* 등록화면  */
	@RequestMapping(value = "/admin/content/create.do")
	public String create(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		
		model.addAttribute("contentPath","content/create.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	/* 수정화면 */
	@RequestMapping(value = "/admin/content/update.do")
	public String update(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		ContentVO result = contentService.getContent(paramMap);
		
		model.addAttribute("result", result);
		
		model.addAttribute("contentPath","content/update.jsp");
		return "/cms/layout/defaultLayout";
	}
	
	/* 삭제 */
	@RequestMapping(value="/admin/content/deleteProc.json")
	public String deleteProc(HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		
		int result = contentService.delete(paramMap);
		
		model.addAttribute("result", result);
		return "jsonView";
		  	
	}
	
	/* 등록 */
	@RequestMapping(value="/admin/content/insertProc.json")
	public String insertProc(HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		try {
			
		
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			paramMap.put("contentId", egovContentIdGnrService.getNextIntegerId());
			paramMap.put("userId", vo.getId());
			
			int result = contentService.insert(paramMap);
			model.addAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "jsonView";
		  	
	}
	
	
	/* 수정 */
	@RequestMapping(value="/admin/content/updateProc.json")
	public String updateProc(HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		try {
			
		
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			paramMap.put("userId", vo.getId());
			
			int result = contentService.update(paramMap);
			model.addAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "jsonView";
		  	
	}

	

}
