<template>
  <div class="footer-wrapper">
    <img src="../../assets/footer.png"/>

    <p><span style="color: #906DFF; font-size: 18px;">TICKETBOX</span> | COPYRIGHT © 2018-PRESENT ISLAND</p>
    <!--<p>TICKETBOX | COPYRIGHT © 2018-PRESENT ISLAND</p>-->
    <div class="buttons-wrapper">
      <a @click="helpDialogVisible=true">HELP</a>|
      <a @click="aboutDialogVisible=true">ABOUT</a>
    </div>
    <el-dialog
      title="HELP"
      :visible.sync="helpDialogVisible"
      width="60%"
      center
      :show-close="false">
      <div class="info-wrapper">
        <div class="logo-wrapper">
          <img src="../../assets/favicon.png"/>
        </div>
        <div style="padding-top: 5%">
          <span>关于登录和注册</span>
          <div>1. 注册后，您需要验证邮箱才能使用TicketBox的全部功能</div>
          <div>2. 若您为场馆注册，您需要记住注册后告知的场馆登陆码</div>
        </div>
        <div style="padding-top: 4%">
          <span>关于购票</span>
          <div>1. 您需要在下单15分钟内完成订单的付款</div>
          <div>2. 退款时，TicketBox根据退票时间与演出开始时间的时间差决定退款金额</div>
          <div>3. 演出收入将由场馆和TicketBox平分，比例由TicketBox经理确定</div>
        </div>
      </div>
    </el-dialog>


    <el-dialog
      title="ABOUT"
      :visible.sync="aboutDialogVisible"
      width="60%"
      center
      :show-close="false">
      <div class="info-wrapper">
        <div class="logo-wrapper">
          <img src="../../assets/favicon.png"/>
        </div>
        <div style="padding-top: 5%">
          <span>
            有关业务联络合作事宜
          </span>
          <div>
            如果您有广告投放的需求，请email: sales@ticketbox.com 并简要介绍合作需求
          </div>
          <div>
            如果您有商务渠道合作的需求，请email: business@ticketbox.com 并简要介绍合作需求
          </div>
        </div>
        <div style="padding-top: 2%;padding-bottom: 4%">
          <span>
            有关媒体外联合作事宜
          </span>
          <div>
            如果您希望与我们进行媒体合作，或有采访需求，请email: talk@ticketbox.com
          </div>
        </div>

        <span>Ticket Box @ 2018 Island All Rights Reserved</span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        aboutDialogVisible: false,
        helpDialogVisible: false
      }
    },
    methods: {}
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./MyFooter.css"></style>
