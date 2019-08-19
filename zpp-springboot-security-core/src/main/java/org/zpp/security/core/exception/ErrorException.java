package org.zpp.security.core.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.zpp.security.core.component.MyOAuth2ExceptionSerializer;


@JsonSerialize(using = MyOAuth2ExceptionSerializer.class)
public class ErrorException extends MyOAuth2Exception {

	public ErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
