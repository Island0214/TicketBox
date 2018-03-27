<template>
  <div class="seat-selection-wrapper">
    <div class="prices-wrapper">
      价位选择：
      <br>
      <br>
      <button v-for="(price, index) in price_types" @click="selectPrice(index)"
              :class="[index === select_price ? 'select-price' : '']">{{ price }}
      </button>
    </div>

    <div class="buy-wrapper" v-if="canSelectSeat">
      该价位对应区域：
      <el-select v-model="area" placeholder="请选择">
        <el-option
          v-for="item in areas"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <br>
      <div class="seat-select-wrapper" v-if="area !== ''">
        <p>位置选择区域</p>
        <div class="seats-wrapper" v-for="row in rows" :key="row">
          <el-checkbox v-for="col in seats" style="margin-left: 2px;" :key="col"
                       v-model="seatArray[(row - 1) * seats + col - 1]"
                       :disabled="disableArray[(row - 1) * seats + col - 1]"></el-checkbox>
        </div>
        <div class="selected-seats-wrapper" v-if="selectedSeats.length !== 0">
          <el-tag v-for="(seat, index) in selectedSeats" :key="index">{{ seat }}</el-tag>
        </div>
      </div>
      <div v-if="showButton">
        <div v-if="area !== ''" style="margin-top: 30px">
          总价：
          <h4 style="position: relative; display: inline-block;">
            {{ totalPrice }}
            <el-tooltip class="item" effect="light" :content="curDiscount" placement="right">
              <i class="el-icon-question"></i>
            </el-tooltip>
          </h4>
          <br>
          <br>
          <br>
          可用优惠券：
          <el-select v-model="coupon" placeholder="请选择" no-data-text="无可用优惠券">
            <el-option
              v-for="(item, index) in coupons"
              :key="item.value"
              :label="item.name"
              :value="index">
            </el-option>
          </el-select>
          <br>
          <br>
          <br>
          折后价格:
          <h3 style="position: relative; display: inline-block; color: #871FCA;">{{ discountPrice }}</h3>
        </div>


        <el-button @click="selectSeatBuy" style="float: right; margin-top: -50px;">选座购买</el-button>
      </div>
    </div>

    <div class="buy-wrapper" v-if="!canSelectSeat">
      购买张数：
      <el-input-number v-model="ticket_num" :min="1" :max="20"></el-input-number>
      <br>
      <div style="margin-top: 30px">
        总价：
        <h4 style="position: relative; display: inline-block;">
          {{ totalPrice }}
          <el-tooltip class="item" effect="light" :content="curDiscount" placement="right">
            <i class="el-icon-question"></i>
          </el-tooltip>
        </h4>
        <br>
        <br>
        <br>
        可用优惠券：
        <el-select v-model="coupon" placeholder="请选择" no-data-text="无可用优惠券">
          <el-option
            v-for="(item, index) in coupons"
            :key="item.value"
            :label="item.name"
            :value="index">
          </el-option>
        </el-select>
        <br>
        <br>
        <br>
        折后价格:
        <h3 style="position: relative; display: inline-block; color: #871FCA;">{{ discountPrice }}</h3>
      </div>
      <el-button @click="immediateBuy" v-if="showButton" style="float: right; margin-top: -50px;">立即购买</el-button>
    </div>

  </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex'

  export default {
    props: ['showButton', 'schedule'],
    data () {
      return {
        price_types: [],
        select_price: 0,
        canSelectSeat: true,
        rows: 10,
        seats: 30,
        seatArray: [],
        selectedSeats: [],
        selectedSeatsCount: [],
        area: '',
        areas: [],
        data: [],
        disableArray: [],
        totalPrice: 0,
        coupon: '',
        discount: 1,
        coupons: [],
        ticket_num: 0,
        userGrade: 1,
        minusDiscount: 0
      }
    },
    methods: {
      ...mapActions({
        getScheduleInfoAction: 'getScheduleInfo',
        getAreaInfoAction: 'getAreaInfoOfSchedule',
        getUserInfo: 'getUserInfo',
        getVipDiscount: 'getVipDiscount',
        getUsableCoupons: 'getUsableCoupons',
        createOrder: 'createOrder',
        getScheduleBasicInfo: 'getScheduleBasicInfo'
      }),
      selectPrice: function (index) {
        this.select_price = index
      },
      initSeatArray: function () {
        if (this.area !== '') {
          this.getAreaInfoAction({
            onSuccess: (data) => {
              console.log(data)
              let seats = eval('(' + data.seats + ')')
              let seat = eval('(' + data.seat + ')')
              this.rows = seat.row
              this.seats = seat.col
              this.disableArray = seats

              this.seatArray = []
              for (let i = 0; i < this.rows * this.seats; i++) {
                this.seatArray.push(false)
              }

            },
            onError: () => {

            },
            schedule: this.schedule,
            area: this.area
          })
        }
//        // todo 取row 和 col
      },
      selectSeatBuy: function () {
        if (this.select_price === -1) {
          this.$message({
            showClose: true,
            type: 'error',
            message: '请选择一种票面价格！'
          })
          return
        }

        if (this.area === '') {
          this.$message({
            showClose: true,
            type: 'error',
            message: '请选择您想购买的区域！'
          })
          return
        }

        if (this.selectedSeats.length === 0) {
          this.$message({
            showClose: true,
            type: 'error',
            message: '请选择至少一个座位！'
          })
          return
        }

        let body = {
          username: this.name,
          price: this.discountPrice,
          schedule: this.schedule,
          area: this.area,
          seats: this.selectedSeats
        }

        if (this.coupons[this.coupon] !== undefined) {
          body.coupon = this.coupons[this.coupon].id
        }

        this.createOrder({
          onSuccess: (data) => {
//            console.log(data)
            this.$router.push('/pay/' + data.order_id)
          },
          onError: () => {

          },
          body: body
        })

        console.log(body)
      },
      immediateBuy: function () {
        let body = {
          username: this.name,
          price: this.discountPrice,
          schedule: this.schedule,
          area: this.ticket_num + ',' + this.this.price_types[this.select_price],
          seats: []
        }

        this.createOrder({
          onSuccess: (data) => {
//            console.log(data)
            this.$router.push('/pay/' + data.order_id)
          },
          onError: () => {

          },
          body: body
        })
//        let order_id = 1
//        this.$router.push('/pay/' + order_id)
      },
      getScheduleInfo: function () {
        this.getScheduleInfoAction({
          onSuccess: (data) => {
            this.data = data
            let prices = eval('(' + data.prices + ')')
            for (let i = 0; i < prices.length; i++) {
              this.price_types.push(prices[i])
            }
            this.setAreas()
//            this.time = data.time

          },
          onError: () => {

          },
          schedule: this.schedule
        })
      },
      setAreas: function () {
        let areas = eval('(' + this.data['price' + this.price_types[this.select_price]] + ')')
//        console.log(areas)
        this.areas = []
        for (let i = 0; i < areas.length; i++) {
          this.areas.push({
            value: areas[i],
            label: areas[i]
          })
        }
      }
    },
    watch: {
      select_price: function () {
        this.area = ''
        this.initSeatArray()
        this.setAreas()
        if (!this.canSelectSeat) {
          if (this.price_types.length === 0) {
            this.totalPrice = 0
          } else {
            this.totalPrice = this.price_types[this.select_price] * this.ticket_num
          }
        }
      },
      seatArray: {
        handler: function () {
          let oldValue = this.selectedSeats
          let selectCounts = []
          this.selectedSeats = []

          for (let i = 0; i < this.seatArray.length; i++) {
            if (this.seatArray[i]) {
//              console.log(i)
              let row = (i - i % this.seats) / this.seats + 1
              let col = i % this.seats + 1
              this.selectedSeats.push((row + '排' + col + '座'))
              selectCounts.push(i)
            }
          }

          if (this.selectedSeats.length > 6) {
            this.selectedSeats = oldValue
            for (let i = 0; i < this.seatArray.length; i++) {
              if (this.selectedSeatsCount.indexOf(i) !== -1) {
                this.seatArray[i] = true
              } else {
                this.seatArray[i] = false
              }
            }
            this.$message({
              showClose: true,
              type: 'error',
              message: '最多选择6个座位！'
            })
          } else {
            this.selectedSeatsCount = selectCounts
            if (this.price_types.length === 0) {
              this.totalPrice = 0
            } else {
              this.totalPrice = this.price_types[this.select_price] * this.selectedSeats.length
            }
            this.$emit('seatChange', this.totalPrice)

            let selectedSeats = []

            for (let i = 0; i < this.selectedSeats.length; i++) {
              selectedSeats.push({
                schedule: this.schedule,
                area: this.area,
                row: parseInt(this.selectedSeats[i].split('排')[0]),
                col: parseInt(this.selectedSeats[i].split('排')[1].split('座')[0])
              })
            }
            this.$emit('setSelectedSeats', selectedSeats)
//            if (price === NaN) {
          }
//          console.log(this.selectedSeats)
        },
        deep: true
      },
      area: function () {
        this.initSeatArray()
        this.seatArray = []
        this.selectedSeats = []
        this.selectedSeatsCount = []
        for (let i = 0; i < this.rows * this.seats; i++) {
          this.seatArray.push(false)
        }
      },
      totalPrice: function () {
        this.getUsableCoupons({
          onSuccess: (data) => {
            this.coupons = data
            this.coupon = ''
            this.minusDiscount = 0

            for (let i = 0; i < this.coupons.length; i++) {
              this.coupons[i].name = '满' + this.coupons[i].consumption + '减' + this.coupons[i].discount
            }
          },
          onError: () => {

          },
          body: {
            username: this.name,
            price: this.totalPrice
          }
        })
      },
      coupon: function () {
//        console.log(this.coupon)
        this.minusDiscount = this.coupons[this.coupon].discount
      },
      ticket_num: function () {
//        console.log(this.ticket_num)
//        console.log(this.select_price)
        if (this.price_types.length === 0) {
          this.totalPrice = 0
        } else {
          this.totalPrice = this.price_types[this.select_price] * this.ticket_num
        }
      }
    },
    computed: {
      ...mapGetters({
        name: 'name'
      }),
      discountPrice: function () {
        return (this.totalPrice - this.minusDiscount) * this.discount
      },
      curDiscount: function () {
        if (this.grade === 1) {
          return '当前会员等级无折扣'
        } else {
          return '当前会员等级享受' + this.discount * 10 + '折优惠'
        }
      }
    },
    mounted () {

      this.initSeatArray()
      this.getScheduleInfo()
      this.getUserInfo({
        onSuccess: (data) => {
          this.grade = data.grade

          this.getVipDiscount({
            onSuccess: (data) => {
              this.discount = data / 100
            },
            onError: () => {

            },
            grade: this.grade
          })
        },
        onError: () => {

        },
        username: this.name
      })

      this.getScheduleBasicInfo({
        onSuccess: (data) => {
          if (this.showButton) {
//            console.log(- new Date() + data.time)
//            console.log(1000 * 60 * 60 * 24 * 15)
            if (data.time - new Date() > 1000 * 60 * 60 * 24 * 14) {
              this.canSelectSeat = false
            }
          }
        },
        onError: () => {

        },
        schedule: this.schedule
      })

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./SeatSelection.css"></style>
