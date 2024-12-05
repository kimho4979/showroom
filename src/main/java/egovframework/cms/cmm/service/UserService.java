package egovframework.cms.cmm.service;

import egovframework.cms.cmm.LoginVO;
import javax.servlet.http.HttpServletRequest;

public abstract interface UserService
{
  public abstract LoginVO actionLogin(LoginVO paramLoginVO, HttpServletRequest paramHttpServletRequest)
    throws Exception;

  public abstract LoginVO selectUserMaster(LoginVO paramLoginVO)
    throws Exception;

  public abstract void updatePassword(LoginVO paramLoginVO)
    throws Exception;

  public abstract void updateLoginInfo(LoginVO paramLoginVO)
    throws Exception;
}
