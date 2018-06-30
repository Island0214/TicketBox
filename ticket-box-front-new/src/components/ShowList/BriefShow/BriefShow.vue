<template>
  <div class="show-wrapper" v-if="schedule !== ''">
    <div class="img-wrapper" @click="openShow">
      <img :src="show.poster">
    </div>
    <div class="info-wrapper">
      <h5 @click="openShow">【{{ show.city }}】{{ show.schedule }}</h5>
      <p><i class="el-icon-service"></i>演出者：{{ show.artist }}</p>
      <p><i class="el-icon-news"></i>演出类型：{{ show.type }}</p>
      <p><i class="el-icon-location-outline"></i>演出地点：{{ schedule.venueName }}</p>
      <p><i class="el-icon-date"></i>演出时间：{{ new Date(show.time).toLocaleString() }}</p>
      <h6>¥ {{ schedule.minPrice }} - ¥ {{ schedule.maxPrice }}</h6>
    </div>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'
  export default {
    props: ['show'],
    name: "BriefShow",
    data () {
      return {
        schedule: ''
      }
    },
    methods: {
      ...mapActions({
        getSchedulePriceInfo: 'getSchedulePriceInfo'
      }),
      openShow: function () {
        window.open('/#/show/' + this.show.schedule_id, '__blank')
      }
    },
    mounted () {
      this.getSchedulePriceInfo({
        onSuccess: (data) => {
          // consol
          this.schedule = data
          // console.log(this.schedule)
        },
        onError: () => {

        },
        schedule: this.show.schedule_id
      })
    }
  }
</script>

<style scoped src="./BriefShow.css">

</style>
