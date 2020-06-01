// pages/commodity/viewCommodity
const util = require('../../../utils/fun.js');
const api = require('../../../utils/config.js');
const { $Toast } = require('../../../a_plugin/iview/base/index');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    
    bottom_show: true,
    address_mask: false,
    spe_mask: false,
    headId: "",
    commodityId: "",
    commodityImgUrl: "",
    commodityName: "",
    commodityDescription: "",
    commodityDetails: "",
    defaultSpecification: "",
    defaultPrice: "",
    defaultSpecificationImg: "",
    selectSpecificationId:0,
    buyNumber:1,
    specification: "",
    isEdit:0,
  },

  onLoad: function (options) {
    // this.getCommodityByCommodityId(options.commodityId)
    if(options.isEdit==1){
      var that =this 
      that.setData({
        isEdit:1
      })
      this.getCommodityByCommodityId(options.commodityId)
    }else{
      this.getCommodityByCommodityId(options.commodityId)
    }
  },

  onShow: function () {

  },

  // 获取商品详情
  getCommodityByCommodityId:function(opt){
    var that = this
    util.request(api.GetCommodityByCommodityId, {
      commodityId: opt
    }, "GET").then(function (res) {
      if (res.status === 1) {
        that.setData({ 
          headId: res.data.headId,
          commodityId: res.data.commodityId,
          commodityImgUrl: res.data.commodityImgUrl,
          commodityName: res.data.commodityName,
          commodityDescription: res.data.commodityDescription,
          commodityDetails: res.data.commodityDetails,
          defaultSpecification: res.data.specification[0].specificationDetail,
          defaultPrice: res.data.specification[0].specificationPrice,
          defaultSpecificationImgUrl: res.data.specification[0].specificationImgUrl,
          specification: res.data.specification,
          selectSpecificationId: res.data.specification[0].specificationId
          })
      }
    });
  },

  // 商品购买数量
  minusNumber: function () {
    var that = this
    that.setData({
      buyNumber: that.data.buyNumber - 1
    })
  },
  plusNumber:function(){
    var that = this
    that.setData({
      buyNumber: that.data.buyNumber + 1
    })
  },

  //选择商品规格
  selectSpecification:function(even){
    var selectId = even.currentTarget.dataset.specificationid
    var index = even.currentTarget.dataset.index
    // console.log(selectId)
    // console.log(even.currentTarget.dataset.index)
    var that = this
    that.setData({
      selectSpecificationId: selectId,
      defaultSpecificationImgUrl: that.data.specification[index].specificationImgUrl,
      defaultSpecification: that.data.specification[index].specificationDetail,
      defaultPrice: that.data.specification[index].specificationPrice,
    })
    
  },

  // 将商品加入购物车
  addCart:function(){
    var that = this
    util.request(api.PostAddCart, {
      userId: wx.getStorageSync("userId"),
      headId: wx.getStorageSync("selectHeadId"),
      commodityId: that.data.commodityId,
      specificationId: that.data.selectSpecificationId,
      number: that.data.buyNumber
    }, "POST").then(function (res) {
      if (res.status === 1) {
        // 弹出轻提示，2秒后隐藏
        $Toast({
          content: '加入成功',
          type: 'success'
        });
        setTimeout(() => {
          $Toast.hide();
        }, 1000);
        that.bntSpeEnd()
      }
    });
  },

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
  bntSpeEnd: function () {
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

  // scrollTop: 0,
  //   city: ['滨州市', '德州市', '东营市', '菏泽市', '济宁市', '济南市', '聊城市', '莱芜市', '临沂市', '青岛市', '日照市', '泰安市', '潍坊市', '威海市', '烟台市', '枣庄市', '淄博市'],
  // region: ['东港区', '莒县', '岚山区', '五莲县', '新市区'],
  // community: ['城区', '陈疃镇', '石臼街道', '河山镇', '两城街道', '南湖镇', '秦楼街道', '日照街道', '三庄镇', '涛雒镇', '西湖镇',],
})