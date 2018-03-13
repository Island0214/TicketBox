<template>
  <div class="manager-info-page">
    <el-table
      :data="tableData"
      style="width: 100%; font-size: 18px; font-weight: 600;">
      <el-table-column
        label="场馆编号"
      >
        <template slot-scope="scope">
          <!--<i class="el-icon-time"></i>-->
          <span>{{ scope.row.code }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="场馆名称"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="场馆地址"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.address }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
          >允许
          </el-button>

          <el-button
            size="mini"
            type="danger"
          >拒绝
          </el-button>

        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    data () {
      return {
        tableData: [{
          code: '1111111',
          name: '萨法撒上发生的风格',
          address: '的水果都富含高但是'
        }, {
          code: '1111112',
          name: '阿斯顿高的水果风味',
          address: '阿斯顿高完全是萨芬日上'
        }, {
          code: '1111113',
          name: '官方大风吹的是',
          address: '个而过分的让我反对德国'
        }, {
          code: '1111114',
          name: '嘎多噶三分大赛',
          address: '认为韩国删掉收费的'
        }]
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="./ManagerApproveSignUp.css"></style>
