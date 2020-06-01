var util = require('../../../utils/util.js');
var api = require('../../../utils/config.js');
//  var user = require('../../utils/user.js');
// var app = getApp();

Page({
  data: {
    userInfo: {
      "avatarUrl":"/static/icons/detail_kefu.png",
      "city":"",
      "gender":1,
      "nickName":"登录",
      "province":""
    },
    ishead:0,
  },

  bind_head:function(){
    var that = this
    if(that.data.ishead){
      wx.navigateTo({
        url: '/pages/header/index/index',
      })
    }else{
      wx.navigateTo({
        url: '/pages/header/register/register',
      })
    }
  },

  bindGetUserInfo:function(e){
    // 判断是否允许小程序获取用户信息
    if (e.detail.errMsg =="getUserInfo:ok"){

      // 判断用户是否已注册，如果已注册就登录
      wx.login({
        success(res) {
          if (res.code) {
            //发起登录请求
            util.request(api.PostUserLogin, {
              code: res.code,
            },
              "POST").then(function (res) {
                // 用户未注册
                if (res.status === 1) {
                  console.log(res)
                  // 向服务器发送用户信息
                  util.request(api.PostRegister, {
                    userName: e.detail.userInfo.nickName, 
                    avatarUrl: e.detail.userInfo.avatarUrl, 
                    city: e.detail.userInfo.city, 
                    open_id: res.data.open_id
                  },
                  "POST").then(function (res) {
                    if (res.status === 1) {
                      wx.setStorageSync("userId", res.data)
                    }
                  });

                }
                //用户已注册
                if(res.status === 2){
                  console.log(res)
                  wx.setStorageSync("userId", res.data.userId)
                  wx.setStorageSync("userInfo", e.detail.userInfo)
                  
                  // this.setData{
                  //   res.
                  // }
                }
              });
          } else {
            console.log('登录失败！' + res.errMsg)
          }
        }
      })
      this.setData({
        userInfo: e.detail.userInfo
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if(wx.getStorageSync("userInfo")){
      this.setData({
        userInfo: wx.getStorageSync("userInfo")
      })
    }

    wx.setStorageSync("isHead", true)
    wx.setStorageSync("headId", 31)
    this.setData({
        ishead: wx.getStorageSync("isHeader")
      })

    // 判断用户是否为团长,
    // if (wx.getStorageSync("isHeader")) {
    //   this.setData({
    //     ishead: wx.getStorageSync("isHeader")
    //   })
    // }else{
    //   wx.setStorage({
    //     key: 'isHeader',
    //     data: false,
    //   })
    // }
  }

})