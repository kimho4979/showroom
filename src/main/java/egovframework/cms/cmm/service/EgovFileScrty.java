/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.security.MessageDigest;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class EgovFileScrty
/*     */ {
/*  35 */   static final char FILE_SEPARATOR = File.separatorChar;
/*     */   static final int BUFFER_SIZE = 1024;
/*  39 */   private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileScrty.class);
/*     */ 
/*     */   public static boolean encryptFile(String source, String target)
/*     */     throws Exception
/*     */   {
/*  52 */     boolean result = false;
/*     */ 
/*  54 */     String sourceFile = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  55 */     String targetFile = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  56 */     File srcFile = new File(sourceFile);
/*     */ 
/*  58 */     BufferedInputStream input = null;
/*  59 */     BufferedOutputStream output = null;
/*     */ 
/*  61 */     byte[] buffer = new byte[1024];
/*     */     try
/*     */     {
/*  64 */       if ((srcFile.exists()) && (srcFile.isFile()))
/*     */       {
/*  66 */         input = new BufferedInputStream(new FileInputStream(srcFile));
/*  67 */         output = new BufferedOutputStream(new FileOutputStream(targetFile));
/*     */ 
/*  69 */         int length = 0;
/*  70 */         while ((length = input.read(buffer)) >= 0) {
/*  71 */           byte[] data = new byte[length];
/*  72 */           System.arraycopy(buffer, 0, data, 0, length);
/*  73 */           output.write(encodeBinary(data).getBytes());
/*  74 */           output.write(System.getProperty("line.separator").getBytes());
/*     */         }
/*     */ 
/*  77 */         result = true;
/*     */       }
/*     */     } finally {
/*  80 */       if (input != null) {
/*     */         try {
/*  82 */           input.close();
/*     */         } catch (Exception ignore) {
/*  84 */           LOGGER.debug("IGNORE: {}" + ignore);
/*     */         }
/*     */       }
/*  87 */       if (output != null) {
/*     */         try {
/*  89 */           output.close();
/*     */         } catch (Exception ignore) {
/*  91 */           LOGGER.debug("IGNORE: {}" + ignore);
/*     */         }
/*     */       }
/*     */     }
/*  95 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean decryptFile(String source, String target)
/*     */     throws Exception
/*     */   {
/* 109 */     boolean result = false;
/*     */ 
/* 111 */     String sourceFile = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 112 */     String targetFile = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 113 */     File srcFile = new File(sourceFile);
/*     */ 
/* 115 */     BufferedReader input = null;
/* 116 */     BufferedOutputStream output = null;
/*     */ 
/* 119 */     String line = null;
/*     */     try
/*     */     {
/* 122 */       if ((srcFile.exists()) && (srcFile.isFile()))
/*     */       {
/* 124 */         input = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));
/* 125 */         output = new BufferedOutputStream(new FileOutputStream(targetFile));
/*     */ 
/* 127 */         while ((line = input.readLine()) != null) {
/* 128 */           byte[] data = line.getBytes();
/* 129 */           output.write(decodeBinary(new String(data)));
/*     */         }
/*     */ 
/* 132 */         result = true;
/*     */       }
/*     */     } finally {
/* 135 */       if (input != null) {
/*     */         try {
/* 137 */           input.close();
/*     */         } catch (Exception ignore) {
/* 139 */           LOGGER.debug("IGNORE: {}" + ignore);
/*     */         }
/*     */       }
/* 142 */       if (output != null) {
/*     */         try {
/* 144 */           output.close();
/*     */         } catch (Exception ignore) {
/* 146 */           LOGGER.debug("IGNORE: {}" + ignore);
/*     */         }
/*     */       }
/*     */     }
/* 150 */     return result;
/*     */   }
/*     */ 
/*     */   public static String encodeBinary(byte[] data)
/*     */     throws Exception
/*     */   {
/* 161 */     if (data == null) {
/* 162 */       return "";
/*     */     }
/*     */ 
/* 165 */     return new String(Base64.encodeBase64(data));
/*     */   }
/*     */ 
/*     */   public static String encode(String data)
/*     */     throws Exception
/*     */   {
/* 176 */     return encodeBinary(data.getBytes());
/*     */   }
/*     */ 
/*     */   public static byte[] decodeBinary(String data)
/*     */     throws Exception
/*     */   {
/* 187 */     return Base64.decodeBase64(data.getBytes());
/*     */   }
/*     */ 
/*     */   public static String decode(String data)
/*     */     throws Exception
/*     */   {
/* 198 */     return new String(decodeBinary(data));
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static String encryptPassword(String data)
/*     */     throws Exception
/*     */   {
/* 213 */     if (data == null) {
/* 214 */       return "";
/*     */     }
/*     */ 
/* 217 */     byte[] plainText = null;
/* 218 */     byte[] hashValue = null;
/* 219 */     plainText = data.getBytes();
/*     */ 
/* 221 */     MessageDigest md = MessageDigest.getInstance("SHA-256");
/*     */ 
/* 234 */     hashValue = md.digest(plainText);
/*     */ 
/* 240 */     return new String(Base64.encodeBase64(hashValue));
/*     */   }
/*     */ 
/*     */   public static String encryptPassword(String password, String id)
/*     */     throws Exception
/*     */   {
/* 253 */     if (password == null) {
/* 254 */       return "";
/*     */     }
/*     */ 
/* 257 */     byte[] hashValue = null;
/*     */ 
/* 259 */     MessageDigest md = MessageDigest.getInstance("SHA-256");
/*     */ 
/* 261 */     md.reset();
/* 262 */     md.update(id.getBytes());
/*     */ 
/* 264 */     hashValue = md.digest(password.getBytes());
/*     */ 
/* 266 */     return new String(Base64.encodeBase64(hashValue));
/*     */   }
/*     */ 
/*     */   public static String encryptPassword(String data, byte[] salt)
/*     */     throws Exception
/*     */   {
/* 278 */     if (data == null) {
/* 279 */       return "";
/*     */     }
/*     */ 
/* 282 */     byte[] hashValue = null;
/*     */ 
/* 284 */     MessageDigest md = MessageDigest.getInstance("SHA-256");
/*     */ 
/* 286 */     md.reset();
/* 287 */     md.update(salt);
/*     */ 
/* 289 */     hashValue = md.digest(data.getBytes());
/*     */ 
/* 291 */     return new String(Base64.encodeBase64(hashValue));
/*     */   }
/*     */ 
/*     */   public static boolean checkPassword(String data, String encoded, byte[] salt)
/*     */     throws Exception
/*     */   {
/* 303 */     byte[] hashValue = null;
/*     */ 
/* 305 */     MessageDigest md = MessageDigest.getInstance("SHA-256");
/*     */ 
/* 307 */     md.reset();
/* 308 */     md.update(salt);
/* 309 */     hashValue = md.digest(data.getBytes());
/*     */ 
/* 311 */     return MessageDigest.isEqual(hashValue, Base64.decodeBase64(encoded.getBytes()));
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovFileScrty
 * JD-Core Version:    0.6.2
 */