<template>
  <div class="order-wrapper">
    <div class="order-title-wrapper">
      <el-row :gutter="20" style="margin: 0">
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <p>订单编号：<span>{{ order.order_id }}</span></p>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <h5>{{ order.type }}</h5>
        </el-col>
      </el-row>
    </div>
    <div class="order-info-wrapper">
      <div class="img-box">
        <img :src="url"/>
      </div>
      <div class="info-wrapper">
        <h3>{{ order.concert_name }}</h3>
        <p>时间：<span>{{ order.start_time }}</span></p>
        <p>地点：<span>{{ order.location }}</span></p>
        <p>座位：<span>{{ order.seat }}</span></p>
      </div>
    </div>
    <div class="order-footer-wrapper">
      <h6>订单价格：<span>¥{{ order.price }}</span></h6>
      <div class="buttons-wrapper">
        <el-button v-if="order.type === '已付款订单'">退款</el-button>
        <el-button v-if="order.type === '待付款订单'">付款</el-button>
        <el-button v-if="order.type === '待付款订单'">取消</el-button>
      </div>
      <!--<img :src="url"/>-->
    </div>
    <!--<p>{{ order.concert_name }}</p>-->
    <!--<p>{{ order.concert_name }}</p>-->
  </div>
</template>

<script>
  export default {
    props: ['order'],
    data () {
      return {
        url: require('../../assets/poster1.jpg')
//        url: require(this.order.poster)
//        poster: require(this.url)
      }
    },
    watch: {
      orderType: function () {
//        alert(this.orderType)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./Order.css"></style>
