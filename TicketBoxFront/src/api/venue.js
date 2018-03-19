import axios from 'axios'

export function getVenueInfo (callback, body) {
  console.log('getVenueInfo')
  // console.log('login')
  axios.post('/venue/info/',
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

export function getAreaInfo (callback, body) {
  console.log('getAreaInfo')
  // console.log('login')
  axios.post('/venue/area/',
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

export function saveAreaInfo (callback, body) {
  console.log('saveAreaInfo')
  // console.log('login')
  axios.post('/venue/area/save/',
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
