///**
// *
// */
//package org.zpp.security.core.social.weixin.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.social.connect.ConnectionFactory;
//import org.springframework.web.servlet.View;
//import org.zpp.security.core.properties.SecurityProperties;
//import org.zpp.security.core.properties.WeixinProperties;
//import org.zpp.security.core.social.MyConnectView;
//import org.zpp.security.core.social.weixin.connect.WeixinConnectionFactory;
//
//
///**
// * 微信登录配置
// *
// * @author zpp
// *
// */
//@Configuration
//@ConditionalOnProperty(prefix = "security.social.weixin", name = "app-id")
//public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {
//
//	@Autowired
//	private SecurityProperties securityProperties;
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
//	 * #createConnectionFactory()
//	 */
//	@Override
//	protected ConnectionFactory<?> createConnectionFactory() {
//		WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
//		return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
//				weixinConfig.getAppSecret());
//	}
//
//	/**
//	 * 微信绑定、解绑配置
//	 *
//	 * connect/weixinConnect：解绑
//	 * connect/weixinConnected：绑定
//	 * @return
//	 */
//	@Bean({"connect/weixinConnect", "connect/weixinConnected"})
//	@ConditionalOnMissingBean(name = "weixinConnectedView")
//	public View weixinConnectedView() {
//		return new MyConnectView();
//	}
//
//}
