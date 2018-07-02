import axios from 'axios'

export function payBalance (callback, body) {
  console.log('payBalance')
  // console.log('login')
  axios.post('/manager/balance/pay/',
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

export function getPaidSchedules (callback, schedule, area) {
  console.log('getUnpaidSchedules')
  // console.log('login')
  axios.get('/manager/schedules/paid',
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
