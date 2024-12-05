/*     */ package egovframework.cms.cmm.service;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;

/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
/*     */ import org.springframework.stereotype.Component;
/*     */ import org.springframework.util.FileCopyUtils;
/*     */ import org.springframework.web.multipart.MultipartFile;

/*     */ 
/*     */ import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/*     */ import egovframework.rte.fdl.property.EgovPropertyService;
/*
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;*/
/*     */ 
/*     */ @Component("EgovFileMngUtil")
/*     */ public class EgovFileMngUtil
/*     */ {
/*     */   public static final int BUFF_SIZE = 2048;
/*     */ 
/*     */   @Resource(name="propertiesService")
/*     */   protected EgovPropertyService propertyService;
/*     */ 
/*     */   @Resource(name="egovFileIdGnrService")
/*     */   private EgovIdGnrService idgenService;
/*  68 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileMngUtil.class);
/*     */ 
/*     */   public List<FileVO> parseFileInf(List<MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath, String limitFileExt, int uploadSize)
/*     */     throws Exception
/*     */   {
/*  85 */     int fileKey = fileKeyParam;
/*     */ 
/*  87 */     String storePathString = storePath;
/*  88 */     String atchFileIdString = "";
/*     */ 
/*  90 */     if (("".equals(atchFileId)) || (atchFileId == null))
/*  91 */       atchFileIdString = this.idgenService.getNextStringId();
/*     */     else {
/*  93 */       atchFileIdString = atchFileId;
/*     */     }
/*     */ 
/*  96 */     File saveFolder = new File(storePathString);
/*     */ 
/*  98 */     if ((!saveFolder.exists()) || (saveFolder.isFile())) {
/*  99 */       saveFolder.mkdirs();
/*     */     }
/*     */ 
/* 102 */     ArrayList tmpList = new ArrayList();
/*     */ 
/* 105 */     String filePath = "";
/* 106 */     List result = new ArrayList();
/*     */ 
/* 109 */     int fileSn = -1;
/* 110 */     for (int i = 0; i < files.size(); i++)
/*     */     {
/* 112 */       MultipartFile file = (MultipartFile)files.get(i);
/* 113 */       String orginFileName = file.getOriginalFilename();
/* 114 */       orginFileName = orginFileName.replace("..", "");
/* 115 */       orginFileName = orginFileName.replace(";", "");
/* 116 */       orginFileName = orginFileName.replace("%", "");
/* 117 */       orginFileName = orginFileName.replace(" ", "");
/*     */ 
/* 119 */       fileSn++;
/*     */ 
/* 125 */       if (!"".equals(orginFileName))
/*     */       {
/* 131 */         if ((!Upload_Filter(orginFileName).booleanValue()) || (!isPermitExt(orginFileName, limitFileExt)))
/*     */         {
/* 133 */           for (int j = 0; j < tmpList.size(); j++)
/*     */           {
/* 135 */             String tmpFile = (String)tmpList.get(j);
/* 136 */             System.out.println(tmpFile);
/* 137 */             EgovFileTool.deleteFile(tmpFile);
/*     */           }
/* 139 */           throw new Exception("fail.upload.limitedFileExt");
/*     */         }
/*     */ 
/* 143 */         long size = file.getSize();
/* 144 */         size /= 1000000L;
/* 145 */         if ((uploadSize > 0) && (size > uploadSize))
/*     */         {
/* 147 */           for (int j = 0; j < tmpList.size(); j++)
/*     */           {
/* 149 */             String tmpFile = (String)tmpList.get(j);
/* 150 */             EgovFileTool.deleteFile(tmpFile);
/*     */           }
/* 152 */           throw new Exception("fail.upload.limitedFileSize");
/*     */         }
/*     */ 
/* 155 */         int index = orginFileName.lastIndexOf(".");
/*     */ 
/* 157 */         String fileExt = orginFileName.substring(index + 1);
/* 158 */         String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey + "." + fileExt;
/* 159 */         long _size = file.getSize();
/*     */ 
/* 161 */         if (!"".equals(orginFileName))
/*     */         {
/* 163 */           filePath = storePathString + File.separator + newName;
/* 164 */           if ("gif,jpg,jpeg,png,bmp".indexOf(fileExt.toLowerCase()) > -1) {
				
             String orifilePath = storePathString + File.separator + file.getOriginalFilename() + newName;
	  
	  //모바일 회전
	  //imgMobileOrientation(orifilePath);
	  /*
             file.transferTo(new File(orifilePath));
             BufferedImage originalImage = ImageIO.read(new File(orifilePath));
             int type = originalImage.getType() == 0 ? 2 : originalImage.getType();
             int width = originalImage.getWidth();
             int height = originalImage.getHeight();
 
             BufferedImage resizedImage = new BufferedImage(width, height, type);
             Graphics2D g = resizedImage.createGraphics();
 
             g.drawImage(originalImage, 0, 0, width, height, null);
             g.dispose();
             g.setComposite(AlphaComposite.Src);
 
             g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
             g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
             g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
             ImageIO.write(resizedImage, fileExt, new File(filePath));*/
						file.transferTo(new File(filePath));
/*     */           } else {
/* 188 */             file.transferTo(new File(filePath));
/*     */           }
/*     */         }
/*     */ 
/* 192 */         FileVO fvo = new FileVO();
/* 193 */         fvo.setFileExtsn(fileExt);
/* 194 */         fvo.setFileStreCours(storePath);
/* 195 */         fvo.setFileMg(Long.toString(_size));
/* 196 */         fvo.setOrignlFileNm(orginFileName);
/* 197 */         fvo.setStreFileNm(newName);
/* 198 */         fvo.setAtchFileId(atchFileIdString);
/* 199 */         fvo.setFileSn(String.valueOf(fileSn));
/*     */ 
/* 202 */         result.add(fvo);
/*     */ 
/* 204 */         tmpList.add(fvo.getFileStreCours() + fvo.getStreFileNm());
/*     */ 
/* 206 */         fileKey++;
/*     */       }
/*     */     }
/*     */ 
/* 210 */     return result;
/*     */   }
/*     */ 
/*     */   protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath)
/*     */     throws Exception
/*     */   {
/* 222 */     InputStream stream = null;
/* 223 */     OutputStream bos = null;
/* 224 */     String stordFilePathReal = (stordFilePath == null ? "" : stordFilePath).replaceAll("..", "");
/*     */     try {
/* 226 */       stream = file.getInputStream();
/* 227 */       File cFile = new File(stordFilePathReal);
/*     */ 
/* 229 */       if (!cFile.isDirectory()) {
/* 230 */         boolean _flag = cFile.mkdir();
/* 231 */         if (!_flag) {
/* 232 */           throw new IOException("Directory creation Failed ");
/*     */         }
/*     */       }
/*     */ 
/* 236 */       bos = new FileOutputStream(stordFilePathReal + File.separator + newName);
/*     */ 
/* 238 */       int bytesRead = 0;
/* 239 */       byte[] buffer = new byte[2048];
/*     */ 
/* 241 */       while ((bytesRead = stream.read(buffer, 0, 2048)) != -1)
/* 242 */         bos.write(buffer, 0, bytesRead);
/*     */     }
/*     */     catch (FileNotFoundException fnfe) {
/* 245 */       LOGGER.debug("fnfe: {}", fnfe);
/*     */ 
/* 251 */       if (bos != null) {
/*     */         try {
/* 253 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 255 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 258 */       if (stream != null)
/*     */         try {
/* 260 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 262 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 247 */       LOGGER.debug("ioe: {}", ioe);
/*     */ 
/* 251 */       if (bos != null) {
/*     */         try {
/* 253 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 255 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 258 */       if (stream != null)
/*     */         try {
/* 260 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 262 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 249 */       LOGGER.debug("e: {}", e);
/*     */ 
/* 251 */       if (bos != null) {
/*     */         try {
/* 253 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 255 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 258 */       if (stream != null)
/*     */         try {
/* 260 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 262 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 251 */       if (bos != null) {
/*     */         try {
/* 253 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 255 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 258 */       if (stream != null)
/*     */         try {
/* 260 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 262 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void downFile(HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/* 277 */     String downFileName = EgovStringUtil.isNullToString(request.getAttribute("downFile")).replaceAll("..", "");
/* 278 */     String orgFileName = EgovStringUtil.isNullToString(request.getAttribute("orgFileName")).replaceAll("..", "");
/*     */ 
/* 292 */     File file = new File(downFileName);
/*     */ 
/* 294 */     if (!file.exists()) {
/* 295 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 298 */     if (!file.isFile()) {
/* 299 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 302 */     byte[] b = new byte[2048];
/* 303 */     String fName = new String(orgFileName.getBytes(), "UTF-8").replaceAll("\r\n", "");
/* 304 */     response.setContentType("application/x-msdownload");
/* 305 */     response.setHeader("Content-Disposition:", "attachment; filename=" + fName);
/* 306 */     response.setHeader("Content-Transfer-Encoding", "binary");
/* 307 */     response.setHeader("Pragma", "no-cache");
/* 308 */     response.setHeader("Expires", "0");
/*     */ 
/* 310 */     BufferedInputStream fin = null;
/* 311 */     BufferedOutputStream outs = null;
/*     */     try
/*     */     {
/* 314 */       fin = new BufferedInputStream(new FileInputStream(file));
/* 315 */       outs = new BufferedOutputStream(response.getOutputStream());
/* 316 */       int read = 0;
/*     */ 
/* 318 */       while ((read = fin.read(b)) != -1)
/* 319 */         outs.write(b, 0, read);
/*     */     }
/*     */     finally {
/* 322 */       if (outs != null) {
/*     */         try {
/* 324 */           outs.close();
/*     */         } catch (Exception ignore) {
/* 326 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 329 */       if (fin != null)
/*     */         try {
/* 331 */           fin.close();
/*     */         } catch (Exception ignore) {
/* 333 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void writeFile(MultipartFile file, String newName, String stordFilePath)
/*     */     throws Exception
/*     */   {
/* 381 */     InputStream stream = null;
/* 382 */     OutputStream bos = null;
/* 383 */     newName = EgovStringUtil.isNullToString(newName).replaceAll("..", "");
/* 384 */     stordFilePath = EgovStringUtil.isNullToString(stordFilePath).replaceAll("..", "");
/*     */     try {
/* 386 */       stream = file.getInputStream();
/* 387 */       File cFile = new File(stordFilePath);
/*     */ 
/* 389 */       if (!cFile.isDirectory()) {
/* 390 */         cFile.mkdir();
/*     */       }
/* 392 */       bos = new FileOutputStream(stordFilePath + File.separator + newName);
/*     */ 
/* 394 */       int bytesRead = 0;
/* 395 */       byte[] buffer = new byte[2048];
/*     */ 
/* 397 */       while ((bytesRead = stream.read(buffer, 0, 2048)) != -1)
/* 398 */         bos.write(buffer, 0, bytesRead);
/*     */     }
/*     */     catch (FileNotFoundException fnfe) {
/* 401 */       LOGGER.debug("fnfe: {}", fnfe);
/*     */ 
/* 407 */       if (bos != null) {
/*     */         try {
/* 409 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 411 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 414 */       if (stream != null)
/*     */         try {
/* 416 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 418 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 403 */       LOGGER.debug("ioe: {}", ioe);
/*     */ 
/* 407 */       if (bos != null) {
/*     */         try {
/* 409 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 411 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 414 */       if (stream != null)
/*     */         try {
/* 416 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 418 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 405 */       LOGGER.debug("e: {}", e);
/*     */ 
/* 407 */       if (bos != null) {
/*     */         try {
/* 409 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 411 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 414 */       if (stream != null)
/*     */         try {
/* 416 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 418 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 407 */       if (bos != null) {
/*     */         try {
/* 409 */           bos.close();
/*     */         } catch (Exception ignore) {
/* 411 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */       }
/* 414 */       if (stream != null)
/*     */         try {
/* 416 */           stream.close();
/*     */         } catch (Exception ignore) {
/* 418 */           LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void downFile(HttpServletRequest request, HttpServletResponse response, String streFileNm, String orignFileNm)
/*     */     throws Exception
/*     */   {
/* 436 */     String downFileName = EgovStringUtil.isNullToString(streFileNm);
/* 437 */     String orgFileName = EgovStringUtil.isNullToString(orignFileNm);
/*     */ 
/* 439 */     File file = new File(downFileName);
/*     */ 
/* 443 */     if (!file.exists()) {
/* 444 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 447 */     if (!file.isFile()) {
/* 448 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 452 */     int fSize = (int)file.length();
/* 453 */     //if (fSize > 0) {
/* 454 */       BufferedInputStream in = null;
/*     */       try
/*     */       {
/* 457 */         in = new BufferedInputStream(new FileInputStream(file));
/*     */ 
/* 459 */         String mimetype = "application/x-msdownload";
/*     */ 
/* 461 */         response.setBufferSize(fSize);
/* 462 */         response.setContentType(mimetype);
				  setDisposition(String.valueOf(orgFileName), request, response);
/* 463 */         
/* 464 */         response.setContentLength(fSize);

			
			
			


/*     */ 
/* 468 */         FileCopyUtils.copy(in, response.getOutputStream());
/*     */       } finally {
/* 470 */         if (in != null) {
/*     */           try {
/* 472 */             in.close();
/*     */           } catch (Exception ignore) {
/* 474 */             LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */           }
/*     */         }
/*     */       }
/* 478 */       response.getOutputStream().flush();
/* 479 */       response.getOutputStream().close();
/*     */     //}
/*     */   }


public void downFile2(HttpServletRequest request, HttpServletResponse response, String streFileNm, String orignFileNm)
/*     */    throws Exception
/*     */  {
/* 436 */    String downFileName = EgovStringUtil.isNullToString(streFileNm);
/* 437 */    String orgFileName = EgovStringUtil.isNullToString(orignFileNm);
/*     */
/* 439 */    File file = new File(downFileName);
/*     */
/* 443 */    if (!file.exists()) {
/* 444 */      throw new FileNotFoundException(downFileName);
/*     */    }
/*     */
/* 447 */    if (!file.isFile()) {
/* 448 */      throw new FileNotFoundException(downFileName);
/*     */    }
/*     */
/* 452 */    int fSize = (int)file.length();
/* 453 */    //if (fSize > 0) {
/* 454 */      BufferedInputStream in = null;
/*     */      try
/*     */      {
/* 457 */        in = new BufferedInputStream(new FileInputStream(file));
/*     */
				String ext = FilenameUtils.getExtension(file.getName());
					
				String mimetype = "image/jpeg";
				
				if(ext.contains("png")) {
					
					mimetype = "image/png";
				}
				
/*     */
/* 461 */        response.setBufferSize(fSize);
/* 462 */        response.setContentType(mimetype);
				  setDisposition(String.valueOf(orgFileName), request, response);
/* 463 */        
/* 464 */        response.setContentLength(fSize);

			
			
			


/*     */
/* 468 */        FileCopyUtils.copy(in, response.getOutputStream());
/*     */      } finally {
/* 470 */        if (in != null) {
/*     */          try {
/* 472 */            in.close();
/*     */          } catch (Exception ignore) {
/* 474 */            LOGGER.debug("IGNORED: {}", ignore.getMessage());
/*     */          }
/*     */        }
/*     */      }
/* 478 */      response.getOutputStream().flush();
/* 479 */      response.getOutputStream().close();
/*     */    //}
/*     */  }

public String getBrowser(HttpServletRequest request) {
	   
    String header = request.getHeader("User-Agent");
    if (header.indexOf("MSIE") > -1) {
        return "MSIE";
    } else if (header.indexOf("Trident") > -1) {   // IE11 문자열 깨짐 방지
        return "Trident";
    } else if (header.indexOf("Chrome") > -1) {
        return "Chrome";
    } else if (header.indexOf("Opera") > -1) {
        return "Opera";
    }
    return "Firefox";
}


			public void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
				   
				   String browser = getBrowser(request);
			
				   String dispositionPrefix = "attachment; filename=";
				   String encodedFilename = null;
			
				   if (browser.equals("MSIE")) {
				      encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
				   } else if (browser.equals("Trident")) {      // IE11 문자열 깨짐 방지
				      encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
				   } else if (browser.equals("Firefox")) {
				      encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
				   } else if (browser.equals("Opera")) {
				      encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
				   } else if (browser.equals("Chrome")) {
				      StringBuffer sb = new StringBuffer();
				      for (int i = 0; i < filename.length(); i++) {
				         char c = filename.charAt(i);
				         if (c > '~' || c == ',') {
				            sb.append(URLEncoder.encode("" + c, "UTF-8"));
				         }else {
				            sb.append(c);
				         }
				      }
				      encodedFilename = sb.toString();
				   } else {
				      //throw new RuntimeException("Not supported browser");
				      throw new IOException("Not supported browser");
				   }
			
				   response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
			
				   if ("Opera".equals(browser)){
				      response.setContentType("application/octet-stream;charset=UTF-8");
				   }
				}

		



  public static Boolean Upload_Filter(String fileName)
  {
    String file_ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

    Boolean result = Boolean.valueOf(true);

    String[] bad_ext = { "php3", "php4", "asp", "jsp", "php", "html", "htm", "inc", "js", "pl", "cgi", "java", "exe" };

    for (int i = 0; i < bad_ext.length; i++)
    {
      if (bad_ext[i].equals(file_ext)) {
        return Boolean.valueOf(false);
      }
    }
    if (fileName.contains(";"))
    {
      return Boolean.valueOf(false);
    }

    if (file_ext.length() == 0)
    {
      return Boolean.valueOf(false);
    }

    if (fileName.contains(".."))
    {
      return Boolean.valueOf(false);
    }
    return result;
  }

  public boolean isPermitExt(String originalFileName, String limitFileExt)
  {
    String file_ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1).toLowerCase();

    if ((limitFileExt == null) || (limitFileExt.equals("")))
    {
      return true;
    }

    String[] fileExtArray = limitFileExt.split(",");

    for (String permExt : fileExtArray)
    {
      if (permExt.toLowerCase().equals(file_ext))
      {
        return true;
      }

    }

    return false;
  }
}
