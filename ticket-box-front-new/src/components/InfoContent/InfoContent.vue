<template>
  <div class="info-content-wrapper">
    <el-tabs type="border-card" style="min-height: 250px;">
      <el-tab-pane :label="infoType">
        <div v-if="infoType === '基本信息' && info.username">
          <div class="head-img-wrapper">
            <img src="../../assets/icon.jpg">
          </div>
          <h2>{{ info.username }}</h2>
          <el-popover
            ref="popover1"
            placement="right"
            width="300"
            trigger="hover">
            <el-table :data="discounts">
              <el-table-column width="150" label="等级">
                <template slot-scope="scope">
                  <span>{{ scope.row.grade }}</span>
                </template>
              </el-table-column>
              <el-table-column width="150" label="消费金额">
                <template slot-scope="scope">
                  <span>¥ {{ scope.row.consumption }}</span>
                </template>
              </el-table-column>
              <!--<el-table-column width="200" property="discount" label="折扣享受">-->
                <!--<template slot-scope="scope">-->
                  <!--<span v-if="scope.row.discount === 100">无折扣</span>-->
                  <!--<span v-else>{{ scope.row.discount }}折</span>-->
                <!--</template>-->
              <!--</el-table-column>-->
            </el-table>
          </el-popover>

          <div v-if="!modify">
            <div class="single-info-wrapper">
              <span>性别</span>
              <p>{{ gender }}<a @click="modify = true">修改</a></p>
            </div>
            <div class="single-info-wrapper">
              <span>生日</span>
              <p>1997-02-14<a @click="modify = true">修改</a></p>
            </div>
            <div class="single-info-wrapper">
              <span>手机号</span>
              <p>{{ phone }}<a @click="modify = true">修改</a></p>
            </div>
            <div class="single-info-wrapper">
              <span>邮箱</span>
              <p>{{ info.email }}</p>
            </div>
            <div class="single-info-wrapper">
              <span>会员等级</span>
              <p>{{ info.grade }}<i class="el-icon-question" v-popover:popover1></i></p>
            </div>
            <div class="single-info-wrapper">
              <span>总消费</span>
              <p>{{ info.consumption }}</p>
            </div>
          </div>
          <div v-else>
            <div class="single-info-wrapper">
              <span>性别</span>
              <p>
                <el-radio v-model="gender" label="男">男</el-radio>
                <el-radio v-model="gender" label="女">女</el-radio>
              </p>

            </div>
            <div class="single-info-wrapper">
              <span>生日</span>
              <p>
                <el-date-picker
                  v-model="birthday"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
              </p>
            </div>
            <div class="single-info-wrapper">
              <span>手机号</span>
              <p>
                <el-input v-model="phone"></el-input>
              </p>
            </div>
            <div class="single-info-wrapper">
              <span>邮箱</span>
              <p>{{ info.email }}</p>
            </div>
            <div class="single-info-wrapper">
              <span>会员等级</span>
              <p>{{ info.grade }}<i class="el-icon-question" v-popover:popover1></i></p>
            </div>
            <div class="single-info-wrapper">
              <span>总消费</span>
              <p>{{ info.consumption }}</p>
            </div>

            <el-button style="position: absolute; right: 20px; bottom: 20px;" @click="modifyInfo">确认修改</el-button>
          </div>
        </div>
        <div v-if="infoType === '密码修改'">
          <div class="change-password-wrapper">
            <p>输入原密码</p>
            <el-input type="password" v-model="passwords[0]"></el-input>
            <p>输入新密码</p>
            <el-input type="password" v-model="passwords[1]"></el-input>
            <p>确认新密码</p>
            <el-input type="password" v-model="passwords[2]"></el-input>

            <el-button @click="changePassword">确认修改</el-button>
          </div>
        </div>

      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import {mapGetters, mapActions, mapMutations} from 'vuex'

  export default {
    name: "InfoContent",
    data() {
      return {
        info: {},
        modify: false,
        gender: '男',
        phone: 13151059368,
        birthday: 'Fri Feb 14 1997 00:00:00 GMT+0800',
        discounts: [
          {grade: 1, consumption: 0},
          {grade: 2, consumption: 500},
          {grade: 3, consumption: 1000},
          {grade: 4, consumption: 2000},
          {grade: 5, consumption: 4000},
          {grade: 6, consumption: 8000}
        ],
        passwords: ['', '', '']
      }
    },
    computed: {
      ...mapGetters({
        infoType: 'infoType',
        name: 'name'
      })
    },
    watch: {
      infoType: function () {
        this.passwords = ['', '', '']
      }
    },
    methods: {
      ...mapMutations({
        setInfoType: 'setInfoType'
      }),
      ...mapActions({
        getUserInfo: 'getUserInfo',
        changePasswordAction: 'changePassword'
      }),
      changePassword: function () {
        if (this.passwords[0] === '' || this.passwords[1] === '' || this.passwords[2] === '') {
          this.$message({
            showClose: true,
            message: '输入不能为空！',
            type: 'error',
            customClass: 'message-wrapper'
          })
        } else {
          this.changePasswordAction({
            onSuccess: (success) => {
              this.$message({
                showClose: true,
                message: success,
                type: 'success',
                customClass: 'message-wrapper'
              })
              this.passwords = ['', '', '']
              this.setInfoType('基本信息')
            },
            onError: (error) => {
              this.$message({
                showClose: true,
                message: error,
                type: 'error',
                customClass: 'message-wrapper'
              })
            },
            body: {
              username: this.name,
              oldPassword: this.passwords[0],
              newPassword1: this.passwords[1],
              newPassword2: this.passwords[2]
            }
          })
        }
      },
      modifyInfo: function () {
        console.log(this.birthday)
        console.log(this.phone)
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/
        if (this.birthday === null) {
          this.$message({
            showClose: true,
            message: '生日不能为空！',
            type: 'error',
            customClass: 'message-wrapper'
          })
        } else if (!myreg.test(this.phone)) {
          this.$message({
            showClose: true,
            message: '请填写格式正确的手机号！',
            type: 'error',
            customClass: 'message-wrapper'
          })
        } else {
          this.modify = false
          this.$message({
            showClose: true,
            message: '成功修改个人信息！',
            type: 'success',
            customClass: 'message-wrapper'
          })
        }
      }
    },
    mounted() {
      this.getUserInfo({
        onSuccess: (data) => {
          console.log(data)
          this.info = data
        },
        onError: () => {

        },
        username: this.name
      })
    }
  }
</script>

<style scoped src="./InfoContent.css">

</style>
