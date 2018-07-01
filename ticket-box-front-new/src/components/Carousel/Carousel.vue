<template>
  <div class="carousel-area-wrapper" v-if="this.ads.length === 5">
    <el-row :gutter="20" style="margin: 0">
      <el-col :xs="10" :sm="10" :md="10" :lg="10" style="min-height: 100px;">
        <transition name="el-fade-in-linear">
          <div class="welcome-wrapper" v-show="showInfo">
            <h1>用票盒<span style="color: #906DFF;">即刻购票</span></h1>
            <h1>{{ ads[curIndex].title }}</h1>
            <!--<h1><span style="color: #FEC581;">{{ schedules[curIndex].artist  }}</span>与您相约</h1>-->
            <h1><span style="color: #FEC581;">TICKET BOX</span>与您相约</h1>
            <el-button class="el-button" @click="openShow(ads[curIndex].schedule_id)">立即购票</el-button>&nbsp;&nbsp;&nbsp;或&nbsp;&nbsp;
            <a @click="toAllShows()">查看更多</a>
          </div>
        </transition>
      </el-col>
      <el-col :xs="14" :sm="14" :md="14" :lg="14">
        <!--<p>as</p>-->
        <!--<div class="pic-wrapper"></div>-->
        <el-carousel type="card" height="500px" style="width: 700px; margin-left: 10px;" @change="changePic"
                     arrow="never"
                     ref="carousel">
          <el-carousel-item v-for="(item, index) in 5" :key="item" style="border-radius: 10px;">
            <!--<h3>{{ item }}</h3>-->
            <img :src="ads[index].poster" @click="openShow(ads[index].schedule_id)">
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>
    <div class="select-wrapper">
      <!--<button class="back-button" style="left: 80px;" @click="prev"><i class="el-icon-back"></i></button>-->
      <ul>
        <li v-for="item in 5" :key="item">
          <button :class="['el-carousel__button', {'is-active': curIndex + 1 === item}]"
                  @mouseover="selectPic(item)"></button>
        </li>
      </ul>
      <!--<button class="back-button" style="right: 80px;" @click="next"><i class="el-icon-back" style="transform: rotateY(180deg);"></i></button>-->
    </div>
  </div>
</template>

<script>
  import { mapMutations, mapActions } from 'vuex'

  /* eslint-disable */
  export default {
    name: "Carousel",
    data() {
      return {
        curIndex: 0,
        showInfo: true,
        schedules: [],
        ads: []
      }
    },
    methods: {
      ...mapMutations({
        setSearchParams: 'setSearchParams'
      }),
      ...mapActions({
        getAds: 'getAds',
        getScheduleBasicInfoAction: 'getScheduleBasicInfo'
      }),
      changePic: function (index1, index2) {
        // console.log(index1)
        this.showInfo = false
        this.curIndex = index1
        setTimeout(() => {
          this.showInfo = true
        }, 400)
      },
      selectPic: function (item) {
        this.curIndex = item - 1
        this.$refs.carousel.setActiveItem(item - 1)
        // console.log(item)
      },
      prev: function () {
        this.$refs.carousel.prev()
      },
      next: function () {
        this.$refs.carousel.next()
      },
      toAllShows: function () {
        this.setSearchParams({
          searchContent: '',
          city: '',
          type: '',
          startTime: '',
          endTime: ''
        })
        window.open('/#/shows', '_blank')
        // this.$router.push('/shows', '')
      },
      openShow: function (id) {
        window.open('/#/show/' + id, '_blank')
      }
    },
    mounted() {
      this.getAds({
        onSuccess: (data) => {
          // console.log(data)
          this.ads = data
        },
        onError: () => {

        }
      })

      // console.log(this.$refs.carousel)
    }
  }
</script>

<style scoped src="./Carousel.css">

</style>
