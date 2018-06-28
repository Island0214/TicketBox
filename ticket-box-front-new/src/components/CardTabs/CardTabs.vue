<template>
  <div class="card-tabs-wrapper">
    <el-tabs type="border-card">
      <el-tab-pane label="特别推荐">
        <!--<el-row :gutter="20" style="margin: 0">-->
          <!--<el-col :xs="4" :sm="4" :md="4" :lg="4" v-for="(concert, index) in hots" :key="index">-->
            <!--<single-brief-concert :info="concert"></single-brief-concert>-->
          <!--</el-col>-->
        <!--</el-row>-->

        <el-carousel indicator-position="none" arrow="never" :autoplay="false" ref="carousel">
          <el-carousel-item>
            <el-row :gutter="20" style="margin: 0">
              <el-col :xs="4" :sm="4" :md="4" :lg="4" v-for="(concert, index) in hots" :key="index" v-if="index >= 6">
                <single-brief-concert :info="concert"></single-brief-concert>
              </el-col>
            </el-row>
          </el-carousel-item>
          <el-carousel-item>
            <el-row :gutter="20" style="margin: 0">
              <el-col :xs="4" :sm="4" :md="4" :lg="4" v-for="(concert, index) in hots" :key="6 + index" v-if="index < 6">
                <single-brief-concert :info="concert"></single-brief-concert>
              </el-col>
            </el-row>
          </el-carousel-item>
        </el-carousel>
      </el-tab-pane>
      <!--<el-tab-pane label="即将开演">-->
        <!--<el-row :gutter="20" style="margin: 0">-->
          <!--<el-col :xs="4" :sm="4" :md="4" :lg="4" v-for="(concert, index) in comings" :key="index">-->
            <!--<single-brief-concert :info="concert"></single-brief-concert>-->
          <!--</el-col>-->
        <!--</el-row>-->
      <!--</el-tab-pane>-->

      <div class="select-wrapper">
        <ul>
          <li v-for="item in 2" :key="item">
            <button :class="['el-carousel__button', {'is-active': curIndex === item}]"
                    @mouseover="selectPage(item)"></button>
          </li>
        </ul>
      </div>
    </el-tabs>

    <div class="intros-wrapper">
      <el-row :gutter="20" style="margin: 0">
        <el-col :xs="8" :sm="8" :md="8" :lg="8" v-for="(intro, index) in intros" :key="index" style="padding: 0">
          <div class="intro-wrapper">
            <img :src="intro.img"/>
            <h1>{{ intro.title.split('').join('   ') }}</h1>
            <p>{{ intro.intro }}</p>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  import SingleBriefConcert from '../SingleBriefConcert/SingleBriefConcert'

  /* eslint-disable */
  export default {
    name: "CardTabs",
    components: {
      SingleBriefConcert
    },
    data() {
      return {
        hots: [],
        comings: [],
        intros: [{
          img: require('../../assets/concert.png'),
          title: '演唱会',
          intro: '感受现场音乐的魅力'
        }, {
          img: require('../../assets/drama.png'),
          title: '戏剧',
          intro: '参与不可复制的艺术'
        }, {
          img: require('../../assets/melodrama.png'),
          title: '音乐剧',
          intro: '乐享文艺生活的感染'
        }],
        curIndex: 1
      }
    },
    methods: {
      ...mapActions({
        getHotSchedules: 'getHotSchedules',
        getComingSchedules: 'getComingSchedules'
      }),
      selectPage: function (item) {
        this.curIndex = item
        this.$refs.carousel.setActiveItem(item - 1)
      }
    },
    mounted() {
      this.getHotSchedules({
        onSuccess: (data) => {
          console.log(data)
          this.hots = data
        }
      })
    }
  }
</script>

<style scoped src="./CardTabs.css">

</style>
