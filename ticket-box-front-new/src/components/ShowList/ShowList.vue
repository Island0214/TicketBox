<template>
  <div class="show-list-content-wrapper">
    <div class="card-tabs-wrapper">
      <el-tabs type="border-card" @tab-click="changeTab">
        <el-tab-pane :label="tab" v-for="(tab, index) in tabs" :key="tab">
          <el-collapse-transition>
            <div v-show="showLoading">
              <div class="transition-box"><i class="el-icon-loading"></i>演出加载中...</div>
              <!--<div class="transition-box">el-collapse-transition</div>-->
            </div>
          </el-collapse-transition>
          <div class="search-content-wrapper" v-if="searchContent !== ''">
            <h4>“ <span>{{ searchContent }}</span> ”的搜索结果：</h4>
          </div>
          <div class="shows-wrapper">
            <brief-show v-for="(show, index) in shows" :show="show" :key="index"></brief-show>
          </div>

          <div v-if="totalPage === 0 && !showLoading">
            <div class="transition-box" style="padding-bottom: 100px; padding-top: 70px;"><i
              class="el-icon-warning"></i>暂无符合当前条件的演出...更换条件再试试吧！
            </div>
          </div>
          <div class="pagination-wrapper" v-if="totalPage !== 0">
            <el-pagination
              background
              layout="prev, pager, next"
              :page-count="totalPage"
              :current-page="curPage"
              @current-change="changePage"
            >
            </el-pagination>
          </div>
          <div class="ad-wrapper" v-if="shows.length < 6">
            <h3>为您推荐更多其他演出</h3>
            <el-carousel indicator-position="none" arrow="never" :autoplay="false" ref="carousel">
              <el-carousel-item>
                <el-row :gutter="20" style="margin: 0">
                  <el-col :xs="8" :sm="8" :md="8" :lg="8" v-for="(concert, index) in hots" :key="index"
                          v-if="index >= 6">
                    <single-brief-concert :info="concert"></single-brief-concert>
                  </el-col>
                </el-row>
              </el-carousel-item>
              <el-carousel-item>
                <el-row :gutter="20" style="margin: 0">
                  <el-col :xs="8" :sm="8" :md="8" :lg="8" v-for="(concert, index) in hots" :key="6 + index"
                          v-if="index < 6">
                    <single-brief-concert :info="concert"></single-brief-concert>
                  </el-col>
                </el-row>
              </el-carousel-item>
            </el-carousel>

            <div class="select-wrapper">
              <ul>
                <li v-for="item in 2" :key="item">
                  <button :class="['el-carousel__button', {'is-active': curIndex === item}]"
                          @mouseover="selectPage(item)"></button>
                </li>
              </ul>
            </div>
          </div>
        </el-tab-pane>
        <!--<el-tab-pane label="最新上架">-->

        <!--</el-tab-pane>-->
      </el-tabs>

    </div>
  </div>
</template>

<script>
  import {mapGetters, mapMutations, mapActions} from 'vuex'
  import BriefShow from './BriefShow/BriefShow'
  import SingleBriefConcert from '../SingleBriefConcert/SingleBriefConcert'

  export default {
    name: "ShowList",
    components: {
      BriefShow,
      SingleBriefConcert
    },
    data() {
      return {
        shows: [],
        showLoading: false,
        curPage: 1,
        totalPage: 0,
        hots: [],
        curIndex: 1,
        tabs: ['热门排序', '最近开场', '最新上架']
      }
    },
    computed: {
      ...mapGetters({
        searchContent: 'searchContent',
        city: 'city',
        type: 'type',
        startTime: 'startTime',
        endTime: 'endTime',
        page: 'page'
      })
    },
    watch: {
      searchContent: function () {
        this.search()
      },
      city: function () {
        this.search()
      },
      type: function () {
        this.search()
      },
      startTime: function () {
        this.search()
      },
      endTime: function () {
        this.search()
      },
      page: function () {
        this.search()
      }
    },
    methods: {
      ...mapMutations({
        setPage: 'setPage',
        setSort: 'setSort'
      }),
      ...mapActions({
        getHotSchedules: 'getHotSchedules',
        searchScheduleByPage: 'searchScheduleByPage'
      }),
      selectPage: function (item) {
        this.curIndex = item
        this.$refs.carousel.setActiveItem(item - 1)
      },
      changeTab: function (item) {
        // console.log(item.label)
        switch (item.label) {
          case '热门排序':
            this.setSort('hottest')
            break;
          case '最近开场':
            this.setSort('latest')
            break;
          case '最新上架':
            this.setSort('newest')
            break;
        }
        this.search()
      },
      search: function () {
        document.documentElement.scrollTop = document.body.scrollTop = 0
        this.totalPage = 0
        this.showLoading = true
        this.searchScheduleByPage({
          onSuccess: (data) => {
            console.log(data)
            this.totalPage = data.totalPages
            this.shows = data.content
            this.showLoading = false
          },
          onError: () => {
          }
        })
      },
      changePage: function (page) {
        this.curPage = page
        this.setPage(page)
        this.search()
      }
    },
    mounted() {
      this.getHotSchedules({
        onSuccess: (data) => {
          // console.log(data)
          this.hots = data
        }
      })
      this.search()
    }
  }
</script>

<style scoped src="./ShowList.css">

</style>
