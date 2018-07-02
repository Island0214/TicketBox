import * as balanceApi from '../../api/balance'
// initial state
const state = {
}

// getters
const getters = {
  // showLogin: state => state.showLogin
}

// actions
const actions = {
  'payBalance' ({state, commit}, {onSuccess, onError, body}) {
    balanceApi.payBalance((data) => {
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
  'getPaidSchedules' ({state, commit}, {onSuccess, onError}) {
    balanceApi.getPaidSchedules((data) => {
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
const mutations = {
}

export default {
  state,
  getters,
  actions,
  mutations
}
