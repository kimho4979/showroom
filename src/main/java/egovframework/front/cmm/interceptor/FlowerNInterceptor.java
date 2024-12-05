package egovframework.front.cmm.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public class FlowerNInterceptor extends HandlerInterceptorAdapter{
	
	/**
     * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다. 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	EgovMap loginVO = null;
    	
    	ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
       
        try {
            loginVO = (EgovMap) request.getSession().getAttribute("nFloLoginVO");
          
            if (loginVO != null && loginVO.get("memberId") != null) {
            	System.out.println(loginVO.toString());
            	String aucType = String.valueOf(loginVO.get("aucType")) ;
            	if("01".equals(aucType) || "02".equals(aucType) || "03".equals(aucType) || "07".equals(aucType)){
            		return true;
            	}else{
            	    modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
                    throw new ModelAndViewDefiningException(modelAndView);
            	}
                
            } else {
            	modelAndView.addObject("message", "해당 메뉴 권한이 없습니다.");
                throw new ModelAndViewDefiningException(modelAndView);
            }
        } catch (Exception e) {
            throw new ModelAndViewDefiningException(modelAndView);
        }
    }

    /**
     * 세션에 메뉴권한(LoginVO.userGubun)을 가지고 메뉴를 조회하여 권한 여부를 체크한다.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	/*
    	EgovMap loginVO = null;
        String requestURI = request.getRequestURI();
       
        try {
         if(!"/loginView.do".equals(requestURI)) {
          loginVO = (EgovMap) request.getSession().getAttribute("loginVO");
          if (loginVO != null && loginVO.getUserId() != null) {
           SearchVO searchVO = new SearchVO();
           searchVO.setSchMenuUrl(requestURI);
           searchVO.setSchUserGubun(loginVO.getUserGubun());
           List<?> resultList = sysadmService.getSelectRollMenuCheckList(searchVO);
           
           if(resultList == null || resultList.size() == 0) {
                     ModelAndView mav = new ModelAndView("forward:/monitorView.do"); 
                     mav.addObject("message", "권한이 없습니다.");
                     throw new ModelAndViewDefiningException(mav);
           }
           
          } else {
           ModelAndView mav = new ModelAndView("forward:/loginView.do");
           mav.addObject("message", "세션이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요.");
           throw new ModelAndViewDefiningException(mav);
          }
         }
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("forward:/monitorView.do"); 
            mav.addObject("message", "권한이 없습니다.");
            throw new ModelAndViewDefiningException(mav);
        }*/    
       }
	
}