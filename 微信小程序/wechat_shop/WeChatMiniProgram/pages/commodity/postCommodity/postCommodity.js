// pages/commodity/postCommodity/postCommodity.js
const util = require('../../../utils/fun.js');
const api = require('../../../utils/config.js');
const { $Toast } = require('../../../a_plugin/iview/base/index');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    headId: "",
    commodityId: "",
    commodityImgUrl: "",
    commodityName: "",
    commodityDescription: "",
    commodityDetails: "",
    specification: "",
    isEdit: 0,
  },

  // 获取商品详情
  getCommodityByCommodityId: function (opt) {
    var that = this
    util.request(api.GetCommodityByCommodityId, {
      commodityId: opt
    }, "GET").then(function (res) {
      if (res.status === 1) {
        var list = []
        for (var i = 0; i < res.data.commodityImgUrl.length;i++){
          list.push({ "url": res.data.commodityImgUrl[i]})
        }

        for (var i = 0; i < res.data.specification.length; i++) {
          res.data.specification[i].specificationImgUrl = 
              [{ "url": res.data.specification[i].specificationImgUrl }]
        }
        that.setData({
          headId: res.data.headId,
          commodityId: res.data.commodityId,
          commodityImgUrl: list,
          commodityName: res.data.commodityName,
          commodityDescription: res.data.commodityDescription,
          commodityDetails: res.data.commodityDetails,
          specification: res.data.specification,
        })
      }
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (options.isEdit == 1) {
      var that = this
      that.setData({
        isEdit: 1
      })
      this.getCommodityByCommodityId(options.commodityId)
    } else {
      this.getCommodityByCommodityId(options.commodityId)
    }
  },

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