<template>
  <div class="app">
    <my-nav-bar></my-nav-bar>
    <router-view/>
    <my-footer></my-footer>
  </div>
</template>

<script>
  import MyNavBar from './components/MyNavBar/MyNavBar'
  import MyFooter from './components/MyFooter/MyFooter'
  import { mapMutations } from 'vuex'

  export default {
    components: {
      MyNavBar,
      MyFooter
    },
    methods: {
      ...mapMutations({
        setLogType: 'setLogType',
        setUsername: 'setUsername'
      })
    },
    created () {
//    console.log(localStorage.getItem('username'))
      if (localStorage.getItem('username') !== null) {
        this.setUsername(localStorage.getItem('username'))
        this.setLogType(localStorage.getItem('type'))
        // alert(localStorage.getItem('username'))
      }
    }
  }
</script>

<style src="./index.css"></style>
