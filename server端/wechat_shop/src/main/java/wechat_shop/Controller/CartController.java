package wechat_shop.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wechat_shop.Bean.CartBean;
import wechat_shop.Service.Impl.CartServiceImpl;
import wechat_shop.Util.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    // 将商品加入购物车
    @PostMapping(value = "add")
    public Result addCart(Integer userId, Integer headId, Integer commodityId, Integer specificationId, Integer number){

        boolean resultBoolean= cartService.addCart(userId, headId, commodityId, specificationId, number);
        if(!resultBoolean){
            return Result.resultErrorMessage("添加失败");
        }
        return Result.resultSuccess("添加成功");
    }

    // 查询购物车内商品
    @GetMapping(value = "cart")
    public Result getCart(Integer userId){

        Map<String, Object> result = cartService.getCart(userId);
        
        return Result.resultSuccess(result);
    }

    // 查询购物车内选择的商品
    @GetMapping(value = "select")
    public Result getCartSelectCommodity(Integer userId){

        //  查询购物车内选择的商品
        Map<String, Object> result = cartService.getCartSelectCommodity(userId);

        return Result.resultSuccess(result);
    }

    // 选择购物车内商品
    @PostMapping(value = "update")
    public Result updateCart(Integer userId, Integer headId, Integer commodityId, Integer specificationId, Integer selectStatus,Integer number){

        // 更新购物车选择状态
        Integer result = cartService.updateCartSelect(userId,headId,commodityId,specificationId, selectStatus, number);

        // 查询购物车内商品
//        Map<String, Object> resultMap = cartService.getCart(userId);

        return Result.resultSuccess(result);
    }

    // 删除购物车内商品
    @DeleteMapping(value = "delete")
    public Result deleteCart(@RequestBody Map<String,String> requestMap) {
        Integer userId = Integer.parseInt(requestMap.get("userId"));
        JSONArray jsonObject = JSON.parseArray(requestMap.get("requestMap"));
        List<Map> list = JSON.parseObject(jsonObject.toString(), List.class);
        List<Map<String,Integer>> commodityList = new ArrayList<>();
        for(Map map: list){
            commodityList.add(map);
        }
//        Map<String,Integer> map = commodityList.get(0);
        Integer result = cartService.deleteCart(userId, commodityList);
//        System.out.println(list.get(0).get("specificationId"));
//        System.out.println(requestMap.get("requestMap"));
//        System.out.println(map.get("specificationId"));
        return Result.resultSuccess(result);
    }
}
