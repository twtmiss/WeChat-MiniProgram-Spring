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
        for(ArticleBean articleVo:articleList){
            String imgName = articleVo.getCoverImgUrl();
            String url = Config.ServerUrl;
            articleVo.setCoverImgUrl(url+imgName);
        }

        return articleList;
    }
}
