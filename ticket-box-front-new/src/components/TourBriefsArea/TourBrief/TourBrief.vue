<template>
  <div
    class="tour-brief-wrapper"
    :style="[{'width': width + 'px'}, {'height': height + 'px'}, {'left': left + 'px'}, {'top': top + 'px'}]"
    @mouseenter="setInfoStatus(true)"
    @mouseout="setInfoStatus(false)"
    @click="openShow"
  >
    <img :src="tour.poster">
    <div>
      <transition name="el-fade-in">
        <div v-show="showInfo">
          <div
            class="intro-wrapper"
          >
            <div class="text-wrapper">
              <h1>{{ tour.artist }}</h1>
              <p>{{ cities }}</p>
            </div>

          </div>
          <!--<div class="transition-box">el-collapse-transition</div>-->
          <!--<div class="transition-box">el-collapse-transition</div>-->
        </div>
      </transition>
    </div>
    <div class="invisible-wrapper">

    </div>
  </div>

</template>

<script>
  import {mapActions} from 'vuex'
  export default {
    props: ['width', 'height', 'left', 'top', 'tour'],
    name: "TourBrief",
    data() {
      return {
        showInfo: false,
        cities: '',
        schedule: 0
      }
    },
    methods: {
      ...mapActions({
        getTourScheduleById: 'getTourScheduleById'
      }),
      setInfoStatus(status) {
        this.showInfo = status
      },
      openShow: function () {
        window.open('/#/show/' + this.schedule, '_blank')
        // alert(this.schedule)
      }
    },
    mounted() {
      for (let i = 0; i < this.tour.cities.length; i++) {
        // console.log(this.tour.cities[i].city)
        this.cities = this.cities + this.tour.cities[i].city + '、'
      }
      // this.cities.
      this.cities = this.cities.substring(0, this.cities.length - 1)

      this.getTourScheduleById({
        onSuccess: (data) => {
          // console.log(data)
          // this.tours = data
          for (let i = 0; i < data.length; i++) {
            if (data[i].time >= new Date().getTime()) {
              this.schedule = data[i].scheduleId
              break;
            }
          }
        },
        onError: () => {
        },
        tour: this.tour.tourId
      })
    }
  }
</script>

<style scoped src="./TourBrief.css">

</style>
