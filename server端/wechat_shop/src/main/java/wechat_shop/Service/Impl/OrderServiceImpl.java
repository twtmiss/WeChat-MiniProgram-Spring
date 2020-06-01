package wechat_shop.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat_shop.Bean.*;
import wechat_shop.Dao.*;
import wechat_shop.Service.OrderService;
import wechat_shop.Util.BigDecimalUtil;
import wechat_shop.Util.Result;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderCommodityDao orderCommodityDao;
    @Autowired
    private UserDao userDao;

    public Map<String, Object> getOrderByOrderId(Integer orderId){
        OrderBean orderBean = orderDao.selectOrderByOrderId(orderId);
        OrderCommodityBean orderCommodityBean = orderCommodityDao.getOrderCommodityListByOrderId(orderBean.getOrderId());

        Integer orderStatus =0;     // 订单未支付

        if (orderBean.getPaymentTime()!=0){
            if(orderBean.getTransactionTime()!=0){
                // 订单已完成
                orderStatus=2;
            }else {
                // 订单未完成，已支付
                orderStatus = 1;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderBean.getOrderId());
        map.put("orderStatus",orderStatus);
        map.put("userId",orderBean.getUserId());
        map.put("headName",userDao.selectNameByHeaderId(orderCommodityBean.getHeaderId()));
        map.put("userName",userDao.selecctUserByUserId(orderBean.getUserId()).getUserName());
        map.put("meansOfTransaction",orderBean.getMeansOfTransaction());
        map.put("totalPrice",orderBean.getTotalPrice());
        map.put("payPrice",orderBean.getPayPrice());
        map.put("discountedPrices",orderBean.getDiscountedPrices());
        map.put("weChatTransactionNumber",orderBean.getWeChatTransactionNumber());
        map.put("createTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getCreateTime())));
        map.put("paymentTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getPaymentTime())));
        map.put("transactionTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getTransactionTime())));
        map.put("commodityId",orderCommodityBean.getCommodityId());
        map.put("headerId",orderCommodityBean.getHeaderId());
        map.put("specificationId",orderCommodityBean.getSpecificationId());
        map.put("commodityName",orderCommodityBean.getName());
        map.put("specificationImgUrl",orderCommodityBean.getImgUrl());
        map.put("description",orderCommodityBean.getDescription());
        map.put("details",orderCommodityBean.getDetails());
        map.put("specificationName",orderCommodityBean.getSpecificationName());
        map.put("commodityNumber",orderCommodityBean.getCommodityNumber());
        map.put("commodityPrice",orderCommodityBean.getCommodityPrice());

        return map;
    }

    public Integer updateOrderStatus(Integer orderId){
        // 查询订单，根据订单时间判断订单状态
        OrderBean orderBean = orderDao.selectOrderByOrderId(orderId);
        if (orderBean.getPaymentTime()!=0){
            if(orderBean.getTransactionTime()!=0){
                // 订单已完成
                return 0;
            }else {
                // 订单未完成，已支付
                if(orderDao.updateOrderTransactionTime(orderId, new Date().getTime()) > 0){ return 1; }
            }
        }else {
            if(orderDao.updateOrderPaymentTime(orderId, new Date().getTime()) > 0){ return 1; }
        }

        return 0;
    }

    public List<Map<String, Object>> getOrderListByUserId(Integer userId){

        List<Map<String, Object>> resultListMap = new ArrayList<>();
        //查询订单列表
        List<OrderBean> orderBeanList = orderDao.getOrderListByUserId(userId);

        for(OrderBean orderBean: orderBeanList){
            //查询订单商品
            OrderCommodityBean orderCommodityBean = orderCommodityDao.getOrderCommodityListByOrderId(orderBean.getOrderId());
            Integer orderStatus =0;     // 订单未支付

            if (orderBean.getPaymentTime()!=0){
                if(orderBean.getTransactionTime()!=0){
                    // 订单已完成
                    orderStatus=2;
                }else {
                    // 订单未完成，已支付
                    orderStatus = 1;
                }
            }
//            System.out.println(orderCommodityBean.getCommodityId());
            Map<String,Object> map = new HashMap<>();
            map.put("orderId",orderBean.getOrderId());
            map.put("orderStatus",orderStatus);
            map.put("userId",orderBean.getUserId());
            map.put("headName",userDao.selecctUserByUserId(orderBean.getUserId()).getUserName());
            map.put("meansOfTransaction",orderBean.getMeansOfTransaction());
            map.put("totalPrice",orderBean.getTotalPrice());
            map.put("payPrice",orderBean.getPayPrice());
            map.put("discountedPrices",orderBean.getDiscountedPrices());
            map.put("weChatTransactionNumber",orderBean.getWeChatTransactionNumber());
            map.put("createTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getCreateTime())));
            map.put("paymentTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getPaymentTime())));
            map.put("transactionTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getTransactionTime())));
            map.put("commodityId",orderCommodityBean.getCommodityId());
            map.put("headerId",orderCommodityBean.getHeaderId());
            map.put("specificationId",orderCommodityBean.getSpecificationId());
            map.put("commodityName",orderCommodityBean.getName());
            map.put("specificationImgUrl",orderCommodityBean.getImgUrl());
            map.put("description",orderCommodityBean.getDescription());
            map.put("details",orderCommodityBean.getDetails());
            map.put("specificationName",orderCommodityBean.getSpecificationName());
            map.put("commodityNumber",orderCommodityBean.getCommodityNumber());
            map.put("commodityPrice",orderCommodityBean.getCommodityPrice());

            resultListMap.add(map);

        }

        return resultListMap;
    }

    // 一件商品一个订单
    public Integer createOrderByUserId(Integer userId){
        List<CartBean> cartBeanList = cartDao.selectCartByUserId(userId);

        // 创建订单
        for(CartBean cartBean: cartBeanList) {
            if (cartBean.getIsSelect() == 1) {  // 1 选择的商品
                // 查询商品信息
                CommodityBean commodityBean = commodityDao.selectCommodityByCommodityId(cartBean.getCommodityId());
                // 查询商品规格信息
                CommoditySpecificationBean specificationBean = commodityDao.selectSpecificationByCommodityIdAndSpecificationId(cartBean.getCommodityId(), cartBean.getSpecificationId());

                BigDecimal price = new BigDecimal(0);
                // 查询规格价格,计算总价
                BigDecimal commodityPrice = BigDecimalUtil.mul(specificationBean.getSpecificationPrice().doubleValue(),cartBean.getCommodityNumber());
                price = BigDecimalUtil.add(commodityPrice.doubleValue(),price.doubleValue());

                OrderBean orderBean = new OrderBean();
                orderBean.setUserId(userId);
                // 订单价格
                orderBean.setTotalPrice(price);     // 总价
                orderBean.setDiscountedPrices(BigDecimal.valueOf(0));   // 优惠价格
                orderBean.setPayPrice(BigDecimalUtil.sub(price.doubleValue(), orderBean.getDiscountedPrices().doubleValue()));// 支付价格
                // 订单时间
                orderBean.setCreateTime();
//                orderBean.setPaymentTime();
                // 微信交易号
                orderBean.setWeChatTransactionNumber("000000");
                // 向订单表写入订单信息
                if(orderDao.insertOrder(orderBean) !=1){
                    return 0;
                }

                OrderCommodityBean orderCommodityBean = new OrderCommodityBean();
                orderCommodityBean.setOrderId(orderBean.getOrderId());
                orderCommodityBean.setHeaderId(commodityBean.getHeadId());
                orderCommodityBean.setCommodityId(commodityBean.getCommodityId());
                orderCommodityBean.setSpecificationId(specificationBean.getSpecificationId());
                orderCommodityBean.setName(commodityBean.getCommodityName());
                orderCommodityBean.setImgUrl(specificationBean.getSpecificationImgUrl());
                orderCommodityBean.setSpecificationName(specificationBean.getSpecificationName());
                orderCommodityBean.setDescription(commodityBean.getCommodityDescription());
                orderCommodityBean.setDetails(commodityBean.getCommodityDetails());
                orderCommodityBean.setCommodityNumber(cartBean.getCommodityNumber());
                orderCommodityBean.setCommodityPrice(specificationBean.getSpecificationPrice());
                // 向订单商品表插入订单商品信息
                if (orderCommodityDao.insertOrderComodity(orderCommodityBean) !=1){
                    return 0;
                }
                // 将写入了的商品，存入删除列表里
                List<CartBean> cartList = new ArrayList<>();
                CartBean cartBean1 = new CartBean();
                cartBean1.setCartId(cartBean.getCartId());
                cartBean1.setUserId(cartBean.getUserId());
                cartBean1.setHeadId(cartBean.getHeadId());
                cartBean1.setCommodityId(cartBean.getCommodityId());
                cartBean1.setSpecificationId(cartBean.getSpecificationId());
                cartList.add(cartBean1);
                // 从购物车表里删除商品信息
                if (cartDao.deleteCart(cartList) !=1){
                    return 0;
                }
            }
        }

        return 1;
    }

    public List<Map<String, Object>> getOrderListByHeadId(Integer headId){

        List<Map<String, Object>> resultListMap = new ArrayList<>();

        //查询订单商品列表
        List<OrderCommodityBean> orderCommodityBeanList =orderCommodityDao.getOrderCommodityListByHeadId(headId);

        for(OrderCommodityBean orderCommodityBean: orderCommodityBeanList){

            //查询订单
            OrderBean orderBean = orderDao.getOrderByOrderId(orderCommodityBean.getOrderId());

            Integer orderStatus =0;     // 订单未支付

            if (orderBean.getPaymentTime()!=0){
                if(orderBean.getTransactionTime()!=0){
                    // 订单已完成
                    orderStatus=2;
                }else {
                    // 订单未完成，已支付
                    orderStatus = 1;
                }
            }
//            System.out.println(orderCommodityBean.getCommodityId());
            Map<String,Object> map = new HashMap<>();
            map.put("orderId",orderBean.getOrderId());
            map.put("orderStatus",orderStatus);
//            map.put("userId",orderBean.getUserId());
            map.put("userName",userDao.selecctUserByUserId(orderBean.getUserId()).getUserName());
            map.put("meansOfTransaction",orderBean.getMeansOfTransaction());
//            map.put("totalPrice",orderBean.getTotalPrice());
            map.put("payPrice",orderBean.getPayPrice());
//            map.put("discountedPrices",orderBean.getDiscountedPrices());
//            map.put("weChatTransactionNumber",orderBean.getWeChatTransactionNumber());
            map.put("createTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getCreateTime())));
            map.put("paymentTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getPaymentTime())));
            map.put("transactionTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(orderBean.getTransactionTime())));
//            map.put("commodityId",orderCommodityBean.getCommodityId());
//            map.put("headerId",orderCommodityBean.getHeaderId());
//            map.put("specificationId",orderCommodityBean.getSpecificationId());
            map.put("commodityName",orderCommodityBean.getName());
            map.put("specificationImgUrl",orderCommodityBean.getImgUrl());
//            map.put("description",orderCommodityBean.getDescription());
//            map.put("details",orderCommodityBean.getDetails());
            map.put("specificationName",orderCommodityBean.getSpecificationName());
            map.put("commodityNumber",orderCommodityBean.getCommodityNumber());
//            map.put("commodityPrice",orderCommodityBean.getCommodityPrice());

            resultListMap.add(map);

        }

        return resultListMap;
    }

//    多件商品组合为一个订单
//    public Integer createOrderByUserId(Integer userId){
//
//        List<CartBean> cartList = new ArrayList<>();
//        OrderBean orderBean = new OrderBean();
//        BigDecimal price = new BigDecimal(0);
//
//        List<CartBean> cartBeanList = cartDao.selectCartByUserId(userId);
//
//        // 创建订单
//        for(CartBean cartBean: cartBeanList) {
//            if (cartBean.getIsSelect() == 1) {  // 1 选择的商品
//                // 查询商品规格信息
//                CommoditySpecificationBean specificationBean = commodityDao.selectSpecificationByCommodityIdAndSpecificationId(cartBean.getCommodityId(), cartBean.getSpecificationId());
//                // 查询规格价格,计算总价
//                BigDecimal commodityPrice = BigDecimalUtil.mul(specificationBean.getSpecificationPrice().doubleValue(),cartBean.getCommodityNumber());
//                price = BigDecimalUtil.add(commodityPrice.doubleValue(),price.doubleValue());
//            }
//        }
//
//        orderBean.setUserId(userId);
//        // 订单价格
//        orderBean.setTotalPrice(price);     // 总价
//        orderBean.setDiscountedPrices(BigDecimal.valueOf(0));   // 优惠价格
//        orderBean.setPayPrice(BigDecimalUtil.sub(price.doubleValue(), orderBean.getDiscountedPrices().doubleValue()));// 支付价格
//        // 订单时间
//        orderBean.setCreateTime();
//        orderBean.setPaymentTime();
//        // 微信交易号
//        orderBean.setWeChatTransactionNumber("000000");
//        // 向订单表写入订单信息
//        if(orderDao.insertOrder(orderBean) !=1){
//            return 0;
//        }
//
//        for(CartBean cartBean: cartBeanList){
//            if(cartBean.getIsSelect()==1){  // 1 选择的商品
//
//                // 查询商品信息
//                CommodityBean commodityBean = commodityDao.selectCommodityByCommodityId(cartBean.getCommodityId());
//                // 查询商品规格信息
//                CommoditySpecificationBean specificationBean = commodityDao.selectSpecificationByCommodityIdAndSpecificationId(cartBean.getCommodityId(), cartBean.getSpecificationId());
//
//                OrderCommodityBean orderCommodityBean = new OrderCommodityBean();
//                orderCommodityBean.setOrderId(orderBean.getOrderId());
//                orderCommodityBean.setHeaderId(commodityBean.getHeadId());
//                orderCommodityBean.setCommodityId(commodityBean.getCommodityId());
//                orderCommodityBean.setSpecificationId(specificationBean.getSpecificationId());
//                orderCommodityBean.setName(commodityBean.getCommodityName());
//                orderCommodityBean.setImgUrl(specificationBean.getSpecificationImgUrl());
//                orderCommodityBean.setDescription(commodityBean.getCommodityDescription());
//                orderCommodityBean.setDetails(commodityBean.getCommodityDetails());
//                orderCommodityBean.setCommodityNumber(cartBean.getCommodityNumber());
//                orderCommodityBean.setCommodityPrice(specificationBean.getSpecificationPrice());
//
//                // 向订单商品表插入订单商品信息
//                if (orderCommodityDao.insertOrderComodity(orderCommodityBean) !=1){
//                    return 0;
//                }
//                // 将写入了的商品，存入删除列表里
//
//                CartBean cartBean1 = new CartBean();
//                cartBean1.setCartId(cartBean.getCartId());
//                cartBean1.setUserId(cartBean.getUserId());
//                cartBean1.setHeadId(cartBean.getHeadId());
//                cartBean1.setCommodityId(cartBean.getCommodityId());
//                cartBean1.setSpecificationId(cartBean.getSpecificationId());
//
//                cartList.add(cartBean1);
//
//            }
//        }
//
//        // 从购物车表里删除商品信息
//        if (cartDao.deleteCart(cartList) !=1){
//            return 0;
//        }
//        return 1;
//    }
}
