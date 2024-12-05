package egovframework.cms.system.organization.service;

import egovframework.cms.system.organization.vo.OrganizationStaffVO;
import java.util.List;
import java.util.Map;

public interface OrganizationStaffService
{
  public Map<String, Object> selectOrganizationStaffList(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public List<OrganizationStaffVO> selectOrganizationStaffListAll(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public OrganizationStaffVO selectOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public void insertOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public void updateOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public void updateOrganizationStaffEtcByStaffIds(String[] paramArrayOfString, OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public void deleteOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.organization.service.OrganizationStaffService
 * JD-Core Version:    0.6.2
 */