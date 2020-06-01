package wechat_shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat_shop.Service.Impl.CommodityServiceImpl;
import wechat_shop.Util.Result;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "commodity")
public class CommodityController {

    @Autowired
    private CommodityServiceImpl commodityService;

    // 获取团长发布的商品列表
    @GetMapping(value = "list")
    public Result getCommodityListByHeadId(Integer headId){

        List<Map<String, Object>> resultCommodity= commodityService.getCommodityListByHeadId(headId);
//        if(headServiceResult.getStatus() == 0){
//            return Result.resultErrorMessage("获取错误");
//        }
        return Result.resultSuccess(resultCommodity);
    }

    // 获取商品信息
    @GetMapping(value = "commodity")
    public Result CommodityInfo(Integer commodityId){

        Map<String, Object> resultCommodity= commodityService.getCommodityByCommodityId(commodityId);
//        if(headServiceResult.getStatus() == 0){
//            return Result.resultErrorMessage("获取错误");
//        }
        return Result.resultSuccess(resultCommodity);
    }

    // 更新商品状态
    @PostMapping("/updateStatus")
    public Object UpdateCommodityStatusByCommodityId(Integer commodityId){
        Integer result = commodityService.UpdateCommodityStatusByCommodityId(commodityId);
        return Result.resultSuccess("OK",result);
    }

    // 更新商品
//    @PostMapping("/update")
//    public Object UpdateCommodityByCommodityId(Integer articleId, String articleMarkdown,String coverImgUrl){
//        Integer result = articleService.UpdateArticleByArticleId(articleId,articleMarkdown,coverImgUrl);
//        return Result.resultSuccess("OK",result);
//    }

    // 删除商品
    @DeleteMapping("/delete")
    public Object DeleteCommodityByCommodityId(Integer commodityId){
        Integer result = commodityService.DeleteCommodityByCommodityId(commodityId);
        return Result.resultSuccess("OK",result);
    }
}
