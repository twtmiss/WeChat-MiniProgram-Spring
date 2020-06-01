package wechat_shop.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat_shop.Bean.ArticleBean;
import wechat_shop.Dao.ArticleDao;
import wechat_shop.Service.ArticleService;
import wechat_shop.Util.Config;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public List<ArticleBean> GetArticle(){
        List<ArticleBean> articleList = articleDao.getAllArticle();
//        for(ArticleBean articleVo:articleList){
//            String imgName = articleVo.getCoverImgUrl();
//            String url = Config.ServerUrl;
//            articleVo.setCoverImgUrl(url+imgName);
//        }

        return articleList;
    }

    public List<ArticleBean> GetArticleListByHeadId(Integer headId){
        List<ArticleBean> articleList = articleDao.selectArticleListByHeadId(headId);
//        for(ArticleBean articleVo:articleList){
//            String imgName = articleVo.getCoverImgUrl();
//            String url = Config.ServerUrl;
//            articleVo.setCoverImgUrl(url+imgName);
//        }
        return articleList;
    }

    public ArticleBean GetArticleByArticleId(Integer articleId){
        ArticleBean article = articleDao.selectArticleByArticleId(articleId);
//        String imgName = article.getCoverImgUrl();
//        String url = Config.ServerUrl;
//        article.setCoverImgUrl(url+imgName);
        return article;
    }

    public Integer CreateArticle(Integer headId, String articleMarkdown,String coverImgUrl){
        Integer result = articleDao.insertArticle(headId,articleMarkdown,coverImgUrl);
        return 0;
    }

    public Integer UpdateArticleStatusByArticleId(Integer articleId){
        ArticleBean articleBean = articleDao.selectArticleByArticleId(articleId);
//        Integer isShow = articleBean.getIsShow();

        Integer article = articleDao.updateArticleStatusByArticleId(articleId,articleBean.getIsShow()==0?1:0);
        return article;
    }

    public Integer UpdateArticleByArticleId(Integer articleId, String articleMarkdown,String coverImgUrl){
        Integer article = articleDao.updateArticleByArticleId(articleId,articleMarkdown,coverImgUrl);
        return article;
    }

    public Integer DeleteArticleByArticleId(Integer articleId){
        Integer article = articleDao.deleteArticleByArticleId(articleId);
        return article;
    }
}
