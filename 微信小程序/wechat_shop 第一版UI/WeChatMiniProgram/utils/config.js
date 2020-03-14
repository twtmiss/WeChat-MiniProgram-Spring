const API_BASE_URL = 'https://www.misslovecloud.cn/wechat';
// const API_BASE_URL = 'http://127.0.0.1:8080/';
const API_IMG_URL = 'https://www.misslovecloud.cn/wechat/uploads/';
module.exports = {
  API_IMG_URL: API_BASE_URL + '/uploads/',
  GetActivity: API_BASE_URL + '/activity/GetActivity', // 获取公司简介
  GetActivityById: API_BASE_URL + '/activity/GetActivityById', // 获取公司简介
  PostRegister: API_BASE_URL + '/user/PostRegister', // 用户注册
  PostText: API_BASE_URL + '/activity/PostCreateActivity', // 获取公司简介
  PostUploadFile: API_BASE_URL + '/upload/PostUploadFile', // 上传文件
  PostActivityByIdForIsShow: API_BASE_URL + '/activity/PostActivityByIdForIsShow', // 上传文件
  PostActivityByIdForDelete: API_BASE_URL + '/activity/PostActivityByIdForDelete', // 上传文件
};
