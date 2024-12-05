/*    */ package egovframework.cms.cmm.service.impl;
/*    */ 
/*    */ import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/*    */ import egovframework.cms.cmm.ComDefaultCodeVO;
/*    */ import egovframework.cms.cmm.service.CmmnDetailCode;
/*    */ import egovframework.cms.cmm.service.EgovCmmUseService;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("egovCmmUseService")
/*    */ public class EgovCmmUseServiceImpl extends EgovAbstractServiceImpl
/*    */   implements EgovCmmUseService
/*    */ {
/*    */ 
/*    */   @Resource(name="cmmUseMapper")
/*    */   private CmmUseMapper cmmUseMapper;
/*    */ 
/*    */   public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo)
/*    */     throws Exception
/*    */   {
/* 47 */     return this.cmmUseMapper.selectCmmCodeDetail(vo);
/*    */   }
/*    */ 
/*    */   public Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<?> voList)
/*    */     throws Exception
/*    */   {
/* 60 */     Map map = new HashMap();
/*    */ 
/* 62 */     Iterator iter = voList.iterator();
/* 63 */     while (iter.hasNext()) {
/* 64 */       ComDefaultCodeVO vo = (ComDefaultCodeVO)iter.next();
/* 65 */       map.put(vo.getCodeId(), this.cmmUseMapper.selectCmmCodeDetail(vo));
/*    */     }
/*    */ 
/* 68 */     return map;
/*    */   }
/*    */ 
/*    */   public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo)
/*    */     throws Exception
/*    */   {
/* 80 */     return this.cmmUseMapper.selectOgrnztIdDetail(vo);
/*    */   }
/*    */ 
/*    */   public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo)
/*    */     throws Exception
/*    */   {
/* 92 */     return this.cmmUseMapper.selectGroupIdDetail(vo);
/*    */   }
/*    */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.impl.EgovCmmUseServiceImpl
 * JD-Core Version:    0.6.2
 */