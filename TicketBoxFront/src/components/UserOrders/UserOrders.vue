<template>
  <div class="user-orders-wrapper">
    <el-row :gutter="20">
      <el-col :xs="6" :sm="6" :md="6" :lg="6">
        <div class="tabs-wrapper tabs-cur-wrapper">
          <a :class="{'select-tab' : orderType === '全部订单'}" @click="selectOrderType('全部订单')">全 部 订 单</a>
          <!--<a>最新演出</a>-->
          <br>
          <a :class="{'select-tab' : orderType === '已完成订单'}" @click="selectOrderType('已完成订单')">已 完 成 订 单</a>
          <br>

          <a :class="{'select-tab' : orderType === '已付款订单'}" @click="selectOrderType('已付款订单')">已 付 款 订 单</a>
          <br>

          <a :class="{'select-tab' : orderType === '待付款订单'}" @click="selectOrderType('待付款订单')">待 付 款 订 单</a>
          <br>

          <a :class="{'select-tab' : orderType === '已退订订单'}" @click="selectOrderType('已退订订单')">已 退 订 订 单</a>
          <br>

          <a :class="{'select-tab' : orderType === '已取消订单'}" @click="selectOrderType('已取消订单')" style="margin-bottom: 0;">已 取 消 订 单</a>
        </div>
      </el-col>
      <el-col :xs="18" :sm="18" :md="18" :lg="18">
        <div class="orders-wrapper">
          <h2 v-if="showNoOrderNotification" class="note">暂无该类订单...</h2>

          <order v-for="order in visibleOrders" :order="order" :key="order.order_id"
                 :showButtons="true"></order>
          <!--<order v-for="order in orders" :order="order" :key="order.order_id"-->
                 <!--v-if="order.type === orderType || orderType === '全部订单'" :showButtons="true"></order>-->
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import Order from '../Order/Order.vue'
  import {mapGetters, mapActions} from 'vuex'
  //  import Order from '../../assets'

  export default {
    components: {
      Order
    },
    data () {
      return {
        orderType: '',
        orders: [{
          order_id: '1',
          created_at: '2018-03-03 11:23:12',
          type: '已完成订单',
          start_time: '2018-04-11 20:00',
          location: '南京欧拉艺术空间',
          concert_name: 'deca joins《春天游泳》2018巡演南京站',
          seat: '5排6座',
          price: '388',
          poster: '../../assets/poster1.jpg'
        },
          {
            order_id: '2',
            created_at: '2018-03-03 11:23:12',
            type: '已付款订单',
            start_time: '2018-04-11 20:00',
            location: '南京欧拉艺术空间',
            concert_name: 'deca joins《春天游泳》2018巡演南京站',
            seat: '5排6座',
            price: '388',
            poster: '../../assets/poster1.jpg'
          },
          {
            order_id: '3',
            created_at: '2018-03-03 11:23:12',
            type: '待付款订单',
            start_time: '2018-04-11 20:00',
            location: '南京欧拉艺术空间',
            concert_name: 'deca joins《春天游泳》2018巡演南京站',
            seat: '5排6座',
            price: '388',
            poster: '../../assets/poster1.jpg'
          },
          {
            order_id: '4',
            created_at: '2018-03-03 11:23:12',
            type: '已退订订单',
            start_time: '2018-04-11 20:00',
            location: '南京欧拉艺术空间',
            concert_name: 'deca joins《春天游泳》2018巡演南京站',
            seat: '5排6座',
            price: '388',
            poster: '../../assets/poster1.jpg'
          }
        ],
        showNoOrderNotification: false,
        visibleOrders: []
      }
    },
    methods: {
      ...mapActions({
        getAllOrders: 'getAllOrders'
      }),
      selectOrderType: function (type) {
        this.orderType = type
      }
    },
    computed: {
      ...mapGetters({
        username: 'name'
      })
    },
    watch: {
      orderType: function () {
//        this.getAllOrders({
//          onSuccess: (data) => {
//            this.orders = data.reverse()
//          },
//          onError: () => {
//          },
//          body: {
//            username: this.username
//          }
//        })
//        if (this.orderType !== '全部订单') {
        this.visibleOrders = []
        for (let i = 0; i < this.orders.length; i++) {
          if (this.orders[i].type === this.orderType || this.orderType === '全部订单') {
            this.visibleOrders.push(this.orders[i])
          }
        }
        if (this.visibleOrders.length !== 0) {
          this.showNoOrderNotification = false
        } else {
          this.showNoOrderNotification = true
        }
        console.log(this.visibleOrders.length)
        console.log(this.orderType)
      }
    },
    mounted () {
      this.orderType = '全部订单'
      this.getAllOrders({
        onSuccess: (data) => {
          this.orders = data.reverse()
        },
        onError: () => {

        },
        body: {
          username: this.username
        }
      })
//      console.log(this.orderType)
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./UserOrders.css"></style>
