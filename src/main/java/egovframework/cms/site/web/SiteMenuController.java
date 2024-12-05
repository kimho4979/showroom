
package egovframework.cms.site.web;



import egovframework.cms.board.service.BoardInfoService;
import egovframework.cms.board.vo.BoardInfoVO;
import egovframework.cms.cmm.CmsManager;
import egovframework.cms.login.service.LoginVO;
import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.cmm.service.EgovFileTool;
import egovframework.cms.cmm.service.EgovProperties;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.cmm.service.Globals;
import egovframework.cms.cmm.util.EgovUserDetailsHelper;
import egovframework.cms.site.service.SiteInfoService;
import egovframework.cms.site.service.SiteMenuService;
import egovframework.cms.site.vo.SiteInfoVO;
import egovframework.cms.site.vo.SiteMenuVO;
import egovframework.cms.system.authority.service.AuthorityService;
import egovframework.cms.system.authority.vo.AuthorityGroupVO;
import egovframework.cms.system.authority.vo.AuthorityLevelVO;
import egovframework.cms.system.employer.vo.EmployerVO;
import egovframework.cms.system.organization.service.OrganizationDeptService;
import egovframework.cms.system.organization.service.OrganizationStaffService;
import egovframework.cms.system.organization.vo.OrganizationDeptVO;
import egovframework.cms.system.organization.vo.OrganizationStaffVO;
import egovframework.cms.template.service.TemplateService;
import egovframework.cms.template.vo.TemplateFileVO;
import egovframework.cms.template.vo.TemplateInfoVO;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

@Controller
public class SiteMenuController
{

  @Resource(name="siteMenuService")
  private SiteMenuService siteMenuService;

  @Resource(name="siteInfoService")
  private SiteInfoService siteInfoService;

  @Resource(name="templateService")
  private TemplateService templateService;

  @Resource(name="authorityService")
  private AuthorityService authorityService;

  @Resource(name="organizationStaffService")
  private OrganizationStaffService organizationStaffService;

  @Resource(name="organizationDeptService")
  private OrganizationDeptService organizationDeptService;

  @Resource(name="EgovFileMngUtil")
  private EgovFileMngUtil egovFileMngUtil;

  @Resource(name="boardInfoService")
  private BoardInfoService boardInfoService;
  private String CONTENT_PATH = String.format("%s/egovframework/cms/site/menu/", new Object[] { CmsManager.getModulePath() });

  private void checkAdminSiteId(HttpServletRequest request, SiteMenuVO searchSiteMenuVO) throws Exception
  {
    EmployerVO employerVO = (EmployerVO)request.getAttribute("EMPLOYER_INFO");
    String[] adminSiteIdsArr = employerVO.getAdminSiteIdArr();
    if ((!EgovUserDetailsHelper.isMasterAdmin().booleanValue()) && 
      (adminSiteIdsArr != null) && (adminSiteIdsArr.length > 0) && 
      (!Arrays.asList(adminSiteIdsArr).contains(searchSiteMenuVO.getSiteId()))) {
      ModelAndView mv = new ModelAndView("egovframework/wms/access_denied");
      throw new ModelAndViewDefiningException(mv);
    }
  }

