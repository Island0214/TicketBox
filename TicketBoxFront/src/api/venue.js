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
