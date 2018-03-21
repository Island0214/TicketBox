<template>
  <div class="page-wrapper">
    <part-title title="会 员 封 禁"></part-title>
    <!--<div class="tabs-wrapper">-->
      <!--<a :class="{'select-tab' : !paid}" @click="paid = false">未 结 算 演 出</a>-->
      <!--&lt;!&ndash;<a>最新演出</a>&ndash;&gt;-->
      <!--<a :class="{'select-tab' : paid}" @click="paid = true" style="margin-left: 30px;">已 结 算 演 出</a>-->
    <!--</div>-->

    <div class="manager-info-page">
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          label="会员名称"
        >
          <template slot-scope="scope">
            <!--<i class="el-icon-time"></i>-->
            <span style="margin-left: 10px">{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="会员邮箱"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="会员等级"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.grade }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="会员状态"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px;" v-if="scope.row.status === 1">使用中</span>
            <span style="margin-left: 10px; color: #999;" v-if="scope.row.status === 0">邮箱未验证</span>
            <span style="margin-left: 10px; color: #871FCA;" v-if="scope.row.status === -1">已封禁</span>
          </template>
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              v-if="scope.row.status === 1"
              @click="banUser(scope.$index)"
            >封 禁
            </el-button>
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
        tableData: []
      }
    },
    methods: {
      ...mapActions({
        getAllUsers: 'getAllUsers',
        banUserAction: 'banUser'
      }),
      banUser: function (index) {
//        console.log(this.tableData[index])
        this.banUserAction({
          onSuccess: () => {
            this.tableData[index].status = -1
            this.$message({
              showClose: true,
              customClass: 'message-wrapper',
              message: '成功封禁用户：' + this.tableData[index].username,
              type: 'success'
            })
          },
          onError: () => {
            this.$message({
              showClose: true,
              customClass: 'message-wrapper',
              message: '用户' + this.tableData[index].username + '不存在',
              type: 'error'
            })
          },
          body: this.tableData[index]
        })
      }
    },
    watch: {
    },
    mounted () {
      this.getAllUsers({
        onSuccess: (data) => {
//          console.log(data)
          this.tableData = data
        },
        onError: () => {

        }
      })
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
