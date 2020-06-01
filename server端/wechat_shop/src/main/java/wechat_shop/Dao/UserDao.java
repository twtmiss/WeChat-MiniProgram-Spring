package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.UserBean;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    // 查询是否已注册过
    String selectIsLogin(@Param("open_id")String open_id);

    // 根据团长绑定的用户id查询用户信息
    List<UserBean> selectUserByHeaderId(@Param("userIdList")List<Integer> userIdList);

    // 根据用户id查询单个用户信息
    UserBean selecctUserByUserId(@Param("userId")Integer userId);

    // 向用户表插入用户信息
    Integer insertUserInfo(UserBean userBean);

    // 更新用户选择的团长id
    Integer UpdateHeadId(@Param("userId")String userId, @Param("headerId")String headerId);

    // 根据用户Id查询用户选择的团长id
    Integer selectSelectHeadIdByUserId(@Param("userId") Integer userId);

    // 根据团长Id查询用户名
    String selectNameByHeaderId(@Param("headId")Integer headId);

    // 根据用户Id获取购物车Id
    Integer selectCartIdByUserId(@Param("userId")Integer userId);

    // 设置用户购物车id为用户id，在用户表中写入
    Integer insertUserCartId(@Param("userId")Integer userId);

}
