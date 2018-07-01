import * as showApi from "../../api/show";

// initial state
const state = {
  searchContent: '',
  city: '',
  type: '',
  startTime: '',
  endTime: '',
  page: 1,
  sort: 'latest',
  pageSize: 15
}

// getters
const getters = {
  searchContent: state => state.searchContent,
  city: state => state.city,
  type: state => state.type,
  startTime: state => state.startTime,
  endTime: state => state.endTime,
  page: state => state.page,
  sort: state => state.sort,
  pageSize: state => state.sort
}

// actions
const actions = {
  'searchScheduleByPage' ({state, commit}, {onSuccess, onError}) {
    // console.log({
    //   city: state.city,
    //   category: state.type,
    //   startTime: state.startTime,
    //   endTime: state.endTime,
    //   userInput: state.searchContent,
    //   pageSize: state.pageSize,
    //   pageNum: state.page,
    //   sort: state.sort
    // })
    console.log('searchScheduleByPage')
    showApi.searchScheduleByPage((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, {
      city: state.city,
      category: state.type,
      startTime: state.startTime,
      endTime: state.endTime,
      userInput: state.searchContent,
      pageSize: state.pageSize,
      pageNum: state.page,
      sort: state.sort
    })
  },
  'getTourScheduleById' ({state, commit}, {onSuccess, onError, tour}) {
    console.log(tour)
    console.log('searchScheduleByPage')
    showApi.getTourScheduleById((data) => {
      // console.log(data)
      if (data.error !== undefined) {
        // console.log('error')
        onError(data.error)
      } else {
        // console.log('success')
        onSuccess(data)
      }
    }, tour)
  }
}

// mutations
const mutations = {
  'setSearchParams' (state, params) {
    state.searchContent = params.searchContent
    state.city = params.city
    state.type = params.type
    state.startTime = params.startTime
    state.endTime = params.endTime
    state.page = 1
    if (params.city === '全部') {
      state.city = ''
    }
    if (params.type === '全部') {
      state.type = ''
    }
    console.log(params)
  },
  'setPage' (state, page) {
    state.page = page
  },
  'setSort' (state, sort) {
    state.sort = sort
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
