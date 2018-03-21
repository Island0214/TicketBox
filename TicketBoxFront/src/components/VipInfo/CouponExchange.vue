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
          <coupon v-for="(coupon, index) in coupons" :key="index" :coupon="coupon" :class="[index===0 ? 'fisrt-child' : '']" :isMine="myCoupon" @minusIntegration="minusIntegration"></coupon>
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
  import {mapActions, mapGetters} from 'vuex'

  export default {
    components: {
      PartTitle,
      Coupon
    },
    props: ['showExchange', 'integration'],
    data () {
      return {
        curScore: 888,
        myCoupon: false,
        coupons: []
      }
    },
    computed: {
      ...mapGetters({
        username: 'name'
      })
    },
    methods: {
      ...mapActions({
        getAllCouponsAction: 'getAllCoupons',
        getMyCouponsAction: 'getMyCoupons'
      }),
      showCoupons (isMyCoupon) {
        this.myCoupon = isMyCoupon
      },
      closeExchange: function () {
        this.$emit('closeExchange', this.curScore)
      },
      getAllCoupons: function () {
        this.getAllCouponsAction({
          onSuccess: (data) => {
//          console.log(data)
            this.coupons = data
          },
          onError: () => {

          }
        })
      },
      getMyCoupons: function () {
        this.getMyCouponsAction({
          onSuccess: (data) => {
//          console.log(data)
            this.coupons = data
          },
          onError: () => {

          },
          username: this.username
        })
      },
      minusIntegration: function (integration) {
        this.curScore = this.curScore - integration
      }
    },
    watch: {
      showExchange: function () {
        this.curScore = this.integration
      },
      myCoupon: function () {
        if (!this.myCoupon) {
          this.getAllCoupons()
        } else {
          this.getMyCoupons()
        }
      }
    },
    mounted () {
      this.getAllCoupons()
    }
  }
</script>

<style scoped src="./VipInfo.css"></style>
