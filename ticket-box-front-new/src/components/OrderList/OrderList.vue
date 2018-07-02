<template>
  <div class="order-list-wrapper">
    <!--<p>sad</p>-->
    <el-tabs type="border-card">
      <el-tab-pane :label="type">
        <el-collapse-transition>
          <div v-show="showLoading">
            <div class="transition-box"><i class="el-icon-loading"></i>订单加载中...</div>
            <!--<div class="transition-box">el-collapse-transition</div>-->
          </div>
        </el-collapse-transition>
        <!--<el-collapse-transition>-->
        <!--</el-collapse-transition>-->
        <order v-for="(order, index) in orders" :key="index" :order="order" :showButtons="true"></order>
        <div class="pagination-wrapper" v-if="totalPage !== 0">
          <el-pagination
            background
            layout="prev, pager, next"
            :page-count="totalPage"
            :current-page="curPage"
            @current-change="changePage"
          >
          </el-pagination>
        </div>
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
        orders: [],
        showLoading: false,
        totalPage: 1,
        curPage: 1
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
      changePage: function () {

      },
      getOrdersByPage: function (page) {
        this.showLoading = true
        this.getAllOrders({
          onSuccess: (data) => {
            console.log(data)
            this.orders = data
            this.showLoading = false
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
