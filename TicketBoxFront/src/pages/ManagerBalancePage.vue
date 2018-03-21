<template>
  <div class="page-wrapper">
    <!--<part-title title="未结算演出"></part-title>-->
    <div class="tabs-wrapper">
      <a :class="{'select-tab' : !paid}" @click="paid = false">未 结 算 演 出</a>
      <!--<a>最新演出</a>-->
      <a :class="{'select-tab' : paid}" @click="paid = true" style="margin-left: 30px;">已 结 算 演 出</a>
    </div>

    <div class="manager-info-page">
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          label="演出名称"
        >
          <template slot-scope="scope">
            <!--<i class="el-icon-time"></i>-->
            <span style="margin-left: 10px">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="场馆"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.venue }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="演出时间"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ new Date(scope.row.time).toLocaleString() }}</span>
          </template>
        </el-table-column>
        <el-table-column
          v-if="!paid"
          label="总计收入"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.income }}</span>
          </template>
        </el-table-column>
        <el-table-column
          v-if="!paid"
          label="平台占比"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">20%</span>
          </template>
        </el-table-column>
        <el-table-column
          v-if="!paid"
          label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index)">结算
            </el-button>
          </template>
        </el-table-column>

        <el-table-column
          v-if="paid"
          label="场馆收入">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.income }}</span>
          </template>
        </el-table-column>

        <el-table-column
          v-if="paid"
          label="平台收入">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.income / 4 }}</span>
          </template>
        </el-table-column>

        <el-table-column
          v-if="paid"
          label="总计收入">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.income * 1.25 }}</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import PartTitle from '../components/Basic/PartTitle/PartTitle.vue'
  import { mapActions } from 'vuex'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        tableData: [],
        paid: false
      }
    },
    methods: {
      ...mapActions({
        getUnpaidSchedules: 'getUnpaidSchedules',
        payBalance: 'payBalance',
        getPaidSchedules: 'getPaidSchedules'
      }),
      handleDelete: function (index) {
        console.log(this.tableData[index])
        this.payBalance({
          onSuccess: (data) => {
            console.log(data)
            this.$message({
              showClose: true,
              message: '成功结算演出：\n' + this.tableData[index].name,
              type: 'success',
              customClass: 'message-wrapper'
            })
            this.tableData.splice(index, 1)
          },
          onError: () => {

          },
          body: this.tableData[index]
        })
      },
      getUnpaid: function () {
        this.getUnpaidSchedules({
          onSuccess: (data) => {
            this.tableData = data
          },
          onError: () => {
          }
        })
      },
      getPaid: function () {
        this.getPaidSchedules({
          onSuccess: (data) => {
            this.tableData = data.reverse()
          },
          onError: () => {
          }
        })
      }
    },
    watch: {
      paid: function () {
        if (this.paid) {
          this.getPaid()
        } else {
          this.getUnpaid()
        }
      }
    },
    mounted () {
      this.getUnpaid()
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 60px;
  }

  .manager-info-page {
    padding: 30px;
    margin-top: 30px;
    width: 90%;
    margin-left: 5%;
    min-height: 700px;
    text-align: center;
  }

  .manager-info-page /deep/ .el-table__header thead tr /deep/ th {
    text-align: center;
  }
</style>
