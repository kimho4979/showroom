/*     */ package egovframework.cms.cmm.service;
/*     */ 
/*     */ import egovframework.cms.cmm.util.EgovBasicLogger;
/*     */ import egovframework.cms.cmm.util.EgovResourceCloseHelper;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class EgovFileMntrg extends Thread
/*     */ {
/*     */   public static final long DEFAULT_DELAY = 30000L;
/*     */   static final int MAX_STR_LEN = 1024;
/*     */   protected String filename;
/*  60 */   protected long delay = 30000L;
/*     */   File file;
/*     */   File logFile;
/*  64 */   long lastModif = 0L;
/*  65 */   boolean warnedAlready = false;
/*  66 */   boolean interrupted = false;
/*  67 */   List<String> realOriginalList = new ArrayList();
/*  68 */   List<String> originalList = new ArrayList();
/*  69 */   List<String> currentList = new ArrayList();
/*  70 */   List<String> changedList = new ArrayList();
/*  71 */   List<String> totalChangedList = new ArrayList();
/*     */ 
/*  73 */   int cnt = 0;
/*     */ 
/*     */   protected EgovFileMntrg(String filename, File logFile)
/*     */   {
/*  84 */     this.logFile = logFile;
/*  85 */     this.filename = filename;
/*  86 */     this.file = new File(filename);
/*     */ 
/*  88 */     File[] fList = this.file.listFiles();
/*  89 */     for (int i = 0; i < fList.length; i++) {
/*  90 */       this.realOriginalList.add(fList[i].getAbsolutePath() + "$" + getLastModifiedTime(fList[i]) + "$" + (fList[i].length() / 1024L > 0L ? fList[i].length() / 1024L : 1L) + "KB");
/*  91 */       writeLog("ORI_" + fList[i].getAbsolutePath() + "$" + getLastModifiedTime(fList[i]) + "$" + (fList[i].length() / 1024L > 0L ? fList[i].length() / 1024L : 1L) + "KB");
/*     */     }
/*  93 */     this.originalList = new ArrayList(this.realOriginalList);
/*  94 */     writeLog("START");
/*  95 */     setDaemon(true);
/*  96 */     checkAndConfigure();
/*     */   }
/*     */ 
/*     */   public void setDelay(long delay)
/*     */   {
/* 108 */     this.delay = delay;
/*     */   }
/*     */ 
/*     */   protected void doOnChange(List<String> changedList)
/*     */   {
/* 119 */     for (int i = 0; i < changedList.size(); i++) {
/* 120 */       writeLog((String)changedList.get(i));
/*     */     }
/* 122 */     changedList.clear();
/* 123 */     this.originalList = new ArrayList(this.currentList);
/* 124 */     this.cnt += 1;
/*     */   }
/*     */ 
/*     */   protected void checkAndConfigure()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       this.currentList.clear();
/* 138 */       this.file = new File(this.filename);
/*     */ 
/* 140 */       File[] fList = this.file.listFiles();
/* 141 */       for (int i = 0; i < fList.length; i++) {
/* 142 */         this.currentList.add(fList[i].getAbsolutePath() + "$" + getLastModifiedTime(fList[i]) + "$" + (fList[i].length() / 1024L > 0L ? fList[i].length() / 1024L : 1L) + "KB");
/*     */       }
/*     */ 
/* 152 */       boolean isSame = false;
/* 153 */       boolean isNew = true;
/* 154 */       boolean isDel = true;
/* 155 */       String str1 = "";
/* 156 */       String str2 = "";
/*     */ 
/* 160 */       for (int i = 0; i < this.originalList.size(); i++) {
/* 161 */         for (int j = 0; j < this.currentList.size(); j++) {
/* 162 */           str1 = (String)this.originalList.get(i);
/* 163 */           str2 = (String)this.currentList.get(j);
/* 164 */           if (str1.substring(0, str1.indexOf("$")).equals(str2.substring(0, str2.indexOf("$")))) {
/* 165 */             isDel = false;
/*     */           }
/*     */         }
/* 168 */         if (isDel) {
/* 169 */           this.changedList.add("DEL$" + (String)this.originalList.get(i));
/*     */         }
/* 171 */         isDel = true;
/*     */       }
/*     */ 
/* 175 */       for (int i = 0; i < this.currentList.size(); i++) {
/* 176 */         for (int j = 0; j < this.originalList.size(); j++) {
/* 177 */           if (((String)this.currentList.get(i)).equals((String)this.originalList.get(j))) {
/* 178 */             isSame = true;
/*     */           }
/* 180 */           str1 = (String)this.currentList.get(i);
/* 181 */           str2 = (String)this.originalList.get(j);
/* 182 */           if (str1.substring(0, str1.indexOf("$")).equals(str2.substring(0, str2.indexOf("$")))) {
/* 183 */             isNew = false;
/*     */           }
/*     */         }
/* 186 */         if (!isSame) {
/* 187 */           if (isNew) {
/* 188 */             this.changedList.add("NEW$" + (String)this.currentList.get(i));
/*     */           }
/*     */           else {
/* 191 */             this.changedList.add("MODI$" + (String)this.currentList.get(i));
/*     */           }
/*     */         }
/*     */ 
/* 195 */         isSame = false;
/* 196 */         isNew = true;
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (RuntimeException e)
/*     */     {
/* 202 */       EgovBasicLogger.debug("Checking error", e);
/*     */     }
/*     */ 
/* 205 */     if (this.changedList.size() > 0)
/*     */     {
/* 207 */       doOnChange(this.changedList);
/*     */     }
/*     */ 
/* 210 */     if (isEnd())
/*     */     {
/* 212 */       this.interrupted = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 223 */     while (!this.interrupted) {
/*     */       try {
/* 225 */         Thread.sleep(this.delay);
/*     */       } catch (InterruptedException e) {
/* 227 */         EgovBasicLogger.ignore("Interrupted Exception", e);
/*     */       }
/* 229 */       checkAndConfigure();
/*     */     }
/* 231 */     if (this.interrupted)
/* 232 */       interrupt();
/*     */   }
/*     */ 
/*     */   public static String getLastModifiedTime(File f)
/*     */   {
/* 244 */     long date = f.lastModified();
/* 245 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd:HH:mm:ss", Locale.KOREA);
/* 246 */     return dateFormat.format(new Date(date));
/*     */   }
/*     */ 
/*     */   public boolean writeLog(String logStr)
/*     */   {
/* 257 */     boolean result = false;
/*     */ 
/* 259 */     FileWriter fWriter = null;
/* 260 */     BufferedWriter bWriter = null;
/* 261 */     BufferedReader br = null;
/*     */     try {
/* 263 */       fWriter = new FileWriter(this.logFile, true);
/* 264 */       bWriter = new BufferedWriter(fWriter);
/* 265 */       br = new BufferedReader(new StringReader(logStr));
/* 266 */       String line = "";
/* 267 */       while ((line = br.readLine()) != null) {
/* 268 */         if (line.length() <= 1024) {
/* 269 */           bWriter.write(line + "\n", 0, line.length() + 1);
/*     */         }
/*     */       }
/* 272 */       result = true;
/*     */     } catch (IOException e) {
/* 274 */       throw new RuntimeException("File IO exception", e);
/*     */     } finally {
/* 276 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 276 */         br, bWriter, fWriter });
/*     */     }
/*     */ 
/* 279 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean isEnd()
/*     */   {
/* 290 */     boolean isEnd = false;
/* 291 */     String lastStr = "";
/* 292 */     BufferedReader br = null;
/* 293 */     FileReader fr = null;
/*     */ 
/* 296 */     label130: 
/*     */     try { if (this.logFile.exists())
/*     */       {
/* 299 */         fr = new FileReader(this.logFile);
/* 300 */         br = new BufferedReader(fr);
/*     */ 
/* 302 */         String line = "";
/* 303 */         while ((line = br.readLine()) != null) {
/* 304 */           if (line.length() <= 1024) {
/* 305 */             lastStr = line;
/*     */           }
/*     */         }
/* 308 */         if (lastStr.equals("END")) {
/* 309 */           isEnd = true;
/*     */ 
/* 311 */           break label130;
/*     */         }
/*     */       } else { isEnd = true; }
/*     */     } catch (IOException e)
/*     */     {
/* 316 */       throw new RuntimeException("File IO exception", e);
/*     */     } finally {
/* 318 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 318 */         br, fr });
/*     */     }
/* 320 */     return isEnd;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovFileMntrg
 * JD-Core Version:    0.6.2
 */