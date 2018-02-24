<template>
  <div>
    <div class="nav-bar-wrapper">
      <router-link to="/">
        <p>TicketBox</p>
      </router-link>

      <div class="register-buttons-wrapper">
        <router-link to="/">
          <p>会员注册</p>
        </router-link>

        <router-link to="/">
          <p>场馆注册</p>
        </router-link>
      </div>
    </div>
    <div class="catalog-wrapper">
      <img src="../../assets/logo.png">
        <a @click="showLoginFrame('场馆')">场馆入口</a>
        <a @click="showLoginFrame('会员')">会员入口</a>
        <a @click="showLoginFrame('经理')">经理入口</a>
    </div>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
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
      setLoginType: 'setLoginType'
    }),
    showLoginFrame: function (type) {
      this.showLogin()
      this.setLoginType(type)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./MyNavBar.css"></style>
