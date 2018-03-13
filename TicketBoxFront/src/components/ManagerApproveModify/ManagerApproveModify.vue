<template>
  <div class="manager-info-page">
    <el-table
      :data="tableData"
      style="width: 100%; font-size: 18px; font-weight: 600;">
      <el-table-column type="expand">
        <template slot-scope="props">
          <!--<el-form label-position="left" inline class="demo-table-expand">-->
            <!--<el-form-item label="场馆编号">-->
              <!--<span>{{ props.row.code }}</span>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="场馆名称">-->
              <!--<span>{{ props.row.name }}</span>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="场馆地址">-->
              <!--<span>{{ props.row.address }}</span>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="操作">-->
              <!--&lt;!&ndash;<span>{{ props.row.shopId }}</span>&ndash;&gt;-->
            <!--</el-form-item>-->
          <!--</el-form>-->
          <!--<p>sad</p>-->
          <el-table
            :data="seatInfo"
            style="width: 80%; margin-left: 10%; font-size: 16px; font-weight: 300;">
            <el-table-column
              label="区域名"
            >
              <template slot-scope="scope">
                <!--<i class="el-icon-time"></i>-->
                <span style="margin-left: 10px">{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="区域排数"
            >
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{ scope.row.row }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="每排座位数"
            >
              <template slot-scope="scope">
                <span style="margin-left: 10px">{{ scope.row.col }}</span>
              </template>
            </el-table-column>
          </el-table>

        </template>
      </el-table-column>
      <el-table-column
        label="场馆编号"
        prop="code">
      </el-table-column>
      <el-table-column
        label="场馆名称"
        prop="name">
      </el-table-column>
      <el-table-column
        label="场馆地址"
        prop="address">
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
        }],
        seatInfo: [{
          name: '内场1区',
          row: '10',
          col: '30'
        }, {
          name: '内场2区',
          row: '10',
          col: '30'
        }, {
          name: '内场3区',
          row: '10',
          col: '30'
        }, {
          name: '内场4区',
          row: '10',
          col: '30'
        }]
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ManagerApproveModify.css"></style>
