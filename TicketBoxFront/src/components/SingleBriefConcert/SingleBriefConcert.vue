<template>
  <el-col :xs="8" :sm="8" :md="8" :lg="8" style="text-align: center">

    <div class="single-brief-concert-wrapper" @click="$router.push('/concert/' + info.concert_id)">
      <div class="img-wrapper">
        <img :src="info.pic">
      </div>
      <p class="title-p">{{ info.title }}</p>
      <p class="place-p">{{ info.place }}</p>
      <p class="time-p">{{ info.time }}</p>
      <p class="price-p">{{ info.price }}</p>

    </div>

  </el-col>
</template>

<script>
  export default {
    props: ['info'],
    name: 'HelloWorld',
    data () {
      return {
        recent: true
      }
    },
    methods: {}
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./SingleBriefConcert.css"></style>
