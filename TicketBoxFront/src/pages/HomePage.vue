<template>
  <div>
    <login-area></login-area>
    <!--<img style="width: 100%; height: 8x0px" src="../assets/footerbg.jpg">-->
    <brief-concerts></brief-concerts>
    <!--<el-carousel :interval="4000" type="card" height="200px" style="width: 300px">-->
      <!--<el-carousel-item v-for="item in 6" :key="item">-->
        <!--<h3>{{ item }}</h3>-->
      <!--</el-carousel-item>-->
    <!--</el-carousel>-->
  </div>
</template>

<script>
import LoginArea from '../components/LoginArea/LoginArea.vue'
import BriefConcerts from '../components/BriefConcerts/BriefConcerts.vue'

export default {
  components: {
    LoginArea,
    BriefConcerts
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
<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
