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
