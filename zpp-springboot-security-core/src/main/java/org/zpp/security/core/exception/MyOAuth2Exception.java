package org.zpp.security.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.zpp.security.core.component.MyOAuth2ExceptionSerializer;

/**
 * 自定义异常
 * @author zpp
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class MyOAuth2Exception extends OAuth2Exception {
	private String errorCode;

	public MyOAuth2Exception(String msg) {
		super(msg);
	}

	public MyOAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}