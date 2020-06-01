
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

## 系统设计

  本平台主要分为七大模块：文章模块、购物模块、用户模块、订单模块、商品模块、支付模块、安全模块。各个模块下又分为不同的功能，最终实现社区商城平台的主要功能。
  用户功能：按照平台用户分为普通用户和团长两类
  普通用户允许使用的功能：文章浏览、购物车查看、购物车编辑、加入购物车
  团长用户允许使用的功能：
  非用户功能：软件除了用户可见的功能外，还有非用户功能：权限验证模块。
  权限验证模块主要功能：用户请求权限验证
  <!-- 登录模块：登录状态验证、用户信息验证 -->

### 购物车模块

- #### 查看购物车
  
  用户点击购物车按钮进入购物车页面
  小程序向服务端发送获取购物车请求，购物车进行权限验证，查询用户购物车列表，返回结果
  小程序将返回结果进行处理，在页面上显示

  小程序向UserCartController发送获取用户购物车商品列表请求，UserCartController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用UserCartService查询用户购物车数据，根UserCartMapper访问数据库从数据库查询购物车数据返回给UserCartService，UserCartService将查询到的数据进行处理打包返回给UserCartController，UserCartController返回购物车数据。

  小程序向UserCartController发送获取用户购物车商品列表请求，UserCartController进行用户权限验证判断，验证失败通过resultErrorMessage()返回权限验证失败消息，验证成功调用UserCartService的selectCart()方法查询用户购物车数据，根据用户id请求数据访问UserCartMapper的selectUserCart()方法从数据库查询购物车数据，UserCartService将查询到的数据进行处理打包返回给UserCartController，UserCartController通过resultSuccess()返回购物车数据。
  
- #### 浏览商品加入购物车
  
  在商品列表页面点击商品进入商品详情页面--》
  
  发送商品id--》返回商品id、商品库存数量、商品价格、名称、描述、详情、规格、商品图片、标签

  选择完商品规格，点击加入购物车--》
  
  发送用户id、商品id、规格、数量--》返回加入成功

  小程序向UserCartController发送商品加入购物车请求，UserCartController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用UserCartService，通过UserCartMapper访问数据库将商品信息添加到数据库中，将结果返回给UserCartService，UserCartService对返回的结果进行处理分析返回给UserCartController，UserCartController返回添加结果信息。

- #### 编辑购物车
  
  用户进入购物车页面

  发送用户id--》返回list：商品id、名称、规格、数量、价格、首图url

  用户点击编辑按钮--》

  · 商品删除
  
  发送选中的商品id、用户id--》返回操作结果

  · 商品数量

  送法商品id、用户id、商品数量--》返回操作结果

  · 商品规格

  发送商品id、用户id、商品规格id、商品数量--》返回操作结果

  小程序向UserCartController发送购物车编辑操作请求，UserCartController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用UserCartService判断用户操作，根据用户执行的操作，通过UserCartMapper访问数据库修改数据库中的购物车数据，将结果返回给UserCartService，UserCartService对返回的结果进行处理分析，将结果返回给UserCartController，UserCartController返回购物车编辑结果信息。

### 文章模块

- #### 文章管理

团长进入文章页面
小程序发送获取文章列表请求，服务端进行权限验证，返回该团长所有的文章列表。
团长左滑文章，弹出编辑、删除、显示/隐藏三个按钮。
点击编辑进入文章编辑页面
点击删除，发送删除请求，服务端进行权限验证，返回单个文章操作结果
点击显示/隐藏，发送修改请求，服务端进行权限验证，判断文章是显示状态修改为隐藏状态，反之亦然。返回单个文章操作结果。

  小程序向ArticleController发送文章编辑操作请求，ArticleController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用ArticleService，根据用户执行的操作，通过ArticleMapper访问数据库修改数据库中的文章数据，将结果返回给ArticleService，ArticleService对返回的结果进行处理分析，将结果返回给ArticleController，ArticleController返回文章编辑结果信息。

