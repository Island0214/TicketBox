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

  export default {
    components: {
      MyNavBar,
      MyFooter
    },
    name: 'App'
  }
</script>

<style scoped src="./index.css"></style>
