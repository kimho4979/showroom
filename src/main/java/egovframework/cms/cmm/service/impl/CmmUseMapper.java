package egovframework.cms.cmm.service.impl;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.cms.cmm.ComDefaultCodeVO;
import egovframework.cms.cmm.service.CmmnDetailCode;
import java.util.List;

@Mapper("cmmUseMapper")
public abstract interface CmmUseMapper
{
  public abstract List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO paramComDefaultCodeVO)
    throws Exception;

  public abstract List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO paramComDefaultCodeVO)
    throws Exception;

  public abstract List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO paramComDefaultCodeVO)
    throws Exception;
}

/* Location:           C:\woorim\화훼온라인매매시스템\소스\평창\WEB-INF\classes\
 * Qualified Name:     egovframework.cms.cmm.service.impl.CmmUseMapper
 * JD-Core Version:    0.6.2
 */