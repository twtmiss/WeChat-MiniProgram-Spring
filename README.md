# 基于SpringBoot实现的社区商城微信小程序

<!-- ## 4 系统设计

### 4.1 系统总体设计

主要分为六大模块：文章模块、用户模块、购物车模块、订单模块、商品模块、安全模块。各个模块下又分为不同的功能，最终实现社区商城的主要功能。总体功能结构如图4-1所示：
图4-1 总体结构功能图
按照平台用户行为将用户分为普通用户和团长两类。
普通用户允许使用的功能：查看文章、查看购物车、编辑购物车、商品加入购物车、查看订单、创建订单、编辑订单、查看商品。
团长用户允许使用的功能：文章管理、发布文章、发布商品、商品管理。

### 4.2 各功能模块详细设计

通过对软件的每个模块的功能设计及逻辑处理，通过对部分或核心的功能辅以时序图进行设计分析。以下每个模块所涉及到的Service类主要负责逻辑处理，Mapper类主要负责数据访问， Controller类负责请求数据分析，同时调用相关Service类完成数据处理。详情如图4-2 总类图所示：
图4-2 总类图
### 4.2.1 文章模块
主要负责显示平台正在进行的活动或是通知等。例如：2月份平台进行购物返现活动或是显示通知，如3月4日平台于凌晨进行技术升级将停止服务4个小时等。每个文章或通知主要以图片为显示内容，辅以文字进行主题和关键字的说明强调。每个文章被点击应当有流畅的动画切换效果，文章详情页面文字与图片的格式应注意统一、互不干扰、简洁清晰的展示效果。页面详情可添加参与文章的商品链接，可以通过商品链接跳转至商品页面。
1. 文章管理
团长进入文章页面，微信小程序发送获取文章列表请求，服务端ArticleController进行权限验证，返回该团长所有的文章列表。在列表内可以左滑文章，弹出编辑、删除、显示/隐藏三个按钮。点击任意按钮，发送相关操作请求，服务端ArticleController接收到请求首先进行权限验证，对数据库内文章数据修改完成后返回操作结果。文章管理功能如图4-3 文章管理时序图所示：
图4-3 文章管理时序图
2. 发布文章
团长进入发布文章页面，点击设置封面按钮，通过微信小程序提供的api读取系统相册选择图片，将图片存入微信小程序的缓存中。在页面内的文章标题栏输入文章标题,在正文内容的文本框内输入markdown格式的文本。
如需在正文插入图片，点击添加图片按钮，通过微信小程序提供的api读取系统相册选择图片，将图片上传至服务端UploadFileController，服务端UploadFileController接收到后存入临时文件夹中返回图片url。在正文内自动插入图片url。
编辑完成后点击预览按钮，确认文章无误后点击提交按钮，小程序将封面图片上传至服务端，服务端收到小程序发来的请求和数据后，将接收到的图片文件存入相应文件夹中，向微信小程序返回图片url地址。微信小程序将封面图片url、标题和正文以json格式传给服务端ArticleController。服务端ArticleController写入完成后返回结果。发布文章功能如图4-4 发布文章时序图所示：
图4-4 发布文章时序图
3. 查看文章
用户进入文章列表页面，小程序发送获取文章请求，服务端ArticleController进行权限验证，将用户选择的团长发布的文章和官方发布的文章一起返回。小程序接收到后，显示在列表。用户文章列表页面任意点击文章的封面图片，微信小程序在当前页面显示出文章的详情内容。用户浏览完毕后点击关闭按钮，关闭文章详情内容。查看文章功能如图4-5 查看文章时序图所示：
图4-5 查看文章时序图

### 4.2.2 用户模块

