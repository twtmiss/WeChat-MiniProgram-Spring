//index.js
//获取应用实例
const app = getApp()
var fun = require('../../utils/fun.js');
const config = require('../../utils/config.js');

Page({
  data: {
    scroll_animation: 'scroll_click_narrow',
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    scr_ifIs: false,
    scr_hidden: true,
    scr_top: 0,
    scr_width: 0,
    scr_height: 0,
    scr_marginleft: 0,
    js_animation_play_state: 'running',
    activityClickIndex: 0,
    scroll_rich_animation: 'scroll_rich_animation',
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    activityList: [],
    i_modal_visible: false,
    i_modal_actions: [
      {
        name: '不预约了',
      },
      {
        name: '不授权预约',
        color: '#19be6b'
      },
      {
        name: '授权预约',
        color: '#2d8cf0',
      }
    ],
    activityText: null
  },
  //事件处理函数
  haha: function (e) {
    console.log(e)
    var that = this
    const query = wx.createSelectorQuery()
    query.select('#view' + e.currentTarget.dataset.clickindex).boundingClientRect()
    query.selectViewport().scrollOffset()
    query.exec(res => {
      that.setData({
        scr_top: res[0].top < 0 ? 0 : res[0].top,
        scr_width: res[0].width,
        scr_height: (res[0].height - 15 - (res[0].top < 0 ? -res[0].top : 0)),
        scr_marginleft: res[0].left,
        activityClickIndex: e.currentTarget.dataset.clickindex
      })
      // console.log('scr_marginleft: ' + that.data.scr_marginleft)
      this.setData({ scroll_animation: 'scroll_click_narrow', scr_ifIs: true, scr_hidden: false, scroll_rich_animation: 'scroll_rich_animation' })
      setTimeout(function () {
        that.setData({
          js_animation_play_state: 'paused'
        })
      }, 400)
    })

  },
  close: function () {
    var that = this
    this.setData({ js_animation_play_state: 'running', scroll_rich_animation: 'scroll_rich_animation_close' })
    setTimeout(function () {
      that.setData({
        // scroll_animation: 'scroll_unclick_narrow',
        scr_ifIs: false,
        scr_hidden: true
      })
    }, 340)
  },
  eh: function () {
    // this.setData({ isShow: false })
  },

  handleOpen() {
    var that = this
    wx.getSetting({
      success(res) {
        if (res.authSetting['scope.userInfo']) {
          wx.navigateTo({
            url: '../register/register',
            success: function (res) { },
            fail: function (res) { },
            complete: function (res) { },
          })
        }
      }
    })
  },

  onShow: function () {
    var that = this
    this.setData({ isShow: true, i_modal_visible: false })
    // console.log("a")
    // fun.request(config.GetActivity, {}, 'GET').then(function (res) {
    //   console.log(res)
    //   that.setData({activityText: res.data})
    //   console.log(res)
    // });
    var that = this
    fun.request(config.GetActivity, {}, 'GET').then(function (res) {
      console.log(res.data)
      var list = []
      for (var i = 0; i < res.data.length; i++) {
        if (res.data[i].isShow == 1) {
          list.push(res.data[i])
        }
      }
      that.setData({ activityList: list })
    });

  },

  onLoad: function () { }

})
