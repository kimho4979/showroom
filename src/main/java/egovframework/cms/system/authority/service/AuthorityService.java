package egovframework.cms.system.authority.service;

import egovframework.cms.system.authority.vo.AuthorityGroupVO;
import egovframework.cms.system.authority.vo.AuthorityLevelVO;
import java.util.List;

public interface AuthorityService
{
  public List<AuthorityGroupVO> selectAuthorityGroupList(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public AuthorityGroupVO selectAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public void insertAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public void updateAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public void deleteAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public List<AuthorityLevelVO> selectAuthorityLevelList(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public AuthorityLevelVO selectAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public void insertAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public void updateAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public void deleteAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.authority.service.AuthorityService
 * JD-Core Version:    0.6.2
 */