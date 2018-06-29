import * as advertisementApi from '../../api/advertisement'
// initial state
const state = {
  searchContent: '21421424',
  city: '',
  type: '',
  startTime: '',
  endTime: ''
}

// getters
const getters = {
  searchContent: state => state.searchContent,
  city: state => state.city,
  type: state => state.type,
  startTime: state => state.startTime,
  endTime: state => state.endTime
}

// actions
const actions = {

}

// mutations
const mutations = {
  'setSearchParams' (state, params) {
    state.searchContent = params.searchContent
    state.city = params.city
    state.type = params.type
    state.startTime = params.startTime
    state.endTime = params.endTime

    console.log(params)
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
