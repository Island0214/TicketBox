<template>
  <div class="vip-info-wrapper">
    <!--<p>s</p>-->
    <part-title title="会员信息"></part-title>

    <el-popover
      ref="popover1"
      placement="right"
      width="400"
      trigger="hover">
      <el-table :data="discounts">
        <el-table-column width="100" label="等级">
          <template slot-scope="scope">
            <span>{{ scope.row.grade }}</span>
          </template>
        </el-table-column>
        <el-table-column width="150" label="消费金额">
          <template slot-scope="scope">
            <span>¥ {{ scope.row.consumption }}</span>
          </template>
        </el-table-column>
        <el-table-column width="200" property="discount" label="折扣享受">
          <template slot-scope="scope">
            <span v-if="scope.row.discount === 100">无折扣</span>
            <span v-else>{{ scope.row.discount }}折</span>
          </template>
        </el-table-column>
      </el-table>
    </el-popover>
    <el-popover
      ref="popover2"
      placement="right"
      width="auto"
      trigger="hover">
      距离下一等级需再消费{{ nextLevel }}
    </el-popover>

    <div class="info-wrapper">
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>当前等级：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ info.grade }}
            <i class="el-icon-question" v-popover:popover1></i>
          </h5>
        </el-col>
      </el-row>
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>当前消费：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ info.consumption }}
            <i class="el-icon-question" v-popover:popover2></i>
          </h5>
        </el-col>
      </el-row>
      <el-row :gutte="20">
        <el-col :xs="8" :sm="8" :md="8" :lg="8">
          <h4>当前积分：</h4>
        </el-col>
        <el-col :xs="16" :sm="16" :md="16" :lg="16">
          <h5>{{ info.integration }}
            <a @click="showExchange=true">兑换</a>
          </h5>
        </el-col>
      </el-row>
    </div>

    <CouponExchange :showExchange="showExchange" @closeExchange="closeExchange"></CouponExchange>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import CouponExchange from './CouponExchange.vue'
  import {mapGetters, mapActions} from 'vuex'

  export default {
    components: {
      PartTitle,
      CouponExchange
    },
    data () {
      return {
        gridData: [{
          grade: '1',
          money: '0',
          discount: '无折扣'
        }, {
          grade: '2',
          money: '288',
          discount: '九五折'
        }, {
          grade: '3',
          money: '688',
          discount: '九折'
        }, {
          grade: '4',
          money: '1288',
          discount: '八五折'
        }],
        nextLevel: 888,
        showExchange: false,
        info: {},
        discounts: []
      }
    },
    methods: {
      ...mapActions({
        getUserInfo: 'getUserInfo',
        getAllDiscounts: 'getAllDiscounts'
      }),
      closeExchange: function () {
        this.showExchange = false
      }
    },
    computed: {
      ...mapGetters({
        name: 'name'
      })
    },
    mounted () {
      this.getUserInfo({
        onSuccess: (data) => {
          this.info = data
        },
        onError: () => {

        },
        username: this.name
      })

      this.getAllDiscounts({
        onSuccess: (data) => {
//          console.log(data)
          this.discounts = data
          this.nextLevel = data[this.info.grade].consumption - this.info.consumption
        },
        onError: () => {

        }
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./VipInfo.css"></style>
