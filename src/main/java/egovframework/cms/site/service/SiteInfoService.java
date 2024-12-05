package egovframework.cms.site.service;

import egovframework.cms.site.vo.SiteInfoVO;
import java.util.List;
import java.util.Map;

public abstract interface SiteInfoService
{
  public abstract List<SiteInfoVO> selectSiteInfoListAll(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public abstract Map<String, Object> selectSiteInfoList(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public abstract SiteInfoVO selectSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public abstract void insertSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public abstract void updateSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public abstract void deleteSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.site.service.SiteInfoService
 * JD-Core Version:    0.6.2
 */