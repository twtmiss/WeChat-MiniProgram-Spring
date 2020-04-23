package wechat_shop.Bean;

import java.util.Date;

// 用户
public class UserBean {
    private Integer userId;         // 用户id
    private String userName;        // 用户昵称
    private String avatarUrl;       // 用户头像图片链接
    private String city;            // 用户所在城市
    private String phoneNumber;     // 用户手机号
    private long registerTime;      // 注册时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime() {
        this.registerTime = new Date().getTime();
    }
}
