import * as userApi from '../../api/user'
// initial state
const state = {
}

// getters
const getters = {
  // showLogin: state => state.showLogin
}

// actions
const actions = {
  'getUserInfo' ({state, commit}, {onSuccess, onError, username}) {
    userApi.getUserInfo((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, username)
  },
  'getVipDiscount' ({state, commit}, {onSuccess, onError, grade}) {
    userApi.getVipDiscount((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, grade)
  },
  'getAllUsers' ({state, commit}, {onSuccess, onError}) {
    userApi.getAllUsers((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'buyTicketOffline' ({state, commit}, {onSuccess, onError, body}) {
    userApi.buyTicketOffline((data) => {
      console.log(data)
      if (!data) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess()
      }
    }, body)
  },
  'banUser' ({state, commit}, {onSuccess, onError, body}) {
    userApi.banUser((data) => {
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
