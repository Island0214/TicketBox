<template>
  <div class="home-page-wrapper">
    <img src="../assets/bg.png">
    <!--<my-nav-bar></my-nav-bar>-->
    <carousel></carousel>
    <card-tabs></card-tabs>
    <tour-briefs-area></tour-briefs-area>
  </div>
</template>

<script>
  import MyNavBar from '../components/MyNavBar/MyNavBar'
  import Carousel from '../components/Carousel/Carousel'
  import CardTabs from '../components/CardTabs/CardTabs'
  import TourBriefsArea from '../components/TourBriefsArea/TourBriefsArea'

  export default {
    name: "HomePage",
    components: {
      MyNavBar,
      Carousel,
      CardTabs,
      TourBriefsArea
    }
  }
</script>

<style scoped>
  .home-page-wrapper {
    width: 100%;
  }

  .home-page-wrapper img {
    position: absolute;
    width: 100%;
    top: 0;
    z-index: -1;
  }
</style>
