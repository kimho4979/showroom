/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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
package egovframework.flower;

import java.util.List;
import java.util.Map;

import egovframework.cms.login.service.LoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * sample에 관한 데이터처리 매퍼 클래스
 *
 * @author  표준프레임워크센터
 * @since 2014.01.24
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2014.01.24        표준프레임워크센터          최초 생성
 *
 * </pre>
 */
@Mapper("fixCAucMapper")
public interface FixCAucMapper {

	List<EgovMap> getMokList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getPumList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getPrePrice(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getLevelList(Map<String, Object> paramMap) throws Exception;

	EgovMap getChulCodeToChulInfo(String chulCode) throws Exception;

	List<EgovMap> getRecentList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getAdmJRecentList(Map<String, Object> paramMap) throws Exception;

	EgovMap getJCodeToJInfo(String jCode) throws Exception;

	List<EgovMap> getChulList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getJRecentList(Map<String, Object> paramMap) throws Exception;
	
	List<EgovMap> getCRecentList(Map<String, Object> paramMap) throws Exception;

	List<EgovMap> getJList(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getMokInfo(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getPumInfo(Map<String, Object> paramMap) throws Exception;
	
	EgovMap getLevelInfo(Map<String, Object> paramMap) throws Exception;

	int messageBiteLength(Map<String, Object> paramMap) throws Exception;

	int sendSms(Map<String, Object> paramMap) throws Exception;

	int sendMms(Map<String, Object> paramMap) throws Exception;
	
	
	
}
