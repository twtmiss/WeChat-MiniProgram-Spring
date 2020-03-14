package denggao.wechat.Dao;

import denggao.wechat.Beans.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//@Param

@Mapper
public interface UserMapper {
    UserVo selectUser();
    int registerUser(UserVo userVo);
    int selectHaveUser(@Param("nickname") String nickname, @Param("phonenumber") String phonenumber);
}
