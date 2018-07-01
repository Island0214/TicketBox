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
      <div class="body-wrapper" v-if="basicData.time">
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

          <el-button v-if="activeStep === 0" @click="reserveTicket = true">立即预定</el-button>
          <el-tooltip class="item" effect="light" content="距演出开场一个月以上只能先进行预购，开票后优先分配座位！" placement="right" v-if="activeStep === 0">
            <i class="el-icon-question" style="font-size: 20px; color: #999; position: absolute; margin-top: 9px; margin-left: 9px;"></i>
          </el-tooltip>
          <el-button v-if="activeStep === 1">选座购买</el-button>
          <el-tooltip class="item" effect="light" content="距演出开场一个月内可进行选座购票！" placement="right" v-if="activeStep === 1">
            <i class="el-icon-question" style="font-size: 20px; color: #999; position: absolute; margin-top: 9px; margin-left: 9px;"></i>
          </el-tooltip>
          <!--<el-button v-if="activeStep === 2">选座购买</el-button>-->
          <div v-if="activeStep === 2" class="warning-wrapper">
            <i class="el-icon-warning"></i>
            <h4>该演出已下架... <a @click="$router.push('/show')">点击看看其他演出吧！</a></h4>
          </div>
          <!--{{ basicData.intro  }}-->
        </div>
      </div>

      <div class="tour-wrapper" v-if="basicData.tourId !== 0">
        <h3>该轮巡演更多场次</h3>
        <el-steps :active="curTour" finish-status="success">
          <el-step @click.native="openShow(tour.scheduleId)" status="success" :title="tour.city" :description="new Date(tour.time).toLocaleString().split(' ')[0]" icon="el-icon-time" v-for="(tour, index) in tours" :key="index" v-if="index <= finishTour"></el-step>
          <el-step @click.native="openShow(tour.scheduleId)" status="wait" :title="tour.city" :description="new Date(tour.time).toLocaleString().split(' ')[0]" icon="el-icon-time" v-for="(tour, index) in tours" :key="index" v-if="index > finishTour && index < curTour"></el-step>
          <el-step @click.native="openShow(tour.scheduleId)" status="process" :title="tour.city" :description="new Date(tour.time).toLocaleString().split(' ')[0]" icon="el-icon-time" v-for="(tour, index) in tours" :key="index" v-if="index === curTour && curTour > finishTour"></el-step>
          <el-step @click.native="openShow(tour.scheduleId)" status="wait" :title="tour.city" :description="new Date(tour.time).toLocaleString().split(' ')[0]" icon="el-icon-time" v-for="(tour, index) in tours" :key="index" v-if="index > curTour"></el-step>
        </el-steps>
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

    <ticket-reserve
      :reserveTicket="reserveTicket"
      :prices="prices"
      :time="basicData.time"
      :curPrice="curPrice"
      :schedule="basicData.schedule"
      @close="reserveTicket = false"
    ></ticket-reserve>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  import TicketReserve from '../TicketReserve/TicketReserve'

  export default {
    name: "ShowInfo",
    components: {
      TicketReserve
    },
    data() {
      return {
        basicData: {
          tourId: 0
        },
        prices: [280, 380, 680, 980, 1280],
        curPrice: 0,
        activeStep: 1,
        venueName: '',
        curTour: 0,
        finishTour: -1,
        tours: [],
        reserveTicket: false
      }
    },
    methods: {
      ...mapActions({
        getScheduleBasicInfo: 'getScheduleBasicInfo',
        getSchedulePriceInfo: 'getSchedulePriceInfo',
        getScheduleInfo: 'getScheduleInfo',
        getTourScheduleById: 'getTourScheduleById'
      }),
      setPrice: function (index) {
        this.curPrice = index
      },
      openShow: function (id) {
        window.open('/#/show/' + id, '_blank')
      }
    },
    mounted() {
      document.documentElement.scrollTop = document.body.scrollTop = 0;

      let that = this
      this.getScheduleBasicInfo({
        onSuccess: (data) => {
          that.basicData = data
          console.log(data)

          if (that.basicData.time - new Date().getTime() < 0) {
            this.activeStep = 2
          } else if (that.basicData.time - new Date() > 30 * 24 * 60 * 60 * 1000) {
            this.activeStep = 0
          } else {
            this.activeStep = 1
          }

          this.getSchedulePriceInfo({
            onSuccess: (data) => {
              that.venueName = data.venueName
            },
            onError: () => {

            },
            schedule: that.$route.params.id
          })

          // console.log(that.basicData.tourId)
          if (data.tourId !== 0) {
            this.getTourScheduleById({
              onSuccess: (data) => {
                console.log(data)
                this.tours = data
                for (let i = 0; i < data.length; i++) {
                  if (data[i].time < new Date().getTime()) {
                    this.finishTour = i
                  }
                  if (data[i].city === that.basicData.city) {
                    this.curTour = i
                  }
                }
                // that.venueName = data.venueName
              },
              onError: () => {

              },
              tour: data.tourId
            })
          }
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
