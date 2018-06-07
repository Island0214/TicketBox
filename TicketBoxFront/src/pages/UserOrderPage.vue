<template>
  <div class="user-order-page-wrapper">
    <user-orders></user-orders>
  </div>
</template>

<script>
  import UserOrders from '../components/UserOrders/UserOrders.vue'
  export default {
    components: {
      UserOrders
    },
    data () {
      return {
      }
    },
    mounted () {
      document.documentElement.scrollTop = document.body.scrollTop = 0;
    }
  }
</script>

<style>
  .user-order-page-wrapper {
    padding: 30px;
  }
</style>
