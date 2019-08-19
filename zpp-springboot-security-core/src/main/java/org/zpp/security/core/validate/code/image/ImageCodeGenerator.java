package org.zpp.security.core.validate.code.image;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.zpp.security.core.properties.SecurityProperties;
import org.zpp.security.core.validate.code.ValidateCodeGenerator;

/**
 *
 * 图片验证码生成器
 * @author zpp
 */
@Slf4j
public class ImageCodeGenerator implements ValidateCodeGenerator {

	/**
	 * 系统配置
	 */
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public ImageCode generate(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
				securityProperties.getCode().getImage().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				securityProperties.getCode().getImage().getHeight());

		//定义图形验证码的长、宽、验证码字符数、干扰元素个数
		CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(width,height,
				securityProperties.getCode().getImage().getLength(), 20);

		log.info("[imageCode] - [{}]",captcha.getCode());
		ImageCode imageCode = new ImageCode(captcha.getImage(),captcha.getCode(),
				securityProperties.getCode().getImage().getExpireIn());
		return imageCode;
	}
	
	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	

}
