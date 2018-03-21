import * as couponApi from '../../api/coupon'
// initial state
const state = {
}

// getters
const getters = {
  // showLogin: state => state.showLogin
}

// actions
const actions = {
  'getAllCoupons' ({state, commit}, {onSuccess, onError}) {
    couponApi.getAllCoupons((data) => {
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
  'exchangeCoupon' ({state, commit}, {onSuccess, onError, body}) {
    couponApi.exchangeCoupon((data) => {
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
  'getMyCoupons' ({state, commit}, {onSuccess, onError, username}) {
    couponApi.getMyCoupons((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, username)
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
