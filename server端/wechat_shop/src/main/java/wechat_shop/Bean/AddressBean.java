package wechat_shop.Bean;

// 用户收货地址

import org.springframework.context.annotation.Bean;

public class AddressBean {
    private Integer addressId;          // 地址id
    private Integer userId;             // 用户id
    private String userName;            // 收货人姓名
    private String phoneNumber;         // 收货人手机号
    private String province;            // 省
    private String city;                // 用户所在城市
    private String region;              // 区域
    private String street;              // 街道
    private String address;             // 详细地址
    private Integer defaultAddress;     // 是否位默认地址，0否 1是

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