  @RequestMapping(value = "/admin/site/menu.do")
  public String write(@ModelAttribute("searchSiteMenuVO") SiteMenuVO searchSiteMenuVO
		  , @ModelAttribute("writeSiteMenu") SiteMenuVO writeSiteMenuVO
		  , @RequestParam(value="siteId", required=false, defaultValue="") String siteId
		  , @RequestParam(value="menuId", required=false, defaultValue="0") int menuId
		  , @RequestParam(value="parntsMenuId", required=false, defaultValue="0") int parntsMenuId
		  , HttpServletRequest request
		  , HttpServletResponse response
		  , ModelMap model)
    throws Exception
  {
	  
	  /*
    EmployerVO employerVO = (EmployerVO)request.getAttribute("EMPLOYER_INFO");

    SiteInfoVO searchSiteInfoVO = new SiteInfoVO();
    if (!EgovUserDetailsHelper.isMasterAdmin().booleanValue()) {
      searchSiteInfoVO.setAdminSiteIdArr(employerVO.getAdminSiteIdArr());
    }
    List siteList = siteInfoService.selectSiteInfoListAll(searchSiteInfoVO);
	
    if ((siteList == null) || ((siteList != null) && (siteList.size() < 1))) {
      model.addAttribute("message", "site.info.nosite");
      return CmsManager.alert(CONTENT_PATH + "result.jsp", model);
    }if (("".equals(siteId)) && (siteList != null)) {
      return "redirect:/site/menu.do?siteId=" + ((SiteInfoVO)siteList.get(0)).getSiteId();
    }

    checkAdminSiteId(request, searchSiteMenuVO);
	
    
	SiteInfoVO siteInfoVO = getSiteInfo(request, siteId);
    if (siteInfoVO == null) {
      model.addAttribute("message", "site.info.nodata");
      return CmsManager.alert(CONTENT_PATH + "result.jsp", model);
    }*/

	try {
		
	
	  
    List<SiteMenuVO> allSiteMenuList = siteMenuService.selectSiteMenuList(searchSiteMenuVO);
    if ((allSiteMenuList == null) || (allSiteMenuList.size() < 1)) {
      writeSiteMenuVO.setSiteId(siteId);
      writeSiteMenuVO.setMenuNm("홈");
      writeSiteMenuVO.setShowAt("Y");
      writeSiteMenuVO.setUseAt("Y");
      siteMenuService.insertSiteMenu(writeSiteMenuVO);
      return "redirect:/site/menu.do?siteId=" + siteId;
    }

    String command = "insert";

    List parntsSiteMenuList = null;

    if (menuId < 1)
    {
      writeSiteMenuVO.setMenuGb("HTML");
      writeSiteMenuVO.setLinkTarget("_SELF");
      writeSiteMenuVO.setUseAt("Y");
      writeSiteMenuVO.setShowAt("Y");
      writeSiteMenuVO.setAppointStaffAt("N");
      writeSiteMenuVO.setShowCpyrgtAt("N");

      if (parntsMenuId > 0)
      {
        SiteMenuVO searchVO = new SiteMenuVO();
        searchVO.setSiteId(siteId);
        searchVO.setMenuId(parntsMenuId);
        parntsSiteMenuList = siteMenuService.selectParntsSiteMenuList(searchVO);

        if ((parntsSiteMenuList == null) || (parntsSiteMenuList.size() < 1))
        {
          model.addAttribute("message", "site.menu.noParntsData");
          return CmsManager.alert(CONTENT_PATH + "result.jsp", model);
        }
        parntsSiteMenuList.remove(0);

        SiteMenuVO parntsSiteMenuVO = new SiteMenuVO();
        parntsSiteMenuVO.setSiteId(siteId);
        parntsSiteMenuVO.setMenuId(parntsMenuId);
        parntsSiteMenuVO = siteMenuService.selectSiteMenu(parntsSiteMenuVO);

        if (parntsSiteMenuVO == null)
        {
          model.addAttribute("message", "site.menu.noParntsData");
          return CmsManager.alert(CONTENT_PATH + "result.jsp", model);
        }
        parntsSiteMenuList.add(parntsSiteMenuVO);

        writeSiteMenuVO.setTemplate(parntsSiteMenuVO.getTemplate());
        writeSiteMenuVO.setLayout(parntsSiteMenuVO.getLayout());
      }

    }
    else
    {
      writeSiteMenuVO = siteMenuService.selectSiteMenu(searchSiteMenuVO);

      if (writeSiteMenuVO == null) {
        model.addAttribute("message", "site.menu.nodata");
        return CmsManager.alert(CONTENT_PATH + "result.jsp", model);
      }

      parntsSiteMenuList = siteMenuService.selectParntsSiteMenuList(searchSiteMenuVO);
      parntsSiteMenuList.remove(0);

      String limitGroupIds = EgovStringUtil.nullConvert(writeSiteMenuVO.getLimitGroupIds());
      writeSiteMenuVO.setLimitGroupIdArr(limitGroupIds.split(","));

      command = "update";
    }
    /*
    TemplateInfoVO templateInfoVO = new TemplateInfoVO();
    templateInfoVO.setUseAt("Y");
    List templateInfoList = templateService.selectTemplateInfoList(templateInfoVO);

    TemplateFileVO templateFileVO = new TemplateFileVO();
    templateFileVO.setFileGb("LAYOUT");
    List templateFileList = templateService.selectTemplateFileList(templateFileVO);
    */
    List<BoardInfoVO> boardList = boardInfoService.selectBoardInfoListAll(null);

    List<SiteMenuVO> treeSiteMenuList = getTreeSiteMenuList(allSiteMenuList);

    List<OrganizationDeptVO> treeDeptList = getTreeDeptList();

    List<OrganizationStaffVO> staffList = null;
    if (!"".equals(EgovStringUtil.nullConvert(writeSiteMenuVO.getMngDeptId())))
    {
      OrganizationDeptVO deptVO = new OrganizationDeptVO();
      deptVO.setDeptId(writeSiteMenuVO.getMngDeptId());
      deptVO = organizationDeptService.selectOrganizationDept(deptVO);
      if (deptVO != null)
      {
        OrganizationStaffVO vo = new OrganizationStaffVO();
        vo.setDeptId(writeSiteMenuVO.getMngDeptId());
        vo.setSearchDeptLft(deptVO.getLft());
        vo.setSearchDeptRgt(deptVO.getRgt());
        //staffList = organizationStaffService.selectOrganizationStaffListAll(vo);
      }
    }

    AuthorityGroupVO searchAuthorityGroupVO = new AuthorityGroupVO();
    //List<AuthorityGroupVO> groupList = authorityService.selectAuthorityGroupList(searchAuthorityGroupVO);
    List<AuthorityGroupVO> groupList = null;
    AuthorityLevelVO searchAuthorityLevelVO = new AuthorityLevelVO();
    //List<AuthorityLevelVO> levelList = authorityService.selectAuthorityLevelList(searchAuthorityLevelVO);
    List<AuthorityLevelVO> levelList = null;
    int rootMenuId = siteMenuService.selectSiteMenuRootMenuId(searchSiteMenuVO);

    model.addAttribute("command", command);
    
    /*model.addAttribute("siteInfoVO", siteInfoVO);
    model.addAttribute("siteList", siteList);*/
    
    model.addAttribute("allSiteMenuList", allSiteMenuList);
    model.addAttribute("parntsSiteMenuList", parntsSiteMenuList);
    model.addAttribute("rootMenuId", Integer.valueOf(rootMenuId));
    model.addAttribute("writeSiteMenu", writeSiteMenuVO);

    model.addAttribute("treeSiteMenuList", treeSiteMenuList);
    model.addAttribute("templateInfoList", null);
    model.addAttribute("templateFileList", null);

    model.addAttribute("boardList", boardList);

    model.addAttribute("groupList", groupList);
    model.addAttribute("levelList", levelList);

    model.addAttribute("treeDeptList", treeDeptList);
    model.addAttribute("staffList", staffList);

    model.addAttribute("contentPath","site/menu/write.jsp");
    
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}

    return "/cms/layout/defaultLayout";
  }

  
  @RequestMapping(value = "/admin/site/menuProc.do")
  public String writeSubmit(@ModelAttribute("searchSiteMenuVO") SiteMenuVO searchSiteMenuVO, MultipartHttpServletRequest multiRequest, @ModelAttribute("writeSiteMenu") SiteMenuVO writeSiteMenuVO, @RequestParam(value="command", required=true, defaultValue="insert") String command, HttpServletRequest request, ModelMap model)
    throws Exception
  {
	  
    /*checkAdminSiteId(request, searchSiteMenuVO);
*/
	  try {
		
	
    LoginVO user = (LoginVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
    String message = "";

    if (EgovStringUtil.nullConvert(request.getParameter("fileDeleteYn")).equals("Y")) {
      writeSiteMenuVO.setImgUrl("");
    }

    FileVO uploadFileVO = uploadPhotoFile(multiRequest, "imageFile");

    if (uploadFileVO != null) {
      writeSiteMenuVO.setImgUrl("/uploads/menu/" + uploadFileVO.getStreFileNm());
    }

    if ("insert".equals(command))
    {
      message = "success.common.insert";
      writeSiteMenuVO.setRegistId(user.getId());
      siteMenuService.insertSiteMenu(writeSiteMenuVO);
    }
    else
    {
      SiteMenuVO vo = siteMenuService.selectSiteMenu(searchSiteMenuVO);
      if (vo == null) {
        model.addAttribute("message", "site.menu.nodata");
        return "/cms/site/menu/result";
      }

      message = "success.common.update";
      writeSiteMenuVO.setLft(vo.getLft());
      writeSiteMenuVO.setRgt(vo.getRgt());
      writeSiteMenuVO.setUpdateId(user.getId());
      siteMenuService.updateSiteMenu(writeSiteMenuVO);
    }

      model.addAttribute("message", message);
    } catch (Exception e) {
		e.printStackTrace();
	}

	  return "/cms/site/menu/result";
  }

  @RequestMapping(value = "/admin/site/menuDel.do")
  public String delete(@ModelAttribute("searchSiteMenuVO") SiteMenuVO searchSiteMenuVO, @RequestParam(value="siteId", required=true) String siteId, @RequestParam(value="menuId", required=true) int menuId, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    //checkAdminSiteId(request, searchSiteMenuVO);

    SiteMenuVO vo = siteMenuService.selectSiteMenu(searchSiteMenuVO);
    if (vo == null) {
      model.addAttribute("message", "site.menu.nodata");
      return "/cms/site/menu/result";
    }

    siteMenuService.deleteSiteMenu(vo);

    model.addAttribute("message", "success.common.delete");

    return "/cms/site/menu/result";
  }

  
  @RequestMapping(value = "/admin/site/menuMove.do")
  public String move(@ModelAttribute("searchSiteMenuVO") SiteMenuVO searchSiteMenuVO, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    //checkAdminSiteId(request, searchSiteMenuVO);

    int rootMenuId = siteMenuService.selectSiteMenuRootMenuId(searchSiteMenuVO);
    List list = siteMenuService.selectSiteMenuList(searchSiteMenuVO);
    model.addAttribute("rootMenuId", Integer.valueOf(rootMenuId));
    model.addAttribute("siteMenuList", list);
    model.addAttribute("contentPath","site/menu/move.jsp");
    return "/cms/layout/defaultLayout";
  }

  @RequestMapping(value = "/admin/site/menuMoveProc.do")
  public String moveSubmit(@ModelAttribute("searchSiteMenuVO") SiteMenuVO searchSiteMenuVO, @RequestParam(value="jsonData", required=true) String jsonData, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    //checkAdminSiteId(request, searchSiteMenuVO);

	try {
		
	System.out.println("jsonData"+jsonData);
    siteMenuService.updateSiteMenuMove(searchSiteMenuVO.getSiteId(), jsonData);

    model.addAttribute("message", "success.common.move");
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}  
    return "/cms/site/menu/result";
  }
  /*
  @RequestMapping(params={"act=distribute"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String delete(@ModelAttribute("searchSiteMenuVO") SiteMenuVO searchSiteMenuVO, @RequestParam(value="siteId", required=true) String siteId, HttpServletRequest request, ModelMap model)
    throws Exception
  {
    //checkAdminSiteId(request, searchSiteMenuVO);

    siteMenuService.siteMenuDistribute(request, siteId);

    model.addAttribute("message", "site.menu.distribute");
    return "/cms/site/menu/result";
  }

  @RequestMapping(params={"act=getOrganizationStaffList"})
  @ResponseBody
  public HashMap<String, String> getOrgnztStaffList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    LinkedHashMap list = new LinkedHashMap();
    list.put("", "+ 담당자를 선택해 주세요");

    String deptId = EgovStringUtil.nullConvert(request.getParameter("mngDeptId"));
    if (!"".equals(deptId))
    {
      OrganizationDeptVO deptVO = new OrganizationDeptVO();
      deptVO.setDeptId(deptId);
      deptVO = organizationDeptService.selectOrganizationDept(deptVO);
      if (deptVO != null) {
        OrganizationStaffVO vo = new OrganizationStaffVO();
        vo.setDeptId(deptId);
        vo.setSearchDeptLft(deptVO.getLft());
        vo.setSearchDeptRgt(deptVO.getRgt());

        List staffList = organizationStaffService.selectOrganizationStaffListAll(vo);

        if (staffList.size() > 0) {
          for (int i = 0; i < staffList.size(); i++) {
            list.put(((OrganizationStaffVO)staffList.get(i)).getStaffId(), String.format("[%s] %s", new Object[] { ((OrganizationStaffVO)staffList.get(i)).getDeptNm(), ((OrganizationStaffVO)staffList.get(i)).getName() }));
          }
        }
      }
    }

    return list;
  }*/

  private List<OrganizationDeptVO> getTreeDeptList() throws Exception
  {
    List list = new ArrayList();

    List deptList = organizationDeptService.selectOrganizationDeptList(null);

    for (int i = 0; i < deptList.size(); i++) {
      if (!"".equals(EgovStringUtil.nullConvert(((OrganizationDeptVO)deptList.get(i)).getParntsDeptId())))
      {
        String deptNm = ((OrganizationDeptVO)deptList.get(i)).getDeptNm();

        if (((OrganizationDeptVO)deptList.get(i)).getLvl() > 2) {
          String lpad = StringUtils.leftPad("", ((OrganizationDeptVO)deptList.get(i)).getLvl() - 2, '*').replace("*", "　");
          deptNm = lpad + " └ " + ((OrganizationDeptVO)deptList.get(i)).getDeptNm();
        }

        OrganizationDeptVO vo = new OrganizationDeptVO();
        vo.setDeptId(((OrganizationDeptVO)deptList.get(i)).getDeptId());
        vo.setDeptNm(deptNm);

        list.add(vo);
      }
    }
    return list;
  }

  private List<SiteMenuVO> getTreeSiteMenuList(List<SiteMenuVO> list) throws Exception
  {
    List rlist = new ArrayList();

    if ((list != null) && (list.size() > 0)) {
      for (int i = 0; i < list.size(); i++) {
        if (((SiteMenuVO)list.get(i)).getParntsMenuId() >= 1)
        {
          String menuNm = ((SiteMenuVO)list.get(i)).getMenuNm();

          if (((SiteMenuVO)list.get(i)).getLvl() > 2) {
            String lpad = StringUtils.leftPad("", ((SiteMenuVO)list.get(i)).getLvl() - 2, '*').replace("*", "　");
            menuNm = lpad + " └ " + ((SiteMenuVO)list.get(i)).getMenuNm();
          }

          SiteMenuVO vo = new SiteMenuVO();
          vo.setMenuId(((SiteMenuVO)list.get(i)).getMenuId());
          vo.setMenuNm(menuNm);

          rlist.add(vo);
        }
      }
    }
    return rlist;
  }

  private SiteInfoVO getSiteInfo(HttpServletRequest request, String siteId) throws Exception
  {
    SiteInfoVO vo = null;

    if (!"".equals(siteId)) {
      vo = new SiteInfoVO();
      vo.setSiteId(siteId);

      vo = siteInfoService.selectSiteInfo(vo);
    }

    return vo;
  }

  private FileVO uploadPhotoFile(MultipartHttpServletRequest multiRequest, String name) throws Exception
  {
	String mode = EgovProperties.getProperty("Globals.Mode");
	String savePath = EgovProperties.getProperty("Globals.DstbtPath"+mode);
	  
    String uploadPath = savePath + "/uploads/menu/";

    String atchFileId = "-1";
    List atchFile = multiRequest.getFiles(name);

    List atchFileList = null;
    try
    {
      atchFileList = egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, "gif,jpg,jpeg,png,bmp", 20);
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    return (atchFileList != null) && (atchFileList.size() > 0) ? (FileVO)atchFileList.get(0) : null;
  }

  private void deletePhotoFile(String fileNm) throws Exception
  {
    if (!"".equals(EgovStringUtil.nullConvert(fileNm)))
      EgovFileTool.deleteFile(Globals.DISTRIBUTE_UPLOAD_PATH + "collection/" + fileNm);
  }
}
