// pages/activity/activity/activity.js
const app = getApp()
var utils = require('../../../utils/fun.js');
const config = require('../../../utils/config.js');
const { $Toast } = require('../../../a_plugin/iview/base/index');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    articleId:0,
    articleMarkdown:"",
    coverImgUrl:"",
    isEdit:0,
  },

  // 获取文章
  getArticle:function(articleId){
    var that = this
    utils.request(config.GetActivityByArticleId, {
      articleId: that.data.articleId
    }, 'GET').then(function (res) {
      if (res.status === 1) {
        that.setData({ article: res.data })
      }
    });
  },

  // 提交按钮点击事件
  handleOpen:function(){
    var that = this
      that.uploadText()
  },

  // 上传文本
  uploadText: function (e) {
    console.log("开始上传文本")
    if (this.data.isEdit == 1) {
      var url = config.PostUpdateActivity
    }
    if (this.data.isEdit == 2){
      var url = config.PostCreateActivity
    }

    utils.request(url,
      {
        headId:wx.getStorageSync("headId"),
        articleId: this.data.articleId,
        articleMarkdown: this.data.articleMarkdown,
        coverImgUrl: this.data.coverImgUrl
      },'Post').then(function (res) {
        if(res.status===1){
          $Toast({
            content: '提交成功，2秒后返回',
            type: 'success'
          });
          setTimeout(() => {
            wx.navigateBack({
              delta: 2
            })
          }, 2000);
        }
      });
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that =this
    console.log(options)
    if (options.isEdit == 0) {  //查看
      that.setData({
        articleId: options.articleId
      })
      that.getArticle(that.data.articleId)
    }
    if (options.isEdit == 1){ //编辑
      that.setData({ 
        isEdit: 1,
        articleMarkdown: options.articleMarkdown,
        coverImgUrl: options.coverImgUrl,
        articleId: options.articleId
      })
    }
    if (options.isEdit == 2){ //新建
        that.setData({
          isEdit: 2,
          articleMarkdown: options.articleMarkdown,
          coverImgUrl: options.coverImgUrl,
        })
      }
    else{
      
    }
    
  },

    /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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