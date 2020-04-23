// // const util = require('../../utils/util.js');
// // const api = require('../../config/api.js');
// // const user = require('../../services/user.js');
// const app = getApp()

Page({
  data: {
    a: '',
    b: '',
    head_id: '',
    nickname: '',
    address: '',
    phone: '',
    avatar: '',
    latitude: 23.099994,      //纬度
    longitude: 113.324520,    //经度
    markers: []
  },
  backup: function (e) {
    let that = this
    // that.setData({
    //   nickname: this.data.markers[e.markerId].nickname,
    //   address: this.data.markers[e.markerId].address,
    //   phone: this.data.markers[e.markerId].phone,
    //   head_id: this.data.markers[e.markerId].head_id,
    //   avatar: this.data.markers[e.markerId].avatar
    // })
    // wx.setStorageSync('nickname', e.currentTarget.dataset.nickname)
    // wx.setStorageSync('address', e.currentTarget.dataset.address)
    // wx.setStorageSync('phone', e.currentTarget.dataset.phone)
    // wx.setStorageSync('avatar', e.currentTarget.dataset.avatar)
  },
  bakcuptap: function (e) {
  //   var that = this;
  //   var pages = getCurrentPages(); // 获取页面栈
  //   var head = pages[pages.length - 2]; // 上一个页面
  //   wx.setStorageSync('headId', this.data.head_id)

  //   head.setData({
  //     // headId: e.currentTarget.dataset.headId,
  //     nickname: e.currentTarget.dataset.nickname,
  //     address: e.currentTarget.dataset.address,
  //     phone: e.currentTarget.dataset.phone,
  //     avatar: e.currentTarget.dataset.avatar
  //   })

  //   util.request(api.TmsUpdateUserHeadId, {
  //     headId: this.data.head_id,
  //     userId: wx.getStorageSync('userId')
  //   }).then(function (res) {
  //     if (res.errno === 0) {
  //       that.setData({
  //         cartGoods: res.data.cartGoods,
  //         cartTotal: res.data.cartTotal
  //       });
  //     }
  //   });
    wx.switchTab({
      url: '/pages/home/home'
    })
  },
  onLoad: function (e) {
    this.mapCtx = wx.createMapContext('myMap')


  },
  onShow: function (e) {
    wx.getSetting({
      success: (res) => {
        console.log(res.authSetting['scope.userInfo'])
        if (!res.authSetting['scope.userInfo']) {
          this.openUserConfirm()

        } else {
          this.getlocal()
        }
      }
    })
  },
  getlocal: function () {
    var that = this
    var data = new Object();
    data.markers = []
    wx.getLocation({
      success: function (res) {
        that.setData({ a: res.latitude, b: res.longitude })
  //       util.request(api.TmsIndexUrlHeadList, { longitude: that.data.b, latitude: that.data.a }).then(function (res) {
  //         if (res.errno === 0) {
  //           for (var i = 0; i < res.data.markers.length; i++) {
  //             data.markers.push({
  //               id: i,
  //               latitude: res.data.markers[i].latitude,   //地图上标点的纬度
  //               longitude: res.data.markers[i].longitude, //地图上标点的经度
  //               iconPath: '/static/images/location.png',  //地图上标记点的图片，样式
  //               callout: {
  //                 content: res.data.markers[i].nickname,  //标记点上方文字
  //                 display: 'ALWAYS'                       //总是显示
  //               },
  //               nickname: res.data.markers[i].nickname,
  //               address: res.data.markers[i].address,
  //               phone: res.data.markers[i].phone,
  //               avatar: res.data.markers[i].avatar,
  //               head_id: res.data.markers[i].head_id,
  //               city: res.data.markers[i].city,
  //               region: res.data.markers[i].region
  //             })
  //           }
  //           console.log(data.markers)
  //           console.log(res.data.markers[1].avatar)
  //           that.setData(data);
  //         }
  //       });
      },
      fail: function (res) {
        console.log("当前位置经纬度获取失败")
        wx.getSetting({
          success: (res) => {
            if (!res.authSetting['scope.userLocation'])
              that.openConfirm()
          }
        })
      }
    })
  },
  // 定位权限关闭，弹窗提醒允许授权
  openConfirm: function () {
    wx.showModal({
      content: '检测到您没打开定位权限，是否去设置打开？',
      confirmText: "确认",
      cancelText: "取消",
      success: function (res) {
        console.log(res);
        //点击“确认”时打开设置页面
        if (res.confirm) {
          console.log('用户点击确认')
          wx.openSetting({
            success: (res) => { }
          })
        } else {
          console.log('用户点击取消')
        }
      }
    });
  },
  // 获取用户信息权限关闭，弹窗提醒允许授权
  // openUserConfirm: function () {
  //   wx.showModal({
  //     content: '检测到您没允许获得用户信息，是否去设置打开？',
  //     confirmText: "确认",
  //     cancelText: "取消",
  //     success: function (res) {
  //       console.log(res);
  //       //点击“确认”时打开设置页面
  //       if (res.confirm) {
  //         console.log('用户点击确认')
  //         wx.redirectTo({
  //           url: '/pages/auth/btnAuth/btnAuth',
  //         })
  //       } else {
  //         console.log('用户点击取消')
  //       }
  //     }
  //   });
  // },
  bakcuptaps: function () {
    var that = this
    that.setData({ a: 119.54967498779297, b: 35.408138275146484 })
  },
  getCenterLocation: function () {
    var that = this
    this.mapCtx.getCenterLocation({
      success: function (res) {
        that.setData({ a: res.latitude })
        console.log(res.longitude)
        console.log(res.latitude)
      }
    })
  },

  moveToLocation: function () {
    this.mapCtx.moveToLocation()
  },

  translateMarker: function () {
    this.mapCtx.translateMarker({
      markerId: 1,
      autoRotate: true,
      duration: 1000,
      destination: {
        latitude: 23.10229,
        longitude: 113.3345211,
      },
      animationEnd() {
        console.log('animation end')
      }
    })
  },
  includePoints: function () {
    wx.chooseLocation({
      success: function (res) {
        console.log(res.name)
      },
    })
  }
})
