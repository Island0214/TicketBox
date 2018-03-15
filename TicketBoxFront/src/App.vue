<template>
  <div id="app">
    <my-nav-bar></my-nav-bar>
    <router-view/>
    <my-footer></my-footer>
  </div>
</template>

<script>
  import MyNavBar from './components/MyNavBar/MyNavBar.vue'
  import MyFooter from './components/MyFooter/MyFooter.vue'
  import {mapMutations} from 'vuex'

  export default {
    components: {
      MyNavBar,
      MyFooter
    },
    name: 'App',
    methods: {
      ...mapMutations({
        setLogType: 'setLogType',
        setUsername: 'setUsername'
      })
    },
    mounted () {
//    console.log(localStorage.getItem('username'))
      if (localStorage.getItem('username') !== null) {
        this.setUsername(localStorage.getItem('username'))
        this.setLogType(localStorage.getItem('type'))
      }
    }
  }
</script>

<style scoped src="./index.css">
</style>
