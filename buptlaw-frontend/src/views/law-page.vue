<script setup lang="ts">
import { onMounted, ref } from "vue";
import { getLawBySearch } from "../services/law";
import { LawSearchBody }  from "../types/law";

const lawSearchBody = ref<LawSearchBody>({
  keyword: "",
  isExactly: false
} as LawSearchBody)

const test = () => {
  console.log(lawSearchBody.value)
}

onMounted(() => {
  getLawBySearch(lawSearchBody.value).then(res => {
    console.log(res)
  })
})
</script>

<template>
  <div id="law">
    <div style="display: flex; justify-content: center; align-items: center">
      <input id="law-search-input"/>
      <button id="law-search-button" @click="test">
        <font-awesome-icon icon="magnifying-glass"/>
      </button>

      <el-tooltip placement="bottom-end" effect="light">
        <button id="law-search-configuration-button">
          高级搜索
          <font-awesome-icon icon="caret-down" style="margin-left: 8px"/>
        </button>
        <template #content>
          <el-form id="law-search-configuration">
            <el-form-item label="检索范围">
              <el-checkbox :label="1" v-model="lawSearchBody.title">标题</el-checkbox>
              <el-checkbox :label="2" v-model="lawSearchBody.content">正文</el-checkbox>
            </el-form-item>
            <el-form-item label="查找方式">
              <el-radio-group v-model="lawSearchBody.isExactly">
                <el-radio :label="true">精确查找</el-radio>
                <el-radio :label="false">模糊查找</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
        </template>
      </el-tooltip>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "main-page"
}
</script>

<style scoped>
#law {
  padding: 0 240px;
}

#law-search-input {
  width: 640px;
  height: 64px;
  border-radius: 12px 0 0 12px;
  border: 1px solid #ebebeb;
  border-right: none;
  padding: 0 16px;
  font-size: 16px;
  color: var(--text-color);
  outline: none;
  box-sizing: border-box;
}

#law-search-button {
  width: 64px;
  height: 64px;
  border: 1px solid #ebebeb;
  border-left: none;
  border-right: none;
  font-size: 24px;
  color: #ffffff;
  background: var(--theme-color);
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

#law-search-button:active {
  background: var(--theme-active-color);
}

#law-search-configuration-button {
  width: 120px;
  height: 64px;
  border-radius: 0 12px 12px 0;
  border: 1px solid #ebebeb;
  border-left: 2px solid #ebebeb;
  font-size: 18px;
  color: #ffffff;
  background: var(--theme-color);
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

#law-search-configuration {
  width: 800px;
  height: 200px;
  font-size: 14px;
}
</style>
