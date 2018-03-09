import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import UserInfoPage from '../pages/UserInfoPage.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/info',
      name: 'UserInfoPage',
      component: UserInfoPage
    }
  ]
})
