package egovframework.front.content.service.impl;

import java.util.Map;

import egovframework.front.content.service.FrontContentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("frontContentMapper")
public interface FrontContentMapper {

	FrontContentVO getFrontContent(Map<String, Object> parmaMap);
}
