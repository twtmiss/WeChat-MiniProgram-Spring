// pages/header/register/register.js
const { $Toast } = require('../../../a_plugin/iview/base/index');
const app = getApp()
const util = require('../../../utils/fun.js');
const api = require('../../../utils/config.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    nickName: "",
    avatarUrl: "",
    country: "",
    province: "",
    city: "",
    phoneNumber: "",
    community: "",
    latitude:"",  // 纬度
    longitude:"",   // 经度

  },
  bindPhoneNumber: function (res) {
    var that = this
    // console.log(res)
    that.setData({ phoneNumber: res.detail.detail.value })
  },
  bindCommunity: function (res) {
    var that = this
    that.setData({ community: res.detail.detail.value })
  },

  // 获取用户位置
  getUserLocation:function(){
    var that = this
    wx.getLocation({
      type: 'wgs84',
      success(res) {
        console.log(res)
        that.setData({
          latitude : res.latitude,
          longitude : res.longitude,
        })
      }
    })
  },

  handleSuccess: function () {
    util.request(api.PostHeaderRegiester, {
      userId: wx.getStorageSync("userId"),
      city: this.data.city,
      phoneNumber: this.data.phoneNumber,
      community: this.data.community,
      latitude: this.data.latitude,  // 纬度
      longitude: this.data.longitude,   // 经度
    }, "POST").then(function (res) {
      if (res.status === 1) {
        wx.setStorageSync("headId", res.data)
        wx.setStorageSync("isHeader", true)
        $Toast({
          content: '注册成功，1秒后返回',
          type: 'success'
        });
        setTimeout(() => {
          wx.navigateBack({
            delta: 1
          })
        }, 1000);
      } else {
        $Toast({
          content: '注册失败',
          type: 'success'
        });
      }
    });

    
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    if (wx.getStorageSync("userInfo")) {
      var userInfo = wx.getStorageSync("userInfo")
      this.setData({
        avatarUrl: userInfo.avatarUrl,
        nickName: userInfo.nickName,
        country: userInfo.country,
        province: userInfo.province,
        city: userInfo.city,
      })
    }
    this.getUserLocation()
  },

    // var that = this
    // // 查看是否授权
    // wx.getSetting({
    //   success(res) {
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称
    //       wx.getUserInfo({
    //         success: function (res) {
    //           that.setData({
    //             nickName: res.userInfo.nickName,
    //             avatarUrl: res.userInfo.avatarUrl,
    //             country: res.userInfo.country,
    //             province: res.userInfo.province,
    //             city: res.userInfo.city
    //           })
    //           console.log(res.userInfo)
    //         }
    //       })
    //     }
    //   }
    // })
 

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})