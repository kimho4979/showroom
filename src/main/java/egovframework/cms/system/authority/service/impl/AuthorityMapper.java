package egovframework.cms.system.authority.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.cms.system.authority.vo.AuthorityGroupVO;
import egovframework.cms.system.authority.vo.AuthorityLevelVO;
import java.util.List;

@Mapper("authorityMapper")
public abstract interface AuthorityMapper
{
  public abstract List<AuthorityGroupVO> selectAuthorityGroupList(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public abstract AuthorityGroupVO selectAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public abstract void insertAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public abstract void updateAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public abstract void deleteAuthorityGroup(AuthorityGroupVO paramAuthorityGroupVO)
    throws Exception;

  public abstract List<AuthorityLevelVO> selectAuthorityLevelList(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public abstract AuthorityLevelVO selectAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public abstract void insertAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public abstract void updateAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;

  public abstract void deleteAuthorityLevel(AuthorityLevelVO paramAuthorityLevelVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.system.authority.service.impl.AuthorityMapper
 * JD-Core Version:    0.6.2
 */