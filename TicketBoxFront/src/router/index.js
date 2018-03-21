import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import UserInfoPage from '../pages/UserInfoPage.vue'
import UserOrderPage from '../pages/UserOrderPage.vue'
import UserConcertsPage from '../pages/UserConcertsPage.vue'
import ConcertInfoPage from '../pages/ConcertInfoPage.vue'
import PayPage from '../pages/PayPage.vue'
import VenueInfoPage from '../pages/VenueInfoPage.vue'
import VenueSchedulePage from '../pages/VenueSchedulePage.vue'
import VenueLivePage from '../pages/VenueLivePage.vue'
import VenueStatisticsPage from '../pages/VenueStatisticsPage.vue'
import ManagerApprovalPage from '../pages/ManagerApprovalPage.vue'
import ManagerStatisticsPage from '../pages/ManagerStatisticsPage.vue'
import ManagerBalancePage from '../pages/ManagerBalancePage.vue'
import UserAuthenticationPage from '../pages/UserAuthenticationPage.vue'
import ManagerBanPage from '../pages/ManagerBanPage.vue'

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
      name: 'UserOrderPage',
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
    },
    {
      path: '/pay/:id',
      name: 'PayPage',
      component: PayPage
    },
    {
      path: '/venue/info',
      name: 'venue',
      component: VenueInfoPage
    },
    {
      path: '/venue/schedule',
      name: 'VenueSchedulePage',
      component: VenueSchedulePage
    },
    {
      path: '/venue/live',
      name: 'VenueLivePage',
      component: VenueLivePage
    },
    {
      path: '/venue/tickets',
      name: 'VenueStatisticsPage',
      component: VenueStatisticsPage
    },
    {
      path: '/manager/approval',
      name: 'ManagerApprovalPage',
      component: ManagerApprovalPage
    },
    {
      path: '/manager/balance',
      name: 'ManagerBalancePage',
      component: ManagerBalancePage
    },
    {
      path: '/manager/statistics',
      name: 'ManagerStatisticsPage',
      component: ManagerStatisticsPage
    },
    {
      path: '/user/authenticate/:name',
      name: 'UserAuthenticationPage',
      component: UserAuthenticationPage
    },
    {
      path: '/manager/ban',
      name: 'ManagerBanPage',
      component: ManagerBanPage
    }
  ]
})
