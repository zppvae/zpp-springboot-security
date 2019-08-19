package org.zpp.security.app.social;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.zpp.security.core.social.MySpringSocialConfigurer;


@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

	/**
	 * bean初始化之前
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * bean 初始化后
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (StringUtils.equals(beanName, "mySocialSecurityConfig")) {
			MySpringSocialConfigurer configurer = (MySpringSocialConfigurer)bean;
			//初始化后改变signUp
			configurer.signupUrl("/social/signUp");
			return configurer;
		}
		return bean;
	}

}
