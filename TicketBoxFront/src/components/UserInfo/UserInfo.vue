<template>
  <div class="user-info-wrapper">
    <!--<p>s</p>-->
    <part-title title="用户信息"></part-title>

    <div class="info-wrapper">
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>邮箱</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ email }}</h5>
        </el-col>
      </el-row>
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>账户：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ name }}
            <!--<a @click="showChangeUsername=true">修改</a>-->
          </h5>
        </el-col>
      </el-row>
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>密码：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>*****
            <a @click="showChangePassword=true">修改</a>
          </h5>
        </el-col>
      </el-row>
    </div>

    <el-dialog
      :visible.sync="showChangePassword"
      width="400px"
      :show-close=false>
      <part-title title="修改密码"></part-title>

      <div class="change-info-wrapper">
        <p>输入原密码</p>
        <el-input type="password" v-model="passwords[0]"></el-input>
        <p>确认原密码</p>
        <el-input type="password" v-model="passwords[1]"></el-input>
        <p>输入新密码</p>
        <el-input type="password" v-model="passwords[2]"></el-input>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button @click="showChangePassword = false">取 消</el-button>
        <el-button @click="changePassword">确 定</el-button>
      </span>
    </el-dialog>

    <!--<el-dialog-->
      <!--:visible.sync="showChangeUsername"-->
      <!--width="400px"-->
      <!--:show-close=false-->
      <!--:before-close="handleClose">-->
      <!--<part-title title="修改用户名"></part-title>-->

      <!--<div class="change-info-wrapper">-->
        <!--<p>输入新用户名</p>-->
        <!--<el-input type="password" v-model="newUsername"></el-input>-->
      <!--</div>-->

      <!--<span slot="footer" class="dialog-footer">-->
        <!--<el-button @click="showChangeUsername = false">取 消</el-button>-->
        <!--<el-button @click="changePassword">确 定</el-button>-->
      <!--</span>-->
    <!--</el-dialog>-->
  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import {mapGetters} from 'vuex'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        email: '111@qq.com',
        showChangePassword: false,
        showChangeUsername: false,
        passwords: ['', '', ''],
        newUsername: ''
      }
    },
    methods: {
      changePassword: function () {
      }
    },
    computed: {
      ...mapGetters({
        name: 'name'
      })
    },
    watch: {
      showChangePassword: function () {
        this.passwords = ['', '', '']
      },
      showChangeUsername: function () {
        this.newUsername = ''
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./UserInfo.css"></style>
