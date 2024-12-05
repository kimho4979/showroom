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
package egovframework.cms.login.web;

import java.util.List;

import egovframework.cms.login.service.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
public class LoginController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	
	@RequestMapping(value = "/admin/login.do")
	public String login(HttpServletRequest request, ModelMap model) throws Exception {
		
		System.out.println("로그인");
		
		
		return "/cms/login/login";
	}
	
	@RequestMapping(value = "/admin/loginProc.do")
	public String loginProc(HttpServletRequest request, ModelMap model) throws Exception {
		
		System.out.println("로그인 실행");
		System.out.println("채동호 실행");
		
		return "/cms/login/loginProc";
	}
	
	
	@RequestMapping(value = "/admin/main.do")
	public String main(HttpServletRequest request, ModelMap model) throws Exception {
		
		System.out.println("메인");
		
		LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		
		
		model.addAttribute("contentPath","main/main.jsp");
		
		return "/cms/layout/defaultLayout";
	}

	@RequestMapping(value = "/admin/sr/list.do")
	public String srList(HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		model.addAttribute("contentPath","sr/list.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	

}
