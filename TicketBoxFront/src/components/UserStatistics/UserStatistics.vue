<template>
  <div class="user-statistics-wrapper">
    <!--<p>s</p>-->
    <part-title title="统计信息"></part-title>

    <div class="info-wrapper">
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>总预定数：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ statistics.totalReserve }}</h5>
        </el-col>
      </el-row>
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>总退订数：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ statistics.totalCancel }}</h5>
        </el-col>
      </el-row>
    </div>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import {mapGetters} from 'vuex'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        statistics: {
          totalReserve: 0,
          totalCancel: 0
        }
      }
    },
    methods: {
    },
    computed: {
      ...mapGetters({
      })
    },
    watch: {
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./UserStatistics.css"></style>
