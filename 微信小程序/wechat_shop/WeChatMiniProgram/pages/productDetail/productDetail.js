// pages/productDetail/productDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // scrollTop: 0,
    city: ['滨州市', '德州市', '东营市', '菏泽市', '济宁市', '济南市', '聊城市', '莱芜市', '临沂市', '青岛市', '日照市', '泰安市', '潍坊市', '威海市', '烟台市', '枣庄市', '淄博市'],
    region: ['东港区', '莒县', '岚山区', '五莲县', '新市区'],
    community: ['城区', '陈疃镇', '石臼街道', '河山镇', '两城街道', '南湖镇', '秦楼街道', '日照街道', '三庄镇', '涛雒镇', '西湖镇',],
    bottom_show: true,
    address_mask: false,
    spe_mask: false
  },
  // //吸顶容器 页面滚动执行方式
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

  // 控制商品选择 view的 弹出
  bntSpeStart: function () {
    var animation = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
      delay: 0
    });
    animation.opacity(1).translate(0, -500).step()
    this.setData({
      specificationAni: animation.export(),
      spe_mask: true,
      bottom_show: false
    })
  },
  // 控制商品选择 view的 收回
  bntSpeEnd:function(){
    console.log("bntSpeEnd")
    var animation = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
      delay: 0
    });
    animation.opacity(1).translate(0, 500).step()
    this.setData({
      specificationAni: animation.export(),
      spe_mask: false,
      bottom_show: true
    })
  },

  // 控制收货地址 view的 弹出
  bntAddressStart: function () {
    var animation = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
      delay: 0
    });
    animation.opacity(1).translate(0, -350).step()
    this.setData({
      addressAni: animation.export(),
      address_mask: true,
      bottom_show: false
    })
  },
  // 控制收货地址 view的 收回
  bntAddressEnd: function () {
    console.log("bntAddressEnd")
    var animation = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
      delay: 0
    });
    animation.opacity(1).translate(0, 350).step()
    this.setData({
      addressAni: animation.export(),
      address_mask: false,
      bottom_show: true
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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