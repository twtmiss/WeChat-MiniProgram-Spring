package denggao.wechat.Controller;


import denggao.wechat.Beans.UserVo;
import denggao.wechat.Service.UserService;
import denggao.wechat.util.toResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/*
 描述: 用户注册
 */
@RestController
@RequestMapping("/user")
public class UserController extends toResponse {
    @Autowired
    private UserService userService;

    private toResponse toResponse = new toResponse();

    /*
    用户注册
     */
    @PostMapping("/PostRegister")
    public Object Register (@RequestBody UserVo user){
        if(user.getNickName() == null || user.getPhoneNumber() == null){
            toResponse.toResponsFail("用户昵称或手机号为空");
        }
        if(userService.registerUser(user)){
            toResponse.toResponsFail("注册失败");
        }
        if(userService.selectHaveUser(user)){
            System.out.println("is true");
            return toResponse.toResponsFail("用户已经注册过了");
        }
//        UserVo userVo = userService.selectUser();
//        System.out.println(userVo.getNickName());
        return toResponse.toResponsSuccess("success");
    }

}
