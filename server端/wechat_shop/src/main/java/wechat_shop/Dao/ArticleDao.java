package wechat_shop.Dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.ArticleBean;


import java.util.List;

@Mapper
@Repository
public interface ArticleDao {

    List<ArticleBean> getAllArticle();
}
