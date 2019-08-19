package org.zpp.security.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.zpp.security.core.component.MyOAuth2ExceptionSerializer;

/**
 * 无效异常
 * @author zpp
 */
@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class InvalidException extends MyOAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_error";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
