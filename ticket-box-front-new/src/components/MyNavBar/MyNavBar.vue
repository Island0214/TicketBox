<template>
  <div class="nav-wrapper">
    <!--<img class="nav-bg" src="../../assets/navbg.png">-->
    <img class="nav-bg" src="../../assets/bg.png">

    <img src="../../assets/logo.png" @click="$router.push('/')"
         style="cursor: pointer; z-index: 2; position: absolute;"/>
    <el-input type="input" v-model="input"></el-input>
    <el-button @click="toAllShows(input)"><i class="el-icon-search"></i></el-button>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
      <el-menu-item index="0" @click="$router.push('/')">首页</el-menu-item>
      <el-menu-item index="1" @click="toAllShows('')">演出</el-menu-item>
      <!--<el-menu-item index="2" @click="$router.push('/order')">订单</el-menu-item>-->
      <el-submenu index="2" v-if="name === ''" @click="clickSubmenu">
        <template slot="title">用户</template>
        <el-menu-item index="2-1" @click="showLoginMutation">登陆</el-menu-item>
        <el-menu-item index="2-2" @click="showSigninMutation">注册</el-menu-item>
      </el-submenu>
      <el-submenu index="2" v-if="name !== ''">
        <template slot="title">{{ name }}</template>
        <el-menu-item index="2-1" @click="$router.push('/info')">个人信息</el-menu-item>
        <el-menu-item index="2-2" @click="$router.push('/order')">我的订单</el-menu-item>
        <el-menu-item index="2-3" @click="quitLogin()">退出登录</el-menu-item>
      </el-submenu>
    </el-menu>

    <div class="log-wrapper">
      <el-dialog
        :visible.sync="showLogin"
        width="400px"
        top="150px"
        :show-close=true
        title="登 录"
        :close-on-click-modal="false"
        :lock-scroll="true"
        @open="clickSubmenu"
        @close="setActiveIndex($route.path)"
      >
        <p>账 号</p>
        <el-input v-model="username"></el-input>
        <p>密 码</p>
        <el-input type="password" v-model="password"></el-input>

        <el-button @click="logIn">登 录</el-button>

        <a @click="goSignin">注册 > </a>
      </el-dialog>

      <el-dialog
        :visible.sync="showSignin"
        width="400px"
        top="150px"
        :show-close=true
        title="注 册"
        :close-on-click-modal="false"
        :lock-scroll="true"
        @open="clickSubmenu"
        @close="setActiveIndex($route.path)"
      >
        <p>账 号</p>
        <el-input v-model="username"></el-input>
        <p>密 码</p>
        <el-input type="password" v-show="!showPassword" v-model="password"></el-input>
        <el-input v-show="showPassword" v-model="password"></el-input>
        <i class="el-icon-view" style="font-size: 16px; margin: 10px 30px 0px 0px;"
           @mouseover="showPassword = true"
           @mouseleave="showPassword = false"
           v-model="password"
        ></i>

        <el-button>注 册</el-button>

        <a @click="goLogin">登陆 > </a>
      </el-dialog>
    </div>

  </div>
</template>

<script>
  import {mapGetters, mapMutations, mapActions} from 'vuex'

  export default {
    name: "MyNavBar",
    data() {
      return {
        activeIndex: '0',
        input: '',
        username: '',
        password: '',
        showPassword: false
      }
    },
    watch: {
      '$route'(to, from) {
        // console.log(to.path)
        let that = this
        let path = to.path
        setTimeout(() => {
          that.setActiveIndex(path)
        }, 100)
      },
      showLogin: function () {
        this.username = ''
        this.password = ''
        // this.setActiveIndex(this.$route.path)
      },
      showSignin: function () {
        let that = this
        this.username = ''
        this.password = ''
        if (this.signin === false) {
          setTimeout(() => {
            that.setActiveIndex(path)
          }, 100)
        }
        // this.setActiveIndex(this.$route.path)
      },
      activeIndex: function () {
        // alert (this.activeIndex)
      }
    },
    computed: {
      ...mapGetters({
        name: 'name',
        showLogin: 'showLogin',
        showSignin: 'showSignin'
      })
    },
    methods: {
      ...mapMutations({
        setSearchParams: 'setSearchParams',
        quitLog: 'quitLog',
        showLoginMutation: 'showLogin',
        hideLoginMutation: 'hideLogin',
        showSigninMutation: 'showSignin',
        hideSigninMutation: 'hideSignin'
      }),
      ...mapActions({
        logInAction: 'logInAction',
        userRegisterAction: 'userRegisterAction'
      }),
      goLogin: function () {
        let that = this
        this.hideSigninMutation()
        setTimeout(() => {
          that.showLoginMutation()
        }, 50)
      },
      goSignin: function () {
        let that = this
        this.hideLoginMutation()
        setTimeout(() => {
          that.showSigninMutation()
        }, 50)
      },
      toAllShows: function (content) {
        this.setSearchParams({
          searchContent: content,
          city: '',
          type: '',
          startTime: '',
          endTime: ''
        })
        this.$router.push('/shows')
      },
      setActiveIndex: function (path) {
        // alert('sa')
        // this.activeIndex = '0'
        this.hideLoginMutation()
        this.hideSigninMutation()
        let that = this
        let p = path.split('/')[1]
        setTimeout(() => {
          // that.setActiveIndex(path)
          switch (p) {
            case '':
              that.activeIndex = '0'
              break;
            case 'shows':
              that.activeIndex = '1'
              break;
            case 'show':
              that.activeIndex = '1'
              break;
            case 'order':
              that.activeIndex = '2-2'
              break;
            case 'info':
              that.activeIndex = '2-1'
              break;
            case 'pay':
              that.activeIndex = '2-2'
              break;
          }
        }, 100)
        console.log(p)
      },
      clickSubmenu: function () {
        // alert(2)
        this.activeIndex = '2'
      },
      logIn: function () {
        if (this.username === '' || this.password === '') {
          this.$message({
            showClose: true,
            message: '请填写完整登陆信息！',
            type: 'error',
            customClass: 'message-wrapper'
          })
        }
        else {
          let loadingInstance = this.$loading({fullscreen: true});
          this.logInAction({
            onSuccess: (success) => {
              this.$message({
                showClose: true,
                message: '登录成功',
                type: 'success',
                customClass: 'message-wrapper'
              })
              loadingInstance.close()
              this.login = false
            },
            onError: (error) => {
              loadingInstance.close()
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
            type: '会员'
          })
        }
      },
      quitLogin: function () {
        let p = this.$route.path.split('/')[1]
        if (!(p === 'shows' || p === 'show' || p === '')) {
          this.$router.push('/')
        }
        this.quitLog()
        this.setActiveIndex(this.$route.path)
      }
    },
    mounted() {
      console.log(this.name)
      this.setActiveIndex(this.$route.path)
    }
  }
</script>

<style scoped src="./MyNavBar.css"></style>
