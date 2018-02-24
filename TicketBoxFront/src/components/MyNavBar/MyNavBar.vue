<template>
  <div>
    <div class="nav-bar-wrapper">
      <router-link to="/">
        <p>TicketBox</p>
      </router-link>

      <div class="register-buttons-wrapper">
        <router-link to="/">
          <p>用户注册</p>
        </router-link>

        <router-link to="/">
          <p>场馆注册</p>
        </router-link>
      </div>
    </div>
    <div class="catalog-wrapper">
      <img src="../../assets/logo.png">
      <router-link to="/">
        <p>场馆入口</p>
      </router-link>
      <router-link to="/">
        <p>会员入口</p>
      </router-link>
      <router-link to="/">
        <p>经理入口</p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./MyNavBar.css"></style>
