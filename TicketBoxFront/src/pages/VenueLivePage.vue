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
        <el-select v-model="concert" placeholder="请选择">
          <el-option
            v-for="item in concerts"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>

      <div v-if="this.concert !== ''">
        <seat-selection :showButton="false" @seatChange="seatChange"></seat-selection>
        <div class="buttons-wrapper">
          购买者会员等级：
          <el-select v-model="discount" placeholder="请选择">
            <el-option
              v-for="item in grades"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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
        <el-select v-model="concert" placeholder="请选择">
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
        <el-select v-model="area" placeholder="请选择">
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
        <el-select v-model="row" placeholder="请选择">
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
        <el-select v-model="col" placeholder="请选择">
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
  export default {
    components: {
      SeatSelection
    },
    data () {
      return {
        concerts: [{
          value: 'decajoins',
          label: 'decajoins'
        }],
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
        col: 1
      }
    },
    computed: {
      discountPrice: function () {
        return this.totalPrice * this.discount
      }
    },
    methods: {
      seatChange: function (totalPrice) {
        this.totalPrice = totalPrice
      }
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
