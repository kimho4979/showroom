/*    */ package egovframework.cms.system.organization.service.impl;
/*    */ 
/*    */ import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/*    */ import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/*    */ import egovframework.cms.cmm.service.UserService;
/*    */ import egovframework.cms.system.organization.service.OrganizationStaffService;
/*    */ import egovframework.cms.system.organization.vo.OrganizationStaffVO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("organizationStaffService")
/*    */ public class OrganizationStaffServiceImpl extends EgovAbstractServiceImpl
/*    */   implements OrganizationStaffService
/*    */ {
/*    */ 
/*    */   @Resource(name="organizationStaffMapper")
/*    */   private OrganizationStaffMapper organizationStaffMapper;
/*    */ 
/*    */   @Resource(name="egovOrganizationStaffIdGnrService")
/*    */   private EgovIdGnrService egovOrganizationStaffIdGnrService;
/*    */ 

/*    */ 
/*    */   public Map<String, Object> selectOrganizationStaffList(OrganizationStaffVO searchOrganizationStaffVO)
/*    */     throws Exception
/*    */   {
/* 34 */     List result = new ArrayList();
/* 35 */     int cnt = this.organizationStaffMapper.selectOrganizationStaffListCnt(searchOrganizationStaffVO).intValue();
/* 36 */     if (cnt > 0) {
/* 37 */       result = this.organizationStaffMapper.selectOrganizationStaffList(searchOrganizationStaffVO);
/*    */     }
/*    */ 
/* 40 */     Map map = new HashMap();
/*    */ 
/* 42 */     map.put("resultList", result);
/* 43 */     map.put("resultCnt", Integer.toString(cnt));
/*    */ 
/* 45 */     return map;
/*    */   }
/*    */ 
/*    */   public List<OrganizationStaffVO> selectOrganizationStaffListAll(OrganizationStaffVO searchOrganizationStaffVO)
/*    */     throws Exception
/*    */   {
/* 52 */     return this.organizationStaffMapper.selectOrganizationStaffListAll(searchOrganizationStaffVO);
/*    */   }
/*    */ 
/*    */   public OrganizationStaffVO selectOrganizationStaff(OrganizationStaffVO searchOrganizationStaffVO)
/*    */     throws Exception
/*    */   {
/* 58 */     return this.organizationStaffMapper.selectOrganizationStaff(searchOrganizationStaffVO);
/*    */   }
/*    */ 
/*    */   public void insertOrganizationStaff(OrganizationStaffVO insertOrganizationStaffVO)
/*    */     throws Exception
/*    */   {
/* 64 */     insertOrganizationStaffVO.setStaffId(this.egovOrganizationStaffIdGnrService.getNextStringId());
/* 65 */     this.organizationStaffMapper.insertOrganizationStaff(insertOrganizationStaffVO);
/*    */   }
/*    */ 
/*    */   public void updateOrganizationStaff(OrganizationStaffVO updateOrganizationStaffVO)
/*    */     throws Exception
/*    */   {
/* 71 */     this.organizationStaffMapper.updateOrganizationStaff(updateOrganizationStaffVO);
/*    */   }
/*    */ 
/*    */   public void updateOrganizationStaffEtcByStaffIds(String[] staffIds, OrganizationStaffVO updateOrganizationStaffVO)
/*    */     throws Exception
/*    */   {
/* 78 */     updateOrganizationStaffVO.setSearchStaffIds(staffIds);
/* 79 */     this.organizationStaffMapper.updateOrganizationStaffEtcByStaffIds(updateOrganizationStaffVO);
/*    */   }
/*    */ 
/*    */   public void deleteOrganizationStaff(OrganizationStaffVO searchOrganizationStaffVO)
/*    */     throws Exception
/*    */   {
/* 85 */     this.organizationStaffMapper.deleteOrganizationStaff(searchOrganizationStaffVO);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.organization.service.impl.OrganizationStaffServiceImpl
 * JD-Core Version:    0.6.2
 */