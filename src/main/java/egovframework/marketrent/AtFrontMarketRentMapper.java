package egovframework.marketrent;

import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("atFrontMarketRentMapper")
public interface AtFrontMarketRentMapper {

	EgovMap selectMarketInfo(Map<String, Object> paramMap) throws Exception;
}
