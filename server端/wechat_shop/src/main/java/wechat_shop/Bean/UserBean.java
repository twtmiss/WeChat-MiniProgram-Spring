package wechat_shop.Bean;

import java.util.Date;

// 用户
public class UserBean {
    private Integer userId;         // 用户id
    private Integer headerId;       // 团长id
    private String userName;        // 用户昵称
    private String avatarUrl;       // 用户头像图片链接
    private String city;            // 用户所在城市
    private String phoneNumber;     // 用户手机号
    private long registerTime;      // 注册时间
    private String open_id;         // 微信小程序用户唯一标识
    private Integer cartId;         // 购物车Id

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
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

    public void setRegisterTime(long registerTime) {
        this.registerTime = new Date().getTime();
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
