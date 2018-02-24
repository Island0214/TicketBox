<template>
  <el-collapse-transition>
    <div class="home-page-wrapper">
      <img src="../../assets/bg.png">
      <div class="login-wrapper">
        <h1>会员登录</h1>

        <h3 style="margin-top: 30%;">账 户</h3>
        <el-input></el-input>
        <h3 style="margin-top: 20%;">密 码</h3>
        <el-input type="password"></el-input>

        <el-button>登 录</el-button>
      </div>
    </div>
  </el-collapse-transition>
</template>

<script>
export default {
  components: {
  },
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./LoginArea.css">
</style>
