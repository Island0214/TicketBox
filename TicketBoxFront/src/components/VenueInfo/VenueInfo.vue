<template>
  <div class="venue-info-wrapper">
    <h1>{{ venue_name }}</h1>

    <div class="info-wrapper">
      <part-title title="基本信息" style="margin-bottom: 30px;"></part-title>

      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>编号：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ code }}</h5>
        </el-col>
      </el-row>
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>地址：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ address }}
            <!--<a @click="showChangeUsername=true">修改</a>-->
          </h5>
        </el-col>
      </el-row>
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>状态：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ validInfo }}</h5>
        </el-col>
      </el-row>
    </div>

    <div class="info-wrapper">
      <part-title title="座位信息" style="margin-bottom: 30px;"></part-title>

      <div class="table-wrapper">
        <el-table
          :data="tableData"
          style="width: 100%">
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
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-row :gutter="20" style="margin: 0">
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-input v-model="name"></el-input>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-input-number :min="1" v-model="rows"></el-input-number>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6">
            <el-input-number :min="1" v-model="cols"></el-input-number>
          </el-col>
          <el-col :xs="6" :sm="6" :md="6" :lg="6" style="margin-top: 15px">
            <el-button size="mini" @click="handleAdd">添加</el-button>
          </el-col>
        </el-row>
      </div>
    </div>

    <el-button @click="saveArea">保存修改</el-button>
  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import {mapGetters, mapActions} from 'vuex'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        venue_name: '',
        address: '',
        tableData: [{
          name: '内场1区',
          row: '10',
          col: '30',
          venue: '1111111'
        }, {
          name: '内场2区',
          row: '10',
          col: '30',
          venue: '1111111'
        }, {
          name: '内场3区',
          row: '10',
          col: '30',
          venue: '1111111'
        }, {
          name: '内场4区',
          row: '10',
          col: '30',
          venue: '1111111'
        }],
        validInfo: true,
        rows: 1,
        cols: 1,
        name: ''
      }
    },
    computed: {
      ...mapGetters({
        code: 'name'
      })
    },
    methods: {
      ...mapActions({
        getVenueInfo: 'getVenueInfo',
        saveAreaInfoAction: 'saveAreaInfo',
        getAreaInfoAction: 'getAreaInfo'
      }),
      handleDelete(index, row) {
        console.log(index, row);
        this.tableData.splice(index, 1)
      },
      handleAdd: function () {
        if (this.name === '') {
          this.$message({
            showClose: true,
            type: 'error',
            message: '请输入区域名称！'
          })
          return
        }

        for (let i = 0; i < this.tableData.length; i++) {
          if (this.name === this.tableData[i].name) {
            this.$message({
              showClose: true,
              type: 'error',
              message: '请勿重复添加区域！'
            })
            return
          }
        }

        this.tableData.push({
          name: this.name,
          row: this.rows,
          col: this.cols,
          venue: this.code
        })
      },
      saveArea: function () {
//        alert('sa')
        this.saveAreaInfoAction({
          onSuccess: () => {
            this.$message(({
              showClose: true,
              message: '修改信息已提交，请等待Ticket经理审核！',
              type: 'success'
            }))
            this.validInfo = '场馆信息审核中...'

          },
          onError: () => {
            this.$message({
              showClose: true,
              type: 'error',
              message: '场馆不能为空，请添加后重试！'
            })
          },
          body: this.tableData
        })
      }
    },
    mounted () {
      this.getVenueInfo({
        onSuccess: (data) => {
          console.log(data.name)

          this.venue_name = data.name
          this.address = data.address

          if (data.status === 2) {
            this.validInfo = '场馆信息已通过审核'
          } else if (data.status === 1) {
            this.validInfo = '请完善场馆座位信息！'
          } else if (data.status === -1) {
            this.validInfo = '场馆信息审核中...'
          } else if (data.status === -2) {
            this.validInfo = '场馆信息未通过，请修改后重新提交！'
          }

        },
        onError: () => {
          this.$router.push('/')
          this.$message(({
            showClose: true,
            message: '获取信息失败...请重试',
            type: 'error'
          }))
        },
        body: {
          code: this.code
        }
      })

      this.getAreaInfoAction({
        onSuccess: (data) => {
//          console.log(data)
          this.tableData = JSON.parse(JSON.stringify(data))
        },
        onError: () => {
          this.$message({
            showClose: true,
            type: 'error',
            message: '信息获取失败，请重试！'
          })
        },
        body: {
          code: this.code
        }
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./VenueInfo.css"></style>
