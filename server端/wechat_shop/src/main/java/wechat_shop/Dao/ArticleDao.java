package wechat_shop.Dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.ArticleBean;


import java.util.List;

@Mapper
@Repository
public interface ArticleDao {

    List<ArticleBean> getAllArticle();

    List<ArticleBean> selectArticleListByHeadId(@Param("headId")Integer headId);

    ArticleBean selectArticleByArticleId(@Param("articleId") Integer articleId);

    Integer updateArticleStatusByArticleId(@Param("articleId") Integer articleId,@Param("isShow") Integer isShow);

    Integer insertArticle(@Param("headId")Integer headId, @Param("articleMarkdown") String articleMarkdown,@Param("coverImgUrl")String coverImgUrl);

    Integer updateArticleByArticleId(@Param("articleId") Integer articleId, @Param("articleMarkdown") String articleMarkdown,@Param("coverImgUrl")String coverImgUrl);

    Integer deleteArticleByArticleId(@Param("articleId") Integer articleId);


}
