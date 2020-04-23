package wechat_shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechat_shop.Service.HeaderService;
import wechat_shop.Service.Impl.HeaderServiceImpl;
import wechat_shop.Util.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "head")
public class HeadController {
    private Result headServiceResult;

    @Autowired
    private HeaderServiceImpl headerService;

    // 获取团长列表
    @GetMapping(value = "list")
    public Result HeadList(){

        headServiceResult = headerService.getHeaderList();
//        switch (headServiceResult.getStatus()){
//            case 0:
//                return Result.resultErrorCodeMessage(0,"a");
//        }
        if (headServiceResult.getStatus() == 0){
            return Result.resultErrorCodeMessage(0,"查询错误");
        }
        return Result.resultSuccess(headServiceResult);
    }

    // 获取团长发布的商品
    @PostMapping(value = "commodity")
    public Result HeadCommodity(Integer headerId){

        headServiceResult = headerService.getHeadCommodity(headerId);
        if(headServiceResult.getStatus() == 0){
            return Result.resultErrorMessage("获取错误");
        }
        return Result.resultSuccess(headServiceResult);
    }

    // 注册团长
}
