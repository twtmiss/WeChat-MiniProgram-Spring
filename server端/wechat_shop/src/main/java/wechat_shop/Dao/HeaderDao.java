package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.HeaderBean;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HeaderDao {

    // 在团长表查询所有信息
    List<HeaderBean> selectAllHeader();

}
