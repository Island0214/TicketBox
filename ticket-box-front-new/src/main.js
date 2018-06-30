import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './typo.css'
import './index.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './element-variables.scss'
import Vuex from 'vuex'
import store from './store/index'

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.use(Vuex)

new Vue({
    render: h => h(App),
    router,
    store
}).$mount('#app')

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
