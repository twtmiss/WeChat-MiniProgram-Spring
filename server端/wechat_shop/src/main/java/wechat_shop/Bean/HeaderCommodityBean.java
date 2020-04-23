package wechat_shop.Bean;

import java.math.BigDecimal;

// 团长发布的商品
public class HeaderCommodityBean {

    private Integer commodityId;            // 商品id
    private Integer headerId;               // 团长id
    private String commodityName;           // 名称
    private String commodityImgUrl;         // 商品图片
    private String commodityDescription;    // 描述
    private String commodityDetails;        // 详情
    private String commoditySpecification;  // 规格
    private String commodityTag;            // 标签
    private Integer commodityNumber;        // 商品库存数量
    private BigDecimal commodityPrice;      // 商品价格

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityImgUrl() {
        return commodityImgUrl;
    }

    public void setCommodityImgUrl(String commodityImgUrl) {
        this.commodityImgUrl = commodityImgUrl;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public String getCommodityDetails() {
        return commodityDetails;
    }

    public void setCommodityDetails(String commodityDetails) {
        this.commodityDetails = commodityDetails;
    }

    public String getCommoditySpecification() {
        return commoditySpecification;
    }

    public void setCommoditySpecification(String commoditySpecification) {
        this.commoditySpecification = commoditySpecification;
    }

    public String getCommodityTag() {
        return commodityTag;
    }

    public void setCommodityTag(String commodityTag) {
        this.commodityTag = commodityTag;
    }

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }
}
