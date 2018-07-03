import axios from 'axios'

export function getSeatById (callback, body) {
  console.log('getSeatById')
  // console.log('login')
  console.log(body)
  axios.post('/venue/getSeatById',
    body,
    {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }
  )
    .then(function (response) {
      // console.log(response.data)
      callback(response.data)
    })
    .catch(function (error) {
    })
}

export function getSeatByPosition (callback, body) {
  console.log('getSeatByPosition')
  // console.log('login')
  axios.post('/venue/getSeatByPosition',
    JSON.parse(JSON.stringify(body)),
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
    .then(function (response) {
      // console.log(response.data)
      callback(response.data)
    })
    .catch(function (error) {
    })
}
