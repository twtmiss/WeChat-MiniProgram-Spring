package wechat_shop.Service;

import org.springframework.web.bind.annotation.GetMapping;
import wechat_shop.Util.Result;

import java.util.Map;

public interface HeaderService {

    // 获取团长列表
    public Result getHeaderList();

    // 获取团长发布的商品
    public Result getHeadCommodity(Integer headerId);
}
