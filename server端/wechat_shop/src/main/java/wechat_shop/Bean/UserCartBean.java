package wechat_shop.Bean;

import java.math.BigDecimal;

// 购物车
public class UserCartBean {
    private Integer cartId;             // 购物车id
    private Integer userId;             // 用户id
    private Integer headerId;           // 团长id
    private Integer commodityId;        // 商品id
    private Integer commodityNumber;    // 购买数量

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }
}
