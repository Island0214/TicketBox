<template>
  <div class="seat-wrapper" :class="[{'select-area': selectedArea}, {'is-sold': content === '已售出'}, {'is-sold': content === '已选中'}]" @click="selectSeat">
    <icon name="couch" :class="['seat-icon']" :style="{'color': color}" v-if="!selected && content !== '已选中'"></icon>
    <icon name="couch" :class="['selected-seat-icon']" v-if="selected || content === '已选中'"></icon>
    <icon name="check" class="check-icon" v-if="selected || content === '已选中'"></icon>
    <p v-if="content !== ''">{{ content }}</p>
  </div>
</template>

<script>
  export default {
    props: ['color', 'area', 'row', 'col', 'selectedArea', 'price', 'count', 'tagClose', 'content'],
    name: "Seat",
    data () {
      return {
        selected: false
      }
    },
    computed: {
    },
    methods: {
      selectSeat: function () {
        // console.log('select')
        if (this.content !== '') {
          return
        }

        if (this.count >= 6 && !this.selected) {
          this.$message({
            showClose: true,
            type: 'error',
            message: '单次最多选择6个座位！',
            customClass: 'message-wrapper'
          })
        } else {
          this.selected = !this.selected
          this.$emit('selectSeat', {
            area: this.area,
            row: this.row,
            col: this.col,
            price: this.price
          })
        }
      }
    },
    watch: {
      tagClose: {
        handler: function () {
          // console.log(this.tagClose)
          if (this.tagClose.area === this.area && this.tagClose.row === this.row && this.tagClose.col === this.col) {
            this.selectSeat()
          }
        },
        deep: true
      }
    },
    mounted () {
      console.log(this.area)
      console.log(this.row)
      console.log(this.col)
      console.log(this.selectedArea)
    }
  }
</script>

<style scoped src="./Seat.css">

</style>
