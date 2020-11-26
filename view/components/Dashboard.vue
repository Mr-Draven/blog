<template>
<div align="center">
    <div class="top" align="center">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" style="width: 140px">
        <el-menu-item index="1">主页</el-menu-item>
        <el-menu-item index="2">新增</el-menu-item>
      </el-menu>
    </div>
  <div class="bottom">
    <router-view/>
  </div>
</div>
</template>

<script>
export default {
name: "Dashboard",
  data(){
    return{
      activeIndex : '1'
    }
  },
  methods:{
    handleSelect(index,keypath){
        if (index==1){
          if (this.$route.path!='/navigator'){
            this.$router.push('/navigator')
          }
        }else if (index==2){
          if (this.$route.path!='/addPage'){
            this.$router.push('/addPage')
          }
        }
    }
  },
  mounted() {
      this.$router.push("/navigator")
  }
}
</script>

<style scoped>
.top{
  margin-top: 1%;
}
.bottom{
  width: 100%;
}


</style>
