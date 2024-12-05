package egovframework.cms.system.organization.web;

import egovframework.cms.cmm.CmsManager;
import egovframework.cms.cmm.LoginVO;
import egovframework.cms.cmm.service.EgovFileScrty;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.UserService;
import egovframework.cms.cmm.util.EgovUserDetailsHelper;
import egovframework.cms.system.authority.service.AuthorityService;
import egovframework.cms.system.authority.vo.AuthorityGroupVO;
import egovframework.cms.system.authority.vo.AuthorityLevelVO;
import egovframework.cms.system.organization.service.OrganizationDeptService;
import egovframework.cms.system.organization.vo.OrganizationDeptVO;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrganizationDeptController
{

  @Resource(name="organizationDeptService")
  private OrganizationDeptService organizationDeptService;

  @Resource(name="authorityService")
  private AuthorityService authorityService;

  @Resource(name="userService")
  private UserService userService;
  private String CONTENT_PATH = String.format("%s/egovframework/cms/organization/", new Object[] { CmsManager.getModulePath() });

  @ModelAttribute("searchOrganizationDeptVO")
  public OrganizationDeptVO searchOrganizationDept(@ModelAttribute OrganizationDeptVO searchVO, @RequestParam Map<String, Object> paramMap, HttpServletRequest request)
    throws Exception
  {
    return searchVO;
  }

  @RequestMapping(value = "/admin/organization/list.do")
  public String form(@ModelAttribute("searchOrganizationDeptVO") OrganizationDeptVO searchOrganizationDeptVO
		  , @ModelAttribute("writeOrganizationDept") OrganizationDeptVO writeOrganizationDeptVO
		  , @RequestParam(value="deptId", required=false, defaultValue="") String deptId
		  , @RequestParam(value="parntsDeptId", required=false, defaultValue="") String parntsDeptId
		  , HttpServletRequest request, ModelMap model)
    throws Exception
  {
    List<OrganizationDeptVO> allOrganizationDeptList = organizationDeptService.selectOrganizationDeptList(searchOrganizationDeptVO);
    if (allOrganizationDeptList.size() < 1) {
      writeOrganizationDeptVO.setDeptNm("조직도");
      writeOrganizationDeptVO.setUseAt("Y");
      organizationDeptService.insertOrganizationDept(writeOrganizationDeptVO);

      return "redirect:/organization/dept.do";
    }

    List<OrganizationDeptVO> parntsOrganizationDeptList = null;

    String command = "".equals(deptId) ? "insert" : "update";

    if ("insert".equals(command)){
      writeOrganizationDeptVO.setUseAt("Y");

      if (!"".equals(parntsDeptId))
      {
        OrganizationDeptVO parntsOrganizationDeptVO = new OrganizationDeptVO();
        parntsOrganizationDeptVO.setDeptId(parntsDeptId);
        parntsOrganizationDeptVO = organizationDeptService.selectOrganizationDept(parntsOrganizationDeptVO);

        if (parntsOrganizationDeptVO == null)
        {
          model.addAttribute("message", "organization.dept.noParntsData");
          return CmsManager.alert("/cms/organization/dept_result.jsp", model);
        }

        OrganizationDeptVO searchVO = new OrganizationDeptVO();
        searchVO.setDeptId(parntsDeptId);
        parntsOrganizationDeptList = organizationDeptService.selectParntsOrganizationDeptList(searchVO);
        parntsOrganizationDeptList.remove(0);
        parntsOrganizationDeptList.add(parntsOrganizationDeptVO);
      }
    }
    else{
      writeOrganizationDeptVO = organizationDeptService.selectOrganizationDept(searchOrganizationDeptVO);

      if (writeOrganizationDeptVO == null)
      {
        model.addAttribute("message", "organization.dept.nodata");
        return CmsManager.alert("/cms/organization/dept_result.jsp", model);
      }

      String loginGroupIds = EgovStringUtil.nullConvert(writeOrganizationDeptVO.getLoginGroupIds());
      writeOrganizationDeptVO.setLoginGroupIdArr(loginGroupIds.split(","));

      parntsOrganizationDeptList = organizationDeptService.selectParntsOrganizationDeptList(searchOrganizationDeptVO);
      parntsOrganizationDeptList.remove(0);
    }

    String rootDeptId = organizationDeptService.selectOrganizationRootDeptId(null);

    AuthorityGroupVO searchAuthorityGroupVO = new AuthorityGroupVO();
    List<AuthorityGroupVO> groupList = authorityService.selectAuthorityGroupList(searchAuthorityGroupVO);

    AuthorityLevelVO searchAuthorityLevelVO = new AuthorityLevelVO();
    List<AuthorityLevelVO> levelList = authorityService.selectAuthorityLevelList(searchAuthorityLevelVO);

    model.addAttribute("groupList", groupList);
    model.addAttribute("levelList", levelList);
    model.addAttribute("command", command);
    model.addAttribute("allOrganizationDeptList", allOrganizationDeptList);
    model.addAttribute("parntsOrganizationDeptList", parntsOrganizationDeptList);
    model.addAttribute("rootDeptId", rootDeptId);
    model.addAttribute("writeOrganizationDept", writeOrganizationDeptVO);

    model.addAttribute("contentPath", "organization/dept_write.jsp");

    return "/cms/layout/defaultLayout";
  }
  
  @RequestMapping(value = "/admin/organization/write.do")
  public String submit(@ModelAttribute("writeOrganizationDept") OrganizationDeptVO writeOrganizationDeptVO, @RequestParam(value="command", required=true, defaultValue="insert") String command, ModelMap model)
    throws Exception
  {
    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    String loginGroupIds = EgovStringUtil.join(writeOrganizationDeptVO.getLoginGroupIdArr(), ",");
    writeOrganizationDeptVO.setLoginGroupIds(loginGroupIds);

    String message = "";

    if ("insert".equals(command))
    {
      String loginId = EgovStringUtil.nullConvert(writeOrganizationDeptVO.getLoginId());
      if (!"".equals(loginId))
      {
        LoginVO tmpVO = new LoginVO();
        tmpVO.setId(loginId);
        tmpVO = userService.selectUserMaster(tmpVO);
        if (tmpVO != null) {
          model.addAttribute("message", "member.general.mberIdDuplctn");
          return CmsManager.alert(CONTENT_PATH + "dept_result.jsp", model);
        }

        String password = writeOrganizationDeptVO.getLoginPassword();
        writeOrganizationDeptVO.setLoginPassword(EgovFileScrty.encryptPassword(password, loginId));
      }

      message = "success.common.insert";
      writeOrganizationDeptVO.setRegistId(user.getId());
      organizationDeptService.insertOrganizationDept(writeOrganizationDeptVO);
    }
    else
    {
      OrganizationDeptVO deptVO = organizationDeptService.selectOrganizationDept(writeOrganizationDeptVO);
      if (deptVO != null)
      {
        String origLoginId = EgovStringUtil.nullConvert(deptVO.getLoginId());
        String loginId = EgovStringUtil.nullConvert(writeOrganizationDeptVO.getLoginId());

        if (("".equals(origLoginId)) && (!"".equals(loginId)))
        {
          LoginVO tmpVO = new LoginVO();
          tmpVO.setId(loginId);
          tmpVO = userService.selectUserMaster(tmpVO);
          if (tmpVO != null) {
            model.addAttribute("message", "member.general.mberIdDuplctn");
            return "/cms/organization/dept_result";
          }

          String password = writeOrganizationDeptVO.getLoginPassword();
          writeOrganizationDeptVO.setLoginPassword(EgovFileScrty.encryptPassword(password, loginId));
        }
        else if (!"".equals(EgovStringUtil.nullConvert(writeOrganizationDeptVO.getLoginPassword())))
        {
          String password = writeOrganizationDeptVO.getLoginPassword();
          writeOrganizationDeptVO.setLoginPassword(EgovFileScrty.encryptPassword(password, loginId));
        }
        else
        {
          writeOrganizationDeptVO.setLoginPassword(null);
        }
      }

      message = "success.common.update";
      writeOrganizationDeptVO.setUpdateId(user.getId());
      organizationDeptService.updateOrganizationDept(writeOrganizationDeptVO);
    }

    model.addAttribute("message", message);
    return "/cms/organization/dept_result";
  }

  @RequestMapping(value = "/admin/organization/delete.do")
  public String delete(@ModelAttribute("searchOrganizationDeptVO") OrganizationDeptVO searchOrganizationDeptVO, @RequestParam(value="deptId", required=true) String deptId, ModelMap model)
    throws Exception
  {
    OrganizationDeptVO vo = organizationDeptService.selectOrganizationDept(searchOrganizationDeptVO);

    if (vo == null)
    {
      model.addAttribute("message", "organization.dept.nodata");
      return "/cms/organization/dept_result";
    }

    organizationDeptService.deleteOrganizationDept(vo);

    model.addAttribute("message", "success.common.delete");
    return "/cms/organization/dept_result";
  }

  @RequestMapping(params={"act=move"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String move(@ModelAttribute("searchOrganizationDeptVO") OrganizationDeptVO searchOrganizationDeptVO, ModelMap model)
    throws Exception
  {
    String rootDeptId = organizationDeptService.selectOrganizationRootDeptId(searchOrganizationDeptVO);

    List<OrganizationDeptVO> list = organizationDeptService.selectOrganizationDeptList(searchOrganizationDeptVO);

    model.addAttribute("rootDeptId", rootDeptId);
    model.addAttribute("organizationDeptList", list);

    model.addAttribute("contentFile", CONTENT_PATH + "dept_move.jsp");

    return "egovframework/wms/common/admin_view";
  }

  @RequestMapping(params={"act=move"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String moveSubmit(@ModelAttribute("searchOrganizationDeptVO") OrganizationDeptVO searchOrganizationDeptVO
		  , @RequestParam(value="jsonData", required=true) String jsonData, ModelMap model)
    throws Exception
  {
    organizationDeptService.updateOrganizationDeptMove(jsonData);

    model.addAttribute("message", "success.common.move");
    return "/cms/organization/dept_result";
  }
}