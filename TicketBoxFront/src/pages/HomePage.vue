<template>
  <div>
    <login-area></login-area>
    <!--<img style="width: 100%" src="../assets/bg.png">-->
  </div>
</template>

<script>
import LoginArea from '../components/LoginArea/LoginArea.vue'

export default {
  components: {
    LoginArea
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
<style scoped>

</style>
