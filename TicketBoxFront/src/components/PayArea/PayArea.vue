<template>
  <div class="pay-area-wrapper">
    <h2 style="margin-bottom: 40px;">您的订单将在<span>{{ countDown }}</span>后关闭, 请及时付款！</h2>
    <part-title title="支付信息" class="title-wrapper" style="text-align: left;"></part-title>
    <div class="pay-wrapper">
      <p>请选择您的支付方式：</p>
      <el-radio v-model="pay_way" label="1">网上银行</el-radio>
      <!--<el-radio v-model="pay_way" label="2">微信支付</el-radio>-->
      <div v-if="pay_way === '1'" class="pay-info-wrapper">
        卡号:
        <el-input v-model="code"></el-input>
        <br>
        <br>
        <br>
        密码:
        <el-input type="password" v-model="password"></el-input>
      </div>
      <el-radio v-model="pay_way" label="2">支付宝</el-radio>
      <div v-if="pay_way === '2'" class="pay-info-wrapper">
        账号:
        <el-input v-model="code"></el-input>
        <br>
        <br>
        <br>
        密码:
        <el-input type="password" v-model="password"></el-input>
      </div>

      <el-button @click="payOrder">立即支付</el-button>
    </div>
  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import {mapActions} from 'vuex'

  export default {
    props: [
      'order'
    ],
    components: {
      PartTitle
    },
    data () {
      return {
        countDown: '',
        pay_way: '0',
        countInterval: '',
        password: '',
        code: ''
      }
    },
    methods: {
      ...mapActions({
        payOrderAction: 'payOrder'
      }),
      MillisecondToDate: function (msd) {
        var time = parseFloat(msd) / 1000
        if (null != time && '' != time) {
          if (time < 14 * 60) {
            time = (14 - parseInt(time / 60.0)) + '分钟' +  parseInt(60 - ((parseFloat(time / 60.0) -
              parseInt(time / 60.0))) * 60) + '秒'
          } else if (time >= 14 * 60 && time < 15 * 60) {
            time = parseInt(60 - ((parseFloat(time / 60.0) -
              parseInt(time / 60.0))) * 60) + '秒'
          } else {
            this.$router.push('/')
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
          this.$router.push('/')
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
    mounted () {
//      console.log(this.order.time)
//      console.log(new Date().getTime())

      if (new Date().getTime() - this.order.time > 15 * 60 * 1000) {
        this.$router.push('/')
      } else {
        this.countInterval = setInterval(() => {
          this.countDown = (this.MillisecondToDate(new Date().getTime() - this.order.time))
        }, 1000)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./PayArea.css"></style>
