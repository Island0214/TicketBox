<template>
  <div class="page-wrapper">
    <div class="title-wrapper">
      <part-title title="订单信息" style="margin: 30px;"></part-title>
    </div>
    <order :order="order" :showButtons="false"></order>
    <pay-area :order="order"></pay-area>
  </div>
</template>

<script>
  import PayArea from '../components/PayArea/PayArea.vue'
  import PartTitle from '../components/Basic/PartTitle/PartTitle.vue'
  import {mapGetters, mapActions} from 'vuex'
//
  export default {
    components: {
      Order: () => import('../components/Order/Order.vue'),
      PayArea,
      PartTitle
    },
    data () {
      return {
        order: {}
      }
    },
    methods: {
      ...mapActions({
        getOrderById: 'getOrderById'
      })
    },
    computed: {
      ...mapGetters({
        username: 'name'
      })
    },
    mounted () {
      this.getOrderById({
        onSuccess: (data) => {
          console.log(data)
          if (data.username !== this.username) {
            this.$router.push('/')
          } else {
            this.order = data
          }
        },
        onError: () => {

        },
        body: {
          order_id: parseInt(this.$route.params.id)
        }
      })
    }
  }
</script>

<style scoped>
  .page-wrapper {
    padding: 30px;
    width: 100%;
    text-align: center;
  }

  .page-wrapper .title-wrapper {
    width: 100%;
    text-align: left;
  }
</style>
