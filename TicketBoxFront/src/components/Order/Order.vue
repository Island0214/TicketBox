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
        <img :src="schedule.poster"/>
      </div>
      <div class="info-wrapper">
        <h3>{{ schedule.schedule }}</h3>
        <p>时间：<span>{{ new Date(schedule.time).toLocaleString() }}</span></p>
        <p>地点：<span>{{ venue.name }}</span></p>
        <p>区域：<span>{{ order.area }}</span></p>
        <p>座位：<span>{{ order.seat }}</span></p>
        <p
          style="float: right; font-size: 12px; margin-bottom: 0; position: absolute; bottom: -25px; right: 0; color: #999;">
          创建时间：{{ new Date(order.time).toLocaleString() }}</p>
      </div>
    </div>
    <div class="order-footer-wrapper">
      <h6>订单价格：<span>¥{{ order.price }}</span></h6>
      <div class="buttons-wrapper" v-if="showButtons">
        <el-button v-if="order.type === '已付款订单'" @click="confirmRefund(order.order_id)">退款</el-button>
        <el-button v-if="order.type === '待付款订单'" @click="$router.push('/pay/' + order.order_id)">付款</el-button>
        <el-button v-if="order.type === '待付款订单'" @click="confirmCancel(order.order_id)">取消</el-button>
      </div>
      <!--<img :src="url"/>-->
    </div>

    <el-dialog
      title="提示"
      :visible.sync="showCancel"
      width="30%"
      top="20%"
      >
      <span>您确定对订单{{ order_id }}进行取消订单操作吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showCancel = false">取 消</el-button>
        <el-button type="primary" @click="cancelOrder">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="提示"
      :visible.sync="showRefund"
      width="30%"
      top="20%"
    >
      <span>您确定对订单{{ order_id }}进行退款操作吗？</span>
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
        url: require('../../assets/poster4.jpg'),
        schedule: {},
        venue: {},
        showCancel: false,
        showRefund: false,
        order_id: 0
      }
    },
    methods: {
      ...mapActions({
        getScheduleBasicInfo: 'getScheduleBasicInfo',
        getVenueInfo: 'getVenueInfo',
        cancelOrderAction: 'cancelOrder',
        refundOrderAction: 'refundOrder'
      }),
      confirmCancel: function (id) {
        this.order_id = id
        this.showCancel = true
      },
      confirmRefund: function (id) {
        this.order_id = id
        this.showRefund = true
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
            this.order.type = "已取消订单"
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
            order_id: this.order_id
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
            this.order.type = "已退订订单"
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
            order_id: this.order_id
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
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./Order.css"></style>
