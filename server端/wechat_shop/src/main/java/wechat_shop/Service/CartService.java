package wechat_shop.Service;

import wechat_shop.Bean.CartBean;

import java.util.List;
import java.util.Map;

public interface CartService {

    boolean addCart(Integer userId, Integer headId, Integer commodityId, Integer specificationId, Integer number);

    Map<String, Object> getCart(Integer userId);

    Integer updateCartSelect(Integer userId, Integer headId, Integer commodityId, Integer specificationId, Integer selectStatus, Integer number);

    Integer deleteCart(Integer userId, List<Map<String,Integer>> commodityList);

    // 查询购物车内选择的商品
    Map<String, Object> getCartSelectCommodity(Integer userId);

}
