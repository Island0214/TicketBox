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

      <seat-selection></seat-selection>

    </div>

    <div class="intro-wrapper">
      <part-title title="项目简介"></part-title>
      <pre>{{ intro }}</pre>
    </div>

  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import SeatSelection from '../SeatSelection/SeatSelection.vue'

  export default {
    components: {
      PartTitle,
      SeatSelection
    },
    data () {
      return {
        artist: 'deca joins',
        start_time: '2018-04-11 20:00',
        location: '南京欧拉艺术空间',
        ticket_num: 1,
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
        '4月14日 周六 成都 @正火艺术中心 6号馆 嘉賓：午夜乒乓\n'
      }
    },
    methods: {
    },
    computed:{
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ConcertInfo.css"></style>
