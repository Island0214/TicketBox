<template>
  <div class="pay-wrapper">
    <el-row :gutter="20" style="margin: 0;">
      <el-col :xs="16" :sm="16" :md="16" :lg="18" style="padding: 0;">
        <pay-area></pay-area>
      </el-col>
      <el-col :xs="4" :sm="4" :md="4" :lg="4" style="padding: 0;">
        <!--<show-list-nav></show-list-nav>-->
        <advertisement-carousel></advertisement-carousel>
        <!--<advertisement-list-right></advertisement-list-right>-->
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import AdvertisementListRight from '../components/AdvertisementListRight/AdvertisementListRight'
  import AdvertisementCarousel from '../components/AdvertisementCarousel/AdvertisementCarousel'
  import PayArea from '../components/PayArea/PayArea'

  export default {
    name: "PayPage",
    components: {
      AdvertisementListRight,
      AdvertisementCarousel,
      PayArea
    },
    created () {
      this.$loading({fullscreen: true}).close()
    }
  }
</script>

<style scoped>
  .pay-wrapper {
    width: 100%;
    padding: 0 120px;
    /*min-height: 900px;*/
    margin-top: 30px;
  }
</style>
