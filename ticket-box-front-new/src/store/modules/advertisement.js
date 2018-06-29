import * as advertisementApi from '../../api/advertisement'
// initial state
const state = {
  searchContent: '21421424'
}

// getters
const getters = {
  searchContent: state => state.searchContent
}

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
  },
  'getCitySchedule'({state, commit}, {onSuccess, onError, city}) {
    advertisementApi.getCitySchedule((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, city)
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
