import axios from 'axios'

export function createOrder (callback, body) {
  console.log('exchangeCoupon')
  // console.log('login')
  axios.post('/user/order/create/',
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

export function getOrderById (callback, body) {
  console.log('getOrderById')
  // console.log('login')
  axios.post('/user/order/info/',
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

export function payOrder (callback, body) {
  console.log('getOrderById')
  // console.log('login')
  axios.post('/user/order/pay/',
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
