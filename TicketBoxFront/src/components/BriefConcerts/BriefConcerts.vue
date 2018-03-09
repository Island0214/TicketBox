<template>
  <div class="brief-concerts-wrapper">
    <div class="tabs-wrapper">
      <a :class="{'select-tab' : recent}" @click="showRecents">最 近 演 出</a>
      <!--<a>最新演出</a>-->
      <a :class="{'select-tab' : !recent}" @click="showHots">热 门 演 出</a>
    </div>

    <el-row :gutter="20" style="margin: 0" slot="concerts-wrapper">
      <single-brief-concert v-for="(concert, index) in concerts" :key="index" :info="concert"></single-brief-concert>
    </el-row>
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
      concerts: [
        {
          pic: require('../../assets/poster1.jpg'),
          title: '郭顶 落地之约',
          place: '[南京] 欧拉艺术空间',
          time: '2017-11-12 21:00',
          price: '¥180 - ¥200'
        },
        {
          pic: require('../../assets/poster2.jpg'),
          title: '徐佳莹 日全蚀',
          place: '[北京] 北京展览馆剧场',
          time: '2017-12-12 19:30',
          price: '¥180 - ¥980'
        },
        {
          pic: require('../../assets/poster3.jpg'),
          title: '田馥甄 如果If',
          place: '[广州] 广州体育馆',
          time: '2017-8-22 19:30',
          price: '¥380 - 1290'
        }
      ]
    }
  },
  methods: {
    showRecents: function () {
      this.recent = true
    },
    showHots: function () {
      this.recent = false
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./BriefConcerts.css"></style>
