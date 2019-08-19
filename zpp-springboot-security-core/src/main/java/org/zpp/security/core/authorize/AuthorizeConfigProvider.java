package org.zpp.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 *
 * 授权配置提供器
 *
 * 各个模块和业务系统可以通过实现此接口向系统添加授权配置
 * @author zpp
 */
public interface AuthorizeConfigProvider {

	/**
	 * @param config
	 * @return 返回的boolean表示配置中是否有针对anyRequest的配置。
	 *
	 */
	boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);


}
