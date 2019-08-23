package org.zpp.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.zpp.security.core.properties.SecurityConstants;

/**
 *
 * 此类由业务系统提供
 * @author zpp
 *
 */
@Component
@Slf4j
public class DemoUserDetailsService implements UserDetailsService, SocialUserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("[表单登录] - [{}]",username);
		return buildUser(username);
	}

	/**
	 *
	 * @param userId  社交登录表的userId
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.info("[社交登录用户] - [{}]",userId);
		return buildUser(userId);
	}

	private SocialUserDetails buildUser(String userId) {
		// 根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否被冻结
		String password = "$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC";
		log.info("[数据库密码] - [{}]",password);
		return new SocialUser(userId, SecurityConstants.BCRYPT + password,
				true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
	}

}
