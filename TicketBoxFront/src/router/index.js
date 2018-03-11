import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import UserInfoPage from '../pages/UserInfoPage.vue'
import UserOrderPage from '../pages/UserOrderPage.vue'
import UserConcertsPage from '../pages/UserConcertsPage.vue'
import ConcertInfoPage from '../pages/ConcertInfoPage.vue'

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
    },
    {
      path: '/order',
      name: 'UserInfoPage',
      component: UserOrderPage
    },
    {
      path: '/concerts',
      name: 'UserConcertsPage',
      component: UserConcertsPage
    },
    {
      path: '/concert/:id',
      name: 'ConcertInfoPage',
      component: ConcertInfoPage
    }
  ]
})
