package wechat_shop.Service;

import org.springframework.web.bind.annotation.GetMapping;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.UserBean;
import wechat_shop.Util.Result;

import java.util.List;
import java.util.Map;

public interface HeaderService {

    // 获取团长列表
    List<Map<String,String>> getHeaderList(Map<String, Object> param);

    // 获取团长信息
    Map<String,String> getHeadInfoByUserId(Integer userId);

    Integer headRegiester(Integer userId,String city,String phoneNumber,String community,String latitude,String longitude);
}
