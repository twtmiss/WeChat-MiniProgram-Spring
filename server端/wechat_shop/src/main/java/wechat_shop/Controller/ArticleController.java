package wechat_shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat_shop.Bean.ArticleBean;
import wechat_shop.Service.ArticleService;
import wechat_shop.Util.Result;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Article")
public class ArticleController {
//    private toResponse toResponse = new toResponse();

    @Autowired
    private ArticleService articleService;


    // 获取状态为显示的文章
    @GetMapping("/ArticleOfShow")
    public Object GetArticleOfShow(){
        List<ArticleBean> articleList = articleService.GetArticle();
        List<ArticleBean> articleResponseList = new ArrayList<>();
        for(ArticleBean articleVo: articleList){
            if(articleVo.getIsShow()==1){
                articleResponseList.add(articleVo);
            }
        }
        return Result.resultSuccess("OK",articleResponseList);
    }

    // 获取团长发布的文章列表
    @GetMapping("/head")
    public Object GetActivityListByHeadId(Integer headId){
        List<ArticleBean> articleList = articleService.GetArticleListByHeadId(headId);
        return Result.resultSuccess("OK",articleList);
    }

    // 获取文章内容
    @GetMapping("/article")
    public Object GetActivityByArticleId(Integer articleId){
        ArticleBean article = articleService.GetArticleByArticleId(articleId);

        return Result.resultSuccess("OK",article);
    }

    // 创建文章
    @PostMapping("/create")
    public Object CreateArticle(Integer headId,Integer articleId, String articleMarkdown,String coverImgUrl){
        Integer result = articleService.CreateArticle(headId,articleMarkdown,coverImgUrl);
        return Result.resultSuccess("OK",result);
    }

    // 更新文章状态
    @PostMapping("/updateStatus")
    public Object UpdateArticleStatusByArticleId(Integer articleId){
        Integer result = articleService.UpdateArticleStatusByArticleId(articleId);
        return Result.resultSuccess("OK",result);
    }

    // 更新文章
    @PostMapping("/update")
    public Object UpdateArticleByArticleId(Integer articleId, String articleMarkdown,String coverImgUrl){
        Integer result = articleService.UpdateArticleByArticleId(articleId,articleMarkdown,coverImgUrl);
        return Result.resultSuccess("OK",result);
    }

    // 删除文章
    @DeleteMapping("/delete")
    public Object DeleteArticleByArticleId(Integer articleId){
        Integer result = articleService.DeleteArticleByArticleId(articleId);
        return Result.resultSuccess("OK",result);
    }
}
