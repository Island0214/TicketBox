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

  export default {
    components: {
      MyNavBar,
      MyFooter
    }
  }
</script>

<style src="./index.css"></style>
