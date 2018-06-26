<template>
  <div class="card-tabs-wrapper">
    <el-tabs type="border-card">
      <el-tab-pane label="特别推荐">
        <el-row :gutter="20" style="margin: 0">
          <el-col :xs="4" :sm="4" :md="4" :lg="4" v-for="(concert, index) in concerts" :key="index">
            <single-brief-concert :info="concert"></single-brief-concert>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="即将开演">

      </el-tab-pane>
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
        concerts: [{
          schedule: '2018张韶涵“旅程”世界巡回演唱会-成都',
          venueName: 'sads',
          time: 'dsaa',
          minPrice: 'asd',
          poster: require('../../assets/poster1.jpg')
        },
          {
            schedule: '2018张韶涵“旅程”世界巡回演唱会-成都',
            venueName: 'sads',
            time: 'dsaa',
            minPrice: 'asd',
            poster: require('../../assets/poster1.jpg')
          },
          {
            schedule: '2018张韶涵“旅程”世界巡回演唱会-成都',
            venueName: 'sads',
            time: 'dsaa',
            minPrice: 'asd',
            poster: require('../../assets/poster1.jpg')
          },
          {
            schedule: '2018张韶涵“旅程”世界巡回演唱会-成都',
            venueName: 'sads',
            time: 'dsaa',
            minPrice: 'asd',
            poster: require('../../assets/poster1.jpg')
          },
          {
            schedule: '2018张韶涵“旅程”世界巡回演唱会-成都',
            venueName: 'sads',
            time: 'dsaa',
            minPrice: 'asd',
            poster: require('../../assets/poster1.jpg')
          },
          {
            schedule: '2018张韶涵“旅程”世界巡回演唱会-成都',
            venueName: 'sads',
            time: 'dsaa',
            minPrice: 'asd',
            poster: require('../../assets/poster1.jpg')
          }],
        intros: [{
          img: require('../../assets/concert.png'),
          title: '演唱会',
          intro: '感受现场音乐的魅力'
        }, {
          img: require('../../assets/melodrama.png'),
          title: '音乐剧',
          intro: '乐享文艺生活的感染'
        }, {
          img: require('../../assets/drama.png'),
          title: '话剧',
          intro: '参与不可复制的艺术'
        }]
      }
    },
    methods: {
      ...mapActions({})
    },
    mounted() {
    }
  }
</script>

<style scoped src="./CardTabs.css">

</style>
