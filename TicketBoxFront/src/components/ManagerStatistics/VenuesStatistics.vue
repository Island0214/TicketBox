<template>
  <div class="manager-statistics-wrapper">
    <el-row style="margin: 0; padding: 0;">
      <el-col :xs="24" :sm="24" :md="8" :lg="8">
        <h4>场馆演出总数</h4>
        <div id="schedule-count-wrapper" class="chart-wrapper"></div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="8" :lg="8">
        <h4>场馆场均收入</h4>
        <div id="average-income-wrapper" class="chart-wrapper"></div>
      </el-col>
      <el-col :xs="24" :sm="24" :md="8" :lg="8">
        <h4>场馆场均上座率</h4>
        <div id="average-occupy-wrapper" class="chart-wrapper"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex'
  import echarts from 'echarts'

  export default {
    data () {
      return {
      }
    },
    computed: {
      ...mapActions({
        code: 'name'
      })
    },
    methods: {
      ...mapActions({
        getScheduleCountByVenue: 'getScheduleCountByVenue',
        getVenueAverageIncome: 'getVenueAverageIncome',
        getVenueAverageOccupy: 'getVenueAverageOccupy'
      }),
      drawBarCharts: function (id, xData, yData, minInterval) {
        let charts = echarts.init(document.getElementById(id))

        charts.setOption({
          tooltip: {
            trigger: 'item',
            formatter: "{b} : {c} "
          },
          grid: [{
            left: '40px'
          }],
          xAxis: {
            data: xData,
            silent: false,
            splitLine: {
              show: false
            }
          },
          yAxis: {
            type: 'value',
            minInterval: minInterval
          },
          series: [{
            type: 'bar',
//                barGap: '10px',
            barWidth: '20%',//固定柱子宽度
            barGap: '30%',
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    {offset: 0, color: '#9a1fe0'},
                    {offset: 0.5, color: '#871FCA'},
                    {offset: 1, color: '#6b1aa3'}
                  ]
                )
              },
              emphasis: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    {offset: 0, color: '#6b1aa3'},
                    {offset: 0.7, color: '#871FCA'},
                    {offset: 1, color: '#9a1fe0'}
                  ]
                )
              }
            },
            data: yData,
            animationDelay: function (idx) {
              return idx * 10;
            }
          }],
          animationEasing: 'elasticOut',
          animationDelayUpdate: function (idx) {
            return idx * 5;
          }
        })
      }
    },
    mounted () {
      this.getVenueAverageIncome({
        onSuccess: (data) => {
          let xAxisData = []
          let data1 = []

          for (let i = 0; i < data.length; i++) {
            xAxisData.push(data[i].name)
            data1.push(data[i].value)
          }

          this.drawBarCharts('average-income-wrapper', xAxisData, data1, 1)

        },
        onError: () => {

        }
      })

      this.getScheduleCountByVenue({
        onSuccess: (data) => {
          let xAxisData = []
          let data1 = []

          for (let i = 0; i < data.length; i++) {
            xAxisData.push(data[i].name)
            data1.push(data[i].value)
          }

          this.drawBarCharts('schedule-count-wrapper', xAxisData, data1, 1)

        },
        onError: () => {

        }
      })

      this.getVenueAverageOccupy({
        onSuccess: (data) => {
          let xAxisData = []
          let data1 = []

          for (let i = 0; i < data.length; i++) {
            xAxisData.push(data[i].name)
            data1.push(data[i].value)
          }

          this.drawBarCharts('average-occupy-wrapper', xAxisData, data1, 0.1)

        },
        onError: () => {

        }
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./statistics.css"></style>
