// pages/activity/editactivity/editactivity.js
const app = getApp()
var utils = require('../../../utils/fun.js');
const config = require('../../../utils/config.js');


Page({

  /**
   * 页面的初始数据
   */
  data: {
    articleId:0,
    coverImgUrl: "http://127.0.0.1:8000/images/setCoverImg.png",
    articleMarkdown: "",
    isEdit:0,
  },

  // 获取文章
  getArticle: function (articleId) {
    var that = this
    utils.request(config.GetActivityByArticleId, {
      articleId: that.data.articleId
    }, 'GET').then(function (res) {
      if (res.status === 1) {
        that.setData({ coverImgUrl: res.data.coverImgUrl, articleMarkdown: res.data.articleMarkdown })
      }
    });
  },

  // textarea失去焦点时更新value的值，暂时没找到其他获取值的方法
  bindTextAreaBlur: function (e) {
    const that = this
    that.setData({ articleMarkdown: e.detail.value })
  },

  //设置封面图片
  setCoverImgUrl: function () {
    var that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        console.log(res)
        that.uploadImg(res.tempFilePaths[0], 0)
      }
    })
  }, 

  // 插入图片
  bindInsertImage: function () {
    const that = this
    wx.chooseImage({
      count: 1,
      success: function (res) {
        that.uploadImg(res.tempFilePaths[0],1)
      }
    })
  },

  // 预览
  bindPreview: function () {
    var that = this
    wx.navigateTo({
      url: '/pages/activity/activity/activity'
        + '?articleMarkdown=' + that.data.articleMarkdown
        + '&articleId=' + that.data.articleId
        + '&coverImgUrl=' + that.data.coverImgUrl
        + '&isEdit=' + that.data.isEdit
    })
  },

  // 上传图片
  uploadImg: function (e,f) {
    var that = this
    wx.uploadFile({
      url: config.PostUploadFile, //仅为示例，非真实的接口地址
      filePath: e,
      name: 'file',
      success: function (res) {
        if(f==0){
          that.setData({ coverImgUrl: res.data})
        }
        if(f==1){
          var str = that.data.articleMarkdown
          that.setData({ articleMarkdown: str + "\n\n" + "![](" + res.data + ")" })
        }
        
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.setData({ articleId: options.articleId, isEdit: options.isEdit })
    if (that.data.articleId != 0) {
      that.getArticle(that.data.articleId)
    }
  },

  onShow: function () {
    
  },

})