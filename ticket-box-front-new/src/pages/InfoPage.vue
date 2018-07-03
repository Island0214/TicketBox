<template>
  <div class="info-wrapper">
    <el-row :gutter="20" style="margin: 0;">
      <el-col :xs="6" :sm="6" :md="6" :lg="6" style="padding: 0;">
        <!--<order-nav></order-nav>-->
        <info-nav-bar></info-nav-bar>
      </el-col>
      <el-col :xs="6" :sm="6" :md="14" :lg="14" style="padding: 0;">
        <info-content></info-content>
      </el-col>
      <el-col :xs="6" :sm="6" :md="4" :lg="4" style="padding: 0;">
        <advertisement-carousel></advertisement-carousel>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import AdvertisementListRight from '../components/AdvertisementListRight/AdvertisementListRight'
  import AdvertisementCarousel from '../components/AdvertisementCarousel/AdvertisementCarousel'
  import InfoNavBar from '../components/InfoNavBar/InfoNavBar'
  import InfoContent from '../components/InfoContent/InfoContent'

  export default {
    name: "InfoPage",
    components: {
      AdvertisementListRight,
      AdvertisementCarousel,
      InfoNavBar,
      InfoContent
    }
  }
</script>

<style scoped>
  .info-wrapper {
    width: 100%;
    padding: 0 120px;
    min-height: 700px;
    margin-top: 30px;
  }
</style>
