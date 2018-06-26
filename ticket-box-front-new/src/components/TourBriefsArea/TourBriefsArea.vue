<template>
  <div class="tour-briefs-wrapper">
    <div class="info-wrapper">
      <tour-brief :width="300" :height="420" :left="35" :top="25"></tour-brief>
      <tour-brief :width="130" :height="130" :left="350" :top="25"></tour-brief>
      <tour-brief :width="130" :height="130" :left="495" :top="25"></tour-brief>
      <tour-brief :width="275" :height="275" :left="350" :top="170"></tour-brief>
      <!--<tour-brief :width="180" :height="200" :left="530" :top="5"></tour-brief>-->
      <!--<tour-brief :width="180" :height="195" :left="530" :top="210"></tour-brief>-->
      <!--<tour-brief :width="130" :height="130" :left="715" :top="5"></tour-brief>-->
      <!--<tour-brief :width="130" :height="130" :left="715" :top="140"></tour-brief>-->
      <!--<tour-brief :width="130" :height="130" :left="715" :top="275"></tour-brief>-->

      <div class="chart-wrapper">

      </div>
    </div>
  </div>
</template>

<script>
  import TourBrief from './TourBrief/TourBrief'

  export default {
    components: {
      TourBrief
    },
    name: "TourBriefsArea"
  }
</script>

<style scoped src="./TourBriefsArea.css">

</style>
