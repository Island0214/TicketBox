<template>
  <div class="ticket-select-wrapper">
    <el-dialog
      :visible.sync="buyTicket"
      width="800px"
      top="30px"
      :show-close=true
      title="选座"
      :close-on-click-modal="false"
      :lock-scroll="true"
      @close="closeDialog"
    >
      <!--<part-title title="修改密码" style="margin-top: -40px; margin-bottom: 30px;"></part-title>-->
      <p>演出：<span style="color: #333; font-size: 16px;">{{schedule}}</span></p>
      <p>价位：
        <span :class="classes[index]" v-for="(price, index) in prices" :key="price">
          <el-checkbox
            v-model="selectPrices[index]" label="备选项1" border>
            {{ price }}
          </el-checkbox>
        </span>

        <span>
          <seat color="#999999" content="已售出" style="top: 5px; cursor: default;"></seat>
          <seat color="#999999" content="已选中" style="top: 5px"></seat>
        </span>

      </p>

      <div class="seats-wrapper">
        <div class="left-wrapper">
          <div v-for="item in 7" :key="'C1' + item">
            <seat v-for="index in 7" :key="'C1' + 'index' + index" color="#f9ab37" area="C1" :row="item" :col="index"
                  :selectedArea="selectPrices[2]" :price="prices[2]" :count="selectedSeats.length" :tagClose="tagClose"
                  content="" @selectSeat="selectSeat"></seat>
          </div>
          <div v-for="item in 7" :key="'E1' + item">
            <seat v-for="index in 7" :key="'E1' + 'index' + index" color="#80bec3" area="E1" :row="item" :col="index"
                  :selectedArea="selectPrices[0]" :price="prices[0]" :count="selectedSeats.length" :tagClose="tagClose"
                  content="" @selectSeat="selectSeat"></seat>
          </div>
        </div>
        <div class="center-wrapper">
          <!--<font-awesome-icon :icon="['fas','couch']"/>-->
          <div v-for="item in 5" :key="'A' + item">
            <seat v-for="index in 15" :key="'A' + 'index' + index" color="#E6494F" area="A" :row="item" :col="index"
                  :selectedArea="selectPrices[4]" :price="prices[4]" :count="selectedSeats.length" :tagClose="tagClose"
                  content="" @selectSeat="selectSeat"></seat>
          </div>
          <div v-for="item in 4" :key="'B' + item">
            <seat v-for="index in 15" :key="'B' + 'index' + index" color="#A3A9FF" area="B" :row="item" :col="index"
                  :selectedArea="selectPrices[3]" :price="prices[3]" :count="selectedSeats.length" :tagClose="tagClose"
                  content="" @selectSeat="selectSeat"></seat>
          </div>
          <div v-for="item in 5" :key="'D' + item">
            <seat v-for="index in 15" :key="'D' + 'index' + index" color="#a3d841" area="D" :row="item" :col="index"
                  :selectedArea="selectPrices[1]" :price="prices[1]" :count="selectedSeats.length" :tagClose="tagClose"
                  content="" @selectSeat="selectSeat"></seat>
          </div>
        </div>
        <div class="right-wrapper">
          <div v-for="item in 7" :key="'C2' + item">
            <seat v-for="index in 7" :key="'C2' + 'index' + index" color="#f9ab37" area="C2" :row="item" :col="index"
                  :selectedArea="selectPrices[2]" :price="prices[2]" :count="selectedSeats.length" :tagClose="tagClose"
                  content="" @selectSeat="selectSeat"></seat>
          </div>
          <div v-for="item in 7" :key="'E2' + item">
            <seat v-for="index in 7" :key="'E2' + 'index' + index" color="#80bec3" area="E2" :row="item" :col="index"
                  :selectedArea="selectPrices[0]" :price="prices[0]" :count="selectedSeats.length" :tagClose="tagClose"
                  content="" @selectSeat="selectSeat"></seat>
          </div>
        </div>

      </div>
      <p v-if="selectedSeats.length !== 0">您选择了：</p>

      <div class="selected-seats-wrapper" v-if="selectedSeats.length !== 0">
        <el-tag v-for="(seat, index) in selectedSeats" :key="index" closable @close="tagClose = seat">{{ seat.area }}区&nbsp;{{
          seat.row }}排{{ seat.col }}座
        </el-tag>
      </div>
      <p>总价：<span>¥ {{totalPrice}}</span></p>


      <span slot="footer" class="dialog-footer">
        <p style="margin-left: 5px;"><i class="el-icon-warning"></i> 单次最多选择6个座位！</p>
        <el-button :disabled="selectedSeats.length === 0">确认购买</el-button>
        <!--<el-button @click="showChangePassword = false">取 消</el-button>-->
        <!--<el-button @click="changePassword">确 定</el-button>-->
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import Seat from './Seat/Seat'
  import {mapActions} from 'vuex'

  export default {
    props: ['buyTicket', 'prices', 'curPrice', 'time', 'schedule'],
    name: "TicketSelectSeat",
    components: {
      Seat
    },
    data() {
      return {
        selectPrices: [false, false, false, false, false],
        nums: [[], [], [], [], []],
        classes: ['checkbox-4', 'checkbox-3', 'checkbox-2', 'checkbox-1', 'checkbox-0'],
        areas: ['A', 'B', 'C', 'D', 'E'],
        selectedAreas: [],
        selectedSeats: [],
        tagClose: {}
      }
    },
    computed: {
      totalPrice: function () {
        let total = 0
        for (let i = 0; i < this.selectedSeats.length; i++) {
          total += this.selectedSeats[i].price
        }
        return total
      },
      selected: function () {
        let select = false
        for (let i = 0; i < this.selectPrices.length; i++) {
          if (this.selectPrices[i]) {
            return true
          }
        }
        return false
      }
    },
    watch: {
      selectedSeats: {
        handler: function () {
          console.log(this.selectedSeats)
        },
        deep: true
      }
    },
    methods: {
      ...mapActions({
        getScheduleInfo: 'getScheduleInfo'
      }),
      setPrice: function (index) {

      },
      closeDialog: function () {
        this.$emit('close')
      },
      selectSeat: function (data) {
        // console.log(data in this.selectedSeats)
        for (let i = 0; i < this.selectedSeats.length; i++) {
          if (data.area === this.selectedSeats[i].area && data.row === this.selectedSeats[i].row && data.col === this.selectedSeats[i].col) {
            console.log('!!!!')
            this.selectedSeats.splice(i, 1)
            return
          }
        }
        this.selectedSeats.push(data)

      }
    },
    mounted() {
      let that = this
      this.selectPrices = this.curPrice
      this.getScheduleInfo({
        onSuccess: (data) => {
          console.log(data)
        },
        onError: () => {

        },
        schedule: that.$route.params.id
      })
    }
  }
</script>

<style scoped src="./TicketSelectSeat.css">

</style>
