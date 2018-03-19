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
        <seat-selection :showButton="false" @seatChange="seatChange" @setSelectedSeats="setSelectedSeats" :schedule="concert"></seat-selection>
        <div class="buttons-wrapper">
          <el-checkbox v-model="isVip">购买者是否为会员</el-checkbox>
          <div v-if="isVip">
            <br>
            <br>
            购买者会员账号：
            <el-input style="width: 250px;" v-model="username"></el-input>
          </div>

          <br>
          <br>
          总价：<h4><span>{{ totalPrice }}</span></h4>

          <el-button @click="confirmTicketInfo">购买</el-button>
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

        <el-button style="margin-top: 120px" @click="checkTicket">检票登记</el-button>
      </div>
    </div>

    <el-dialog
      title="结算"
      :visible.sync="dialogVisible"
      width="60%"
      :showClose="false"
      >
      会员: <span>{{ vipInfo }}</span>
      <br>
      <br>
      折扣: <span>{{ discountInfo }}</span>
      <br>
      <br>
      区域: <span>{{ areaInfo }}</span>
      <br>
      <br>
      座位: <span>{{ seatInfo }}</span>
      <br>
      <br>
      价格: <span>{{ discountPrice }}</span>
      <br>
      <br>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button @click="buyTicketOffline">确 定</el-button>
      </span>
    </el-dialog>
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
        totalPrice: 0,
        discount: 1,
        area: '',
        areas: [],
        rows: 0,
        row: '',
        cols: 0,
        col: '',
        isVip: false,
        seats: [],
        dialogVisible: false,
        vipInfo: '非会员',
        seatInfo: '',
        discountInfo: '无折扣',
        areaInfo: '',
        username: '',
        data: ''
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
      buyTicket: function () {
        this.concert = ''
      },
      concert: function () {
        console.log(this.concert)
        this.rows = 0
        this.cols = 0
        this.row = ''
        this.col = ''
        this.getScheduleInfo()
      },
      isVip: function () {
        this.username = ''
      },
      area: function () {
//        console.log(this.area)
        this.getAreaInfoAction({
          onSuccess: (data) => {
//            console.log(data)
            let seat = eval('(' + data.seat + ')')
            this.rows = seat.row
            this.cols = seat.col

          },
          onError: () => {

          },
          schedule: this.concert,
          area: this.area
        })
      }
    },
    methods: {
      ...mapActions({
        getVenueSchedules: 'getVenueSchedules',
        getUserInfoAction: 'getUserInfo',
        getVipDiscountAction: 'getVipDiscount',
        buyTicketOfflineAction: 'buyTicketOffline',
        getScheduleInfoAction: 'getScheduleInfo',
        getAreaInfoAction: 'getAreaInfoOfSchedule',
        checkTicketAction: 'checkTicket'
      }),
      seatChange: function (totalPrice) {
        this.totalPrice = totalPrice
      },
      setSelectedSeats: function (seats) {
        console.log(seats)
        let seatInfo = ''
        for (let i = 0; i < seats.length; i++) {
          seatInfo = seatInfo + seats[i].row + '排' + seats[i].col + '座' + ', '
        }
        this.seats = seats
        this.seatInfo = seatInfo.substr(0, seatInfo.length - 2)

        if (seats.length > 0) {
          this.areaInfo = seats[0].area
        }
      },
      confirmTicketInfo: function () {
        if (this.seats.length === 0) {
          this.$message({
            showClose: true,
            type: 'error',
            message: '请至少选择一张票！',
            customClass: 'message-wrapper'
          })
        } else {
          if (this.isVip) {
            this.getUserInfoAction({
              onSuccess: (data) => {
                if (data !== null) {
                  this.vipInfo = data.username
//                  this.discountInfo = data.grade
//                  this.discount = data.grade / 100
                  var that = this
                  this.getVipDiscountAction({
                    onSuccess: (data) => {
                      console.log(data)
                      that.discount = data / 100
                      if (data === 100) {
                        this.discountInfo = '无折扣'
                      } else {
                        this.discountInfo = data + '折'
                      }
                      this.dialogVisible = true
                    },
                    onError: () => {

                    },
                    grade: data.grade
                  })
                } else {
                  this.vipInfo = '会员账号不存在'
                  this.discountInfo = '无折扣'
                  this.discount = 1
                }
              },
              onError: () => {

              },
              username: this.username
            })

          }

        }
      },
      buyTicketOffline: function () {
        this.buyTicketOfflineAction({
          onSuccess: (data) => {
            this.$message({
              showClose: true,
              message: '购买成功！',
              type: 'success',
              customCLass: 'message-wrapper'
            })
            this.dialogVisible = false
            this.$router.push('/')
          },
          onError: () => {

          },
          body: {
            price: this.discountPrice,
            username: this.username,
            schedule: this.concert,
            seat: this.seatInfo,
            area: this.areaInfo
          }
        })
      },
      getScheduleInfo: function () {
        this.getScheduleInfoAction({
          onSuccess: (data) => {
            this.data = data
            let prices = eval('(' + data.prices + ')').reverse()
            for (let i = 0; i < prices.length; i++) {
//              this.price_types.push(prices[i])
              this.areas = []
              let areas =  eval('(' + this.data['price' + prices[i]] + ')')
//              console.log(areas)

              for (let j = 0; j < areas.length; j++) {
                this.areas.push({
                  label: areas[j],
                  value: areas[j]
                })
              }
            }
            console.log(this.areas)
//            this.setAreas()

          },
          onError: () => {

          },
          schedule: this.concert
        })
      },
      checkTicket: function () {
        let body = {
          schedule: this.concert,
          area: this.area,
          row: this.row,
          col: this.col
        }

        this.checkTicketAction({
          onSuccess: () => {
            this.$message({
              showClose: true,
              customClass: 'message-wrapper',
              type: 'success',
              message: this.row + '排' + this.col + '座' + '检票成功！'
            })

            this.area = ''
            this.row = ''
            this.col = ''
          },
          onError: (error) => {
            this.$message({
              showClose: true,
              customClass: 'message-wrapper',
              type: 'error',
              message: error
            })
          },
          body: body
        })
//        console.log(body)
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

  .page-wrapper .el-dialog .el-dialog__body /deep/ span {
    color: #871FCA;
    margin-left: 30px;
  }

</style>
