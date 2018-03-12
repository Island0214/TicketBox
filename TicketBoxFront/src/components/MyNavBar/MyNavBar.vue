<template>
  <div>
    <div class="nav-bar-wrapper">
      <router-link to="/">
        <p>TicketBox</p>
      </router-link>

      <div class="register-buttons-wrapper" v-if="!logStatus">
          <!--<p @click="openPage('/vipSign')">会员注册</p>-->
        <p @click="showSigninFrame('会员')">会员注册</p>
        <p @click="showSigninFrame('场馆')">场馆注册</p>
      </div>

      <div class="register-buttons-wrapper" v-else>
        <p>{{ name }}</p>
        <p @click="quitLogin">退出</p>
        <!--<p @click="openPage('/vipSign')">会员注册</p>-->
        <!--<p @click="showSignin('会员')">会员注册</p>-->
        <!--<p @click="showSignin('场馆')">场馆注册</p>-->
      </div>
    </div>
    <div class="catalog-wrapper">
      <!--<router-link to="/">-->
      <img src="../../assets/logo.png" @click="$router.push('/')">
      <!--</router-link>-->
      <div v-if="!logStatus">
        <a @click="showLoginFrame('场馆')">场馆入口</a>
        <a @click="showLoginFrame('会员')">会员入口</a>
        <a @click="showLoginFrame('经理')">经理入口</a>
      </div>

      <div v-else>
        <div v-if="logType === '场馆'">
          <router-link to="/venue/info">
            <a>场馆信息</a>
          </router-link>
          <router-link to="/venue/schedule">
            <a>计划发布</a>
          </router-link>
          <router-link to="/venue/live">
            <a>现场事务</a>
          </router-link>
          <router-link to="/venue/tickets">
            <a>票务信息</a>
          </router-link>
        </div>
        <div v-if="logType === '会员'">
          <!--<a>会员天地</a>-->
          <router-link to="/concerts">
            <a>所有演出</a>
          </router-link>
          <router-link to="/order">
            <a>我的订单</a>
          </router-link>
          <router-link to="/info">
            <a>我的信息</a>
          </router-link>
        </div>
        <div v-if="logType === '经理'">
          <router-link to="/">
            <a>场馆审批</a>
          </router-link>
          <router-link to="/">
            <a>支付结算</a>
          </router-link>
          <router-link to="/">
            <a>统计信息</a>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapMutations, mapGetters} from 'vuex'
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    ...mapMutations({
      showLogin: 'showLogin',
      showSignin: 'showSignin',
      setLoginType: 'setLoginType',
      quitLog: 'quitLog'
    }),
    showLoginFrame: function (type) {
      this.showLogin()
      this.setLoginType(type)
      this.$router.push('/')
    },
    showSigninFrame: function (type) {
      this.showSignin(type)
      this.$router.push('/')
    },
    openPage: function (router) {
      window.open(window.location. origin + router)
    },
    quitLogin: function () {
      this.quitLog()
      this.$router.push('/')
    }
  },
  computed: {
    ...mapGetters({
      logStatus: 'logStatus',
      name: 'name',
      logType: 'logType'
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./MyNavBar.css"></style>
