<template>
  <div>
  <div class="show-info-wrapper">
    <!--<h1>{{ basicData.schedule }}</h1>-->
    <div class="breadcrumb-wrapper">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/shows' }">演出</el-breadcrumb-item>
        <el-breadcrumb-item>演出详情</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="body-wrapper">
      <div class="img-wrapper">
        <img :src="basicData.poster">
      </div>
      <div class="info-wrapper">
        <h1>{{ basicData.schedule }}</h1>
        <el-steps :active="activeStep" finish-status="success" simple style="margin-top: 20px">
          <el-step title="项目预售"></el-step>
          <el-step title="售票中"></el-step>
          <el-step title="演出结束"></el-step>
        </el-steps>

        <p><i class="el-icon-service"></i>艺人：<span>{{ basicData.artist }}</span></p>
        <p><i class="el-icon-news"></i>类型：<span>{{ basicData.type }}</span></p>
        <p><i class="el-icon-location-outline"></i>地点：<span>【{{ basicData.city }}】{{ venueName }}</span></p>
        <p><i class="el-icon-date"></i>时间：
          <!--<el-button class="time-button">{{ new Date(basicData.time).toLocaleString() }}</el-button>-->
          <span>{{ new Date(basicData.time).toLocaleString() }}</span>
        </p>
        <p><i><img src="../../assets/price.png"></i>价格：
          <!--<el-button class="time-button">{{ new Date(basicData.time).toLocaleString() }}</el-button>-->
          <!--<span>{{ new Date(basicData.time).toLocaleString() }}</span>-->
          <button v-for="(price, index) in prices" :key="price"
                  :class="['price-button', {'is-active':index === curPrice}]" @click="setPrice(index)">{{ price }}
          </button>
        </p>

        <el-button v-if="activeStep === 0">立即预定</el-button>
        <el-button v-if="activeStep === 1">选座购买</el-button>
        <!--<el-button v-if="activeStep === 2">选座购买</el-button>-->
        <div v-if="activeStep === 2" class="warning-wrapper">
          <i class="el-icon-warning"></i>
          <h4>该演出已下架... <a @click="$router.push('/show')">点击看看其他演出吧！</a></h4>
        </div>
        <!--{{ basicData.intro  }}-->
      </div>
    </div>
  </div>

  <div class="intro-wrapper">
    <div class="breadcrumb-wrapper">
      <el-breadcrumb separator="/">
        <!--<el-breadcrumb-item :to="{ path: '/shows' }">演出</el-breadcrumb-item>-->
        <el-breadcrumb-item>演出说明</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <pre>{{ basicData.intro }}</pre>

    </div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    name: "ShowInfo",
    data() {
      return {
        basicData: {},
        prices: [80, 180, 280, 380],
        curPrice: 0,
        activeStep: 1,
        venueName: ''
      }
    },
    methods: {
      ...mapActions({
        getScheduleBasicInfo: 'getScheduleBasicInfo',
        getSchedulePriceInfo: 'getSchedulePriceInfo',
        getScheduleInfo: 'getScheduleInfo'
      }),
      setPrice: function (index) {
        this.curPrice = index
      }
    },
    mounted() {
      document.documentElement.scrollTop = document.body.scrollTop = 0;
      window.addEventListener('scroll', this.handleScroll)

      let that = this
      this.getScheduleInfo({
        onSuccess: (data) => {
          // that.prices = eval('(' + data.prices + ')')
        },
        onError: () => {

        },
        schedule: that.$route.params.id
      })

      this.getScheduleBasicInfo({
        onSuccess: (data) => {
          console.log(data)
          that.basicData = data

          this.getSchedulePriceInfo({
            onSuccess: (data) => {
              console.log(data)
              that.venueName = data.venueName
            },
            onError: () => {

            },
            schedule: that.$route.params.id
          })
        },
        onError: () => {

        },
        schedule: that.$route.params.id
      })
    }
  }
</script>

<style scoped src="./ShowInfo.css">

</style>
