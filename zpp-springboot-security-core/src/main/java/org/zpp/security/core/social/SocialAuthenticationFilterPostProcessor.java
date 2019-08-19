package org.zpp.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * 后置处理器
 */
public interface SocialAuthenticationFilterPostProcessor {
	
	void process(SocialAuthenticationFilter socialAuthenticationFilter);

}
