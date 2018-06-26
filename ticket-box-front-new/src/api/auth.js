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

export function managerLogin (callback, body) {
  console.log('managerLogin')
  // console.log('login')
  axios.post('/manager/login/',
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

export function venueLogin (callback, body) {
  console.log('venueLogin')
  // console.log('login')
  axios.post('/venue/login/',
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

export function userAuthenticate (callback, username) {
  console.log('userAuthenticate')
  // console.log('login')
  axios.get('/user/authenticate/' + username,
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

export function venueRegister (callback, body) {
  console.log('venueRegister')
  // console.log('login')
  console.log(body)
  axios.post('/venue/register/',
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
