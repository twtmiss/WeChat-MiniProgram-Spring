package wechat_shop.Service;

import java.util.List;
import java.util.Map;

public interface CommodityService {

    // 根据团长Id获取团长发布的商品
    List<Map<String, Object>> getCommodityListByHeadId(Integer headId);

    // 根据商品Id获取商品详细信息
    Map<String, Object> getCommodityByCommodityId(Integer commodityId);

    Integer UpdateCommodityStatusByCommodityId(Integer commodityId);

//    Integer UpdateArticleByArticleId(Integer articleId, String articleMarkdown,String coverImgUrl);

    Integer DeleteCommodityByCommodityId(Integer commodityId);
}
