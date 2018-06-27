<template>
  <div class="show-list-nav-wrapper">
    <h3>演出城市</h3>
    <p v-if="locate !== ''" @click="curCity = cities.indexOf(locate)"><i class="el-icon-location"></i>推荐进入<span>{{ locate }}</span>站
    </p>
    <div class="city-wrapper">
      <div class="info-wrapper" :style="{height: cityWrapperHeight }">
        <button v-for="(city, index) in cities"
                :key="index"
                :class="[{'is-active': index === curCity}]"
                @click="curCity = index"
        >
          {{ city }}
        </button>

        <a @click="setCityWrapperHeight()">
          {{ arrowType }}
          <i class="el-icon-arrow-down" v-if="arrowType === '更多'"></i>
          <i class="el-icon-arrow-up" v-if="arrowType === '收起'"></i>
        </a>
      </div>
    </div>

    <h3>演出类型</h3>
    <div class="type-wrapper">
      <div class="info-wrapper">
        <button v-for="(type, index) in types"
                :key="index"
                :class="[{'is-active': index === curType}]"
                @click="curType = index"
        >
          {{ type }}
        </button>
      </div>
    </div>

    <h3>演出时间</h3>
    <div class="type-wrapper" style="border-bottom: none; margin-bottom: 110px;">
      <div class="info-wrapper">
        <button v-for="(time, index) in times"
                :key="index"
                :class="[{'is-active': index === curTime}]"
                @click="curTime = index"
        >
          {{ time }}
        </button>
        <el-date-picker
          v-model="time"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        >
        </el-date-picker>
      </div>
    </div>

  </div>

</template>

<script>
  import BMap from 'BMap'

  export default {
    name: "ShowListNav",
    data() {
      return {
        locate: '',
        cities: ['全部', '南京', '上海', '廣州', '深圳', '北京', '杭州', '香港', '台灣'],
        types: ['全部', '演唱会', '话剧', '音乐剧'],
        times: ['全部', '最近一周', '最近一个月'],
        cityWrapperHeight: '60px',
        arrowType: '更多',
        curCity: 0,
        curType: 0,
        curTime: 0,
        time: []
      }
    },
    methods: {
      setCityWrapperHeight: function () {
        if (this.arrowType === '更多') {
          this.cityWrapperHeight = 'auto'
          this.arrowType = '收起'
        } else {
          this.cityWrapperHeight = '60px'
          this.arrowType = '更多'
        }
      }
    },
    watch: {
      time: {
        handler: function () {
          if (this.time.length !== 0) {
            // this.curTime = -1
            if (this.time[1].getTime() - this.time[0].getTime() === 3600 * 1000 * 24 * 7) {
              this.curTime = 1
            } else if (this.time[1].getTime() - this.time[0].getTime() === 3600 * 1000 * 24 * 30) {
              this.curTime = 2
            } else {
              this.curTime = -1
            }
          }
        }
      },
      curTime: function () {
        // if (this.curTime === -1) {
        //   this.time = []
        //   return
        // }
        let start = new Date();
        let end = new Date();
        switch (this.curTime) {
          case 0:
            this.time = []
            break;
          case 1:
            // console.log(1)
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 7)
            this.time = [start, end]
            break;
          case 2:
            // console.log(2)
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
            this.time = [start, end]
            break;
          // monthLayer.setWe(monthLayer.getWeak() + 1)
          // this.time.push(monthLayer)
        }
      }
    },
    mounted() {
      // 百度地图API功能
      // let map = new BMap.Map("allmap");
      let point = new BMap.Point(116.331398, 39.897445);
      let that = this

      function myFun(result) {
        that.locate = result.name.split('市')[0];
      }

      let myCity = new BMap.LocalCity();
      myCity.get(myFun);

      // 初试时间
      // let now = new Date()
      // this.time.push(now)
      // let monthLayer = new Date()
      // monthLayer.setMonth(monthLayer.getMonth()+1)
      // this.time.push(monthLayer)
    }
  }
</script>

<style scoped src="./ShowListNav.css">

</style>
