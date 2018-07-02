import * as orderApi from '../../api/order'
// initial state
const state = {
  orderType: ''
}

// getters
const getters = {
  orderType: state => state.orderType
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
  },
  'cancelOrder' ({state, commit}, {onSuccess, onError, body}) {
    orderApi.cancelOrder((data) => {
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
  'refundOrder' ({state, commit}, {onSuccess, onError, body}) {
    orderApi.refundOrder((data) => {
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
  'getAllOrders' ({state, commit}, {onSuccess, onError, body}) {
    orderApi.getAllOrders((data) => {
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
  'setOrderType' (state, type) {
    // console.log(username)
    state.orderType = type
    console.log(state.orderType)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
