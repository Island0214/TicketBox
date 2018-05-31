<template>
  <div class="user-statistics-wrapper">
    <!--<p>s</p>-->
    <part-title title="统计信息"></part-title>

    <div class="info-wrapper">

      <el-row :gutte="20" style="text-align: center;">
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <div class="chart-wrapper" id="order-chart"></div>
        </el-col>
        <el-col :xs="12" :sm="12" :md="12" :lg="12">
          <div class="chart-wrapper" id="user-chart"></div>
        </el-col>
      </el-row>

    </div>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import {mapGetters, mapActions} from 'vuex'
  import echarts from 'echarts'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        statistics: {
          totalReserve: 0,
          totalCancel: 0
        }
      }
    },
    methods: {
      ...mapActions({
        getOrderStatistics: 'getOrderStatistics',
        getUserStatistics: 'getUserStatistics'
      }),
      drawPieChart: function () {
        let charts = echarts.init(document.getElementById('order-chart'))
        let loadingInstance = this.$loading({ fullscreen: true });

        this.getOrderStatistics({
          onSuccess: (data) => {
            console.log(data)
            for (let i = 0; i < data.length; i++) {
//              data[i].value = data[i].value * 50 + 300
            }
            charts.setOption({
              backgroundColor: '#fff',

              title: {
                text: '订单统计',
                left: 'center',
                top: 20,
                textStyle: {
                  color: '#333'
                }
              },

              tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
              },

              visualMap: {
                show: false,
                min: 0,
                max: 7,
                inRange: {
                  colorLightness: [0.3, 0.8]
                }
              },
              series : [
                {
                  name:'订单类型',
                  type:'pie',
                  radius : '55%',
                  center: ['50%', '50%'],
                  data: data.sort(function (a, b) { return a.value - b.value; }),
                  roseType: 'radius',
                  label: {
                    normal: {
                      textStyle: {
                        color: '#666'
                      }
                    }
                  },
                  labelLine: {
                    normal: {
                      lineStyle: {
                        color: '#999'
                      },
                      smooth: 0.2,
                      length: 10,
                      length2: 20
                    }
                  },
                  itemStyle: {
                    normal: {
                      color: '#871FCA',
                      shadowBlur: 200,
                      shadowColor: 'rgba(0, 0, 0, 0)'
                    }
                  },

                  animationType: 'scale',
                  animationEasing: 'elasticOut',
                  animationDelay: function (idx) {
                    return Math.random() * 200;
                  }
                }
              ]
            })
            loadingInstance.close()
          },
          onError: () => {

          },
          username: this.username
        })
      },
      drawBarChart: function () {
        let charts = echarts.init(document.getElementById('user-chart'))
        let loadingInstance = this.$loading({ fullscreen: true });

        this.getUserStatistics({
          onSuccess: (data) => {
            let xAxisData = []
            let data1 = []

            for (let i = 0; i < data.length; i++) {
              xAxisData.push(data[i].name)
              data1.push(data[i].value)
            }

            charts.setOption({
              title: {
                text: '每月消费统计',
                left: 'center',
                top: 20
              },
//              legend: {
//                data: ['bar', 'bar2'],
//                align: 'left'
//              },
//              toolbox: {
//                // y: 'bottom',
//                feature: {
//                  magicType: {
//                    type: ['stack', 'tiled']
//                  },
//                  dataView: {},
//                  saveAsImage: {
//                    pixelRatio: 2
//                  }
//                }
//              },
              tooltip: {},
              xAxis: {
                data: xAxisData,
                silent: false,
                splitLine: {
                  show: false
                }
              },
              yAxis: {
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
                data: data1,
                animationDelay: function (idx) {
                  return idx * 10;
                }
              }],
              animationEasing: 'elasticOut',
              animationDelayUpdate: function (idx) {
                return idx * 5;
              }
            })
            loadingInstance.close()
          },
          onError: () => {

          },
          username: this.username
        })


      }
    },
    computed: {
      ...mapGetters({
        username: 'name'
      })
    },
    watch: {
    },
    mounted () {
      this.drawPieChart()
      this.drawBarChart()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./UserStatistics.css"></style>
