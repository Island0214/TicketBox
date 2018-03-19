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
