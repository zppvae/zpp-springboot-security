package org.zpp.security.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.zpp.security.core.component.MyOAuth2ExceptionSerializer;

/**
 * 未授权异常
 * @author zpp
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class UnauthorizedException extends MyOAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
