package egovframework.cms.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
/*
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/

public class CommonUtil {
	
	/**
	 * 인증코드, 번호 생성 유틸
	 * @param type
	 * @param cnt
	 * @return
	 * type P -> 특수기호포함
	 * type A -> 대문자로만
	 * type S -> 소문자로만
	 * type I -> 숫자형으로
	 * type C -> 소문자, 숫자형
	 */
	public static String randomValue(String type, int cnt) {

		StringBuffer strPwd = new StringBuffer();
		char str[] = new char[1];
		// 특수기호 포함
		if (type.equals("P")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 94) + 33);
				strPwd.append(str);
			}
			// 대문자로만
		} else if (type.equals("A")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 26) + 65);
				strPwd.append(str);
			}
			// 소문자로만
		} else if (type.equals("S")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 26) + 97);
				strPwd.append(str);
			}
			// 숫자형으로
		} else if (type.equals("I")) {
			int strs[] = new int[1];
			for (int i = 0; i < cnt; i++) {
				strs[0] = (int) (Math.random() * 9);
				strPwd.append(strs[0]);
			}
			// 소문자, 숫자형
		} else if (type.equals("C")) {
			Random rnd = new Random();
			for (int i = 0; i < cnt; i++) {
				if (rnd.nextBoolean()) {
					strPwd.append((char) ((int) (rnd.nextInt(26)) + 97));
				} else {
					strPwd.append((rnd.nextInt(10)));
				}
			}
		}
		return strPwd.toString();
	}

	/**
	 * 게시판 사용에 필요한 기본 파라미터 삽입(dataTables js 사용형태)
	 * @param request
	 * @param pRequestParamMap
	 * pRequestParamMap 내부로 파라미터 넣어줌
	 * @param resultMap 
	 */
	public static void addParamBoard(HttpServletRequest request, Map<String, Object> pRequestParamMap, Map<String, Object> resultMap) {
		int length = Integer.parseInt((String) pRequestParamMap.get("length"));
		int start = Integer.parseInt((String) pRequestParamMap.get("start"));
		int draw = Integer.parseInt((String) pRequestParamMap.get("draw"));
		//int pageNum = (start/length)+1;	// 페이지 번호 필요할경우 주석해제
		
		String hpId = getHpId(request, pRequestParamMap);
		Map<String , String> loginVo = (Map<String, String>) request.getSession().getAttribute("loginVO");
		String userType = loginVo == null?"":loginVo.get("userType");
		
		pRequestParamMap.put("userType", userType);
		pRequestParamMap.put("hpId", hpId);
		pRequestParamMap.put("firstIndex", start);
		pRequestParamMap.put("recordCountPerPage", length);
		resultMap.put("draw", draw);
	}

	/**
	 * 세션에 현재 접속중인 hpId 를 조회한다.
	 * 만약 외부링크에의한 접속일경우 hpId 를 get 으로 받기 때문에 파라미터에서 조회한다.
	 * 데이터가 없을경우 return null
	 * @param request
	 * @param pRequestParamMap
	 * @return
	 */
	public static String getHpId(HttpServletRequest request, Map<String, Object> pRequestParamMap) {
		String hpId = null;
		hpId = String.valueOf(request.getSession().getAttribute("hpId"));
		if(hpId == null || "".equals(hpId) || "null".equals(hpId)){
			hpId = String.valueOf(pRequestParamMap.get("hpId"));
		}
		
		request.getSession().setAttribute("hpId", hpId);
		pRequestParamMap.put("hpId", hpId);
		
		return hpId;
	}
	
	/*
	public static List getVoFromMultiJson(String pJsonString, String pNodeName, Class pClass) throws ParseException, JsonParseException, JsonMappingException, IOException, ParseException

    {
        //----------------------------------------------------------------
		// JSON STRING -> JSON OBJECT로 변환  ObjectMapper.configure( org.codehaus.jackson.map.SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		//----------------------------------------------------------------
        JSONObject jSONObject = new JSONObject();
        // jSONObject = (JSONObject) new JSONParser().parse(pJsonString.toString());
        jSONObject = (JSONObject) new JSONParser().parse(pJsonString);

        //----------------------------------------------------------------
        // data node 구함
        //----------------------------------------------------------------
        JSONArray lJsonArray = ((JSONArray)jSONObject.get(pNodeName));
        ArrayList lRtn = new ArrayList();


        ObjectMapper lObjectMapper = new ObjectMapper();
        lObjectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for ( int inx = 0 ; inx < lJsonArray.size(); inx++)
        {
          Object lClass = lObjectMapper.readValue(lJsonArray.get(inx).toString(), pClass);
          lRtn.add(lClass);
        }

        return lRtn;
    }*/


}
