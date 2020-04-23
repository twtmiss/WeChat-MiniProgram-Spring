//const API_BASE_URL = 'https://www.misslovecloud.cn/wechat';
const API_BASE_URL = 'http://192.168.31.94:8080/';
const API_IMG_URL = 'https://www.misslovecloud.cn/wechat/uploads/';
module.exports = {
  API_IMG_URL: API_BASE_URL + '/uploads/',
  GetActivity: API_BASE_URL + '/Article/ArticleOfShow', //
  GetActivityById: API_BASE_URL + '/activity/GetActivityById', //
  PostRegister: API_BASE_URL + '/user/PostRegister', //
  PostText: API_BASE_URL + '/activity/PostCreateActivity', //
  PostUploadFile: API_BASE_URL + '/upload/PostUploadFile', //
  PostActivityByIdForIsShow: API_BASE_URL + '/activity/PostActivityByIdForIsShow', //
  PostActivityByIdForDelete: API_BASE_URL + '/activity/PostActivityByIdForDelete', //
};
