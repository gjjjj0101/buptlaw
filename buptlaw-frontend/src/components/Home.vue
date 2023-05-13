<script setup>
import {ref, watch} from "vue";
import {Search} from '@element-plus/icons-vue';

const listItem = [
  {
    name: '法律法规',
    isSelect: true,
  },
  {
    name: '法律法规',
    isSelect: false,
  },
  {
    name: '法律法规',
    isSelect: false,
  },
  {
    name: '法律法规',
    isSelect: false,
  },
  {
    name: '法律法规',
    isSelect: false,
  },
]

const textRef = ref('法律法规')
const searchContent = ref('')

const contentList = ref([
  {
    title: '中共中央办公厅、国务院',
    labelList: ['现行有效 ', '2023.04.20 公布', '2023.04.20 施行']
  },
  {
    title: '中共中央办公厅、国务院',
    labelList: ['现行有效 ', '2023.04.20 公布', '2023.04.20 施行']
  },
  {
    title: '中共中央办公厅、国务院',
    labelList: ['现行有效 ', '2023.04.20 公布', '2023.04.20 施行']
  },
])
</script>

<template>
  <div style="width: 100%; text-align: center">
    <div style="width: 100%; display: flex; justify-content: center">
      <div style="width: 75%; background: rgba(0, 0, 0, 0.05)">
        <el-row>
          <el-col :span="4">
            <img src="../assets/vue.svg">
            12313212313
          </el-col>
          <el-col :span="16">
            <div v-for="i in listItem" :class="['item', i.isSelect ? 'item-on-select' : '']">
              {{ i.name }}
            </div>
          </el-col>
          <el-col :span="4">

          </el-col>
        </el-row>
      </div>
    </div>

    <el-row style="background: #747bff; height: 120px">
      <el-col :span="3" :offset="3" style="height: 100%; display: flex; align-items: center">
        <div style="width: 100%; text-align: center; font-size: 36px; color: #ffffff; ">{{ textRef }}</div>
      </el-col>
      <el-col :span="12" style="height: 100%; display: flex; align-items: center;">
        <div style="width: 100%; border-radius: 16px; overflow: hidden">
          <el-input v-model="searchContent" placeholder="请输入搜索内容" style="height: 64px;">
            <template #append>
              <el-button :icon="Search" @click="handleClick" />
            </template>
          </el-input>
        </div>
      </el-col>
    </el-row>

    <div style="width: 100%; display: flex; justify-content: center; background: #f6f6f6">
      <div style="width: 75%">
        <div v-for="i in contentList" class="content-item">
          <div style="">{{ i.title }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request  from "../utils/request";
export default {
  name: "Home",
  methods:{
    handleClick(){
      console.log('test')
      request.get('/test/test1').then(res=>{
        console.log(res.data)
      })
    }
  }
}
</script>

<style scoped>
.item {
  width: 64px;
  display: inline-block;
  height: 48px;
  line-height: 48px;
  text-align: center;
  padding: 0 8px;
  margin: 0 8px;
}

.item:hover {
  color: gray;
}

.item-on-select {
  color: #535bf2;
}

.content-item {
  background: #FFFFFF;
  border-bottom: 1px solid #f6f6f6;
  height: 96px;
  text-align: left;
}
</style>