主要是实现新用户的注册和已注册的用户在微信小程序上重新登录、记录用户购买的商品信息、对用户个人信息的修改及用户选择的团长进行管理等功能。用户主动选择注册后，在用户微信的本地缓存存入用户的登陆信息，方便下次无需登陆即可使用。用户可以查看个人的购买记录，购买的商品信息等。如果用户是注册在案的团长，可通过团长的个人管理页面管理发布的商品信息，已被购买的商品订单、处理用户的交易询问等信息。 
1. 用户注册
因微信官方要求，不能够强迫用户注册及自动注册。新用户在点击切换团长按钮、在购物车页面、个人页面点击任意按钮时会弹出需要用户注册提示，点击注册按钮弹出微信授权获取用户信息提示，用户可以选择允许或拒绝。拒绝无法获取小程序的相关服务。用户选择允许小程序获取权限后，微信小程序将通过微信提供的API获取用户的信息，封装后发送给服务器UserController。UserController将数据传给UserService，调用UserMapper将相关信息写入数据库后完成注册。用户注册功能如图4-6用户注册时序图所示：
图4-6 用户注册时序图
2. 团长注册
已经在平台上注册过的用户在微信小程序的个人页面通过点击相关按钮注册为团长。用户在注册团长的页面内根据提示填写相关信息并上传照片，确认填写无误后点击提交按钮注册为团长。
3. 切换团长
已经在平台上注册过的用户在点击切换团长按钮跳转至切换团长页面。微信小程序经过用户同意后获取用户地理位置信息，将用户当前所处的经纬度发送给服务端HeaderController，HeaderController调用HeaderService根据经纬度从数据库查询当前位置周边的团长信息，返回给微信小程序。微信小程序接收到返回的数据后，调用微信APP提供的地图API在页面上显示地图组件，同时在地图上显示用户当前的所在位置和用户周边的团长信息。用户点击地图上的团长查看团长的相关信息，点击确定后，微信小程序向服务端UserController发送选择的团长。切换团长功能如图4-7 团长选择时序图所示：
图4-7 切换团长时序图

### 4.2.3 购物车模块

主要是实现浏览商品、选择商品不同的规格信息、每种商品规格的购买数量、查看商品详细信息等功能。商品列表应排列有序，跳转至商品详情页面时也许有流畅的动画切换效果，用户在商品详情界面能够浏览商品信息及商品的宣传文字图片和商品规格，点击相应按钮选择要购买的商品规格信息和选择的商品规格购买数量，选择完成后将相关信息添加至用户购物车或点击结算按钮直接跳转到商品结算页面。同时能够将商品收藏至用户的个人商品收藏列表中。
1. 查看购物车
用户点击购物车按钮进入购物车页面，微信小程序向UserCartController发送获取用户购物车商品列表请求，UserCartController进行用户权限验证判断，验证失败通过resultErrorMessage()返回权限验证失败消息，验证成功调用UserCartService的SelectCart()方法查询用户购物车数据，根据用户id请求数据访问UserCartMapper的selectUserCart()方法从数据库查询购物车数据，UserCartService将查询到的数据进行处理打包返回给UserCartController，UserCartController通过()返回购物车数据。查看购物车功能如图4-8查看购物车时序图所示：
图4-8查看购物车时序图
2. 商品加入购物车
用户在商品列表页面点击商品缩略图进入商品的详情页面，选择完商品的规格和相应的购买数量后，点击下方的加入购物车按钮，将商品加入到购物车中。微信小程序向UserCartController发送商品加入购物车请求，用户权限验证判断，验证失败返回权限验证失败消息，验证成功UserCartService，通过UserCartMapper将商品信息添加到数据库中，返回操作结果。商品加入购物车功能如图4-9 商品加入购物车时序图所示：
图4-9 商品加入购物车时序图
3. 编辑购物车
用户在购物车页面点击编辑按钮，选中要从购物车中删除的商品，点击删除按钮，微信小程序向UserCartController发送删除商品操作请求，进行用户权限验证判断，验证失败返回权限验证失败消息，验证成功调用UserCartService从购物车中查询要删除的商品数据，调用UserCartMapper从数据库中删除要删除的购物车商品数据，UserCartService对操作结果进行处理分析返回给UserCartController，UserCartController返回删除后的购物车数据。编辑购物车功能如图4-10 编辑购物车时序图所示：
图4-10 编辑购物车时序图

### 4.2.4 订单模块

用户在订单确认页面确认订单无误后，将订单发送给服务端，接收到返回的数据后，调用微信提供的微信支付API，确认，向微信平台发送相关的支付请求，对用户的支付结果进行相关处理。
1. 创建订单
在购物车页面选择要购买的商品，确认要购买的商品规格和数量，进入订单确认页面。交易方式为线下顾客上门取货。支付方式为微信支付。微信小程序向OrderController发送提交订单请求，OrderController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用OrderService对接收到的商品数据调用OrderMapper从数据库查询获取每件商品单价和库存，根据购买数量计算总价，对处理后的数据打包返回给OrderController，OrderController返回订单信息。创建订单功能如图4-11 创建订单时序图所示：
图4-11 创建订单时序图
2. 查看订单
用户在个人页面点击订单按钮，跳转到订单页面。微信小程序向OrderController发送获取用户订单请求，进行权限验证后，调用OrderService对根据用户id调用OrderMapper从数据库查询用户所有订单信息返回订单信息。微信小程序接收到后，按全部订单、已完成、待付款、待完成四个分类。点击订单跳转到该订单的详情页面。
3. 编辑订单
用户订单分为已完成、待付款和未完成三个状态。对于已完成状态的订单用户只能查看不能进行操作。对于待付款状态的订单，用户点击付款按钮进入订单的付款页面。付款成功返回订单详情页面。对于待完成状态的订单，当用户与团长完成交易后点击完成按钮，将订单状态修改为完成状态。
进入订单详情页面，发送订单操作请求，服务端OrderController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用OrderService对订单编辑操作进行判断，根据用户id、订单id调用OrderMapper从数据库修改用户订单信息返回操作结果。编辑订单如图4-12 编辑订单时序图所示：
图4-12 编辑订单时序图
 
