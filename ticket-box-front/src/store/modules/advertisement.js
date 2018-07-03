import * as advertisementApi from '../../api/advertisement'
// initial state
const state = {
}

// getters
const getters = {
}

// actions
const actions = {
    'getAds' ({state, commit}, {onSuccess, onError}) {
        advertisementApi.getAds((data) => {
            console.log(data)
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
