<template>
  <div class="pay-area-wrapper">
    <div class="breadcrumb-wrapper">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/order' }">订单</el-breadcrumb-item>
        <el-breadcrumb-item>支付</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="order-title-wrapper" v-if="order.order_id">
      <el-row :gutter="20" style="margin: 0">
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <p>订单编号：<span>{{ order.order_id }}</span></p>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <p style="text-align: right">总价：<span class="price-p">¥ {{ order.price }}</span></p>
        </el-col>
      </el-row>
    </div>
    <div class="order-info-wrapper" v-if="schedule.poster && venue.name">
      <div class="img-box">
        <img :src="schedule.poster"/>
      </div>
      <div class="info-wrapper">
        <p>
          <icon name="bullhorn"></icon>
          演出：<span>{{ schedule.schedule }}</span></p>
        <p>
          <icon name="map-marker-alt" style="margin-left: 2px; margin-right: 12px;"></icon>
          地点：<span>【{{ schedule.city }}】{{ venue.name }}</span></p>
        <p>
          <icon name="clock"></icon>
          时间：<span>{{ new Date(schedule.time).toLocaleString() }}</span></p>
        <!--<p>地点：<span>{{ order.name }}</span></p>-->
        <!--<p>区域：<span>{{ order.area }}</span></p>-->
        <p><icon name="couch"></icon>座位：<span>{{ seatsInfo.join(', ') }}</span></p>

        <h3>您的订单将在<span>{{ countDown }}</span>后关闭, 请及时付款！</h3>

        <el-tabs type="border-card" style="height: 295px;">
          <el-tab-pane label="网上银行">
            <el-radio v-model="pay_way" label="1"><img src="../../assets/pay/bank1.jpg"></el-radio>
            <el-radio v-model="pay_way" label="2"><img src="../../assets/pay/bank2.jpg"></el-radio>
            <el-radio v-model="pay_way" label="3"><img src="../../assets/pay/bank3.jpg" style="height: 60px; margin-top: 13px"></el-radio>
            <el-radio v-model="pay_way" label="4"><img src="../../assets/pay/bank4.jpg" style="height: 60px; margin-top: 13px"></el-radio>

            <el-button @click="payOrder">立即支付</el-button>
          </el-tab-pane>
          <el-tab-pane label="支付平台">
            <el-radio v-model="pay_way" label="1"><img src="../../assets/pay/pay1.png"></el-radio>
            <el-radio v-model="pay_way" label="2"><img src="../../assets/pay/pay2.png"></el-radio>
            <el-button @click="payOrder">立即支付</el-button>
          </el-tab-pane>
          <!--<el-tab-pane label="最新上架">-->

          <!--</el-tab-pane>-->
        </el-tabs>

      </div>
    </div>
  </div>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'

  export default {
    components: {},
    data() {
      return {
        countDown: '',
        pay_way: '0',
        countInterval: '',
        password: '',
        code: '',
        order: {},
        schedule: {},
        venue: {},
        seatsInfo: []
      }
    },
    computed: {
      ...mapGetters({
        username: 'name'
      })
    },
    methods: {
      ...mapActions({
        payOrderAction: 'payOrder',
        getOrderById: 'getOrderById',
        getScheduleBasicInfo: 'getScheduleBasicInfo',
        getVenueInfo: 'getVenueInfo',
        getSeatById: 'getSeatById'
      }),
      MillisecondToDate: function (msd) {
        let time = parseFloat(msd) / 1000
        if (null != time && '' != time) {
          if (time < 14 * 60) {
            time = (14 - parseInt(time / 60.0)) + '分钟' + parseInt(60 - ((parseFloat(time / 60.0) -
              parseInt(time / 60.0))) * 60) + '秒'
          } else if (time >= 14 * 60 && time < 15 * 60) {
            time = parseInt(60 - ((parseFloat(time / 60.0) -
              parseInt(time / 60.0))) * 60) + '秒'
          } else {
            // this.$router.push('/')
            this.$message({
              showClose: true,
              type: 'error',
              message: '订单已自动关闭！',
              customClass: 'message-wrapper'
            })
            clearInterval(this.countInterval)
            time = '0秒'
          }
        } else {
          // this.$router.push('/')
        }
        return time
      },
      payOrder: function () {
        let type = ''
        if (this.pay_way === '0') {
          this.$message({
            showClose: true,
            type: 'error',
            message: '请选择一种支付方式！',
            customClass: 'message-wrapper'
          })
          return
        }
        if (this.pay_way === '1') {
          type = '网上银行'
        } else {
          type = '支付宝'
        }
        this.payOrderAction({
          onSuccess: (data) => {
            console.log(data)
            this.$message({
              showClose: true,
              type: 'success',
              message: data.success,
              customClass: 'message-wrapper'
            })
            this.$router.push('/order')
          },
          onError: (error) => {
            this.$message({
              showClose: true,
              type: 'error',
              message: error,
              customClass: 'message-wrapper'
            })
          },
          body: {
            order_id: this.order.order_id,
            code: this.code,
            password: this.password,
            type: type
          }
        })
      }
    },
    mounted() {
      let that = this
      this.getOrderById({
        onSuccess: (data) => {
          // console.log('order')
          // console.log(data)
          if (data.username !== this.username) {
            this.$router.push('/')
          } else {
            this.order = data

            let seats = data.seatIds.split(',')
            console.log(seats)

            for (let i = 0; i < seats.length; i++) {
              that.getSeatById({
                onSuccess: (data) => {
                  console.log(data)
                },
                onError: () => {

                },
                body: {
                  seatId: parseInt(seats[i])
                }
              })

            }

            that.getScheduleBasicInfo({
              onSuccess: (data) => {
                // console.log('schedule')
                // console.log(data)
                that.schedule = data

                that.getVenueInfo({
                  onSuccess: (data) => {
                    // console.log('venue')
                    // console.log(data)
                    that.venue = data
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
              schedule: that.order.schedule
            })

            if (new Date().getTime() - this.order.time > 15 * 60 * 1000) {
              // this.$router.push('/')
            } else {
              this.countInterval = setInterval(() => {
                this.countDown = (this.MillisecondToDate(new Date().getTime() - this.order.time))
              }, 1000)
            }
          }
        },
        onError: () => {

        },
        body: {
          order_id: parseInt(this.$route.params.id)
        }
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./PayArea.css"></style>