### 4.2.5 商品模块
1. 发布商品
微信小程序向CommodityController发送发布请求，CommodityController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用CommodityService对接收到的数据进行处理分析，调用CommodityMapper从数据库添加商品信息，将操作结果返回给CommodityController，CommodityController对数据进行处理后返回给小程序。发布商品如图4-13发布商品时序图所示：
图4-13 发布商品时序图
2. 商品管理
进入商品管理页面，发送获取商品请求，服务端进行权限验证，查询商品，返回数据。列表形式显示，左滑进行编辑操作：上架/下架、删除、编辑。点击上架/下架按钮，发送请求，修改商品状态；点击删除按钮，删除；点击编辑按钮，进入编辑页面。
微信小程序向CommodityController发送商品操作请求，CommodityController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用CommodityService对接收到的数据判断操作状态，调用CommodityMapper从数据库修改商品信息，将操作结果返回给CommodityController，CommodityController对数据进行处理后返回给小程序。编辑商品如图4-14编辑商品时序图所示：
图4-14 编辑商品时序图
3. 查看商品
微信小程序向CommodityController发送获取商品列表请求，CommodityController接收到请求和数据，进行权限验证判断，验证失败返回权限验证失败消息，验证成功调用CommodityService对接收到的数据进行处理分析，调用CommodityMapper从数据库添加商品信息，将操作结果返回给CommodityController，CommodityController对数据进行处理后返回给小程序。
4.3 数据库设计
4.3.1 概念设计
1. 文章实体属性
文章实体包括：文章编号、封面图片url、文章内容、是否显示、文章类型等属性，文章实体属性图如图4-15所示：
图4-15 文章实体属性图
2. 用户实体属性
用户实体包括：用户编号、用户昵称、头像url、所在城市、手机号、注册时间等属性，用户实体属性图如图4-16所示：
图4-16 用户实体属性图
3. 团长实体属性
团长实体包括：团长编号、用户编号、所在城市、所在社区、手机号、注册时间、所处经度、所处纬度等属性，团长实体属性图如图4-17所示：
图4-17 团长实体属性图
4. 商品实体属性
商品实体包括：商品编号、团长编号、商品名称、封面url、商品描述、商品详情、商品标签等属性，商品实体属性图如图4-18所示：
图4-18 商品实体属性图
5. 商品规格实体属性
商品规格实体包括：规格编号、商品编号、规格顺序、规格名称、规格详情、图片url、库存数量、规格价格等属性，商品规格实体属性图如图4-19所示：
图4-19 商品规格实体属性图
6. 用户收货地址实体属性
用户收货地址实体包括：地址编号、用户编号、收货人姓名、手机号、所在省份、所在城市、所在区域、所在街道、详细地址、默认收货地址等属性，用户收货地址实体属性图如图4-20所示：
图4-20 用户收货地址实体属性图
7. 购物车实体属性
购物车实体包括：购物车编号、用户编号、团长编号、商品编号、商品数量等属性，购物车实体属性图如图4-21所示：
图4-21 购物车实体属性图
8. 订单实体属性
订单实体包括：订单编号、商品编号、交易方式、订单总价、支付价格、优惠价格、微信交易号、创建时间、付款时间、完成时间等属性，订单实体属性图如图4-22所示：
图4-22 订单实体属性图
9. 订单商品实体属性图
订单商品实体包括：订单编号、商品编号、团长编号、商品名称、商品图片、商品描述、商品详情、商品规格、购买数量、商品价格等属性，订单商品实体属性图如图4-23所示：
图4-23 订单商品实体属性图
10. 平台主要实体之间的关系
各实体间的联系为一个普通用户管理多个收货地址，一个普通用户管理一个购物车、可以创建多个订单、查看多个团长、查看多个商品。一个订单可以包含多个商品，一个商品可以包含多个商品规格。一个团长可以发布多个商品、发布多个文章，管理多个订单。一个订单包含多个订单商品。实体关系如图4-24所示：
图4-24 实体关系图
4.3.2 逻辑结构设计
1. article文章表
文章表主要保存文章信息，主要字段有文章编号、封面图片url、文章内容文本、文章是否显示、文章类型等属性。结构如表4-1所示：
表4-1 article文章表
数据项	数据类型	是否为空	是否主键	描述
article_id	int	否	是	文章编号
cover_img_url	varchar	否		封面图片url
article_markdown	longtext	否		文章内容
is_show
create_type	int
int	否
否	
	是否显示
