package org.zpp.security.component;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import org.zpp.security.core.authorize.AuthorizeConfigProvider;

/**
 * demo授权配置
 */
@Component
public class DemoAuthorizeConifgProvider implements AuthorizeConfigProvider {


	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		return false;
	}

}
