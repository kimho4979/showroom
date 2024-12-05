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
package egovframework.front.login.service;

import java.util.List;
import java.util.Map;

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
public interface FrontLoginService {

	EgovMap getAtMember(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getAtMembers() throws Exception;

	List<EgovMap> getFloMember(Map<String, Object> paramMap) throws Exception;

	EgovMap getSnsCheckVO(Map<String, Object> snsLoginVO) throws Exception;

	void insertAtSns(Map<String, Object> snsLoginVO) throws Exception;

	EgovMap getAtIdLogin(EgovMap atSnsLoginVO) throws Exception;

	EgovMap getNaverInfo(EgovMap atLoginVO) throws Exception;

	EgovMap getKakaoInfo(EgovMap atLoginVO) throws Exception;

	EgovMap getFacebookInfo(EgovMap atLoginVO) throws Exception;

	void deleteSns(Map<String, Object> paramMap) throws Exception;

	EgovMap getFloDef(Map<String, Object> paramMap) throws Exception;
	
	int insertFloDef(Map<String, Object> paramMap) throws Exception;
	
	int updateFloDef(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> smsList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> mmsList(Map<String, Object> paramMap) throws Exception;

	EgovMap getSmsApp(Map<String, Object> paramMap) throws Exception;

	void insertSmsApp(Map<String, Object> paramMap) throws Exception;

	void deleteSmsApp(Map<String, Object> paramMap) throws Exception;
	

}
