//const API_BASE_URL = 'https://www.misslovecloud.cn/wechat';
const API_BASE_URL = 'http://192.168.31.94:8080';
const API_IMG_URL = 'https://www.misslovecloud.cn/wechat/uploads/';
module.exports = {

  // 文章
  API_IMG_URL: API_IMG_URL,
  PostUploadFile: API_BASE_URL + '/upload/PostUploadFile', // 上传文件
  GetActivityListByHeadId: API_BASE_URL + '/Article/head', //
  GetActivityByArticleId: API_BASE_URL + '/Article/article', //
  GetActivity: API_BASE_URL + '/Article/ArticleOfShow', //
  GetActivityById: API_BASE_URL + '/Article/GetActivityByActivityId', //
  DeleteDeleteActivity: API_BASE_URL + '/Article/delete', //
  PostUpdateActivityStatus: API_BASE_URL + '/Article/updateStatus', //
  PostUpdateActivity: API_BASE_URL + '/Article/update', //
  PostCreateActivity: API_BASE_URL + '/Article/create', //

  // 团长
  PostHeaderRegiester: API_BASE_URL + 'head/regiester',     // 团长注册
  GetHeaderList: API_BASE_URL + '/head/list',         // 获取团长列表
  GetHeadInfoByUserId: API_BASE_URL + '/head/HeaderInfo',     // 获取团长信息

  // 商品
  GetHeadCommodityByHeadId: API_BASE_URL + '/commodity/list',   // 获取团长发布的商品
  GetCommodityByCommodityId: API_BASE_URL + '/commodity/commodity',   // 获取商品详情
  DeleteDeleteActivity: API_BASE_URL + '/commodity/delete', //
  PostUpdateActivityStatus: API_BASE_URL + '/commodity/updateStatus', //

  // 购物车
  PostAddCart: API_BASE_URL + 'cart/add',   // 商品添加进购物车
  GetCommodityListByUserId: API_BASE_URL + '/cart/cart',   // 获取购物车商品
  PostUpdateCommodity: API_BASE_URL + '/cart/update',   // 更新购物车商品
  DeleteCart: API_BASE_URL + '/cart/delete',   // 删除购物车商品
  GetCartSelectByUserId: API_BASE_URL + '/cart/select',   // 获取购物车选择的商品

  // 用户
  PostUserLogin: API_BASE_URL + '/user/login', // 用户登录
  PostRegister: API_BASE_URL + '/user/register', // 用户注册
  UpdateUserHeaderId: API_BASE_URL + '/user/updateHeadId', // 更新用户选择的团长

  // 订单
  PostCreateOrder: API_BASE_URL + '/order/create', // 创建订单
  GetOrderList: API_BASE_URL + '/order/list', // 获取订单列表
  PostOrderStatus: API_BASE_URL + '/order/update', // 更新订单状态
  GetOrderInfo: API_BASE_URL + '/order/order', // 获取订单信息
  
  // PostText: API_BASE_URL + '/activity/PostCreateActivity', //
  // PostUploadFile: API_BASE_URL + '/upload/PostUploadFile', //
  // PostActivityByIdForIsShow: API_BASE_URL + '/activity/PostActivityByIdForIsShow', //
  // PostActivityByIdForDelete: API_BASE_URL + '/activity/PostActivityByIdForDelete', //
};
