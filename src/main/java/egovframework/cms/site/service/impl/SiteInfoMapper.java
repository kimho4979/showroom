package egovframework.cms.site.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.cms.site.vo.SiteInfoVO;
import java.util.List;

@Mapper("siteInfoMapper")
public interface SiteInfoMapper
{
  public Integer selectSiteInfoListCnt(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public List<SiteInfoVO> selectSiteInfoList(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public List<SiteInfoVO> selectSiteInfoListAll(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public SiteInfoVO selectSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public void insertSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public void updateSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;

  public void deleteSiteInfo(SiteInfoVO paramSiteInfoVO)
    throws Exception;
}
