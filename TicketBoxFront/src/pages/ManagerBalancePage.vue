<template>
  <div class="page-wrapper">
    <part-title title="未结算演出"></part-title>
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
          label="结束时间"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.time }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="总计收入"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.income }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="平台占比"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">20%</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">结算</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import PartTitle from '../components/Basic/PartTitle/PartTitle.vue'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        tableData: [{
          name: '内场1区',
          time: '10',
          income: '30'
        }, {
          name: '内场1区',
          time: '10',
          income: '30'
        }, {
          name: '内场1区',
          time: '10',
          income: '30'
        }, {
          name: '内场1区',
          time: '10',
          income: '30'
        }]
      }
    }
  }
</script>
