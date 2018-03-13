<template>
  <div class="venue-statistics-wrapper">
    <part-title title="预定信息"></part-title>
    <div class="info-wrapper"></div>
    <part-title title="退订信息"></part-title>
    <div class="info-wrapper"></div>
    <part-title title="财务信息"></part-title>
    <div class="info-wrapper"></div>
  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  export default {
    components: {
      PartTitle
    },
    data () {
      return {
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./VenueStatistics.css"></style>
