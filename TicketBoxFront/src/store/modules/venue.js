import * as authApi from '../../api/venue'
// initial state
const state = {
}

// getters
const getters = {
  // showLogin: state => state.showLogin
}

// actions
const actions = {
  'getVenueInfo' ({state, commit}, {onSuccess, onError, body}) {
    authApi.getVenueInfo((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, body)
  },
  'getAreaInfo' ({state, commit}, {onSuccess, onError, body}) {
    authApi.getAreaInfo((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, body)
  },
  'dispatchSchedule' ({state, commit}, {onSuccess, onError, body}) {
    authApi.dispatchSchedule((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, body)
  },
  'saveAreaInfo' ({state, commit}, {onSuccess, onError, body}) {
    authApi.saveAreaInfo((data) => {
      console.log(data)
      if (!data) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess()
      }
    }, body)
  }
}

// mutations
const mutations = {
}

export default {
  state,
  getters,
  actions,
  mutations
}
