import axios from 'axios'

export function getSeatById (callback) {
  console.log('getSeatById')
  // console.log('login')
  axios.get('/venue/ad',
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
