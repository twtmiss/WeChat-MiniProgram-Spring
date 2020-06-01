package wechat_shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.UserBean;
import wechat_shop.Service.HeaderService;
import wechat_shop.Service.Impl.HeaderServiceImpl;
import wechat_shop.Util.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "head")
public class HeadController {
    private Result headServiceResult;

    @Autowired
    private HeaderServiceImpl headerService;

    // 获取团长列表
    @GetMapping(value = "list")
    public Result HeadList(String longitude, String latitude){
        if(longitude == null || latitude ==null){
            return Result.resultErrorMessage("经度或纬度为空");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("longitude", Double.valueOf(longitude));
        param.put("latitude", Double.valueOf(latitude));
        List<Map<String,String>> headerList = headerService.getHeaderList(param);
//        switch (headServiceResult.getStatus()){
//            case 0:
//                return Result.resultErrorCodeMessage(0,"a");
//        }
//        if (headServiceResult.getStatus() == 0){
//            return Result.resultErrorCodeMessage(0,"查询错误");
//        }
        return Result.resultSuccess(headerList);
    }

    // 获取团长信息
    @GetMapping(value = "HeaderInfo")
    public Result HeadInfo(Integer userId){
        if(userId == null){
            return Result.resultErrorMessage("用户Id为空");
        }

        Map<String, String> headerInfo = headerService.getHeadInfoByUserId(userId);
//        switch (headServiceResult.getStatus()){
//            case 0:
//                return Result.resultErrorCodeMessage(0,"a");
//        }
//        if (headServiceResult.getStatus() == 0){
//            return Result.resultErrorCodeMessage(0,"查询错误");
//        }
        return Result.resultSuccess(headerInfo);
    }

    // 团长注册
    @PostMapping(value = "regiester")
    public Result HeadRegiester(Integer userId,String city,String phoneNumber,String community,String latitude,String longitude){
        if(userId == null){
            return Result.resultErrorMessage("用户Id为空");
        }

        Integer headId = headerService.headRegiester(userId,city,phoneNumber,community,latitude, longitude);
//       city phoneNumber community latitude longitude
        return Result.resultSuccess(headId);
    }

}
