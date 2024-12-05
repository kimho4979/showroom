package egovframework.cms.system.organization.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.cms.system.organization.vo.OrganizationStaffVO;
import java.util.List;

@Mapper("organizationStaffMapper")
public abstract interface OrganizationStaffMapper
{
  public abstract Integer selectOrganizationStaffListCnt(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract List<OrganizationStaffVO> selectOrganizationStaffList(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract List<OrganizationStaffVO> selectOrganizationStaffListAll(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract OrganizationStaffVO selectOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract void insertOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract void updateOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract void updateOrganizationStaffEtcByStaffIds(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract void deleteOrganizationStaff(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;

  public abstract void deleteOrganizationStaffByDeptId(OrganizationStaffVO paramOrganizationStaffVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.organization.service.impl.OrganizationStaffMapper
 * JD-Core Version:    0.6.2
 */