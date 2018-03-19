<template>
  <div class="page-wrapper">
    <!--<concert-info></concert-info>-->
    <schedule-dispatch></schedule-dispatch>
  </div>
</template>

<script>
  import ScheduleDispatch from '../components/ScheduleDispatch/ScheduleDispatch.vue'

  export default {
    components: {
      ScheduleDispatch
    },
    data () {
      return {
      }
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 60px;
  }
</style>
