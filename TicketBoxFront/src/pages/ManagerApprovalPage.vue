<template>
  <div class="page-wrapper">
    <!--<concert-info></concert-info>-->
    <div class="tabs-wrapper">
      <a :class="{'select-tab' : !modify}" @click="modify = false">注 册 审 批</a>
      <!--<a>最新演出</a>-->
      <a :class="{'select-tab' : modify}" @click="modify = true" style="margin-left: 30px;">修 改 审 批</a>
    </div>

    <manager-approve-sign-up v-if="!modify"></manager-approve-sign-up>
    <manager-approve-modify v-if="modify"></manager-approve-modify>



  </div>
</template>

<script>
  import ManagerApproveSignUp from '../components/ManagerApproveSignUp/ManagerApproveSignUp.vue'
  import ManagerApproveModify from '../components/ManagerApproveModify/ManagerApproveModify.vue'

  export default {
    components: {
      ManagerApproveSignUp,
      ManagerApproveModify
    },
    data () {
      return {
        modify: false
      }
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 60px;
  }
</style>
