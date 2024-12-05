package egovframework.cms.system.organization.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.cms.cmm.CmsManager;
import egovframework.cms.cmm.LoginVO;
import egovframework.cms.cmm.service.EgovFileMngUtil;
import egovframework.cms.cmm.service.EgovFileScrty;
import egovframework.cms.cmm.service.EgovFileTool;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.FileVO;
import egovframework.cms.cmm.service.Globals;
import egovframework.cms.cmm.service.UserService;
import egovframework.cms.cmm.util.EgovUserDetailsHelper;
import egovframework.cms.cmm.util.MapQuery;
import egovframework.cms.system.authority.service.AuthorityService;
import egovframework.cms.system.authority.vo.AuthorityGroupVO;
import egovframework.cms.system.authority.vo.AuthorityLevelVO;
import egovframework.cms.system.organization.service.OrganizationDeptService;
import egovframework.cms.system.organization.service.OrganizationStaffService;
import egovframework.cms.system.organization.vo.OrganizationDeptVO;
import egovframework.cms.system.organization.vo.OrganizationStaffVO;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class OrganizationStaffController
{

  @Resource(name="propertiesService")
  protected EgovPropertyService propertyService;

  @Resource(name="organizationStaffService")
  private OrganizationStaffService organizationStaffService;

  @Resource(name="userService")
  private UserService userService;

  @Resource(name="organizationDeptService")
  private OrganizationDeptService organizationDeptService;

  @Resource(name="authorityService")
  private AuthorityService authorityService;

  @Resource(name="EgovFileMngUtil")
  private EgovFileMngUtil egovFileMngUtil;
  OrganizationStaffVO searchOrganizationStaffVO = new OrganizationStaffVO();

  private String CONTENT_PATH = String.format("%s/egovframework/wms/system/organization/", new Object[] { CmsManager.getModulePath() });

  @ModelAttribute("searchOrganizationStaffVO")
  public OrganizationStaffVO searchOrganizationStaff(@ModelAttribute OrganizationStaffVO searchVO, @RequestParam Map<String, Object> paramMap, HttpServletRequest request)
    throws Exception
  {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("searchDeptId", searchVO.getSearchDeptId());
    param.put("searchCondition", searchVO.getSearchCondition());
    param.put("searchKeyword", searchVO.getSearchKeyword());
    param.put("pageIndex", Integer.valueOf(searchVO.getPageIndex()));

    searchVO.setQueryString(MapQuery.urlEncodeUTF8(param));

    searchOrganizationStaffVO = searchVO;

    return searchOrganizationStaffVO;
  }

  @RequestMapping({"/organization/staff.do"})
  public String list(@RequestParam Map<String, Object> paramMap, ModelMap model)
    throws Exception
  {
    OrganizationDeptVO deptVO = new OrganizationDeptVO();

    if (!"".equals(EgovStringUtil.nullConvert(searchOrganizationStaffVO.getSearchDeptId())))
    {
      deptVO.setDeptId(searchOrganizationStaffVO.getSearchDeptId());
      deptVO = organizationDeptService.selectOrganizationDept(deptVO);

      if (deptVO != null)
      {
        searchOrganizationStaffVO.setSearchDeptLft(deptVO.getLft());
        searchOrganizationStaffVO.setSearchDeptRgt(deptVO.getRgt());
      }
    }

    searchOrganizationStaffVO.setPageUnit(propertyService.getInt("pageUnit"));
    searchOrganizationStaffVO.setPageSize(propertyService.getInt("pageSize"));

    PaginationInfo paginationInfo = new PaginationInfo();

    paginationInfo.setCurrentPageNo(searchOrganizationStaffVO.getPageIndex());
    paginationInfo.setRecordCountPerPage(searchOrganizationStaffVO.getPageUnit());
    paginationInfo.setPageSize(searchOrganizationStaffVO.getPageSize());

    searchOrganizationStaffVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    searchOrganizationStaffVO.setLastIndex(paginationInfo.getLastRecordIndex());
    searchOrganizationStaffVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    Map param = new HashMap();
    param.putAll(paramMap);
    param.put("pageIndex", null);
    String paginationQueryString = MapQuery.urlEncodeUTF8(param);

    Map map = organizationStaffService.selectOrganizationStaffList(searchOrganizationStaffVO);
    int totCnt = Integer.parseInt((String)map.get("resultCnt"));

    paginationInfo.setTotalRecordCount(totCnt);

    String rootDeptId = organizationDeptService.selectOrganizationRootDeptId(null);
    List allDeptList = organizationDeptService.selectOrganizationDeptList(null);

    model.addAttribute("deptVO", deptVO);
    model.addAttribute("rootDeptId", rootDeptId);
    model.addAttribute("allDeptList", allDeptList);

    model.addAttribute("paginationInfo", paginationInfo);
    model.addAttribute("paginationQueryString", paginationQueryString);
    model.addAttribute("resultList", map.get("resultList"));
    model.addAttribute("resultCnt", map.get("resultCnt"));

    model.addAttribute("contentFile", CONTENT_PATH + "staff_list.jsp");

    return "egovframework/wms/common/admin_view";
  }

  @RequestMapping(params={"act=write"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String form(@ModelAttribute("writeOrganizationStaff") OrganizationStaffVO writeOrganizationStaffVO, @RequestParam(value="staffId", required=false, defaultValue="") String staffId, ModelMap model)
    throws Exception
  {
    String command = "".equals(staffId) ? "insert" : "update";

    if ("insert".equals(command))
    {
      writeOrganizationStaffVO.setDeptId(searchOrganizationStaffVO.getSearchDeptId());
    }
    else
    {
      writeOrganizationStaffVO = organizationStaffService.selectOrganizationStaff(searchOrganizationStaffVO);

      if (writeOrganizationStaffVO == null) {
        model.addAttribute("message", "organization.staff.nodata");
        return CmsManager.alert(CONTENT_PATH + "staff_result.jsp", model);
      }

      String loginGroupIds = EgovStringUtil.nullConvert(writeOrganizationStaffVO.getLoginGroupIds());
      writeOrganizationStaffVO.setLoginGroupIdArr(loginGroupIds.split(","));
    }

    List treeDeptList = getTreeDeptList();

    AuthorityGroupVO searchAuthorityGroupVO = new AuthorityGroupVO();
    List groupList = authorityService.selectAuthorityGroupList(searchAuthorityGroupVO);

    AuthorityLevelVO searchAuthorityLevelVO = new AuthorityLevelVO();
    List levelList = authorityService.selectAuthorityLevelList(searchAuthorityLevelVO);

    model.addAttribute("groupList", groupList);
    model.addAttribute("levelList", levelList);
    model.addAttribute("command", command);
    model.addAttribute("treeDeptList", treeDeptList);
    model.addAttribute("writeOrganizationStaff", writeOrganizationStaffVO);

    model.addAttribute("contentFile", CONTENT_PATH + "staff_write.jsp");

    return "egovframework/wms/common/admin_view";
  }

  @RequestMapping(params={"act=write"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String submit(@ModelAttribute("writeOrganizationStaff") OrganizationStaffVO writeOrganizationStaffVO, @RequestParam(value="command", required=true, defaultValue="insert") String command, MultipartHttpServletRequest multiRequest, ModelMap model)
    throws Exception
  {
    LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    String loginGroupIds = EgovStringUtil.join(writeOrganizationStaffVO.getLoginGroupIdArr(), ",");
    writeOrganizationStaffVO.setLoginGroupIds(loginGroupIds);

    if (EgovStringUtil.nullConvert(writeOrganizationStaffVO.getDeletePhotoFile()).equals("Y")) {
      writeOrganizationStaffVO.setPhotoFile("");
    }

    FileVO uploadFileVO = uploadPhotoFile(multiRequest);
    if (uploadFileVO != null) {
      writeOrganizationStaffVO.setPhotoFile(uploadFileVO.getStreFileNm());
    }

    String message = "";

    if ("insert".equals(command))
    {
      String loginId = EgovStringUtil.nullConvert(writeOrganizationStaffVO.getLoginId());
      if (!"".equals(loginId)) {
        LoginVO tmpVO = new LoginVO();
        tmpVO.setId(loginId);
        tmpVO = userService.selectUserMaster(tmpVO);
        if (tmpVO != null) {
          model.addAttribute("message", "member.general.mberIdDuplctn");
          return CmsManager.alert(CONTENT_PATH + "staff_result.jsp", model);
        }

        String password = writeOrganizationStaffVO.getLoginPassword();
        writeOrganizationStaffVO.setLoginPassword(EgovFileScrty.encryptPassword(password, loginId));
      }

      message = "success.common.insert";
      writeOrganizationStaffVO.setRegistId(user.getId());
      organizationStaffService.insertOrganizationStaff(writeOrganizationStaffVO);
    }
    else
    {
      OrganizationStaffVO staffVO = organizationStaffService.selectOrganizationStaff(searchOrganizationStaffVO);
      if (staffVO == null) {
        model.addAttribute("message", "organization.staff.nodata");
        return CmsManager.alert(CONTENT_PATH + "staff_result.jsp", model);
      }

      String origLoginId = EgovStringUtil.nullConvert(staffVO.getLoginId());
      String loginId = EgovStringUtil.nullConvert(writeOrganizationStaffVO.getLoginId());

      if (("".equals(origLoginId)) && (!"".equals(loginId))) {
        LoginVO tmpVO = new LoginVO();
        tmpVO.setId(loginId);
        tmpVO = userService.selectUserMaster(tmpVO);
        if (tmpVO != null) {
          model.addAttribute("message", "member.general.mberIdDuplctn");
          return CmsManager.alert(CONTENT_PATH + "staff_result.jsp", model);
        }

        String password = writeOrganizationStaffVO.getLoginPassword();
        writeOrganizationStaffVO.setLoginPassword(EgovFileScrty.encryptPassword(password, loginId));
      }
      else if (!"".equals(EgovStringUtil.nullConvert(writeOrganizationStaffVO.getLoginPassword())))
      {
        String password = writeOrganizationStaffVO.getLoginPassword();
        writeOrganizationStaffVO.setLoginPassword(EgovFileScrty.encryptPassword(password, loginId));
      }
      else
      {
        writeOrganizationStaffVO.setLoginPassword(null);
      }

      message = "success.common.update";
      writeOrganizationStaffVO.setUpdateId(user.getId());
      organizationStaffService.updateOrganizationStaff(writeOrganizationStaffVO);

      if (EgovStringUtil.nullConvert(writeOrganizationStaffVO.getDeletePhotoFile()).equals("Y")) {
        deletePhotoFile(staffVO.getPhotoFile());
      }

    }

    model.addAttribute("message", message);

    return CmsManager.alert(CONTENT_PATH + "staff_result.jsp", model);
  }

  @RequestMapping(params={"act=delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String delete(@RequestParam(value="staffId", required=true) String staffId, ModelMap model)
    throws Exception
  {
    OrganizationStaffVO vo = organizationStaffService.selectOrganizationStaff(searchOrganizationStaffVO);

    if (vo == null)
    {
      model.addAttribute("message", "organization.staff.nodata");
      return CmsManager.alert(CONTENT_PATH + "staff_result.jsp", model);
    }

    organizationStaffService.deleteOrganizationStaff(vo);

    model.addAttribute("message", "success.common.delete");

    return CmsManager.alert(CONTENT_PATH + "staff_result.jsp", model);
  }

  private List<OrganizationDeptVO> getTreeDeptList() throws Exception
  {
    List list = new ArrayList();

    List deptList = organizationDeptService.selectOrganizationDeptList(null);

    for (int i = 0; i < deptList.size(); i++)
    {
      if (!"".equals(EgovStringUtil.nullConvert(((OrganizationDeptVO)deptList.get(i)).getParntsDeptId())))
      {
        String deptNm = ((OrganizationDeptVO)deptList.get(i)).getDeptNm();

        if (((OrganizationDeptVO)deptList.get(i)).getLvl() > 2)
        {
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

  private FileVO uploadPhotoFile(MultipartHttpServletRequest multiRequest) throws Exception
  {
    String uploadPath = Globals.DISTRIBUTE_UPLOAD_PATH + "organization/staff/";

    String atchFileId = "-1";
    List atchFile = multiRequest.getFiles("atchPhotoFile");

    List atchFileList = null;
    try {
      atchFileList = egovFileMngUtil.parseFileInf(atchFile, "", 0, atchFileId, uploadPath, "gif,jpg,jpeg,png,bmp", 20);
    } catch (Exception e) {
      System.out.println(e);
    }

    return (atchFileList != null) && (atchFileList.size() > 0) ? (FileVO)atchFileList.get(0) : null;
  }

  private void deletePhotoFile(String fileNm) throws Exception
  {
    if (!"".equals(EgovStringUtil.nullConvert(fileNm)))
      EgovFileTool.deleteFile(Globals.DISTRIBUTE_UPLOAD_PATH + "organization/staff/" + fileNm);
  }
}