package org.zpp.security.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.zpp.security.core.properties.SecurityProperties;
import org.zpp.security.web.logout.MyLogoutSuccessHandler;
import org.zpp.security.web.session.MyExpiredSessionStrategy;
import org.zpp.security.web.session.MyInvalidSessionStrategy;


@Configuration
public class WebSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * session 失效策略
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new MyInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}

	/**
	 * 并发登录导致 session 超时策略（后一个登录剔除前一个用户）
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new MyExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}

	/**
	 * 退出成功处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new MyLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
	}
	
}
