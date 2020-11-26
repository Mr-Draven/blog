<template>
  <div class="container">
    <div class="left" align="center">
        <ul v-for="itemParent in titleOptions" :title="itemParent.title">
          <a href="#" @click="">{{itemParent.title}}</a>
          <li v-for="item in itemParent.subtitle"><a href="#" @click="fetchArticle(item)">{{item}}</a></li>
        </ul>
    </div>

    <div class="right" id="content" align="left">
      <el-switch
        v-model="isEdit"
        active-text="编辑"
        @change="changeSwitch"
      style="margin-bottom: 2%">
      </el-switch>
      <mavon-editor
        class="md"
        :value="htmlContent"
        :subfield = "false"
        :defaultOpen = "openStr"
        :toolbarsFlag = "false"
        :editable="true"
        :scrollStyle="true"
        :ishljs = "true"
        codeStyle='rainbow'
        previewBackground="#ffffff"
        @save="save"
        @change="contentChange"
      ></mavon-editor>
    </div>

  </div>
</template>

<script>
import Qs from 'qs'
export default {
  name: "Preview",
  data(){
    return{
      htmlContent:'<div style="font-size: 28px;font-family: \'Times New Roman\'" align="center">Welcome</div>',
      isEdit:false,
      openStr:'preview',
      mdContent:'',
      mdContentPre:'',
      isInit:true,
      titleOptions:[],
      currentTitle:"",
    }
  },
  methods:{
    fetchInitContent(classify){
      let that = this
      this.axios.get("/api/getTitleOptions?classify="+classify).then(function (resp){
        that.titleOptions = resp.data.titleOptions
      })
    },
    changeSwitch(){
      if (this.currentTitle!=''){
        if (this.openStr=='preview'){
          this.openStr = 'edit'
        }else {
          this.openStr = 'preview'
          this.save(this.mdContent,null)
        }
      }

    },
    save(value,render){
        if (this.mdContentPre!=value){
          let that = this
          let dataStr  = {
            title:this.currentTitle,
            content:value
          }
          let postData = Qs.stringify(dataStr);
          this.axios.post("/api/storeContent",postData,{headers:{'Content-Type':'application/x-www-form-urlencoded'}}).then(function (resp){
            if (resp.data=='ok'){
              that.$message({
                type:'success',
                message:"文件已经保存!"
              })
              that.mdContentPre = value
            }
          })
        }
    },
    contentChange(value,render){
        this.mdContent = value
        if (this.isInit){
          this.mdContentPre = value
          this.isInit = false
        }
    },
    fetchArticle(title){
      let that = this
      this.axios.get('/api/fetchArticle?title='+title).then(function (resp){
        if (resp.data.status=='ok'){
          that.htmlContent = resp.data.article
          that.currentTitle = title;
        }
      })
    }
  },
  mounted() {
    let classify = this.$store.state.classify
    this.fetchInitContent(classify) // 这里的数据需要从vuestate中获取
    this.currentTitle=''
  }
}
</script>

<style scoped>
.container{
  width: 80%;
  margin-top: 1%;

}

.left{
  display: inline-block;
  width: 20%;
  height: 600px;
  overflow: auto;
  vertical-align: top;
}

.right{
  display: inline-block;
  width: 70%;

}

ul{
  list-style: none;
  text-align: left;
}

ul a{
  text-decoration: none;
  font-weight: 900;
  font-size: 22px;
  font-family: Simsun;
  color: black;
}

ul a:hover{
  cursor: pointer;
}

ul li{
  margin-left: 10%;
  margin-top: 10%;
}

ul li a{
  text-decoration: none;
  font-weight: 500;
  font-size: 18px;
  font-family: Simsun;
}

</style>
