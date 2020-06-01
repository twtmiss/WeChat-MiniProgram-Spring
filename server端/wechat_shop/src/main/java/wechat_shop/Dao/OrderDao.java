package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.CommodityBean;
import wechat_shop.Bean.OrderBean;
import wechat_shop.Bean.OrderCommodityBean;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderDao {

    // 向订单表写入订单信息
    Integer insertOrder(OrderBean orderBean);

    List<OrderBean> getOrderListByUserId(@Param("userId") Integer userId);

//    List<OrderBean> getOrderListByOrderId(@Param("orderId") Integer orderId);

    OrderBean getOrderByOrderId(@Param("orderId") Integer orderId);

    OrderBean selectOrderByOrderId(@Param("orderId")Integer orderId);

    // 更新订单支付时间
    Integer updateOrderPaymentTime(@Param("orderId") Integer orderId, @Param("time") long time);

    // 更新订单完成时间
    Integer updateOrderTransactionTime(@Param("orderId") Integer orderId, @Param("time") long time);


}
