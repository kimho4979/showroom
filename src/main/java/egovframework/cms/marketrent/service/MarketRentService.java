/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.cms.marketrent.service;

import java.util.List;
import java.util.Map;

import egovframework.cms.marketrent.vo.MarketFileVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : EgovSampleService.java
 * @Description : EgovSampleService Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public interface MarketRentService {


	int delete(Map<String, Object> paramMap) throws Exception;

	int save(Map<String, Object> paramMap) throws Exception;

	int insert(Map<String, Object> paramMap) throws Exception;

	int update(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getMarketRentList(Map<String, Object> paramMap) throws Exception;

	int getMarketRentListCnt(Map<String, Object> paramMap) throws Exception;

	EgovMap getMarketRent(Map<String, Object> paramMap) throws Exception;

	EgovMap getMarketRentAddInfo(Map<String, Object> paramMap) throws Exception;

	String getMarketGroupType(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getMarketGroupTypeList(Map<String, Object> paramMap) throws Exception;

	EgovMap selectMarketInfo(Map<String, Object> paramMap) throws Exception;

	void marketFileProc(Map<String, Object> fileMap) throws Exception;

	EgovMap getFileOne(Map<String, Object> fileMap) throws Exception;

	void deleteFileOne(Map<String, Object> fileMap) throws Exception;
	
	List<MarketFileVO> selectMarketFileList(MarketFileVO marketFileVO) throws Exception;

	

}
