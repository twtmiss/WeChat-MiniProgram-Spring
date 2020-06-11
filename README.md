# 基于SpringBoot实现的社区商城微信小程序

## 项目背景

**主要目标**：为各个社区内有意愿做小买卖的业主提供一个易上手、能够满足多种需求、面向同城同区域交易的一个社区商城平台。

## 系统功能

### 软件版本

- Spring Boot版本：2.2.2
- Java版本：1.8.0_202
- 微信小程序开发者工具版本：1.02.1911180
- Mysql数据库版本：8.0.15
- maven版本：3.5.4
- Python版本：3.7.3

因为有涉及到上传图片并获取的功能，这一部分功能解决方案暂时为使用SpringBoot上传文件到静态资源目录，使用Python Django完成静态资源的获取。



## 效果图

![小程序主页页面](http://pic.misslovecloud.cn/picgo/wechatdevtools_XfBxl7rIqS.png)

![小程序文章页面](http://pic.misslovecloud.cn/picgo/wechatdevtools_neBlDtofow.png)

![购物车页面](http://pic.misslovecloud.cn/picgo/wechatdevtools_QyqDn6t7G4.png)

![文章管理页面](http://pic.misslovecloud.cn/picgo/wechatdevtools_41GQX4FxEJ.png)

![商品编辑页面](http://pic.misslovecloud.cn/picgo/wechatdevtools_6ypwxr1TRl.png)


<!-- 
# 数据库设计

## 表

- user 用户表
  字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  user_id | int(20) | 否 | 用户id
  user_name | varchar(255) | 否 | 用户名
  user_avatar_url | varchar(255) | 否 | 用户头像
  user_city | varchar(255) | 否 | 城市
  user_phone_num | varchar(15) | 否 | 手机号
  user_register_time | varchar(15) | 否 | 用户注册时间

- user_address 用户收货地址表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  user_address_id | int(20) | 否 | 地址id
  user_id | int(20) | 否 | 用户id
  user_name | varchar(255) | 否 | 用户名
  user_phone_num | varchar(15) | 否 | 收货人手机号
  user_province | varchar(255) | 否 | 省
  user_city | varchar(255) | 否 | 城市
  user_region | varchar(255) | 否 | 区域
  user_street | varchar(255) | 否 | 街道
  user_address | varchar(255) | 否 | 详细地址
  user_default_address | varchar(255) | 否 | 默认地址，0否 1是
  
- shopping_cart 购物车表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  cart_id | int(20) | 否 | 购物车id
  user_id | int(20) | 否 | 用户id
  header_id | int(20) | 否 | 团长id
  commodity_id | int(20) | 否 | 商品id
  commodity_number | int(20) | 否 | 购买数量

- user_order 订单表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  order_id | int(20) | 否 | 订单id
  commodity_id | int(20) | 否 | 商品id
  means_of_transaction | varchar(255) | 否 | 交易方式
  total_price | varchar(15) | 否 | 总价
  pay_price | varchar(255) | 否 | 支付价格
  discounted_prices | varchar(255) | 否 | 优惠价格
  wechat_transaction_number | varchar(255) | 否 | 微信交易号
  create_time | varchar(255) | 否 | 创建时间
  payment_time | varchar(255) | 否 | 付款时间
  transaction_time | varchar(255) | 否 | 成交时间

- user_order_commodity 订单商品表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  order_id | int(20) | 否 | 订单id
  commodity_id | int(20) | 否 | 商品id
  header_id | varchar(255) | 否 | 团长id
  name | varchar(15) | 否 | 名称
  imgurl | varchar(255) | 否 | 商品图片
  description | varchar(255) | 否 | 描述
  details | varchar(255) | 否 | 详情
  specification | varchar(255) | 否 | 规格
  commodity_number | varchar(255) | 否 | COLLATE
  commodity_price | varchar(255) | 否 | 商品价格

- header 团长表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  header_id | int(20) | 否 | 团长id
  header_city | int(20) | 否 | 城市
  header_community | varchar(255) | 否 | 社区
  header_phone_number | varchar(15) | 否 | 手机号
  header_registered_time | varchar(255) | 否 | 注册时间
  header_longitude | varchar(255) | 否 | 经度
  header_latitude | varchar(255) | 否 | 纬度

- commodity 团长商品表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  header_commodity_id | int(20) | 否 | 商品id
  header_id | int(20) | 否 | 团长id
  header_commodity_name | varchar(255) | 否 | 名称
  header_commodity_imgurl | varchar(15) | 否 | 商品图片
  header_commodity_description | varchar(255) | 否 | 描述
  header_commodity_details | varchar(255) | 否 | 详情
  header_commodity_specification | varchar(255) | 否 | 规格
  header_commodity_tag | varchar(255) | 否 | 标签
  header_commodity_number | varchar(255) | 否 | 商品库存数量
  header_commodity_price | varchar(255) | 否 | 商品价格

- header_commodity_specification 商品规格表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  commodity_specification_id | int(20) | 否 | 商品规格id
  header_commodity_id | int(20) | 否 | 商品id
  commodity_specification_name | varchar(255) | 否 | 规格名
  commodity_specification_details | varchar(255) | 否 | 规格详情
  
- group_buy 团购表

- article 文章表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  article_id | int(20) | 否 | 文章id
  cover_img_url | varchar(255) | 否 | 预览图url
  article_markdown | longtext | 否 | 文章markdown文本
  is_show | varchar(15) | 否 | 是否显示
  create_type | int(1) | 否 | 类型，1 文章
