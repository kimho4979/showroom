package egovframework.cms.system.organization.service;

import egovframework.cms.system.organization.vo.OrganizationDeptVO;
import java.util.List;

public abstract interface OrganizationDeptService
{
  public abstract List<OrganizationDeptVO> selectOrganizationDeptList(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract List<OrganizationDeptVO> selectParntsOrganizationDeptList(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract List<OrganizationDeptVO> selectChldrnOrganizationDeptList(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract OrganizationDeptVO selectOrganizationDept(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract String selectOrganizationRootDeptId(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract void insertOrganizationDept(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract void updateOrganizationDept(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract void deleteOrganizationDept(OrganizationDeptVO paramOrganizationDeptVO)
    throws Exception;

  public abstract void updateOrganizationDeptMove(String paramString)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.organization.service.OrganizationDeptService
 * JD-Core Version:    0.6.2
 */