文章类型
2. header 团长表
团长表主要保存活动信息，主要字段有团长编号、用户编号、所在城市、所在社区、手机号、注册时间、所处经度、所处纬度等属性。结构如表4-2所示：
表4-2 header团长表
数据项	数据类型	是否为空	是否主键	描述
header_id	int	否	是	团长编号
user_id	int	否		用户编号
header_city	varchar	否		所在城市
header_community
header_phone_number
header_registered_time
header_longitude
header_latitude	varchar
varchar
varchar
varchar
varchar	否
否
否
否
否		所在社区
手机号
注册时间
所处经度
所处纬度
3. header_commodity 商品表
商品表主要保存团长商品信息，主要字段有商品编号、团长编号、商品名称、商品封面url、商品描述、商品详情、商品标签等属性。结构如表4-3所示：
表4-3 header_commodity商品表
数据项	数据类型	是否允空	是否主键	描述
header_commodity_id	int	否	是	商品编号
header_id	int	否		团长编号
header_commodity_name	varchar	否		商品名称
header_commodity_imgurl
header_commodity_description
header_commodity_details
header_commodity_tag	longtext
varchar
longtext
varchar	否
否
否
否	

	封面url
商品描述
商品详情
商品标签
4. header_commodity_specification 商品规格表
商品规格表主要保存团长商品规格信息，主要字段有商品规格编号、商品编号、规格顺序、规格名称、规格详情、规格图片url、规格库存数量、规格价格等属性。结构如表4-4所示：
表4-4 header_commodity_specification商品规格表
数据项	数据类型	是否为空	是否主键	描述
commodity_specification_id	int	否	是	规格编号
commodity_specification_sequence	int	否		商品编号
header_commodity_id	int	否		规格顺序
commodity_specification_name
commodity_specification_details
commodity_specification_img
commodity_specification_number
commodity_specification_price	varchar
varchar
varchar
int decimal	否
否
否
否
否		规格名称
规格详情
图片url
库存数量
规格价格
5. user 用户表
用户表主要保存用户信息，主要字段用户编号、用户昵称、用户头像url、用户所在城市、用户手机号、用户注册时间等属性。结构如表4-5所示：
表4-5 user用户表
数据项	数据类型	是否为空	是否主键	描述
user_id	int	否	是	用户编号
user_name	varchar	否		用户昵称
user_avatar_url	varchar	否		头像url
user_city
user_phone_number
user_register_time	varchar
varchar
varchar	否
否
否		所在城市
手机号
注册时间
6. user_address 用户收货地址表
用户收货地址表主要保存用户收货地址信息，主要字段有用户地址编号、用户编号、收货人姓名、收货人手机号、所在省份、所在城市、所在区域、所在街道、详细地址、是否默认收货地址等属性。结构如表4-6所示：
表4-6 user_address用户收货地址表
数据项	数据类型	是否为空	是否主键	描述
user_address_id	int	否	是	地址编号
user_id	int	否		用户编号
user_name	varchar	否		收货人姓名
user_phone_number
user_province
user_city
user_region
user_street
user_address
user_default_address	int
varchar
varchar
varchar
varchar
varchar
varchar	否
否
否
否
否
否
否		手机号
所在省份
所在城市
所在区域
所在街道
详细地址
默认收货地址
7. user_cart 购物车表
用户购物车表主要保存用户购物车信息，主要字段有购物车编号、用户编号、团长编号、商品编号、商品数量、商品规格编号等属性。结构如表4-7所示：
表4-7 user_cart 购物车表
数据项	数据类型	是否为空	是否主键	描述
cart_id	int	否	是	购物车编号
user_id
header_id	int
int	否
否		用户编号
团长编号
commodity_id commodity_number	int
int	否
否		商品编号
商品数量
commodity_specification_id	int	否		规格编号
8. user_order 订单表
订单表主要保存用户订单信息，主要字段有订单编号、商品编号、交易方式、总价、支付价格、优惠价格、微信交易号、订单创建时间、订单付款时间、订单完成时间等属性。结构如表4-8所示：
表4-8 user_order订单表
数据项	数据类型	是否为空	是否主键	描述
order_id	int	否	是	订单编号
commodity_id means_of_transaction total_price
pay_price
discounted_prices wechat_transaction_number create_time
payment_time transaction_time	int
decimal
decimal
decimal
decimal
varchar
varchar
varchar
varchar	否
否
否
否
否
否
否
否
否		商品编号
交易方式
订单总价
支付价格
优惠价格
微信交易号
创建时间
付款时间
完成时间
9. user_order_ommodity 订单商品表
订单商品表主要保存用户订单商品信息，主要字段有订单编号、商品编号、团长编号、商品名称、商品图片、商品描述、商品详情、商品规格、购买数量、商品价格等属性。结构如表4-9所示：
表4-9 user_order_ommodity订单商品表
数据项	数据类型	是否为空	是否主键	描述
order_id	int	否	是	订单编号
commodity_id	int	否		商品编号
header_id	varchar	否		团长编号
name
imgurl
description
details
specification
commodity_number
commodity_price	varchar
varchar
varchar
varchar
varchar
int
decimal	否
否
否
否
否
否
否		商品名称
商品图片
商品描述
商品详情
商品规格
购买数量
商品价格

 
5 系统实现
5.1 系统实现环境
本平台服务端使用SpringBoot作为核心框架，使用maven引入其他若干个jar包进行开发。使用IDEA作为开发工具进行项目的搭建。Java版本使用1.8.0，MySQL数据库使用8.0.15版本。
本平台在windows系统上进行开发测试，开发完成后将服务端项目上传至运行Ubuntu操作系统的服务器上。使用nginx、tomcat作为Httpfuwu软件。
根据微信发布的微信小程序相关开发要求，在微信小程序与服务端之间需要使用Https连接，禁止使用http连接。从腾讯云平台申请Https证书，使用ngins进行证书的配置，实现Https连接。
5.2 系统主要功能实现
5.2.1 文章模块主要功能实现
1. 发布文章
发布文章实现过程，首先判断用户是否有发布文章的权限，在发布页面编写文章信息，上传到服务器，具体实现过程：
(1) 用户点击个人页面，通过UserService判断用户是否为团长方法UserAuthority()，根据传入的用户id从数据库团长表查询记录是否存在，UserController向微信小程序返回查询结果，如果该用户具有团长权限，用户可以点击发布文章按钮进入发布文章页面。
(2) 团长在微信小程序发布文章页面点击上传封面按钮，通过微信小程序提供的相册API打开相册，团长选择封面点击确认按钮，将图片缓存至微信小程序的缓存内。
(3) 团长在文章编辑页面内的文章标题栏输入文章的标题，在正文文本编辑框内输入markdown格式的文本内容。相关信息编辑完成后点击完成按钮，微信小程序根据输入的相关信息生成文章预览信息，用户在文章预览页面查看文章详情。
(4) 团长在文章编辑页面可以点击图片上传按钮，调用微信小程序提供的相册API选择要在正文内插入的图片，点击确定按钮将选择的图片封装好后上传至服务端。服务端上传完成返回图片URL链接，微信小程序在正文下一行自动添加图片链接。
(5) 团长在预览页面发现文章内容编辑有误点击返回按钮重新编辑，确认无误后点击提交按钮，微信小程序将文章的封面及正文封装为json格式发送给服务端，服务端将数据写入到数据库后向微信小程序返回相应的操作结果。
上述部分关键代码如下：
if(activityVo.getCoverImgUrl()==null|| activityVo.getCoverImgUrl().equals("")){
return toResponse.toResponsFail("封面图片为空");}
if(activityVo.getCreateType().equals("create")){
activityVo.setIsShow(0);
int id = activityService.createActivity(activityVo);
return toResponse.toResponsMsgSuccess("提交成功", id);}
if(activityVo.getCreateType().equals("edit")){
if(activityService.updateActivity(activityVo)){
return toResponse.toResponsMsgSuccess("提交成功", "id");
}
return toResponse.toResponsFail("更新失败");
}
return toResponse.toResponsFail("提交失败");
发布文章页面如图5-1所示：
图5-1 文章发布页面图
2. 文章管理
文章管理实现过程，首先判断用户是否有编辑文章的权限，在文章列表页面点击文章的按钮对文章实现相关操作，具体实现过程：
(1) 团长进入文章管理页面，通过ArticleService获取文章列表方法GetArticleList()，根据传入的团长id从数据库团长发布的文章数据，ArticleComtroller向微信小程序返回查询结果。文章列表每一个文章可以左滑显示更多操作：编辑、删除、隐藏/显示三个按钮。
(2) 点击删除按钮，微信小程序获取要删除的文章编号，向ArticleService的删除文章方法DeleteArticle()传递要删除的文章编号和团长编号。完成操作权限验证后，调用deleteArticle()方法从数据库删除该团长相对应的文章，返回操作结果。
(3) 点击隐藏/显示按钮，微信小程序获取要隐藏/显示的文章编号，向ArticleService的IsShowArticle()方法传递要隐藏/显示的文章编号和团长编号。服务端完成验证后，在数据库修改该文章的is_show值(0隐藏，1显示)，返回操作结果。
(4) 点击编辑按钮，微信小程序获取要编辑的文章编号，向ArticleService的编辑文章方法EditArticle()传递要编辑的文章编号和团长编号。服务端完成操作权限验证后，根据ArtilceId在数据库查询返回查询结果。微信小程序接收到后，在编辑页面将文章的封面、标题、正文内容修改为从服务端获取的查询结果。团长完成文章信息的编辑后，点击提交按钮完成文章的编辑操作。
上述部分关键代码如下：
if(activityMapper.ActivityIsShow(activityId, isShowValue==0?1:0) >= 0){
return true;}
if(activityMapper.ActivityDelete(activityId) >= 0){
return true;}
文章管理页面如图5-2所示：
图5-2 文章管理页面图
3. 查看文章
查看文章实现过程，根据用户位置推送用户周边团长发布的文章，具体实现过程：
(1)用户在微信小程序点击文章按钮进入文章列表页面。微信小程序向ArticleController发送获取文章请求，ArticleController根据用户id进行权限验证，权限验证失败返回权限错误信息。
(2)权限验证通过后，调用ArticleService的GetArticle()方法，根据用户编号在数据库查询用户选择的团长编号和团长所在位置的经纬度，根据团长编号在数据库查询团长发布的文章，根据团长的经纬度查询周边团长发布的文章，将查询到的文章以json格式返回给微信小程序。微信小程序接收到后在文章页面完成相关数据绑定，显示文章。
(3)用户在文章页面点击文章封面，在文章页面以缩放的形式打开该文章详情。用户点击关闭按钮，微信小程序关闭该文章的详情内容，显示文章封面列表页面。
上述部分关键代码如下：
List<ActivityVo> activityMarkdownList = new ArrayList<>();
activityMarkdownList = activityMapper.getAllActivityMarkdown();
for(ActivityVo activityVo:activityMarkdownList){
    String imgName = activityVo.getCoverImgUrl();
    String url = Config.ServerUrl;
    activityVo.setCoverImgUrl(url+imgName);}
