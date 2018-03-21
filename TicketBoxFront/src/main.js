// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './typo.css'
import './index.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './element-variables.scss'
import Vuex from 'vuex'
import store from './store'
import {mapMutations} from 'vuex'
// import axios from 'axios'

// axios.defaults.baseURL = 'http://127.0.0.1:8080/'
Vue.use(Vuex)
Vue.config.productionTip = false
Vue.use(ElementUI)
//
// const name = 'App.vue'
//
// const App = (resolve) => {
//   require.ensure([], (require) => {
//     resolve(require.context('./component', false, /\.vue$/)(`./${name}`));
//   }, 'app');
// };

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  methods: {
    ...mapMutations({
      setLogType: 'setLogType',
      setUsername: 'setUsername'
    })
  },
  created () {
//    console.log(localStorage.getItem('username'))
    if (localStorage.getItem('username') !== null) {
      this.setUsername(localStorage.getItem('username'))
      this.setLogType(localStorage.getItem('type'))
    }
  }
})

function format (num) {
  if (num < 10) {
    return '0' + num
  } else {
    return num
  }
}

Date.prototype.toLocaleString = function() {
  return this.getFullYear() + "-" + format(this.getMonth() + 1) + "-" + format(parseInt(this.getDate())) + " " + format(this.getHours()) + ":" + format(this.getMinutes()) + ":" + format(this.getSeconds());
};
