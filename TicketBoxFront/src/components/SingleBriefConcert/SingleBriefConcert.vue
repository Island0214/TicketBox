<template>
  <el-col :xs="8" :sm="8" :md="8" :lg="8" style="text-align: center">

    <div class="single-brief-concert-wrapper" @click="$router.push('/concert/' + info.concert_id)">
      <div class="img-wrapper">
        <img :src="info.poster">
      </div>
      <p class="title-p">{{ info.schedule }}</p>
      <p class="place-p">{{ info.venue }}</p>
      <p class="time-p">{{ new Date(info.time).toLocaleString() }}</p>
      <p class="price-p">{{ info.price }}</p>

    </div>

  </el-col>
</template>

<script>
  import {mapActions} from 'vuex'
  export default {
    props: ['info'],
    name: 'HelloWorld',
    data () {
      return {
        recent: true
      }
    },
    methods: {
      ...mapActions({
        getSchedulePriceInfo: 'getSchedulePriceInfo'
      })
    },
    mounted () {
      console.log(this.info)
      this.getSchedulePriceInfo({
        onSuccess: (data) => {
          console.log(data)
        },
        onError: () => {

        },
        schedule: this.info.venue
      })

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./SingleBriefConcert.css"></style>
