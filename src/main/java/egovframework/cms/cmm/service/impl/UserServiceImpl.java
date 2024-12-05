package egovframework.cms.cmm.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.cms.cmm.LoginVO;
import egovframework.cms.cmm.service.EgovClntInfo;
import egovframework.cms.cmm.service.EgovFileScrty;
import egovframework.cms.cmm.service.EgovStringUtil;
import egovframework.cms.cmm.service.UserService;
//import egovframework.cms.log.service.LogService;
//import egovframework.cms.log.vo.LoginLogVO;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl
  implements UserService
{

  @Resource(name="userMapper")
  private UserMapper userMapper;

  /*
  @Resource(name="logService")
  private LogService logService;
*/
  public LoginVO actionLogin(LoginVO vo, HttpServletRequest request)
    throws Exception
  {
    String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
    if (("BPM".equals(vo.getUserSe())) || (request.getParameter("cmsUrl") != null))
      vo.setPassword(vo.getPassword());
    else {
      vo.setPassword(enpassword);
    }

    vo.setStatus("P");

    LoginVO loginVO = userMapper.actionLogin(vo);

    String successAt = "Y";
    String reason = "";
    if ((loginVO != null) && (!loginVO.getId().equals("")) && (!loginVO.getPassword().equals("")))
      updateLoginInfo(loginVO);
    else {
      loginVO = new LoginVO();
    }
/*
    LoginLogVO log = new LoginLogVO();
    log.setSuccessAt(successAt);
    log.setUserId(vo.getId());
    log.setLoginIp(request.getRemoteAddr());
    log.setReason(reason);
    log.setUseragent(EgovClntInfo.getClntWebKind(request));
    log.setLoginReferer(EgovStringUtil.nullConvert(request.getParameter("returnUrl")));
*/
    //logService.insertLoginLog(log);
    return loginVO;
  }

  public LoginVO selectUserMaster(LoginVO vo)
    throws Exception
  {
    return userMapper.selectUserMaster(vo);
  }

  public void updatePassword(LoginVO vo)
    throws Exception
  {
    String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());

    LoginVO pwVO = new LoginVO();
    pwVO.setId(vo.getId());
    pwVO.setPassword(enpassword);
    pwVO.setUserSe(vo.getUserSe());
    userMapper.updatePassword(pwVO);
    userMapper.updateChangePasswordDatetime(pwVO);
  }

  public void updateLoginInfo(LoginVO vo)
    throws Exception
  {
    userMapper.updateLoginInfo(vo);
  }
}