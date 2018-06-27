import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import ShowListPage from '../pages/ShowListPage.vue'

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
    }
  ]
})
