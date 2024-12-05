package egovframework.cms.site.service;

import egovframework.cms.site.vo.SiteMenuVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface SiteMenuService
{
  public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public List<SiteMenuVO> selectParntsSiteMenuList(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public List<SiteMenuVO> selectChldrnSiteMenuList(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public SiteMenuVO selectSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public int selectSiteMenuRootMenuId(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void insertSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void updateSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void deleteSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void deleteSiteMenuBySiteId(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void updateSiteMenuMove(String paramString1, String paramString2)
    throws Exception;

  public void siteMenuDistribute(HttpServletRequest paramHttpServletRequest, String paramString)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.site.service.SiteMenuService
 * JD-Core Version:    0.6.2
 */