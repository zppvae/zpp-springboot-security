package org.zpp.security.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.zpp.security.core.component.MyOAuth2ExceptionSerializer;

/**
 * 禁止访问异常
 * @author zpp
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class ForbiddenException extends MyOAuth2Exception {

	public ForbiddenException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "forbidden";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}

