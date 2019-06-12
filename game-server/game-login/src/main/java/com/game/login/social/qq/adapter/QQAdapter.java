package com.game.login.social.qq.adapter;

import com.game.common.entity.qq.QQUserInfo;
import com.game.login.social.qq.service.QQService;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author : wx
 * @Desc :
 * @Date :  下午 3:06 2019/6/10 0010
 * @explain :适配器 将qq的信息 适配成social的类型
 */
public class QQAdapter implements ApiAdapter<QQService> {

    @Override
    public boolean test(QQService api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQService api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        //用户的名字
        values.setDisplayName(userInfo.getNickname());
        //用户的头像
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        //个人主页，QQ没个人主页
        values.setProfileUrl(null);
        //用户的ID
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQService api) {
        return null;
    }

    @Override
    public void updateStatus(QQService api, String message) {

    }
}
