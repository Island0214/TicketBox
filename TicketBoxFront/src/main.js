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
  template: '<App/>'
})
