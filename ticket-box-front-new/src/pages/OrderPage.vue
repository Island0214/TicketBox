<template>
  <div class="order-page">
    <el-row :gutter="20" style="margin: 0;">
      <el-col :xs="6" :sm="6" :md="6" :lg="6" style="padding: 0;">
        <order-nav></order-nav>
      </el-col>
      <el-col :xs="6" :sm="6" :md="14" :lg="14" style="padding: 0;">
        <order-list></order-list>
        <advertisement-recommendation></advertisement-recommendation>
      </el-col>
      <el-col :xs="6" :sm="6" :md="4" :lg="4" style="padding: 0;">
        <advertisement-carousel></advertisement-carousel>
        <advertisement-list-right></advertisement-list-right>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import OrderNav from '../components/OrderNav/OrderNav'
  import OrderList from '../components/OrderList/OrderList'
  import AdvertisementListRight from '../components/AdvertisementListRight/AdvertisementListRight'
  import AdvertisementCarousel from '../components/AdvertisementCarousel/AdvertisementCarousel'
  import AdvertisementRecommendation from '../components/AdvertisementRecommendation/AdvertisementRecommendation'

  export default {
    name: "OrderPage",
    components: {
      AdvertisementListRight,
      AdvertisementCarousel,
      OrderNav,
      AdvertisementRecommendation,
      OrderList
    }
  }
</script>

<style scoped>
  .order-page {
    width: 100%;
    padding: 0 120px;
    min-height: 900px;
    margin-top: 30px;
  }
</style>
