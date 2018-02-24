// initial state
const state = {
  showLogin: false,
  loginType: ''
}

// getters
const getters = {
  showLogin: state => state.showLogin,
  loginType: state => state.loginType
}

// actions
const actions = {
}

// mutations
const mutations = {
  'showLogin' (state) {
    state.showLogin = true
  },
  'hideLogin' (state) {
    state.showLogin = false
    // alert(state.largeSize)
  },
  'setLoginType' (state, type) {
    state.loginType = type
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
