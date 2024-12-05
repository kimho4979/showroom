package egovframework.cms.common.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.cms.common.util.CommonFileUtil;
import egovframework.cms.common.util.CommonUtil;

@Controller
public class CommonController {

	
	
	//  에디터 이미지 업로드(ajax)
	@RequestMapping("/admin/editor/imageFileUpload.json")
	public String editorFileUpload(HttpServletRequest pRequest, 
						MultipartHttpServletRequest multiRequest
					  , HttpServletResponse pResponse
					  , ModelMap model) throws Exception{
		
		Map<String, String> map = new HashMap<String, String>();
		try {
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		String dirType = "editor";
    	String userFilesPath = new HttpServletRequestWrapper(pRequest).getRealPath("/")+ File.separator + "uploads" + File.separator +dirType + File.separator;
    	
    	
    	if(!files.isEmpty()){
			boolean checkFileSize = false;
			boolean checkFileExtension = false;
			//사이즈 체크
			checkFileSize = CommonFileUtil.checkFileSize(files, 20);
			checkFileExtension = CommonFileUtil.checkFileExtension(files, "jpg,gif,png,jpeg,bmp");
			if(!checkFileExtension || !checkFileSize){
				if(!checkFileExtension){
					map.put("message", "첨부가 불가능한 확장자 파일입니다.\n 가능 확장자 : jpg,gif,png,jpeg,bmp");
					map.put("result", "2");
					model.addAllAttributes(map);
					return "jsonView";// 확장자 에러
				}
				if(!checkFileSize){
					map.put("message", "첨부파일은 합산 10 MB 까지 입력가능합니다." );
					map.put("result", "3");
					model.addAllAttributes(map);
					return "jsonView";	// 사이즈 에러
				}
			}
		}
		
    	if(!files.isEmpty()){
    		Iterator<Entry<String, MultipartFile>> fileIterator = files.entrySet().iterator();
			MultipartFile file;
			while (fileIterator.hasNext()){
				Entry<String, MultipartFile> entry = fileIterator.next();
				file = entry.getValue();
				
				if(!"".equals(file.getOriginalFilename())){
			    	@SuppressWarnings("static-access")
					HashMap<String, String> fileInfo = CommonFileUtil.uploadFileEditor(file, userFilesPath, dirType);
			    	
			    	map.put("url",  fileInfo.get("stordFilePath") + fileInfo.get("saveFileName"));
			    	map.put("result", "1");
				}
			}
    	}
    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		model.addAllAttributes(map);
		
		return "jsonView";
	}
	

	
	
}
