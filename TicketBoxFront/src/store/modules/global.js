// initial state
const state = {
  showLogin: false,
  loginType: '',
  showSignin: false,
  signinType: '',
  logStatus: false,
  logType: '',
  name: 'ISLAND'
}

// getters
const getters = {
  showLogin: state => state.showLogin,
  showSignin: state => state.showSignin,
  loginType: state => state.loginType,
  signinType: state => state.signinType,
  logStatus: state => state.logStatus,
  logType: state => state.logType,
  name: state => state.name
}

// actions
const actions = {
  'logInAction' ({state, commit}, {onSuccess, onError, body}) {
    commit('setLogType', body.type)
    commit('hideLogin')
    // alert(state.logType)
  }
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
  },
  'setLogType' (state, type) {
    state.logStatus = true
    state.logType = type
  },
  'quitLog'(state, type) {
    state.logStatus = false
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
