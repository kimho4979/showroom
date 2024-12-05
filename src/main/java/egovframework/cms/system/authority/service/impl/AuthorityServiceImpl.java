/*    */ package egovframework.cms.system.authority.service.impl;
/*    */ 
/*    */ import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/*    */ import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/*    */ import egovframework.cms.system.authority.service.AuthorityService;
/*    */ import egovframework.cms.system.authority.vo.AuthorityGroupVO;
/*    */ import egovframework.cms.system.authority.vo.AuthorityLevelVO;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("authorityService")
/*    */ public class AuthorityServiceImpl extends EgovAbstractServiceImpl
/*    */   implements AuthorityService
/*    */ {
/*    */ 
/*    */   @Resource(name="authorityMapper")
/*    */   private AuthorityMapper authorityMapper;
/*    */ 
/*    */   @Resource(name="egovAuthorityGroupIdGnrService")
/*    */   private EgovIdGnrService egovAuthorityGroupIdGnrService;
/*    */ 
/*    */   public List<AuthorityGroupVO> selectAuthorityGroupList(AuthorityGroupVO searchAuthorityGroupVO)
/*    */     throws Exception
/*    */   {
/* 27 */     return this.authorityMapper.selectAuthorityGroupList(searchAuthorityGroupVO);
/*    */   }
/*    */ 
/*    */   public AuthorityGroupVO selectAuthorityGroup(AuthorityGroupVO searchAuthorityGroupVO)
/*    */     throws Exception
/*    */   {
/* 33 */     return this.authorityMapper.selectAuthorityGroup(searchAuthorityGroupVO);
/*    */   }
/*    */ 
/*    */   public void insertAuthorityGroup(AuthorityGroupVO insertAuthorityGroupVO)
/*    */     throws Exception
/*    */   {
/* 39 */     insertAuthorityGroupVO.setGroupId(this.egovAuthorityGroupIdGnrService.getNextStringId());
/* 40 */     this.authorityMapper.insertAuthorityGroup(insertAuthorityGroupVO);
/*    */   }
/*    */ 
/*    */   public void updateAuthorityGroup(AuthorityGroupVO updateAuthorityGroupVO)
/*    */     throws Exception
/*    */   {
/* 46 */     this.authorityMapper.updateAuthorityGroup(updateAuthorityGroupVO);
/*    */   }
/*    */ 
/*    */   public void deleteAuthorityGroup(AuthorityGroupVO searchAuthorityGroupVO)
/*    */     throws Exception
/*    */   {
/* 52 */     this.authorityMapper.deleteAuthorityGroup(searchAuthorityGroupVO);
/*    */   }
/*    */ 
/*    */   public List<AuthorityLevelVO> selectAuthorityLevelList(AuthorityLevelVO searchAuthorityLevelVO)
/*    */     throws Exception
/*    */   {
/* 58 */     return this.authorityMapper.selectAuthorityLevelList(searchAuthorityLevelVO);
/*    */   }
/*    */ 
/*    */   public AuthorityLevelVO selectAuthorityLevel(AuthorityLevelVO searchAuthorityLevelVO)
/*    */     throws Exception
/*    */   {
/* 64 */     return this.authorityMapper.selectAuthorityLevel(searchAuthorityLevelVO);
/*    */   }
/*    */ 
/*    */   public void insertAuthorityLevel(AuthorityLevelVO insertAuthorityLevelVO)
/*    */     throws Exception
/*    */   {
/* 70 */     this.authorityMapper.insertAuthorityLevel(insertAuthorityLevelVO);
/*    */   }
/*    */ 
/*    */   public void updateAuthorityLevel(AuthorityLevelVO updateAuthorityLevelVO)
/*    */     throws Exception
/*    */   {
/* 76 */     this.authorityMapper.updateAuthorityLevel(updateAuthorityLevelVO);
/*    */   }
/*    */ 
/*    */   public void deleteAuthorityLevel(AuthorityLevelVO searchAuthorityLevelVO)
/*    */     throws Exception
/*    */   {
/* 82 */     this.authorityMapper.deleteAuthorityLevel(searchAuthorityLevelVO);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.authority.service.impl.AuthorityServiceImpl
 * JD-Core Version:    0.6.2
 */