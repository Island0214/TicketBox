<template>
  <div class="manager-info-page">
    <el-table
      :data="tableData"
      style="width: 100%; font-size: 18px; font-weight: 600;">
      <el-table-column type="expand">
        <template slot-scope="scope">
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
            :data="scope.row.seats"
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
          <div style="white-space: nowrap">
            <el-button
              size="mini"
              type="danger"
              @click="setVenueStatus(scope.row.index, scope.row.code, 2)"
            >允许
            </el-button>

            <el-button
              size="mini"
              type="danger"
              @click="setVenueStatus(scope.row.index, scope.row.code, -2)"
            >拒绝
            </el-button>
          </div>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'

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
    },
    methods: {
      ...mapActions({
        getVenuesByStatusAction: 'getVenuesByStatus',
        setVenueStatusAction: 'setVenueStatus',
        getAreaInfoAction: 'getAreaInfo'
      }),
      getVenuesByStatus: function (status) {
        this.getVenuesByStatusAction({
          onSuccess: (data) => {
            console.log(data)
            this.tableData = JSON.parse(JSON.stringify(data))
            let that = this

            for (let i = 0; i < this.tableData.length; i++) {
              this.getAreaInfoAction({
                onSuccess: (data) => {
                  console.log(data)
                  this.tableData[i].seats = data
//                  this.tableData = JSON.parse(JSON.stringify(data))
                },
                onError: () => {
                  this.$message({
                    showClose: true,
                    type: 'error',
                    message: '信息获取失败，请重试！'
                  })
                },
                body: {
                  code: that.tableData[i].code
                }
              })
            }
          },
          onError: () => {

          },
          status: status
        })
      },
      setVenueStatus: function (index, code, status) {
        this.setVenueStatusAction({
          onSuccess: () => {
            this.tableData.splice(index, 1)
            if (status === -3) {
              this.$message({
                showClose: true,
                type: 'success',
                message: '已拒绝场馆' + code + '注册！',
                customClass: 'message-wrapper'
              })
            }
            if (status === 1) {
              this.$message({
                showClose: true,
                type: 'success',
                message: '已通过场馆' + code + '注册！',
                customClass: 'message-wrapper'
              })
            }
          },
          onError: () => {

          },
          body: {
            code: code,
            status: status
          }
        })
      }
    },
    mounted () {
      this.getVenuesByStatus(-1)
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ManagerApproveModify.css"></style>
