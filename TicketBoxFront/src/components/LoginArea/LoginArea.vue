<template>
  <div class="home-page-wrapper">
    <img src="../../assets/bg1.png">
    <el-collapse-transition>
      <div class="login-wrapper" v-show="showLogin">
        <h1>{{ this.loginType }}登录</h1>
        <i class="el-icon-close" @click="hideLogin"></i>

        <h3 style="margin-top: 30%;" v-if="this.loginType !== '场馆'">账 号</h3>
        <h3 style="margin-top: 30%;" v-if="this.loginType === '场馆'">编 号</h3>
        <el-input v-model="username"></el-input>
        <h3 style="margin-top: 20%;">密 码</h3>
        <el-input type="password" v-model="password"></el-input>

        <el-button @click="logIn">登 录</el-button>
      </div>
    </el-collapse-transition>

    <el-collapse-transition>
      <div class="login-wrapper" v-show="showSignin && signinType === '会员'">
        <div v-if="!vipSignSuccess">
          <h1>立刻加入会员</h1>
          <i class="el-icon-close" @click="hideSignin"></i>
          <h3 style="margin-top: 25%;">邮 箱</h3>
          <el-input v-model="email"></el-input>
          <h3 style="margin-top: 10%;">账 户</h3>
          <el-input v-model="username"></el-input>
          <h3 style="margin-top: 10%;">密 码</h3>
          <el-input type="password" v-show="!showPassword" v-model="password"></el-input>
          <el-input v-show="showPassword" v-model="password"></el-input>
          <i class="el-icon-view" style="font-size: 16px; margin: 10px 30px 0px 0px;"
             @mouseover="showPassword = true"
             @mouseleave="showPassword = false"
             v-model="password"
          ></i>

          <el-button @click="userRegister">注 册</el-button>
        </div>

        <div v-else>
          <h1>注册完成</h1>
          <i class="el-icon-close" @click="hideSignin"></i>
          <h3 style="margin-top: 60%">验证邮件已发至您的邮箱，<br>前往验证后即可登录购票！</h3>
          <el-button @click="hideSignin">确定</el-button>

        </div>
      </div>
    </el-collapse-transition>

    <el-collapse-transition>
      <div class="login-wrapper" v-show="showSignin && signinType === '场馆'">
        <div v-if="!arenaSignSuccess">
          <h1>成为售票场馆</h1>
          <i class="el-icon-close" @click="hideSignin"></i>
          <h3 style="margin-top: 25%;">名 称</h3>
          <el-input v-model="placeName"></el-input>
          <h3 style="margin-top: 10%;">地 址</h3>
          <el-input v-model="address"></el-input>
          <h3 style="margin-top: 10%;">密 码</h3>
          <el-input type="password" v-show="!showPassword" v-model="password"></el-input>
          <el-input v-show="showPassword" v-model="password"></el-input>
          <i class="el-icon-view" style="font-size: 16px; margin: 10px 30px 0px 0px;"
             @mouseover="showPassword = true"
             @mouseleave="showPassword = false"
             v-model="password"
          ></i>

          <el-button @click="venueRegister">注 册</el-button>
        </div>

        <div v-else>
          <h1>注册完成</h1>
          <i class="el-icon-close" @click="hideSignin"></i>
          <h3 style="margin-top: 60%">注册场馆编码为: {{ code }}，<br>请使用场馆编码进行登录！</h3>
          <el-button @click="hideSignin">确定</el-button>

        </div>
      </div>
    </el-collapse-transition>
  </div>
</template>

<script>
  import { mapGetters, mapMutations, mapActions } from 'vuex'

  export default {
    components: {},
    name: 'HelloWorld',
    data () {
      return {
        showPassword: false,
        password: '',
        email: '',
        username: '',
        vipSignSuccess: false,
        arenaSignSuccess: false,
        placeName: '',
        address: '',
        code: 1111111
      }
    },
    computed: {
      ...mapGetters({
        showLogin: 'showLogin',
        loginType: 'loginType',
        showSignin: 'showSignin',
        signinType: 'signinType'
      })
    },
    methods: {
      ...mapMutations({
        hideLogin: 'hideLogin',
        hideSignin: 'hideSignin'
      }),
      ...mapActions({
        logInAction: 'logInAction',
        userRegisterAction: 'userRegisterAction',
        venueRegisterAction: 'venueRegisterAction'
      }),
      logIn: function () {
        this.logInAction({
          onSuccess: (success) => {
            this.$message({
              showClose: true,
              message: '登录成功',
              type: 'success',
              customClass: 'message-wrapper'
            })
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
            username: this.username,
            password: this.password
          },
          type: this.loginType
        })
      },
      userRegister: function () {
        this.userRegisterAction({
          onSuccess: () => {
            this.vipSignSuccess = true
          },
          onError: (error) => {
            this.$message({
              showClose: true,
              type: 'error',
              message: error,
              customClass: 'message-wrapper'
            })
          },
          body: {
            username: this.username,
            email: this.email,
            password: this.password
          }
        })
//      this.vipSignSuccess=true
      },
      venueRegister: function () {
        this.venueRegisterAction({
          onSuccess: (venue) => {
            this.arenaSignSuccess = true
            this.code = venue.code
          },
          onError: (error) => {
            this.$message({
              showClose: true,
              type: 'error',
              message: error
            })
          },
          body: {
            name: this.placeName,
            address: this.address,
            password: this.password
          }
        })
      }
    },
    watch: {
      showLogin: function () {
        if (this.showLogin) {
          this.password = ''
          this.email = ''
          this.username = ''
          this.vipSignSuccess = false
          this.arenaSignSuccess = false
          this.hideSignin()
        }
      },
      showSignin: function () {
        if (this.showSignin) {
          this.password = ''
          this.email = ''
          this.username = ''
          this.address = ''
          this.placeName = ''
          this.vipSignSuccess = false
          this.arenaSignSuccess = false
          this.hideLogin()
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./LoginArea.css">
</style>
