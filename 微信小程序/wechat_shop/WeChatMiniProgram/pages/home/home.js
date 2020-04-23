const util = require('../../utils/fun.js');
const api = require('../../utils/config.js');

//获取应用实例
// const app = getApp()
Page({
  // "enablePullDownRefresh": true,
  data: {

    // currentTab: 0,
    // spot: [],
    // head: [],
    // headId: '',
    // userId: '',
    // nickname: '',
    // address: '',
    // phone: '',
    // avatar: '',
    // presale: [],
    // newGoods: [],
    // hotGoods: [],
    // topics: [],
    // brands: [],
    // floorGoods: [],
    // banner: [],
    // channel: [],
    current: 'tab1',
    scrollTop: 0,
    current: 1,
    imageList: [
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/1.png',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/2.png',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/3.png',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/4.png',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/5.png',
        mode: "widthFix"
      }
    ],
    imageList2: [
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/2.png',
        content: '红米7大电量18个月质保智能学生老年人全网通手机小米官方旗舰店redmi7A官网正品xiaomi红米note7',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/4.png',
        content: 'OPPO K3 oppok3手机全新机新款上市oppok3限量超薄oppoa5手机 a7x r17oppor15x 0ppok3r90pp0k1',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/1.png',
        content: '12期分期可减540当天发Huawei/华为 P30手机官方旗舰店正品p30荣耀p30pro直降mate20x新款5g全网通p10 p20pro',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/3.png',
        content: '【选送小米27W快充头】Xiaomi/小米 Redmi K20Pro骁龙855红米K20手机官方旗舰弹出全面屏4800万广角三摄note7',
        mode: "widthFix"
      },
      {
        url: 'https://activity.vtuzx.com/doc/vtuUI/weapp/swiper/5.png',
        content: '可12期免息【抢360券+送无线充】xiaomi/小米9手机plus官方旗舰店骁龙855透明尊享版九Mix4红米K20小米9学生',
        mode: "widthFix"
      }
    ],
    windowWidth: wx.getSystemInfoSync().windowWidth
  },

  // 商品列表
  //吸顶容器
  // onChange(event) {
  //   console.log(event.detail, 'click right menu callback data')
  // },
  //吸顶容器 页面滚动执行方式
  onPageScroll(event) {
    this.setData({
      scrollTop: event.scrollTop
    })
  },

  // 吸顶 标签页
  handleChange({ detail }) {
    this.setData({
      current: detail.key
    });
  },
  bind_product:function(){
    wx.navigateTo({
      url: '/pages/productDetail/productDetail',
    })
  },
  switch_head: function () {
    wx.navigateTo({
      url: '/pages/selectHead/selectHead',
    })
  },

  // onShareAppMessage: function () {
  //   return {
  //     title: 'NideShop',
  //     desc: '微信小程序商城',
  //     path: '/pages/index/index'
  //   }
  // },
  // onPullDownRefresh() {
  //   // 增加下拉刷新数据的功能
  //   console.log("开始刷新")
  //   this.getIndexData();
  // },

  // tap_nearby: function (e) {
  //   wx.navigateTo({
  //     url: '/pages/selectHead/selectHead',
  //   })
  // },
  // backup: function (e) {
  //   wx.navigateTo({
  //     url: '/pages/goodst/goodst?productId=' + e.currentTarget.dataset.productid,
  //   })
  // },
  // getIndexData: function () {
  //   let that = this;
  //   var data = new Object();
  //   if (wx.getStorageSync('headId') != '') {

  //     var headOb = {
  //       headId: wx.getStorageSync('headId'),
  //       nickname: wx.getStorageSync('nickname'),
  //       address: wx.getStorageSync('address'),
  //       phone: wx.getStorageSync('phone'),
  //       avatar: wx.getStorageSync('avatar')
  //     }
  //     that.setData({
  //       head: headOb
  //     })
  //   }

  //   util.request(api.TmsIndexUrlHeadDetail, { userId: wx.getStorageSync("userId") }).then(function (res) {
  //     if (res.errno === 0) {
  //       data.head = res.data.headInfo
  //       that.setData(data);
  //       if (res.data.headInfo.head_id != null) {
  //         wx.setStorageSync('headId', res.data.headInfo.head_id)
  //       }
  //     }
  //   });


  //   util.request(api.TmsIndexUrlSpot, { userId: wx.getStorageSync('userId') }).then(function (res) {
  //     if (res.errno === 0) {
  //       data.spot = res.data
  //       that.setData(data);
  //     }
  //   });
  //   util.request(api.TmsIndexUrlBanner).then(function (res) {
  //     if (res.errno === 0) {
  //       data.banner = res.data.banner
  //       that.setData(data);
  //     }
  //   });
  //   if (wx.getStorageSync('cartId') == '') {
  //     util.request(api.TmsGetUserCardId, { userId: wx.getStorageSync('userId') }).then(function (res) {
  //       if (res.errno === 0) {
  //         if (res.cartId != "" && res.cartId != 0) {
  //           wx.setStorageSync('cartId', res.data)
  //         }
  //       }
  //     });
  //   }
  // },
  // isOnLogin: function () {
  //   wx.showModal({
  //     content: '检测到您没有授权，是否要授权登录？',
  //     confirmText: "确认",
  //     cancelText: "取消",
  //     success: function (res) {
  //       console.log(res);
  //       //点击“确认”时打开设置页面
  //       if (res.confirm) {
  //         console.log('用户点击确认')
  //         wx.navigateTo({
  //           url: '/pages/auth/btnAuth/btnAuth',
  //         })
  //       } else {
  //         console.log('用户点击取消')
  //       }
  //     }
  //   });
  // },
  // onLoad: function (options) {
  //   // 页面显示
  //   var that = this;
  // },
  // onReady: function () {
  //   // 页面渲染完成
  // },
  // onShow: function () {
  //   var that = this
  //   var data = new Object();
  //   // console.log("asdasdasd")
  //   // console.log(wx.getStorageSync("userId"))
  //   // // setTimeout(function () {
  //   // if (wx.getStorageSync("userId") == "") {
  //   //   this.isOnLogin();
  //   // } else {
  //   //   this.getIndexData();
  //   // }
  //   // console.log("cvyubhnjk")
  //   // }, 1000) //延迟时间 这里是1秒
  // },
  // onHide: function () {
  //   // 页面隐藏
  // },
  // onUnload: function () {
  //   // 页面关闭
  // },
})