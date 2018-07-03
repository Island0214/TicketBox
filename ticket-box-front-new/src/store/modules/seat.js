import * as seatApi from '../../api/seat'
// initial state
const state = {
}

// getters
const getters = {
}

// actions
const actions = {
  'getSeatByPosition' ({state, commit}, {onSuccess, onError, body}) {
    seatApi.getSeatByPosition((data) => {
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
  'getSeatById' ({state, commit}, {onSuccess, onError, body}) {
    seatApi.getSeatById((data) => {
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
