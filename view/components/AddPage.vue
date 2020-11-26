<template>
<div class="container">
  <div class="top" align="center">
    <el-select
      style="width: 150px"
      v-model="mainTitle"
      filterable
      allow-create
      default-first-option
      @change="getSubtitle"
      placeholder="分类">
      <el-option
        v-for="item in mainTitleOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
    <el-select
      style="width: 150px"
      v-model="subTitle"
      filterable
      allow-create
      default-first-option
      placeholder="主标题">
      <el-option
        v-for="item in subTitleOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
    <el-input v-model="title" type="primary" placeholder="请输入的文章的名称" style="width: 200px"/>
    <el-button type="primary" @click="storeArticle">保存</el-button>
  </div>

  <div class="bottom">
    <mavon-editor
      class="md"
      :subfield = "true"
      :defaultOpen = "'preview'"
      :toolbarsFlag = "true"
      :editable="true"
      :scrollStyle="true"
      :ishljs = "true"
      v-model='mdContent'
      codeStyle='rainbow'
      previewBackground="#ffffff"
      @change="contentChange"
    ></mavon-editor>
  </div>
</div>
</template>

<script>
import Qs from 'qs'
export default {
name: "AddPage",
  data(){
    return{
      mdContent:'',
      mainTitle:'',
      mainTitleOptions: [],
      subTitle:"",
      subTitleOptions:[],
      title:""
    }
  },
  methods:{
    contentChange(value,render){
        this.mdContent = value
    },
    storeArticle(){
      if (this.mdContent==''){

      }else if (this.mainTitle==''){
        this.failMessage("错误,分类不能为空")
      }else if (this.subTitle==''){
        this.failMessage('错误,主标题不能为空')
      }else if (this.title==''){
        this.failMessage('错误,文章题目不能为空')
      }else {
        let  dataStr = {
          content:this.mdContent,
          classify:this.mainTitle,
          subtitle:this.subTitle,
          title:this.title
        }
        let data = Qs.stringify(dataStr);
        let that = this
        this.axios.post("/api/addArticle",data,{headers:{'Content-Type':'application/x-www-form-urlencoded'}}).then(function (resp){
          if (resp.data=='ok'){
              that.successMessage('文章保存成功!',that)
          }else {
            that.failMessage("网络发生错误,请稍后重试!");
          }
        })
      }
    },
    failMessage(message){
      this.$message({
        type:'error',
        message:message
      })
    },
    successMessage(message,that){
      that.$message({
        type:'success',
        message:message
      })
    },
    getSubtitle(){
      let that = this
      console.log(that.subTitleOptions)
      this.axios.get('/api/getAddPageSubtitleInfo?classify='+this.mainTitle).then(function (resp){
        if (resp.data.status=='ok'){
            that.subTitleOptions = resp.data.subtitle
            that.$forceUpdate(that.subTitleOptions)
        }
      })
    }
  },
  mounted() {
    let that = this
    this.axios.get('/api/getAddPageInitInfo').then(function (resp){
      if (resp.data.status=='ok'){
        that.mainTitleOptions = resp.data.classify
      }
    })
  }
}
</script>

<style scoped>

.top{
  margin-top: 1%;
}

.bottom{
  margin-top: 1%;
}

.container{
  width: 80%;
}
</style>
