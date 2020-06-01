package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.CommodityBean;
import wechat_shop.Bean.OrderBean;
import wechat_shop.Bean.OrderCommodityBean;

import java.util.List;

@Mapper
@Repository
public interface OrderCommodityDao {

    // 向订单表写入订单信息
    Integer insertOrderComodity(OrderCommodityBean orderCommodityBean);

    OrderCommodityBean getOrderCommodityListByOrderId(@Param("orderId") Integer orderId);

    List<OrderCommodityBean> getOrderCommodityListByHeadId(@Param("headId") Integer headId);

}
