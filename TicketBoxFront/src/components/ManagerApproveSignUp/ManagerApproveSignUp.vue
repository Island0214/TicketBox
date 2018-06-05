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
            @click="setVenueStatus(scope.row.index, scope.row.code, 1)"
          >允许
          </el-button>

          <el-button
            size="mini"
            type="danger"
            @click="setVenueStatus(scope.row.index, scope.row.code, -3)"
          >拒绝
          </el-button>

        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    data() {
      return {
        tableData: []
      }
    },
    methods: {
      ...mapActions({
        getVenuesByStatusAction: 'getVenuesByStatus',
        setVenueStatusAction: 'setVenueStatus'
      }),
      getVenuesByStatus: function (status) {
        this.getVenuesByStatusAction({
          onSuccess: (data) => {
//            console.log(data)
            this.tableData = JSON.parse(JSON.stringify(data))
          },
          onError: () => {

          },
          status: status
        })
      },
      setVenueStatus: function (index, code, status) {
        if (status === -3) {
          this.$confirm('此操作将拒绝场馆的注册，是否继续？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            showClose: false
          }).then(() => {
            this.setVenueStatusAction({
              onSuccess: () => {
                this.tableData.splice(index, 1)
                this.$message({
                  showClose: true,
                  type: 'success',
                  message: '已拒绝场馆' + code + '注册！',
                  customClass: 'message-wrapper'
                })
              },
              onError: () => {
              },
              body: {
                code: code,
                status: status
              }
            })
          })
        } else if (status === 1) {
          this.$confirm('此操作将批准场馆的注册，是否继续？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            showClose: false
          }).then(() => {
            this.setVenueStatusAction({
              onSuccess: () => {
                this.tableData.splice(index, 1)
                this.$message({
                  showClose: true,
                  type: 'success',
                  message: '已通过场馆' + code + '注册！',
                  customClass: 'message-wrapper'
                })
              },
              onError: () => {
              },
              body: {
                code: code,
                status: status
              }
            })
          })
        }
      }
    },
    mounted() {
      this.getVenuesByStatus(0)
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style src="./ManagerApproveSignUp.css"></style>
