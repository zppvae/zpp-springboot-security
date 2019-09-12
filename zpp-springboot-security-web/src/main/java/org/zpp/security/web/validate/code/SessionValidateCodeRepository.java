package org.zpp.security.web.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.zpp.common.validate.code.ValidateCode;
import org.zpp.common.validate.code.ValidateCodeRepository;
import org.zpp.common.validate.code.ValidateCodeType;

import javax.servlet.http.HttpServletRequest;

/**
 * 浏览器session存储
 *
 * @author zpp
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	/**
	 * 操作session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Override
	public void save(String deviceId, ValidateCode code, ValidateCodeType validateCodeType) {
		ServletWebRequest request = new ServletWebRequest(httpServletRequest);
		sessionStrategy.setAttribute(request, getSessionKey(validateCodeType), code);
	}
	
	/**
	 * 构建验证码放入session时的key
	 * 
	 * @return
	 */
	private String getSessionKey(ValidateCodeType validateCodeType) {
		return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
	}


	@Override
	public ValidateCode get(String deviceId, ValidateCodeType validateCodeType) {
		ServletWebRequest request = new ServletWebRequest(httpServletRequest);
		return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(validateCodeType));
	}


	@Override
	public void remove(String deviceId, ValidateCodeType codeType) {
		ServletWebRequest request = new ServletWebRequest(httpServletRequest);
		sessionStrategy.removeAttribute(request, getSessionKey(codeType));
	}

}