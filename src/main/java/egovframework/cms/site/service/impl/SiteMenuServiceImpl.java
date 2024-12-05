package egovframework.cms.site.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.cms.cmm.service.EgovFileTool;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.Globals;
import egovframework.cms.site.service.SiteMenuService;
import egovframework.cms.site.vo.SiteMenuJsonVO;
import egovframework.cms.site.vo.SiteMenuVO;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

@Service("siteMenuService")
public class SiteMenuServiceImpl extends EgovAbstractServiceImpl
  implements SiteMenuService
{

  @Resource(name="siteMenuMapper")
  private SiteMenuMapper siteMenuMapper;

  public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO vo)
    throws Exception
  {
    return this.siteMenuMapper.selectSiteMenuList(vo);
  }

  public List<SiteMenuVO> selectParntsSiteMenuList(SiteMenuVO vo)
    throws Exception
  {
    return this.siteMenuMapper.selectParntsSiteMenuList(vo);
  }

  public List<SiteMenuVO> selectChldrnSiteMenuList(SiteMenuVO vo)
    throws Exception
  {
    return this.siteMenuMapper.selectChldrnSiteMenuList(vo);
  }

  public SiteMenuVO selectSiteMenu(SiteMenuVO vo)
    throws Exception
  {
    return this.siteMenuMapper.selectSiteMenu(vo);
  }

  public int selectSiteMenuRootMenuId(SiteMenuVO vo)
    throws Exception
  {
    return this.siteMenuMapper.selectSiteMenuRootMenuId(vo).intValue();
  }

  public void insertSiteMenu(SiteMenuVO vo)
    throws Exception
  {
    vo.setShowSatisAt(EgovStringUtil.nullConvert(vo.getShowSatisAt(), "N"));
    vo.setAppointStaffAt(EgovStringUtil.nullConvert(vo.getAppointStaffAt(), "N"));
    vo.setMainMenuAt(EgovStringUtil.nullConvert(vo.getMainMenuAt(), "N"));
    vo.setShowAt(EgovStringUtil.nullConvert(vo.getShowAt(), "N"));
    vo.setUseAt(EgovStringUtil.nullConvert(vo.getUseAt(), "N"));
    vo.setShowCpyrgtAt(EgovStringUtil.nullConvert(vo.getShowCpyrgtAt(), "N"));
    vo.setShowSnsAt(EgovStringUtil.nullConvert(vo.getShowSnsAt(), "N"));
    vo.setLimitGroupIds(EgovStringUtil.join(vo.getLimitGroupIdArr(), ","));

    if (vo.getParntsMenuId() < 1) {
      SiteMenuVO siteMenuVO = new SiteMenuVO();
      siteMenuVO.setSiteId(vo.getSiteId());
      vo.setParntsMenuId(selectSiteMenuRootMenuId(siteMenuVO));
    }

    int parntsMenuId = vo.getParntsMenuId();

    SiteMenuVO pvo = null;
    String[] menuNm = vo.getMenuNm().split(",");
    HashMap hm = null;
    for (int i = 0; i < menuNm.length; i++) {
      pvo = new SiteMenuVO();
      pvo.setSiteId(vo.getSiteId());
      pvo.setMenuId(parntsMenuId);
      pvo = selectSiteMenu(pvo);
      if (pvo == null) {
        pvo = new SiteMenuVO();
        pvo.setSiteId(vo.getSiteId());
        pvo.setRgt(0);
        pvo.setLvl(0);
      }

      hm = new HashMap();
      hm.put("siteId", pvo.getSiteId());
      hm.put("lft", Integer.valueOf(pvo.getRgt()));
      this.siteMenuMapper.updateSiteMenuLftForInsert(hm);

      hm = new HashMap();
      hm.put("siteId", pvo.getSiteId());
      hm.put("rgt", Integer.valueOf(pvo.getRgt() - 1));
      this.siteMenuMapper.updateSiteMenuRgtForInsert(hm);

      vo.setParntsMenuId(parntsMenuId);
      vo.setMenuNm(menuNm[i]);
      vo.setLft(pvo.getRgt());
      vo.setRgt(pvo.getRgt() + 1);
      vo.setLvl(pvo.getLvl() + 1);

      this.siteMenuMapper.insertSiteMenu(vo);
    }
  }

  public void updateSiteMenu(SiteMenuVO vo)
    throws Exception
  {
    String updateChldrnsLayout = vo.getUpdateChldrnsLayout();
    String updateChldrnsMngStaff = vo.getUpdateChldrnsMngStaff();
    String updateChldrnsSatis = vo.getUpdateChldrnsSatis();
    String updateChldrnsCpyrgtAt = vo.getUpdateChldrnsCpyrgtAt();
    String updateChldrnsSnsAt = vo.getUpdateChldrnsSnsAt();
    String updateChldrnsMngUserIds = vo.getUpdateChldrnsMngUserIds();

    if (("Y".equals(updateChldrnsLayout)) || ("Y".equals(updateChldrnsMngStaff)) || ("Y".equals(updateChldrnsSatis)) || ("Y".equals(updateChldrnsCpyrgtAt)) || ("Y".equals(updateChldrnsMngUserIds)) || ("Y".equals(updateChldrnsSnsAt)))
    {
      this.siteMenuMapper.updateChldrnSiteMenu(vo);
    }

    vo.setShowSatisAt(EgovStringUtil.nullConvert(vo.getShowSatisAt(), "N"));
    vo.setAppointStaffAt(EgovStringUtil.nullConvert(vo.getAppointStaffAt(), "N"));
    vo.setMainMenuAt(EgovStringUtil.nullConvert(vo.getMainMenuAt(), "N"));
    vo.setShowAt(EgovStringUtil.nullConvert(vo.getShowAt(), "N"));
    vo.setUseAt(EgovStringUtil.nullConvert(vo.getUseAt(), "N"));
    vo.setShowCpyrgtAt(EgovStringUtil.nullConvert(vo.getShowCpyrgtAt(), "N"));
    vo.setShowSnsAt(EgovStringUtil.nullConvert(vo.getShowSnsAt(), "N"));
    vo.setLimitGroupIds(EgovStringUtil.join(vo.getLimitGroupIdArr(), ","));

    this.siteMenuMapper.updateSiteMenu(vo);
  }

  public void deleteSiteMenu(SiteMenuVO vo)
    throws Exception
  {
    this.siteMenuMapper.deleteSiteMenu(vo);

    int width = vo.getRgt() - vo.getLft() + 1;

    HashMap hm = new HashMap();
    hm.put("siteId", vo.getSiteId());
    hm.put("lft", Integer.valueOf(vo.getRgt()));
    hm.put("width", Integer.valueOf(width));
    this.siteMenuMapper.updateSiteMenuLftForDelete(hm);

    hm = new HashMap();
    hm.put("siteId", vo.getSiteId());
    hm.put("rgt", Integer.valueOf(vo.getRgt()));
    hm.put("width", Integer.valueOf(width));
    this.siteMenuMapper.updateSiteMenuRgtForDelete(hm);
  }

  public void deleteSiteMenuBySiteId(SiteMenuVO vo)
    throws Exception
  {
    this.siteMenuMapper.deleteSiteMenuBySiteId(vo);
  }

  public void updateSiteMenuMove(String siteId, String jsonData)
    throws Exception
  {
	  jsonData = jsonData.replace("&quot;", "\"");
	  
    if (!"".equals(EgovStringUtil.nullConvert(jsonData))) {
      Object obj = JSONValue.parse(jsonData);
      
      JSONArray array = (JSONArray)obj;

      SiteMenuVO searchVO = new SiteMenuVO();
      searchVO.setSiteId(siteId);
      int rootMenuId = selectSiteMenuRootMenuId(searchVO);

      SiteMenuVO vo = null;
      JSONObject obj2 = null;
      for (int i = 0; i < array.size(); i++) {
        obj2 = (JSONObject)array.get(i);
        if (obj2.get("item_id") != null)
        {
          int itemId = Integer.parseInt(EgovStringUtil.nullConvert(obj2.get("item_id")));
          int parentId = obj2.get("parent_id") == null ? rootMenuId : Integer.parseInt(EgovStringUtil.nullConvert(obj2.get("parent_id")));
          int depth = ((Long)obj2.get("depth")).intValue();
          int left = ((Long)obj2.get("left")).intValue();
          int right = ((Long)obj2.get("right")).intValue();

          vo = new SiteMenuVO();
          vo.setSiteId(siteId);
          vo.setMenuId(itemId);
          vo.setParntsMenuId(parentId);
          vo.setLvl(depth + 1);
          vo.setLft(left - 1);
          vo.setRgt(right - 1);

          this.siteMenuMapper.updateSiteMenuMove(vo);
        }
      }
    }
  }

  public void siteMenuDistribute(HttpServletRequest request, String siteId) throws Exception
  {
    SiteMenuJsonVO searchSiteMenuJsonVO = new SiteMenuJsonVO();
    searchSiteMenuJsonVO.setSiteId(siteId);
    List list = this.siteMenuMapper.selectSiteMenuJsonList(searchSiteMenuJsonVO);

    String filePath = Globals.DISTRIBUTE_PATH + "/sites/" + siteId + "/data/menu.ser";
    EgovFileTool.createNewFile(filePath);

    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
    oos.writeObject(list);
    oos.close();
  }
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.site.service.impl.SiteMenuServiceImpl
 * JD-Core Version:    0.6.2
 */