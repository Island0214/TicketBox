<template>
  <div class="user-info-page-wrapper">
    <venue-info></venue-info>
  </div>
</template>

<script>
  import VenueInfo from '../components/VenueInfo/VenueInfo.vue'

  export default {
    components: {
      VenueInfo
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
  }
</style>
