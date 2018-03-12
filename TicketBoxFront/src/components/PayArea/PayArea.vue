<template>
  <div class="pay-area-wrapper">
    <h2 style="margin-bottom: 40px;">您的订单将在<span>{{ countDown }}</span>后关闭, 请及时付款！</h2>
    <part-title title="支付信息" class="title-wrapper"></part-title>
    <div class="pay-wrapper">
      <p>请选择您的支付方式：</p>
      <el-radio v-model="pay_way" label="1">网上银行</el-radio>
      <el-radio v-model="pay_way" label="2">微信支付</el-radio>
      <el-radio v-model="pay_way" label="3">支付宝</el-radio>
      <el-button>立即支付</el-button>
    </div>
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
        countDown: '15分钟',
        pay_way: '0'
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./PayArea.css"></style>
