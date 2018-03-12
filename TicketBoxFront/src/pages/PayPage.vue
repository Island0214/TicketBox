<template>
  <div class="page-wrapper">
    <div class="title-wrapper">
      <part-title title="订单信息" style="margin: 30px;"></part-title>
    </div>
    <order :order="order" :showButtons="false"></order>
    <pay-area></pay-area>
  </div>
</template>

<script>
  import PayArea from '../components/PayArea/PayArea.vue'
  import PartTitle from '../components/Basic/PartTitle/PartTitle.vue'
//
  export default {
    components: {
      Order: () => import('../components/Order/Order.vue'),
      PayArea,
      PartTitle
    },
    data () {
      return {
        order: {
          order_id: '4',
          created_at: '2018-03-03 11:23:12',
          type: '待付款订单',
          start_time: '2018-04-11 20:00',
          location: '南京欧拉艺术空间',
          concert_name: 'deca joins《春天游泳》2018巡演南京站',
          seat: '5排6座',
          price: '388',
          poster: '../../assets/poster4.jpg'
        }
      }
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 30px;
    width: 100%;
    text-align: center;
  }

  .page-wrapper .title-wrapper {
    width: 100%;
    text-align: left;
  }
</style>
