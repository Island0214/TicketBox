import axios from 'axios'

export function searchScheduleByPage (callback, body) {
  console.log('searchScheduleByPage')
  // console.log('login')
  axios.post('/user/schedule/search/',
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

export function getTourScheduleById (callback, tour) {
  // console.log(tour)
  console.log('getTourScheduleById')
  // console.log('login')
  axios.get('/venue/tourSchedule/' + tour,
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

