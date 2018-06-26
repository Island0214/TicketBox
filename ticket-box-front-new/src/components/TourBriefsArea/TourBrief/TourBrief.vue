<template>
  <div class="tour-brief-wrapper" :style="[{'width': width + 'px'}, {'height': height + 'px'}, {'left': left + 'px'}, {'top': top + 'px'}]">
    <img src="../../../assets/poster1.jpg">
  </div>

</template>

<script>
  export default {
    props: ['width', 'height', 'left', 'top'],
    name: "TourBrief"
  }
</script>

<style scoped src="./TourBrief.css">

</style>
