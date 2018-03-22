<template>
  <div class="concert-info-wrapper">
    <div class="concert-image-wrapper">
      <img :src="basicData.poster"/>
    </div>

    <div class="info-wrapper">
      <h1>{{ basicData.schedule }}</h1>

      <p>演出者：<span>{{ basicData.artist }}</span></p>
      <p>演出时间：<span>{{ new Date(basicData.time).toLocaleString() }}</span></p>
      <p>演出地点：<span>{{ location }}</span></p>

      <seat-selection :showButton="true" :schedule="$route.params.id"></seat-selection>

    </div>

    <div class="intro-wrapper">
      <part-title title="项目简介"></part-title>
      <pre>{{ basicData.intro }}</pre>
    </div>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import SeatSelection from '../SeatSelection/SeatSelection.vue'
  import {mapActions} from 'vuex'

  export default {
    components: {
      PartTitle,
      SeatSelection
    },
    data () {
      return {
        location: '',
        ticket_num: 1,
        basicData: {}
      }
    },
    methods: {
      ...mapActions({
        getScheduleBasicInfo: 'getScheduleBasicInfo',
        getSchedulePriceInfo: 'getSchedulePriceInfo'
      })
    },
    computed:{
    },
    mounted () {
//      console.log(this.$route.params.id)
      this.getScheduleBasicInfo({
        onSuccess: (data) => {
          console.log(data)
          this.basicData = data
        },
        onError: () => {

        },
        schedule: this.$route.params.id
      })

      this.getSchedulePriceInfo({
        onSuccess: (data) => {
          this.location = data.venueName
        },
        onError: () => {

        },
        schedule: this.$route.params.id
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ConcertInfo.css"></style>
