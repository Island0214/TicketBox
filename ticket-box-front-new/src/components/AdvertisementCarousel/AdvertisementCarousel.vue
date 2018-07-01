<template>
  <div class="advertisement-carousel-wrapper">
    <el-carousel height="290px" indicator-position="none" arrow="never" @change="changePic" ref="carousel">
      <el-carousel-item v-for="(item, index) in ads" :key="index">
        <!--<h3>{{ item }}</h3>-->
        <div class="img-wrapper">
          <img :src="item.poster" @click="openShow(item.schedule_id)"/>
        </div>
        <div class="info-wrapper" @click="openShow(item.schedule_id)">
          <p>{{ item.title }}</p>

        </div>
        <!--<div class="invisible-wrapper" @click="openShow(item.schedule_id)">-->

        <!--</div>-->
      </el-carousel-item>
    </el-carousel>

    <div class="select-wrapper">
      <ul>
        <li v-for="item in 5" :key="item">
          <button :class="['el-carousel__button', {'is-active': curIndex === item}]"
                  @mouseover="selectPic(item)"></button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    name: "AdvertisementCarousel",
    data() {
      return {
        curIndex: 1,
        ads: []
      }
    },
    methods: {
      ...mapActions({
        getAds: 'getAds',
        getScheduleBasicInfoAction: 'getScheduleBasicInfo'
      }),
      changePic: function (index1, index2) {
        // console.log(index1)
        // this.showInfo = false
        // if (index1 === ) {

        // }
        if (index1 === 0) {
          index1 = 5
        }
        this.curIndex = index1
        // console.log(index1)
      },
      selectPic: function (item) {
        this.curIndex = item
        this.$refs.carousel.setActiveItem(item)
        // console.log(item)
      },
      openShow: function (id) {
        window.open('/#/show/' + id, '_blank')
      }
    },
    mounted() {
      this.getAds({
        onSuccess: (data) => {
          console.log(data)
          this.ads = data
        },
        onError: () => {

        }
      })
    }
  }
</script>

<style scoped src="./AdvertisementCarousel.css">

</style>
