package egovframework.cms.cmm.service;

import egovframework.cms.cmm.SndngMailVO;

public abstract interface EgovSndngMailService
{
  public abstract boolean sndngMail(SndngMailVO paramSndngMailVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovSndngMailService
 * JD-Core Version:    0.6.2
 */