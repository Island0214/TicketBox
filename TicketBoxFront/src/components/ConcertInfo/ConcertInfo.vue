<template>
  <div class="concert-info-wrapper">
    <div class="concert-image-wrapper">
      <img src="../../assets/poster4.jpg"/>
    </div>

    <div class="info-wrapper">
      <h1>deca joins《春天游泳》2018巡演南京站</h1>

      <p>演出者：<span>{{ artist }}</span></p>
      <p>演出时间：<span>{{ start_time }}</span></p>
      <p>演出地点：<span>{{ location }}</span></p>

      <div class="prices-wrapper">
        <button v-for="(price, index) in price_types" @click="selectPrice(index)" :class="[index === select_price ? 'select-price' : '']">{{ price }}</button>
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
            <el-checkbox v-for="col in seats" style="margin-left: 2px;" :key="col" v-model="seatArray[(row - 1) * seats + col - 1]"></el-checkbox>
          </div>
          <div class="selected-seats-wrapper" v-if="selectedSeats.length !== 0">
            <el-tag v-for="(seat, index) in selectedSeats" :key="index">{{ seat }}</el-tag>
          </div>
        </div>
        <el-button @click="selectSeatBuy">选座购买</el-button>
      </div>

      <div class="buy-wrapper" v-if="!canSelectSeat">
        购买张数：<el-input-number v-model="ticket_num" :min="1" :max="20"></el-input-number>
        <br>
        <el-button @click="immediateBuy">立即购买</el-button>
      </div>
    </div>

    <div class="intro-wrapper">
      <part-title title="项目简介"></part-title>
      <pre>{{ intro }}</pre>
    </div>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        artist: 'deca joins',
        start_time: '2018-04-11 20:00',
        location: '南京欧拉艺术空间',
        price_types: [100, 120, 200, 400, 600],
        select_price: -1,
        canSelectSeat: true,
        ticket_num: 1,
        area: '',
        areas: [
          {
            value: '内场1区',
            label: '内场1区'
          },
          {
            value: '内场2区',
            label: '内场2区'
          },
          {
            value: '内场3区',
            label: '内场3区'
          }
        ],
        intro: '海面在搖晃，讓西邊的太陽盡情地沉沒\n' +
        '遙遠的海浪在吹噓著無奈\n' +
        '前方的路上，有影子陪我\n' +
        '\n' +
        '這裡的春天快得像一陣風\n' +
        '昨天沒有醉，風也不太喝酒\n' +
        '把煩躁枕在頭下；身體披蓋了孤獨\n' +
        '在夜裡點支菸，最完美的一天。\n' +
        '\n' +
        '3月30日 周五 广州 @MAOLivehouse广州 \n' +
        '4月01日 周日 厦门 @RealLive_厦门  \n' +
        '4月04日 周三 武汉 @VOXLIVEHOUSE  \n' +
        '4月05日 周四 郑州 @7LIVEHOUSE  \n' +
        '4月06日 周五 北京 @疆进酒OMNI SPACE\n' +
        '4月08日 周日 上海 @ModernSkyLAB上海 擔任@落日飛車樂隊 專場嘉賓\n' +
        '4月09日 周一 杭州 @MAOLivehouse杭州  \n' +
        '4月11日 周三 南京 @欧拉艺术空间 嘉賓：午夜乒乓\n' +
        '4月12日 周四 重庆 @MAOLivehouse重庆官微 嘉賓：午夜乒乓\n' +
        '4月14日 周六 成都 @正火艺术中心 6号馆 嘉賓：午夜乒乓\n',
        rows: 10,
        seats: 30,
        seatArray: [],
        selectedSeats: [],
        selectedSeatsCount: []
      }
    },
    methods: {
      selectPrice: function (index) {
        this.select_price = index
      },
      selectSeat: function (row, col) {
        alert(row + ';' + col)
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
      }
    },
    computed:{
    },
    watch:{
      seatArray: {
        handler: function () {
          let oldValue = this.selectedSeats
          let selectCounts = []
          this.selectedSeats = []

          for (let i = 0; i < this.seatArray.length; i++) {
            if (this.seatArray[i]) {
//              console.log(i)
              let row = (i - i % this.seats)/ this.seats + 1
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
          }

//          console.log(this.selectedSeats)
        },
        deep: true
      },
      area: function () {
        this.seatArray = []
        this.selectedSeats = []
        this.selectedSeatsCount = []
        for (let i = 0; i < this.rows * this.seats; i++) {
          this.seatArray.push(false)
        }
      }
    },
    mounted () {
      console.log(this.$route.params.id)
      for (let i = 0; i < this.rows * this.seats; i++) {
        this.seatArray.push(false)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ConcertInfo.css"></style>
