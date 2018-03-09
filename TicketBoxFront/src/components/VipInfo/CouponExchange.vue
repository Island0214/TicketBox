<template>
  <div>

    <el-dialog
      class="dialog-wrapper"
      width="80%"
      top="10vh"
      :visible.sync="showExchange"
      :modal=true
      :modal-append-to-body=false
      :show-close=false
      :close-on-click-modal=false
      :close-on-press-escape=false
      :fullscreen="true"
    >
      <part-title title="积分兑换"></part-title>

      <div class="content-wrapper">
        <h3>当前可用积分：<span>{{ curScore }}</span></h3>

        <div class="tabs-wrapper">
          <a :class="{'select-tab' : !myCoupon}" @click="showCoupons(false)">所有兑换券</a>
          <!--<a>最新演出</a>-->
          <a :class="{'select-tab' : myCoupon}" @click="showCoupons(true)">我的兑换券</a>
        </div>

        <div class="coupons-wrapper">
          <h4 v-if="coupons.length === 0">暂无优惠券...</h4>
          <coupon v-for="(coupon, index) in coupons" :key="index" :coupon="coupon" :class="[index===0 ? 'fisrt-child' : '']"></coupon>
        </div>
      </div>


      <span slot="footer" class="dialog-footer footer-wrapper">
          <!--<div @click="showOutputLayerSetting=false">-->
            <!--<my-dialog-button content="确定"></my-dialog-button>-->
          <!--</div>-->
            <!--<div @click="showOutputLayerSetting=false">-->
            <!--<my-dialog-button content="取消"></my-dialog-button>-->
          <!--</div>-->
        <el-button @click="closeExchange">确定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import Coupon from './Coupon.vue'

  export default {
    components: {
      PartTitle,
      Coupon
    },
    props: ['showExchange'],
    data () {
      return {
        curScore: 888,
        myCoupon: false,
        coupons: [{
          name: '新学期特惠',
          discount: '满500减50',
          isMine: false,
          score: 300
        }, {
          name: '新学期特惠',
          discount: '满500减50',
          isMine: false,
          score: 300
        }]
      }
    },
    methods: {
      showCoupons (isMyCoupon) {
        this.myCoupon = isMyCoupon
      },
      closeExchange: function () {
        this.$emit('closeExchange')
      }
    }
  }
</script>

<style scoped src="./VipInfo.css"></style>
