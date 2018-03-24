import * as orderApi from '../../api/order'
// initial state
const state = {
}

// getters
const getters = {
  // showLogin: state => state.showLogin
}

// actions
const actions = {
  'createOrder' ({state, commit}, {onSuccess, onError, body}) {
    orderApi.createOrder((data) => {
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
  'getOrderById' ({state, commit}, {onSuccess, onError, body}) {
    orderApi.getOrderById((data) => {
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
  'payOrder' ({state, commit}, {onSuccess, onError, body}) {
    orderApi.payOrder((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
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
