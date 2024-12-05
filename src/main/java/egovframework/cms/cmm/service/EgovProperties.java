/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import egovframework.cms.cmm.EgovWebUtil;
/*     */ import egovframework.cms.cmm.util.EgovResourceCloseHelper;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class EgovProperties
/*     */ {
/*  40 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovProperties.class);
/*     */ 
/*  43 */   static final String FILE_SEPARATOR = System.getProperty("file.separator");
/*     */ 
/*  50 */   public static final String RELATIVE_PATH_PREFIX = EgovProperties.class.getResource("").getPath().substring(0, EgovProperties.class.getResource("").getPath().lastIndexOf("cms"));
/*     */ 
/*  52 */   public static final String GLOBALS_PROPERTIES_FILE = RELATIVE_PATH_PREFIX + "egovProps" + FILE_SEPARATOR + "globals.properties";
/*     */ 
/*     */   public static String getPathProperty(String keyName)
/*     */   {
/*  60 */     String value = "";
/*     */ 
/*  64 */     FileInputStream fis = null;
/*     */     try {
/*  66 */       Properties props = new Properties();
/*     */ 
/*  68 */       fis = new FileInputStream(EgovWebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE));
/*  69 */       props.load(new BufferedInputStream(fis));
/*     */ 
/*  71 */       value = props.getProperty(keyName).trim();
/*  72 */       value = RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + value;
/*     */     } catch (FileNotFoundException fne) {
/*  74 */       LOGGER.debug("Property file not found.", fne);
/*  75 */       throw new RuntimeException("Property file not found", fne);
/*     */     } catch (IOException ioe) {
/*  77 */       LOGGER.debug("Property file IO exception", ioe);
/*  78 */       throw new RuntimeException("Property file IO exception", ioe);
/*     */     } finally {
/*  80 */       EgovResourceCloseHelper.close(new Closeable[] { 
/*  80 */         fis });
/*     */     }
/*     */ 
/*  83 */     return value;
/*     */   }
/*     */ 
/*     */   public static String getProperty(String keyName)
/*     */   {
/*  92 */     String value = "";
/*     */ 
/*  96 */     FileInputStream fis = null;
/*     */     try {
/*  98 */       Properties props = new Properties();
/*     */ 
/* 100 */       fis = new FileInputStream(EgovWebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE));
/*     */ 
/* 102 */       props.load(new BufferedInputStream(fis));
/* 103 */       if (props.getProperty(keyName) == null) {
/* 104 */         return "";
/*     */       }
/* 106 */       value = props.getProperty(keyName).trim();
/*     */     } catch (FileNotFoundException fne) {
/* 108 */       LOGGER.debug("Property file not found.", fne);
/* 109 */       throw new RuntimeException("Property file not found", fne);
/*     */     } catch (IOException ioe) {
/* 111 */       LOGGER.debug("Property file IO exception", ioe);
/* 112 */       throw new RuntimeException("Property file IO exception", ioe);
/*     */     } finally {
/* 114 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 114 */         fis }); } EgovResourceCloseHelper.close(new Closeable[] { 
/* 114 */       fis });
/*     */ 
/* 117 */     return value;
/*     */   }
/*     */ 
/*     */   public static String getPathProperty(String fileName, String key)
/*     */   {
/* 127 */     FileInputStream fis = null;
/*     */     try {
/* 129 */       Properties props = new Properties();
/*     */ 
/* 131 */       fis = new FileInputStream(EgovWebUtil.filePathBlackList(fileName));
/* 132 */       props.load(new BufferedInputStream(fis));
/* 133 */       fis.close();
/*     */ 
/* 135 */       String value = props.getProperty(key);
/* 136 */       value = RELATIVE_PATH_PREFIX + "egovProps" + System.getProperty("file.separator") + value;
/*     */ 
/* 138 */       return value;
/*     */     } catch (FileNotFoundException fne) {
/* 140 */       LOGGER.debug("Property file not found.", fne);
/* 141 */       throw new RuntimeException("Property file not found", fne);
/*     */     } catch (IOException ioe) {
/* 143 */       LOGGER.debug("Property file IO exception", ioe);
/* 144 */       throw new RuntimeException("Property file IO exception", ioe);
/*     */     } finally {
/* 146 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 146 */         fis });
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getProperty(String fileName, String key)
/*     */   {
/* 157 */     FileInputStream fis = null;
/*     */     try {
/* 159 */       Properties props = new Properties();
/*     */ 
/* 161 */       fis = new FileInputStream(EgovWebUtil.filePathBlackList(fileName));
/* 162 */       props.load(new BufferedInputStream(fis));
/* 163 */       fis.close();
/*     */ 
/* 165 */       String value = props.getProperty(key);
/*     */ 
/* 167 */       return value;
/*     */     } catch (FileNotFoundException fne) {
/* 169 */       LOGGER.debug("Property file not found.", fne);
/* 170 */       throw new RuntimeException("Property file not found", fne);
/*     */     } catch (IOException ioe) {
/* 172 */       LOGGER.debug("Property file IO exception", ioe);
/* 173 */       throw new RuntimeException("Property file IO exception", ioe);
/*     */     } finally {
/* 175 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 175 */         fis });
/*     */     }
/*     */   }
/*     */ 
/*     */   public static ArrayList<Map<String, String>> loadPropertyFile(String property)
/*     */   {
/* 187 */     ArrayList keyList = new ArrayList();
/*     */ 
/* 189 */     String src = property.replace('\\', File.separatorChar).replace('/', File.separatorChar);
/* 190 */     FileInputStream fis = null;
/*     */     try
/*     */     {
/* 193 */       File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 194 */       if (srcFile.exists())
/*     */       {
/* 196 */         Properties props = new Properties();
/* 197 */         fis = new FileInputStream(src);
/* 198 */         props.load(new BufferedInputStream(fis));
/* 199 */         fis.close();
/*     */ 
/* 201 */         Enumeration plist = props.propertyNames();
/* 202 */         if (plist != null)
/* 203 */           while (plist.hasMoreElements()) {
/* 204 */             Map map = new HashMap();
/* 205 */             String key = (String)plist.nextElement();
/* 206 */             map.put(key, props.getProperty(key));
/* 207 */             keyList.add(map);
/*     */           }
/*     */       }
/*     */     }
/*     */     catch (IOException ex) {
/* 212 */       LOGGER.debug("IO Exception", ex);
/* 213 */       throw new RuntimeException(ex);
/*     */     } finally {
/* 215 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 215 */         fis });
/*     */     }
/*     */ 
/* 218 */     return keyList;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovProperties
 * JD-Core Version:    0.6.2
 */