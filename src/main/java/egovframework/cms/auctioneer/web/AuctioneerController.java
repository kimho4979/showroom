package egovframework.cms.auctioneer.web;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.cms.auctioneer.service.AuctioneerService;
import egovframework.cms.content.service.ContentVO;
import egovframework.cms.login.service.LoginVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class AuctioneerController {
	
	@Resource(name = "auctioneerService")
	private AuctioneerService auctioneerService;
	
	/* 리스트 페이지 */
	@RequestMapping(value="/admin/auctioneer/list.do")
	public String list(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		model.addAttribute("contentPath", "auctioneer/list.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 리스트 */
	@RequestMapping(value="/admin/auctioneer/auctioneerList.json")
	public String auctioneerList(HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		
		System.out.println(paramMap.toString());
		
		paramMap.put("firstIndex", paramMap.get("start"));
		paramMap.put("recordCountPerPage", paramMap.get("length"));
		
		List<EgovMap> resultList =  auctioneerService.getAuctioneerList(paramMap);
		int totCnt = auctioneerService.getAuctioneerListCnt(paramMap);
		
		/*
		model.addAttribute("resultList", resultList);
		model.addAttribute("totCnt", totCnt);*/
		model.addAttribute("data", resultList);
		model.addAttribute("recordsTotal", totCnt);
		model.addAttribute("recordsFiltered", totCnt);
		
		return "jsonView";
	}
	
	
	/* 등록화면 */
	@RequestMapping(value="/admin/auctioneer/create.do")
	public String create(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		model.addAttribute("contentPath", "auctioneer/create.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 아이디 중복체크 */
	@RequestMapping(value="/admin/auctioneer/idCheck.json")
	public String idCheck(HttpServletRequest pRequest, HttpServletResponse pResponse, String id, ModelMap model) throws Exception {
		
		model.addAttribute("result", auctioneerService.idValidCheck(id));
		
		return "jsonView";
	}
	
	
	/* 등록 */
	@RequestMapping(value="/admin/auctioneer/insertProc.json")
	public String insertProc(HttpServletRequest pRequest, HttpServletResponse pResponse,
			@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		try {
			paramMap.put("passwordSha2", encrypt(String.valueOf(paramMap.get("password"))));
			model.addAttribute("result", auctioneerService.insert(paramMap));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	/* 수정화면 */
	@RequestMapping(value="/admin/auctioneer/update.do")
	public String update(Long memberId, ModelMap model) throws Exception {
		
		EgovMap result = auctioneerService.getAuctioneer(memberId);
		
		model.addAttribute("result", result);
		
		model.addAttribute("contentPath", "auctioneer/update.jsp");
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 수정 */
	@RequestMapping(value="/admin/auctioneer/updateProc.json")
	public String updateProc(HttpServletRequest pRequest, HttpServletResponse pResponse,
			@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		try {
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			paramMap.put("userId", vo.getId());
			
			String password = String.valueOf(paramMap.get("password"));
			if(password != null) {
				if(password.trim().length() == 0) {
					password = null;
				}
			}
			paramMap.put("passwordSha2", encrypt(password));
			
			int result = auctioneerService.update(paramMap);
			model.addAttribute("result", result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	
	/* 삭제 */
	@RequestMapping(value="/admin/auctioneer/deleteProc.json")
	public String deleteProc(Long memberId, ModelMap model) throws Exception {
		
		try {
			model.addAttribute("result", auctioneerService.delete(memberId));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	}

	
	public static String encrypt(String data) throws Exception {
		if(data == null) {
			return "";
		}
		
		byte[] plainText = null;
		byte[] hashValue = null;
		plainText = data.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		hashValue = md.digest(plainText);
		
		return new String(Base64.encode(hashValue));
	}

}
