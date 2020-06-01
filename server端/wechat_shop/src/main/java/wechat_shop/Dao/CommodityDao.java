package wechat_shop.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wechat_shop.Bean.CommoditySpecificationBean;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.CommodityBean;

import java.util.List;

@Mapper
@Repository
public interface CommodityDao {

    // 获取团长发布的商品
    List<CommodityBean> selectCommodityListByHeadId(@Param("headId") Integer headId);

    CommodityBean selectCommodityByCommodityId(@Param("commodityId")Integer commodityId);

    // 根据商品Id从规格表查询规格列表
    List<CommoditySpecificationBean> selectSpecificationListByCommodityId(@Param("commodityId") Integer commodityId);

    // 根据商品Id从规格表查询价格最低的规格
    String selectCommodityMinPriceByCommodityId(@Param("commodityId") Integer commodityId);

    // 根据商品Id查询商品名称
    String selectCommodityNameByCommodityId(@Param("commodityId") Integer commodityId);

    // 根据商品Id从规格表查询规格
    CommoditySpecificationBean selectSpecificationByCommodityIdAndSpecificationId(@Param("commodityId") Integer commodityId,@Param("specificationId") Integer specificationId);

//    Integer updateCommodityStatusByCommodityId(@Param("commodityId") Integer commodityId,@Param("isShow") Integer isShow);

//    Integer updateArticleByArticleId(@Param("articleId") Integer articleId, @Param("articleMarkdown") String articleMarkdown,@Param("coverImgUrl")String coverImgUrl);

    Integer deleteCommodityByCommodityId(@Param("commodityId") Integer commodityId);

}
