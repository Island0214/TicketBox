<template>
  <div class="user-concerts-wrapper">
    <concerts-list></concerts-list>
  </div>
</template>

<script>
  import ConcertsList from '../components/ConcertsList/ConcertsList.vue'

  export default {
    components: {
      ConcertsList
    },
    data () {
      return {
      }
    }
  }
</script>

<style>
  .user-concerts-wrapper {
    padding: 30px;
  }
</style>
