/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import egovframework.cms.cmm.EgovWebUtil;
/*     */ import egovframework.cms.cmm.util.EgovResourceCloseHelper;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class EgovFormBasedFileUtil
/*     */ {
/*     */   public static final int BUFFER_SIZE = 8192;
/*  49 */   public static final String SEPERATOR = File.separator;
/*     */ 
/*  51 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovFormBasedFileUtil.class);
/*     */ 
/*     */   public static String getTodayString()
/*     */   {
/*  59 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
/*     */ 
/*  61 */     return format.format(new Date());
/*     */   }
/*     */ 
/*     */   public static String getPhysicalFileName()
/*     */   {
/*  69 */     return EgovFormBasedUUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
/*     */   }
/*     */ 
/*     */   protected static String convert(String filename)
/*     */     throws Exception
/*     */   {
/*  80 */     return filename;
/*     */   }
/*     */ 
/*     */   public static long saveFile(InputStream is, File file)
/*     */     throws IOException
/*     */   {
/*  91 */     if (!file.getParentFile().exists()) {
/*  92 */       file.getParentFile().mkdirs();
/*     */     }
/*     */ 
/*  95 */     OutputStream os = null;
/*  96 */     long size = 0L;
/*     */     try
/*     */     {
/*  99 */       os = new FileOutputStream(file);
/*     */ 
/* 101 */       int bytesRead = 0;
/* 102 */       byte[] buffer = new byte[8192];
/*     */ 
/* 104 */       while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
/* 105 */         size += bytesRead;
/* 106 */         os.write(buffer, 0, bytesRead);
/*     */       }
/*     */     } finally {
/* 109 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 109 */         os });
/*     */     }
/*     */ 
/* 112 */     return size;
/*     */   }
/*     */ 
/*     */   public static List<EgovFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize)
/*     */     throws Exception
/*     */   {
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */   public static void downloadFile(HttpServletResponse response, String where, String serverSubPath, String physicalName, String original)
/*     */     throws Exception
/*     */   {
/* 193 */     String downFileName = where + SEPERATOR + serverSubPath + SEPERATOR + physicalName;
/*     */ 
/* 195 */     File file = new File(EgovWebUtil.filePathBlackList(downFileName));
/*     */ 
/* 197 */     if (!file.exists()) {
/* 198 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 201 */     if (!file.isFile()) {
/* 202 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 205 */     byte[] b = new byte[8192];
/*     */ 
/* 207 */     original = original.replaceAll("\r", "").replaceAll("\n", "");
/* 208 */     response.setContentType("application/octet-stream");
/* 209 */     response.setHeader("Content-Disposition", "attachment; filename=\"" + convert(original) + "\";");
/* 210 */     response.setHeader("Content-Transfer-Encoding", "binary");
/* 211 */     response.setHeader("Pragma", "no-cache");
/* 212 */     response.setHeader("Expires", "0");
/*     */ 
/* 214 */     BufferedInputStream fin = null;
/* 215 */     BufferedOutputStream outs = null;
/*     */     try
/*     */     {
/* 218 */       fin = new BufferedInputStream(new FileInputStream(file));
/* 219 */       outs = new BufferedOutputStream(response.getOutputStream());
/*     */ 
/* 221 */       int read = 0;
/*     */ 
/* 223 */       while ((read = fin.read(b)) != -1)
/* 224 */         outs.write(b, 0, read);
/*     */     }
/*     */     finally {
/* 227 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 227 */         outs, fin });
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void viewFile(HttpServletResponse response, String where, String serverSubPath, String physicalName, String mimeTypeParam)
/*     */     throws Exception
/*     */   {
/* 245 */     String mimeType = mimeTypeParam;
/*     */ 
/* 247 */     String downFileName = where + serverSubPath;
/*     */ 
/* 249 */     File file = new File(EgovWebUtil.filePathBlackList(downFileName));
/*     */ 
/* 251 */     if (!file.exists()) {
/* 252 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 255 */     if (!file.isFile()) {
/* 256 */       throw new FileNotFoundException(downFileName);
/*     */     }
/*     */ 
/* 259 */     byte[] b = new byte[8192];
/*     */ 
/* 261 */     if (mimeType == null) {
/* 262 */       mimeType = "application/octet-stream;";
/*     */     }
/*     */ 
/* 265 */     response.setContentType(EgovWebUtil.removeCRLF(mimeType));
/* 266 */     response.setHeader("Content-Disposition", "filename=image;");
/*     */ 
/* 268 */     BufferedInputStream fin = null;
/* 269 */     BufferedOutputStream outs = null;
/*     */     try
/*     */     {
/* 272 */       fin = new BufferedInputStream(new FileInputStream(file));
/* 273 */       outs = new BufferedOutputStream(response.getOutputStream());
/*     */ 
/* 275 */       int read = 0;
/*     */ 
/* 277 */       while ((read = fin.read(b)) != -1)
/* 278 */         outs.write(b, 0, read);
/*     */     }
/*     */     finally {
/* 281 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 281 */         outs, fin });
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovFormBasedFileUtil
 * JD-Core Version:    0.6.2
 */