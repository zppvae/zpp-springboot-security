package org.zpp.security.component;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 *  注册逻辑（自动注册社交账号）
 * @author zpp
 *
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {


	@Override
	public String execute(Connection<?> connection) {
		//根据社交用户信息默认创建用户并返回用户唯一标识
		return connection.getDisplayName();
	}

}
