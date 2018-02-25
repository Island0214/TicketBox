// initial state
const state = {
  showLogin: false,
  loginType: '',
  showSignin: false,
  signinType: ''
}

// getters
const getters = {
  showLogin: state => state.showLogin,
  showSignin: state => state.showSignin,
  loginType: state => state.loginType,
  signinType: state => state.signinType
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
  'showSignin' (state, type) {
    state.showSignin = true
    state.signinType = type
  },
  'hideSignin' (state) {
    state.showSignin = false
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
