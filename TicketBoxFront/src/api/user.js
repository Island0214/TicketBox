import axios from 'axios'

export function getUserInfo (callback, username) {
  console.log('getUserInfo')
  // console.log('login')
  axios.get('/user/info/' + username,
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

export function getVipDiscount (callback, grade) {
  console.log('getVipDiscount')
  // console.log('login')
  axios.get('/user/vip/' + grade,
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

export function buyTicketOffline (callback, body) {
  console.log('buyTicketOffline')
  // console.log('login')
  axios.post('/user/buyTicket/offline/', body,
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

export function getAllUsers (callback) {
  console.log('getAllUsers')
  // console.log('login')
  axios.get('/user/all/',
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

export function banUser (callback, body) {
  console.log('banUser')
  // console.log('login')
  axios.post('/manager/user/ban/', body,
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
