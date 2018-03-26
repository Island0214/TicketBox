<template>
  <div class="venue-statistics-wrapper">
    <part-title title="预定信息"></part-title>
    <div class="info-wrapper">
      <el-row :gutte="20" style="text-align: center;">
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <div id="occupy-chart" class="chart-wrapper"></div>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <div id="count-chart" class="chart-wrapper"></div>
        </el-col>
      </el-row>
    </div>
    <part-title title="退订信息"></part-title>
    <div class="info-wrapper">
      <el-table
        :data="tableData"
        style="width: 100%; font-size: 18px; font-weight: 600;">
        <el-table-column
          label="订单编号"
        >
          <template slot-scope="scope">
            <!--<i class="el-icon-time"></i>-->
            <span>{{ scope.row.order_id }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="场馆区域"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.area }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="场馆座位"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.seat }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="订单时间">
          <template slot-scope="scope">
            <span>{{ new Date(scope.row.time).toLocaleString() }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="订单状态">
          <template slot-scope="scope">
            <span>已退订</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <part-title title="财务信息"></part-title>
    <div class="info-wrapper">
      <el-table
        :data="financeData"
        style="width: 100%; font-size: 18px; font-weight: 600;">
        <el-table-column
          label="演出名称"
        >
          <template slot-scope="scope">
            <!--<i class="el-icon-time"></i>-->
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="演出时间"
        >
          <template slot-scope="scope">
            <span>{{ new Date(scope.row.time).toLocaleString() }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="演出收入"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.income }}</span>
          </template>
        </el-table-column>
      </el-table>

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
        tableData: [],
        financeData: []
      }
    },
    computed: {
      ...mapGetters({
        code: 'name'
      })
    },
    methods: {
      ...mapActions({
        getVenueOrderStatistic: 'getVenueOrderStatistic',
        getDayOrderStatistic: 'getDayOrderStatistic',
        getVenueRefundOrder: 'getVenueRefundOrder',
        getFinanceStatistic: 'getFinanceStatistic'
      }),
      drawBarCharts: function () {
        let charts = echarts.init(document.getElementById('occupy-chart'))

        this.getVenueOrderStatistic({
          onSuccess: (data) => {
            let xAxisData = []
            let data1 = []

            for (let i = 0; i < data.length; i++) {
              xAxisData.push(data[i].name)
              data1.push(data[i].value)
            }

            charts.setOption({
              title: {
                text: '每场上座率',
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
              tooltip: {
                trigger: 'item',
                formatter: "{b} : {c}% "
              },
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
          },
          onError: () => {

          },
          code: this.code
        })
      },
      drawLineCharts: function () {
        let charts = echarts.init(document.getElementById('count-chart'))
        this.getDayOrderStatistic({
          onSuccess: (data) => {
            let xAxisData = []
            let data1 = []

            for (let i = 0; i < data.length; i++) {
              xAxisData.push(data[i].name)
              data1.push(data[i].value)
            }
            charts.setOption({
              title: {
                text: '每日订单数',
                left: 'center',
                top: 20
              },
              xAxis: {
                type: 'category',
                data: xAxisData
              },
              yAxis: {
                type: 'value',
                minInterval: 1
              },
              tooltip: {
                formatter: "{b}订单数 : {c} "
              },
              series: [{
                data: data1,
                type: 'line',
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
                }
              }]
            })

          },
          onError: () => {

          },
          code: this.code
        })

      }
    },
    mounted () {
      this.drawBarCharts()
      this.drawLineCharts()
      this.getVenueRefundOrder({
        onSuccess: (data) => {
          this.tableData = data
        },
        onError: () => {

        },
        code: this.code
      })
      this.getFinanceStatistic({
        onSuccess: (data) => {
          this.financeData = data
        },
        onError: () => {

        },
        code: this.code
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./VenueStatistics.css"></style>
