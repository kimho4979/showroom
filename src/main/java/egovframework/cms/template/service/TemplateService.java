package egovframework.cms.template.service;

import egovframework.cms.template.vo.TemplateFileVO;
import egovframework.cms.template.vo.TemplateInfoVO;
import java.util.List;

public abstract interface TemplateService
{
  public abstract List<TemplateInfoVO> selectTemplateInfoList(TemplateInfoVO paramTemplateInfoVO)
    throws Exception;

  public abstract TemplateInfoVO selectTemplateInfo(TemplateInfoVO paramTemplateInfoVO)
    throws Exception;

  public abstract void insertTemplateInfo(TemplateInfoVO paramTemplateInfoVO)
    throws Exception;

  public abstract void updateTemplateInfo(TemplateInfoVO paramTemplateInfoVO)
    throws Exception;

  public abstract void deleteTemplateInfo(TemplateInfoVO paramTemplateInfoVO)
    throws Exception;

  public abstract List<TemplateFileVO> selectTemplateFileList(TemplateFileVO paramTemplateFileVO)
    throws Exception;

  public abstract TemplateFileVO selectTemplateFile(TemplateFileVO paramTemplateFileVO)
    throws Exception;

  public abstract void insertTemplateFile(TemplateFileVO paramTemplateFileVO)
    throws Exception;

  public abstract void updateTemplateFile(TemplateFileVO paramTemplateFileVO)
    throws Exception;

  public abstract void deleteTemplateFile(TemplateFileVO paramTemplateFileVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.template.service.TemplateService
 * JD-Core Version:    0.6.2
 */