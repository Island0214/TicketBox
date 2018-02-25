import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import VipSignUpPage from '../pages/VipSignUpPage.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/vipSign',
      name: 'VipSignUpPage',
      component: VipSignUpPage
    }
  ]
})
