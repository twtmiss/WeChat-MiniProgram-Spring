package denggao.wechat.Service;

import denggao.wechat.Beans.UserVo;
import denggao.wechat.Dao.UserMapper;
import denggao.wechat.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl{
    @Autowired
    private UserMapper userMapper;


    public UserVo selectUser(){
        return userMapper.selectUser();
    }

    public boolean selectHaveUser(UserVo userVo){
        System.out.println(userMapper.selectHaveUser(userVo.getNickName(),userVo.getPhoneNumber()));
        if(userMapper.selectHaveUser(userVo.getNickName(),userVo.getPhoneNumber()) > 0){

            return true;
        }
        return false;
    }

    public boolean registerUser(UserVo userVo){
        userVo.setRegisterTime(System.currentTimeMillis());
        if(userMapper.registerUser(userVo) >= 1){
            return true;
        }else {
            return false;
        }
    }
}
