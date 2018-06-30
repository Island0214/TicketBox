<template>
  <div class="nav-wrapper">
    <!--<img class="nav-bg" src="../../assets/navbg.png">-->
    <img class="nav-bg" src="../../assets/bg.png">

    <img src="../../assets/logo.png"/>
    <el-input type="input" v-model="input"></el-input>
    <el-button @click="toAllShows(input)"><i class="el-icon-search"></i></el-button>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
      <el-menu-item index="0" @click="$router.push('/')">首页</el-menu-item>
      <el-menu-item index="1" @click="toAllShows('')">演出</el-menu-item>
      <el-menu-item index="2">订单</el-menu-item>
      <el-submenu index="3">
        <template slot="title">用户</template>
        <el-menu-item index="2-1">登陆</el-menu-item>
        <el-menu-item index="2-2">注册</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex'

  export default {
    name: "MyNavBar",
    data() {
      return {
        activeIndex: '0',
        input: ''
      }
    },
    watch: {
      '$route'(to, from) {
        // console.log(to.path)
        let path = to.path
        this.setActiveIndex(path)
      }
    },
    methods: {
      ...mapMutations({
        setSearchParams: 'setSearchParams'
      }),
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
        let p = path.split('/')[1]
        switch (p) {
          case '':
            this.activeIndex = '0'
            break;
          case 'shows':
            this.activeIndex = '1'
            break;
          case 'show':
            this.activeIndex = '1'
            break;
        }
      }
    },
    mounted () {
      console.log(this.$route)
      this.setActiveIndex(this.$route.path)
    }
  }
</script>

<style scoped src="./MyNavBar.css"></style>
