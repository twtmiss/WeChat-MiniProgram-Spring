// pages/shopping/shopping.js
const util = require('../../../utils/fun.js');
const api = require('../../../utils/config.js');
// import Toast from "../../../a_plugin/vtuweapp/toast/vtu-index";
const { $Toast } = require('../../../a_plugin/iview/base/index');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    commodityList:"",
    // selectList:[],
    buyList:[],
    editList:[],
    cartStatus:0,
    selectNumber:0,
    totalPrice:0,
  },

  onLoad: function (options) {
    this.getCommodityListByUserId()
  },

  // 获取购物车内商品
  getCommodityListByUserId: function () {
    var that = this
    util.request(api.GetCommodityListByUserId, {
      userId: wx.getStorageSync('userId')
    }, "GET").then(function (res) {
      if (res.status === 1) {
        var resSelectList=[]
        var list = []
        for (var i = 0; i <res.data.commodityList.length; i++) {
          resSelectList.push(res.data.commodityList[i].isSelect)
          list.push(0)
        }
        that.setData({
          commodityList: res.data.commodityList,
          buyList: resSelectList,
          editList: list,
          selectNumber: that.selectCommodityNumber(resSelectList),
          totalPrice: res.data.totalPrice
        })
        // console.log(typeof(that.data.commodityList))
      }
    });
  },

  // 选择商品
  selectCommodity:function(e){
    var that = this
    // 在非编辑状态下选择商品
    if(that.data.cartStatus==0){
      that.updateCart(
        e.currentTarget.dataset.headid,
        e.currentTarget.dataset.commodityid,
        e.currentTarget.dataset.specificationid,
        1,
        e.currentTarget.dataset.number,
      )
    }
    // 在编辑状态下选择商品
    else{
      var index = e.currentTarget.dataset.index
      var list = that.data.editList
      if (list[index]==0){
        list[index]=1
        that.setData({ editList: list, selectNumber: that.selectCommodityNumber(list) })
      }
      else{
        list[index] = 0
        that.setData({ editList: list, selectNumber: that.selectCommodityNumber(list)  })
      }
      // that.setData({editList: that.data.editList[index]})
    }
  },

  // 更改购买数量
  editNumber:function(e){
    var that = this
    if (e.currentTarget.dataset.status==0){
      // 减少数量
      var index = e.currentTarget.dataset.index

      if (that.data.commodityList[index].number == 1){
        // 轻提示
          // Toast('数量最少为1');
        $Toast({
          content: '商品数量最少为1，不能再减啦',
          type: 'warning'
        });
      }
      else{
        that.updateCart(
          that.data.commodityList[index].headId,
          that.data.commodityList[index].commodityId,
          that.data.commodityList[index].specificationId,
          0,
          that.data.commodityList[index].number - 1)
      }
    }
    if (e.currentTarget.dataset.status == 1) {
      // 减少数量
      var index = e.currentTarget.dataset.index
      // var number = that.data.buyList[index].number - 1
      that.updateCart(
        that.data.commodityList[index].headId,
        that.data.commodityList[index].commodityId,
        that.data.commodityList[index].specificationId,
        0,
        that.data.commodityList[index].number + 1)
    }
  },

  updateCart: function (headId, commodityId, specificationId, selectStatus,number){
    var that =this
    util.request(api.PostUpdateCommodity, {
      userId: wx.getStorageSync('userId'),
      headId: headId,
      commodityId: commodityId,
      specificationId: specificationId,
      selectStatus: selectStatus, // 0为不更新选择，1更新
      number: number
    }, "POST").then(function (res) {
      if (res.status === 1) {
        that.getCommodityListByUserId()
      }
    });
  },

  

  // 购物车结算
  bind_Settlement:function(){
    var that = this 
    if(that.data.cartStatus==0){
      console.log("123")
      wx.navigateTo({
        url: '/pages/order/confirm/confirm'
      })
    }
    if(that.data.cartStatus==1){
      // 删除商品
      // var map = that.data.commodityList
      var map = {
        "specificationId": "",
        "commodityId": "",
        "headId": ""
      }
      var requestList = []
      for(var i=0;i<that.data.editList.length;i++){
        if (that.data.editList[i]==1){
          map.specificationId = that.data.commodityList[i].specificationId
          map.commodityId = that.data.commodityList[i].commodityId
          map.headId = that.data.commodityList[i].headId
          requestList.push(map)
        }
      }
      console.log(requestList)
      // JSON.stringify
      // var requestMap ={
      //   "userId": wx.getStorageSync('userId'),
      //   "requestList": requestList,
      // }
      util.request(api.DeleteCart, {
        userId: wx.getStorageSync('userId'),
        requestMap: JSON.stringify(requestList),
      }, "DELETE","application/json").then(function (res) {
        if (res.status === 1) {
          that.getCommodityListByUserId()
          that.setData({cartStatus:0})
        }
      });
    }
    
  },

  // 改变购物车状态
  changeCartStatus:function(){
    var that = this
    
    that.setData({ 
      cartStatus: !that.data.cartStatus,
      selectNumber: that.data.cartStatus ? that.selectCommodityNumber(that.data.buyList) : 0
     })
  },

  // 计算选中数量
  selectCommodityNumber:function(li){
    var num = 0
    for (var i = 0; i < li.length; i++) {
      if (li[i]==1){
        num = num + 1
      }
    }
    return num
  },


  //
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getCommodityListByUserId()
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