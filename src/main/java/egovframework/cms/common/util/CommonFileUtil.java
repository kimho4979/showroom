package egovframework.cms.common.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cms.cmm.EgovWebUtil;


public class CommonFileUtil {

	public static final int BUFF_SIZE = 2048;

	
	/**
	* 첨부로 등록된 파일을 서버에 업로드한다.
	*
	* @param file
	* @return
	* @throws Exception
	*/
	public static HashMap<String, String> uploadFileEditor(MultipartFile file, String stordFilePath, String dirType) throws Exception {
	
		HashMap<String, String> map = new HashMap<String, String>();
		//Write File 이후 Move File????
		String saveFileName = "";
		String orginFileName = file.getOriginalFilename();
	
		int index = orginFileName.lastIndexOf(".");
		//String fileName = orginFileName.substring(0, _index);
		String fileExt = orginFileName.substring(index + 1);
		long size = file.getSize();
	
		//newName 은 Naming Convention에 의해서 생성
		saveFileName = getTimeStamp() + "." + fileExt;
		
		Calendar cal = Calendar.getInstance();
		String year = cal.get(Calendar.YEAR)+"";
		String mon = (cal.get(Calendar.MONTH)+1)+"";
		String day = cal.get(Calendar.DAY_OF_MONTH)+"";
		
		if(Integer.parseInt(mon) < 10) mon = "0"+mon;
		if(Integer.parseInt(day) < 10) day = "0"+day;
		
		
		String dirDay1 = year + mon + day + File.separator;
		String dirDay2 = year + mon + day + "/";
		
		// 파일생성
		writeFile(file, saveFileName, stordFilePath+dirDay1);
		//storedFilePath는 지정
		map.put("orginFileName", orginFileName);
		map.put("saveFileName", saveFileName);
		map.put("fileExt", fileExt);
		map.put("stordFilePath", "/yfmc/uploads/"+dirType+"/"+ dirDay2);
		map.put("fileSize", String.valueOf(size));
	
		return map;
	}
	

	/**
	* 첨부로 등록된 파일을 서버에 업로드한다.
	*
	* @param file
	* @return
	* @throws Exception
	*/
	public static HashMap<String, String> uploadFileAndThumbnail(MultipartFile file, String stordFilePath, String dirType) throws Exception {
	
		HashMap<String, String> map = new HashMap<String, String>();
		//Write File 이후 Move File????
		String saveFileName = "";
		String orginFileName = file.getOriginalFilename();
	
		int index = orginFileName.lastIndexOf(".");
		//String fileName = orginFileName.substring(0, _index);
		String fileExt = orginFileName.substring(index + 1);
		long size = file.getSize();
	
		//newName 은 Naming Convention에 의해서 생성
		String timeTemp = getTimeStamp();
		saveFileName = timeTemp + "." + fileExt;
		
		Calendar cal = Calendar.getInstance();
		String year = cal.get(Calendar.YEAR)+"";
		String mon = (cal.get(Calendar.MONTH)+1)+"";
		String day = cal.get(Calendar.DAY_OF_MONTH)+"";
		
		if(Integer.parseInt(mon) < 10) mon = "0"+mon;
		if(Integer.parseInt(day) < 10) day = "0"+day;
		
		
		String dirDay1 = year + mon + day + File.separator;
		String dirDay2 = year + mon + day + "/";
		
		// 파일생성
		writeFile(file, saveFileName, stordFilePath+dirDay1);
		//storedFilePath는 지정
		map.put("orginFileName", orginFileName);
		map.put("saveFileName", saveFileName);
		map.put("fileExt", fileExt);
		map.put("stordFilePath", "/upload/"+dirType+"/"+ dirDay2);
		map.put("fileSize", String.valueOf(size));
		
		// 썸네일 저장
		try {
            //썸네일 가로사이즈
            int thumbnail_width = 180;
            //썸네일 세로사이즈
            int thumbnail_height = 100;
            //원본이미지파일의 경로+파일명
            String saveThumFileName = timeTemp + "_thum." + fileExt;
            File origin_file_name = new File(stordFilePath+dirDay1+saveFileName);
            //생성할 썸네일파일의 경로+썸네일파일명
            File thumb_file_name = new File(stordFilePath+dirDay1+saveThumFileName );
 
            BufferedImage buffer_original_image = ImageIO.read(origin_file_name);
            BufferedImage buffer_thumbnail_image = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = buffer_thumbnail_image.createGraphics();
            graphic.drawImage(buffer_original_image, 0, 0, thumbnail_width, thumbnail_height, null);
            ImageIO.write(buffer_thumbnail_image, "jpg", thumb_file_name);
            //System.out.println("썸네일 생성완료");
        } catch (Exception e) {
            e.printStackTrace();
        }

		return map;
	}
	

	
	/**
	* 2011.08.09
	* 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
	* 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	*
	* @param
	* @return Timestamp 값
	* @exception MyException
	* @see
	*/
	private static String getTimeStamp() {
		String rtnStr = null;
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";
		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnStr;
	}

	
	/**
	* 파일을 실제 물리적인 경로에 생성한다.
	*
	* @param file
	* @param newName
	* @param stordFilePath
	* @throws Exception
	*/
	protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
		InputStream stream = null;
		OutputStream bos = null;
	
