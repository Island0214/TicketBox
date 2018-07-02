<template>
  <div class="advertisement-recommendation-wrapper">
    <h5>猜您喜欢</h5>
    <el-carousel indicator-position="none" arrow="never" :autoplay="false" ref="carouselAD">
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
</template>

<script>
  import {mapActions} from 'vuex'
  import SingleBriefConcert from '../SingleBriefConcert/SingleBriefConcert'

  export default {
    name: "AdvertisementRecommendation",
    components: {
      SingleBriefConcert
    },
    data() {
      return {
        hots: [],
        curIndex: 1,
      }
    },
    methods: {
      ...mapActions({
        getHotSchedules: 'getHotSchedules'
      }),
      selectPage: function (item) {
        this.curIndex = item
        this.$refs.carouselAD.setActiveItem(item - 1)
      }
    },
    mounted () {
      this.getHotSchedules({
        onSuccess: (data) => {
          // console.log(data)
          this.hots = data
        }
      })
    }
  }
</script>

<style scoped src="./AdvertisementRecommendation.css">

</style>
