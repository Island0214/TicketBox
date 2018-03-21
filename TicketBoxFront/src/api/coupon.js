import axios from 'axios'

export function getAllCoupons (callback) {
  console.log('getAllCoupons')
  // console.log('login')
  axios.get('/user/coupon/all',
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
    .then(function (response) {
      console.log(response.data)
      callback(response.data)
    })
    .catch(function (error) {
    })
}

export function getMyCoupons (callback, username) {
  console.log('getMyCoupons')
  // console.log('login')
  axios.get('/user/coupon/my/' + username,
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
    .then(function (response) {
      console.log(response.data)
      callback(response.data)
    })
    .catch(function (error) {
    })
}

export function exchangeCoupon (callback, body) {
  console.log('exchangeCoupon')
  // console.log('login')
  axios.post('/user/coupon/exchange',
    JSON.parse(JSON.stringify(body)),
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
    .then(function (response) {
      console.log(response.data)
      callback(response.data)
    })
    .catch(function (error) {
    })
}
