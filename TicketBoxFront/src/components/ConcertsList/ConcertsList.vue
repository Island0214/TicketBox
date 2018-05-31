<template>
  <div class="concerts-wrapper">
    <div class="params-wrapper">
      <el-input v-model="searchInput"></el-input>
      <i class="el-icon-search" @click="trySearch"></i>
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

    <div class="text-wrapper" v-if="showNoConcertsNote">
      <h4>没有相关演出...<br>请修改筛选条件后再次尝试！</h4>
    </div>


    <el-row :gutter="20" style="margin: 0">
      <single-brief-concert v-for="(concert, index) in concerts" :key="index" :info="concert" :size="6" style="margin-top: 40px;"></single-brief-concert>
    </el-row>

    <div class="pagination-wrapper" v-if="!showNoConcertsNote">
      <el-pagination
        background
        layout="prev, pager, next"
        :page-count="totalPage"
        :current-page.sync="curPage"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import SingleBriefConcert from '../../components/SingleBriefConcert/SingleBriefConcert.vue'
  import {mapActions} from 'vuex'
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
        searchInput: '',
        type: '全部',
        concerts: [],
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
        time: [],
        totalPage: 1,
        curPage: 1,
        searchBody: {
          name: '',
          type: '',
          start: '',
          end: '',
          page: 0
        },
        showNoConcertsNote: false
      }
    },
    watch: {
      curPage: function () {
        this.searchBody.page = this.curPage - 1
        this.findScheduleByPage(this.curPage - 1)
      },
      time: function () {
//        console.log(this.time)
        this.searchBody.start = this.time[0]
        this.searchBody.end = this.time[1]
      },
      type: function () {
        if (this.type === '全部') {
          this.searchBody.type = ''
        } else {
          this.searchBody.type = this.type
        }
      },
      searchBody: {
        handler: function () {
          console.log(this.searchBody)
          this.findScheduleByPage()
        },
        deep: true
      }
    },
    methods: {
      ...mapActions({
        findScheduleByPageAction: 'findScheduleByPage'
      }),
      findScheduleByPage: function () {
        let loadingInstance = this.$loading({ fullscreen: true });

        this.findScheduleByPageAction({
          onSuccess: (data) => {
//            console.log(data)
            this.concerts = data.content
            this.totalPage = data.totalPages
            if (this.concerts.length === 0) {
              this.showNoConcertsNote = true
            } else {
              this.showNoConcertsNote = false
            }
            loadingInstance.close()
          },
          onError: () => {

          },
          body: this.searchBody
        })
      },
      trySearch: function () {
        this.searchBody.name = this.searchInput
      }
    },
    mounted () {
      this.findScheduleByPage(0)
      let now = new Date()
      this.time.push(now)
      let monthLayer = new Date()
      monthLayer.setMonth(monthLayer.getMonth()+1)
      this.time.push(monthLayer)
      console.log(this.time)
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ConcertsList.css"></style>
