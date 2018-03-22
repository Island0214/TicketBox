import axios from 'axios'

export function dispatchSchedule (callback, body) {
  console.log('dispatchSchedule')
  // console.log('login')
  axios.post('/venue/schedule/dispatch/',
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

export function setScheduleSeat (callback, body) {
  console.log('setScheduleSeat')
  // console.log('login')
  axios.post('/venue/schedule/seats/',
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

export function getVenueSchedules (callback, venue) {
  console.log('getVenueSchedules')
  // console.log('login')
  axios.get('/venue/schedules/' + venue,
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

export function getScheduleInfo (callback, schedule) {
  console.log('getVenueSchedules')
  // console.log('login')
  axios.get('/venue/schedule/' + schedule,
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

export function getAreaInfoOfSchedule (callback, schedule, area) {
  console.log('getAreaInfo')
  // console.log('login')
  axios.get('/venue/seats/' + schedule + '/' + area,
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

export function getUnpaidSchedules (callback, schedule, area) {
  console.log('getUnpaidSchedules')
  // console.log('login')
  axios.get('/manager/schedules/unpaid',
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

export function getNewestThreeSchedules (callback, schedule, area) {
  console.log('getNewestThreeSchedules')
  // console.log('login')
  axios.get('/user/schedule/new',
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

export function getClosestThreeSchedules (callback, schedule, area) {
  console.log('getClosestThreeSchedules')
  // console.log('login')
  axios.get('/user/schedule/close',
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

export function getSchedulePriceInfo (callback, schedule) {
  console.log('getSchedulePriceInfo')
  // console.log('login')
  axios.get('/user/schedule/info/' + schedule,
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
