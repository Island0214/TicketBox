<template>
  <div class="show-list-content-wrapper">
    <div class="card-tabs-wrapper">
      <el-tabs type="border-card">
        <el-tab-pane label="最近开场">
          <div class="search-content-wrapper">
            <h4>“ <span>{{ searchContent }}</span> ”的搜索结果：</h4>
          </div>
        </el-tab-pane>
        <el-tab-pane label="最新上架">

        </el-tab-pane>
      </el-tabs>

    </div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: "ShowList",
    computed: {
      ...mapGetters({
        searchContent: 'searchContent'
      })
    }
  }
</script>

<style scoped src="./ShowList.css">

</style>
