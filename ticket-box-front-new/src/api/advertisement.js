import axios from 'axios'

export function getAds (callback) {
    console.log('getAds')
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

export function getHotSchedules (callback) {
  console.log('getHotSchedules')
  // console.log('login')
  axios.get('/venue/hotSchedules',
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

export function getComingSchedules (callback) {
  console.log('getComingSchedules')
  // console.log('login')
  axios.get('/venue/comingSchedules',
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

export function getRandomFiveTours (callback) {
  console.log('getRandomFiveTours')
  // console.log('login')
  axios.get('/venue/randomTours',
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


export function getHotOperas (callback) {
  console.log('getHotOperas')
  // console.log('login')
  axios.get('/venue/hotOperas',
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


export function getHotConcerts (callback) {
  console.log('getHotConcerts')
  // console.log('login')
  axios.get('/venue/hotConcerts',
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

export function getTourCities (callback) {
  console.log('getTourCities')
  // console.log('login')
  axios.get('/venue/tourCities',
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

export function getCitySchedule (callback, city) {
  console.log('getCitySchedule')
  // console.log('login')
  // console.log('/venue/citySchedules?city=' + city)
  axios.get('/venue/citySchedules?city=' + city,
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
