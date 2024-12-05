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
package egovframework.cms.marketrent.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.cmm.service.EgovFormBasedFileUtil;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.content.service.ContentService;
import egovframework.cms.content.service.ContentVO;
import egovframework.cms.login.service.LoginVO;
import egovframework.cms.market.vo.MarketInfoVO;
import egovframework.cms.marketrent.service.MarketRentService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cms.marketrent.vo.MarketFileVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
public class MarketRentController {
	
	/** EgovSampleService */
	@Resource(name = "marketRentService")
	private MarketRentService marketRentService;
	
	@Resource(name="egovMarketInfoIdGnrService")
	private EgovIdGnrService egovMarketInfoIdGnrService;
	
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;

	/* 리스트 페이지  */
	@RequestMapping(value = "/admin/marketRent/list.do")
	public String list(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		try {
			
		
		List<EgovMap> typeGroupList = marketRentService.getMarketGroupTypeList(paramMap);
		model.addAttribute("typeGroupList", typeGroupList);
		model.addAttribute("contentPath","marketRent/list.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/cms/layout/defaultLayout";
	}
	
	
	/* 리스트 */
	@RequestMapping(value="/admin/marketRent/marketRentList.json")
	public String contentList(HttpServletRequest pRequest, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		
		try {
			
		int firstIndex = Integer.parseInt(String.valueOf(paramMap.get("start")));
		int length = Integer.parseInt(String.valueOf(paramMap.get("length")));
		
		paramMap.put("firstIndex", firstIndex);
		paramMap.put("recordCountPerPage", length);
		
		String searchTypeGroupCode = String.valueOf(paramMap.get("searchTypeGroupCode"));
		System.out.println("ssssssssssssssssss:"+searchTypeGroupCode);
		if(paramMap.get("searchTypeGroupCode") != null && !"".equals(searchTypeGroupCode)){
			System.out.println("sdddddddddddddddddddd:"+searchTypeGroupCode);
			String marketTypeArr = marketRentService.getMarketGroupType(paramMap);
			String[] searchTypeArr = marketTypeArr.split(",");
			paramMap.put("searchTypeArr",searchTypeArr);
		}
		
		
		List<EgovMap> resultList =  marketRentService.getMarketRentList(paramMap);
		int totCnt = marketRentService.getMarketRentListCnt(paramMap);
		
		for(EgovMap result : resultList){
			paramMap.put("marketType", result.get("tyCode"));
			paramMap.put("marketNo", result.get("roCode"));
			EgovMap addInfo = marketRentService.getMarketRentAddInfo(paramMap);
			if(addInfo != null){
				result.put("addInfoYn","Y");
				result.put("marketSeq",addInfo.get("marketSeq"));
			}else{
				result.put("addInfoYn","N");
				result.put("marketSeq","");
			}
			
		}
		
		model.addAttribute("data", resultList);
		model.addAttribute("recordsTotal", totCnt);
		model.addAttribute("recordsFiltered", totCnt);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "jsonView";
		  	
	}
	
	/* 등록화면  */
	@RequestMapping(value = "/admin/marketRent/create.do")
	public String create(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		//EgovMap eResult = marketRentService.getMarketRent(paramMap);
		EgovMap result = marketRentService.selectMarketInfo(paramMap);
		
		
		model.addAttribute("result",result);
		//model.addAttribute("eResult", eResult);
		model.addAttribute("contentPath","marketRent/create.jsp");
		
		return "/cms/layout/defaultLayout";
	}
	
	/* 수정화면 */
	@RequestMapping(value = "/admin/marketRent/update.do")
	public String update(@RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception {
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		EgovMap fileCheckVO = marketRentService.getFileOne(fileMap);
		
		EgovMap eResult = marketRentService.getMarketRent(paramMap);
		
		//20.10.05 김재광 수정
		EgovMap result = marketRentService.selectMarketInfo(paramMap);
		
		model.addAttribute("fileCheckVO", fileCheckVO);
		model.addAttribute("marketFileList",getMarketFileList(eResult));
		model.addAttribute("result", result);
		model.addAttribute("eResult", eResult);
		
		model.addAttribute("contentPath","marketRent/update.jsp");
		return "/cms/layout/defaultLayout";
	}
	
	/* 삭제 */
	@RequestMapping(value="/admin/marketRent/deleteProc.json")
	public String deleteProc(HttpServletRequest pRequest
			, HttpServletResponse pResponse
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		
		int result = marketRentService.delete(paramMap);
		
		model.addAttribute("result", result);
		return "jsonView";
		  	
	}
	
	/* 등록 */
	@RequestMapping(value="/admin/marketRent/insertProc.do")
	public String insertProc(HttpServletRequest pRequest
			, HttpServletResponse pResponse
			, MultipartHttpServletRequest multiRequest
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		try {
			
		
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			paramMap.put("marketSeq", egovMarketInfoIdGnrService.getNextIntegerId());
			paramMap.put("userId", vo.getId());
			
			int result = marketRentService.insert(paramMap);
		
			//파일
			List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
			
			if ((uploadFileList != null) && (uploadFileList.size() > 0)){
				Map<String, Object> fileMap = new HashMap<String, Object>();
		      
				for (int i = 0; i < uploadFileList.size(); i++){
		    	  
		    	  FileVO fileVO = uploadFileList.get(i);
		    	  fileMap.put("fileSeq",((FileVO)uploadFileList.get(i)).getFileSn());
		    	  fileMap.put("marketSeq", paramMap.get("marketSeq"));
		    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
		    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
		    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
		    	  
		    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
		    	  fileMap.put("fileCn", fileVO.getFileCn());
		    	  fileMap.put("fileSize", fileVO.getFileMg());
		    	  fileMap.put("id", vo.getId());
		    	  
		    	  //썸네일 이후 수정
		    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
		    	  marketRentService.marketFileProc(fileMap);
		    	  
		    	  
		    	  if (fileVO != null) {
				      File image = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
				      if (image.exists()) {
				        try {
				          Thumbnails.of(new File[] { image }).size(600, 600).outputQuality(1.0F).toFile(image);
				        } catch (Exception ex) {
				          ex.fillInStackTrace();
				        }
				      }
				    }
		    	  
		       }
		    }
	
			model.addAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "/cms/marketRent/result";
		  	
	}
	
	
	/* 수정 */
	@RequestMapping(value="/admin/marketRent/updateProc.do")
	public String updateProc(HttpServletRequest pRequest
			, HttpServletResponse pResponse
			, MultipartHttpServletRequest multiRequest
			, @RequestParam Map<String, Object> paramMap, ModelMap model) throws Exception{
		try {
			
		
			LoginVO vo = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
			paramMap.put("userId", vo.getId());
			
			int result = marketRentService.update(paramMap);
			String marketSeq = String.valueOf(paramMap.get("marketSeq"));
			String[] deleteAtchFile = pRequest.getParameterValues("deleteAtchFile");
			if(deleteAtchFile != null && deleteAtchFile.length > 0){
				for (int i = 0; i < deleteAtchFile.length; i++){
					paramMap.put("fileSeq",deleteAtchFile[i]);
					marketRentService.deleteFileOne(paramMap);
			      }
			}
			
			List<FileVO> uploadFileList = uploadThumbFile(model, multiRequest);
			
			if ((uploadFileList != null) && (uploadFileList.size() > 0)){
				Map<String, Object> fileMap = new HashMap<String, Object>();
		      
				for (int i = 0; i < uploadFileList.size(); i++){
					
		    	  FileVO fileVO = uploadFileList.get(i);
		    	  fileMap.put("fileSeq", ((FileVO)uploadFileList.get(i)).getFileSn());
		    	  fileMap.put("marketSeq", paramMap.get("marketSeq"));
		    	  fileMap.put("streFilePath", fileVO.getFileStreCours());
		    	  fileMap.put("streFileNm", fileVO.getOrignlFileNm()+"."+fileVO.getStreFileNm());
		    	  fileMap.put("orignlFileNm", fileVO.getOrignlFileNm());
		    	  
		    	  fileMap.put("fileExtsn", fileVO.getFileExtsn());
		    	  fileMap.put("fileCn", fileVO.getFileCn());
		    	  fileMap.put("fileSize", fileVO.getFileMg());
		    	  fileMap.put("id", vo.getId());
		    	  
		    	  //썸네일 이후 수정
		    	  fileMap.put("thumbStreFileNm", fileVO.getStreFileNm());
		    	  
		    	  
		    	  EgovMap fileCheckVO = marketRentService.getFileOne(fileMap);
		    	  if(fileCheckVO != null){
		    		  marketRentService.deleteFileOne(fileMap);
		    	  }
		    	  
		    	  marketRentService.marketFileProc(fileMap);
		    	  
		    	  
		    	  if (fileVO != null) {
				      File image = new File(fileVO.getFileStreCours() + fileVO.getStreFileNm());
				      if (image.exists()) {
				        try {
				          Thumbnails.of(new File[] { image }).size(300, 300).outputQuality(1.0F).toFile(image);
				        } catch (Exception ex) {
				          ex.fillInStackTrace();
				        }
				      }
				    }
		    	  
		       }
		    }
			
			
			model.addAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "/cms/marketRent/result";
		  	
	}

	
	private List<FileVO> uploadThumbFile(ModelMap model, MultipartHttpServletRequest multiRequest) throws Exception{
		
		String mode = EgovProperties.getProperty("Globals.Mode");
		String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
		
	    String uploadPath = savePath + "/uploads/market/";
		
	    String atchFileId = "-1";
	    List<MultipartFile> atchFile = multiRequest.getFiles("attachFile");

	    List<FileVO> atchFileList = null;
	    try {
	      atchFileList = egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, "gif,jpg,jpeg,png,bmp", 20);
	    } catch (Exception e) {
	      model.addAttribute("message", e.getMessage());
	      e.printStackTrace();
	    }

	    return atchFileList;
	  }
	
	private List<MarketFileVO> getMarketFileList(EgovMap result) throws Exception
	{
		MarketFileVO marketFileVO = new MarketFileVO();
		
		marketFileVO.setMarketSeq(Integer.parseInt(String.valueOf(result.get("marketSeq"))));
		
		return marketRentService.selectMarketFileList(marketFileVO);
	}
	

}
