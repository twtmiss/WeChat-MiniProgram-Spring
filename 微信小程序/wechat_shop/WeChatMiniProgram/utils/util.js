module.exports = {
  request: request,
}

/**
 * 封封微信的的request
 */
function request(url, data = {}, method = "POST", header = "application/x-www-form-urlencoded") {
  wx.showLoading({
    title: '加载中...',
  });
  return new Promise(function (resolve, reject) {
    wx.request({
      url: url,
      data: data,
      method: method,
      header: {
        'Content-Type': header,
        'X-Nideshop-Token': wx.getStorageSync('token')
      },
      success: function (res) {
        wx.hideLoading();
        if (res.statusCode == 200) {

          // if (res.data.errno == 401) {
          //   wx.navigateTo({
          //     url: '/pages/auth/btnAuth/btnAuth',
          //   })
          // } else {
            resolve(res.data);
          // }
        } else {
          reject(res.errMsg);
        }

      },
      fail: function (err) {
        reject(err)
      }
    })
  });
}