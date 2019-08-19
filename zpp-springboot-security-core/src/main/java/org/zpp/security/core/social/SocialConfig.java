package org.zpp.security.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.View;
import org.zpp.security.core.properties.QQProperties;
import org.zpp.security.core.properties.SecurityProperties;
import org.zpp.security.core.properties.WeixinProperties;
import org.zpp.security.core.social.qq.connect.QQConnectionFactory;
import org.zpp.security.core.social.weixin.connect.WeixinConnectionFactory;

import javax.sql.DataSource;


@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 注册连接
	 */
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	/**
	 * app环境下需要
	 */
	@Autowired(required = false)
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	@Autowired(required = false)
	private WeixinConnectionFactory weixinConnectionFactory;

	@Autowired(required = false)
	private QQConnectionFactory qqConnectionFactory;

	/**
	 * 社交登录数据库连接配置
	 * @param connectionFactoryLocator  查找connectionFactory
	 * @return
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		//Encryptors.noOpText(),加解密
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		//设置社交登录表的表名前缀
		repository.setTablePrefix("t_");
		if(connectionSignUp != null) {
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}

	@Bean
	public SpringSocialConfigurer mySocialSecurityConfig() {
		//社交登录前缀
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
		MySpringSocialConfigurer configurer = new MySpringSocialConfigurer(filterProcessesUrl);
		//注册页面的URL
		configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
		return configurer;
	}

	/**
	 * 社交工具类
	 * @param connectionFactoryLocator
	 * @return
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,
				getUsersConnectionRepository(connectionFactoryLocator)) {
		};
	}

	/**
	 * 微信绑定、解绑配置
	 *
	 * connect/weixinConnect：解绑
	 * connect/weixinConnected：绑定
	 * @return
	 */
	@Bean({"connect/weixinConnect", "connect/weixinConnected"})
	@ConditionalOnMissingBean(name = "weixinConnectedView")
	public View weixinConnectedView() {
		return new MyConnectView();
	}

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
		if (weixinConnectionFactory != null) {
			configurer.addConnectionFactory(weixinConnectionFactory);
		}
		if (qqConnectionFactory != null) {
			configurer.addConnectionFactory(qqConnectionFactory);
		}
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Bean
	@ConditionalOnProperty(prefix = "security.social.weixin", name = "app-id")
	public WeixinConnectionFactory weixinConnectionFactory(){
		WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
				weixinConfig.getAppSecret());
	}

	@Bean
	@ConditionalOnProperty(prefix = "security.social.qq", name = "app-id")
	public QQConnectionFactory qqConnectionFactory(){
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(),
				qqConfig.getAppId(), qqConfig.getAppSecret());
	}
}
