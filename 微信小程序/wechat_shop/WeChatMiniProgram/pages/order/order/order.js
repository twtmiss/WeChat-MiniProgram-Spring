// pages/order/order/order.js
const util = require('../../../utils/fun.js');
const api = require('../../../utils/config.js');
const { $Toast } = require('../../../a_plugin/iview/base/index');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    order: {},
  },

  onShow: function (e) {
    // this.getOrder(e)
  },

  getOrder: function (e) {
    console.log(e)
    var that = this
    util.request(api.GetOrderInfo, {
      orderId:e.orderId,
    }, "GET").then(function (res) {
      if (res.status === 1) {
        that.setData({ order: res.data })
      }
    });
  },

  // 更新订单状态
  updateOrderStatus: function (e) {
    var that = this
    util.request(api.PostOrderStatus, {
      orderId: e.currentTarget.dataset.orderid,
    }, "POST").then(function (res) {
      if (res.status === 1) {
        that.getOrder()
      }
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getOrder(options)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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