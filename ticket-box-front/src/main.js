import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './typo.css'
import './index.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './element-variables.scss'

Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
    render: h => h(App),
    router
}).$mount('#app')
