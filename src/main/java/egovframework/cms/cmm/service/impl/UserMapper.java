package egovframework.cms.cmm.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.cms.cmm.LoginVO;

@Mapper("userMapper")
public abstract interface UserMapper
{
  public abstract LoginVO actionLogin(LoginVO paramLoginVO)
    throws Exception;

  public abstract LoginVO selectUserMaster(LoginVO paramLoginVO)
    throws Exception;

  public abstract void updatePassword(LoginVO paramLoginVO)
    throws Exception;

  public abstract void updateLoginInfo(LoginVO paramLoginVO)
    throws Exception;

  public abstract void updateChangePasswordDatetime(LoginVO paramLoginVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.impl.UserMapper
 * JD-Core Version:    0.6.2
 */