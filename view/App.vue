<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App'
}
</script>

<style>
h1,h2,h3,h4,h5,h6 {
  font-family: "Microsoft YaHei";
  font-weight: 700;
}

p{
  font-family: Simsun;
}

html,body{
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

img{
  width: 40%;
  margin-left: 30%;
}


code span{
  font-family: "Times New Roman";
  font-size: 16px;
}

blockquote{
  border-left:3px solid #d3d3d3;
}

blockquote p{
  margin-left: 2%;
}
center{
  font-family: "Microsoft YaHei";
  color: #999999;
}

li{
  font-family: Simsun;
}

.el-tree{
  font-family: "Microsoft YaHei";
  font-weight: normal;
}
.el-tree span{
  font-size: 16px;
}
pre{
  background-color: #474949;
}

</style>
