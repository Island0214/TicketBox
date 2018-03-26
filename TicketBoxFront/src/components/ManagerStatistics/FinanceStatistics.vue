<template>
  <div class="manager-statistics-wrapper">
    <h4>Tickets总收入: <span>{{ total }}</span></h4>
    <h4>收入明细详情</h4>
    <!--<a href=""></a>-->
    <div class="info-wrapper">
      <el-table
        :data="financeData"
        style="width: 100%; font-size: 18px; font-weight: 600;">
        <el-table-column
          label="场馆编号"
        >
          <template slot-scope="scope">
            <!--<i class="el-icon-time"></i>-->
            <span>{{ scope.row.venue }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="演出名称"
        >
          <template slot-scope="scope">
            <!--<i class="el-icon-time"></i>-->
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="演出时间"
        >
          <template slot-scope="scope">
            <span>{{ new Date(scope.row.time).toLocaleString() }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="演出收入"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.income }}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    data () {
      return {
        total: 0,
        financeData: []
      }
    },
    methods: {
      ...mapActions({
        getManagerFinanceStatistics: 'getManagerFinanceStatistics'
      })
    },
    mounted () {
      this.getManagerFinanceStatistics({
        onSuccess: (data) => {
          this.total = data.total
          this.financeData = data.balanceList
        },
        onError: () => {

        }
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./statistics.css"></style>
