/*     */ package egovframework.cms.template.service.impl;
/*     */ 
/*     */ import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/*     */ import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/*     */ import egovframework.cms.cmm.service.EgovFileTool;
/*     */ import egovframework.cms.cmm.service.EgovStringUtil;
/*     */ import egovframework.cms.cmm.service.Globals;
/*     */ import egovframework.cms.template.service.TemplateService;
/*     */ import egovframework.cms.template.vo.TemplateFileVO;
/*     */ import egovframework.cms.template.vo.TemplateInfoVO;
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("templateService")
/*     */ public class TemplateServiceImpl extends EgovAbstractServiceImpl
/*     */   implements TemplateService
/*     */ {
/*     */ 
/*     */   @Resource(name="templateMapper")
/*     */   private TemplateMapper templateMapper;
/*     */ 
/*     */   @Resource(name="egovTemplateFileIdGnrService")
/*     */   private EgovIdGnrService egovTemplateFileIdGnrService;
/*     */ 
/*     */   public List<TemplateInfoVO> selectTemplateInfoList(TemplateInfoVO vo)
/*     */     throws Exception
/*     */   {
/*  31 */     return this.templateMapper.selectTemplateInfoList(vo);
/*     */   }
/*     */ 
/*     */   public TemplateInfoVO selectTemplateInfo(TemplateInfoVO vo)
/*     */     throws Exception
/*     */   {
/*  37 */     return this.templateMapper.selectTemplateInfo(vo);
/*     */   }
/*     */ 
/*     */   public void insertTemplateInfo(TemplateInfoVO vo)
/*     */     throws Exception
/*     */   {
/*  43 */     this.templateMapper.insertTemplateInfo(vo);
/*     */ 
/*  45 */     String dirPath = Globals.DISTRIBUTE_TEMPLATE_PATH + vo.getTplId() + "/";
/*  46 */     dirPath = dirPath.replace('\\', File.separatorChar).replace('/', File.separatorChar);
/*  47 */     EgovFileTool.createNewDirectory(dirPath);
/*     */ 
/*  49 */     String copyTplId = EgovStringUtil.nullConvert(vo.getCopyTplId());
/*  50 */     if (!"".equals(copyTplId))
/*     */     {
/*  52 */       TemplateFileVO templateFileVO = new TemplateFileVO();
/*  53 */       templateFileVO.setTplId(copyTplId);
/*  54 */       List templateFileList = selectTemplateFileList(templateFileVO);
/*     */ 
/*  56 */       if ((templateFileList != null) && (templateFileList.size() > 0))
/*     */       {
/*  58 */         for (int i = 0; i < templateFileList.size(); i++)
/*     */         {
/*  60 */           ((TemplateFileVO)templateFileList.get(i)).setTplId(vo.getTplId());
/*  61 */           ((TemplateFileVO)templateFileList.get(i)).setTplFileId(this.egovTemplateFileIdGnrService.getNextIntegerId());
/*  62 */           ((TemplateFileVO)templateFileList.get(i)).setRegistId(vo.getRegistId());
/*  63 */           this.templateMapper.insertTemplateFile((TemplateFileVO)templateFileList.get(i));
/*     */         }
/*     */       }
/*     */ 
/*  67 */       String originalDirPath = Globals.DISTRIBUTE_TEMPLATE_PATH + copyTplId + "/";
/*  68 */       originalDirPath = originalDirPath.replace('\\', File.separatorChar).replace('/', File.separatorChar);
/*  69 */       EgovFileTool.copyDirectory(originalDirPath, dirPath);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateTemplateInfo(TemplateInfoVO vo)
/*     */     throws Exception
/*     */   {
/*  76 */     this.templateMapper.updateTemplateInfo(vo);
/*     */   }
/*     */ 
/*     */   public void deleteTemplateInfo(TemplateInfoVO vo)
/*     */     throws Exception
/*     */   {
/*  82 */     this.templateMapper.deleteTemplateInfo(vo);
/*     */ 
/*  84 */     String filePath = Globals.DISTRIBUTE_TEMPLATE_PATH + vo.getTplId() + "/";
/*  85 */     filePath = filePath.replace('\\', File.separatorChar).replace('/', File.separatorChar);
/*  86 */     EgovFileTool.deleteDirectory(filePath);
/*     */ 
/*  88 */     TemplateFileVO templateFileVO = new TemplateFileVO();
/*  89 */     templateFileVO.setTplId(vo.getTplId());
/*  90 */     this.templateMapper.deleteTemplateFile(templateFileVO);
/*     */   }
/*     */ 
/*     */   public List<TemplateFileVO> selectTemplateFileList(TemplateFileVO vo)
/*     */     throws Exception
/*     */   {
/*  96 */     return this.templateMapper.selectTemplateFileList(vo);
/*     */   }
/*     */ 
/*     */   public TemplateFileVO selectTemplateFile(TemplateFileVO vo)
/*     */     throws Exception
/*     */   {
/* 102 */     return this.templateMapper.selectTemplateFile(vo);
/*     */   }
/*     */ 
/*     */   public void insertTemplateFile(TemplateFileVO vo)
/*     */     throws Exception
/*     */   {
/* 108 */     vo.setTplFileId(this.egovTemplateFileIdGnrService.getNextIntegerId());
/*     */ 
/* 110 */     this.templateMapper.insertTemplateFile(vo);
/*     */ 
/* 112 */     String filePath = getFilePath(vo);
/* 113 */     String fileAbsolutePath = EgovFileTool.createNewFile(filePath.concat(vo.getFileNm()));
/* 114 */     FileUtils.writeStringToFile(new File(fileAbsolutePath), vo.getFileCn(), "UTF-8");
/*     */   }
/*     */ 
/*     */   public void updateTemplateFile(TemplateFileVO vo)
/*     */     throws Exception
/*     */   {
/* 120 */     this.templateMapper.updateTemplateFile(vo);
/*     */ 
/* 122 */     String filePath = getFilePath(vo);
/* 123 */     String fileAbsolutePath = EgovFileTool.createNewFile(filePath.concat(vo.getFileNm()));
/* 124 */     FileUtils.writeStringToFile(new File(fileAbsolutePath), vo.getFileCn(), "UTF-8");
/*     */   }
/*     */ 
/*     */   public void deleteTemplateFile(TemplateFileVO vo)
/*     */     throws Exception
/*     */   {
/* 130 */     this.templateMapper.deleteTemplateFile(vo);
/*     */ 
/* 132 */     String filePath = getFilePath(vo);
/* 133 */     EgovFileTool.deleteFile(filePath.concat(vo.getFileNm()));
/*     */   }
/*     */ 
/*     */   private String getFilePath(TemplateFileVO searchTemplateFileVO) throws Exception
/*     */   {
/* 138 */     if ("".equals(EgovStringUtil.nullConvert(searchTemplateFileVO.getFileGb()))) {
/* 139 */       searchTemplateFileVO.setFileGb("LAYOUT");
/*     */     }
/*     */ 
/* 142 */     String filePath = Globals.DISTRIBUTE_TEMPLATE_PATH;
/* 143 */     filePath = filePath.concat(searchTemplateFileVO.getTplId()).concat("/");
/* 144 */     if (!"LAYOUT".equals(searchTemplateFileVO.getFileGb())) {
/* 145 */       filePath = filePath.concat(searchTemplateFileVO.getFileGb().toLowerCase()).concat("/");
/*     */     }
/* 147 */     filePath = filePath.replace('\\', File.separatorChar).replace('/', File.separatorChar);
/*     */ 
/* 149 */     return filePath;
/*     */   }
/*     */ }

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.template.service.impl.TemplateServiceImpl
 * JD-Core Version:    0.6.2
 */