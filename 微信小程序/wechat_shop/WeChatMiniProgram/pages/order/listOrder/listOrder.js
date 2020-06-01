// pages/order/listorder/listorder.js
const util = require('../../../utils/fun.js');
const api = require('../../../utils/config.js');
const { $Toast } = require('../../../a_plugin/iview/base/index');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderList:[],
    isHead: 0,
  },

  onShow: function () {
    // this.getOrderList()
  },

  getOrderList:function(e){
    
    var that = this
    util.request(api.GetOrderList, {
      userId: wx.getStorageSync('userId'),
      headId: that.data.isHead==0?0:e.headId,
    }, "GET").then(function (res) {
      if (res.status === 1) {
        console.log(res.data)
        that.setData({ orderList: res.data})
      }
    });
  },

  // 更新订单状态
  updateOrderStatus:function(e){
    var that = this
    if(that.data.isHead==0){
      util.request(api.PostOrderStatus, {
        orderId: e.currentTarget.dataset.orderid,
      }, "POST").then(function (res) {
        if (res.status === 1) {
          that.getOrderList()
        }
      });
    }
  },

  // 跳转到订单详情页面
  orderInfo: function (e){
    wx.navigateTo({
      url: '/pages/order/order/order?orderId=' + e.currentTarget.dataset.orderid,
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    if(options.headId!=null){
      that.setData({ isHead: 1 })
    }else{
      that.setData({ isHead: 0 })
    }
    
    this.getOrderList(options)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  

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