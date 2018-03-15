<template>
  <div class="page-wrapper">
    <img src="../assets/bg1.png">
    <div class="info-wrapper" v-if="authenticateSuccessfully">

      <h1>邮箱验证成功！</h1>

      <el-button @click="showLoginFrame('会员')">立 即 登 录</el-button>

    </div>

    <div class="info-wrapper" v-if="!authenticateSuccessfully">

      <h1 style="margin-top: 70%">邮箱验证失败...请重试!</h1>

      <!--<el-button @click="showLoginFrame('会员')">立 即 登 录</el-button>-->

    </div>
  </div>
</template>

<script>
  import {mapMutations, mapActions} from 'vuex'
  export default {
    components: {
    },
    data () {
      return {
        authenticateSuccessfully: true
      }
    },
    methods: {
      ...mapMutations({
        showLogin: 'showLogin',
        setLoginType: 'setLoginType'
      }),
      ...mapActions({
        userAuthenticate: 'userAuthenticate'
      }),
      showLoginFrame: function (type) {
        this.showLogin()
        this.setLoginType(type)
        this.$router.push('/')
      }
    },
    mounted () {
      console.log(this.$route.params.name)
      this.userAuthenticate({
        onSuccess: () => {
          this.authenticateSuccessfully = true
        },
        onError: () => {
          this.authenticateSuccessfully = false
        },
        username: this.$route.params.name
      })
    }
  }
</script>

<style scoped>
  .page-wrapper {
    /*padding: 30px;*/
    width: 100%;
    text-align: center;
    position: relative;
    display: inline-block;
  }

  .page-wrapper img {
    width: 100%;
  }

  .page-wrapper h1 {
    width: 100%;
    position: relative;
    display: inline-block;
    margin-top: 50%;
  }

  .page-wrapper h1 span {
    font-size: 20px;
    color: #871FCA;
    cursor: pointer;
  }

  .page-wrapper .info-wrapper {
    width: 30%;
    position: absolute;
    right: 0;
    top: 0;
    background-color: #fff;
    border-bottom: 8px solid #871FCA;
    bottom: 6px;
  }

  .page-wrapper .info-wrapper .el-button {
    width: 200px;
    margin-top: 50%;
  }

  .page-wrapper h1 span:hover {
    text-decoration: underline;
  }
</style>
