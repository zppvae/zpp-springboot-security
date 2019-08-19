package org.zpp.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.zpp.security.core.social.qq.api.QQ;
import org.zpp.security.core.social.qq.api.QQUserInfo;


/**
 * 将QQ的数据与SpringSocial标准化数据之间做适配
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 测试QQ服务是否可用
	 * @param api
	 * @return
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		//个人主页
		values.setProfileUrl(null);
		//服务商的用户id
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		//do noting
	}

}
