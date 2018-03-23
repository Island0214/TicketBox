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
