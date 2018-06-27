import * as statisticsApi from '../../api/statistics'
// initial state
const state = {
}

// getters
const getters = {
  // showLogin: state => state.showLogin
}

// actions
const actions = {
  'getOrderStatistics' ({state, commit}, {onSuccess, onError, username}) {
    statisticsApi.getOrderStatistics((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, username)
  },
  'getUserStatistics' ({state, commit}, {onSuccess, onError, username}) {
    statisticsApi.getUserStatistics((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, username)
  },
  'getVenueOrderStatistic' ({state, commit}, {onSuccess, onError, code}) {
    statisticsApi.getVenueOrderStatistic((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, code)
  },
  'getDayOrderStatistic' ({state, commit}, {onSuccess, onError, code}) {
    statisticsApi.getDayOrderStatistic((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, code)
  },
  'getVenueRefundOrder' ({state, commit}, {onSuccess, onError, code}) {
    statisticsApi.getVenueRefundOrder((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, code)
  },
  'getFinanceStatistic' ({state, commit}, {onSuccess, onError, code}) {
    statisticsApi.getFinanceStatistic((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, code)
  },
  'getScheduleCountByVenue' ({state, commit}, {onSuccess, onError}) {
    statisticsApi.getScheduleCountByVenue((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getVenueAverageIncome' ({state, commit}, {onSuccess, onError}) {
    statisticsApi.getVenueAverageIncome((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getVenueAverageOccupy' ({state, commit}, {onSuccess, onError}) {
    statisticsApi.getVenueAverageOccupy((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getVipStatistics' ({state, commit}, {onSuccess, onError}) {
    statisticsApi.getVipStatistics((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getManagerFinanceStatistics' ({state, commit}, {onSuccess, onError}) {
    statisticsApi.getManagerFinanceStatistics((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
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
