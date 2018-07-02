import * as authApi from '../../api/auth'
// initial state
const state = {
  showLogin: false,
  loginType: '',
  showSignin: false,
  signinType: '',
  logStatus: false,
  logType: '',
  name: ''
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
  'logInAction' ({state, commit}, {onSuccess, onError, body, type}) {
    console.log(body)
    if (type === '会员') {
      authApi.userLogin((data) => {
        // console.log(data)
        // console.log(data.error)
        if (data.error !== undefined) {
          // console.log('error')
          onError(data.error)
        } else {
          // console.log('success')
          onSuccess(data.success)
          localStorage.setItem('username', body.username)
          localStorage.setItem('type', type)
          commit('setLogType', type)
          commit('setUsername', body.username)
          commit('hideLogin')
        }
      }, body)
    }

    if (type === '经理') {
      authApi.managerLogin((data) => {
        console.log(data)
        if (data.error !== undefined) {
          onError(data.error)
        } else {
          onSuccess(data.success)
          localStorage.setItem('username', body.username)
          localStorage.setItem('type', type)
          commit('setLogType', type)
          commit('setUsername', body.username)
          commit('hideLogin')
        }
      }, body)
    }

    if (type === '场馆') {
      authApi.venueLogin((data) => {
        if (data.error !== undefined) {
          onError(data.error)
        } else {
          onSuccess(data.success)
          localStorage.setItem('username', body.username)
          localStorage.setItem('type', type)
          commit('setLogType', type)
          commit('setUsername', body.username)
          commit('hideLogin')
        }
      }, body)
    }
    // alert(state.logType)
  },
  'userRegisterAction' ({state, commit}, {onSuccess, onError, body}) {
    authApi.userRegister((data) => {
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
  'userAuthenticate' ({state, commit}, {onSuccess, onError, username}) {
    authApi.userAuthenticate((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess()
      }
    }, username)
  },
  'venueRegisterAction' ({state, commit}, {onSuccess, onError, body}) {
    authApi.venueRegister((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(JSON.parse(data.venue))
      }
    }, body)
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
    localStorage.clear()
    state.logType = ''
    state.name = ''
  },
  'setUsername' (state, username) {
    // console.log(username)
    state.name = username
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
