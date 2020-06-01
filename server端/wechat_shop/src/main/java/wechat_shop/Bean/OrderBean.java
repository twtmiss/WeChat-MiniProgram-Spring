package wechat_shop.Bean;

import java.math.BigDecimal;
import java.util.Date;

// 订单
public class OrderBean {
    private Integer orderId;                    // 订单id
    private Integer userId;
//    private Integer headId;
//    private Integer commodityId;                // 商品id
//    private Integer specificationId;
//    private Integer buyNumber;
    private String meansOfTransaction;          // 交易方式
    private BigDecimal totalPrice;              // 总价
    private BigDecimal payPrice;                // 支付价格
    private BigDecimal discountedPrices;        // 优惠价格
    private String weChatTransactionNumber;     // 微信交易号
    private long createTime;                    // 创建时间
    private long paymentTime;                   // 付款时间
    private long transactionTime;               // 成交时间

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

//    public Integer getCommodityId() {
//        return commodityId;
//    }
//
//    public void setCommodityId(Integer commodityId) {
//        this.commodityId = commodityId;
//    }

    public String getMeansOfTransaction() {
        return meansOfTransaction;
    }

    public void setMeansOfTransaction(String meansOfTransaction) {
        this.meansOfTransaction = meansOfTransaction;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getDiscountedPrices() {
        return discountedPrices;
    }

    public void setDiscountedPrices(BigDecimal discountedPrices) {
        this.discountedPrices = discountedPrices;
    }

    public String getWeChatTransactionNumber() {
        return weChatTransactionNumber;
    }

    public void setWeChatTransactionNumber(String weChatTransactionNumber) {
        this.weChatTransactionNumber = weChatTransactionNumber;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime() {
        this.createTime = new Date().getTime();
    }

    public long getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime() {
        this.paymentTime = new Date().getTime();
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime() {
        this.transactionTime = new Date().getTime();
    }

//    public Integer getHeadId() {
//        return headId;
//    }
//
//    public void setHeadId(Integer headId) {
//        this.headId = headId;
//    }
//
//    public Integer getSpecificationId() {
//        return specificationId;
//    }
//
//    public void setSpecificationId(Integer specificationId) {
//        this.specificationId = specificationId;
//    }
//
//    public Integer getBuyNumber() {
//        return buyNumber;
//    }
//
//    public void setBuyNumber(Integer buyNumber) {
//        this.buyNumber = buyNumber;
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
