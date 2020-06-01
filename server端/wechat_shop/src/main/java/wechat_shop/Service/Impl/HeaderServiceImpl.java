package wechat_shop.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat_shop.Bean.HeaderBean;
import wechat_shop.Bean.CommodityBean;
import wechat_shop.Bean.UserBean;
import wechat_shop.Dao.CommodityDao;
import wechat_shop.Dao.HeaderDao;
import wechat_shop.Dao.UserDao;
import wechat_shop.Service.HeaderService;
import wechat_shop.Util.Result;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HeaderServiceImpl implements HeaderService {

    @Autowired
    private HeaderDao headerDao;
    @Autowired
    private CommodityDao headerCommodityDao;
    @Autowired
    private UserDao userDao;

    // 获取团长列表
    public List<Map<String,String>> getHeaderList(Map<String, Object> param){
        List<HeaderBean> headListMap = new ArrayList<>();
        // 从团长表获取所有团长信息,根据userId升序排列
        List<HeaderBean> headerList = headerDao.selectAllHeader();

        List<Integer> userIdList = new ArrayList<>();
        for(HeaderBean header: headerList){
            userIdList.add(header.getUserId());
        }
        // 从查询到的团长信息列表，根据每一个团长的userid查询用户信息，,根据userId升序排列
        List<UserBean> userBeanList = userDao.selectUserByHeaderId(userIdList);

        // 对数据进行封装，返回名字、图片、手机号、城市、社区、经度、纬度、团长id

        List<Map<String,String>> headerResultList = new ArrayList<>();
        for(int i=0;i<headerList.size();i++){
            Map<String,String> headerMap = new HashMap<>();
            headerMap.put("nickName",userBeanList.get(i).getUserName());
            headerMap.put("avatarUrl",userBeanList.get(i).getAvatarUrl());
            headerMap.put("phoneNumber",userBeanList.get(i).getPhoneNumber());
            headerMap.put("city",headerList.get(i).getCity());
            headerMap.put("community",headerList.get(i).getCommunity());
            headerMap.put("headerId",headerList.get(i).getHeaderId().toString());
            headerMap.put("longitude",headerList.get(i).getLongitude());
            headerMap.put("latitude",headerList.get(i).getLatitude());
//            headerMap.put("address",userBeanList.get(i).getUserName());
            headerResultList.add(headerMap);
        }
        return headerResultList;
    }

    // 获取团长信息
    public Map<String,String> getHeadInfoByUserId(Integer userId){
        Map<String,String> resultMap = new HashMap<>();

        // 根据用户Id查询用户选择的团长id
        Integer selectHeadId = userDao.selectSelectHeadIdByUserId(userId);

        // 根据团长Id从团长表查团长地址、手机号
        HeaderBean header = headerDao.selectHeadByHeadId(selectHeadId);

        //根据用户Id从用户表查名称和头像url
        UserBean user = userDao.selecctUserByUserId(userId);

        resultMap.put("headerId", header.getHeaderId().toString());
        resultMap.put("headerNickName",user.getUserName());
        resultMap.put("headerPhoneNumber",header.getPhoneNumber());
        resultMap.put("headerAvatarUrl",user.getAvatarUrl());
        resultMap.put("headCity",header.getCity());
        resultMap.put("headcommunity",header.getCommunity());
        return resultMap;
    }

    public Integer headRegiester(Integer userId,String city,String phoneNumber,String community,String latitude,String longitude){
        // 先判断是否为团长
        HeaderBean headerBeans = headerDao.selectHeadByUserId(userId);

        if(headerBeans != null){
            return headerBeans.getHeaderId();
        }

        HeaderBean headerBean = new HeaderBean();
        headerBean.setUserId(userId);
        headerBean.setCity(city);
        headerBean.setPhoneNumber(phoneNumber);
        headerBean.setCommunity(community);
        headerBean.setLatitude(latitude);
        headerBean.setLongitude(longitude);
        headerBean.setRegisteredTime();

        Integer result = headerDao.insertHeadRegiester(headerBean);
//        System.out.println("q"+result);
//        System.out.println("2"+headerBean.getHeaderId());
        return headerBean.getHeaderId();
    }

//    // 获取团长发布的商品
//    public Result getHeadCommodity(Integer headerId){
//        List<CommodityBean> headerCommodityList = headerCommodityDao.getHeadCommodity(headerId);
//        return Result.resultSuccess(headerCommodityList);
//    }
}
