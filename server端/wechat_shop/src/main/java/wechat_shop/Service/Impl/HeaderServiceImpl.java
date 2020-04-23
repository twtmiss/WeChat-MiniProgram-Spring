package wechat_shop.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.HeaderCommodityBean;
import wechat_shop.Dao.HeaderCommodityDao;
import wechat_shop.Dao.HeaderDao;
import wechat_shop.Service.HeaderService;
import wechat_shop.Util.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HeaderServiceImpl implements HeaderService {

    @Autowired
    private HeaderDao headerDao;
    @Autowired
    private HeaderCommodityDao headerCommodityDao;

    // 获取团长列表
    public Result getHeaderList(){
        List<HeaderBean> headListMap = new ArrayList<>();
        // 从团长表获取所有团长信息
        headListMap = headerDao.selectAllHeader();
        return Result.resultSuccess(headListMap);
    }

    // 获取团长发布的商品
    public Result getHeadCommodity(Integer headerId){
        List<HeaderCommodityBean> headerCommodityList = headerCommodityDao.getHeadCommodity(headerId);
        return Result.resultSuccess(headerCommodityList);
    }
}
