package wechat_shop.Service.Impl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat_shop.Bean.CartBean;
import wechat_shop.Bean.CommodityBean;
import wechat_shop.Bean.CommoditySpecificationBean;
import wechat_shop.Dao.CartDao;
import wechat_shop.Dao.CommodityDao;
import wechat_shop.Dao.HeaderDao;
import wechat_shop.Dao.UserDao;
import wechat_shop.Service.CartService;
import wechat_shop.Util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommodityDao commodityDao;

    public Map<String, Object> getCart(Integer userId){
        List<CartBean> cartList = cartDao.selectCartByUserId(userId);
        List<Map<String, Object>> resultList= new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        Map<String, Object> cartMap;
        Map<String, Object> resultMap = new HashMap<>();
        for(CartBean cartBean: cartList){
            cartMap = new HashMap<>();
            // 查询团长名
            cartMap.put("headId", cartBean.getHeadId());
            cartMap.put("headName",userDao.selectNameByHeaderId(cartBean.getHeadId()));
            // 查询商品名
            CommodityBean commodityBean = commodityDao.selectCommodityByCommodityId(cartBean.getCommodityId());
            cartMap.put("commodityId",commodityBean.getCommodityId());
            cartMap.put("commodityName",commodityBean.getCommodityName());
            // 查询规格图片，规格名，规格价格
            CommoditySpecificationBean specificationBean = commodityDao.selectSpecificationByCommodityIdAndSpecificationId(cartBean.getCommodityId(), cartBean.getSpecificationId());
            cartMap.put("specificationImgUrl",specificationBean.getSpecificationImgUrl());
            cartMap.put("specificationName",specificationBean.getSpecificationName());
            cartMap.put("specificationId",specificationBean.getSpecificationId());
            String price = BigDecimalUtil.mul(specificationBean.getSpecificationPrice().doubleValue(),cartBean.getCommodityNumber()).toString();
            cartMap.put("specificationPrice",price);
            // 购买数量
            cartMap.put("number", cartBean.getCommodityNumber());
            cartMap.put("isSelect",cartBean.getIsSelect());
            if(cartBean.getIsSelect()==1){totalPrice = BigDecimalUtil.add(totalPrice.doubleValue(),Double.valueOf(price));}
            resultList.add(cartMap);
        }
        resultMap.put("commodityList", resultList);
        resultMap.put("totalPrice", totalPrice);
        return resultMap;
    }

    public Map<String, Object> getCartSelectCommodity(Integer userId){
        BigDecimal totalPrice = new BigDecimal(0);
        Map<String, Object> cartMap;
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> resultList= new ArrayList<>();

        List<CartBean> cartList = cartDao.selectCartByUserId(userId);

        for(CartBean cartBean: cartList){
            cartMap = new HashMap<>();
            if(cartBean.getIsSelect()==1){  // 1 选择的商品
                // 查询团长名
                cartMap.put("headId", cartBean.getHeadId());
                cartMap.put("headName",userDao.selectNameByHeaderId(cartBean.getHeadId()));
                // 查询商品名
                CommodityBean commodityBean = commodityDao.selectCommodityByCommodityId(cartBean.getCommodityId());
                cartMap.put("commodityId",commodityBean.getCommodityId());
                cartMap.put("commodityName",commodityBean.getCommodityName());
                // 查询规格图片，规格名，规格价格
                CommoditySpecificationBean specificationBean = commodityDao.selectSpecificationByCommodityIdAndSpecificationId(cartBean.getCommodityId(), cartBean.getSpecificationId());
                cartMap.put("specificationImgUrl",specificationBean.getSpecificationImgUrl());
                cartMap.put("specificationName",specificationBean.getSpecificationName());
                cartMap.put("specificationId",specificationBean.getSpecificationId());
                String price = BigDecimalUtil.mul(specificationBean.getSpecificationPrice().doubleValue(),cartBean.getCommodityNumber()).toString();
                cartMap.put("specificationPrice",price);
                // 购买数量
                cartMap.put("number", cartBean.getCommodityNumber());
                cartMap.put("isSelect",cartBean.getIsSelect());
                if(cartBean.getIsSelect()==1){totalPrice = BigDecimalUtil.add(totalPrice.doubleValue(),Double.valueOf(price));}
                resultList.add(cartMap);
            }
        }
        resultMap.put("commodityList", resultList);
        resultMap.put("totalPrice", totalPrice);
        return resultMap;
    }

    public boolean addCart(Integer userId, Integer headId, Integer commodityId, Integer specificationId, Integer number){

        // 根据用户Id获取购物车Id
        Integer cartId = userDao.selectCartIdByUserId(userId);

        // 查询购物车内是否已存在该商品
        Integer commodityCartNum = cartDao.selectCommodityCart(cartId, userId, headId, commodityId, specificationId);
        if(commodityCartNum>0){
            // 说明已存在，将数量累加。
            Integer commodityNumber = cartDao.selectCommodityNumber(cartId, userId, headId, commodityId, specificationId);
            if(cartDao.updateCartCommodityNumber(cartId, userId, headId, commodityId, specificationId, commodityNumber+number) == 1){
                return true;
            }
        }else{
            // 向购物车表插入商品信息
            Integer result = cartDao.insertCart(cartId, userId, headId, commodityId, specificationId, number);
            if(result>0){
                return true;
            }
        }

        return false;
    }

    public Integer updateCartSelect(Integer userId, Integer headId, Integer commodityId, Integer specificationId, Integer selectStatus, Integer number){

        // 根据用户Id获取购物车Id
        Integer cartId = userDao.selectCartIdByUserId(userId);
//        System.out.println("cartId"+cartId);

        // 查询购物车中的商品选中状态

        Integer commoditySelectStatus = cartDao.selectSelectStatus(cartId,userId,headId,commodityId,specificationId);
//        System.out.println("selectStatus"+commoditySelectStatus);
        // 更新购物车
        // 判断是否要更新商品选中状态，selectStatus 为0不更新。
        Integer result = cartDao.updateCart(cartId,userId,headId,commodityId,specificationId,commoditySelectStatus,selectStatus, number);

        return result;
    }

    public Integer deleteCart(Integer userId, List<Map<String,Integer>> commodityList){

        // 根据用户Id获取购物车Id
        Integer cartId = userDao.selectCartIdByUserId(userId);
        List<CartBean> cartBeanList = new ArrayList<>();
        for(Map<String,Integer> map: commodityList){
            CartBean cartBean = new CartBean();
            cartBean.setCartId(cartId);
            cartBean.setUserId(userId);
            cartBean.setHeadId(map.get("headId"));
            cartBean.setCommodityId(map.get("commodityId"));
            cartBean.setSpecificationId(map.get("specificationId"));

            cartBeanList.add(cartBean);
        }

        Integer result = cartDao.deleteCart(cartBeanList);

        return result;
    }
}
