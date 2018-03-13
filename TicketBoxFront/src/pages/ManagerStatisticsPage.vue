<template>
  <div class="page-wrapper">
    <part-title title="场馆统计"></part-title>
    <venues-statistics></venues-statistics>
    <part-title title="会员统计"></part-title>
    <vip-statistics></vip-statistics>
    <part-title title="财务统计"></part-title>
    <finance-statistics></finance-statistics>
  </div>
</template>

<script>
  import PartTitle from '../components/Basic/PartTitle/PartTitle.vue'
  import VenuesStatistics from '../components/ManagerStatistics/VenuesStatistics.vue'
  import VipStatistics from '../components/ManagerStatistics/VipStatistics.vue'
  import FinanceStatistics from '../components/ManagerStatistics/FinanceStatistics.vue'

  export default {
    components: {
      PartTitle,
      VenuesStatistics,
      VipStatistics,
      FinanceStatistics
    },
    data () {
      return {}
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 60px;
  }
</style>