- #### 发布文章
  
团长进入发布文章页面，点击设置封面按钮，通过微信小程序提供的api读取系统相册选择图片，将图片存入缓存
在标题输入栏输入文章标题,在文章正文文本框内输入markdown格式的文章内容。
如需在正文插入图片，点击添加图片按钮，通过微信小程序提供的api读取系统相册选择图片，将图片上传至服务端UploadFileController，服务端UploadFileController接收到后存入临时文件夹中返回图片url。在正文内自动插入图片url。
编辑完成后点击预览按钮，确认文章无误后点击提交按钮，小程序将封面图片上传至服务端，服务端接收到后存入文章图片文件夹中，返回图片url。微信小程序将封面图片url、标题和正文以json格式传给服务端ArticleController。服务端ArticleController写入完成后返回结果。

  小程序向ArticleController发送发布文章请求，ArticleController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用ArticleService对发送的数据进行分析处理，根ArticleMapper访问数据库将文章数据添加到数据库中，操作结果给ArticleService，ArticleService对结果进行处理返回给ArticleController，ArticleController返回操作结果

- #### 编辑文章
  
  团长进入编辑页面，点击封面图片，通过微信小程序提供的api读取系统相册选择图片，将图片存入缓存
  在标题栏修改文章标题
  在文章正文文本框内输入markdown格式的文章内容，
  如需插入图片，点击添加图片按钮，通过微信小程序提供的api读取系统相册，选择图片，将图片上传至服务端，服务端接收到后存入临时文件夹中，返回图片url。在正文内自动插入添加图片的markdown语法和图片url。
  点击预览按钮，确认文章无误后点击提交按钮，
  小程序将封面图片上传至服务端，服务端接收到后存入文章文件夹中，返回图片url。
  将封面图片url、标题和正文以json格式传给服务端。服务端写入完成后返回结果。

  小程序向ArticleController发送文章编辑请求，ArticleController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用ArticleService对发送的数据进行分析处理，根ArticleMapper访问数据库修改数据库中的文章数据，操作结果给ArticleService，ArticleService对结果进行处理返回给ArticleController，ArticleController返回操作结果
  
- #### 查看文章

用户进入文章列表页面，小程序发送获取文章请求，服务端ArticleController进行权限验证，将用户选择的团长发布的文章和官方发布的文章一起返回。小程序接收到后，显示在列表。用户点击文章封面，小程序弹出文章详情。点击右上角关闭按钮，小程序将文章详情关闭

  小程序向ArticleController发送获取文章信息请求，ArticleController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用ArticleService，通过ArticleMapper访问数据库查询文章数据，将结果返回给ArticleService，ArticleService对返回的结果进行处理分析，将结果返回给ArticleController，ArticleController返回文章信息。

### 用户模块
  
- #### 用户注册

因微信官方要求，不能够强迫用户注册及自动注册。新用户第一次打开微信小程序后，会随机选择团长及随机推荐活动信息。未注册的用户可以点击浏览活动信息、查看随机选择的团长橱窗。新用户点击选择团长按钮、查看购物车、查看个人页面时弹出微信授权提示，用户可以选择允许或拒绝。拒绝无法获取小程序的相关服务。允许后，微信小程序获取用户信息，并发送给服务器。服务器将相关信息写入数据库后完成注册。

  小程序向UserController发送用户注册请求，UserController调用UserService将收到的用户数据进行处理，通过UserMapper从数据库中查询用户信息，判断用户是否已经注册，如果注册返回查询结果，UserController返回用户已注册信息，没有注册通过UserMapper将用户信息写入数据库，将结果返回给UserService，UserService对返回的结果进行处理分析，将结果返回给UserController，UserController返回注册信息。

