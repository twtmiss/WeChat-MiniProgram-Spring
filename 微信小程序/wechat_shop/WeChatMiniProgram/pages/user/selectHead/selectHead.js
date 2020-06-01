const util = require('../../../utils/util.js');
const api = require('../../../utils/config.js');
// // const user = require('../../services/user.js');
// const app = getApp()

Page({
  data: {
    userLatitude: '',
    userLongitude: '',
    headerId: '',
    nickName: '',
    phoneNumber: '',
    avatarUrl: '',
    community:'',
    city:'',
    latitude: 23.099994,      //纬度
    longitude: 113.324520,    //经度
    markers: []
  },

  // 点击地图上的标记点时显示团长信息
  backup: function (e) {
    let that = this
    console.log(this.data.markers[e.markerId].headerId)
    that.setData({
      headerId: this.data.markers[e.markerId].headerId,
      nickName: this.data.markers[e.markerId].nickName,
      phoneNumber: this.data.markers[e.markerId].phoneNumber,
      city: this.data.markers[e.markerId].city,
      community: this.data.markers[e.markerId].community,
      headId: this.data.markers[e.markerId].headId,
      avatarUrl: this.data.markers[e.markerId].avatarUrl
    })
    
  },

  // 选择完团长,点击确定按钮提交信息
  bakcuptap: function (e) {
    var that = this;
  //   var pages = getCurrentPages(); // 获取页面栈
  //   var head = pages[pages.length - 2]; // 上一个页面
    

  //   head.setData({
  //     headId: e.currentTarget.dataset.headId,
  //     nickname: e.currentTarget.dataset.nickname,
  //     address: e.currentTarget.dataset.address,
  //     phone: e.currentTarget.dataset.phone,
  //     avatar: e.currentTarget.dataset.avatar
  //   })

    
    util.request(api.UpdateUserHeaderId, {
      headerId: that.data.headerId,
      userId: wx.getStorageSync('userId')
    }).then(function (res) {
      if (res.status === 1) {
        wx.setStorageSync('selectHeadId', that.data.headerId)
        // wx.setStorageSync('headerId', this.data.headerId)
        wx.setStorageSync('headerNickName', that.data.nickName)
        wx.setStorageSync('headerPhoneNumber', that.data.phoneNumber)
        wx.setStorageSync('headerAvatarUrl', that.data.avatarUrl)
      }
    });
    wx.switchTab({
      url: '/pages/home/home'
    })
  },

  onLoad: function (e) {
    this.mapCtx = wx.createMapContext('myMap')
  },

  onShow: function (e) {
    this.getlocal()
  },

  getlocal: function () {
    var that = this
    var data = new Object();
    data.markers = []
    wx.getLocation({
      success: function (res) {
        that.setData({ userLatitude: res.latitude, userLongitude: res.longitude })
        util.request(
          api.GetHeaderList, 
          { 
            latitude: that.data.latitude, 
            longitude: that.data.userLongitude  
          },
          "GET").then(function (res) {
            if (res.status === 1) {
            for (var i = 0; i < res.data.length; i++) {
              data.markers.push({
                id: i,
                latitude: res.data[i].latitude,   //地图上标点的纬度
                longitude: res.data[i].longitude, //地图上标点的经度
                iconPath: '/static/images/location.png',  //地图上标记点的图片，样式
                callout: {
                  content: res.data[i].nickName,  //标记点上方文字
                  display: 'ALWAYS'                       //总是显示
                },
                nickName: res.data[i].nickName,
                // address: res.data[i].address,
                phoneNumber: res.data[i].phoneNumber,
                avatarUrl: res.data[i].avatarUrl,
                headerId: res.data[i].headerId,
                city: res.data[i].city,
                community: res.data[i].community
              })
            }
            console.log(data.markers)
            // console.log(res.data.markers[1].avatar)
            that.setData(data);
          }
        });
      },
      fail: function (res) {
        console.log("当前位置经纬度获取失败")
        // wx.getSetting({
        //   success: (res) => {
        //     if (!res.authSetting['scope.userLocation'])
        //       that.openConfirm()
        //   }
        // })
      }
    })
  },

  // 定位权限关闭，弹窗提醒允许授权
  // openConfirm: function () {
  //   wx.openSetting({
  //     success: (res) => { }
  //   })
  //   // wx.showModal({
  //   //   content: '检测到您没打开定位权限，是否去设置打开？',
  //   //   confirmText: "确认",
  //   //   cancelText: "取消",
  //   //   success: function (res) {
  //   //     console.log(res);
  //   //     //点击“确认”时打开设置页面
  //   //     if (res.confirm) {
  //   //       console.log('用户点击确认')

  //   //       wx.openSetting({
  //   //         success: (res) => { }
  //   //       })
  //   //     } else {
  //   //       console.log('用户点击取消')
  //   //     }
  //   //   }
  //   // });
  // },

  // bakcuptaps: function () {
  //   var that = this
  //   that.setData({ a: 119.54967498779297, b: 35.408138275146484 })
  // },

  // getCenterLocation: function () {
  //   var that = this
  //   this.mapCtx.getCenterLocation({
  //     success: function (res) {
  //       that.setData({ a: res.latitude })
  //       console.log(res.longitude)
  //       console.log(res.latitude)
  //     }
  //   })
  // },

  // moveToLocation: function () {
  //   this.mapCtx.moveToLocation()
  // },

  // translateMarker: function () {
  //   this.mapCtx.translateMarker({
  //     markerId: 1,
  //     autoRotate: true,
  //     duration: 1000,
  //     destination: {
  //       latitude: 23.10229,
  //       longitude: 113.3345211,
  //     },
  //     animationEnd() {
  //       console.log('animation end')
  //     }
  //   })
  // },

  // includePoints: function () {
  //   wx.chooseLocation({
  //     success: function (res) {
  //       console.log(res.name)
  //     },
  //   })
  // }
})
