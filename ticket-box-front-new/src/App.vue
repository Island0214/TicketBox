<template>
    <div class="app">
        <my-nav-bar></my-nav-bar>
        <router-view/>
    </div>
</template>

<script>
    import MyNavBar from './components/MyNavBar/MyNavBar'

    export default {
        components: {
            MyNavBar
        }
    }
</script>

<style src="./index.css"></style>
