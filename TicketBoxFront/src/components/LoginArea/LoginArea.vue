<template>
  <div class="home-page-wrapper">
    <img src="../../assets/bg.png">
    <el-collapse-transition>

      <div class="login-wrapper" v-show="showLogin">
        <h1>{{ this.loginType }}登录</h1>
        <i class="el-icon-close" @click="hideLogin"></i>

        <h3 style="margin-top: 30%;" v-if="this.loginType !== '场馆'">账 号</h3>
        <h3 style="margin-top: 30%;" v-if="this.loginType === '场馆'">编 号</h3>
        <el-input></el-input>
        <h3 style="margin-top: 20%;">密 码</h3>
        <el-input type="password"></el-input>

        <el-button>登 录</el-button>
      </div>
    </el-collapse-transition>
  </div>
</template>

<script>
import {mapGetters, mapMutations} from 'vuex'

export default {
  components: {
  },
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  computed: {
    ...mapGetters({
      showLogin: 'showLogin',
      loginType: 'loginType'
    })
  },
  methods: {
    ...mapMutations({
      hideLogin: 'hideLogin'
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./LoginArea.css">
</style>
