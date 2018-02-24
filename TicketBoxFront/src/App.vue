<template>
  <div id="app">
    <my-nav-bar></my-nav-bar>
    <router-view/>
  </div>
</template>

<script>
import MyNavBar from './components/MyNavBar/MyNavBar.vue'

export default {
  components: {
    MyNavBar
  },
  name: 'App'
}
</script>

<style>
</style>