- #### 注册团长
  
  已注册用户在个人信息页面可以点击注册团长按钮，跳转到注册团长页面。
  填写信息：手机号，地址，团长名称，所在社区，点击提交按钮
  服务端将信息写入数据库，返回结果。
  小程序接收到，进行提示，返回用户页面

  小程序向HeaderController发送注册团长请求，HeaderController调用HeaderService将收到的数据进行处理，通过HeaderMapper从数据库中查询用户信息，判断用户是否已经注册团长，如果注册返回查询结果，HeaderController返回已注册信息，没有注册通过HeadderMapper将用户信息写入数据库，将结果返回给HeaderService，HeaderService对返回的结果进行处理分析，将结果返回给HeaderController，HeaderController返回注册信息。
  
- #### 在线客服
  
  用户点击在线客服按钮，调用微信小程序提供的APi服务建立用户与客服的微信聊天会话。用户可随时在微信聊天聊表里找到微信小程序客服进行沟通。

- #### 用户切换团长及查看旗下商品

  在首页点击团长切换按钮--》

  获取用户经纬度显示在地图上--》

  发送获取附近的团长位置请求--》返回团长id、名称、经度、纬度、社区、手机号

  选择团长点击确定按钮返回首页--》

  发送获取团长旗下商品列表请求--》返回商品id、名称、价格、标签、首图

  小程序向HeaderController发送获取团长信息请求，HeaderController调用HeaderService根据用户所处位置，通过HeaderMapper从数据库中查询用户周边的团长信息，将查询结果返回给HeaderService，HeaderService对返回的结果进行处理分析，将结果返回给HeaderController，HeaderController返回团长数据。
  用户选择团长后，小程序向UserController发送切换团长信息请求，UserController进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用UserService，通过UserMapper操作数据库对用户选择的团长信息进行修改，将结果返回给UserService，UserService对返回的结果进行处理分析，将结果返回给UserController，UserController返回操作结果。

### 订单模块
  
- #### 创建订单
  
  在购物车页面选择要购买的商品，确认商品规格和数量，点击结算按钮，跳转到订单提交页面
  交易方式为线下顾客上门取货
  选择支付方式：线下付款、微信支付
  小程序发送用户id、list：选中的商品id、规格、数量--》
  服务端返回要结算的商品--商品id、名称、规格、数量、价格、首图url
  商品总价、优惠价格、支付价格
  点击付款按钮--》进入付款页面--》付款成功提示--》订单详情页面
  取消付款进入订单详情页面

  小程序向OrderController发送提交订单请求，OrderController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用OrderService对接收到的商品数据调用OrderMapper从数据库查询获取每件商品单价和库存，根据购买数量计算总价，对处理后的数据打包返回给OrderController，OrderController将数据进行处理返回给小程序。
  
- #### 查看订单
  
  用户订单
  在用户页面点击订单按钮，进入订单页面
  小程序发送获取订单请求，服务端进行权限验证，根据用户id返回订单信息
  接收到后，按全部订单、已完成、待付款、待完成四个分类
  点击任意订单进入订单详情页面

  小程序向OrderController发送获取用户订单请求，OrderController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用OrderService对根据用户id调用OrderMapper从数据库查询用户所有订单信息，对处理后的数据打包返回给OrderController，OrderController将数据进行处理返回给小程序。

  团长订单
  进入订单列表页面，按全部订单、已完成、待付款、待完成四个分类
  发送获取订单请求，返回订单列表和订单状态
  点击订单记录，进入订单详情页面

  小程序向OrderController发送获取团长订单请求，OrderController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用OrderService对根据团长id调用OrderMapper从数据库查询团长所有订单信息，对处理后的数据打包返回给OrderController，OrderController将数据进行处理返回给小程序。

  订单详情显示订单数据和状态，根据状态显示不同可点击按钮
  
- #### 编辑订单
  
  用户
