
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

- group_buy 团购表

- article 文章表
   字段名 | 字段类型 | 是否可为空 | 备注
  :-: | :-: | :-: | :-:
  article_id | int(20) | 否 | 文章id
  cover_img_url | varchar(255) | 否 | 预览图url
  article_markdown | longtext | 否 | 文章markdown文本
  is_show | varchar(15) | 否 | 是否显示
  create_type | int(1) | 否 | 类型，1 文章

  
## 流程

- ### 用户选择团长及旗下商品

  在首页点击团长切换按钮--》

  获取用户经纬度显示在地图上--》

  发送获取附近的团长位置请求--》返回团长id、名称、经度、纬度、社区、手机号

  选择团长点击确定按钮返回首页--》

  发送获取团长旗下商品列表请求--》返回商品id、名称、价格、标签、首图
  
- ### 浏览商品加入购物车
  
  在商品列表页面点击商品进入商品详情页面--》
  
  发送商品id--》返回商品id、商品库存数量、商品价格、名称、描述、详情、规格、商品图片、标签

  选择完商品规格，点击加入购物车--》
  
  发送用户id、商品id、规格、数量--》返回加入成功

- ### 编辑购物车
  
  用户进入购物车页面

  发送用户id--》返回list：商品id、名称、规格、数量、价格、首图url

  用户点击编辑按钮--》

  · 商品删除
  
  发送选中的商品id、用户id--》返回操作结果

  · 商品数量

  送法商品id、用户id、商品数量--》返回操作结果

  · 商品规格

  发送商品id、用户id、商品规格id、商品数量--》返回操作结果

- ### 购物车结算、创建订单、支付

  用户进入购物车页面

  发送用户id--》返回list：商品id、名称、规格、数量、价格、首图url

  点击结算按钮进入订单结算页面

  发送用户id、list：选中的商品id、规格、数量--》

  返回

  · 用户默认的收货地址--地址id、用户id、收货人姓名、手机号、省、市、县、街道、详细地址

  · 要结算的商品--商品id、名称、规格、数量、价格、首图url

  · 订单总价、优惠价格、

  点击付款按钮--》进入付款页面--》付款成功提示--》订单详情页面

- ### 查看订单情况

- ### 发布首页文章

- ### 注册团长

- ### 发布的商品管理

- ### 发布的文章管理
