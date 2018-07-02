<template>
  <div class="order-nav-wrapper">
    <!--<p>asd</p>-->
    <div class="breadcrumb-wrapper">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>订单</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
    >
      <el-menu-item v-for="(item, index) in titles" :key="item" :index="index + ''" @click="selectType(types[index])">
        <i class="el-icon-menu"></i>
        <span slot="title">{{ item }}</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex'

  export default {
    name: "OrderNav",
    data () {
      return {
        types: ['', '已完成订单', '已付款订单', '待付款订单', '已取消订单'],
        titles: ['全部订单', '已完成订单', '已付款订单', '待付款订单', '已取消订单'],
        activeIndex: '0'
      }
    },
    methods: {
      ...mapMutations({
        setOrderType: 'setOrderType'
      }),
      selectType: function (type) {
        this.setOrderType(type)
      }
    },
    watch: {
      activeIndex: function () {
        // alert('as')
        // this.setOrderType(this.types[this.activeIndex])
      }
    }
  }
</script>

<style scoped src="./OrderNav.css">

</style>
