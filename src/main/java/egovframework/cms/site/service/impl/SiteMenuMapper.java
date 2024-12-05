package egovframework.cms.site.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.cms.site.vo.SiteMenuJsonVO;
import egovframework.cms.site.vo.SiteMenuVO;
import java.util.HashMap;
import java.util.List;

@Mapper("siteMenuMapper")
public interface SiteMenuMapper
{
  public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public List<SiteMenuJsonVO> selectSiteMenuJsonList(SiteMenuJsonVO paramSiteMenuJsonVO)
    throws Exception;

  public List<SiteMenuVO> selectParntsSiteMenuList(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public List<SiteMenuVO> selectChldrnSiteMenuList(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public Integer selectSiteMenuRootMenuId(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public SiteMenuVO selectSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void insertSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void updateSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void updateSiteMenuMove(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void updateChldrnSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void updateSiteMenuLftForInsert(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void updateSiteMenuRgtForInsert(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void updateSiteMenuLftForDelete(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void updateSiteMenuRgtForDelete(HashMap<String, Object> paramHashMap)
    throws Exception;

  public void deleteSiteMenu(SiteMenuVO paramSiteMenuVO)
    throws Exception;

  public void deleteSiteMenuBySiteId(SiteMenuVO paramSiteMenuVO)
    throws Exception;
}
