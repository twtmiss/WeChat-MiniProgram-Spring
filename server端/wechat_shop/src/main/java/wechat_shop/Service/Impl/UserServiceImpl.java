package wechat_shop.Service.Impl;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat_shop.Bean.UserBean;
import wechat_shop.Dao.UserDao;
import wechat_shop.Service.UserService;
import wechat_shop.Util.Config;
import wechat_shop.Util.HttpClientUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Map<String, String> UserLogin(String code){
        Map<String, String> param = new HashMap<>();
        param.put("appid", Config.WeChatAppid);
        param.put("secret", Config.WeChatSecret);
        param.put("js_code", code);
//        param.put("grant_type", Config.WeChatGrant_type);

        // 发送请求
        String wxResult = HttpClientUtil.doGet(Config.WeChatUrl, param);
//        System.out.println(wxResult);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
//        String unionid = jsonObject.get("unionid").toString();

        String userId = userDao.selectIsLogin(open_id);
        Map<String, String> result = new HashMap<>();
        // 判断是否已注册
        if(userId != null){
            //用户已注册
            result.put("userId",userId);
            result.put("isRegister","1"); // 0 未注册，1 已注册
            return result;
        }
        // 封装返回小程序
        result.put("isRegister","0"); // 0 未注册，1 已注册
        result.put("session_key", session_key);
        result.put("open_id", open_id);
        result.put("userId","");
//        result.put("unionid", unionid);

        return result;
    }

    public Integer UserRegister(String userName, String avatarUrl, String city, String phoneNumber, String open_id){
        // 根据用户名去user表查询是否已注册过
        long registerTime = new Date().getTime();
        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setAvatarUrl(avatarUrl);
        userBean.setCity(city);
        userBean.setPhoneNumber(phoneNumber);
        userBean.setRegisterTime(registerTime);
        userBean.setOpen_id(open_id);
        userDao.insertUserInfo(userBean);
        Integer userId = userBean.getUserId();

        // 设置用户购物车id为用户id，在用户表中写入
        Integer result = userDao.insertUserCartId(userId);
        return userId;
    }

    public Integer UpdateHeadId(String userId, String headerId){
        return userDao.UpdateHeadId(userId,headerId);
    }
}
