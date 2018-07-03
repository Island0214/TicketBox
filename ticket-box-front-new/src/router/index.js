import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import ShowListPage from '../pages/ShowListPage.vue'
import ShowPage from '../pages/ShowPage.vue'
import OrderPage from '../pages/OrderPage.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/shows',
      name: 'ShowListPage',
      component: ShowListPage
    },
    {
      path: '/show/:id',
      name: 'ShowPage',
      component: ShowPage
    },
    {
      path: '/order',
      name: 'OrderPage',
      component: OrderPage
    }
  ]
})
