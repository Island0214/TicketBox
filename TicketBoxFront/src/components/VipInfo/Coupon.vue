<template>
  <div class="coupon-wrapper">
    <h5>优惠券名称：</h5>
    <h4>{{ coupon.name }}</h4>
    <br>
    <br>
    <h5>优惠券折扣：</h5>
    <h4>满 {{ coupon.consumption }} 减 {{ coupon.discount }}</h4>
    <br v-if="!isMine">
    <br v-if="!isMine">
    <h5 v-if="!isMine">所需积分：</h5>
    <h4 v-if="!isMine">{{ coupon.integration }}</h4>

    <el-button v-if="!isMine" @click="exchangeCoupon">兑<br>换</el-button>
  </div>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'

  export default {
    props: ['coupon', 'isMine'],
    data () {
      return {
      }
    },
    computed: {
      ...mapGetters({
        username: 'name'
      })
    },
    methods: {
      ...mapActions({
        exchangeCouponAction: 'exchangeCoupon'
      }),
      exchangeCoupon: function () {
        this.exchangeCouponAction({
          onSuccess: () => {
            this.$message({
              showClose: true,
              message: '兑换' + this.coupon.name + '成功！',
              customClass: 'message-wrapper',
              type: 'success'
            })
            this.$emit('minusIntegration', this.coupon.integration)
          },
          onError: () => {
            this.$message({
              showClose: true,
              message: '积分不足！',
              customClass: 'message-wrapper',
              type: 'success'
            })
          },
          body: {
            username: this.username,
            coupon: this.coupon.id
          }
        })
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
    /*width: 20px;*/
    /* height: 0; */
    width: auto;
    padding: 10px;
    bottom: 30px;
    top: 30px;
    right: 10px;
  }
</style>
