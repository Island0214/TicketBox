<template>
  <div
    class="tour-brief-wrapper"
    :style="[{'width': width + 'px'}, {'height': height + 'px'}, {'left': left + 'px'}, {'top': top + 'px'}]"
    @mouseenter="setInfoStatus(true)"
    @mouseout="setInfoStatus(false)"
  >
    <img :src="tour.poster">
    <div>
      <transition name="el-fade-in">
        <div v-show="showInfo">
          <div
            class="intro-wrapper"
          >
            <div class="text-wrapper">
              <h1>{{ tour.artist }}</h1>
              <p>asdfdghggdasfdghjgdfsdafghfsdghvcxzdafsgdfhj</p>
            </div>

          </div>
          <!--<div class="transition-box">el-collapse-transition</div>-->
          <!--<div class="transition-box">el-collapse-transition</div>-->
        </div>
      </transition>
    </div>
    <div class="invisible-wrapper">

    </div>
  </div>

</template>

<script>
  export default {
    props: ['width', 'height', 'left', 'top', 'tour'],
    name: "TourBrief",
    data () {
      return {
        showInfo: false
      }
    },
    methods: {
      setInfoStatus (status) {
        this.showInfo = status
      }
    }
  }
</script>

<style scoped src="./TourBrief.css">

</style>
