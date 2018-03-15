import axios from 'axios'

export function userLogin (callback, body) {
  console.log('userLogin')
  // console.log('login')
  axios.post('/user/login/',
    JSON.parse(JSON.stringify(body)),
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
    .then(function (response) {
      callback(response.data)
    })
    .catch(function (error) {
    })
}

export function userRegister (callback, body) {
  console.log('userRegister')
  // console.log('login')
  console.log(body)
  axios.post('/user/register/',
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
