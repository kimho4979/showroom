package egovframework.cms.cmm.service;

import egovframework.cms.cmm.ComDefaultCodeVO;
import java.util.List;
import java.util.Map;

public abstract interface EgovCmmUseService
{
  public abstract List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO paramComDefaultCodeVO)
    throws Exception;

  public abstract Map<String, List<CmmnDetailCode>> selectCmmCodeDetails(List<?> paramList)
    throws Exception;

  public abstract List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO paramComDefaultCodeVO)
    throws Exception;

  public abstract List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO paramComDefaultCodeVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.EgovCmmUseService
 * JD-Core Version:    0.6.2
 */