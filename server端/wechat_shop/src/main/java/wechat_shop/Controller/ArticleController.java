package wechat_shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
