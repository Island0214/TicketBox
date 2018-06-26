<template>
  <el-col :xs="size" :sm="size" :md="size" :lg="size" style="text-align: center">

    <div class="single-brief-concert-wrapper" @click="pushToInfo">
      <div class="img-wrapper">
        <img :src="info.poster">
      </div>
      <p class="title-p">{{ info.schedule }}</p>
      <p class="place-p">{{ scheduleInfo.venueName }}</p>
      <!--<p class="time-p">{{ new Date(info.time).toLocaleString() }}</p>-->
      <p class="price-p">¥ {{ scheduleInfo.minPrice }} - ¥ {{ scheduleInfo.maxPrice }}</p>

    </div>

  </el-col>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'

  export default {
    props: ['info', 'size'],
    name: 'HelloWorld',
    data() {
      return {
        recent: true,
        scheduleInfo: {}
      }
    },
    computed: {},
    methods: {
      ...mapActions({
        getSchedulePriceInfo: 'getSchedulePriceInfo'
      }),
      pushToInfo: function () {

      }
    },
    mounted() {
//      console.log(this.info)
//       this.getSchedulePriceInfo({
//         onSuccess: (data) => {
//           this.scheduleInfo = data
// //          console.log(data)
//         },
//         onError: () => {
//
//         },
//         schedule: this.info.schedule_id
//       })

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./SingleBriefConcert.css"></style>
