import Vue from 'vue'
import Vuex from 'vuex'
import global from './modules/global'
import venue from './modules/venue'
import schedule from './modules/schedule'
import user from './modules/user'
import balance from './modules/balance'
import coupon from './modules/coupon'
import order from './modules/order'
import statistics from './modules/statistics'
import advertisement from './modules/advertisement'
import show from './modules/show'
import seat from './modules/seat'

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  modules: {
    global,
    venue,
    schedule,
    user,
    balance,
    coupon,
    order,
    statistics,
    advertisement,
    show,
    seat
  },
  strict: debug
  // plugins: debug ? [createLogger()] : []
})
