<template>
  <div class="page-wrapper">
    <!--<concert-info></concert-info>-->
    <venue-statistics></venue-statistics>
  </div>
</template>

<script>
  import VenueStatistics from '../components/VenueStatistics/VenueStatistics.vue'

  export default {
    components: {
//      ConcertInfo
      VenueStatistics
    },
    data () {
      return {
      }
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 60px;
  }
</style>
