<template>
  <div class="order-wrapper" v-if="order && venue.name">
    <div style="border: 1px solid #f5f7fa">
      <div class="order-title-wrapper">
        <el-row :gutter="20" style="margin: 0">
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <p>订单编号：<span>{{ order.orderId }}</span></p>
          </el-col>
          <el-col :xs="12" :sm="12" :md="12" :lg="12">
            <h5>{{ type }}</h5>
          </el-col>
        </el-row>
      </div>
      <div class="order-info-wrapper">
        <div class="img-box" @click="openShow()">
          <img :src="order.poster"/>
        </div>
        <div class="info-wrapper">
          <p><icon name="bullhorn"></icon>演出：<span style="cursor: pointer;" @click="openShow()">{{ order.scheduleName }}</span></p>
          <p><icon name="map-marker-alt" style="margin-left: 2px; margin-right: 12px;"></icon>地点：<span>【{{ schedule.city }}】{{ venue.name }}</span></p>
          <p><icon name="clock"></icon>时间：<span>{{ new Date(order.scheduleTime).toLocaleString() }}</span></p>
          <!--<p>地点：<span>{{ order.name }}</span></p>-->
          <!--<p>区域：<span>{{ order.area }}</span></p>-->
          <p><icon name="couch"></icon>座位：<span>{{ order.seatsInfo.join(', ') }}</span></p>
          <p style="margin-top: 0px;"><icon name="money-bill-wave"></icon>价格：<span class="price-p">¥ {{ order.price }}</span></p>
        </div>
      </div>
      <div class="order-footer-wrapper">
        <div class="buttons-wrapper" v-if="showButtons">
          <el-button v-if="type === '已付款订单'" @click="confirmRefund(order.orderId)">退款</el-button>
          <el-button v-if="type === '待付款订单'" @click="$router.push('/pay/' + order.orderId)">付款</el-button>
          <el-button v-if="type === '待付款订单'" @click="confirmCancel(order.orderId)">取消</el-button>
        </div>
        <p> 创建时间：{{ new Date(order.orderTime).toLocaleString() }}</p>
        <!--<img :src="url"/>-->
      </div>

    </div>
    <el-dialog
      title="取消订单"
      :visible.sync="showCancel"
      width="400px"
      top="20%"
      :show-close="false"
      >
      <span>您确定对订单{{ order.orderId }}进行取消订单操作吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showCancel = false">取 消</el-button>
        <el-button type="primary" @click="cancelOrder">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="退款"
      :visible.sync="showRefund"
      width="400px"
      top="20%"
      :show-close="false"
    >
      <span>您确定对订单{{ order.orderId }}进行退款操作吗？</span>
      <!--<br>-->
      <!--<span>当前退款可退回{{ rate }}%金额</span>-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="showRefund = false">取 消</el-button>
        <el-button type="primary" @click="refundOrder">确 定</el-button>
      </span>
    </el-dialog>
    <!--<p>{{ order.concert_name }}</p>-->
    <!--<p>{{ order.concert_name }}</p>-->
  </div>
</template>

<script>
  import { mapActions } from 'vuex'

  export default {
    props: ['order', 'showButtons'],
    data () {
      return {
        url: '',
        schedule: {},
        venue: {},
        showCancel: false,
        showRefund: false,
        rate: 100,
        type: ''
      }
    },
    methods: {
      ...mapActions({
        getScheduleBasicInfo: 'getScheduleBasicInfo',
        getVenueInfo: 'getVenueInfo',
        cancelOrderAction: 'cancelOrder',
        refundOrderAction: 'refundOrder'
      }),
      confirmCancel: function () {
        this.showCancel = true
      },
      openShow: function () {
        // console.log(this.scheduleId)
        window.open('/#/show/' + this.order.scheduleId, '_blank')
      },
      confirmRefund: function () {
        this.showRefund = true

        let diff = this.schedule.time - new Date()
        console.log(new Date(this.schedule.time).toLocaleString())
        console.log( new Date().toLocaleString())

        console.log(diff)
        console.log( 24 * 60 * 1000)
        console.log(5 * 24 * 60 * 1000)
//        console.log(diff)
        if (diff < 24 * 60 * 60 * 1000) {
          this.rate = 60
        } else if (diff < 5 * 24 * 60 * 60 * 1000) {
          this.rate = 70
        } else if (diff < 9 * 24 * 60 * 60 * 1000) {
          this.rate = 80
        } else if (diff < 15 * 24 * 60 * 60 * 1000) {
          this.rate = 90
        }

      },
      cancelOrder: function () {
        this.showCancel = false
        this.cancelOrderAction({
          onSuccess: (data) => {
            this.$message({
              showClose: true,
              message: data.success,
              type: 'success',
              customClass: 'message-wrapper'
            })
            this.type = "已取消订单"
          },
          onError: (error) => {
            this.$message({
              showClose: true,
              message: error,
              type: 'error',
              customClass: 'message-wrapper'
            })
          },
          body: {
            orderId: this.order.orderId
          }
        })
//        location.reload()
//        console.log('cancel')
      },
      refundOrder: function () {
        this.showRefund = false
        this.refundOrderAction({
          onSuccess: (data) => {
            this.$message({
              showClose: true,
              message: data.success,
              type: 'success',
              customClass: 'message-wrapper'
            })
            this.type = "已取消订单"
          },
          onError: (error) => {
            this.$message({
              showClose: true,
              message: error,
              type: 'error',
              customClass: 'message-wrapper'
            })
          },
          body: {
            orderId: this.order.orderId
          }
        })
      }
    },
    watch: {
      orderType: function () {
      },
      order: function () {
//        console.log(this.order.schedule)
        this.getScheduleBasicInfo({
          onSuccess: (data) => {
//          console.log(data)
            this.schedule = data

            this.getVenueInfo({
              onSuccess: (data) => {
//              console.log(data)
                this.venue = data
              },
              onError: () => {

              },
              body: {
                code: data.venue
              }
            })

          },
          onError: () => {

          },
          schedule: parseInt(this.order.schedule)
        })
      }
    },
    mounted () {
      console.log(this.order)
      // this.order_id = this.order.orderId
      this.type = this.order.type

      this.getScheduleBasicInfo({
        onSuccess: (data) => {
//          console.log(data)
          this.schedule = data

          this.getVenueInfo({
            onSuccess: (data) => {
             // console.log(data)
              this.venue = data
            },
            onError: () => {

            },
            body: {
              code: data.venue
            }
          })

        },
        onError: () => {

        },
        schedule: this.order.scheduleId
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./Order.css"></style>
