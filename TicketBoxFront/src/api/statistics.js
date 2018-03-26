import axios from 'axios'

export function getOrderStatistics (callback, username) {
  console.log('getOrderStatistics')
  // console.log('login')
  axios.get('/user/order/statistic/' + username + '/',
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

export function getUserStatistics (callback, username) {
  console.log('getUserStatistics')
  // console.log('login')
  axios.get('/user/consumption/statistic/' + username + '/',
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


export function getVenueOrderStatistic (callback, code) {
  console.log('getVenueOrderStatistic')
  // console.log('login')
  axios.get('/venue/order/statistic/' + code + '/',
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

export function getDayOrderStatistic (callback, code) {
  console.log('getDayOrderStatistic')
  // console.log('login')
  axios.get('/venue/day/statistic/' + code + '/',
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

export function getVenueRefundOrder (callback, code) {
  console.log('getVenueRefundOrder')
  // console.log('login')
  axios.get('/venue/order/refund/' + code + '/',
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

export function getFinanceStatistic (callback, code) {
  console.log('getFinanceStatistic')
  // console.log('login')
  axios.get('/venue/finance/statistic/' + code + '/',
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

export function getScheduleCountByVenue (callback) {
  console.log('getScheduleCountByVenue')
  // console.log('login')
  axios.get('/manager/schedule/count/',
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


export function getVenueAverageIncome (callback) {
  console.log('getVenueAverageIncome')
  // console.log('login')
  axios.get('/manager/venue/income/',
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

export function getVenueAverageOccupy (callback) {
  console.log('getVenueAverageOccupy')
  // console.log('login')
  axios.get('/manager/venue/occupy/',
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
