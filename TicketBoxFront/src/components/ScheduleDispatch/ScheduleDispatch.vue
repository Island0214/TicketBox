<template>
  <div class="schedule-dispatch-wrapper">

    <part-title title="基本信息"></part-title>

    <div class="upload-pic-wrapper">
      <el-upload
        class="avatar-uploader"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </div>

    <div class="info-wrapper">
      <el-row :gutter="20">
        <el-col :xs="6" :sm="6" :md="6" :lg="6">
          <h4>演出名称:</h4>
        </el-col>
        <el-col :xs="18" :sm="18" :md="18" :lg="18">
          <el-input v-model="concert_name"></el-input>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :xs="6" :sm="6" :md="6" :lg="6">
          <h4>演出人员:</h4>
        </el-col>
        <el-col :xs="18" :sm="18" :md="18" :lg="18">
          <el-input v-model="artist"></el-input>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :xs="6" :sm="6" :md="6" :lg="6">
          <h4>演出时间:</h4>
        </el-col>
        <el-col :xs="18" :sm="18" :md="18" :lg="18">
          <el-date-picker
            v-model="start_time"
            type="datetime"
          >
          </el-date-picker>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :xs="6" :sm="6" :md="6" :lg="6">
          <h4>演出简介:</h4>
        </el-col>
        <el-col :xs="18" :sm="18" :md="18" :lg="18">
          <el-input type="textarea" v-model="concert_intro"></el-input>
        </el-col>
      </el-row>
    </div>

    <div class="price-info-wrapper">
      <part-title title="票价信息"></part-title>

      <el-table
        :data="areaData">
        <el-table-column
          label="区域名"
        >
          <template slot-scope="scope">
            <!--<i class="el-icon-time"></i>-->
            <span style="margin-left: 10px">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="对应价格">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.price" :min="1" style="width: 80%"></el-input-number>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <el-button class="dispatch-button" @click="dispatchSchedule">发布新计划</el-button>
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
        imageUrl: require('../../assets/poster4.jpg'),
        start_time: '',
        concert_name: '',
        artist: '',
        concert_intro: '',
        areaData: [{
          name: '内场1区',
          price: 0
        }, {
          name: '内场2区',
          price: 0
        }, {
          name: '内场3区',
          price: 0
        }, {
          name: '内场4区',
          price: 0
        }],
      }
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      dispatchSchedule: function () {
        if (this.start_time === '' || this.artist === '' || this.concert_name === '' || this.concert_intro === '') {
          this.$message({
            showClose: true,
            type: 'error',
            message: '信息不能为空！'
          })
          return
        }
        for (let i = 0; i < this.areaData.length; i++) {
          if (this.areaData[i].price === 0) {
            this.$message({
              showClose: true,
              type: 'error',
              message: '票价不能为空！'
            })
            return
          }
        }
        let body = {
          concert_name: this.concert_name,
          concert_intro: this.concert_intro,
          artist: this.artist,
          start_time: this.start_time,
          prices: this.areaData
        }
        console.log(body)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ScheduleDispatch.css"></style>
