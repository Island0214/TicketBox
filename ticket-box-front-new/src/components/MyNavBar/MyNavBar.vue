<template>
  <div class="nav-wrapper">
    <!--<img class="nav-bg" src="../../assets/navbg.png">-->
    <img class="nav-bg" src="../../assets/bg.png">

    <img src="../../assets/logo.png"/>
    <el-input type="input"></el-input>
    <el-button><i class="el-icon-search"></i></el-button>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
      <el-menu-item index="0">首页</el-menu-item>
      <el-menu-item index="1">演出</el-menu-item>
      <el-menu-item index="2">订单</el-menu-item>
      <el-submenu index="3">
        <template slot="title">用户</template>
        <el-menu-item index="2-1">登陆</el-menu-item>
        <el-menu-item index="2-2">注册</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
  export default {
    name: "MyNavBar",
    data() {
      return {
        activeIndex: '0'
      }
    }
  }
</script>

<style scoped src="./MyNavBar.css"></style>
