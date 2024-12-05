package egovframework.srrsrvtn.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.cms.common.util.CommonFileUtil;
import egovframework.cms.login.service.LoginService;
import egovframework.front.login.service.FrontLoginService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.srrsrvtn.service.SrrsrvtnService;
import egovframework.srrsrvtn.vo.SrrsrvtnVO;

 /**
    *  전시실 예약 시스템 - 예약
    *
    *  <p>
    *      달력에 선택한 날의 오전/오후/종일을 선택한뒤 예약을 접수한다.
    *  </p>
    *
    * @version : 0.0.1
    * @author : 채동호
    * @param : 예약접수정보
    * @return : 접수건수
    * @throws : Exception
    * @see : 
    */
@Controller
public class SrrsrvtnController {
    
    @Autowired
    private SrrsrvtnService srrsrvtnService;


    @Resource(name = "frontLoginService")
	protected FrontLoginService frontLoginService;
    
    /** EgovSampleService */
	@Resource(name = "loginService")
	private LoginService loginService;


    /**
    *  전시실 예약 시스템 - 공통
    *
    *  <p>
    *      기준 가격표 (전시실 임대료 및 장비 사용료 )
    *  </p>
    *
    * @version : 0.0.1
    * @author : 채동호
    * @param : none
    * @return : List<Map<String, Object>>
    * @throws : Exception
    * @see : 
    */
    @RequestMapping(value = "/front/getcmmnrfrnc.json", method=RequestMethod.GET)
    public String getcmmnrfrnc(Model model) {
        try {
            model.addAttribute("eqpmntRfrnc",srrsrvtnService.getEqpmntRfrnc());    
            model.addAttribute("prcRfrnc",srrsrvtnService.getPrcRfrnc());    
            model.addAttribute("cmmnCode",srrsrvtnService.getCmmnCode());    
        } catch (Exception e) {
            model.addAttribute("result", e.getMessage());                        
        }
        
        return "jsonView";
    }
    /**
    *  전시실 예약 시스템 - 어드민
    *
    *  <p>
    *      조회
    *  </p>
    *
    * @version : 0.0.1
    * @author : 채동호
    * @param : none
    * @return : 
    * @throws : Exception
    * @see : 
    */
    @RequestMapping(value = "/admin/getadminlist.json"
    , method=RequestMethod.POST
    , produces = MediaType.APPLICATION_JSON_VALUE
    , consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getadminlist(@RequestBody Map<String,Object> data ,Model model) {
        try {
            
            ObjectMapper mapper = new ObjectMapper();
            
            //System.out.println(mapper.readValue(mapper.writeValueAsString(data), Object.class));
            List<SrrsrvtnVO> result = srrsrvtnService.getAdminList(data);
            
            //System.out.println(mapper.convertValue(result, EgovMap.class));
            List<EgovMap> _result = (mapper.convertValue(result, List.class));
            
            if (data.get("userId") instanceof String) {
                System.out.println("스트링 타입입니다.");
                data.put("id", data.get("userId"));
                EgovMap result2 = frontLoginService.getAtMember(data);
                for (EgovMap map : _result) {
                    if(map.get("userId").equals(null != result2 && null != result2.get("loginId") ? result2.get("loginId").toString() : "")) {
                        map.put("email",null != result2 && null != result2.get("email") ? result2.get("email").toString() : "");
                        map.put("mngrNm",null != result2 && null != result2.get("name") ? result2.get("name").toString() : "");
                        map.put("telNo",null != result2 && null != result2.get("phone") ? result2.get("phone").toString() : "");
                    }
                }
            } else {
                System.out.println("오브젝트 타입입니다.");
                List<EgovMap> result2 = frontLoginService.getAtMembers();
                for (EgovMap map : _result) {
                    for (EgovMap egovMap : result2) {
                        //System.out.println(map.get("userId") + " : " + egovMap.get("loginId"));
                        if(map.get("userId").equals(null != egovMap && null != egovMap.get("loginId") ? egovMap.get("loginId").toString() : "")) {
                            map.put("email",null != egovMap && null != egovMap.get("email") ? egovMap.get("email").toString() : "");
                            map.put("mngrNm",null != egovMap && null != egovMap.get("name") ? egovMap.get("name").toString() : "");
                            map.put("telNo",null != egovMap && null != egovMap.get("phone") ? egovMap.get("phone").toString() : "");
                        }
                        
                    }
                }
            }
            
            
            model.addAttribute("result", _result);   
            
            if (data.get("seq") != null) {
            	
            	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            	if (authentication instanceof UsernamePasswordAuthenticationToken) {
            	    UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

            	    String userId = (String) auth.getPrincipal();
            	    
            	    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            		HttpServletRequest request = requestAttributes.getRequest();
            		
            		String remoteAddr = "";
            		if(request.getRemoteAddr() != null && request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")) {
            			remoteAddr = "127.0.0.1";
            		} else {
            			remoteAddr = request.getRemoteAddr();
            		}
            		
            		Map<String, Object> paramMap = new HashMap<String, Object>();
            		
            		paramMap.put("userId", userId);
            		paramMap.put("accessIp", remoteAddr);
            		paramMap.put("url", request.getRequestURL().toString());
            		paramMap.put("querystring", data.get("userId"));

            		// Generate a random unique UUID
            		UUID uuid = UUID.randomUUID();

            		// Convert the UUID to a string
            		String uniqueId = uuid.toString();

            		// Set the uniqueId in the paramMap
            		paramMap.put("uniqueId", uniqueId);
            		
            		loginService.registAccessInfo(paramMap);
            	}
            }
            
            
        } catch (Exception e) {
            model.addAttribute("result", e.getMessage());
            e.printStackTrace();                        
        }
        
        return "jsonView";
    }
            

    /**  전시실 예약 시스템 - 공통
    *
    *  <p>
    *      전시실 예약 현황
    *  </p>
    *
    * @version : 0.0.1
    * @author : 하민우
    * @param : SrrsrvtnVO
    * @return : List<Map<String, Object>>
    * @throws : Exception
    * @see : 
    */
    @RequestMapping(value = "/front/getsrrsrvtnlist.json", method=RequestMethod.POST)
    public String getsrrsrvtnlist(
    	@RequestParam Map<String,Object> param, @ModelAttribute SrrsrvtnVO data, Model model) {
		List<SrrsrvtnVO> resultList = new ArrayList<>();
		List<SrrsrvtnVO> resultListGrp = new ArrayList<>();
        data.setUserId(param.get("loginId").toString());
        try {
            resultListGrp = srrsrvtnService.getSrRsrvtnListGrp(data);
        	resultList = srrsrvtnService.getSrRsrvtnList(data);
            param.put("id", param.get("loginId").toString());
            EgovMap result = frontLoginService.getAtMember(param);
            for (SrrsrvtnVO srrsrvtnVO : resultList) {
                srrsrvtnVO.setMngrNm(null != result && null != result.get("name") ? result.get("name").toString() : "");
                srrsrvtnVO.setTelNo(null != result && null != result.get("phone") ? result.get("phone").toString() : "");
                srrsrvtnVO.setEmail(null != result && null != result.get("email") ? result.get("email").toString() : "");
            }    
            for (SrrsrvtnVO srrsrvtnVO : resultListGrp) {
                srrsrvtnVO.setMngrNm(null != result && null != result.get("name") ? result.get("name").toString() : "");
                srrsrvtnVO.setTelNo(null != result && null != result.get("phone") ? result.get("phone").toString() : "");
                srrsrvtnVO.setEmail(null != result && null != result.get("email") ? result.get("email").toString() : "");
            }
            model.addAttribute("srrsrvtnlist",resultList); 
            // 그룹별로 가져오기 
            model.addAttribute("srrsrvtnlistGrp",resultListGrp); 
            // 계약서 바인딩에 필요한 데이터
            model.addAttribute("eqpmntRfrnc",srrsrvtnService.getEqpmntRfrnc());     
        } catch (Exception e) {
            model.addAttribute("result", e.getMessage());                        
        }
        
        return "jsonView";
    }
    
    /**  전시실 예약 시스템 - 공통
     *
     *  <p>
     *      전시실 예약 가능 여부
     *  </p>
     *
     * @version : 0.0.1
     * @author : 하민우
     * @param : SrrsrvtnVO
     * @return : SrrsrvtnVO
     * @throws : Exception
     * @see : 
     */
    @RequestMapping(value = "/front/getRsrvYn.json"
    		, method=RequestMethod.POST
    		, produces = MediaType.APPLICATION_JSON_VALUE
    	    , consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getRsrvYn(@RequestBody SrrsrvtnVO data, Model model) {
		List<SrrsrvtnVO> resultList = new ArrayList<>();
    	try {
    		resultList = srrsrvtnService.getRsrvYn(data);
    		
    		model.addAttribute("result", resultList); 
    	} catch (Exception e) {
    		model.addAttribute("result", e.getMessage());                        
    	}
    	
    	return "jsonView";
    }
    
    /**  전시실 예약 시스템 - 공통
    *
    *  <p>
    *      전시실 예약정보 세션에서 불러오기
    *  </p>
    *
    * @version : 0.0.1
    * @author : 하민우
    * @param : SrrsrvtnVO
    * @return : SrrsrvtnVO
    * @throws : Exception
    * @see : 
    */
	@RequestMapping(value = "/front/getRsrvSessionInfo.json", method=RequestMethod.POST)
	public String getRsrvSessionInfo(
		@RequestParam Map<String,Object> param, @ModelAttribute SrrsrvtnVO data, Model model) {
		SrrsrvtnVO result = new SrrsrvtnVO();
	   	try {
	        data.setUserId(param.get("userId").toString());
            param.put("id", param.get("userId").toString());
            EgovMap  result2 = frontLoginService.getAtMember(param);
	        
	   		result = srrsrvtnService.getRsrvSessionInfo(data);
            result.setMngrNm(null != result2 && null != result2.get("name") ? result2.get("name").toString() : "");
            result.setTelNo(null != result2 && null != result2.get("phone") ? result2.get("phone").toString() : "");
            result.setEmail(null != result2 && null != result2.get("email") ? result2.get("email").toString() : "");

            model.addAttribute("rsrvsessioninfo",result); 
	   	} catch (Exception e) {
	   		model.addAttribute("result", e.getMessage());                        
	   	}
   	
	   	return "jsonView";
	}
         /**  전시실 예약 시스템 - 공통
     *
     *  <p>
     *      전시실 예약 insert
     *  </p>
     *
     * @version : 0.0.1
     * @author : 하민우
     * @param : SrrsrvtnVO
     * @return : List<Map<String, Object>>
     * @throws : Exception
     * @see : 
     */
    @RequestMapping(value = "/admin/multiRsrvtn.json", method=RequestMethod.POST, consumes = { "multipart/form-data" })
    public String multiRsrvtn(
        @RequestPart("rsrvtnData") SrrsrvtnVO rsrvtnData,
        @RequestParam("act") String act,
        Model model) {
        
        try {
            HashMap<String, Object> resultMap = new HashMap<String, Object>();
            
            Map<String,Object> data = BeanUtils.describe(rsrvtnData);
            HashMap<String, Object> rtnObj = srrsrvtnService.multiRsrvtn(data);
            
            if (rtnObj != null) {
                resultMap = rtnObj;
            } else {
                resultMap.put("code", "S");
            }
            
            model.addAttribute("result", resultMap);
        } catch (Exception e) {
            model.addAttribute("result", e.getMessage());                        
        }
        
        return "jsonView";
    }
    /**  전시실 예약 시스템 - 공통
    *
    *  <p>
    *      전시실 예약 insert
    *  </p>
    *
    * @version : 0.0.1
    * @author : 하민우
    * @param : SrrsrvtnVO
    * @return : List<Map<String, Object>>
    * @throws : Exception
    * @see : 
    */
    @RequestMapping(value = "/front/multiRsrvtn.json", method=RequestMethod.POST, consumes = { "multipart/form-data" })
    public String insertRsrvtn(
    	@RequestPart("rsrvtnDataList") List<HashMap<String, Object>> rsrvtnDataList,
    	@RequestPart(value="comRgstNoFile", required=false) MultipartFile comRgstNoFile,
        @RequestPart(value="btchStylFile1", required=false) MultipartFile btchStylFile1,
        @RequestPart(value="btchStylFile2", required=false) MultipartFile btchStylFile2,
        @RequestPart(value="btchStylFile3", required=false) MultipartFile btchStylFile3,
        @RequestPart(value="btchStylFile4", required=false) MultipartFile btchStylFile4,
        @RequestPart(value="btchStylFile5", required=false) MultipartFile btchStylFile5,
    	@RequestParam("act") String act,
    	Model model) {
    	
    	try {
            rsrvtnDataList.get(0).put("id", rsrvtnDataList.get(0).get("userId"));
            EgovMap map = frontLoginService.getAtMember(rsrvtnDataList.get(0)); 
            rsrvtnDataList.get(0).put("mngrNm", null != map && null != map.get("name") ? map.get("name") : "");
            rsrvtnDataList.get(0).put("telNo", null != map && null != map.get("phone") ? map.get("phone") : "");
            rsrvtnDataList.get(0).put("email", null != map && null != map.get("email") ? map.get("email") : "");
            
    		HashMap<String, Object> resultMap = new HashMap<String, Object>();
    		
    		List<MultipartFile> multipartFiles = new ArrayList<>();
     		
     		if ( btchStylFile1 != null ) {
     			multipartFiles.add(btchStylFile1);
     		}
     		
     		if ( btchStylFile2 != null ) {
     			multipartFiles.add(btchStylFile2);
     		}
     		
     		if ( btchStylFile3 != null ) {
     			multipartFiles.add(btchStylFile3);
     		}
     		
     		if ( btchStylFile4 != null ) {
     			multipartFiles.add(btchStylFile4);
     		}
     		
     		if ( btchStylFile5 != null ) {
     			multipartFiles.add(btchStylFile5);
     		}
    		
    		
    		HashMap<String, Object> rtnObj = srrsrvtnService.multiRsrvtn(rsrvtnDataList, comRgstNoFile, multipartFiles);
    		
    		if (rtnObj != null) {
    			resultMap = rtnObj;
    		} else {
    			resultMap.put("code", "S");
    		}
    		
    		model.addAttribute("result", resultMap);
    	} catch (Exception e) {
    		model.addAttribute("result", e.getMessage());                        
    	}
    	
        return "jsonView";
    }
    
    /**  전시실 예약 시스템 - 어드민
     *
     *  <p>
     *      전시실 예약 어드민 예약현황 엑셀다운로드 
     *  </p>
     *
     * @version : 0.0.1
     * @author : 채동호
     * @param : Map<String,Object>
     * @return : void
     * @throws : Exception
     * @see : 
     * @desc : 엑셀은 GET 파라미터에 반드시 인코딩해서 넘겨야됨 
     * @desc : location.href 등 페이지 이동 방식으로 구현   
     */
    @RequestMapping(value = "/admin/srrsrvtnExcel.excel"
    , method=RequestMethod.GET
    )
    public ModelAndView srrsrvtnExcel(HttpServletRequest request){
        Map<String, Object> map = null;
        try {
            String evntNm = request.getParameter("evntNm");
            evntNm = URLDecoder.decode(evntNm, "UTF-8");
            String status = request.getParameter("status");
            status = URLDecoder.decode(status, "UTF-8");
            String hallType = request.getParameter("hallType");
            hallType = URLDecoder.decode(hallType, "UTF-8");
            String rprsntrNm = request.getParameter("rprsntrNm");
            rprsntrNm = URLDecoder.decode(rprsntrNm, "UTF-8");
            String inDt1 = request.getParameter("inDt1");
            inDt1 = URLDecoder.decode(inDt1, "UTF-8");
            String inDt2 = request.getParameter("inDt2");
            inDt2 = URLDecoder.decode(inDt2, "UTF-8");
            String rsrvDt1 = request.getParameter("rsrvDt1");
            rsrvDt1 = URLDecoder.decode(rsrvDt1, "UTF-8");
            String rsrvDt2 = request.getParameter("rsrvDt2");
            rsrvDt2 = URLDecoder.decode(rsrvDt2, "UTF-8");
            map = new HashMap<String, Object>();
            map.put("status", status);
            map.put("evntNm", evntNm);
            map.put("hallType", hallType);
            map.put("rprsntrNm", rprsntrNm);
            map.put("inDt1", inDt1);
            map.put("inDt2", inDt2);
            map.put("rsrvDt1", rsrvDt1);
            map.put("rsrvDt2", rsrvDt2);
            System.out.println(map);
            List<SrrsrvtnVO> list = srrsrvtnService.getAdminListExcel(map);

            for(EgovMap egovMap : frontLoginService.getAtMembers()) {
                for (SrrsrvtnVO srrsrvtnVO : list) {
                    if(srrsrvtnVO.getUserId().equals(null != egovMap && null != egovMap.get("loginId") ? egovMap.get("loginId").toString() : "")) {
                        srrsrvtnVO.setMngrNm(null != egovMap && null != egovMap.get("name") ? egovMap.get("name").toString() : "");
                        srrsrvtnVO.setTelNo(null != egovMap && null != egovMap.get("phone") ? egovMap.get("phone").toString() : "");
                        srrsrvtnVO.setEmail(null != egovMap && null != egovMap.get("email") ? egovMap.get("email").toString() : "");
                    }
                }
            }
                        
            map = new HashMap<String, Object>();
            map.put("category", list);
            map.put("locale", Locale.KOREA);
	        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return new ModelAndView("CategoryPOIExcelView","categoryMap",map);

    }

     /**  전시실 예약정보 수정 - 공통
      *
      *  <p>
      *      전시실 예약정보 수정
      *  </p>
      *
      * @version : 0.0.1
      * @author : 하민우
      * @param : SrrsrvtnVO
      * @return : SrrsrvtnVO
      * @throws : Exception
      * @see :
      */
     @RequestMapping(value = "/admin/updateRsrvList.json")
     public String updateRsrvList(@RequestBody Map<String,Object> data ,Model model) {
         try {
             int result = srrsrvtnService.updateRsrvList(data);
             model.addAttribute("result", result);
         } catch (Exception e) {
             model.addAttribute("result", e.getMessage());
         }

         return "jsonView";
     }
     /**  전시실 예약정보 수정 - 공통
      *
      *  <p>
      *      전시실 예약정보 수정
      *  </p>
      *
      * @version : 0.0.1
      * @author : 하민우
      * @param : SrrsrvtnVO
      * @return : SrrsrvtnVO
      * @throws : Exception
      * @see :
      */
     @RequestMapping(value = "/admin/insertRsrvList.json")
     public String insertRsrvList(@RequestBody Map<String,Object> data ,Model model) {
         try {
             int result = srrsrvtnService.insertSrrsrvtn(data);
             model.addAttribute("result", result);
         } catch (Exception e) {
             model.addAttribute("result", e.getMessage());
         }

         return "jsonView";
     }
    /**  전시실 예약 시스템 - 프론트
     *
     *  <p>
     *      전시실 예약 어드민 예약 개인정보 동의서  
     *  </p>
     *
     * @version : 0.0.1
     * @author : 채동호
     * @param : 
     * @return : 
     * @throws : Exception
     * @see : 
     * @desc : 전시실 예약 메뉴 및 달력은 먼저 개인정보동의를 받아야한다. 
     * @desc :    
     */
    @RequestMapping(value = "/front/saveAgree.json", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveAgree(@RequestBody Map<String,Object> param, HttpServletRequest request,ModelMap model) {
        try {
            srrsrvtnService.saveAgree(param);
            model.addAttribute("result","success");
        } catch (Exception e) {
            model.addAttribute("result",e.getMessage());
        }
        return "jsonView";
    }
    @RequestMapping(value = "/front/checkAgree.json", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String checkAgree(@RequestBody Map<String,Object> param, HttpServletRequest request,ModelMap model) {
        try {
            int cnt = srrsrvtnService.checkAgree(param);
            if(cnt  > 0)
                model.addAttribute("result","already");
            else
                model.addAttribute("result","no");    
        } catch (Exception e) {
            model.addAttribute("result",e.getMessage());
        }
        return "jsonView";
    }

     /**  전시실 예약 시스템 - 공통
      *
      *  <p>
      *      전시실 예약 insert
      *  </p>
      *
      * @version : 0.0.1
      * @author : 하민우
      * @param : SrrsrvtnVO
      * @return : List<Map<String, Object>>
      * @throws : Exception
      * @see :
      */
     @RequestMapping(value = "/admin/updateStatusList.json")
     public String updateStatusList(@RequestBody Map<String,Object> data ,Model model) {
         try {
             System.out.println("Controller data = "+data);
             HashMap<String, Object> result = srrsrvtnService.updateStatusList(data);
             model.addAttribute("result", result);
         } catch (Exception e) {
             model.addAttribute("result", e.getMessage());
         }

         return "jsonView";
     }

     /**  전시실 예약 시스템 - 공통
      *
      *  <p>
      *      전시실 예약 insert
      *  </p>
      *
      * @version : 0.0.1
      * @author : 하민우
      * @param : SrrsrvtnVO
      * @return : List<Map<String, Object>>
      * @throws : Exception
      * @see :
      */
     @RequestMapping(value = "/admin/deleteUserInfo.json")
     public String deleteUserInfo(@RequestBody Map<String,Object> data ,Model model) {
         try {
             int result = srrsrvtnService.deleteUserInfo(data);
             model.addAttribute("result", result);
         } catch (Exception e) {
             model.addAttribute("result", e.getMessage());
         }

         return "jsonView";
     }

     /**  전시실 예약 시스템 - 프론트
      *
      *  <p>
      *      전시실 예약 출력물 - 계약서
      *  </p>
      *
      * @version : 0.0.1
      * @author : 채동호
      * @param : List<Map<String, Object>>
      * @return : List<Map<String, Object>>
      * @throws : Exception
      * @see :
      */
      @RequestMapping(value = "/front/print/srContract.do", method=RequestMethod.POST)
      public String srContract(@RequestParam Map<String, String> params,@RequestParam(value = "act", required = false) String act, ModelMap model) {
        try {
            String param = params.get("params");
            ObjectMapper oMapper = new ObjectMapper();
            List<Map<String,Object>> result = oMapper.readValue(param, List.class);

            SrrsrvtnVO data = new SrrsrvtnVO();
            data.setUserId(result.get(0).get("userId").toString());
            Map<String,Object> _param = new HashMap<String,Object>();
            _param.put("id", result.get(0).get("userId").toString());
            data.setGrpId(Integer.parseInt(result.get(0).get("grpId").toString()));
            //result.get(0).get("grpId").toString()
            EgovMap resultEgovMap = frontLoginService.getAtMember(_param);
            List<SrrsrvtnVO> resultList = srrsrvtnService.getSrRsrvtnList(data);
            for (SrrsrvtnVO srrsrvtnVO : resultList) {
                srrsrvtnVO.setMngrNm(null != resultEgovMap && null != resultEgovMap.get("name") ? resultEgovMap.get("name").toString() : "");
                srrsrvtnVO.setTelNo(null != resultEgovMap && null != resultEgovMap.get("phone") ? resultEgovMap.get("phone").toString() : "");
                srrsrvtnVO.setEmail(null != resultEgovMap && null != resultEgovMap.get("email") ? resultEgovMap.get("email").toString() : "");
                System.out.println("상태:"+srrsrvtnVO.getStatus());
                System.out.println("상태:"+srrsrvtnVO.getStatusCd());
                // 반려일때 계산 안함.
                if("R".equals(srrsrvtnVO.getStatusCd())){
                    srrsrvtnVO.setTotalPay(0);
                    srrsrvtnVO.setRentPay(0);
                    srrsrvtnVO.setEqpmntPay(0);
                    srrsrvtnVO.setWlsMic(0);
                    srrsrvtnVO.setwMic(0);
                    srrsrvtnVO.setChr(0);
                    srrsrvtnVO.setGrbgPck(0);
                    srrsrvtnVO.setPrjctr(0);
                    srrsrvtnVO.setbAmp(0);
                    srrsrvtnVO.setTbl(0);
                    srrsrvtnVO.setMngPay(0);
                    
                }
            }

            // 반려 내용 삭제 
            java.util.Iterator<SrrsrvtnVO> iterator = resultList.iterator();
            while (iterator.hasNext()) {
                SrrsrvtnVO srrsrvtnVO = iterator.next();
                if (srrsrvtnVO.getStatusCd().equals("R")) {
                    iterator.remove();
                }
            }

            if(resultList.size() == 0 ) {
                model.addAttribute("msg","noDataFromSr");
                return "cmmn/egovError";
            }

            String json = oMapper.writeValueAsString(resultList);
            List<Map<String,Object>> resultList2 = oMapper.readValue(json, new TypeReference<List<Map<String,Object>>>(){});
            for (Map<String, Object> map : resultList2) {
                map.put("mngrNm", resultEgovMap.get("name").toString());
                map.put("telNo", resultEgovMap.get("phone").toString());
                map.put("email", resultEgovMap.get("email").toString());
                
            }
            model.addAttribute("resultJson",json);
            model.addAttribute("resultList",resultList2);
            model.addAttribute("eqpmntRfrnc",srrsrvtnService.getEqpmntRfrnc());    
            model.addAttribute("prcRfrnc",srrsrvtnService.getPrcRfrnc());    
            model.addAttribute("result","success");
            if(result.get(0).get("type").toString().equals("detail")) {
                return "cms/sr/srContractDetail";
            } else {
                return "cms/sr/srContract";
            }
        } catch (Exception e) {
            model.addAttribute("result",e.getMessage());
        }
        
        return "cms/sr/srContract";
      }
         /**  전시실 예약 시스템 - 프론트
      *
      *  <p>
      *      전시실 예약 출력물 - 견적서
      *  </p>
      *
      * @version : 0.0.1
      * @author : 채동호
      * @param : List<Map<String, Object>>
      * @return : List<Map<String, Object>>
      * @throws : Exception
      * @see :
      */
      @RequestMapping(value = "/front/print/srContractEstimate.do", method=RequestMethod.POST)
      public String srContractEstimate(@RequestParam Map<String, String> params,@RequestParam(value = "act", required = false) String act, ModelMap model) {
        try {
            String param = params.get("params");
            ObjectMapper oMapper = new ObjectMapper();
            List<Map<String,Object>> result = oMapper.readValue(param, List.class);
            
            model.addAttribute("resultList",result);
            model.addAttribute("eqpmntRfrnc",srrsrvtnService.getEqpmntRfrnc());    
            model.addAttribute("prcRfrnc",srrsrvtnService.getPrcRfrnc());    
        } catch (Exception e) {
            model.addAttribute("result",e.getMessage());
        }  
        return "cms/sr/srContractEstimate";
      }
      
      /**  전시실 예약 시스템 - 프론트
      *
      *  <p>
      *      전시실 예약 출력물 - 계약서
      *  </p>
      *
      * @version : 0.0.1
      * @author : 채동호
      * @param : List<Map<String, Object>>
      * @return : List<Map<String, Object>>
      * @throws : Exception
      * @see :
      */
      @RequestMapping(value = "/admin/fileDownLoad.do", method=RequestMethod.POST)
      public void fileDownLoad(@RequestParam Map<String, String> params, HttpServletResponse response, HttpServletRequest request, ModelMap model
              , @RequestParam(value = "act", required = false) String act
              , @RequestParam(value = "fileNm", required = false) String fileNm
              , @RequestParam(value = "fileType", required = false) String fileType
              , @RequestParam(value = "filePath", required = false) String filePath
                               ) {
        try {
            request.setAttribute("downFileName", fileNm);
            request.setAttribute("downFilePath", filePath);
        	CommonFileUtil.downFile(request, response);
        	
        } catch (Exception e) {
        }
      }
      
}
