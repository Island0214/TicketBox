<template>
  <div class="ticket-reserve-wrapper">
    <el-dialog
      :visible.sync="reserveTicket"
      width="640px"
      :show-close=true
      title="预售"
      :close-on-click-modal="false"
      @close="closeDialog"
    >
      <!--<part-title title="修改密码" style="margin-top: -40px; margin-bottom: 30px;"></part-title>-->
      <p>演出：<span style="color: #333; font-size: 16px;">{{schedule}}</span></p>
      <p>价位：
        <!--<el-button class="time-button">{{ new Date(basicData.time).toLocaleString() }}</el-button>-->
        <!--<span>{{ new Date(basicData.time).toLocaleString() }}</span>-->
        <button v-for="(price, index) in prices" :key="price"
                :class="['price-button', {'is-active':selectPrices.indexOf(index) !== -1}]" @click="setPrice(index)">{{
          price }}
        </button>
      </p>
      <p v-if="this.selectPrices.length > 0">您选择了：</p>

      <div class="type-wrapper" v-for="(item, index) in selectPrices" :key="index">
        <el-row :gutter="20" style="margin: 0;">
          <el-col :xs="8" :sm="8" :md="8" :lg="10">
            <span>{{ new Date(time).toLocaleString() }}</span>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="6">
            <span>¥ {{ prices[item] }}</span>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8" :lg="8">
            <el-input-number v-model="nums[item]" :min="1" :max="6" label="描述文字"></el-input-number>
          </el-col>
        </el-row>
      </div>
      <p>总价：<span>¥ {{totalPrice}}</span></p>


      <span slot="footer" class="dialog-footer">
        <p><i class="el-icon-warning"></i> 每种价位预售票单次最多可购买6张</p>
        <el-button :disabled="this.selectPrices.length === 0">确认购买</el-button>
        <!--<el-button @click="showChangePassword = false">取 消</el-button>-->
        <!--<el-button @click="changePassword">确 定</el-button>-->
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    props: ['reserveTicket', 'prices', 'curPrice', 'time', 'schedule'],
    name: "TicketReserve",
    data() {
      return {
        selectPrices: [],
        nums: [1, 1, 1, 1, 1]
      }
    },
    computed: {
      totalPrice: function () {
        let total = 0
        for (let i = 0; i < this.prices.length; i++) {
          if (i in this.selectPrices) {
            total += this.prices[i] * this.nums[i]
          }
        }
        return total
      }
    },
    watch: {
      reserveTicket: function () {
        if (this.selectPrices.indexOf(this.curPrice) === -1) {
          this.selectPrices.push(this.curPrice)
        }
      }
    },
    methods: {
      setPrice: function (index) {
        if (this.selectPrices.indexOf(index) !== -1) {
          this.selectPrices.splice(this.selectPrices.indexOf(index), 1)
        } else {
          this.selectPrices.push(index)
        }
        console.log(this.selectPrices)
      },
      closeDialog: function () {
        this.$emit('close')
      }
    },
    mounted() {
      this.selectPrices.push(this.curPrice)
    }
  }
</script>

<style scoped src="./TicketReserve.css">

</style>
