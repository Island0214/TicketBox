<template>
  <div class="coupon-wrapper">
    <h5>优惠券名称：</h5>
    <h4>{{ coupon.name }}</h4>
    <br>
    <br>
    <h5>优惠券折扣：</h5>
    <h4>{{ coupon.discount }}</h4>
    <br v-if="!coupon.isMine">
    <br v-if="!coupon.isMine">
    <h5 v-if="!coupon.isMine">所需积分：</h5>
    <h4 v-if="!coupon.isMine">{{ coupon.score }}</h4>

    <el-button v-if="!coupon.isMine">兑<br>换</el-button>
  </div>
</template>

<script>
  export default {
    props: ['coupon'],
    data () {
      return {
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .coupon-wrapper {
    position: relative;
    display: inline-block;
    padding: 30px;
    /*border-top: 1px solid #999;*/
    border-bottom: 1px solid #999;
    width: 100%;
    height: 100%;
  }

  .coupon-wrapper h5 {
    font-weight: 400;
  }

  .coupon-wrapper h4, h5{
    width: auto;
    position: relative;
    display: inline-block;
  }

  .coupon-wrapper .el-button {
    position: absolute;
    width: auto;
    /* height: 0; */
    bottom: 10px;
    top: 10px;
    right: 10px;
  }
</style>
