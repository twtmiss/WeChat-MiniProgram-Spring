package denggao.wechat.Service.impl;

import denggao.wechat.Beans.UserVo;

public interface UserServiceImpl {
    UserVo selectUser();
    boolean registerUser(UserVo userVo);
    boolean selectHaveUser(UserVo userVo);
}
