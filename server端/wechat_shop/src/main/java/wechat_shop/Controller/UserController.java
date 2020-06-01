package wechat_shop.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechat_shop.Service.Impl.UserServiceImpl;
import wechat_shop.Util.Result;

import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {
    private Result headServiceResult;

    @Autowired
    private UserServiceImpl userService;

    // 用户登录
    @PostMapping(value = "login")
    public Result UserLogin(String code){
        Map<String, String> resultMap = userService.UserLogin(code);

        if(resultMap.get("isRegister").equals("1")){
            return Result.resultSuccess(2,"用户已注册",resultMap);
        }
        return Result.resultSuccess(resultMap);
    }

    // 用户注册
    @PostMapping(value = "register")
    public Result UserRegister(String userName, String avatarUrl, String city, String phoneNumber, String open_id){

        Integer userId = userService.UserRegister(userName,avatarUrl,city,phoneNumber,open_id);
        if(userId == null){
            return Result.resultErrorMessage("注册失败");
        }
        return Result.resultSuccess(userId);
    }

    // 更新用户选择的团长id
    @PostMapping(value = "updateHeadId")
    public Result updateHeadId(String userId, String headerId){
        if(userService.UpdateHeadId(userId,headerId) != 1){
            return Result.resultErrorMessage("注册失败");
        }
        return Result.resultSuccess(userId);
    }
}
