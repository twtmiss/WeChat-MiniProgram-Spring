package wechat_shop.Service;

import java.util.Map;

public interface UserService {

    // 用户注册
    Integer UserRegister(String userName, String avatarUrl, String city, String phoneNumber, String open_id);

    // 用户登录
    Map<String, String> UserLogin(String code);

    // 更新用户选择的团长id
    Integer UpdateHeadId(String userId, String headerId);
}
