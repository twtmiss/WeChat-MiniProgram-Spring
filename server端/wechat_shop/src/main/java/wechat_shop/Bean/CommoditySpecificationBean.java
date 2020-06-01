package wechat_shop.Bean;

import java.math.BigDecimal;

public class CommoditySpecificationBean {
    private Integer specificationId;
    private Integer commodityId;
    private Integer specificationSequence;
    private String specificationName;
    private String specificationDetail;
    private String specificationImgUrl;
    private Integer specificationNumber;
    private BigDecimal specificationPrice;

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getSpecificationSequence() {
        return specificationSequence;
    }

    public void setSpecificationSequence(Integer specificationSequence) {
        this.specificationSequence = specificationSequence;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public String getSpecificationDetail() {
        return specificationDetail;
    }

    public void setSpecificationDetail(String specificationDetail) {
        this.specificationDetail = specificationDetail;
    }

    public String getSpecificationImgUrl() {
        return specificationImgUrl;
    }

    public void setSpecificationImgUrl(String specificationImgUrl) {
        this.specificationImgUrl = specificationImgUrl;
    }

    public Integer getSpecificationNumber() {
        return specificationNumber;
    }

    public void setSpecificationNumber(Integer specificationNumber) {
        this.specificationNumber = specificationNumber;
    }

    public BigDecimal getSpecificationPrice() {
        return specificationPrice;
    }

    public void setSpecificationPrice(BigDecimal specificationPrice) {
        this.specificationPrice = specificationPrice;
    }
}
