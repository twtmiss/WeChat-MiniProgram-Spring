// pages/activity/activityList/activityList.js
const app = getApp()
var utils = require('../../../utils/fun.js');
const config = require('../../../utils/config.js');

Page({
  data: {
    activityList: [],
    coverImgUrl: config.API_IMG_URL
  },

  onShow: function () {
    this.getActivity()
  },

  // 每个activity的点击事件
  bingActivity(e) {
    wx.navigateTo({
      url: '/pages/activity/activity/activity?articleId='
        +e.currentTarget.dataset.articleid
        + '&isEdit=' + 0,
    })
  },

  // 发布文章
  createActivity:function(){
    wx.navigateTo({
      url: '/pages/activity/editActivity/editActivity?isEdit=2',
    })
  },

  bindButton: function (e) {
    var that = this
    if (e.currentTarget.dataset.btn==1){
      // 删除
      utils.request(config.DeleteDeleteActivity, {
        articleId: e.currentTarget.dataset.articleid
      }, 'DELETE').then(function (res) {
        if (res.status === 1) {
          that.getActivity()
        }
      });
    }
    if (e.currentTarget.dataset.btn == 2) {
      // 隐藏/显示
      utils.request(config.PostUpdateActivityStatus, {
        articleId: e.currentTarget.dataset.articleid
      }, 'POST').then(function (res) {
        if(res.status===1){
          that.getActivity()
        }
      });
    }
    if (e.currentTarget.dataset.btn == 3) {
      // 编辑
      wx.navigateTo({
        url: '/pages/activity/editActivity/editActivity?articleId='+e.currentTarget.dataset.articleid
        +'&isEdit=1',
      })

    }
  },

  getActivity: function () {
    var that = this
    utils.request(config.GetActivityListByHeadId, {
      headId:wx.getStorageSync("headId")
    }, 'GET').then(function (res) {
      if (res.status === 1) {
        that.setData({ activityList: res.data })
      }
    });
  }
});