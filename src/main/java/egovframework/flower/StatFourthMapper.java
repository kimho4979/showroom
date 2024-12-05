package egovframework.flower;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Mapper("statFourthMapper")
public interface StatFourthMapper {

	List<EgovMap> sangJangCntList(Map<String, Object> paramMap) throws Exception;
}
