package wechat_shop.Bean;

// 文章
public class ArticleBean {
    private Integer articleId;          // 文章id
    private Integer headId;
    private String coverImgUrl;         // 文章封面图片地址链接
    private String articleMarkdown;     // 文章md格式文本
    private Integer isShow;             // 文章是否显示 0否 1是
    private String createType;          // 文章类型

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getArticleMarkdown() {
        return articleMarkdown;
    }

    public void setArticleMarkdown(String articleMarkdown) {
        this.articleMarkdown = articleMarkdown;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }
}