		try {
			stream = file.getInputStream();
			File cFile = new File(EgovWebUtil.filePathBlackList(stordFilePath));
		
			if (!cFile.isDirectory())
				cFile.mkdirs();
		
			bos = new FileOutputStream(EgovWebUtil.filePathBlackList(stordFilePath + File.separator + newName));
		
			int bytesRead = 0;
			byte[] buffer = new byte[BUFF_SIZE];
		
			while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
			}
		}
	}
	
	/**
	* 전체 첨부파일 사이즈.
	*
	* @param files
	* @return files_size
	* @throws Exception
	*/
	public static boolean checkFileSize(Map<String, MultipartFile> files , long fileLimitSize) throws Exception {
	
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		fileLimitSize = fileLimitSize * 1048576;
		long files_size = 0;
		
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			files_size += file.getSize();
		}
		if(fileLimitSize >= files_size){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/**
	* 전체 첨부파일 확장자 체크.
	*
	* @param files
	* @param adminBoard
	* @return files_ext
	* @throws Exception
	*/
	public static boolean checkFileExtension(Map<String, MultipartFile> files, String Upload_ext ) throws Exception {
	
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String files_ext = "";
		String [] Upload_Extension = Upload_ext.split(",");
		int lastIndex = 0;
		int trueCheck = 0;
		int loopCheck = 0;
		
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			lastIndex = file.getOriginalFilename().lastIndexOf('.');
			files_ext = file.getOriginalFilename().substring( lastIndex+1, file.getOriginalFilename().length() );
			if(files_ext != null && files_ext != ""){
				loopCheck ++;
			}
			for(int i = 0; i < Upload_Extension.length; i++){
				if(Upload_Extension[i].toLowerCase().equals(files_ext.toLowerCase())){
					trueCheck++;
				}
			}
		}
		
		if(trueCheck == loopCheck){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 멀티파트 내부에 특정 name 값을 가진 파일을 조회한다. 
	 * @param fileId
	 * @return
	 * ex. fileId 값이 file 일 경우 file1, file2 이런 파일들 조회 
	 */
	public static Map<String, MultipartFile> getFilesOfName(String fileId, MultipartHttpServletRequest multiRequest) {
		Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
		
		Iterator<Entry<String, MultipartFile>> fileIterator = multiRequest.getFileMap().entrySet().iterator();
        MultipartFile file;

		 while (fileIterator.hasNext()){
             Entry<String, MultipartFile> entry = fileIterator.next();
             file = entry.getValue();
            if(!"".equals(file.getOriginalFilename())){
            	if( file.getName().indexOf(fileId) > -1){
            		files.put(file.getName(), file);
	            }
            }
       }
		return files; 
	}


	/**
	 * 멀티파트 내부 특정 이름으로 된 파일을 추출한다.
	 * @param multiRequest
	 * @return
	 */
	public static MultipartFile getImportbyNameFile(String fileId, MultipartHttpServletRequest multiRequest) {
		
		Iterator<Entry<String, MultipartFile>> fileIterator = multiRequest.getFileMap().entrySet().iterator();
        MultipartFile file = null;
        
		while (fileIterator.hasNext()){
			Entry<String, MultipartFile> entry = fileIterator.next();
			file = entry.getValue();
			
			if(!"".equals(file.getOriginalFilename())){
				if( fileId.equals(file.getName())){
					return file;
				}
			}
		}
		
		// 없을 경우 null 리턴
		return null;
	}
	
	
	/**
	 * 멀티파트 파일을 파일로 변환한다.
	 * @param multipart
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static File multipartToFile(MultipartFile multipart)throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}
	
	
	/**
	* 서버의 파일을 다운로드한다.
	*
	* @param request
	* @param response
	* @throws Exception
	*/
	public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String downFilePath = "";
		String downFileName = "";
		String orgFileName = "";
		
		if ((String)request.getAttribute("downFilePath") == null) {
			downFilePath = "";
		} else {
			downFilePath = (String)request.getAttribute("downFilePath");
		}
	
		if ((String)request.getAttribute("downFileName") == null) {
			downFileName = "";
		} else {
			downFileName = (String)request.getAttribute("downFileName");
		}
	
		if ((String)request.getAttribute("orgFileName") == null) {
			orgFileName = "";
		} else {
			orgFileName = (String)request.getAttribute("orgFileName");
		}
		orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");
	
		File file = new File(EgovWebUtil.filePathBlackList( downFilePath + downFileName));
		
		if (!file.exists()) {
			throw new FileNotFoundException(downFileName);
		}
	
		if (!file.isFile()) {
			throw new FileNotFoundException(downFileName);
		}
	
		String userAgent = request.getHeader("User-Agent");
		byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
		
		if (userAgent.indexOf("Opera") > -1){
			response.setContentType("application/octet-stream;charset=UTF-8");
		}else{
			response.setContentType("application/x-msdownload");
		}
		String encodedFilename = null;
		if (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) { // MS IE (보통은 6.x 이상 가정)
			encodedFilename = URLEncoder.encode(orgFileName, "UTF-8").replaceAll("\\+", "%20");
		} else if (userAgent.indexOf("Firefox") > -1) { // Firefox
			encodedFilename = "\"" + new String(orgFileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (userAgent.indexOf("Opera") > -1) { // Opera
			encodedFilename = "\"" + new String(orgFileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (userAgent.indexOf("AdobeAIR") > -1) { // AdobeAIR
			encodedFilename = "\"" + new String(orgFileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (userAgent.indexOf("Chrome") > -1) { // Chrome
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < orgFileName.length(); i++) {
				char c = orgFileName.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else { //throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}
		response.setHeader("Content-Disposition", "attachment; filename=" + encodedFilename);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-store, no-cache, must-revalidate");
		response.setHeader("Expires", "0");
	
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
	
		try {
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;
		
			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
		} finally {
			if (outs != null) {
				try {
					outs.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
			}
		}
	}
	
	
}
