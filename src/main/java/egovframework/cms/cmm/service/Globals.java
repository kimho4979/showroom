/*    */ package egovframework.cms.cmm.service;
/*    */ 
/*    */ public class Globals
/*    */ {
/* 23 */   public static final String OS_TYPE = EgovProperties.getProperty("Globals.OsType");
/*    */ 
/* 25 */   public static final String DB_TYPE = EgovProperties.getProperty("Globals.DbType");
/*    */ 
/* 27 */   public static final String MAIN_PAGE = EgovProperties.getProperty("Globals.MainPage");
/*    */ 
/* 29 */   public static final String SHELL_FILE_PATH = EgovProperties.getPathProperty("Globals.ShellFilePath");
/*    */ 
/* 31 */   public static final String CONF_PATH = EgovProperties.getPathProperty("Globals.ConfPath");
/*    */ 
/* 33 */   public static final String SERVER_CONF_PATH = EgovProperties.getPathProperty("Globals.ServerConfPath");
/*    */ 
/* 35 */   public static final String CLIENT_CONF_PATH = EgovProperties.getPathProperty("Globals.ClientConfPath");
/*    */ 
/* 37 */   public static final String FILE_FORMAT_PATH = EgovProperties.getPathProperty("Globals.FileFormatPath");
/*    */   public static final String ORIGIN_FILE_NM = "originalFileName";
/*    */   public static final String FILE_EXT = "fileExtension";
/*    */   public static final String FILE_SIZE = "fileSize";
/*    */   public static final String UPLOAD_FILE_NM = "uploadFileName";
/*    */   public static final String FILE_PATH = "filePath";
/* 51 */   public static final String MAIL_REQUEST_PATH = EgovProperties.getPathProperty("Globals.MailRequestPath");
/*    */ 
/* 53 */   public static final String MAIL_RESPONSE_PATH = EgovProperties.getPathProperty("Globals.MailRResponsePath");
/*    */ 
/* 56 */   public static final String LOCAL_IP = EgovProperties.getProperty("Globals.LocalIp");
/*    */ 
/* 59 */   public static final String ROOT_PATH = "DEV".equals(EgovProperties.getProperty("Globals.Mode")) ? EgovProperties.getProperty("Globals.RootPathDev") : "LOC".equals(EgovProperties.getProperty("Globals.Mode")) ? EgovProperties.getProperty("Globals.RootPathLoc") : EgovProperties.getProperty("Globals.RootPathRel");
/*    */ 
/* 62 */   public static final String DISTRIBUTE_PATH = "DEV".equals(EgovProperties.getProperty("Globals.Mode")) ? EgovProperties.getProperty("Globals.DstbtPathDev") : "LOC".equals(EgovProperties.getProperty("Globals.Mode")) ? EgovProperties.getProperty("Globals.DstbtPathLoc") : EgovProperties.getProperty("Globals.DstbtPathRel");
/* 63 */   public static final String DISTRIBUTE_TEMPLATE_PATH = DISTRIBUTE_PATH + EgovProperties.getProperty("Globals.DstbtTemplateDir");
/* 64 */   public static final String DISTRIBUTE_TEMP_PATH = DISTRIBUTE_PATH + EgovProperties.getProperty("Globals.DstbtTempDir");
/* 65 */   public static final String DISTRIBUTE_UPLOAD_PATH = DISTRIBUTE_PATH + EgovProperties.getProperty("Globals.DstbtUploadDir");
/*    */ 
/* 68 */   public static final String URL_EXT = EgovProperties.getProperty("Globals.UrlExt");
/*    */   public static final String LOGIN_SESSION_NM = "loginVO";
/*    */   public static final String TEMP_LOGIN_SESSION_NM = "tempLoginVO";
/*    */   public static final String REALNAME_SESSION_NM = "realnameVO";
/*    */   public static final String SNSLOGIN_SESSION_NM = "snsLoginVO";
/*    */   public static final String TEMP_REALNAME_SESSION_NM = "tempRealnameSession";
/* 77 */   public static final String REPORT_URL = EgovProperties.getProperty("Globals.reportURL");
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.Globals
 * JD-Core Version:    0.6.2
 */