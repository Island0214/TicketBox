<template>
  <div class="brief-concerts-wrapper">
    <div class="tabs-wrapper">
      <a :class="{'select-tab' : recent}" @click="showRecents">最 近 演 出</a>
      <!--<a>最新演出</a>-->
      <a :class="{'select-tab' : !recent}" @click="showHots">最 新 演 出</a>
    </div>

    <el-row :gutter="20" style="margin: 0" slot="concerts-wrapper">
      <single-brief-concert v-for="(concert, index) in concerts" :key="index" :info="concert" :size="8"></single-brief-concert>
    </el-row>
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
      concerts: []
    }
  },
  methods: {
    ...mapActions({
      getNewestThreeSchedules: 'getNewestThreeSchedules',
      getClosestThreeSchedules: 'getClosestThreeSchedules'
    }),
    showRecents: function () {
      this.recent = true
      this.getClosestThreeSchedules({
        onSuccess: (data) => {
          this.concerts = data
        },
        onError: () => {

        }
      })
    },
    showHots: function () {
      this.recent = false
      this.getNewestThreeSchedules({
        onSuccess: (data) => {
          this.concerts = data
        },
        onError: () => {

        }
      })
    }
  },
  mounted () {
    this.getClosestThreeSchedules({
      onSuccess: (data) => {
        this.concerts = data
      },
      onError: () => {

      }
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./BriefConcerts.css"></style>
