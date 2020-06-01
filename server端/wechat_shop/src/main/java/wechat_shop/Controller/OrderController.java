package wechat_shop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wechat_shop.Service.CartService;
import wechat_shop.Service.OrderService;
import wechat_shop.Util.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单
    @PostMapping(value = "create")
    public Result CreateOrder(Integer userId){

        //  创建订单
        Integer result = orderService.createOrderByUserId(userId);

        return Result.resultSuccess(result);
    }

    // 获取订单列表
    @GetMapping(value = "list")
    public Result getOrderList(Integer userId,Integer headId){

        List<Map<String, Object>> resultListMap = new ArrayList<>();
        if(headId==0){
            //  获取订单列表
            resultListMap = orderService.getOrderListByUserId(userId);
        }else {

            //  获取团长的订单列表
            resultListMap = orderService.getOrderListByHeadId(headId);
        }


        return Result.resultSuccess(resultListMap);
    }

    // 获取订单详情
    @GetMapping(value = "order")
    public Result getOrder(Integer orderId){

        //  获取订单列表
        Map<String, Object> resultMap = orderService.getOrderByOrderId(orderId);

        return Result.resultSuccess(resultMap);
    }

    // 更新订单状态
    @PostMapping(value = "update")
    public Result updateOrderStatus(Integer orderId){

        //  获取订单列表
        Integer result = orderService.updateOrderStatus(orderId);

        return Result.resultSuccess(result);
    }
}
