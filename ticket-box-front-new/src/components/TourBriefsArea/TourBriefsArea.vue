<template>
  <div class="tour-briefs-wrapper">
    <div class="info-wrapper">

      <el-tabs type="border-card" style="width: 855px;">
        <el-tab-pane label="热门巡演" style="height: 450px" v-if="tours.length === 5">
          <tour-brief :width="300" :height="435" :left="35" :top="25" :tour="tours[0]"></tour-brief>
          <tour-brief :width="180" :height="180" :left="350" :top="25" :tour="tours[1]"></tour-brief>
          <tour-brief :width="180" :height="240" :left="350" :top="220" :tour="tours[2]"></tour-brief>
          <tour-brief :width="275" :height="290" :left="545" :top="25" :tour="tours[3]"></tour-brief>
          <tour-brief :width="130" :height="130" :left="545" :top="330" :tour="tours[4]"></tour-brief>
          <!--<tour-brief :width="130" :height="130" :left="495" :top="25"></tour-brief>-->
          <!--<tour-brief :width="180" :height="240" :left="640" :top="25"></tour-brief>-->
          <!--<tour-brief :width="180" :height="180" :left="640" :top="280"></tour-brief>-->
          <div class="more-concert-button" @click="openPage('/#/shows')">
            <h1>MORE</h1>
            <h2 style="left: 0; top: 0;">更</h2>
            <h2 style="right: 0; top: 0;">多</h2>
            <h2 style="left: 0; bottom: 0;">演</h2>
            <h2 style="right: 0; bottom: 0;">出</h2>
          </div>
        </el-tab-pane>
      </el-tabs>

      <div class="chart-wrapper">
        <el-tabs type="border-card">
          <el-tab-pane label="热门戏剧" style="height: 168px;">
            <ul>
              <li v-for="(drama, index) in dramas" :key="index">
                <a @click="openShow(drama.sheduleId)"><span>{{ index + 1}}</span>{{ drama.scheduleName }}</a>
              </li>
            </ul>
          </el-tab-pane>
        </el-tabs>
      </div>
      <div class="chart-wrapper" style="top: 280px;">
        <el-tabs type="border-card">
          <el-tab-pane label="热门音乐剧" style="height: 170px;">
            <ul>
              <li v-for="(drama, index) in melodramas" :key="index">
                <a @click="openShow(drama.sheduleId)"><span>{{ index + 1}}</span>{{ drama.scheduleName }}</a>
              </li>
            </ul>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
  import TourBrief from './TourBrief/TourBrief'
  import {mapActions} from 'vuex'

  export default {
    components: {
      TourBrief
    },
    name: "TourBriefsArea",
    data() {
      return {
        dramas: [],
        tours: [],
        shows: [],
        melodramas: []
      }
    },
    methods: {
      ...mapActions({
        getRandomFiveTours: 'getRandomFiveTours',
        getHotOperas: 'getHotOperas',
        getHotConcerts: 'getHotConcerts'
      }),
      openPage: function (path) {
        window.open(path, '_blank')
      },
      openShow: function (id) {
        window.open('/#/show/' + id, '_blank')
      }
    },
    mounted () {
      this.getRandomFiveTours({
        onSuccess: (data) => {
          this.tours = data
          // console.log(data)
        },
        onError: () => {

        }
      })

      this.getHotOperas({
        onSuccess: (data) => {
          this.dramas = data
          // console.log(data)
        },
        onError: () => {

        }
      })

      this.getHotConcerts({
        onSuccess: (data) => {
          this.melodramas = data
          // console.log(data)
        },
        onError: () => {

        }
      })
    }
  }
</script>

<style scoped src="./TourBriefsArea.css">

</style>
