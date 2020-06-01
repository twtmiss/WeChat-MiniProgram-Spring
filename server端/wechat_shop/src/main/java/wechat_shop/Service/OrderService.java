package wechat_shop.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface OrderService {

    Integer createOrderByUserId(Integer userId);

    List<Map<String, Object>> getOrderListByUserId(Integer userId);

    List<Map<String, Object>> getOrderListByHeadId(Integer headId);

    Integer updateOrderStatus(Integer orderId);

    Map<String, Object> getOrderByOrderId(Integer orderId);
}
