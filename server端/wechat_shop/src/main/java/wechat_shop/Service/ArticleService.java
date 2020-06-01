package wechat_shop.Service;

import wechat_shop.Bean.ArticleBean;

import java.util.List;

public interface ArticleService {
    List<ArticleBean> GetArticle();

    List<ArticleBean> GetArticleListByHeadId(Integer headId);

    ArticleBean GetArticleByArticleId(Integer articleId);

    Integer UpdateArticleStatusByArticleId(Integer articleId);

    Integer CreateArticle(Integer headId,String articleMarkdown,String coverImgUrl);

    Integer UpdateArticleByArticleId(Integer articleId, String articleMarkdown,String coverImgUrl);

    Integer DeleteArticleByArticleId(Integer articleId);
}
