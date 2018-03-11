<template>
  <div class="page-wrapper">
    <concert-info></concert-info>
  </div>
</template>

<script>
  import ConcertInfo from '../components/ConcertInfo/ConcertInfo.vue'

  export default {
    components: {
      ConcertInfo
    },
    data () {
      return {
      }
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 30px;
  }
</style>
