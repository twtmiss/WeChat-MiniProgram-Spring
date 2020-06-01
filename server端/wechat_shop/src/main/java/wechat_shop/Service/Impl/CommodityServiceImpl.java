package wechat_shop.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat_shop.Bean.CommodityBean;
import wechat_shop.Bean.CommoditySpecificationBean;
import wechat_shop.Dao.CommodityDao;
import wechat_shop.Service.CommodityService;
import java.util.*;


@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityDao commodityDao;

    public List<Map<String, Object>> getCommodityListByHeadId(Integer headId){
        List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();

        // 根据团长Id从商品表查询商品列表
        List<CommodityBean> commodityBeanList = commodityDao.selectCommodityListByHeadId(headId);

        Map<String,Object> commodityMap;
        for(CommodityBean commodity: commodityBeanList){
            commodityMap = new HashMap<>();
            // 放入商品信息
            commodityMap.put("commodityId", commodity.getCommodityId());
            commodityMap.put("commodityName", commodity.getCommodityName());
//            commodityMap.put("commodityDescription", commodity.getCommodityDescription());
//            commodityMap.put("commodityDetails", Arrays.asList(commodity.getCommodityDetails().split(",")));
//            commodityMap.put("commodityImgUrl", Arrays.asList(commodity.getCommodityImgUrl().split(",")));
            commodityMap.put("commodityImgUrl", commodity.getCommodityImgUrl().split(",")[0]);
            // 根据商品Id从规格表查询价格最低的规格
            Double price = Double.valueOf(commodityDao.selectCommodityMinPriceByCommodityId(commodity.getCommodityId()));
            commodityMap.put("commodityPrice", String.format("%.2f",price));
//            List<CommoditySpecificationBean> specificationBeanList = commodityDao.selectSpecificationByCommodityId(commodity.getCommodityId());
//            commodityMap.put("specification", specificationBeanList);
            resultMap.add(commodityMap);
        }
        return resultMap;
    }

    public Map<String, Object> getCommodityByCommodityId(Integer commodityId){
        Map<String, Object> resultMap = new HashMap<>();

        // 根据团长Id从商品表查询商品列表
        CommodityBean commodityBean = commodityDao.selectCommodityByCommodityId(commodityId);
        // 根据商品Id从规格表查规格
        List<CommoditySpecificationBean> specificationBeanList = commodityDao.selectSpecificationListByCommodityId(commodityId);
        // 放入商品信息
        resultMap.put("commodityId", commodityBean.getCommodityId());
        resultMap.put("headId", commodityBean.getHeadId());
        resultMap.put("commodityName", commodityBean.getCommodityName());
        resultMap.put("commodityDescription", commodityBean.getCommodityDescription());
        resultMap.put("commodityDetails", Arrays.asList(commodityBean.getCommodityDetails().split(",")));
        resultMap.put("commodityImgUrl", Arrays.asList(commodityBean.getCommodityImgUrl().split(",")));
        resultMap.put("specification", specificationBeanList);

        return resultMap;
    }

    public Integer UpdateCommodityStatusByCommodityId(Integer commodityId){
        CommodityBean commodityBean = commodityDao.selectCommodityByCommodityId(commodityId);

//        Integer result = commodityDao.updateCommodityStatusByCommodityId(commodityId, commodityBean.);
        return 0;
    }

    public Integer DeleteCommodityByCommodityId(Integer commodityId){
        Integer result = commodityDao.deleteCommodityByCommodityId(commodityId);
        return 0;
    }
}
