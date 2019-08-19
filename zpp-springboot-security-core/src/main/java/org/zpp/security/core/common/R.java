package org.zpp.security.core.common;

import lombok.*;
import lombok.experimental.Accessors;
import org.zpp.security.core.properties.SecurityConstants;

import java.io.Serializable;

@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code = SecurityConstants.SUCCESS;

	@Getter
	@Setter
	private String msg = "success";


	@Getter
	@Setter
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public R(Throwable e) {
		super();
		this.code = SecurityConstants.FAIL;
		this.msg = e.getMessage();
	}
}