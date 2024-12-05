package egovframework.srrsrvtn.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public class SRInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 요청이 컨트롤러에 도달하기 전에 수행할 작업
        System.out.println("전시실 인터셉터");
        //return true;
        EgovMap loginVO = null;

        if(request.getQueryString() != null){
            String[] query = request.getQueryString().split("&");
            for(String q : query){
                String[] qArr = q.split("=");
                //paramMap.put(qArr[0], qArr[1]);
                if(qArr[0].equals("type") && qArr[1].equals("2") ){
                    return true;
                }
            }
        }
    	
    	ModelAndView modelAndView = new ModelAndView("forward:/front/login.do");
       
        try {
            loginVO = (EgovMap) request.getSession().getAttribute("atLoginVO");
          
            if (loginVO != null && loginVO.get("memberId") != null) {
            	System.out.println(loginVO.toString());
                return true;
            } else {
            	modelAndView.addObject("message", "전시실 예약은 로그인후에 이용가능합니다.");
                throw new ModelAndViewDefiningException(modelAndView);
            }
        } catch (Exception e) {
            throw new ModelAndViewDefiningException(modelAndView);
        } 
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 요청이 컨트롤러에서 처리된 후, 뷰가 렌더링 되기 전에 수행할 작업
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        // 뷰가 렌더링 된 후에 수행할 작업
        System.out.println("Request and Response is completed");
    }
}