进入订单详情页面，发送获取订单详情请求，服务端权限验证，返回订单详情
已完成的订单只能查看，无任何按钮可以点击
待付款订单，点击付款按钮进入付款页面
付款成功返回订单详情
待完成订单，用户点击完成交易，发送请求，权限验证后，修改数据库，返回结果。订单为完成状态

  小程序向OrderController发送编辑用户订单请求，OrderController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用OrderService对订单编辑操作进行判断，根据用户id、订单id调用OrderMapper从数据库修改用户订单信息，对处理后的数据打包返回给OrderController，OrderController将数据进行处理返回给小程序。

  团长
  进入订单列表页面
  左滑显示编辑操作：取消一个操作

  小程序向OrderController发送编辑用户订单请求，OrderController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用OrderService根据团长id、订单id调用OrderMapper从数据库修改订单信息，对处理后的数据打包返回给OrderController，OrderController将数据进行处理返回给小程序。

### 商品模块
  
- #### 发布商品
  
  进入发布商品页面
  分上下两栏，上栏：商品详情，下栏：商品规格、库存、价格
  上栏
  上传商品封面图，展示图。输入商品名、描述、详情
  下栏
  点击添加按钮，输入规格名、上传规格图片，库存数量，单价
  点击发布按钮

  小程序向CommodityController发送发布请求，CommodityController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用CommodityService对接收到的数据进行处理分析，调用CommodityMapper从数据库添加商品信息，将操作结果返回给CommodityController，CommodityController对数据进行处理后返回给小程序。

- #### 商品管理
  
  进入商品管理页面
  发送获取商品请求，服务端进行权限验证，查询商品，返回数据
  列表形式显示，左滑进行编辑操作：上架/下架、删除、编辑
  点击上架/下架按钮，发送请求，修改商品状态
  点击删除按钮，删除
  点击编辑按钮，进入编辑页面

  小程序向CommodityController发送商品操作请求，CommodityController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用CommodityService对接收到的数据判断操作状态，调用CommodityMapper从数据库修改商品信息，将操作结果返回给CommodityController，CommodityController对数据进行处理后返回给小程序。

- #### 商品编辑
  
  进入商品编辑页面，参考商品发布页面

  小程序向CommodityController发送编辑商品信息请求，CommodityController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用CommodityService对接收到的数据进行处理分析，调用CommodityMapper从数据库修改商品信息，将操作结果返回给CommodityController，CommodityController对数据进行处理后返回给小程序。

- #### 查看商品

  进入商品列表页面
  发送获取商品列表请求，服务端进行权限验证，查询商品，返回数据
  点击商品，进入商品详情页面
  发送获取商品详情请求，服务端进行权限验证，查询商品，返回数据

  小程序向CommodityController发送获取商品列表请求，CommodityController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用CommodityService对接收到的数据进行处理分析，调用CommodityMapper从数据库添加商品信息，将操作结果返回给CommodityController，CommodityController对数据进行处理后返回给小程序。
  
<!-- ### 支付模块

- #### 微信支付

  进入微信支付页面
  微信小程序调用微信提供的微信支付API，将结算的商品信息发送给服务端，服务端计算完成待支付金额后返回给微信小程序，微信小程序生成支付页面，用户输入支付密码后完成支付。待服务端确认支付成功后返回支付结果

- #### 线下支付

  由顾客和团长通过线下面对面的形式，现金、支付宝微信面对面扫码、银行转账等任意行为。

### 权限验证模块

- #### 用户请求权限验证
  
  在普通用户或团长发送请求时，需要携带session或token。
  服务端接收到请求后，判断session或token是否过期，如果过期用户需要重新登录。
  根据用户请求类型进行判断该请求信息是否属于此用户，如果属于进行后面的逻辑处理，不属于返回权限错误 -->

<!-- 
登录模块暂不写
### 登录模块

- ####  登录状态验证

  小程序打开后，获取缓存内用户信息。如果缓存内存有用户登录等信息，将用户信息、session、token发送给服务端
  服务端接收到请求后，判断session或token是否过期，如果过期用户需要重新登录
  根据发送的用户信息与服务器内存储的信息进行判断是否相符，如果不相同，用户需要重新登录
  
- #### 用户信息更新

  已注册用户重新登陆时，将用户信息发送给服务端，服务端更新数据库，返回操作结果。 -->
