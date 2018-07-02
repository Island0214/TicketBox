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

export function checkTicket (callback, body) {
  console.log('checkTicket')
  // console.log('login')
  axios.post('/venue/seat/check/',
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

export function getVenuesByStatus (callback, status) {
  console.log('getVenuesByStatus')
  // console.log('login')
  axios.get('/manager/venues/' + status,
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

export function setVenueStatus (callback, body) {
  console.log('setVenueStatus')
  // console.log('login')
  axios.post('/manager/venue/status',
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


