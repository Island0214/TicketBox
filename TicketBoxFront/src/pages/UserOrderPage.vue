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
    }
  }
</script>

<style>
  .user-order-page-wrapper {
    padding: 30px;
  }
</style>
