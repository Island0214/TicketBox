<template>
  <div class="user-info-page-wrapper">
    <user-info></user-info>
    <vip-info></vip-info>
    <user-statistics></user-statistics>
  </div>
</template>

<script>
  import VipInfo from '../components/VipInfo/VipInfo.vue'
  import UserInfo from '../components/UserInfo/UserInfo.vue'
  import UserStatistics from '../components/UserStatistics/UserStatistics.vue'

  export default {
    components: {
      VipInfo,
      UserInfo,
      UserStatistics
    },
    data () {
      return {
      }
    }
  }
</script>

<style>
  .user-info-page-wrapper {
    padding: 30px;
    padding-top: 60px;
  }
</style>
