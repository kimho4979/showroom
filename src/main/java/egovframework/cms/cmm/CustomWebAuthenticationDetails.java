package egovframework.cms.cmm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
	private String otp;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.otp = request.getParameter("otp");
    }

    public String getOtp() {
        return otp;
    }
}