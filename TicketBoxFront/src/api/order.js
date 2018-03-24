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
  console.log('payOrder')
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

export function cancelOrder (callback, body) {
  console.log('cancelOrder')
  // console.log('login')
  axios.post('/user/order/cancel/',
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

export function refundOrder (callback, body) {
  console.log('refundOrder')
  // console.log('login')
  axios.post('/user/order/refund/',
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

export function getAllOrders (callback, body) {
  console.log('getAllOrders')
  // console.log('login')
  axios.post('/user/order/all/',
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
