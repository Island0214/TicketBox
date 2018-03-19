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
                       v-model="seatArray[(row - 1) * seats + col - 1]" :disabled="disableArray[(row - 1) * seats + col - 1]"></el-checkbox>
        </div>
        <div class="selected-seats-wrapper" v-if="selectedSeats.length !== 0">
          <el-tag v-for="(seat, index) in selectedSeats" :key="index">{{ seat }}</el-tag>
        </div>
      </div>
      <el-button @click="selectSeatBuy" v-if="showButton">选座购买</el-button>
    </div>

    <div class="buy-wrapper" v-if="!canSelectSeat">
      购买张数：
      <el-input-number v-model="ticket_num" :min="1" :max="20"></el-input-number>
      <br>
      <el-button @click="immediateBuy" v-if="showButton">立即购买</el-button>
    </div>

  </div>
</template>

<script>
  import {mapActions} from 'vuex'

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
        disableArray: []
      }
    },
    methods: {
      ...mapActions({
        getScheduleInfoAction: 'getScheduleInfo',
        getAreaInfoAction: 'getAreaInfoOfSchedule'
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

        // todo buy
        let order_id = 1
        this.$router.push('/pay/' + order_id)
      },
      immediateBuy: function () {
        // todo buy
        let order_id = 1
        this.$router.push('/pay/' + order_id)
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

          },
          onError: () => {

          },
          schedule: this.schedule
        })
      },
      setAreas: function () {
        let areas =  eval('(' + this.data['price' + this.price_types[this.select_price]] + ')')
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

          if (this.selectedSeats.length > 20) {
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
              message: '最多选择20个座位！'
            })
          } else {
            this.selectedSeatsCount = selectCounts
            if (this.price_types.length === 0) {
              this.$emit('seatChange', 0)
            } else {
              this.$emit('seatChange', this.price_types[this.select_price] * this.selectedSeats.length)
            }
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
      }
    },
    mounted () {
      this.initSeatArray()
      this.getScheduleInfo()
//      console.log(this.seatArray)
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./SeatSelection.css"></style>
