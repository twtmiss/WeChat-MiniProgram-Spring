const util = require('../../utils/fun.js');
const api = require('../../utils/config.js');

//获取应用实例
// const app = getApp()
Page({
  // "enablePullDownRefresh": true,
  data: {
    commodityList:[],
    nickname: '',
    city:'',
    address: '',
    phone: '',
    avatar: '',
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
    windowWidth: wx.getSystemInfoSync().windowWidth
  },

  // 页面加载
  onLoad: function (options) {
    // this.getHeadInfoByUserId();
    // this.getHeadCommodityByHeadId();
  },
  onShow:function(){
    this.getHeadInfoByUserId();
    this.getHeadCommodityByHeadId();
  },

  // 根据用户id获取选择的团长信息
  getHeadInfoByUserId: function(){
    var that = this
    util.request(api.GetHeadInfoByUserId, {
      userId: wx.getStorageSync('userId')
    },"GET").then(function (res) {
      if (res.status === 1) {
        that.setData({ 
          nickname: res.data.headerNickName,
          city: res.data.headCity,
          address: res.data.headcommunity,
          phone: res.data.headerPhoneNumber,
          avatar: res.data.headerAvatarUrl,
        })
        wx.setStorageSync('selectHeadId', res.data.headerId)
        wx.setStorageSync('headerNickName', res.data.headerNickName)
        wx.setStorageSync('headerPhoneNumber', res.data.headerPhoneNumber)
        wx.setStorageSync('headerAvatarUrl', res.data.headerAvatarUrl)
      }
    });
  },

  // 根据团长Id获取团长发布的商品
  getHeadCommodityByHeadId:function(){
    var that = this
    util.request(api.GetHeadCommodityByHeadId, {
      headId: wx.getStorageSync('selectHeadId')
    }, "GET").then(function (res) {
      if (res.status === 1) {
        that.setData({ commodityList: res.data })
      }
    });
  },

  // 跳转到商品详情页面
  bind_product: function (event) {
    // console.log(event.currentTarget.dataset.commodityid)
    wx.navigateTo({
      url: '/pages/commodity/viewCommodity/viewCommodity?commodityId=' 
            + event.currentTarget.dataset.commodityid,
    })
  },

  // 选择团长
  switch_head: function () {
    if (wx.getStorageSync("userInfo")) {
      wx.navigateTo({
        url: '/pages/user/selectHead/selectHead',
      })
    } else {
      wx.showToast({
        title: '未登录,请点击右下角"我的"登录',
        icon: 'none',
        duration: 2000
      })
    }
  },
















  // 商品列表
  //吸顶容器
  // onChange(event) {
  //   console.log(event.detail, 'click right menu callback data')
  // },
  //吸顶容器 页面滚动执行方式
  // onPageScroll(event) {
  //   this.setData({
  //     scrollTop: event.scrollTop
  //   })
  // },

  // // 吸顶 标签页
  // handleChange({ detail }) {
  //   this.setData({
  //     current: detail.key
  //   });
  // },

 

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