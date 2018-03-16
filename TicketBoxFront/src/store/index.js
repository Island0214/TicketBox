import Vue from 'vue'
import Vuex from 'vuex'
import global from './modules/global'
import venue from './modules/venue'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  modules: {
    global,
    venue
  },
  strict: debug
  // plugins: debug ? [createLogger()] : []
})