return activityMarkdownList;
查看文章页面如图5-3所示：
图5-3 查看文章页面图
5.2.2 用户模块主要功能实现
1. 用户注册
用户注册实现过程，因微信官方要求，不能够强迫用户注册及自动注册。微信小程序弹出申请获取读取用户信息权限的弹窗，用户允许微信小程序获取用户信息后完成用户的注册。具体实现过程：
(1) 新用户打开微信小程序后，任意点击切换团长按钮、购物车按钮、登陆按钮后，微信小程序读取小程序缓存，查询用户是否已登录，没有登录弹窗提示用户需要登录。
(2) 微信小程序申请获取用户个人信息权限授权框，用户允许后将用户信息发送到UserController，通过UserService查询用户是否注册方法IsRegister()从数据库查询是否注册过，如果没有注册，通过Register()方法将用户信息写入数据库，返回注册结果，如果已注册返回登录结果。微信小程序接收到服务端返回的数据并进行除了分析后，在缓存种写入用户的信息，完成登录。
上述部分关键代码如下：
if(user.getNickName() == null || user.getPhoneNumber() == null){
    toResponse.toResponsFail("用户昵称或手机号为空");}
if(userService.registerUser(user)){
    toResponse.toResponsFail("注册失败");}
if(userService.selectHaveUser(user)){
    System.out.println("is true");
    return toResponse.toResponsFail("用户已经注册过了");}
