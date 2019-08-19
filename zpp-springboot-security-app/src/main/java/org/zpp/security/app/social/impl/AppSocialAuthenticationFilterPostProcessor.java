package org.zpp.security.app.social.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.zpp.security.core.social.SocialAuthenticationFilterPostProcessor;

/**
 * 后置处理器实现
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {
	
	@Autowired
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Override
	public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
		//设置成自己写的生成令牌的成功处理器，不像浏览器那样跳转
		socialAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
	}

}
