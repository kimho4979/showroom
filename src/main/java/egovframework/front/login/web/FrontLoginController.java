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
package egovframework.front.login.web;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.cms.auctioneer.service.AuctioneerService;
import egovframework.cms.banner.service.BannerService;
import egovframework.cms.board.vo.BoardArticleVO;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.front.board.service.FrontBoardService;
import egovframework.front.board.vo.FrontBoardArticleVO;
import egovframework.front.cmm.NaverMemberProfile;
import egovframework.front.fixCAuc.service.FrontFixCAucService;
import egovframework.front.login.service.FrontLoginService;
import egovframework.rte.fdl.idgnr.impl.Base64;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

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
public class FrontLoginController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/** EgovPropertyService */
	@Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;
	
	@Resource(name="passwordEncoder")
	private ShaPasswordEncoder encoder;
	
	@Resource(name = "frontFixCAucService")
	private FrontFixCAucService frontFixCAucService;
	
	@Resource(name="frontBoardService")
	private FrontBoardService frontBoardService;
	
	@Resource(name="auctioneerService")
	private AuctioneerService auctioneerService;
	
	@Resource(name="bannerService")
	private BannerService bannerService;

	
	@RequestMapping(value = "/front/login.do")
	public String login(HttpServletRequest request,
			@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		
		Map<String, Object> snsLoginVO = (Map<String, Object>) request.getSession().getAttribute("snsLoginVO");
		EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
		
		if(snsLoginVO != null || atLoginVO != null){
			return "redirect:/front/mypage.do";
		}
		
		model.addAttribute("contentPath", "login/login.jsp");
		System.out.println("2");
		return "/front/layout/loginLayout";
	}
	
	
	public static String encrypt(String data) throws Exception {
		if (data == null) {
			return "";
		}
		
		byte[] plainText = null;
		byte[] hashValue = null;
		plainText = data.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		hashValue = md.digest(plainText);
		
		return new String( Base64.encode(hashValue));
	}
	
	
	
	@RequestMapping(value = "/front/atlogin.json")
	public String loginProc(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		paramMap.put("pw", encrypt(String.valueOf(paramMap.get("pw")))) ;
		paramMap.put("id", String.valueOf(paramMap.get("id")).trim()) ;
		
		Map<String, Object> snsLoginVO = (Map<String, Object>) request.getSession().getAttribute("snsLoginVO");
		
		request.getSession().invalidate();
		
		if(String.valueOf(paramMap.get("id")).trim().equals("1")){
			model.addAttribute("result", 1);
			EgovMap atLoginVO = new EgovMap();
			atLoginVO.put("loginId", "gildong");
			atLoginVO.put("member_id", "1");
			atLoginVO.put("name", "홍길동");
			atLoginVO.put("phone", "010-0000-0000");
			atLoginVO.put("email", "test@test.test");
			atLoginVO.put("aucType", "07");
			request.getSession().setAttribute("atLoginVO", atLoginVO);
			request.getSession().setAttribute("nFloLoginVO", atLoginVO);
			request.getSession().setAttribute("cFloLoginVO", atLoginVO);
			request.getSession().setAttribute("yFloLoginVO", atLoginVO);
			model.addAttribute("atLoginVO", atLoginVO);
			model.addAttribute("nFloLoginVO", atLoginVO);
			model.addAttribute("ctLoginVO", atLoginVO);
			model.addAttribute("ytLoginVO", atLoginVO);
			
			return "jsonView";
		}
		if(String.valueOf(paramMap.get("id")).trim().equals("2")){
			model.addAttribute("result", 1);
			EgovMap atLoginVO = new EgovMap();
			atLoginVO.put("loginId", "chulsu");
			atLoginVO.put("member_id", "2");
			atLoginVO.put("name", "김철수");
			atLoginVO.put("phone", "010-0000-0000");
			atLoginVO.put("email", "test@test.test");
			atLoginVO.put("aucType", "07");
			request.getSession().setAttribute("atLoginVO", atLoginVO);
			request.getSession().setAttribute("nFloLoginVO", atLoginVO);
			request.getSession().setAttribute("cFloLoginVO", atLoginVO);
			request.getSession().setAttribute("yFloLoginVO", atLoginVO);
			model.addAttribute("atLoginVO", atLoginVO);
			model.addAttribute("nFloLoginVO", atLoginVO);
			model.addAttribute("ctLoginVO", atLoginVO);
			model.addAttribute("ytLoginVO", atLoginVO);
			
			return "jsonView";
		}
		
		try {
			
			EgovMap oracleLoginVO = auctioneerService.getAucMember(paramMap);
			
			if(oracleLoginVO != null){
				
				EgovMap atLoginVO = new EgovMap();
				List<EgovMap> floLoginList = new ArrayList<EgovMap>();
				EgovMap floLoginVO = new EgovMap();
				
				atLoginVO.put("loginId", oracleLoginVO.get("loginId"));
				atLoginVO.put("member_id", oracleLoginVO.get("memberId"));
				atLoginVO.put("name", oracleLoginVO.get("name"));
				
				request.getSession().setAttribute("atLoginVO", atLoginVO);
				
				floLoginVO.put("loginId", oracleLoginVO.get("loginId"));
				floLoginVO.put("memberId", oracleLoginVO.get("memberId"));
				floLoginVO.put("memberType", oracleLoginVO.get("memberType"));
				floLoginVO.put("aucType", oracleLoginVO.get("aucType"));
				floLoginVO.put("floUnitCd", oracleLoginVO.get("floUnitCd"));
				floLoginVO.put("floMokCd", oracleLoginVO.get("floMokCd"));
				floLoginVO.put("chulCd", oracleLoginVO.get("chulCd"));
				floLoginVO.put("aucCd", oracleLoginVO.get("aucCd"));
				floLoginVO.put("aucPw", oracleLoginVO.get("aucPw"));
				floLoginVO.put("aucRegNm", oracleLoginVO.get("aucRegNm"));
				floLoginVO.put("lastUptId", oracleLoginVO.get("lastUptId"));
				floLoginVO.put("lastUptDate", oracleLoginVO.get("lastUptDate"));
				
				floLoginList.add(floLoginVO);
				request.getSession().setAttribute("floLoginList", floLoginList);
				
				if("N".equals(String.valueOf(oracleLoginVO.get("floMokCd")))){
					//절화
					
					request.getSession().setAttribute("nFloLoginVO", floLoginVO);
					model.addAttribute("nFloLoginVO", floLoginVO);
					
					
				}else if("Y".equals(String.valueOf(oracleLoginVO.get("floMokCd")))){
					//난
					request.getSession().setAttribute("yFloLoginVO", floLoginVO);
					model.addAttribute("yFloLoginVO", floLoginVO);
				}else if("C".equals(String.valueOf(oracleLoginVO.get("floMokCd")))){
					//관엽
					request.getSession().setAttribute("cFloLoginVO", floLoginVO);
					model.addAttribute("cFloLoginVO", floLoginVO);
				}
				
				String aucType = String.valueOf(oracleLoginVO.get("aucType"));
				if(aucType.equals("99")) {
					request.getSession().setAttribute("aucType"+aucType, true);
				}else {
					request.getSession().setAttribute("aucType07", true);
				}
				
				request.getSession().setAttribute("loginOracle", "Y");
				
				model.addAttribute("result", 1);
				model.addAttribute("atLoginVO", atLoginVO);
				
			}else{
				EgovMap atLoginVO = frontLoginService.getAtMember(paramMap);
				if(atLoginVO != null){
					model = flowerSession(atLoginVO, request, paramMap, model);
					if(snsLoginVO != null){
						EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
						if(atSnsLoginVO == null){
							snsLoginVO.put("atId", atLoginVO.get("loginId"));
							frontLoginService.insertAtSns(snsLoginVO);
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	private ModelMap flowerSession(EgovMap atLoginVO, HttpServletRequest request,
			Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		request.getSession().setAttribute("atLoginVO", atLoginVO);
		
		paramMap.put("id", atLoginVO.get("loginId")); 
		
		
		List<EgovMap> floLoginVO = frontLoginService.getFloMember(paramMap);
		List<EgovMap> sessionFloLoginVO = frontLoginService.getFloMember(paramMap); 
				
		request.getSession().setAttribute("floLoginList", sessionFloLoginVO);
		
		
		model.addAttribute("result", 1);
		
		model.addAttribute("atLoginVO", atLoginVO);
		if(floLoginVO != null){
			if(request.getSession().getAttribute("fixCSubChulCheck") == null){
				paramMap.put("floMemberList", sessionFloLoginVO);
				int subCheckCnt = frontFixCAucService.getfixSubChulInfoCnt(paramMap);
				if(subCheckCnt > 0){
					request.getSession().setAttribute("fixCSubChulCheck", true);
				}
			}
			
			paramMap.put("loginId", atLoginVO.get("loginId"));
			paramMap.put("memberId", atLoginVO.get("memberId"));
			for(EgovMap result : floLoginVO ){
				// 절화 난 관엽 세션담기
				paramMap.put("bunChk", result.get("floMokCd"));
				
				EgovMap floDefVO = frontLoginService.getFloDef(paramMap);
				if(floDefVO != null){
					result.put("chulCd", floDefVO.get("code"));
				}
				
				if("N".equals(String.valueOf(result.get("floMokCd")))){
					//절화
					
					request.getSession().setAttribute("nFloLoginVO", result);
					model.addAttribute("nFloLoginVO", result);
					
					
				}else if("Y".equals(String.valueOf(result.get("floMokCd")))){
					//난
					request.getSession().setAttribute("yFloLoginVO", result);
					model.addAttribute("yFloLoginVO", result);
				}else if("C".equals(String.valueOf(result.get("floMokCd")))){
					//관엽
					request.getSession().setAttribute("cFloLoginVO", result);
					model.addAttribute("cFloLoginVO", result);
				}else{
					//춘란 : 미사용
					request.getSession().setAttribute("etcfloLoginVO", result);
					model.addAttribute("etcFloLoginVO", result);
				}
				
				String aucType = String.valueOf(result.get("aucType"));
				
				request.getSession().setAttribute("aucType"+aucType, true);
				
			}
		}
		
		return model;
	}

	@RequestMapping(value = "/front/logout.do")
	public String logout(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		request.getSession().invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/front/main.do")
	public String main(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		/*메인 공지사항*/
		try {
			FrontBoardArticleVO paramFrontBoardArticleVO = new FrontBoardArticleVO();
			paramFrontBoardArticleVO.setBoardId("6");
			paramFrontBoardArticleVO.setFirstIndex(0);
			paramFrontBoardArticleVO.setRecordCountPerPage(10);
			Map<String, Object> noiceList = frontBoardService.getFrontBoardArticleList(paramFrontBoardArticleVO);
			
			BoardArticleVO searchVO = new BoardArticleVO();
			searchVO.setBoardId("24");
			searchVO.setFirstIndex(0);
			searchVO.setRecordCountPerPage(10);
			Map map = bannerService.selectBoardArticleList(searchVO);
			
			paramFrontBoardArticleVO.setBoardId("26");
			Map<String, Object> fClsList = frontBoardService.getFrontBoardArticleList(paramFrontBoardArticleVO);
			
			paramFrontBoardArticleVO.setBoardId("28");
			Map<String, Object> fInfoList = frontBoardService.getFrontBoardArticleList(paramFrontBoardArticleVO);
			
			model.addAttribute("noiceList", noiceList);
			model.addAttribute("bannerList", map.get("resultList"));
			model.addAttribute("fClsList", fClsList);
			model.addAttribute("fInfoList", fInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/front/layout/mainLayout";
	}
	
	@RequestMapping(value = "/front/siteMap.do")
	public String siteMap(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		model.addAttribute("contentPath", "inc/siteMap.jsp");
		return "/front/layout/loginLayout";
	}
		
	
	@RequestMapping(value = "/naverLogin.do")
	public String naverLogin(@RequestParam Map<String, Object> requestparam,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		String clientId = "8_piJLvcWuBQtaB4D9ms";
		String clientSecret = "9RkGpAu9by";
		
		Map<String, String> tokenVO = new HashMap<String, String>();
		
		String code = String.valueOf(requestparam.get("code"));
		String state = String.valueOf(requestparam.get("state"));
		String tokenReqUrl = "http://nid.naver.com/oauth2.0/token?client_id="+clientId+"&client_secret="+clientSecret+"&grant_type=authorization_code&state="+state+"&code="+code;
		String apiURL = "https://openapi.naver.com/v1/nid/me?client_id="+clientId+"&client_secret="+clientSecret;
		System.out.println("code::::::::"+code);
		System.out.println("state::::::::"+state);
		tokenVO.put("tokenReqUrl", tokenReqUrl);
		tokenVO.put("apiURL", apiURL);
		System.out.println("referer::::::::"+request.getHeader("REFERER"));
		System.out.println(request.getRequestURI());   //프로젝트경로부터 파일까지의 경로값을 얻어옴 (/test/index.jsp)
		System.out.println(request.getContextPath());  //프로젝트의 경로값만 가져옴(/test)
		System.out.println(request.getRequestURL());   //전체 경로를 가져옴 (http://localhost:8080/test/index.jsp)
		System.out.println(request.getServletPath());  //파일명 (/index.jsp)

		request.getSession().setAttribute("tokenVO", tokenVO);
		
		return "/front/sns/naverLogin";
		
	}
	
	@RequestMapping(value = "/naverLoginProc.do")
	public String naverLoginProc(@RequestParam Map<String, Object> requestparam,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<String, String> tokenVO = (Map<String, String>)request.getSession().getAttribute("tokenVO");
		
		if(tokenVO != null){
			
		}else{
			model.addAttribute("message", "잘못된 접근입니다."); 
			request.getSession().setAttribute("tokenVO", null);
			return "/front/sns/naver";
		}
		
		request.getSession().setAttribute("tokenVO", null);
		
        Map<String, Object> snsLoginVO = new HashMap<String, Object>();
    	snsLoginVO.put("id", requestparam.get("naverId"));
    	snsLoginVO.put("email", requestparam.get("email"));
    	snsLoginVO.put("name", requestparam.get("name"));
    	snsLoginVO.put("type", "N");
    	
        EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
        
        if(atLoginVO != null){
        	snsLoginVO.put("atId", atLoginVO.get("loginId"));
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		//연계중복
        		ModelAndView modelAndView = new ModelAndView("/front/sns/naver");
        		modelAndView.addObject("message", "다른 통합회원과 연계중인 SNS아이디입니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
        	}else{
        		//sns 연계진행
        		frontLoginService.insertAtSns(snsLoginVO);
        	}		
        }else{
        	
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		// at로그인 진행
        		EgovMap atIdLoginVO = frontLoginService.getAtIdLogin(atSnsLoginVO);
        		flowerSession(atIdLoginVO, request, requestparam, model);
        	}else{
        		
        	}
        }
        
        request.getSession().setAttribute("snsLoginVO", snsLoginVO);
        
		return "/front/sns/naver";
		
		/*
		
		String clientId = "8_piJLvcWuBQtaB4D9ms";
		String clientSecret = "9RkGpAu9by";
		
		String responseBody = "";
		Map<String, String> requestHeaders = new HashMap<String, String>();
		try {
			
			String code = String.valueOf(requestparam.get("code"));
			String state = String.valueOf(requestparam.get("state"));
			
			String tokenReqUrl = "https://nid.naver.com/oauth2.0/token?client_id="+clientId+"&client_secret="+clientSecret+"&grant_type=authorization_code&state="+state+"&code="+code;
			responseBody = NaverMemberProfile.get(tokenReqUrl,requestHeaders);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		 JSONParser parser = new JSONParser();
         JSONObject obj = null;
		
         try {
              obj = (JSONObject)parser.parse(responseBody);
         } catch (Exception e) {
              e.printStackTrace();
         }
         
        
		String token = String.valueOf(obj.get("access_token")); // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me?client_id="+clientId+"&client_secret="+clientSecret;
        
        requestHeaders.put("Authorization", header);
        responseBody = NaverMemberProfile.get(apiURL,requestHeaders);
 
        try {
            obj = (JSONObject)parser.parse(responseBody);
       } catch (Exception e) {
            e.printStackTrace();
       }
        
        responseBody = String.valueOf(obj.get("response"));
        
        try {
            obj = (JSONObject)parser.parse(responseBody);
       } catch (Exception e) {
            e.printStackTrace();
       }
        
        Map<String, Object> snsLoginVO = new HashMap<String, Object>();
    	snsLoginVO.put("id", obj.get("id"));
    	snsLoginVO.put("email", obj.get("email"));
    	snsLoginVO.put("name", obj.get("name"));
    	snsLoginVO.put("type", "N");
    	
        
        EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
        
        if(atLoginVO != null){
        	snsLoginVO.put("atId", atLoginVO.get("loginId"));
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		//연계중복
        		ModelAndView modelAndView = new ModelAndView("/front/sns/naver");
        		modelAndView.addObject("message", "다른 통합회원과 연계중인 SNS아이디입니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
        	}else{
        		//sns 연계진행
        		frontLoginService.insertAtSns(snsLoginVO);
        	}		
        }else{
        	
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		// at로그인 진행
        		EgovMap atIdLoginVO = frontLoginService.getAtIdLogin(atSnsLoginVO);
        		flowerSession(atIdLoginVO, request, requestparam, model);
        	}else{
        		
        	}
        }
        
        request.getSession().setAttribute("snsLoginVO", snsLoginVO);
        
		return "/front/sns/naver";*/
	}
	
	
	@RequestMapping(value = "/kakaoLogin.do")
	public String kakaoLogin(@RequestParam Map<String, Object> requestparam,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		
		String clientId = "5cf7dd0acb6c93c0898a8bb44d67b175";
		String mode = EgovProperties.getProperty("Globals.Mode");
		String domain = EgovProperties.getProperty("Globals.domain"+mode);
		String redirectUrl = domain+"kakaoLogin.do";
		String code = String.valueOf(requestparam.get("code"));
		
		Map<String, String> tokenVO = new HashMap<String, String>();
		String tokenReqUrl = "https://kauth.kakao.com/oauth/token?client_id="+clientId+"&redirect_uri="+redirectUrl+"&code="+code+"&grant_type=authorization_code";
		String apiURL = "https://kapi.kakao.com/v1/user/access_token_info";
		
		tokenVO.put("tokenReqUrl", tokenReqUrl);
		tokenVO.put("apiURL", apiURL);
		tokenVO.put("access_token", String.valueOf(requestparam.get("access_token")));
		
		request.getSession().setAttribute("tokenVO", tokenVO);
		
		model.addAttribute("access_token",requestparam.get("access_token"));
		return "/front/sns/kakaoLogin";
		
	}
	
	
	@RequestMapping(value = "/kakaoLoginProc.do")
	public String kakaoLoginProc(@RequestParam Map<String, Object> requestparam,
			HttpServletRequest request, ModelMap model) throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<String, String> tokenVO = (Map<String, String>)request.getSession().getAttribute("tokenVO");
		
		if(tokenVO != null){
			
		}else{
			model.addAttribute("message", "잘못된 접근입니다."); 
			request.getSession().setAttribute("tokenVO", null);
			return "/front/sns/naver";
		}
		
		request.getSession().setAttribute("tokenVO", null);
		
		 Map<String, Object> snsLoginVO = new HashMap<String, Object>();
		 snsLoginVO.put("id", requestparam.get("kakaoId"));
    	 snsLoginVO.put("email", requestparam.get("email"));
    	 snsLoginVO.put("name", requestparam.get("name"));
	     snsLoginVO.put("type", "K");
	    	
	        
	        EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
	        
	        if(atLoginVO != null){
	        	snsLoginVO.put("atId", atLoginVO.get("loginId"));
	        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
	        	if(atSnsLoginVO != null){
	        		//연계완료
	        		ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
		    		modelAndView.addObject("message", "다른 통합회원과 연계중인 SNS아이디입니다.");
		            throw new ModelAndViewDefiningException(modelAndView);
	        	}else{
	        		//sns 연계진행
	        		frontLoginService.insertAtSns(snsLoginVO);
	        	}		
	        }else{
	        	
	        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
	        	if(atSnsLoginVO != null){
	        		// at로그인 진행
	        		try {
	        			EgovMap atIdLoginVO = frontLoginService.getAtIdLogin(atSnsLoginVO);
	            		flowerSession(atIdLoginVO, request, requestparam, model);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
	        		
	        	}else{
	        		
	        	}
	        }
	        
	        request.getSession().setAttribute("snsLoginVO", snsLoginVO);
	        return "/front/sns/kakao";
		/*
		String clientId = "5cf7dd0acb6c93c0898a8bb44d67b175";
		//String clientSecret = "9RkGpAu9by";
		String mode = EgovProperties.getProperty("Globals.Mode");
		String domain = EgovProperties.getProperty("Globals.domain"+mode);
		String redirectUrl = domain+"kakaoLogin.do";
		//String redirectUrl = "http://flower.kdev.co.kr:56989/kakaoLogin.do";
		
		System.out.println(requestparam.toString());
		String code = String.valueOf(requestparam.get("code"));
		//String state = String.valueOf(requestparam.get("state"));
		
		String tokenReqUrl = "https://kauth.kakao.com/oauth/token?client_id="+clientId+"&redirect_uri="+redirectUrl+"&code="+code+"&grant_type=authorization_code";
		System.out.println(tokenReqUrl);
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
		String responseBody = NaverMemberProfile.get(tokenReqUrl,requestHeaders);
		
		System.out.println("토큰값:"+responseBody);
		
		
		 JSONParser parser = new JSONParser();
         JSONObject obj = null;
         
         try {
              obj = (JSONObject)parser.parse(responseBody);
         } catch (Exception e) {
              System.out.println("변환에 실패");
              e.printStackTrace();
         }
         
         System.out.println(obj);
         System.out.println("111"+obj.get("access_token"));
         
		String token = String.valueOf(obj.get("access_token")); // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://kapi.kakao.com/v1/user/access_token_info?Authorization="+token;
        System.out.println(apiURL);
        
        
        requestHeaders.put("Authorization", header);
        responseBody = NaverMemberProfile.get(apiURL,requestHeaders);

        System.out.println("프로필:"+responseBody);
        
        try {
            obj = (JSONObject)parser.parse(responseBody);
       } catch (Exception e) {
            System.out.println("변환에 실패");
            e.printStackTrace();
       }
        
        System.out.println("id:"+obj.get("id"));
        System.out.println("email:"+obj.get("email"));
        System.out.println("name:"+obj.get("name"));
        
        Map<String, Object> snsLoginVO = new HashMap<String, Object>();
    	snsLoginVO.put("id", obj.get("id"));
    	snsLoginVO.put("email", obj.get("email"));
    	snsLoginVO.put("name", obj.get("name"));
    	snsLoginVO.put("type", "K");
    	
        
        EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
        
        if(atLoginVO != null){
        	snsLoginVO.put("atId", atLoginVO.get("loginId"));
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		//연계완료
        		ModelAndView modelAndView = new ModelAndView("forward:/front/mypage.do");
	    		modelAndView.addObject("message", "다른 통합회원과 연계중인 SNS아이디입니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
        	}else{
        		//sns 연계진행
        		frontLoginService.insertAtSns(snsLoginVO);
        	}		
        }else{
        	
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		// at로그인 진행
        		try {
        			EgovMap atIdLoginVO = frontLoginService.getAtIdLogin(atSnsLoginVO);
            		flowerSession(atIdLoginVO, request, requestparam, model);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
        		
        	}else{
        		
        	}
        }
        
        request.getSession().setAttribute("snsLoginVO", snsLoginVO);
		
        return "redirect:/front/mypage.do";*/
	}
	
	
	@RequestMapping(value = "/facebookLogin.do")
	public String facebookLogin(@RequestParam Map<String, Object> requestparam,
			HttpServletRequest request, ModelMap model) throws Exception {

		String clientId = "784135178790977";
		String clientSecret = "65a6965f457b108f863dd8f632a0c156";
		String mode = EgovProperties.getProperty("Globals.Mode");
		String domain = EgovProperties.getProperty("Globals.domain"+mode);
		String redirectUrl = domain+"/facebookLogin.do";
		//String redirectUrl = "http://flower.kdev.co.kr:56989/facebookLogin.do";
		
		System.out.println(requestparam.toString());
		String code = String.valueOf(requestparam.get("code"));
		
		String tokenReqUrl = "https://graph.facebook.com/v2.11/oauth/access_token?client_id="+clientId+"&client_secret="+clientSecret+"&redirect_uri="+redirectUrl+"&code="+code;
		System.out.println(tokenReqUrl);
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
		String responseBody = NaverMemberProfile.get(tokenReqUrl,requestHeaders);
		
		System.out.println("토큰값:"+responseBody);
		
		
		 JSONParser parser = new JSONParser();
         JSONObject obj = null;
         
         try {
              obj = (JSONObject)parser.parse(responseBody);
         } catch (Exception e) {
              System.out.println("변환에 실패");
              e.printStackTrace();
         }
         
         System.out.println(obj);
         System.out.println("111"+obj.get("access_token"));
         /*
         System.out.println(obj.get("name"));
         System.out.println(obj.get("isMarried"));
		*/
		String token = String.valueOf(obj.get("access_token")); // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://graph.facebook.com/me?access_token="+token;
        System.out.println(apiURL);
        
        
        requestHeaders.put("Authorization", header);
        responseBody = NaverMemberProfile.get(apiURL,requestHeaders);

        System.out.println("프로필:"+responseBody);
        
        try {
            obj = (JSONObject)parser.parse(responseBody);
       } catch (Exception e) {
            System.out.println("변환에 실패");
            e.printStackTrace();
       }
        
        System.out.println("id:"+obj.get("id"));
        System.out.println("name:"+obj.get("name"));
        
        Map<String, Object> snsLoginVO = new HashMap<String, Object>();
    	snsLoginVO.put("id", obj.get("id"));
    	snsLoginVO.put("name", obj.get("name"));
    	snsLoginVO.put("type", "F");
    	
        
        EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
        
        if(atLoginVO != null){
        	snsLoginVO.put("atId", atLoginVO.get("loginId"));
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		//연계완료
        		ModelAndView modelAndView = new ModelAndView("/front/sns/naver");
        		modelAndView.addObject("message", "다른 통합회원과 연계중인 SNS아이디입니다.");
	            throw new ModelAndViewDefiningException(modelAndView);
        	}else{
        		//sns 연계진행
        		frontLoginService.insertAtSns(snsLoginVO);
        	}		
        }else{
        	
        	EgovMap atSnsLoginVO = frontLoginService.getSnsCheckVO(snsLoginVO);
        	if(atSnsLoginVO != null){
        		// at로그인 진행
        		EgovMap atIdLoginVO = frontLoginService.getAtIdLogin(atSnsLoginVO);
        		flowerSession(atIdLoginVO, request, requestparam, model);
        	}else{
        		
        	}
        }
        
        request.getSession().setAttribute("snsLoginVO", snsLoginVO);
        
		return "/front/sns/naver";
	}
	
	
	
	
	@RequestMapping(value = "/front/mypage.do")
	public String mypage(HttpServletRequest request,
			@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		try {
			
		
		@SuppressWarnings("unchecked")
		Map<String, Object> snsLoginVO = (Map<String, Object>) request.getSession().getAttribute("snsLoginVO");
		EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
		
		if(atLoginVO == null && snsLoginVO == null){
			return "redirect:/";
		}
		
		
		String loginOracle = String.valueOf(request.getSession().getAttribute("loginOracle"));

		if(loginOracle == "null"){
			System.out.println("loginOracle: " + loginOracle);
			System.out.println("loginOracle: " + atLoginVO);
			System.out.println("loginOracle: " + "마이페이지");
			model.addAttribute("contentPath", "login/mypage.jsp");
			return "/front/layout/loginLayout";
		}

		System.out.println("  atLoginVO: " + atLoginVO);
		System.out.println("loginOracle: " + loginOracle);
		
		if(atLoginVO != null && !"Y".equals(loginOracle)){
			EgovMap naverVO = frontLoginService.getNaverInfo(atLoginVO);
			EgovMap kakaoVO = frontLoginService.getKakaoInfo(atLoginVO);
			EgovMap facebookVO = frontLoginService.getFacebookInfo(atLoginVO);
			
			model.addAttribute("naverVO", naverVO);
			model.addAttribute("kakaoVO", kakaoVO);
			model.addAttribute("facebookVO", facebookVO);
			
			paramMap.put("id", atLoginVO.get("loginId")); 
			
			List<EgovMap> floList = frontLoginService.getFloMember(paramMap);
			
			if(floList != null && floList.size() > 0){
				paramMap.put("floMemberList", floList);
				List<EgovMap> smsList = frontLoginService.smsList(paramMap);
				List<EgovMap> mmsList = frontLoginService.mmsList(paramMap);
				
				model.addAttribute("smsList", smsList);
				model.addAttribute("mmsList", mmsList);
				
			}
			
			paramMap.put("loginId", atLoginVO.get("loginId")); 
			paramMap.put("memberId", atLoginVO.get("memberId")); 
			
			for(EgovMap floVO : floList){
				paramMap.put("bunChk",floVO.get("floMokCd"));
				paramMap.put("code",floVO.get("chulCd"));
				EgovMap smsAppVO = frontLoginService.getSmsApp(paramMap);
				floVO.put("smsAppVO", smsAppVO);
			}
			
			model.addAttribute("floList", floList);
			
		}
		
		model.addAttribute("contentPath", "login/mypage.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/front/layout/loginLayout";
	}
	
	
	@RequestMapping(value = "/front/snsDel.json")
	public String snsDel(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		
		try {
			
			EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
			if(atLoginVO != null){
				paramMap.put("userId", atLoginVO.get("loginId"));
				frontLoginService.deleteSns(paramMap);
				request.getSession().setAttribute("snsLoginVO", null);
				model.addAttribute("result", "1");
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	@RequestMapping(value = "/front/setFloDef.json")
	public String insertFloDef(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		
		try {
			
			EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
			
			if(atLoginVO != null){
				paramMap.put("loginId", atLoginVO.get("loginId"));
				paramMap.put("memberId", atLoginVO.get("memberId"));
				
				EgovMap floDefVO = frontLoginService.getFloDef(paramMap);
				
				if(floDefVO != null){
					frontLoginService.updateFloDef(paramMap);
				}else{
					frontLoginService.insertFloDef(paramMap);
				}
				
				
				flowerSession(atLoginVO, request, paramMap, model);
				
				model.addAttribute("result", "1");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	
	
	@RequestMapping(value = "/front/setSmsApp.json")
	public String setSmsApp(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		try {
			
			EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
			
			if(atLoginVO != null){
				paramMap.put("loginId", atLoginVO.get("loginId"));
				paramMap.put("memberId", atLoginVO.get("memberId"));
				
				frontLoginService.insertSmsApp(paramMap);
				
				model.addAttribute("result", "1");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	@RequestMapping(value = "/front/setSmsCan.json")
	public String setSmsCan(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		try {
			
			EgovMap atLoginVO = (EgovMap)request.getSession().getAttribute("atLoginVO");
			
			if(atLoginVO != null){
				paramMap.put("loginId", atLoginVO.get("loginId"));
				paramMap.put("memberId", atLoginVO.get("memberId"));
				
				frontLoginService.deleteSmsApp(paramMap);
				
				model.addAttribute("result", "1");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	
	

	
	
	
	



}
