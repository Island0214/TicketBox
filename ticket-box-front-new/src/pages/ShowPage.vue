<template>
  <div class="show-wrapper">
    <el-row :gutter="20" style="margin: 0;">
      <el-col :xs="16" :sm="16" :md="16" :lg="18" style="padding: 0;">
        <!--<show-list-nav></show-list-nav>-->
        <show-info></show-info>
      </el-col>
      <el-col :xs="4" :sm="4" :md="4" :lg="4" style="padding: 0;">
        <!--<show-list-nav></show-list-nav>-->
        <advertisement-carousel></advertisement-carousel>
        <advertisement-list-right></advertisement-list-right>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import AdvertisementListRight from '../components/AdvertisementListRight/AdvertisementListRight'
  import AdvertisementCarousel from '../components/AdvertisementCarousel/AdvertisementCarousel'
  import ShowInfo from '../components/ShowInfo/ShowInfo'

  export default {
    name: "ShowPage",
    components: {
      AdvertisementListRight,
      AdvertisementCarousel,
      ShowInfo
    }
  }
</script>

<style scoped>
  .show-wrapper {
    width: 100%;
    padding: 0 120px;
    min-height: 900px;
    margin-top: 30px;
  }
</style>
