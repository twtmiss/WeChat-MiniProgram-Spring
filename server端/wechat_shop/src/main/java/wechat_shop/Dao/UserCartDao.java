package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.HeaderCommodityBean;

import java.util.List;

@Mapper
@Repository
public interface UserCartDao {

    // 在团长表查询所有信息
    List<HeaderBean> selectAllHeader();

    // 获取团长发布的商品
    List<HeaderCommodityBean> getHeadCommodity(int headerId);
}
