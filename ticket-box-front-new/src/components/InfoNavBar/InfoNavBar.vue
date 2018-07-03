<template>
  <div class="info-nav-wrapper">
    <!--<p>asd</p>-->
    <div class="breadcrumb-wrapper">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>个人信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-vertical-demo"
    >
      <el-menu-item index="0" @click="selectType('基本信息')">
        <i class="el-icon-menu"></i>
        <span slot="title">基本信息</span>
      </el-menu-item>
      <el-menu-item index="1" @click="selectType('密码修改')">
        <i class="el-icon-menu"></i>
        <span slot="title">密码修改</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  import {mapGetters, mapMutations} from 'vuex'

  export default {
    name: "InfoNavBar",
    data () {
      return {
        titles: ['基本信息', '密码修改'],
        activeIndex: '0'
      }
    },
    computed: {
      ...mapGetters({
        infoType: 'infoType'
      })
    },
    methods: {
      ...mapMutations({
        setInfoType: 'setInfoType'
      }),
      selectType: function (type) {
        this.setInfoType(type)
      }
    },
    watch: {
      infoType: function () {
        // alert('as')
        if (this.infoType === '基本信息') {
          this.activeIndex = '0'
        } else {
          this.activeIndex = '1'
        }
        // this.setOrderType(this.types[this.activeIndex])
      }
    }
  }
</script>

<style scoped src="./InfoNavBar.css">

</style>
