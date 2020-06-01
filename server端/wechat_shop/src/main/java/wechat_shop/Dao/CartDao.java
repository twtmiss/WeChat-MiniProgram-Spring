package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.CartBean;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CartDao {

    // 根据用户Id查询购物车内容
    List<CartBean> selectCartByUserId(@Param("userId")Integer userId);

    // 查询购物车内是否已存在该商品
    Integer selectCommodityCart(@Param("cartId")Integer cartId, @Param("userId")Integer userId, @Param("headId")Integer headId, @Param("commodityId")Integer commodityId, @Param("specificationId")Integer specificationId);

    // 查询购物车内该商品的数量
    Integer selectCommodityNumber(@Param("cartId")Integer cartId, @Param("userId")Integer userId, @Param("headId")Integer headId, @Param("commodityId")Integer commodityId, @Param("specificationId")Integer specificationId);

    // 向购物车表插入商品信息
    Integer insertCart(@Param("cartId")Integer cartId, @Param("userId")Integer userId, @Param("headId")Integer headId, @Param("commodityId")Integer commodityId, @Param("specificationId")Integer specificationId, @Param("commodityNumber")Integer number);

    // 更新购物车商品选择状态
    Integer updateCart(@Param("cartId")Integer cartId, @Param("userId")Integer userId, @Param("headId")Integer headId, @Param("commodityId")Integer commodityId, @Param("specificationId")Integer specificationId,@Param("commoditySelectStatus") Integer commoditySelectStatus,@Param("selectStatus")Integer selectStatus, @Param("number")Integer number);

    // 向购物车表插入商品信息
    Integer updateCartCommodityNumber(@Param("cartId")Integer cartId, @Param("userId")Integer userId, @Param("headId")Integer headId, @Param("commodityId")Integer commodityId, @Param("specificationId")Integer specificationId, @Param("commodityNumber")Integer number);

    // 查询购物车中的商品选中状态
    Integer selectSelectStatus(@Param("cartId")Integer cartId, @Param("userId")Integer userId, @Param("headId")Integer headId, @Param("commodityId")Integer commodityId, @Param("specificationId")Integer specificationId);

    Integer deleteCart(@Param("cartList")List<CartBean> cartList);



}
