/*     */ package egovframework.cms.system.organization.service.impl;
/*     */ 
/*     */ import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/*     */ import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/*     */ import egovframework.cms.cmm.service.EgovStringUtil;
/*     */ import egovframework.cms.cmm.service.UserService;
/*     */ import egovframework.cms.system.organization.service.OrganizationDeptService;
/*     */ import egovframework.cms.system.organization.vo.OrganizationDeptVO;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
/*     */ import org.json.simple.JSONValue;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("organizationDeptService")
/*     */ public class OrganizationDeptServiceImpl extends EgovAbstractServiceImpl
/*     */   implements OrganizationDeptService
/*     */ {
/*     */ 
/*     */   @Resource(name="organizationDeptMapper")
/*     */   private OrganizationDeptMapper organizationDeptMapper;
/*     */ 
/*     */   @Resource(name="egovOrganizationDeptIdGnrService")
/*     */   private EgovIdGnrService egovOrganizationDeptIdGnrService;
/*     */ 

/*     */ 
/*     */   public List<OrganizationDeptVO> selectOrganizationDeptList(OrganizationDeptVO searchOrganizationDeptVO)
/*     */     throws Exception
/*     */   {
/*  36 */     return this.organizationDeptMapper.selectOrganizationDeptList(searchOrganizationDeptVO);
/*     */   }
/*     */ 
/*     */   public List<OrganizationDeptVO> selectParntsOrganizationDeptList(OrganizationDeptVO searchOrganizationDeptVO)
/*     */     throws Exception
/*     */   {
/*  43 */     return this.organizationDeptMapper.selectParntsOrganizationDeptList(searchOrganizationDeptVO);
/*     */   }
/*     */ 
/*     */   public List<OrganizationDeptVO> selectChldrnOrganizationDeptList(OrganizationDeptVO searchOrganizationDeptVO)
/*     */     throws Exception
/*     */   {
/*  50 */     return this.organizationDeptMapper.selectChldrnOrganizationDeptList(searchOrganizationDeptVO);
/*     */   }
/*     */ 
/*     */   public OrganizationDeptVO selectOrganizationDept(OrganizationDeptVO searchOrganizationDeptVO)
/*     */     throws Exception
/*     */   {
/*  56 */     return this.organizationDeptMapper.selectOrganizationDept(searchOrganizationDeptVO);
/*     */   }
/*     */ 
/*     */   public String selectOrganizationRootDeptId(OrganizationDeptVO searchOrganizationDeptVO)
/*     */     throws Exception
/*     */   {
/*  62 */     return this.organizationDeptMapper.selectOrganizationRootDeptId(searchOrganizationDeptVO);
/*     */   }
/*     */ 
/*     */   public void insertOrganizationDept(OrganizationDeptVO insertOrganizationDeptVO)
/*     */     throws Exception
/*     */   {
/*  68 */     if ("".equals(insertOrganizationDeptVO.getParntsDeptId())) {
/*  69 */       insertOrganizationDeptVO.setParntsDeptId(this.organizationDeptMapper.selectOrganizationRootDeptId(null));
/*     */     }
/*     */ 
/*  72 */     String parntsDeptId = EgovStringUtil.nullConvert(insertOrganizationDeptVO.getParntsDeptId());
/*     */ 
/*  74 */     OrganizationDeptVO pvo = new OrganizationDeptVO();
/*  75 */     pvo.setDeptId(parntsDeptId);
/*  76 */     pvo = this.organizationDeptMapper.selectOrganizationDept(pvo);
/*     */ 
/*  78 */     if (pvo == null)
/*     */     {
/*  80 */       pvo = new OrganizationDeptVO();
/*  81 */       pvo.setRgt(0);
/*  82 */       pvo.setLvl(0);
/*     */     }
/*     */ 
/*  85 */     HashMap hm = new HashMap();
/*  86 */     hm.put("lft", Integer.valueOf(pvo.getRgt()));
/*  87 */     this.organizationDeptMapper.updateOrganizationDeptLftForInsert(hm);
/*     */ 
/*  89 */     hm = new HashMap();
/*  90 */     hm.put("rgt", Integer.valueOf(pvo.getRgt() - 1));
/*  91 */     this.organizationDeptMapper.updateOrganizationDeptRgtForInsert(hm);
/*     */ 
/*  93 */     insertOrganizationDeptVO.setDeptId(this.egovOrganizationDeptIdGnrService.getNextStringId());
/*  94 */     insertOrganizationDeptVO.setParntsDeptId(parntsDeptId);
/*  95 */     insertOrganizationDeptVO.setLft(pvo.getRgt());
/*  96 */     insertOrganizationDeptVO.setRgt(pvo.getRgt() + 1);
/*  97 */     insertOrganizationDeptVO.setLvl(pvo.getLvl() + 1);
/*     */ 
/*  99 */     this.organizationDeptMapper.insertOrganizationDept(insertOrganizationDeptVO);
/*     */   }
/*     */ 
/*     */   public void updateOrganizationDept(OrganizationDeptVO updateOrganizationDeptVO)
/*     */     throws Exception
/*     */   {
/* 105 */     this.organizationDeptMapper.updateOrganizationDept(updateOrganizationDeptVO);
/*     */   }
/*     */ 
/*     */   public void deleteOrganizationDept(OrganizationDeptVO organizationDeptVO)
/*     */     throws Exception
/*     */   {
/* 112 */     List chldrnList = this.organizationDeptMapper.selectChldrnOrganizationDeptList(organizationDeptVO);
/*     */ 
/* 114 */     if (chldrnList.size() > 0)
/*     */     {
/* 116 */       for (int i = 0; i < chldrnList.size(); i++);
/*     */     }
/*     */ 
/* 125 */     this.organizationDeptMapper.deleteOrganizationDept(organizationDeptVO);
/*     */ 
/* 127 */     int width = organizationDeptVO.getRgt() - organizationDeptVO.getLft() + 1;
/*     */ 
/* 129 */     HashMap hm = new HashMap();
/* 130 */     hm.put("lft", Integer.valueOf(organizationDeptVO.getRgt()));
/* 131 */     hm.put("width", Integer.valueOf(width));
/* 132 */     this.organizationDeptMapper.updateOrganizationDeptLftForDelete(hm);
/*     */ 
/* 134 */     hm = new HashMap();
/* 135 */     hm.put("rgt", Integer.valueOf(organizationDeptVO.getRgt()));
/* 136 */     hm.put("width", Integer.valueOf(width));
/* 137 */     this.organizationDeptMapper.updateOrganizationDeptRgtForDelete(hm);
/*     */   }
/*     */ 
/*     */   public void updateOrganizationDeptMove(String jsonData)
/*     */     throws Exception
/*     */   {
/* 143 */     String rootDeptId = this.organizationDeptMapper.selectOrganizationRootDeptId(null).replace("DEPT_", "");
/*     */ 
/* 145 */     Object obj = JSONValue.parse(jsonData);
/* 146 */     JSONArray array = (JSONArray)obj;
/*     */ 
/* 148 */     OrganizationDeptVO vo = null;
/* 149 */     JSONObject obj2 = null;
/*     */ 
/* 151 */     for (int i = 0; i < array.size(); i++)
/*     */     {
/* 153 */       obj2 = (JSONObject)array.get(i);
/*     */ 
/* 155 */       if (obj2.get("item_id") != null)
/*     */       {
/* 159 */         String itemId = EgovStringUtil.nullConvert(obj2.get("item_id"));
/* 160 */         String parentId = obj2.get("parent_id") == null ? rootDeptId : EgovStringUtil.nullConvert(obj2.get("parent_id"));
/* 161 */         int depth = ((Long)obj2.get("depth")).intValue();
/* 162 */         int left = ((Long)obj2.get("left")).intValue();
/* 163 */         int right = ((Long)obj2.get("right")).intValue();
/*     */ 
/* 165 */         vo = new OrganizationDeptVO();
/* 166 */         vo.setDeptId("DEPT_" + itemId);
/* 167 */         vo.setParntsDeptId("DEPT_" + parentId);
/* 168 */         vo.setLvl(depth + 1);
/* 169 */         vo.setLft(left - 1);
/* 170 */         vo.setRgt(right - 1);
/*     */ 
/* 172 */         this.organizationDeptMapper.updateOrganizationDeptMove(vo);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.organization.service.impl.OrganizationDeptServiceImpl
 * JD-Core Version:    0.6.2
 */