import * as scheduleApi from '../../api/schedule'
// initial state
const state = {
}

// getters
const getters = {
  // showLogin: state => state.showLogin
}

// actions
const actions = {
  'dispatchSchedule' ({state, commit}, {onSuccess, onError, body}) {
    scheduleApi.dispatchSchedule((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, body)
  },
  'setScheduleSeat' ({state, commit}, {onSuccess, onError, body}) {
    scheduleApi.setScheduleSeat((data) => {
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
  'getVenueSchedules' ({state, commit}, {onSuccess, onError, venue}) {
    scheduleApi.getVenueSchedules((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, venue)
  },
  'getScheduleInfo' ({state, commit}, {onSuccess, onError, schedule}) {
    scheduleApi.getScheduleInfo((data) => {
      console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, schedule)
  },
  'getAreaInfoOfSchedule' ({state, commit}, {onSuccess, onError, schedule, area}) {
    scheduleApi.getAreaInfoOfSchedule((data) => {
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, schedule, area)
  },
  'getUnpaidSchedules' ({state, commit}, {onSuccess, onError}) {
    scheduleApi.getUnpaidSchedules((data) => {
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getNewestThreeSchedules' ({state, commit}, {onSuccess, onError}) {
    scheduleApi.getNewestThreeSchedules((data) => {
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getClosestThreeSchedules' ({state, commit}, {onSuccess, onError}) {
    scheduleApi.getClosestThreeSchedules((data) => {
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    })
  },
  'getSchedulePriceInfo' ({state, commit}, {onSuccess, onError, schedule}) {
    scheduleApi.getSchedulePriceInfo((data) => {
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, schedule)
  },
  'getScheduleBasicInfo' ({state, commit}, {onSuccess, onError, schedule}) {
    scheduleApi.getScheduleBasicInfo((data) => {
      if (data.error !== undefined) {
        // console.log('error')
        onError()
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, schedule)
  },
  'findScheduleByPage' ({state, commit}, {onSuccess, onError, body}) {
    scheduleApi.findScheduleByPage((data) => {
      if (data.error !== undefined) {
        // console.log('error')
        onError()
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
