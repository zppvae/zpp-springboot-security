package org.zpp.security.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.zpp.security.core.component.MyOAuth2ExceptionSerializer;

/**
 * 方法不允许异常
 * @author zpp
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends MyOAuth2Exception {

	public MethodNotAllowed(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
