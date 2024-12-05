/*      */ package egovframework.cms.cmm.service;
/*      */ 
/*      */ import egovframework.cms.cmm.EgovWebUtil;
/*      */ import egovframework.cms.cmm.util.EgovResourceCloseHelper;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.Closeable;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintStream;
/*      */ import java.io.StringReader;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Vector;
/*      */ 
/*      */ public class EgovFileTool
/*      */ {
/*      */   static final long BUFFER_SIZE = 1024L;
/*   42 */   static final char FILE_SEPARATOR = File.separatorChar;
/*      */   static final char ACCESS_READ = 'R';
/*      */   static final char ACCESS_SYS = 'S';
/*      */   static final char ACCESS_HIDE = 'H';
/*      */   static final int MAX_STR_LEN = 1024;
/*      */ 
/*      */   public static boolean getExistDirectory(String targetDirPath)
/*      */     throws Exception
/*      */   {
/*   64 */     if ((targetDirPath == null) || (targetDirPath.equals(""))) {
/*   65 */       return false;
/*      */     }
/*      */ 
/*   68 */     boolean result = false;
/*   69 */     File f = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/*   70 */     if ((f.exists()) && (f.isDirectory())) {
/*   71 */       result = true;
/*      */     }
/*      */ 
/*   74 */     return result;
/*      */   }
/*      */ 
/*      */   public static List<String> getExistDirectory(String baseDirPath, String targetDirPath, int cnt)
/*      */     throws Exception
/*      */   {
/*   90 */     if ((baseDirPath == null) || (baseDirPath.equals("")) || (targetDirPath == null) || (targetDirPath.equals("")) || (cnt == 0)) {
/*   91 */       return new ArrayList();
/*      */     }
/*   93 */     int dirCnt = 0;
/*   94 */     if (cnt < 0)
/*   95 */       dirCnt = 21474846;
/*      */     else {
/*   97 */       dirCnt = cnt;
/*      */     }
/*      */ 
/*  101 */     List result = new ArrayList();
/*      */ 
/*  103 */     List subResult = new ArrayList();
/*      */ 
/*  105 */     int dirFindCnt = 0;
/*  106 */     boolean isExist = false;
/*  107 */     String[] dirList = null;
/*  108 */     String subDirPath = "";
/*  109 */     File f = null;
/*      */ 
/*  111 */     f = new File(EgovWebUtil.filePathBlackList(baseDirPath));
/*  112 */     isExist = f.exists();
/*      */ 
/*  114 */     if (isExist) {
/*  115 */       dirList = f.list();
/*      */     }
/*      */ 
/*  118 */     for (int i = 0; (dirList != null) && (i < dirList.length); i++)
/*      */     {
/*  120 */       subDirPath = baseDirPath + "/" + dirList[i];
/*      */ 
/*  124 */       f = new File(EgovWebUtil.filePathBlackList(subDirPath));
/*      */ 
/*  127 */       if (targetDirPath.equals(dirList[i]))
/*      */       {
/*  129 */         if (new File(EgovWebUtil.filePathBlackList(baseDirPath) + "/" + dirList[i]).isDirectory()) {
/*  130 */           dirFindCnt++;
/*  131 */           result.add(baseDirPath + "/" + dirList[i]);
/*  132 */           if (dirFindCnt == dirCnt)
/*      */           {
/*      */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  139 */       int subCnt = dirCnt - dirFindCnt;
/*  140 */       if (f.isDirectory())
/*      */       {
/*  142 */         subResult = getExistDirectory(subDirPath, targetDirPath, subCnt);
/*      */ 
/*  144 */         dirFindCnt += subResult.size();
/*      */ 
/*  146 */         if (dirCnt <= dirFindCnt) {
/*  147 */           for (int j = 0; j < subResult.size(); j++) {
/*  148 */             result.add((String)subResult.get(j));
/*      */           }
/*      */ 
/*  151 */           break;
/*      */         }
/*  153 */         for (int j = 0; j < subResult.size(); j++) {
/*  154 */           result.add((String)subResult.get(j));
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  160 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean getExistDirectory(String targetDirPath, String fromDate, String toDate)
/*      */     throws Exception
/*      */   {
/*  176 */     if ((targetDirPath == null) || (targetDirPath.equals("")) || (fromDate == null) || (fromDate.equals("")) || (toDate == null) || (toDate.equals(""))) {
/*  177 */       return false;
/*      */     }
/*      */ 
/*  180 */     boolean result = false;
/*  181 */     String lastModifyedDate = "";
/*  182 */     File f = null;
/*      */ 
/*  184 */     f = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/*  185 */     lastModifyedDate = getLastModifiedDateFromFile(f);
/*      */ 
/*  187 */     if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate)) && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
/*  188 */       result = true;
/*      */     }
/*      */ 
/*  191 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean getExistDirectory(String targetDirPath, String ownerName)
/*      */     throws Exception
/*      */   {
/*  206 */     if ((targetDirPath == null) || (targetDirPath.equals("")) || (ownerName == null) || (ownerName.equals(""))) {
/*  207 */       return false;
/*      */     }
/*      */ 
/*  210 */     boolean result = false;
/*      */ 
/*  216 */     String realOwner = getOwner(targetDirPath);
/*  217 */     if (ownerName.equals(realOwner))
/*  218 */       result = true;
/*      */     else {
/*  220 */       result = false;
/*      */     }
/*      */ 
/*  223 */     return result;
/*      */   }
/*      */ 
/*      */   public static String getLastModifiedDateFromFile(File f)
/*      */   {
/*  236 */     String result = "";
/*      */ 
/*  238 */     if (f.exists()) {
/*  239 */       long date = f.lastModified();
/*  240 */       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
/*  241 */       result = dateFormat.format(new Date(date));
/*      */     } else {
/*  243 */       result = "";
/*      */     }
/*      */ 
/*  246 */     return result;
/*      */   }
/*      */ 
/*      */   public static String getLastModifiedDateFromFile(String filePath)
/*      */   {
/*  259 */     File f = null;
/*  260 */     String result = "";
/*  261 */     f = new File(EgovWebUtil.filePathBlackList(filePath));
/*  262 */     result = getLastModifiedDateFromFile(f);
/*      */ 
/*  264 */     return result;
/*      */   }
/*      */ 
/*      */   public static List<String> getLastDirectoryForModifiedDate(String baseDirPath, String fromDate, String toDate)
/*      */   {
/*  280 */     if ((baseDirPath == null) || (baseDirPath.equals("")) || (fromDate == null) || (fromDate.equals("")) || (toDate == null) || (toDate.equals(""))) {
/*  281 */       return new ArrayList();
/*      */     }
/*      */ 
/*  284 */     File f = null;
/*  285 */     File childFile = null;
/*      */ 
/*  287 */     String subDirPath = "";
/*  288 */     List childResult = null;
/*  289 */     List result = new ArrayList();
/*      */ 
/*  291 */     f = new File(EgovWebUtil.filePathBlackList(baseDirPath));
/*  292 */     String[] subDirList = f.list();
/*  293 */     for (int i = 0; i < subDirList.length; i++)
/*      */     {
/*  295 */       subDirPath = baseDirPath + "/" + subDirList[i];
/*  296 */       childFile = new File(EgovWebUtil.filePathBlackList(subDirPath));
/*  297 */       if (childFile.isDirectory())
/*      */       {
/*  299 */         String lastModifyedDate = getLastModifiedDateFromFile(childFile);
/*  300 */         if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate)) && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
/*  301 */           result.add(baseDirPath + "/" + subDirList[i]);
/*      */         }
/*  303 */         childResult = getLastDirectoryForModifiedDate(baseDirPath + "/" + subDirList[i], fromDate, toDate);
/*      */ 
/*  305 */         for (int j = 0; j < childResult.size(); j++) {
/*  306 */           result.add((String)childResult.get(j));
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  311 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean canRead(String filePath)
/*      */   {
/*  325 */     if ((filePath == null) || (filePath.equals(""))) {
/*  326 */       return false;
/*      */     }
/*      */ 
/*  329 */     File f = null;
/*  330 */     boolean result = false;
/*  331 */     f = new File(EgovWebUtil.filePathBlackList(filePath));
/*  332 */     if (f.exists()) {
/*  333 */       result = f.canRead();
/*      */     }
/*      */ 
/*  336 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean canWrite(String filePath)
/*      */   {
/*  350 */     if ((filePath == null) || (filePath.equals(""))) {
/*  351 */       return false;
/*      */     }
/*      */ 
/*  354 */     File f = null;
/*  355 */     boolean result = false;
/*  356 */     f = new File(EgovWebUtil.filePathBlackList(filePath));
/*  357 */     if (f.exists()) {
/*  358 */       result = f.canWrite();
/*      */     }
/*      */ 
/*  361 */     return result;
/*      */   }
/*      */ 
/*      */   public static String getName(String filePath)
/*      */   {
/*  375 */     if ((filePath == null) || (filePath.equals(""))) {
/*  376 */       return "";
/*      */     }
/*      */ 
/*  379 */     File f = null;
/*  380 */     String result = "";
/*      */ 
/*  382 */     f = new File(EgovWebUtil.filePathBlackList(filePath));
/*  383 */     if (f.exists()) {
/*  384 */       result = f.getName();
/*      */     }
/*      */ 
/*  387 */     return result;
/*      */   }
/*      */ 
/*      */   public static String deletePath(String filePath)
/*      */   {
/*  400 */     File file = new File(EgovWebUtil.filePathBlackList(filePath));
/*  401 */     String result = "";
/*      */ 
/*  403 */     if (file.exists()) {
/*  404 */       result = file.getAbsolutePath();
/*  405 */       if (!file.delete()) {
/*  406 */         result = "";
/*      */       }
/*      */     }
/*      */ 
/*  410 */     return result;
/*      */   }
/*      */ 
/*      */   public static String createDirectory(String dirPath)
/*      */   {
/*  423 */     File file = new File(EgovWebUtil.filePathBlackList(dirPath));
/*  424 */     String result = "";
/*      */     try {
/*  426 */       if (!file.exists()) {
/*  427 */         file.createNewFile();
/*  428 */         file.getAbsolutePath();
/*      */       }
/*      */     }
/*      */     catch (IOException e) {
/*  432 */       throw new RuntimeException(e);
/*      */     }
/*      */ 
/*  435 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean checkFileExstByName(String dir, String file)
/*      */     throws Exception
/*      */   {
/*  449 */     boolean result = false;
/*      */ 
/*  452 */     String drctry = dir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  453 */     File srcDrctry = new File(EgovWebUtil.filePathBlackList(drctry));
/*      */ 
/*  456 */     if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
/*      */     {
/*  459 */       File[] fileArray = srcDrctry.listFiles();
/*  460 */       List list = getSubFilesByName(fileArray, file);
/*  461 */       if ((list != null) && (list.size() > 0)) {
/*  462 */         result = true;
/*      */       }
/*      */     }
/*      */ 
/*  466 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean checkFileExstByExtnt(String dir, String eventn)
/*      */     throws Exception
/*      */   {
/*  480 */     boolean result = false;
/*      */ 
/*  483 */     String drctry = dir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  484 */     File srcDrctry = new File(EgovWebUtil.filePathBlackList(drctry));
/*      */ 
/*  487 */     if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
/*      */     {
/*  490 */       File[] fileArray = srcDrctry.listFiles();
/*  491 */       List list = getSubFilesByExtnt(fileArray, eventn);
/*  492 */       if ((list != null) && (list.size() > 0)) {
/*  493 */         result = true;
/*      */       }
/*      */     }
/*      */ 
/*  497 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean checkFileExstByOwner(String dir, String owner)
/*      */     throws Exception
/*      */   {
/*  511 */     boolean result = false;
/*      */ 
/*  514 */     String drctry = dir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  515 */     File srcDrctry = new File(EgovWebUtil.filePathBlackList(drctry));
/*      */ 
/*  518 */     if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
/*      */     {
/*  521 */       File[] fileArray = srcDrctry.listFiles();
/*  522 */       List list = getSubFilesByOwner(fileArray, owner);
/*  523 */       if ((list != null) && (list.size() > 0)) {
/*  524 */         result = true;
/*      */       }
/*      */     }
/*      */ 
/*  528 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean checkFileExstByUpdtPd(String dir, String updtFrom, String updtTo)
/*      */     throws Exception
/*      */   {
/*  543 */     boolean result = false;
/*      */ 
/*  546 */     String drctry = dir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  547 */     File srcDrctry = new File(EgovWebUtil.filePathBlackList(drctry));
/*      */ 
/*  550 */     if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
/*      */     {
/*  553 */       File[] fileArray = srcDrctry.listFiles();
/*  554 */       List list = getSubFilesByUpdtPd(fileArray, updtFrom, updtTo);
/*  555 */       if ((list != null) && (list.size() > 0)) {
/*  556 */         result = true;
/*      */       }
/*      */     }
/*      */ 
/*  560 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean checkFileExstBySize(String dir, long sizeFrom, long sizeTo)
/*      */     throws Exception
/*      */   {
/*  575 */     boolean result = false;
/*      */ 
/*  578 */     String drctry = dir.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  579 */     File srcDrctry = new File(EgovWebUtil.filePathBlackList(drctry));
/*      */ 
/*  582 */     if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
/*      */     {
/*  585 */       File[] fileArray = srcDrctry.listFiles();
/*  586 */       List list = getSubFilesBySize(fileArray, sizeFrom, sizeTo);
/*  587 */       if ((list != null) && (list.size() > 0)) {
/*  588 */         result = true;
/*      */       }
/*      */     }
/*      */ 
/*  592 */     return result;
/*      */   }
/*      */ 
/*      */   public static List<String> getSubFilesByAll(File[] fileArray)
/*      */     throws Exception
/*      */   {
/*  604 */     ArrayList list = new ArrayList();
/*      */ 
/*  606 */     for (int i = 0; i < fileArray.length; i++)
/*      */     {
/*  608 */       if (fileArray[i].isDirectory()) {
/*  609 */         File[] tmpArray = fileArray[i].listFiles();
/*  610 */         list.addAll(getSubFilesByAll(tmpArray));
/*      */       }
/*      */       else {
/*  613 */         list.add(fileArray[i].getAbsolutePath());
/*      */       }
/*      */     }
/*      */ 
/*  617 */     return list;
/*      */   }
/*      */ 
/*      */   public static List<String> getSubFilesByName(File[] fileArray, String file)
/*      */     throws Exception
/*      */   {
/*  630 */     List list = new ArrayList();
/*      */ 
/*  632 */     for (int i = 0; i < fileArray.length; i++)
/*      */     {
/*  634 */       if (fileArray[i].isDirectory()) {
/*  635 */         File[] tmpArray = fileArray[i].listFiles();
/*  636 */         list.addAll(getSubFilesByName(tmpArray, file));
/*      */       }
/*  639 */       else if (fileArray[i].getName().equals(file)) {
/*  640 */         list.add(fileArray[i].getAbsolutePath());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  645 */     return list;
/*      */   }
/*      */ 
/*      */   public static List<String> getSubFilesByExtnt(File[] fileArray, String extnt)
/*      */     throws Exception
/*      */   {
/*  658 */     List list = new ArrayList();
/*      */ 
/*  660 */     for (int i = 0; i < fileArray.length; i++)
/*      */     {
/*  662 */       if (fileArray[i].isDirectory()) {
/*  663 */         File[] tmpArray = fileArray[i].listFiles();
/*  664 */         list.addAll(getSubFilesByExtnt(tmpArray, extnt));
/*      */       }
/*  667 */       else if (fileArray[i].getName().indexOf(extnt) != -1) {
/*  668 */         list.add(fileArray[i].getAbsolutePath());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  673 */     return list;
/*      */   }
/*      */ 
/*      */   public static List<String> getSubFilesByUpdtPd(File[] fileArray, String updtFrom, String updtTo)
/*      */     throws Exception
/*      */   {
/*  687 */     List list = new ArrayList();
/*      */ 
/*  689 */     for (int i = 0; i < fileArray.length; i++)
/*      */     {
/*  691 */       if (fileArray[i].isDirectory()) {
/*  692 */         File[] tmpArray = fileArray[i].listFiles();
/*  693 */         list.addAll(getSubFilesByUpdtPd(tmpArray, updtFrom, updtTo));
/*      */       }
/*      */       else
/*      */       {
/*  697 */         long date = fileArray[i].lastModified();
/*  698 */         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
/*  699 */         String lastUpdtDate = dateFormat.format(new Date(date));
/*      */ 
/*  701 */         if ((Integer.parseInt(lastUpdtDate) >= Integer.parseInt(updtFrom)) && (Integer.parseInt(lastUpdtDate) <= Integer.parseInt(updtTo))) {
/*  702 */           list.add(fileArray[i].getAbsolutePath());
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  707 */     return list;
/*      */   }
/*      */ 
/*      */   public static List<String> getSubFilesBySize(File[] fileArray, long sizeFrom, long sizeTo)
/*      */     throws Exception
/*      */   {
/*  721 */     List list = new ArrayList();
/*      */ 
/*  723 */     for (int i = 0; i < fileArray.length; i++)
/*      */     {
/*  725 */       if (fileArray[i].isDirectory()) {
/*  726 */         File[] tmpArray = fileArray[i].listFiles();
/*  727 */         list.addAll(getSubFilesBySize(tmpArray, sizeFrom, sizeTo));
/*      */       }
/*      */       else
/*      */       {
/*  731 */         long size = fileArray[i].length();
/*      */ 
/*  733 */         if ((size >= sizeFrom * 1024L) && (size <= sizeTo * 1024L)) {
/*  734 */           list.add(fileArray[i].getAbsolutePath());
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  739 */     return list;
/*      */   }
/*      */ 
/*      */   public static List<String> getSubFilesByOwner(File[] fileArray, String owner)
/*      */     throws Exception
/*      */   {
/*  752 */     List list = new ArrayList();
/*      */ 
/*  754 */     for (int i = 0; i < fileArray.length; i++)
/*      */     {
/*  756 */       if (fileArray[i].isDirectory()) {
/*  757 */         File[] tmpArray = fileArray[i].listFiles();
/*  758 */         List list1 = getSubFilesByOwner(tmpArray, owner);
/*  759 */         if (list1 != null) {
/*  760 */           list.addAll(list1);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  765 */         String fullpath = EgovWebUtil.filePathBlackList(fileArray[i].getAbsolutePath());
/*  766 */         Process p = null;
/*  767 */         if (Globals.OS_TYPE.equals("UNIX")) {
/*  768 */           String[] command = { EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".getDrctryByOwner"), 
/*  769 */             fullpath.substring(0, fullpath.lastIndexOf("/")), fullpath.substring(fullpath.lastIndexOf("/"), fullpath.length()), owner };
/*  770 */           p = Runtime.getRuntime().exec(command);
/*  771 */           p.waitFor();
/*  772 */         } else if (Globals.OS_TYPE.equals("WINDOWS")) {
/*  773 */           String command = EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".getDrctryByOwner");
/*  774 */           p = Runtime.getRuntime().exec(command);
/*  775 */           p.waitFor();
/*      */         }
/*      */ 
/*  778 */         if ((p != null) && (p.exitValue() != 0)) {
/*  779 */           BufferedReader b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
/*      */           try
/*      */           {
/*  781 */             while (b_err.ready());
/*      */           }
/*      */           finally
/*      */           {
/*  786 */             EgovResourceCloseHelper.close(new Closeable[] { 
/*  786 */               b_err });
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  791 */           BufferedReader b_out = null;
/*      */           try {
/*  793 */             b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*  794 */             while (b_out.ready())
/*      */             {
/*  796 */               String tmpStr = b_out.readLine();
/*  797 */               if ((tmpStr != null) && ("".equals(tmpStr)) && (tmpStr.length() <= 1024))
/*  798 */                 list.add(fileArray[i].getAbsolutePath());
/*      */             }
/*      */           }
/*      */           finally {
/*  802 */             EgovResourceCloseHelper.close(new Closeable[] { 
/*  802 */               b_out });
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  808 */     return list;
/*      */   }
/*      */ 
/*      */   public static String createNewDirectory(String dirPath)
/*      */   {
/*  823 */     if ((dirPath == null) || (dirPath.equals(""))) {
/*  824 */       return "";
/*      */     }
/*      */ 
/*  827 */     File file = new File(EgovWebUtil.filePathBlackList(dirPath));
/*  828 */     String result = "";
/*      */ 
/*  830 */     if (file.exists())
/*      */     {
/*  832 */       if (file.isFile())
/*      */       {
/*  834 */         if (file.mkdirs())
/*  835 */           result = file.getAbsolutePath();
/*      */       }
/*      */       else {
/*  838 */         result = file.getAbsolutePath();
/*      */       }
/*      */ 
/*      */     }
/*  842 */     else if (file.mkdirs()) {
/*  843 */       result = file.getAbsolutePath();
/*      */     }
/*      */ 
/*  847 */     return result;
/*      */   }
/*      */ 
/*      */   public static String createNewFile(String filePath)
/*      */   {
/*  862 */     if ((filePath == null) || (filePath.equals(""))) {
/*  863 */       return "";
/*      */     }
/*      */ 
/*  866 */     File file = new File(EgovWebUtil.filePathBlackList(filePath));
/*  867 */     String result = "";
/*      */     try {
/*  869 */       if (file.exists()) {
/*  870 */         result = filePath;
/*      */       }
/*      */       else {
/*  873 */         new File(file.getParent()).mkdirs();
/*  874 */         if (file.createNewFile())
/*  875 */           result = file.getAbsolutePath();
/*      */       }
/*      */     }
/*      */     catch (IOException e) {
/*  879 */       throw new RuntimeException(e);
/*      */     }
/*      */ 
/*  882 */     return result;
/*      */   }
/*      */ 
/*      */   public static String deleteDirectory(String dirDeletePath)
/*      */   {
/*  897 */     if ((dirDeletePath == null) || (dirDeletePath.equals(""))) {
/*  898 */       return "";
/*      */     }
/*  900 */     String result = "";
/*  901 */     File file = new File(EgovWebUtil.filePathBlackList(dirDeletePath));
/*  902 */     if (file.isDirectory()) {
/*  903 */       String[] fileList = file.list();
/*      */ 
/*  905 */       for (int i = 0; i < fileList.length; i++)
/*      */       {
/*  908 */         File f = new File(EgovWebUtil.filePathBlackList(dirDeletePath) + "/" + fileList[i]);
/*  909 */         if (f.isFile())
/*      */         {
/*  911 */           f.delete();
/*      */         }
/*      */         else {
/*  914 */           deleteDirectory(dirDeletePath + "/" + fileList[i]);
/*      */         }
/*      */       }
/*      */ 
/*  918 */       result = deletePath(dirDeletePath);
/*      */     } else {
/*  920 */       result = "";
/*      */     }
/*      */ 
/*  923 */     return result;
/*      */   }
/*      */ 
/*      */   public static String deleteFile(String fileDeletePath)
/*      */   {
/*  938 */     if ((fileDeletePath == null) || (fileDeletePath.equals(""))) {
/*  939 */       return "";
/*      */     }
/*  941 */     String result = "";
/*  942 */     File file = new File(EgovWebUtil.filePathBlackList(fileDeletePath));
/*  943 */     if (file.isFile())
/*  944 */       result = deletePath(fileDeletePath);
/*      */     else {
/*  946 */       result = "";
/*      */     }
/*      */ 
/*  949 */     return result;
/*      */   }
/*      */ 
/*      */   public static void deleteDummyFile()
/*      */   {
/*  956 */     File file = new File(Globals.DISTRIBUTE_UPLOAD_PATH + "dummy");
/*  957 */     if (file.exists()) {
/*  958 */       if (file.isDirectory()) {
/*  959 */         File[] files = file.listFiles();
/*      */ 
/*  961 */         for (int i = 0; i < files.length; i++)
/*  962 */           if (!files[i].delete())
/*      */           {
/*  964 */             System.out.println(files[i].getName() + "삭제실패");
/*      */           }
/*      */       }
/*  967 */       if (!file.delete())
/*      */       {
/*  969 */         System.out.println("파일삭제 실패");
/*      */       }
/*      */     } else {
/*  972 */       System.out.println("파일이 존재하지 않습니다.");
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean checkReadAuth(String file)
/*      */     throws Exception
/*      */   {
/*  987 */     boolean result = false;
/*      */ 
/*  990 */     String file1 = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*  991 */     File srcFile = new File(EgovWebUtil.filePathBlackList(file1));
/*      */ 
/*  994 */     if (srcFile.exists())
/*      */     {
/*  996 */       result = srcFile.canRead();
/*      */     }
/*      */ 
/*  999 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean checkWriteAuth(String file)
/*      */     throws Exception
/*      */   {
/* 1012 */     boolean result = false;
/*      */ 
/* 1015 */     String file1 = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1016 */     File srcFile = new File(EgovWebUtil.filePathBlackList(file1));
/*      */ 
/* 1019 */     if (srcFile.exists())
/*      */     {
/* 1021 */       result = srcFile.canWrite();
/*      */     }
/*      */ 
/* 1024 */     return result;
/*      */   }
/*      */ 
/*      */   public static List<String> getFileListByDate(String drctry, String updtDate)
/*      */     throws Exception
/*      */   {
/* 1038 */     List list = null;
/*      */ 
/* 1041 */     String drctry1 = drctry.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1042 */     File file = new File(EgovWebUtil.filePathBlackList(drctry1));
/*      */ 
/* 1045 */     if ((file.exists()) && (file.isDirectory())) {
/* 1046 */       File[] fileArray = file.listFiles();
/* 1047 */       list = getSubFilesByDate(fileArray, updtDate);
/*      */     }
/*      */ 
/* 1050 */     return list;
/*      */   }
/*      */ 
/*      */   public static List<String> getFileListByUpdtPd(String drctry, String updtFrom, String updtTo)
/*      */     throws Exception
/*      */   {
/* 1065 */     List list = null;
/*      */ 
/* 1068 */     String drctry1 = drctry.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1069 */     File file = new File(EgovWebUtil.filePathBlackList(drctry1));
/*      */ 
/* 1072 */     if ((file.exists()) && (file.isDirectory())) {
/* 1073 */       File[] fileArray = file.listFiles();
/* 1074 */       list = getSubFilesByUpdtPd(fileArray, updtFrom, updtTo);
/*      */     }
/*      */ 
/* 1077 */     return list;
/*      */   }
/*      */ 
/*      */   public static List<String> getSubFilesByDate(File[] fileArray, String updtDate)
/*      */     throws Exception
/*      */   {
/* 1090 */     List list = new ArrayList();
/*      */ 
/* 1092 */     for (int i = 0; i < fileArray.length; i++)
/*      */     {
/* 1094 */       if (fileArray[i].isDirectory()) {
/* 1095 */         File[] tmpArray = fileArray[i].listFiles();
/* 1096 */         list.addAll(getSubFilesByDate(tmpArray, updtDate));
/*      */       }
/*      */       else
/*      */       {
/* 1100 */         long date = fileArray[i].lastModified();
/* 1101 */         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
/* 1102 */         String lastUpdtDate = dateFormat.format(new Date(date));
/* 1103 */         if (Integer.parseInt(lastUpdtDate) == Integer.parseInt(updtDate)) {
/* 1104 */           list.add(fileArray[i].getAbsolutePath());
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1109 */     return list;
/*      */   }
/*      */ 
/*      */   public static Vector<List<String>> parsFileByChar(String parFile, String parChar, int parField)
/*      */     throws Exception
/*      */   {
/* 1124 */     Vector parResult = new Vector();
/*      */ 
/* 1127 */     String parFile1 = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1128 */     File file = new File(EgovWebUtil.filePathBlackList(parFile1));
/* 1129 */     BufferedReader br = null;
/*      */     try
/*      */     {
/* 1132 */       if ((file.exists()) && (file.isFile()))
/*      */       {
/* 1135 */         br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
/* 1136 */         StringBuffer strBuff = new StringBuffer();
/* 1137 */         String line = "";
/* 1138 */         while ((line = br.readLine()) != null) {
/* 1139 */           if (line.length() < 1024) {
/* 1140 */             strBuff.append(line);
/*      */           }
/*      */         }
/*      */ 
/* 1144 */         String[] strArr = EgovStringUtil.split(strBuff.toString(), parChar);
/*      */ 
/* 1147 */         int filedCnt = 1;
/* 1148 */         List arr = new ArrayList();
/* 1149 */         for (int i = 0; i < strArr.length; i++)
/*      */         {
/* 1151 */           if (parField != 1) {
/* 1152 */             if (filedCnt % parField == 1) {
/* 1153 */               if (strArr[i] != null) {
/* 1154 */                 arr.add(strArr[i]);
/*      */               }
/* 1156 */               if (i == strArr.length - 1)
/* 1157 */                 parResult.add(arr);
/*      */             }
/* 1159 */             else if (filedCnt % parField == 0) {
/* 1160 */               if (strArr[i] != null) {
/* 1161 */                 arr.add(strArr[i]);
/* 1162 */                 parResult.add(arr);
/*      */               }
/*      */             }
/* 1165 */             else if (strArr[i] != null) {
/* 1166 */               arr.add(strArr[i]);
/* 1167 */               if (i == strArr.length - 1)
/* 1168 */                 parResult.add(arr);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 1173 */             arr = new ArrayList();
/* 1174 */             if (strArr[i] != null) {
/* 1175 */               arr.add(strArr[i]);
/*      */             }
/* 1177 */             parResult.add(arr);
/*      */           }
/*      */ 
/* 1180 */           filedCnt++;
/*      */         }
/*      */       }
/*      */     } finally {
/* 1184 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 1184 */         br });
/*      */     }
/*      */ 
/* 1187 */     return parResult;
/*      */   }
/*      */ 
/*      */   public static Vector<List<String>> parsFileBySize(String parFile, int[] parLen, int parLine)
/*      */     throws Exception
/*      */   {
/* 1202 */     Vector parResult = new Vector();
/*      */ 
/* 1205 */     String parFile1 = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1206 */     File file = new File(EgovWebUtil.filePathBlackList(parFile1));
/* 1207 */     BufferedReader br = null;
/*      */     try
/*      */     {
/* 1210 */       if ((file.exists()) && (file.isFile()))
/*      */       {
/* 1213 */         br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
/* 1214 */         if (parLine < 0) {
/* 1215 */           parLine = 0;
/*      */         }
/* 1217 */         String[] strArr = new String[parLine];
/* 1218 */         String line = "";
/* 1219 */         int readCnt = 0;
/* 1220 */         while (((line = br.readLine()) != null) && (readCnt < parLine)) {
/* 1221 */           if (line.length() <= 1024) {
/* 1222 */             strArr[(readCnt++)] = line;
/*      */           }
/*      */         }
/*      */ 
/* 1226 */         for (int i = 0; i < strArr.length; i++) {
/* 1227 */           String text = strArr[i];
/* 1228 */           List arr = new ArrayList();
/* 1229 */           int idx = 0;
/* 1230 */           boolean result = false;
/* 1231 */           for (int j = 0; j < parLen.length; j++) {
/* 1232 */             if (!result) {
/* 1233 */               String split = "";
/* 1234 */               if (text.length() < idx + parLen[j]) {
/* 1235 */                 split = text.substring(idx, text.length());
/* 1236 */                 result = true;
/*      */               } else {
/* 1238 */                 split = text.substring(idx, idx + parLen[j]);
/*      */               }
/* 1240 */               arr.add(split);
/* 1241 */               idx += parLen[j];
/*      */             }
/*      */           }
/* 1244 */           parResult.add(arr);
/*      */         }
/*      */       }
/*      */     } finally {
/* 1248 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 1248 */         br });
/*      */     }
/*      */ 
/* 1251 */     return parResult;
/*      */   }
/*      */ 
/*      */   public static boolean cmprFilesBySize(String cmprFile1, String cmprFile2)
/*      */     throws Exception
/*      */   {
/* 1265 */     boolean result = false;
/*      */ 
/* 1268 */     String cmprFile11 = cmprFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1269 */     String cmprFile22 = cmprFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1270 */     File file1 = new File(EgovWebUtil.filePathBlackList(cmprFile11));
/* 1271 */     File file2 = new File(EgovWebUtil.filePathBlackList(cmprFile22));
/*      */ 
/* 1274 */     if ((file1.exists()) && (file2.exists()) && (file1.isFile()) && (file2.isFile()))
/*      */     {
/* 1277 */       long size1 = file1.length();
/*      */ 
/* 1280 */       long size2 = file2.length();
/*      */ 
/* 1283 */       if (size1 == size2) {
/* 1284 */         result = true;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1289 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean cmprFilesByUpdtPd(String cmprFile1, String cmprFile2)
/*      */     throws Exception
/*      */   {
/* 1303 */     boolean result = false;
/*      */ 
/* 1306 */     String cmprFile11 = cmprFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1307 */     String cmprFile22 = cmprFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1308 */     File file1 = new File(EgovWebUtil.filePathBlackList(cmprFile11));
/* 1309 */     File file2 = new File(EgovWebUtil.filePathBlackList(cmprFile22));
/*      */ 
/* 1312 */     if ((file1.exists()) && (file2.exists()) && (file1.isFile()) && (file2.isFile()))
/*      */     {
/* 1315 */       long date1 = file1.lastModified();
/* 1316 */       SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
/* 1317 */       String lastUpdtDate1 = dateFormat1.format(new Date(date1));
/*      */ 
/* 1320 */       long date2 = file2.lastModified();
/* 1321 */       SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
/* 1322 */       String lastUpdtDate2 = dateFormat2.format(new Date(date2));
/*      */ 
/* 1325 */       if (lastUpdtDate1.equals(lastUpdtDate2)) {
/* 1326 */         result = true;
/*      */       }
/*      */     }
/*      */ 
/* 1330 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean cmprFilesByContent(String cmprFile1, String cmprFile2)
/*      */     throws Exception
/*      */   {
/* 1344 */     boolean result = false;
/*      */ 
/* 1347 */     String cmprFile11 = cmprFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1348 */     String cmprFile22 = cmprFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1349 */     File file1 = new File(EgovWebUtil.filePathBlackList(cmprFile11));
/* 1350 */     File file2 = new File(EgovWebUtil.filePathBlackList(cmprFile22));
/*      */ 
/* 1352 */     BufferedReader br1 = null;
/* 1353 */     BufferedReader br2 = null;
/*      */     try
/*      */     {
/* 1357 */       if ((file1.exists()) && (file2.exists()) && (file1.isFile()) && (file2.isFile()))
/*      */       {
/* 1359 */         List cmprText1 = new ArrayList();
/* 1360 */         List cmprText2 = new ArrayList();
/*      */ 
/* 1363 */         br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
/* 1364 */         String line1 = "";
/* 1365 */         while ((line1 = br1.readLine()) != null) {
/* 1366 */           if (line1.length() < 1024) {
/* 1367 */             cmprText1.add(line1);
/*      */           }
/*      */         }
/*      */ 
/* 1371 */         br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
/* 1372 */         String line2 = "";
/* 1373 */         while ((line2 = br2.readLine()) != null) {
/* 1374 */           if (line2.length() <= 1024) {
/* 1375 */             cmprText2.add(line2);
/*      */           }
/*      */         }
/*      */ 
/* 1379 */         boolean isWrong = false;
/* 1380 */         for (int i = 0; i < cmprText1.size(); i++) {
/* 1381 */           if (!isWrong) {
/* 1382 */             String text1 = (String)cmprText1.get(i);
/* 1383 */             String text2 = (String)cmprText2.get(i);
/*      */ 
/* 1385 */             if (!text1.equals(text2)) {
/* 1386 */               isWrong = true;
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/* 1391 */         if (!isWrong)
/* 1392 */           result = true;
/*      */       }
/*      */     }
/*      */     finally {
/* 1396 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 1396 */         br1, br2 });
/*      */     }
/*      */ 
/* 1399 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean cmprFilesByOwner(String cmprFile1, String cmprFile2)
/*      */     throws Exception
/*      */   {
/* 1413 */     boolean result = false;
/*      */ 
/* 1416 */     String owner1 = getOwner(cmprFile1);
/*      */ 
/* 1419 */     String owner2 = getOwner(cmprFile2);
/*      */ 
/* 1421 */     if ((owner1 != null) && (owner2 != null) && (!"".equals(owner1)) && (!"".equals(owner2)) && (owner1.equals(owner2))) {
/* 1422 */       result = true;
/*      */     }
/*      */ 
/* 1425 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyFile(String source, String target)
/*      */     throws Exception
/*      */   {
/* 1439 */     boolean result = false;
/*      */ 
/* 1442 */     String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1443 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/*      */ 
/* 1446 */     String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1449 */     if (srcFile.exists())
/*      */     {
/* 1452 */       tar = createNewFile(tar);
/*      */ 
/* 1454 */       File tarFile = new File(EgovWebUtil.filePathBlackList(tar));
/*      */ 
/* 1457 */       result = execCopyFile(srcFile, tarFile);
/*      */     }
/*      */ 
/* 1460 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyFiles(String[] source, String target)
/*      */     throws Exception
/*      */   {
/* 1474 */     boolean result = true;
/*      */ 
/* 1477 */     for (int i = 0; i < source.length; i++) {
/* 1478 */       String src = source[i].replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1479 */       File chkFile = new File(EgovWebUtil.filePathBlackList(src));
/* 1480 */       if (!chkFile.exists())
/*      */       {
/* 1482 */         return result;
/*      */       }
/*      */     }
/*      */ 
/* 1486 */     String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1489 */     for (int j = 0; j < source.length; j++)
/*      */     {
/* 1491 */       if (result)
/*      */       {
/* 1494 */         File chkFile = new File(EgovWebUtil.filePathBlackList(source[j]));
/* 1495 */         String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
/*      */ 
/* 1498 */         tarTemp = createNewFile(tarTemp);
/* 1499 */         File tarFile = new File(EgovWebUtil.filePathBlackList(tarTemp));
/*      */ 
/* 1502 */         result = execCopyFile(chkFile, tarFile);
/*      */       }
/*      */     }
/*      */ 
/* 1506 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyFilesByExtnt(String source, String extnt, String target)
/*      */     throws Exception
/*      */   {
/* 1521 */     boolean result = true;
/*      */ 
/* 1524 */     String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1525 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/*      */ 
/* 1528 */     if ((srcFile.exists()) && (srcFile.isDirectory()))
/*      */     {
/* 1530 */       String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1533 */       File[] fileArray = srcFile.listFiles();
/* 1534 */       List list = getSubFilesByExtnt(fileArray, extnt);
/*      */ 
/* 1537 */       for (int i = 0; i < list.size(); i++) {
/* 1538 */         if (result)
/*      */         {
/* 1540 */           String abspath = (String)list.get(i);
/*      */ 
/* 1543 */           File chkFile = new File(EgovWebUtil.filePathBlackList(abspath));
/* 1544 */           String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
/*      */ 
/* 1547 */           tarTemp = createNewFile(tarTemp);
/* 1548 */           File tarFile = new File(EgovWebUtil.filePathBlackList(tarTemp));
/*      */ 
/* 1551 */           result = execCopyFile(chkFile, tarFile);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1556 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyFilesByUpdtPd(String source, String updtFrom, String updtTo, String target)
/*      */     throws Exception
/*      */   {
/* 1572 */     boolean result = true;
/*      */ 
/* 1575 */     String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1576 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/*      */ 
/* 1579 */     if ((srcFile.exists()) && (srcFile.isDirectory()))
/*      */     {
/* 1581 */       String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1584 */       File[] fileArray = srcFile.listFiles();
/* 1585 */       List list = getSubFilesByUpdtPd(fileArray, updtFrom, updtTo);
/*      */ 
/* 1588 */       for (int i = 0; i < list.size(); i++)
/*      */       {
/* 1590 */         if (result)
/*      */         {
/* 1593 */           String abspath = (String)list.get(i);
/*      */ 
/* 1596 */           File chkFile = new File(EgovWebUtil.filePathBlackList(abspath));
/* 1597 */           String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
/*      */ 
/* 1600 */           tarTemp = createNewFile(tarTemp);
/* 1601 */           File tarFile = new File(tarTemp);
/*      */ 
/* 1604 */           result = execCopyFile(chkFile, tarFile);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1609 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyFilesBySize(String source, long sizeFrom, long sizeTo, String target)
/*      */     throws Exception
/*      */   {
/* 1625 */     boolean result = true;
/*      */ 
/* 1628 */     String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1629 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/*      */ 
/* 1632 */     if ((srcFile.exists()) && (srcFile.isDirectory()))
/*      */     {
/* 1634 */       String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1637 */       File[] fileArray = srcFile.listFiles();
/* 1638 */       List list = getSubFilesBySize(fileArray, sizeFrom, sizeTo);
/*      */ 
/* 1641 */       for (int i = 0; i < list.size(); i++)
/*      */       {
/* 1643 */         if (result)
/*      */         {
/* 1645 */           String abspath = (String)list.get(i);
/*      */ 
/* 1648 */           File chkFile = new File(EgovWebUtil.filePathBlackList(abspath));
/* 1649 */           String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
/*      */ 
/* 1652 */           tarTemp = createNewFile(tarTemp);
/* 1653 */           File tarFile = new File(EgovWebUtil.filePathBlackList(tarTemp));
/*      */ 
/* 1656 */           result = execCopyFile(chkFile, tarFile);
/* 1657 */           if (result)
/*      */           {
/*      */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1665 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyFilesByOwner(String source, String owner, String target)
/*      */     throws Exception
/*      */   {
/* 1680 */     boolean result = true;
/*      */ 
/* 1683 */     String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1684 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/*      */ 
/* 1687 */     if ((srcFile.exists()) && (srcFile.isDirectory()))
/*      */     {
/* 1689 */       String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1692 */       File[] fileArray = srcFile.listFiles();
/* 1693 */       List list = getSubFilesByOwner(fileArray, owner);
/*      */ 
/* 1696 */       for (int i = 0; i < list.size(); i++)
/*      */       {
/* 1698 */         if (result)
/*      */         {
/* 1701 */           String abspath = (String)list.get(i);
/*      */ 
/* 1704 */           File chkFile = new File(EgovWebUtil.filePathBlackList(abspath));
/* 1705 */           String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
/*      */ 
/* 1708 */           tarTemp = createNewFile(tarTemp);
/* 1709 */           File tarFile = new File(EgovWebUtil.filePathBlackList(tarTemp));
/*      */ 
/* 1712 */           result = execCopyFile(chkFile, tarFile);
/*      */ 
/* 1714 */           if (!result)
/*      */           {
/*      */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1722 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean execCopyFile(File srcFile, File tarFile)
/*      */     throws Exception
/*      */   {
/* 1736 */     boolean result = false;
/* 1737 */     FileInputStream fis = null;
/* 1738 */     FileOutputStream fos = null;
/*      */     try
/*      */     {
/* 1741 */       fis = new FileInputStream(srcFile);
/*      */ 
/* 1744 */       File tarFile1 = tarFile;
/* 1745 */       if (tarFile1.isDirectory()) {
/* 1746 */         tarFile1 = new File(EgovWebUtil.filePathBlackList(tarFile1.getAbsolutePath()) + "/" + srcFile.getName());
/*      */       }
/* 1748 */       fos = new FileOutputStream(tarFile1);
/* 1749 */       byte[] buffer = new byte[1024];
/* 1750 */       int i = 0;
/* 1751 */       if ((fis != null) && (fos != null)) {
/* 1752 */         while ((i = fis.read(buffer)) != -1) {
/* 1753 */           fos.write(buffer, 0, i);
/*      */         }
/*      */       }
/*      */ 
/* 1757 */       result = true;
/*      */     } finally {
/* 1759 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 1759 */         fis, fos });
/*      */     }
/*      */ 
/* 1762 */     return result;
/*      */   }
/*      */ 
/*      */   public static String deleteDirectory(String dirDeletePath, String dirOwner)
/*      */   {
/* 1778 */     if ((dirDeletePath == null) || (dirDeletePath.equals("")) || (dirOwner == null) || (dirOwner.equals(""))) {
/* 1779 */       return "";
/*      */     }
/*      */ 
/* 1783 */     String result = "";
/* 1784 */     File file = new File(EgovWebUtil.filePathBlackList(dirDeletePath));
/*      */     try
/*      */     {
/* 1788 */       boolean isInCondition = false;
/* 1789 */       String realOwner = getOwner(dirDeletePath);
/*      */ 
/* 1791 */       if (dirOwner.equals(realOwner)) {
/* 1792 */         isInCondition = true;
/*      */       }
/*      */ 
/* 1795 */       if ((file.isDirectory()) && (isInCondition))
/* 1796 */         result = deleteDirectory(dirDeletePath);
/*      */       else
/* 1798 */         result = realOwner;
/*      */     }
/*      */     catch (Exception e) {
/* 1801 */       throw new RuntimeException(e);
/*      */     }
/*      */ 
/* 1804 */     return result;
/*      */   }
/*      */ 
/*      */   public static String deleteDirectory(String dirDeletePath, String fromDate, String toDate)
/*      */   {
/* 1820 */     if ((dirDeletePath == null) || (dirDeletePath.equals("")) || (fromDate == null) || (fromDate.equals("")) || (toDate == null) || (toDate.equals(""))) {
/* 1821 */       return "";
/*      */     }
/*      */ 
/* 1825 */     String result = "";
/* 1826 */     File file = new File(EgovWebUtil.filePathBlackList(dirDeletePath));
/*      */ 
/* 1829 */     boolean isInCondition = false;
/* 1830 */     String lastModifyedDate = getLastModifiedDateFromFile(file);
/*      */ 
/* 1833 */     if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate)) && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
/* 1834 */       isInCondition = true;
/*      */     }
/*      */ 
/* 1838 */     if ((file.isDirectory()) && (isInCondition)) {
/* 1839 */       result = deleteDirectory(dirDeletePath);
/*      */     }
/*      */ 
/* 1842 */     return result;
/*      */   }
/*      */ 
/*      */   public static String getMountLc(String file)
/*      */     throws Exception
/*      */   {
/* 1855 */     String diskName = "";
/*      */ 
/* 1858 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1860 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 1861 */     if (srcFile.exists())
/*      */     {
/* 1864 */       if (Globals.OS_TYPE.equals("UNIX")) {
/* 1865 */         Process p = null;
/* 1866 */         String[] command = { EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".getMountLc"), src, "/" };
/* 1867 */         p = Runtime.getRuntime().exec(command);
/*      */ 
/* 1871 */         BufferedReader b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
/*      */ 
/* 1874 */         label190: label207: 
/*      */         try { String str;
/*      */           while (true) { str = b_out.readLine();
/* 1875 */             if (str == null) break label190; if ("".equals(str)) {
/*      */               break label207;
/*      */             }
/* 1878 */             if (str.length() > 1024) break;
/* 1879 */             diskName = str;
/*      */           }
/* 1881 */           diskName = str.substring(0, 1024);
/*      */         }
/*      */         finally
/*      */         {
/* 1885 */           EgovResourceCloseHelper.close(new Closeable[] { 
/* 1885 */             b_out });
/*      */         }
/*      */ 
/* 1888 */         if (p != null) {
/* 1889 */           p.destroy();
/*      */         }
/*      */ 
/*      */       }
/* 1893 */       else if (Globals.OS_TYPE.equals("WINDOWS"))
/*      */       {
/* 1895 */         diskName = src.substring(0, 1).toUpperCase();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1900 */     return diskName;
/*      */   }
/*      */ 
/*      */   public static String getDrctryName(String file)
/*      */     throws Exception
/*      */   {
/* 1912 */     String drctryName = "";
/* 1913 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1915 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 1916 */     if (srcFile.exists()) {
/* 1917 */       drctryName = srcFile.getParent();
/*      */     }
/*      */ 
/* 1920 */     return drctryName;
/*      */   }
/*      */ 
/*      */   public static String getFileName(String file)
/*      */     throws Exception
/*      */   {
/* 1932 */     String fileName = "";
/* 1933 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1935 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 1936 */     if (srcFile.exists()) {
/* 1937 */       fileName = srcFile.getName();
/*      */     }
/*      */ 
/* 1940 */     return fileName;
/*      */   }
/*      */ 
/*      */   public static String getUpdtDate(String file)
/*      */     throws Exception
/*      */   {
/* 1952 */     String updtDate = "";
/* 1953 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 1955 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 1956 */     if (srcFile.exists()) {
/* 1957 */       long date = srcFile.lastModified();
/* 1958 */       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
/* 1959 */       updtDate = dateFormat.format(new Date(date));
/*      */     }
/*      */ 
/* 1962 */     return updtDate;
/*      */   }
/*      */ 
/*      */   public static String getUpdtDate(String file, String format) throws Exception
/*      */   {
/* 1967 */     String updtDate = "";
/* 1968 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */     try
/*      */     {
/* 1971 */       File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 1972 */       if (srcFile.exists()) {
/* 1973 */         long date = srcFile.lastModified();
/* 1974 */         SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.KOREA);
/* 1975 */         updtDate = dateFormat.format(new Date(date));
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (Exception ex)
/*      */     {
/* 1982 */       throw new RuntimeException(ex);
/*      */     }
/*      */ 
/* 1985 */     return updtDate;
/*      */   }
/*      */ 
/*      */   public static String getOwner(String file)
/*      */     throws Exception
/*      */   {
/* 1997 */     String owner = "";
/* 1998 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 1999 */     BufferedReader b_err = null;
/* 2000 */     BufferedReader b_out = null;
/*      */     try {
/* 2002 */       File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 2003 */       if (srcFile.exists())
/*      */       {
/* 2006 */         String parentPath = srcFile.getParent();
/* 2007 */         String fname = srcFile.getName();
/*      */ 
/* 2009 */         Process p = null;
/* 2010 */         String cmdStr = EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".getDrctryOwner");
/* 2011 */         String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), parentPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), 
/* 2012 */           fname };
/* 2013 */         p = Runtime.getRuntime().exec(command);
/* 2014 */         p.waitFor();
/*      */ 
/* 2016 */         if ((p != null) && (p.exitValue() != 0)) {
/* 2017 */           b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
/* 2018 */           while (b_err.ready());
/*      */         }
/*      */         else
/*      */         {
/* 2023 */           boolean result = false;
/* 2024 */           b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 2025 */           while (b_out.ready()) {
/* 2026 */             if (!result)
/*      */             {
/* 2028 */               owner = b_out.readLine();
/* 2029 */               if ((owner != null) && (owner.length() <= 1024) && 
/* 2030 */                 (owner != null) && (!"".equals(owner))) {
/* 2031 */                 result = true;
/* 2032 */                 break;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/* 2041 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2041 */         b_err, b_out });
/*      */     }
/*      */ 
/* 2044 */     return owner;
/*      */   }
/*      */ 
/*      */   public static String getAccess(String file)
/*      */     throws Exception
/*      */   {
/* 2056 */     String access = "";
/* 2057 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/* 2058 */     BufferedReader b_err = null;
/* 2059 */     BufferedReader b_out = null;
/*      */     try {
/* 2061 */       File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 2062 */       if (srcFile.exists())
/*      */       {
/* 2065 */         String parentPath = EgovWebUtil.filePathBlackList(srcFile.getParent());
/* 2066 */         String fname = EgovWebUtil.filePathBlackList(srcFile.getName());
/*      */ 
/* 2068 */         Process p = null;
/* 2069 */         if (Globals.OS_TYPE.equals("UNIX")) {
/* 2070 */           String[] command = { EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".getDrctryAccess"), parentPath, fname };
/* 2071 */           p = Runtime.getRuntime().exec(command);
/* 2072 */           p.waitFor();
/*      */ 
/* 2074 */           if ((p != null) && (p.exitValue() != 0)) {
/* 2075 */             b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
/* 2076 */             while (b_err.ready());
/* 2080 */             b_err.close();
/*      */           }
/*      */           else
/*      */           {
/* 2084 */             boolean result = false;
/* 2085 */             b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 2086 */             while (b_out.ready()) {
/* 2087 */               if (!result) {
/* 2088 */                 access = b_out.readLine();
/* 2089 */                 if ((access != null) && (!"".equals(access)) && (access.length() <= 1024)) {
/* 2090 */                   result = true;
/* 2091 */                   break;
/*      */                 }
/*      */               }
/*      */             }
/* 2095 */             b_out.close();
/*      */           }
/* 2097 */         } else if (Globals.OS_TYPE.equals("WINDOWS")) {
/* 2098 */           String[] command = { "cmd", "/c", "attrib", src };
/* 2099 */           p = Runtime.getRuntime().exec(command);
/* 2100 */           p.waitFor();
/*      */ 
/* 2102 */           if ((p != null) && (p.exitValue() != 0))
/*      */           {
/* 2104 */             while (b_err.ready());
/*      */           }
/*      */           else
/*      */           {
/* 2109 */             boolean result = false;
/* 2110 */             b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 2111 */             while (b_out.ready()) {
/* 2112 */               if (!result) {
/* 2113 */                 access = b_out.readLine();
/* 2114 */                 if ((access != null) && (!"".equals(access)) && (access.length() <= 1024)) {
/* 2115 */                   access = access.toUpperCase().replace(src.toUpperCase(), "");
/* 2116 */                   access = access.replace(" ", "");
/* 2117 */                   result = true;
/* 2118 */                   if (result)
/*      */                   {
/*      */                     break;
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/* 2125 */             if (result) {
/* 2126 */               String acs = "";
/* 2127 */               boolean read = false;
/* 2128 */               boolean write = true;
/* 2129 */               boolean system = false;
/* 2130 */               boolean hidden = false;
/*      */ 
/* 2132 */               for (int i = 0; i < access.length(); i++) {
/* 2133 */                 char chr = access.charAt(i);
/* 2134 */                 switch (chr) {
/*      */                 case 'R':
/* 2136 */                   read = true;
/* 2137 */                   write = false;
/* 2138 */                   break;
/*      */                 case 'S':
/* 2140 */                   system = true;
/* 2141 */                   break;
/*      */                 case 'H':
/* 2143 */                   hidden = true;
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/* 2150 */               if (read)
/* 2151 */                 acs = acs + "READ-ONLY|";
/*      */               else {
/* 2153 */                 acs = acs + "READ|";
/*      */               }
/* 2155 */               if (write) {
/* 2156 */                 acs = acs + "WRITE|";
/*      */               }
/* 2158 */               if (system) {
/* 2159 */                 acs = acs + "SYSTEM|";
/*      */               }
/* 2161 */               if (hidden) {
/* 2162 */                 acs = acs + "HIDDEN|";
/*      */               }
/* 2164 */               access = acs;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     finally {
/* 2171 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2171 */         b_err, b_out });
/*      */     }
/*      */ 
/* 2174 */     return access;
/*      */   }
/*      */ 
/*      */   public static long getSize(String file)
/*      */     throws Exception
/*      */   {
/* 2186 */     long size = 0L;
/* 2187 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 2189 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 2190 */     if (srcFile.exists()) {
/* 2191 */       size = srcFile.length();
/*      */     }
/*      */ 
/* 2194 */     return size;
/*      */   }
/*      */ 
/*      */   public static String getFormat(String file)
/*      */     throws Exception
/*      */   {
/* 2207 */     String format = "";
/* 2208 */     String type = "";
/*      */ 
/* 2210 */     String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
/*      */ 
/* 2212 */     File srcFile = new File(EgovWebUtil.filePathBlackList(src));
/* 2213 */     if (srcFile.exists())
/*      */     {
/* 2215 */       String[] strArr = src.split("\\.");
/* 2216 */       if (strArr.length >= 2) {
/* 2217 */         format = strArr[(strArr.length - 1)].toLowerCase();
/* 2218 */         type = EgovProperties.getProperty(Globals.FILE_FORMAT_PATH, format);
/*      */       }
/*      */     }
/*      */ 
/* 2222 */     return type;
/*      */   }
/*      */ 
/*      */   public static boolean copyDirectory(String originalDirPath, String targetDirPath)
/*      */     throws Exception
/*      */   {
/* 2237 */     if ((originalDirPath == null) || (originalDirPath.equals("")) || (targetDirPath == null) || (targetDirPath.equals(""))) {
/* 2238 */       return false;
/*      */     }
/* 2240 */     boolean result = false;
/* 2241 */     File f = null;
/*      */ 
/* 2243 */     f = new File(EgovWebUtil.filePathBlackList(originalDirPath));
/*      */ 
/* 2245 */     if ((f.exists()) && (f.isDirectory()))
/*      */     {
/* 2248 */       String targetDirPath1 = createNewDirectory(targetDirPath);
/* 2249 */       if (targetDirPath1.equals("")) {
/* 2250 */         result = false;
/*      */       } else {
/* 2252 */         File targetDir = new File(EgovWebUtil.filePathBlackList(targetDirPath1));
/* 2253 */         targetDir.mkdirs();
/*      */ 
/* 2255 */         String[] originalFileList = f.list();
/* 2256 */         if (originalFileList.length > 0) {
/* 2257 */           for (int i = 0; i < originalFileList.length; i++) {
/* 2258 */             File subF = new File(EgovWebUtil.filePathBlackList(originalDirPath) + FILE_SEPARATOR + originalFileList[i]);
/* 2259 */             if (subF.isFile())
/*      */             {
/* 2261 */               result = copyFile(originalDirPath + FILE_SEPARATOR + originalFileList[i], targetDir.getAbsolutePath() + FILE_SEPARATOR + originalFileList[i]);
/*      */             }
/*      */             else
/* 2264 */               result = copyDirectory(originalDirPath + "/" + originalFileList[i], targetDirPath1 + "/" + originalFileList[i]);
/*      */           }
/*      */         }
/*      */         else
/* 2268 */           result = true;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2273 */       result = false;
/*      */     }
/*      */ 
/* 2276 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyDirectory(String originalDirPath, String targetDirPath, String fromDate, String toDate)
/*      */     throws Exception
/*      */   {
/* 2293 */     if ((originalDirPath == null) || (originalDirPath.equals("")) || (targetDirPath == null) || (targetDirPath.equals("")) || (fromDate == null) || (fromDate.equals("")) || (toDate == null) || 
/* 2294 */       (toDate.equals(""))) {
/* 2295 */       return false;
/*      */     }
/* 2297 */     boolean result = false;
/* 2298 */     File f = null;
/*      */ 
/* 2300 */     f = new File(EgovWebUtil.filePathBlackList(originalDirPath));
/* 2301 */     boolean isInCondition = false;
/* 2302 */     String lastModifyedDate = getLastModifiedDateFromFile(f);
/* 2303 */     if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate)) && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
/* 2304 */       isInCondition = true;
/*      */     }
/*      */ 
/* 2308 */     if ((f.exists()) && (f.isDirectory()) && (isInCondition))
/*      */     {
/* 2311 */       String targetDirPath1 = createNewDirectory(targetDirPath);
/* 2312 */       if (targetDirPath1.equals("")) {
/* 2313 */         result = false;
/*      */       } else {
/* 2315 */         File targetDir = new File(EgovWebUtil.filePathBlackList(targetDirPath1));
/* 2316 */         targetDir.mkdirs();
/*      */ 
/* 2318 */         String[] originalFileList = f.list();
/* 2319 */         if (originalFileList.length > 0)
/* 2320 */           for (int i = 0; i < originalFileList.length; i++) {
/* 2321 */             File subF = new File(EgovWebUtil.filePathBlackList(originalDirPath) + FILE_SEPARATOR + originalFileList[i]);
/* 2322 */             if (subF.isFile())
/*      */             {
/* 2324 */               result = copyFile(originalDirPath + FILE_SEPARATOR + originalFileList[i], targetDir.getAbsolutePath() + FILE_SEPARATOR + originalFileList[i]);
/*      */             }
/*      */             else
/*      */             {
/* 2328 */               result = copyDirectory(originalDirPath + "/" + originalFileList[i], targetDirPath1 + "/" + originalFileList[i]);
/*      */             }
/*      */           }
/*      */         else {
/* 2332 */           result = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2338 */       result = false;
/*      */     }
/*      */ 
/* 2341 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean copyDirectory(String originalDirPath, String targetDirPath, String owner)
/*      */     throws Exception
/*      */   {
/* 2357 */     if ((originalDirPath == null) || (originalDirPath.equals("")) || (targetDirPath == null) || (targetDirPath.equals("")) || (owner == null) || (owner.equals(""))) {
/* 2358 */       return false;
/*      */     }
/* 2360 */     boolean result = false;
/* 2361 */     File f = null;
/*      */ 
/* 2363 */     f = new File(EgovWebUtil.filePathBlackList(originalDirPath));
/* 2364 */     boolean isInCondition = false;
/* 2365 */     String realOwner = getOwner(originalDirPath);
/* 2366 */     if (realOwner.equals(owner)) {
/* 2367 */       isInCondition = true;
/*      */     }
/*      */ 
/* 2371 */     if ((f.exists()) && (f.isDirectory()) && (isInCondition))
/*      */     {
/* 2373 */       String targetDirPath1 = createNewDirectory(targetDirPath);
/* 2374 */       if (targetDirPath1.equals(""))
/*      */       {
/* 2376 */         result = false;
/*      */       } else {
/* 2378 */         File targetDir = new File(EgovWebUtil.filePathBlackList(targetDirPath1));
/* 2379 */         targetDir.mkdirs();
/*      */ 
/* 2381 */         String[] originalFileList = f.list();
/* 2382 */         if (originalFileList.length > 0)
/* 2383 */           for (int i = 0; i < originalFileList.length; i++) {
/* 2384 */             File subF = new File(EgovWebUtil.filePathBlackList(originalDirPath) + FILE_SEPARATOR + originalFileList[i]);
/* 2385 */             if (subF.isFile())
/*      */             {
/* 2387 */               result = copyFile(originalDirPath + FILE_SEPARATOR + originalFileList[i], targetDir.getAbsolutePath() + FILE_SEPARATOR + originalFileList[i]);
/*      */             }
/*      */             else
/*      */             {
/* 2391 */               result = copyDirectory(originalDirPath + "/" + originalFileList[i], targetDirPath1 + "/" + originalFileList[i]);
/*      */             }
/*      */           }
/*      */         else {
/* 2395 */           result = false;
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2401 */       result = false;
/*      */     }
/*      */ 
/* 2404 */     return result;
/*      */   }
/*      */ 
/*      */   public static long getDirectorySize(String targetDirPath)
/*      */     throws Exception
/*      */   {
/* 2416 */     File f = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/* 2417 */     if (!f.exists()) {
/* 2418 */       return 0L;
/*      */     }
/* 2420 */     if (f.isFile()) {
/* 2421 */       return f.length();
/*      */     }
/*      */ 
/* 2424 */     File[] list = f.listFiles();
/* 2425 */     long size = 0L;
/* 2426 */     long fileSize = 0L;
/*      */ 
/* 2428 */     for (int i = 0; i < list.length; i++)
/*      */     {
/* 2430 */       if (list[i].isDirectory())
/*      */       {
/* 2432 */         fileSize = getDirectorySize(list[i].getAbsolutePath());
/*      */       }
/*      */       else {
/* 2435 */         fileSize = list[i].length();
/*      */       }
/* 2437 */       size += fileSize;
/*      */     }
/* 2439 */     return size;
/*      */   }
/*      */ 
/*      */   public static boolean moveFile(String originalDirPath, String targetDirPath)
/*      */     throws Exception
/*      */   {
/* 2454 */     if ((originalDirPath == null) || (originalDirPath.equals("")) || (targetDirPath == null) || (targetDirPath.equals(""))) {
/* 2455 */       return false;
/*      */     }
/* 2457 */     boolean result = false;
/* 2458 */     File f = null;
/* 2459 */     BufferedReader b_err = null;
/* 2460 */     BufferedReader b_out = null;
/*      */     try {
/* 2462 */       f = new File(EgovWebUtil.filePathBlackList(originalDirPath));
/*      */ 
/* 2465 */       if (f.exists())
/*      */       {
/* 2467 */         File targetDir = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/* 2468 */         if (targetDir.exists())
/*      */         {
/* 2470 */           result = false;
/*      */         }
/*      */         else {
/* 2473 */           String cmdStr = EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".moveDrctry");
/* 2474 */           String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), 
/* 2475 */             EgovWebUtil.filePathBlackList(originalDirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)), 
/* 2476 */             EgovWebUtil.filePathBlackList(targetDirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)) };
/* 2477 */           Process p = Runtime.getRuntime().exec(command);
/*      */ 
/* 2479 */           p.waitFor();
/*      */ 
/* 2481 */           if ((p != null) && (p.exitValue() != 0)) {
/* 2482 */             b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
/* 2483 */             while (b_err.ready());
/* 2487 */             b_err.close();
/*      */           }
/*      */           else
/*      */           {
/* 2491 */             result = true;
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 2497 */         result = false;
/*      */       }
/*      */     } finally {
/* 2500 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2500 */         b_err, b_out });
/*      */     }
/*      */ 
/* 2503 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean moveFile(String originalDirPath, String targetDirPath, String fromDate, String toDate)
/*      */     throws Exception
/*      */   {
/* 2520 */     if ((originalDirPath == null) || (originalDirPath.equals("")) || (targetDirPath == null) || (targetDirPath.equals("")) || (fromDate == null) || (fromDate.equals("")) || (toDate == null) || 
/* 2521 */       (toDate.equals(""))) {
/* 2522 */       return false;
/*      */     }
/* 2524 */     boolean result = false;
/* 2525 */     File f = null;
/* 2526 */     BufferedReader b_err = null;
/* 2527 */     BufferedReader b_out = null;
/*      */ 
/* 2529 */     label434: 
/*      */     try { f = new File(originalDirPath);
/*      */ 
/* 2532 */       if (f.exists())
/*      */       {
/* 2534 */         File targetDir = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/* 2535 */         if (targetDir.exists())
/*      */         {
/* 2537 */           result = false;
/* 2538 */           break label434;
/*      */         }
/* 2540 */         boolean isInCondition = false;
/* 2541 */         String lastModifyedDate = getLastModifiedDateFromFile(f);
/* 2542 */         if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate)) && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
/* 2543 */           isInCondition = true;
/*      */         }
/*      */ 
/* 2546 */         if (isInCondition) {
/* 2547 */           String cmdStr = EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".moveDrctry");
/* 2548 */           String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), 
/* 2549 */             EgovWebUtil.filePathBlackList(originalDirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)), 
/* 2550 */             EgovWebUtil.filePathBlackList(targetDirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)) };
/* 2551 */           Process p = Runtime.getRuntime().exec(command);
/* 2552 */           String access = "";
/* 2553 */           p.waitFor();
/*      */ 
/* 2555 */           if ((p != null) && (p.exitValue() != 0)) {
/* 2556 */             b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
/* 2557 */             while (b_err.ready());
/* 2561 */             break label434;
/*      */           }
/*      */ 
/* 2564 */           result = false;
/* 2565 */           b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 2566 */           while (b_out.ready()) {
/* 2567 */             if (!result) {
/* 2568 */               access = b_out.readLine();
/* 2569 */               if ((access != null) && (!"".equals(access)) && (access.length() <= 1024)) {
/* 2570 */                 result = true;
/* 2571 */                 break;
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/* 2579 */           break label434;
/*      */         }
/*      */       } else { result = false; }
/*      */     } finally
/*      */     {
/* 2584 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2584 */         b_err, b_out });
/*      */     }
/*      */ 
/* 2587 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean moveFile(String originalDirPath, String targetDirPath, String owner)
/*      */     throws Exception
/*      */   {
/* 2603 */     if ((originalDirPath == null) || (originalDirPath.equals("")) || (targetDirPath == null) || (targetDirPath.equals("")) || (owner == null) || (owner.equals(""))) {
/* 2604 */       return false;
/*      */     }
/*      */ 
/* 2608 */     boolean result = false;
/* 2609 */     File f = null;
/* 2610 */     BufferedReader b_err = null;
/* 2611 */     BufferedReader b_out = null;
/*      */ 
/* 2613 */     label403: 
/*      */     try { f = new File(originalDirPath);
/*      */ 
/* 2616 */       if (f.exists())
/*      */       {
/* 2618 */         File targetDir = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/* 2619 */         if (targetDir.exists())
/*      */         {
/* 2621 */           result = false;
/* 2622 */           break label403;
/*      */         }
/* 2624 */         boolean isInCondition = false;
/* 2625 */         String realOwner = getOwner(originalDirPath);
/* 2626 */         if (realOwner.equals(owner)) {
/* 2627 */           isInCondition = true;
/*      */         }
/*      */ 
/* 2630 */         if (isInCondition) {
/* 2631 */           String cmdStr = EgovProperties.getProperty(Globals.SHELL_FILE_PATH, "SHELL." + Globals.OS_TYPE + ".moveDrctry");
/* 2632 */           String[] command = { cmdStr.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR), 
/* 2633 */             EgovWebUtil.filePathBlackList(originalDirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)), 
/* 2634 */             EgovWebUtil.filePathBlackList(targetDirPath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR)) };
/* 2635 */           Process p = Runtime.getRuntime().exec(command);
/* 2636 */           String access = "";
/* 2637 */           p.waitFor();
/*      */ 
/* 2639 */           if ((p != null) && (p.exitValue() != 0)) {
/* 2640 */             b_err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
/* 2641 */             while (b_err.ready());
/* 2645 */             break label403;
/*      */           }
/*      */ 
/* 2648 */           result = false;
/* 2649 */           b_out = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 2650 */           while (b_out.ready())
/*      */           {
/* 2652 */             if (!result) {
/* 2653 */               access = b_out.readLine();
/* 2654 */               if ((access != null) && (!"".equals(access)) && (access.length() <= 1024)) {
/* 2655 */                 result = true;
/* 2656 */                 if (result)
/*      */                 {
/*      */                   break;
/*      */                 }
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/* 2665 */           break label403;
/*      */         }
/*      */       } else { result = false; }
/*      */     } finally
/*      */     {
/* 2670 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2670 */         b_err, b_out });
/*      */     }
/*      */ 
/* 2673 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean startDirectoryMonitering(String targetDirPath)
/*      */     throws Exception
/*      */   {
/* 2690 */     if ((targetDirPath == null) || (targetDirPath.equals(""))) {
/* 2691 */       return false;
/*      */     }
/*      */ 
/* 2694 */     boolean result = false;
/* 2695 */     FileReader fr = null;
/* 2696 */     BufferedReader br = null;
/*      */ 
/* 2700 */     label242: 
/*      */     try { File targetF = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/* 2701 */       File logF = new File(EgovWebUtil.filePathBlackList(Globals.CONF_PATH) + "/" + targetF.getName() + ".log");
/*      */ 
/* 2703 */       if ((targetF.exists()) && (targetF.isDirectory()))
/*      */       {
/* 2705 */         if (logF.exists())
/*      */         {
/* 2707 */           result = true;
/*      */ 
/* 2709 */           String lastStr = "";
/* 2710 */           fr = new FileReader(logF);
/* 2711 */           br = new BufferedReader(fr);
/*      */ 
/* 2713 */           String line = "";
/* 2714 */           while ((line = br.readLine()) != null) {
/* 2715 */             if (line.length() < 1024) {
/* 2716 */               lastStr = line;
/*      */             }
/*      */           }
/* 2719 */           if (lastStr.equals("END")) {
/* 2720 */             EgovFileMntrg t = new EgovFileMntrg(EgovWebUtil.filePathBlackList(targetDirPath), logF);
/* 2721 */             t.start();
/*      */ 
/* 2723 */             break label242;
/*      */           } } else { result = logF.createNewFile();
/* 2725 */           EgovFileMntrg t = new EgovFileMntrg(targetDirPath, logF);
/* 2726 */           t.start(); }
/*      */       }
/*      */     } finally
/*      */     {
/* 2730 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2730 */         fr, br });
/*      */     }
/*      */ 
/* 2733 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean stopDirectoryMonitering(String targetDirPath)
/*      */     throws Exception
/*      */   {
/* 2749 */     if ((targetDirPath == null) || (targetDirPath.equals(""))) {
/* 2750 */       return false;
/*      */     }
/*      */ 
/* 2753 */     boolean result = false;
/* 2754 */     FileReader fr = null;
/* 2755 */     BufferedReader br = null;
/* 2756 */     FileWriter fWriter = null;
/* 2757 */     BufferedWriter bWriter = null;
/*      */     try {
/* 2759 */       File targetF = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/* 2760 */       File logF = new File(EgovWebUtil.filePathBlackList(Globals.CONF_PATH) + "/" + targetF.getName() + ".log");
/* 2761 */       if (logF.exists())
/*      */       {
/* 2764 */         String lastStr = "";
/* 2765 */         fr = new FileReader(logF);
/* 2766 */         br = new BufferedReader(fr);
/*      */ 
/* 2768 */         String line = "";
/* 2769 */         while ((line = br.readLine()) != null) {
/* 2770 */           if (line.length() < 1024)
/* 2771 */             lastStr = line;
/*      */         }
/* 2773 */         br.close();
/*      */ 
/* 2779 */         if (!lastStr.equals("END")) {
/* 2780 */           fWriter = new FileWriter(logF, true);
/* 2781 */           bWriter = new BufferedWriter(fWriter);
/* 2782 */           br = new BufferedReader(new StringReader("END"));
/* 2783 */           while (((line = br.readLine()) != null) && (!lastStr.equals("END"))) {
/* 2784 */             if (line.length() < 1024) {
/* 2785 */               bWriter.write(line + "\n", 0, line.length() + 1);
/*      */             }
/*      */           }
/*      */         }
/* 2789 */         result = true;
/*      */       } else {
/* 2791 */         result = false;
/*      */       }
/*      */     }
/*      */     finally {
/* 2795 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2795 */         fr, br, fWriter, bWriter });
/*      */     }
/*      */ 
/* 2798 */     return result;
/*      */   }
/*      */ 
/*      */   public static StringBuffer getDirectoryMoniteringInfo(String targetDirPath)
/*      */     throws Exception
/*      */   {
/* 2813 */     if ((targetDirPath == null) || (targetDirPath.equals(""))) {
/* 2814 */       return new StringBuffer();
/*      */     }
/*      */ 
/* 2817 */     StringBuffer result = new StringBuffer();
/* 2818 */     FileReader fr = null;
/*      */     try {
/* 2820 */       File targetF = new File(EgovWebUtil.filePathBlackList(targetDirPath));
/* 2821 */       File logF = new File(EgovWebUtil.filePathBlackList(Globals.CONF_PATH) + "/" + targetF.getName() + ".log");
/* 2822 */       if (!logF.exists()) {
/* 2823 */         result = new StringBuffer();
/*      */       }
/* 2825 */       fr = new FileReader(logF);
/* 2826 */       int ch = 0;
/* 2827 */       while ((ch = fr.read()) != -1)
/* 2828 */         result.append((char)ch);
/*      */     }
/*      */     finally {
/* 2831 */       EgovResourceCloseHelper.close(new Closeable[] { 
/* 2831 */         fr });
/*      */     }
/*      */ 
/* 2834 */     return result;
/*      */   }
/*      */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovFileTool
 * JD-Core Version:    0.6.2
 */