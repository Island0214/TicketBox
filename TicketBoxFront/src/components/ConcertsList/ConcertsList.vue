<template>
  <div class="concerts-wrapper">
    <div class="params-wrapper">
      <el-input></el-input>
      <i class="el-icon-search"></i>
    </div>

    <div class="params-wrapper">
      演出类型：
      <el-select v-model="type" placeholder="请选择">
        <el-option
          v-for="item in types"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      演出时间：
      <el-date-picker
        v-model="time"
        type="daterange"
        align="right"
        unlink-panels
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :picker-options="pickerOptions2">
      </el-date-picker>
    </div>

    <el-row :gutter="20" style="margin: 0">
      <single-brief-concert v-for="(concert, index) in concerts" :key="index" :info="concert" style="margin-top: 40px;"></single-brief-concert>
    </el-row>

    <div class="pagination-wrapper">
      <el-pagination
        layout="prev, pager, next"
        :total="1000">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import SingleBriefConcert from '../../components/SingleBriefConcert/SingleBriefConcert.vue'

  export default {
    components: {
      SingleBriefConcert
    },
    name: 'HelloWorld',
    data () {
      return {
        recent: true,
        types: [
          {
            value: '全部',
            label: '全部'
          },
          {
            value: '演唱会',
            label: '演唱会'
          },
          {
            value: '话剧',
            label: '话剧'
          },
          {
            value: '体育赛事',
            label: '体育赛事'
          }
        ],
        type: '全部',
        concerts: [
          {
            pic: require('../../assets/poster1.jpg'),
            title: '郭顶 落地之约',
            place: '[南京] 欧拉艺术空间',
            time: '2017-11-12 21:00',
            price: '¥180 - ¥200',
            concert_id: 1
          },
          {
            pic: require('../../assets/poster2.jpg'),
            title: '徐佳莹 日全蚀',
            place: '[北京] 北京展览馆剧场',
            time: '2017-12-12 19:30',
            price: '¥180 - ¥980',
            concert_id: 2
          },
          {
            pic: require('../../assets/poster3.jpg'),
            title: '田馥甄 如果If',
            place: '[广州] 广州体育馆',
            time: '2017-8-22 19:30',
            price: '¥380 - 1290',
            concert_id: 3
          },
          {
            pic: require('../../assets/poster1.jpg'),
            title: '郭顶 落地之约',
            place: '[南京] 欧拉艺术空间',
            time: '2017-11-12 21:00',
            price: '¥180 - ¥200',
            concert_id: 4
          },
          {
            pic: require('../../assets/poster2.jpg'),
            title: '徐佳莹 日全蚀',
            place: '[北京] 北京展览馆剧场',
            time: '2017-12-12 19:30',
            price: '¥180 - ¥980',
            concert_id: 5
          },
          {
            pic: require('../../assets/poster3.jpg'),
            title: '田馥甄 如果If',
            place: '[广州] 广州体育馆',
            time: '2017-8-22 19:30',
            price: '¥380 - 1290',
            concert_id: 6
          }
        ],
        pickerOptions2: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              picker.$emit('pick', [start, end])
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              picker.$emit('pick', [start, end])
            }
          }]
        },
        time: ''
      }
    },
    methods: {
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ConcertsList.css"></style>
