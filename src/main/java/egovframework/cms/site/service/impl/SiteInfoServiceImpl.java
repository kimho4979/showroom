package egovframework.cms.site.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.cms.cmm.service.EgovFileTool;
import egovframework.cms.cmm.service.Globals;
import egovframework.cms.site.service.SiteInfoService;
import egovframework.cms.site.vo.SiteInfoVO;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("siteInfoService")
public class SiteInfoServiceImpl extends EgovAbstractServiceImpl
  implements SiteInfoService
{

  @Resource(name="siteInfoMapper")
  private SiteInfoMapper siteInfoMapper;

  @Resource(name="siteMenuMapper")
  private SiteMenuMapper siteMenuMapper;


  public List<SiteInfoVO> selectSiteInfoListAll(SiteInfoVO vo)
    throws Exception
  {
    return siteInfoMapper.selectSiteInfoListAll(vo);
  }

  public Map<String, Object> selectSiteInfoList(SiteInfoVO vo)
    throws Exception
  {
    List result = new ArrayList();
    int cnt = siteInfoMapper.selectSiteInfoListCnt(vo).intValue();
    if (cnt > 0) {
      result = siteInfoMapper.selectSiteInfoList(vo);
    }

    Map map = new HashMap();

    map.put("resultList", result);
    map.put("resultCnt", Integer.toString(cnt));

    return map;
  }

  public SiteInfoVO selectSiteInfo(SiteInfoVO vo)
    throws Exception
  {
    return siteInfoMapper.selectSiteInfo(vo);
  }

  public void insertSiteInfo(SiteInfoVO vo)
    throws Exception
  {
    siteInfoMapper.insertSiteInfo(vo);

    String createPath = Globals.DISTRIBUTE_PATH + "/" + vo.getSiteId();
    createPath = createPath.replace('\\', File.separatorChar).replace('/', File.separatorChar);

    File createDir = new File(createPath);
    createDir.mkdir();
  }

  public void updateSiteInfo(SiteInfoVO vo)
    throws Exception
  {
    siteInfoMapper.updateSiteInfo(vo);
  }

  public void deleteSiteInfo(SiteInfoVO vo)
    throws Exception
  {
    siteInfoMapper.deleteSiteInfo(vo);

    String createdPath = Globals.DISTRIBUTE_PATH + "/" + vo.getSiteId() + "/";
    createdPath = createdPath.replace('\\', File.separatorChar).replace('/', File.separatorChar);
    EgovFileTool.deleteDirectory(createdPath);
  }
}
