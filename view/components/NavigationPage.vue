<template>
<div class="container" align="left">

  <div class="card" v-for="(item,index) in options" :key="index" @click="toPreview(item.value)" align="center" :style="{backgroundColor: color[index]}">
    <span style="display: inline-block;width: 100%;height: 20%">{{item.value}}</span>
  </div>
</div>
</template>

<script>
export default {
  name: "NavigationPage",
  data(){
    return{
      options:[],
      color:['#67C23A','#E6A23C','#F56C6C','#409EFF','#faecd8','#FDE6E0','#DCE2F1','#ecccb3','#22c8b2','#c3ecee','#bcefd0','#a1e8e4','#fe6471','#daf1ef','#019875','#DDF3FF']

    }
  },
  methods:{
    toPreview(value){
      this.$store.commit('setClassify',value)
      this.$router.push("/preview")
    }
  },
  mounted() {
    let that = this
    this.axios.get('/api/dashboard').then(function (resp){
        if (resp.data.status =='ok'){
          that.options = resp.data.options
        }
    })
  }
}
</script>

<style scoped>

.container{
  margin-top: 5%;
  width: 60%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}


.card{
  display: inline-block;
  width: 22%;
  margin-top: 5%;
  margin-left: 9%;
  height: 100%;
  line-height: 100px;
  background-color: #67C23A;
  border-radius: 8px;
  transition: all 0.4s;
  font-family: "Times New Roman";
}

.card:hover{
  cursor: pointer;
  transform: translateY(-5px);
  box-shadow: 5px 5px 5px #888888;

}
.container:after{
  content: "";
  width:20%;
  margin-left: 5%;
}

</style>
