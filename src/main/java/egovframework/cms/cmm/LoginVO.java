/*     */ package egovframework.cms.cmm;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class LoginVO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8274004534207618049L;
/*     */   private String id;
/*     */   private String name;
/*     */   private String ihidNum;
/*     */   private String email;
/*     */   private String password;
/*     */   private String passwordHint;
/*     */   private String passwordCnsr;
/*     */   private String userSe;
/*     */   private String deptId;
/*     */   private String deptNm;
/*     */   private String zipcode;
/*     */   private String address;
/*     */   private String detailAddress;
/*     */   private String telNo;
/*     */   private String hpNo;
/*     */   private String groupIds;
/*     */   private int levelId;
/*     */   private String status;
/*     */   private String lastPwchangeDttm;
/*     */   private String uniqId;
/*     */   private String url;
/*     */   private String ip;
/*     */   private String dn;
/*     */ 
/*     */   public String getId()
/*     */   {
/*  73 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  78 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  83 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  88 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getIhidNum()
/*     */   {
/*  93 */     return this.ihidNum;
/*     */   }
/*     */ 
/*     */   public void setIhidNum(String ihidNum)
/*     */   {
/*  98 */     this.ihidNum = ihidNum;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 103 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 108 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getPassword()
/*     */   {
/* 113 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 118 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public String getPasswordHint()
/*     */   {
/* 123 */     return this.passwordHint;
/*     */   }
/*     */ 
/*     */   public void setPasswordHint(String passwordHint)
/*     */   {
/* 128 */     this.passwordHint = passwordHint;
/*     */   }
/*     */ 
/*     */   public String getPasswordCnsr()
/*     */   {
/* 133 */     return this.passwordCnsr;
/*     */   }
/*     */ 
/*     */   public void setPasswordCnsr(String passwordCnsr)
/*     */   {
/* 138 */     this.passwordCnsr = passwordCnsr;
/*     */   }
/*     */ 
/*     */   public String getUserSe()
/*     */   {
/* 143 */     return this.userSe;
/*     */   }
/*     */ 
/*     */   public void setUserSe(String userSe)
/*     */   {
/* 148 */     this.userSe = userSe;
/*     */   }
/*     */ 
/*     */   public String getDeptId()
/*     */   {
/* 153 */     return this.deptId;
/*     */   }
/*     */ 
/*     */   public void setDeptId(String deptId)
/*     */   {
/* 158 */     this.deptId = deptId;
/*     */   }
/*     */ 
/*     */   public String getDeptNm()
/*     */   {
/* 163 */     return this.deptNm;
/*     */   }
/*     */ 
/*     */   public void setDeptNm(String deptNm)
/*     */   {
/* 168 */     this.deptNm = deptNm;
/*     */   }
/*     */ 
/*     */   public String getZipcode()
/*     */   {
/* 173 */     return this.zipcode;
/*     */   }
/*     */ 
/*     */   public void setZipcode(String zipcode)
/*     */   {
/* 178 */     this.zipcode = zipcode;
/*     */   }
/*     */ 
/*     */   public String getAddress()
/*     */   {
/* 183 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/* 188 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public String getDetailAddress()
/*     */   {
/* 193 */     return this.detailAddress;
/*     */   }
/*     */ 
/*     */   public void setDetailAddress(String detailAddress)
/*     */   {
/* 198 */     this.detailAddress = detailAddress;
/*     */   }
/*     */ 
/*     */   public String getTelNo()
/*     */   {
/* 203 */     return this.telNo;
/*     */   }
/*     */ 
/*     */   public void setTelNo(String telNo)
/*     */   {
/* 208 */     this.telNo = telNo;
/*     */   }
/*     */ 
/*     */   public String getHpNo()
/*     */   {
/* 213 */     return this.hpNo;
/*     */   }
/*     */ 
/*     */   public void setHpNo(String hpNo)
/*     */   {
/* 218 */     this.hpNo = hpNo;
/*     */   }
/*     */ 
/*     */   public String getGroupIds()
/*     */   {
/* 223 */     return this.groupIds;
/*     */   }
/*     */ 
/*     */   public void setGroupIds(String groupIds)
/*     */   {
/* 228 */     this.groupIds = groupIds;
/*     */   }
/*     */ 
/*     */   public int getLevelId()
/*     */   {
/* 233 */     return this.levelId;
/*     */   }
/*     */ 
/*     */   public void setLevelId(int levelId)
/*     */   {
/* 238 */     this.levelId = levelId;
/*     */   }
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 243 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 248 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getLastPwchangeDttm()
/*     */   {
/* 253 */     return this.lastPwchangeDttm;
/*     */   }
/*     */ 
/*     */   public void setLastPwchangeDttm(String lastPwchangeDttm)
/*     */   {
/* 258 */     this.lastPwchangeDttm = lastPwchangeDttm;
/*     */   }
/*     */ 
/*     */   public String getUniqId()
/*     */   {
/* 263 */     return this.uniqId;
/*     */   }
/*     */ 
/*     */   public void setUniqId(String uniqId)
/*     */   {
/* 268 */     this.uniqId = uniqId;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/* 273 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 278 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getIp()
/*     */   {
/* 283 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/* 288 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public String getDn()
/*     */   {
/* 293 */     return this.dn;
/*     */   }
/*     */ 
/*     */   public void setDn(String dn)
/*     */   {
/* 298 */     this.dn = dn;
/*     */   }
/*     */ 
/*     */   public static long getSerialversionuid()
/*     */   {
/* 303 */     return -8274004534207618049L;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.LoginVO
 * JD-Core Version:    0.6.2
 */