<template>
  <div class="concert-info-wrapper">
    <div :class="[scrollImage ? 'scroll-image' : 'concert-image-wrapper', {'fix-scroll-image': fixScroll}]" :style="{'margin-left': marginLeft + 'px', 'margin-top': marginTop + 'px'}" id="poster-wrapper">
      <img :src="basicData.poster"/>
    </div>

    <div class="info-wrapper" v-if="basicData.time" id="concert-info-wrapper">
      <h1>{{ basicData.schedule }}</h1>

      <p>演出者：<span>{{ basicData.artist }}</span></p>
      <p>演出时间：<span>{{ new Date(basicData.time).toLocaleString() }}</span></p>
      <p>演出地点：<span>{{ location }}</span></p>

      <seat-selection :showButton="true" :schedule="$route.params.id"></seat-selection>

    </div>

    <div class="intro-wrapper">
      <part-title title="项目简介"></part-title>
      <pre>{{ basicData.intro }}</pre>
    </div>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import SeatSelection from '../SeatSelection/SeatSelection.vue'
  import {mapActions} from 'vuex'

  export default {
    components: {
      PartTitle,
      SeatSelection
    },
    data () {
      return {
        location: '',
        ticket_num: 1,
        basicData: {},
        scrollImage: false,
        fixScroll: false,
        marginLeft: 30,
        marginTop: 0
      }
    },
    methods: {
      ...mapActions({
        getScheduleBasicInfo: 'getScheduleBasicInfo',
        getSchedulePriceInfo: 'getSchedulePriceInfo'
      }),
      handleScroll: function () {
        let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
//        console.log(scrollTop)

//        console.log(document.getElementsByClassName('info-wrapper').item(0).offsetTop + document.getElementsByClassName('info-wrapper').item(0).clientHeight)
//        console.log(document.getElementById('concert-info-wrapper').clientHeight + 150 - scrollTop)
        if (scrollTop > 150 && document.getElementsByClassName('info-wrapper').item(0).clientHeight > 400) {
//          alert('scroll')
//          console.log(document.documentElement.scrollLeft)
          this.scrollImage = true
          this.marginLeft = 30 - document.documentElement.scrollLeft

          if (document.getElementById('concert-info-wrapper').clientHeight + 150 - scrollTop < 400) {
            this.fixScroll = true
            this.marginLeft = 30
            this.marginTop = document.getElementById('concert-info-wrapper').clientHeight - 400
          } else {
            this.fixScroll = false
            this.marginTop = 0
          }
        } else {
          this.scrollImage = false
          this.marginLeft = 30
          this.marginTop = 0
        }
      }
    },
    computed:{
    },
    watch: {
    },
    mounted () {
      document.documentElement.scrollTop = document.body.scrollTop = 0;
      window.addEventListener('scroll', this.handleScroll)

//      console.log(this.$route.params.id)
      let loadingInstance = this.$loading({ fullscreen: true });

      this.getScheduleBasicInfo({
        onSuccess: (data) => {
          console.log(data)
          this.basicData = data

          this.getSchedulePriceInfo({
            onSuccess: (data) => {
              this.location = data.venueName
              loadingInstance.close()
            },
            onError: () => {

            },
            schedule: this.$route.params.id
          })
        },
        onError: () => {

        },
        schedule: this.$route.params.id
      })

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ConcertInfo.css"></style>
