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
package egovframework.cms.cmm;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.cms.login.service.LoginService;
import egovframework.cms.login.service.LoginVO;

public class AdminAuthService implements AuthenticationProvider {
	
	/** EgovSampleService */
	@Resource(name = "loginService")
	private LoginService loginService;
	

	@Resource(name="passwordEncoder")
	 private ShaPasswordEncoder encoder;
	
	private static final Logger logger 
		= LoggerFactory.getLogger(AdminAuthService.class);
	
	/**
	 * 로그인 사용자 인증 서비스 구현 메서드
	 * 성공하면 default-target-url 에 설정된 화면으로 이동한다.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) 
			throws AuthenticationException {
		
		logger.info("AuthenticationProvider > authenticate 호출");
		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		
		String remoteAddr = "";
		if(request.getRemoteAddr() != null && request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")) {
			remoteAddr = "127.0.0.1";
		} else {
			remoteAddr = request.getRemoteAddr();
		}
		
		// 로그인 화면에서 입력한 정보를 가져옵니다.
		String user_id = (String)authentication.getPrincipal();
		String user_pw = (String)authentication.getCredentials();
		
		// OTP check
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        String otp = details.getOtp();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userId", user_id);
		paramMap.put("password", encoder.encodePassword(user_pw,null));
		paramMap.put("accessIp", remoteAddr);
		paramMap.put("url", request.getRequestURL().toString());
		
		// Generate a random unique UUID
		UUID uuid = UUID.randomUUID();

		// Convert the UUID to a string
		String uniqueId = uuid.toString();

		// Set the uniqueId in the paramMap
		paramMap.put("uniqueId", uniqueId);
		
		LoginVO resultVO = new LoginVO();
		try {
			loginService.registAccessInfo(paramMap);
			resultVO = loginService.getAdminInfo(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 정보가 없으면 나간다.
		if ( user_id == null || user_id == "" ) {
			throw new BadCredentialsException("어드민 로그인 아이디가 없습니다." );
		}
		
		if ( user_pw == null || user_pw == ""  ) {
			throw new BadCredentialsException("어드민 로그인 암호가 없습니다." );
		}
		
		if(resultVO == null){
			throw new BadCredentialsException("로그인정보가 틀립니다." );
		}
		
		String authKey = resultVO.getAuthKey();
		
		try{
			
			long codeCheck = Integer.parseInt(otp);
			String encodedKey = authKey;
			long l = new Date().getTime();
			long ll = l / 30000;

			boolean check_code = false;
			check_code = check_code(encodedKey, codeCheck, ll);
			
			logger.info("코드 비교 결과 : check_code:{} ",check_code);

			if (!check_code) {
				throw new BadCredentialsException("코드가 일치하지 않습니다." );
			}

		} catch (Exception e) {
			throw new BadCredentialsException("코드가 일치하지 않습니다." );
		}
		
		// 
		// dao를 통해서 로그인 사용자의 정보를 확인한다.
		// 안 맞는 경우에는 exception 을 던진다.
		// 디비사용은 안하니까 .우선은 간단하게 테스트 로직으로 한다,
		
		
		// 로그인한 사용자의 부가정보를 담아준다.
		
		resultVO.setUserType("ROLE_ADMIN");
		 
		// 로그인 사용자의 권한
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		if ( resultVO.getId() != "") {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 어드민 권한 , ADMIN 하면 컨트롤어에서 권한 없다고 한다.
		} else {
			roles.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY")); // 일반 사용자이다 .
		}
		
        // 반환할 값을 만든다.
        UsernamePasswordAuthenticationToken result 
        	= new UsernamePasswordAuthenticationToken(user_id, user_pw, roles);
        
        // 로그인한 사용자의 정보를 detail 에 넣어준다.
        result.setDetails(resultVO);
        
        HttpSession session = request.getSession();
        session.setAttribute("lvl", resultVO.getLvl());
        
		return result;
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		logger.info("AuthenticationProvider > supports 호출");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	public String encodedKey() throws Exception {
		byte[] buffer = new byte[5 + 5 * 5];
	    new Random().nextBytes(buffer);
	    Base32 codec = new Base32();
	    byte[] secretKey = Arrays.copyOf(buffer, 10);  //16자 이상이여하므로 10으로 설정 필요
	    byte[] bEncodedKey = codec.encode(secretKey);
	     
	    //인증키 생성
	    String encodedKey = new String(bEncodedKey); 
	    System.out.println("encodedKey >>>>>>>>>>>>>>> " +  encodedKey);
	    logger.info("encodedKey", encodedKey);
	    return encodedKey;
	}
	
	//코드 체크 함수
	private static boolean check_code(String secret, long code, long t) throws InvalidKeyException, NoSuchAlgorithmException {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);

		int window = 3;
		for (int i = -window; i <= window; ++i) {
			long hash = verify_code(decodedKey, t + i);

			if (hash == code) {
				return true;
			}
		}

		return false;
	}
	
	//바코드 생성 함수
	public static String getQRBarcodeURL(String user, String host, String secret) {
		String format = "http://chart.apis.google.com/chart?123123213213fadsdsaf";

		return String.format(format, user, host, secret);
	}
	

	//코드 확인 함수
	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException{
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}

		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);

		int offset = hash[20 - 1] & 0xF;

		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			truncatedHash |= (hash[offset + i] & 0xFF);
		}

		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;

		return (int) truncatedHash;
	}


}
