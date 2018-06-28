import * as advertisementApi from '../../api/advertisement'
// initial state
const state = {}

// getters
const getters = {}

// actions
const actions = {
  'getAds'({state, commit}, {onSuccess, onError}) {
    advertisementApi.getAds((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getHotSchedules'({state, commit}, {onSuccess, onError}) {
    advertisementApi.getHotSchedules((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getComingSchedules'({state, commit}, {onSuccess, onError}) {
    advertisementApi.getComingSchedules((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getRandomFiveTours'({state, commit}, {onSuccess, onError}) {
    advertisementApi.getRandomFiveTours((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getHotOperas'({state, commit}, {onSuccess, onError}) {
    advertisementApi.getHotOperas((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getHotConcerts'({state, commit}, {onSuccess, onError}) {
    advertisementApi.getHotConcerts((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getTourCities'({state, commit}, {onSuccess, onError}) {
    advertisementApi.getTourCities((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  }
}

// mutations
const mutations = {}

export default {
  state,
  getters,
  actions,
  mutations
}
