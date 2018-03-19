<template>
  <div class="page-wrapper">
    <!--<concert-info></concert-info>-->
    <div class="tabs-wrapper">
      <a :class="{'select-tab' : buyTicket}" @click="buyTicket = true">现 场 购 票</a>
      <!--<a>最新演出</a>-->
      <a :class="{'select-tab' : !buyTicket}" @click="buyTicket = false" style="margin-left: 30px;">检 票 入 场</a>
    </div>

    <div v-if="buyTicket" class="operation-wrapper">
      <!--<el-checkbox>购买者是否为会员</el-checkbox>-->
      <!--<br>-->
      <!--<br>-->
      <div class="buttons-wrapper">
        选择场次：
        <el-select v-model="concert" placeholder="请选择" style="width: 100%; margin-top: 30px;">
          <el-option
            v-for="item in concerts"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>

      <div v-if="this.concert !== ''">
        <seat-selection :showButton="false" @seatChange="seatChange" :schedule="concert"></seat-selection>
        <div class="buttons-wrapper">
          <el-checkbox v-model="isVip">购买者是否为会员</el-checkbox>
          <div v-if="isVip">
            <br>
            <br>
            购买者会员账号：
            <el-input style="width: 250px;"></el-input>
          </div>

          <br>
          <br>
          总价：<h4><span>{{ discountPrice }}</span></h4>

          <el-button>购买</el-button>
        </div>
      </div>
    </div>

    <div v-if="!buyTicket" class="operation-wrapper">
      <div class="buttons-wrapper selects-wrapper">
        选择场次：
        <el-select v-model="concert" placeholder="请选择" style="width: 80%;">
          <el-option
            v-for="item in concerts"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <br>
        <br>
        <!--<br>-->

        选择区域：
        <el-select v-model="area" placeholder="请选择" style="width: 80%;">
          <el-option
            v-for="item in areas"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <br>
        <br>

        选择排号：
        <el-select v-model="row" placeholder="请选择" style="width: 80%;">
          <el-option
            v-for="item in rows"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
        <br>
        <br>

        选择座位：
        <el-select v-model="col" placeholder="请选择" style="width: 80%;">
          <el-option
            v-for="item in cols"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>

        <el-button style="margin-top: 120px">检票登记</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  import SeatSelection from '../components/SeatSelection/SeatSelection.vue'
  import {mapGetters, mapActions} from 'vuex'
  export default {
    components: {
      SeatSelection
    },
    data () {
      return {
        concerts: [],
        concert: '',
        buyTicket: true,
        grades: [{
          value: 1,
          label: '1级',
        }, {
          value: 0.95,
          label: '2级',
        }],
        totalPrice: 1,
        discount: 1,
        area: '',
        areas: [],
        rows: 30,
        row: 1,
        cols: 30,
        col: 1,
        isVip: false
      }
    },
    computed: {
      ...mapGetters({
        code: 'name'
      }),
      discountPrice: function () {
        return this.totalPrice * this.discount
      }
    },
    watch: {
      concert: function () {
        console.log(this.concert)
      }
    },
    methods: {
      ...mapActions({
        getVenueSchedules: 'getVenueSchedules'
      }),
      seatChange: function (totalPrice) {
        this.totalPrice = totalPrice
      }
    },
    mounted () {
//      console.log(this.code)
      this.getVenueSchedules({
        onSuccess: (data) => {
//          console.log(data)
          for (let i = 0; i < data.length; i++) {
            this.concerts.push({
              value: data[i].schedule_id,
              label: data[i].schedule
            })
          }
        },
        onError: () => {
          this.$message({
            showClose: true,
            message: '获取信息失败！请重试...',
            customClass: 'message-wrapper',
            type: 'error'
          })
        },
        venue: this.code
      })
    }
  }
</script>

<style>
  .page-wrapper {
    padding: 60px;
    min-height: 800px;
  }

  .page-wrapper .operation-wrapper {
    text-align: center;
    width: 100%;
    padding: 30px;
  }

  .page-wrapper .operation-wrapper .selects-wrapper .el-select {
    margin-bottom: 60px;
  }

  .page-wrapper .operation-wrapper .buttons-wrapper {
    position: relative;
    display: inline-block;
    width: 700px;
    text-align: left;
    margin-top: 30px;
  }

  .page-wrapper .operation-wrapper .buttons-wrapper h4 {
    position: relative;
    display: inline-block;
  }

  .page-wrapper .operation-wrapper .buttons-wrapper h4 span {
    color: #871FCA;
  }

  .page-wrapper .operation-wrapper .buttons-wrapper .el-button {
    width: 200px;
    float: right;
    margin-top: 60px;
  }
</style>
