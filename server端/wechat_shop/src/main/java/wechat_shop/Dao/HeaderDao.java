package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.HeaderBean;

import java.util.List;

@Mapper
@Repository
public interface HeaderDao {

    // 在团长表查询所有信息
    List<HeaderBean> selectAllHeader();

    //  获取团长信息
    HeaderBean selectHeadByUserId(@Param("userId") Integer userId);

    // 根据团长Id从团长表查团长信息
    HeaderBean selectHeadByHeadId(@Param("headId") Integer headId);

    Integer insertHeadRegiester(HeaderBean headerBean);

}