用户注册页面如图5-4所示：
图5-4 用户注册页面图
2. 切换团长
切换团长实现过程，首先判断用户是否登录，若没有登录判断用户是否已授权允许微信获取用户信息，若已允许获取用户信息权限，向服务端发送用户验证信息完成用户登录操作，如果用户没有注册则跳转到注册页面。具体实现过程如下：
(1) 用户点击切换团长按钮后，微信小程序从缓存中读取缓存数据，判断用户是否已经登录，若没有登录判断用户是否已授权允许微信获取用户信息，若已允许获取用户信息权限，向服务端发送用户验证信息完成用户登录操作，如果用户没有注册则跳转到注册页面，已登录的用户进入团长选择页面。
(2) 微信小程序通过HeaderController传递用户编号和用户当前位置数据，调用HeaderService获取周边团长方法GetHeaderList()根据用户位置信息在数据库查询周边的团长信息，将查询结果以json格式返回。
(3) 微信小程序在地图上显示从服务端接收到的用户周边团长信息，根据团长的经纬度在地图上显示标记位置。用户点击地图上的团长标记可以查看该团长的相关信息。选择完成团长后点击确认按钮返回首页。
(4) 微信小程序将用户选择的团长编号和用户编号发送给UserController。通过UserService修改用户选择的团长方法UpdateUserHeader()，根据用户编号在数据库中修改用户选择的团长编号。
上述部分关键代码如下：
ROUND(6378.138 * 2 * ASIN(
SQRT(
	POW( SIN( ( #{latitude} * PI() / 180 - h.latitude * PI() / 180 ) / 2 ), 2 )
		+ COS( #{latitude} * PI() / 180 )
* COS( h.latitude * PI() / 180 )
		* POW( SIN( 
( #{longitude} * PI() / 180 - h.longitude * PI() / 180 ) / 2 ), 2 )
)) * 1000
	) 
切换团长页面如图5-5所示：
图5-5 切换团长页面图
5.2.3 订单模块主要功能实现
1. 订单管理
订单管理实现过程，根据用户订单状态分为已完成、待付款、未完成三个状态，用户与团长之间完成相关交易后在订单详情页面更改订单状态。具体实现过程如下：
(1) 用户在个人页面点击订单按钮进入订单列表页面。
(2) 微信小程序向OrderController发送用户编号，通过OrderService查询用户订单方法GetUserOrder()，根据用户编号从数据库查询用户全部订单返回结果。
(3) 微信小程序接收到服务端返回数据后，将返回的的订单信息数据进行分类：全部订单、未付款订单、未完成订单。用户可以查看每个分类下的订单。
(4) 用户对于已完成状态的订单只能在订单详情页面查看订单相关信息，无法对订单进行任何操作。
(5) 用户对于待付款状态的订单，可以在订单详情页面点击付款按钮进入订单的付款页面。向OrderController发送订单数据，通过OrderService订单结算方法OrderPayment()计算订单内商品总价格、优惠价格和实际支付价格，返回处理结果。用户在微信的支付页面完成支付后，向OrderController发送支付结果，通过OrderService订单支付结果方法OrderPaymentResult()判断用户是否完成支付，返回结果。
(6) 用户待完成订单，用户与团长线下完成交易后，在订单详情页面点击完成交易按钮，向OrderController发送请求，进行用户权限验证，验证不通过返回失败信息，验证通过后，通过OrderService订单完成方法OrderComplete ()修改订单信息，返回结果。
上述部分关键代码如下：
int num = tmsOrderService.update(orderInfo);
if (num > 0) {
    return toResponsMsgSuccess("修改订单成功");
} else {
    return toResponsFail("修改订单失败");
}
订单管理页面如图5-6所示：
图5-6 订单管理页面图
2. 创建订单
创建订单实现过程，用户选择购物车内要结算的商品点击结算按钮，计算订单总价、优惠价格、实际支付价格，用户确认后点击提交按钮。具体实现过程如下：
(1) 用户在购物车页面点击商品左侧的选中按钮选择要购买的商品，确认完成该商品的商品规格信息和相应的购买数量后，点击结算按钮，跳转到订单确认页面。
(2)向OrderController发送结算信息，通过OrderService订单结算方法OrderPayment ()计算订单内商品总价格、优惠价格和实际支付价格，返回处理结果。
(3) 用户确认订单信息后点击提交订单按钮，向OrderController发送提交订单信息，通过OrderService订单创建方法CreateOrder()向数据库写入订单信息返回结果。
上述部分关键代码如下：
BigDecimal productTotalPrice = new BigDecimal("0.00"); 
for (TmsOrderRequestProductVo productVo : productVoList){
BigDecimal specificationPrice=tmsSpecificationMapper.selectSpecificationPrice(productVo.getProductId(), productVo.getSpecificationId());
BigDecimal number = new BigDecimal(String.valueOf(productVo.getNumber()));
BigDecimal specificationTotalPrice = specificationPrice.multiply(number);
productTotalPrice = productTotalPrice.add(specificationTotalPrice);
}
创建订单页面如图5-7所示：
图5-7 创建订单页面图
5.2.4 商品模块主要功能实现 
1. 发布商品
发布商品实现过程，首先判断用户是否有发布商品的权限，在发布商品页面编写商品信息，上传到服务器。具体实现过程如下：
(1) 通过读取缓存数据，向HeaderController发送用户id，验证是否具有发布商品的权限，如果验证失败返回相关信息，验证通过后进入商品发布页面。发布商品页面分上下两栏，上栏填写商品详情，下栏填写商品规格、库存、价格。
(2) 上栏：团长点击封面设置按钮，从相册里选择商品的封面图片。点击添加商品展示图按钮从相册里选择添加多个商品的展示图。在商品信息栏输入商品名、商品描述和商品详情等相关信息。
(3) 下栏：团长点击加号按钮添加该商品的商品规格信息，每一件商品允许设置多个商品规格信息。在规格输入栏输入规格名、库存数量、单价，点击上传规格图片按钮选择规格图片。
(4) 商品信息输入完成后，向CommodityController发送商品数据，通过CommodityService发布商品方法CreateCommodity()处理传递的商品数据后写入到商品表和规格表中，返回结果。 
上述部分关键代码如下：
TmsProductVo tmsProductVo = new TmsProductVo();
tmsProductVo.setHead_id(headId);
tmsProductVo.setName(productName);
Integer productId = tmsProductMapper.createProduct(tmsProductVo);
if(productId == null){
    return false;
}
发布商品页面如图5-8所示：
图5-8 发布商品页面图
2. 商品管理
商品管理实现过程，首先判断用户是否有编辑商品的权限，在商品列表页面点击商品操作的按钮对商品实现相关操作。商品的编辑操作有：上架/下架、删除、编辑三个按钮。具体实现过程如下：
(1) 通过读取缓存数据，向HeaderController发送用户id，验证是否具有操作商品的权限，如果验证失败返回相关信息，验证通过后进入商品列表页面。向CommodityController发送获取商品请求，通过CommodityService获取商品方法GetCommodity()根据传递的团长id从数据库中查询商品信息，返回结果。
(2) 点击上架/下架按钮，向CommodityController发送修改商品状态请求，通过CommodityService修改商品状态方法ShowCommodity()根据传递的团长id从数据库中查询商品信息修改商品上/下架状态，返回结果。
(3) 点击删除按钮，向CommodityController发送删除商品请求，通过CommodityService删除商品方法DeleteCommodity()根据传递的团长id从数据库中删除商品信息，返回结果。
(4) 点击编辑按钮，微信小程序从服务端获取该商品的详细信息，对服务端返回的商品数据进行处理后，显示在商品的编辑页面中。团长修改商品信息后，点击提交按钮，向CommodityController发送编辑商品信息请求，通过CommodityService编辑商品信息方法EditCommodity()根据传递的商品id从数据库中更新商品信息，返回结果。
上述部分关键代码如下：
if(tmsProductMapper.updateProduct(headId, productId, productName) <= 0){
    return false;
}
BigDecimal price = new BigDecimal(specificationPrice);
if(tmsSpecificationMapper.updateSpecification(productId, specificationId, specificationDes, price, specificationInStock) <= 0){
    return false;
}
商品管理页面如图5-9所示：
图5-9 商品管理页面图 -->


![](http://pic.misslovecloud.cn/picgo/wechatdevtools_XfBxl7rIqS.png)

![](http://pic.misslovecloud.cn/picgo/wechatdevtools_neBlDtofow.png)

![](http://pic.misslovecloud.cn/picgo/wechatdevtools_QyqDn6t7G4.png)

![](http://pic.misslovecloud.cn/picgo/wechatdevtools_41GQX4FxEJ.png)

![](http://pic.misslovecloud.cn/picgo/wechatdevtools_6ypwxr1TRl.png)



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

## 系统设计

  本平台主要分为七大模块：文章模块、购物模块、用户模块、订单模块、商品模块、支付模块、安全模块。各个模块下又分为不同的功能，最终实现社区商城平台的主要功能。
  用户功能：按照平台用户分为普通用户和团长两类
  普通用户允许使用的功能：文章浏览、购物车查看、购物车编辑、加入购物车
  团长用户允许使用的功能：
  非用户功能：软件除了用户可见的功能外，还有非用户功能：权限验证模块。
  权限验证模块主要功能：用户请求权限验证
  <!-- 登录模块：登录状态验证、用户信息验证 -->
<!--
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
