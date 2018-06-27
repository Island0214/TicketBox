<template>
  <div class="tour-briefs-wrapper">
    <div class="info-wrapper">

      <el-tabs type="border-card" style="width: 855px;">
        <el-tab-pane label="热门巡演" style="height: 450px">
          <tour-brief :width="300" :height="435" :left="35" :top="25"></tour-brief>
          <tour-brief :width="180" :height="180" :left="350" :top="25"></tour-brief>
          <tour-brief :width="180" :height="240" :left="350" :top="220"></tour-brief>
          <tour-brief :width="275" :height="290" :left="545" :top="25"></tour-brief>
          <tour-brief :width="130" :height="130" :left="545" :top="330"></tour-brief>
          <!--<tour-brief :width="130" :height="130" :left="495" :top="25"></tour-brief>-->
          <!--<tour-brief :width="180" :height="240" :left="640" :top="25"></tour-brief>-->
          <!--<tour-brief :width="180" :height="180" :left="640" :top="280"></tour-brief>-->
          <div class="more-concert-button">
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
          <el-tab-pane label="热门话剧" style="height: 450px;">
            <ul>
              <li v-for="(drama, index) in dramas" :key="index">
                <a><span>{{ index + 1}}</span>{{ drama.title }}</a>
              </li>
            </ul>
          </el-tab-pane>
          <el-tab-pane label="热门音乐剧" style="height: 450px;">

          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
  import TourBrief from './TourBrief/TourBrief'

  export default {
    components: {
      TourBrief
    },
    name: "TourBriefsArea",
    data () {
      return {
        dramas: [{
          title: 'sadasdasdasdasdsafsafsafsfsaf'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        },{
          title: 'sadasdasdasdasd'
        }],
        melodramas: []
      }
    }
  }
</script>

<style scoped src="./TourBriefsArea.css">

</style>
