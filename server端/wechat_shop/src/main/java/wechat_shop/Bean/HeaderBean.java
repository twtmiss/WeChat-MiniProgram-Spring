package wechat_shop.Bean;

import java.util.Date;

// 团长
public class HeaderBean {
    private Integer headerId;       // 团长id
    private String city;            // 城市
    private String community;       // 社区
    private String phoneNumber;     // 手机号
    private Long registeredTime;    // 注册时间
    private String longitude;       // 经度
    private String latitude;        // 纬度

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime() {
        this.registeredTime = new Date().getTime();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
