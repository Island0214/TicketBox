<template>
  <div class="schedule-dispatch-wrapper">

    <part-title title="基本信息"></part-title>

    <div class="upload-pic-wrapper">
      <el-upload
        class="avatar-uploader"
        action=""
        ref="picUpload"
        :show-file-list="false"
        :before-upload="dispatchSchedule"
        :on-change="changePic"
        :auto-upload="false">
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
          <h4>演出类型:</h4>
        </el-col>
        <el-col :xs="18" :sm="18" :md="18" :lg="18">
          <el-select v-model="type" placeholder="请选择" style="width: 100%;">
            <el-option
              v-for="item in types"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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
            <span style="margin-left: 10px">{{ scope.row.area }}</span>
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

    <el-button class="dispatch-button" @click="uploadImg">发布新计划</el-button>
  </div>
</template>

<script>
  import PartTitle from '../Basic/PartTitle/PartTitle.vue'
  import { mapGetters, mapActions } from 'vuex'

  export default {
    components: {
      PartTitle
    },
    data () {
      return {
        imageUrl: '',
        start_time: '',
        concert_name: '',
        artist: '',
        concert_intro: '',
        areaData: [],
        schedule_id: '',
        types: [{
          value: '演唱会',
          label: '演唱会'
        }, {
          value: '舞蹈',
          label: '舞蹈'
        }, {
          value: '话剧',
          label: '话剧'
        }, {
          value: '体育赛事',
          label: '体育赛事'
        }],
        type: ''
      }
    },
    computed: {
      ...mapGetters({
        code: 'name'
      })
    },
    methods: {
      ...mapActions({
        getVenueInfo: 'getVenueInfo',
        dispatchScheduleAction: 'dispatchSchedule',
        setScheduleSeatAction: 'setScheduleSeat',
        getAreaInfoAction: 'getAreaInfo'
      }),
      changePic (file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      uploadImg () {
//        this.imageUrl = URL.createObjectURL(file.raw)
        console.log(this.$refs.picUpload)
        if (this.$refs.picUpload.uploadFiles.length === 0) {
          this.$message({
            showClose: true,
            type: 'error',
            message: '请选择一张图片作为海报！',
            customClass: 'message-wrapper'
          })
          return
        }

        if (this.start_time === '' || this.artist === '' || this.concert_name === '' || this.concert_intro === '') {
          this.$message({
            showClose: true,
            type: 'error',
            message: '信息不能为空！',
            customClass: 'message-wrapper'
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

        this.$refs.picUpload.submit()

      },
      getBase64Image: function (img) {
        var canvas = document.createElement("canvas")
        canvas.width = img.width
        canvas.height = img.height
        var ctx = canvas.getContext("2d")
        ctx.drawImage(img, 0, 0, img.width, img.height)
        var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase()
        var dataURL = canvas.toDataURL("image/"+ext)
        return dataURL
      },
      dispatchSchedule: function (file) {
        var loadingInstance = this.$loading({
          body: true,
          lock: true,
          text: '计划发布中...'
        })

        this.getVenueInfo({
          onSuccess: (data) => {
            if (data.status === 1) {
              let reader = new FileReader()

              var poster = ''
              var schedule = this.concert_name
              var intro = this.concert_intro
              var artist = this.artist
              var time = this.start_time
              var type = this.type
              var venue = this.code

              var that = this

              reader.onload = function(e) {

                let body = {
                  schedule: schedule,
                  intro: intro,
                  artist: artist,
                  time: time,
//                prices: this.areaData,
                  poster: e.target.result,
                  type: type,
                  venue: venue
                }
                console.log(body)


                // todo
                that.dispatchScheduleAction({
                  onSuccess: (data) => {
//                    console.log(data)
                    this.schedule_id = data.schedule_id
                    console.log(that.areaData)

                    that.setScheduleSeatAction({
                      onSuccess: () => {
                        that.$router.push('/')
                        that.$message({
                          showClose: true,
                          type: 'success',
                          message: '计划发布成功！',
                          customClass: 'message-wrapper'
                        })
                        loadingInstance.close()
                      },
                      onError: () => {
                        that.$router.push('/')
                        v.$message({
                          showClose: true,
                          type: 'error',
                          message: '计划发布失败！请重试...',
                          customClass: 'message-wrapper'
                        })
                      },
                      body: {
                        schedule: this.schedule_id,
                        seatList: that.areaData
                      }
                    })

                  },
                  onError: () => {

                  },
                  body: body
                })

              }

              reader.readAsDataURL(file)


            } else {
              this.$message({
                showClose: true,
                type: 'error',
                message: '场馆信息通过Ticket经理审核后可发布新计划！',
                customClass: 'message-wrapper'
              })
            }

          },
          onError: () => {
            this.$router.push('/')
            this.$message(({
              showClose: true,
              message: '获取信息失败...请重试',
              type: 'error',
              customClass: 'message-wrapper'
            }))
          },
          body: {
            code: this.code
          }
        })
        return false
      }
    },
    mounted () {
      this.getAreaInfoAction({
        onSuccess: (data) => {
          console.log(data)
          for (let i = 0; i < data.length; i++) {
            this.areaData.push({
              area: data[i].name,
              price: 1,
              row: data[i].row,
              col: data[i].col
            })
          }
//          this.tableData = JSON.parse(JSON.stringify(data))
        },
        onError: () => {
          this.$message({
            showClose: true,
            type: 'error',
            message: '信息获取失败，请重试！'
          })
        },
        body: {
          code: this.code
        }
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="./ScheduleDispatch.css"></style>
