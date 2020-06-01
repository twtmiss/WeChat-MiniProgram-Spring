// pages/commodity/listCommodity/listCommodity.js
const app = getApp()
var utils = require('../../../utils/fun.js');
const config = require('../../../utils/config.js');

Page({
  data: {
    commodityList: [],
  },

  onShow: function () {
    this.getCommodity()
  },

  // 每个activity的点击事件
  bingActivity(e) {
    wx.navigateTo({
      url: '/pages/commodity/viewCommodity/viewCommodity?commodityId='
        + e.currentTarget.dataset.commodityid
        + '&isEdit=' + 1,
    })
  },

  // 发布文章
  createActivity: function () {
    wx.navigateTo({
      url: '/pages/commodity/postCommodity/postCommodity' + '?isEdit=' + 0,
    })
  },

  bindButton: function (e) {
    var that = this
    if (e.currentTarget.dataset.btn == 1) {
      // 删除
      utils.request(config.DeleteDeleteActivity, {
        articleId: e.currentTarget.dataset.articleid
      }, 'DELETE').then(function (res) {
        if (res.status === 1) {
          that.getActivity()
        }
      });
    }
    // if (e.currentTarget.dataset.btn == 2) {
    //   // 隐藏/显示
    //   utils.request(config.PostUpdateActivityStatus, {
    //     articleId: e.currentTarget.dataset.articleid
    //   }, 'POST').then(function (res) {
    //     if (res.status === 1) {
    //       that.getActivity()
    //     }
    //   });
    // }
    if (e.currentTarget.dataset.btn == 3) {
      // 编辑
      wx.navigateTo({
        url: '/pages/commodity/postCommodity/postCommodity?commodityId=' + e.currentTarget.dataset.commodityid+ '&isEdit=' + 1,
      })

    }
  },

  getCommodity: function () {
    var that = this
    utils.request(config.GetHeadCommodityByHeadId, {
      headId: wx.getStorageSync("headId")
    }, 'GET').then(function (res) {
      if (res.status === 1) {
        that.setData({ commodityList: res.data })
      }
    });
  }
})