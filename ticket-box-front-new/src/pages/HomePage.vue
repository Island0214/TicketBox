<template>
    <div class="home-page-wrapper">
        <img src="../assets/bg.png">
        <!--<my-nav-bar></my-nav-bar>-->
        <carousel></carousel>

    </div>
</template>

<script>
    import MyNavBar from '../components/MyNavBar/MyNavBar'
    import Carousel from '../components/Carousel/Carousel'
    export default {
        name: "HomePage",
        components: {
            MyNavBar,
            Carousel
        }
    }
</script>

<style scoped>
    .home-page-wrapper {
        /*position: relative;*/
        /*display: inline-block;*/
        /*background: url('../assets/bg.png');*/
        /*background-size: contain;*/
        width: 100%;
    }

    .home-page-wrapper img {
        position: absolute;
        width: 100%;
        top: 0;
        z-index: -1;
    }
</style>