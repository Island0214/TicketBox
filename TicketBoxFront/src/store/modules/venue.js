import * as venueApi from '../../api/venue'
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
    venueApi.getVenueInfo((data) => {
      // console.log(data)
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
    venueApi.getAreaInfo((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, body)
  },
  'saveAreaInfo' ({state, commit}, {onSuccess, onError, body}) {
    venueApi.saveAreaInfo((data) => {
      // console.log(data)
      if (!data) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess()
      }
    }, body)
  },
  'checkTicket' ({state, commit}, {onSuccess, onError, body}) {
    venueApi.checkTicket((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess()
      }
    }, body)
  },
  'getVenuesByStatus' ({state, commit}, {onSuccess, onError, status}) {
    venueApi.getVenuesByStatus((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, status)
  },
  'setVenueStatus' ({state, commit}, {onSuccess, onError, body}) {
    venueApi.setVenueStatus((data) => {
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
