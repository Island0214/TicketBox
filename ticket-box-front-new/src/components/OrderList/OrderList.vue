<template>
  <div class="order-list-wrapper">
    <!--<p>sad</p>-->
    <el-tabs type="border-card">
      <el-tab-pane :label="type">
        <!--<el-collapse-transition>-->
        <!--</el-collapse-transition>-->
        <order v-for="(order, index) in orders" :key="index" :order="order" :showButtons="true"></order>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'
  import Order from './Order/Order'

  export default {
    name: "OrderList",
    components: {
      Order
    },
    data() {
      return {
        type: '全部订单',
        orders: []
      }
    },
    computed: {
      ...mapGetters({
        orderType: 'orderType',
        name: 'name'
      })
    },
    watch: {
      orderType: function () {
        console.log(this.orderType)
        if (this.orderType === '') {
          this.type = '全部订单'
        } else {
          this.type = this.orderType
        }
        this.getOrdersByPage(1)
      }
    },
    methods: {
      ...mapActions({
        getAllOrders: 'getAllOrders'
      }),
      getOrdersByPage: function (page) {

        this.getAllOrders({
          onSuccess: (data) => {
            console.log(data)
            this.orders = data
          },
          onError: () => {

          },
          body: {
            pageSize: 15,
            pageNum: page,
            username: this.name,
            status: this.orderType
          }
        })
      }
    },
    mounted () {
      // console.log(this.orderType)
      this.getOrdersByPage(1)
    }
  }
</script>

<style scoped src="./OrderList.css">

</style>
