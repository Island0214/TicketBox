<template>
  <div class="advertisement-list-wrapper">
    <h5>根据您的所在地向您推荐</h5>
    <div class="advertisement-wrapper" v-for="item in advertisements" @click="openShow(item.schedule_id)">
      <div class="img-wrapper">
        <img :src="item.poster">
      </div>
      <div class="info-wrapper">
        <p class="title-p">【{{ item.city  }}】{{ item.schedule  }}</p>
        <!--<p class="body-p">{{ item.venueName }}</p>-->
        <p class="body-p">{{ new Date(item.time).toLocaleString() }}</p>
        <p class="price-p"><span>¥ {{item.minPrice}} - ¥ {{item.maxPrice}}</span></p>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  import BMap from 'BMap'

  export default {
    name: "AdvertisementListRight",
    data () {
      return {
        locate: '',
        advertisements: []
      }
    },
    methods: {
      ...mapActions({
        getCitySchedule: 'getCitySchedule',
        getSchedulePriceInfo: 'getSchedulePriceInfo'
      }),
      openShow: function (id) {
        window.open('/#/show/' + id, '_blank')
      }
    },
    mounted () {
      let point = new BMap.Point(116.331398, 39.897445);
      let that = this

      function myFun(result) {
        that.locate = result.name.split('市')[0];

        that.getCitySchedule({
          onSuccess: (data) => {
            // this.cities = this.cities.concat(data)
            // console.log(data)
            // this.advertisements = data

            for (let i = 0; i < data.length; i++) {
              let schedule = data[i]
              // alert(schedule.schedule_id)

              that.getSchedulePriceInfo({
                onSuccess: (data) => {
                  // this.scheduleInfo = data
                  schedule.venueName = data.venueName
                  schedule.minPrice = data.minPrice
                  schedule.maxPrice = data.maxPrice
                  that.advertisements.push(schedule)
                },
                onError: () => {

                },
                schedule: schedule.schedule_id
              })
            }
          },
          onError: () => {

          },
          city: that.locate
        })
      }

      let myCity = new BMap.LocalCity();
      myCity.get(myFun);
    }
  }
</script>

<style scoped src="./AdvertisementListRight.css">

</style>
