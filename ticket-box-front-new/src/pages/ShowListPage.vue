<template>
  <div class="show-list-wrapper">
    <el-row :gutter="20" style="margin: 0;">
      <el-col :xs="6" :sm="6" :md="6" :lg="6" style="padding: 0;">
        <show-list-nav></show-list-nav>
      </el-col>
      <el-col :xs="6" :sm="6" :md="14" :lg="14" style="padding: 0;">
        <show-list></show-list>
      </el-col>
      <el-col :xs="6" :sm="6" :md="4" :lg="4" style="padding: 0;">
        <!--<show-list-nav></show-list-nav>-->
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import ShowListNav from '../components/ShowListNav/ShowListNav'
  import ShowList from '../components/ShowList/ShowList'

  export default {
    name: "ShowListPage",
    components: {
      ShowListNav,
      ShowList
    }
  }
</script>

<style scoped>
  .show-list-wrapper {
    width: 100%;
    padding: 0 120px;
    min-height: 800px;
    margin-top: 70px;
  }
</style>
