<template>
  <div class="manager-statistics-wrapper">
    <h4>总会员人数: <span>{{ total }}</span></h4>
    <h4>会员等级分布</h4>
    <div id="vip-cate-wrapper" class="chart-wrapper" style="width: 400px;"></div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  import echarts from 'echarts'

  export default {
    data () {
      return {
        total: 0
      }
    },
    methods: {
      ...mapActions({
        getVipStatistics: 'getVipStatistics'
      }),
      drawPieChart: function () {
        let charts = echarts.init(document.getElementById('vip-cate-wrapper'))

        this.getVipStatistics({
          onSuccess: (data) => {
            this.total = data.total_count

            charts.setOption({
              backgroundColor: '#fff',

              tooltip : {
                trigger: 'item',
                formatter: "{b}会员 : {c} 个"
              },

              visualMap: {
                show: false,
                min: 0,
                max: 5,
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
                  data: data.intInfoBeans.sort(function (a, b) { return a.value - b.value; }),
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
          },
          onError: () => {

          }
        })
      },

    },
    mounted () {
      this.drawPieChart()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./statistics.css"></style>
