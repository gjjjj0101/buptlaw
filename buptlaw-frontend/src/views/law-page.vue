<script setup lang="ts">
import { onMounted, ref }             from "vue";
import { getLawBySearch }             from "../services/law";
import { LawSearchBody }              from "../types/law";
import { LAW_CLASS_TREE_SELECT_LIST, OFFICE_CLASS_TREE_SELECT_LIST } from "../consts/law";

const lawSearchBody = ref<LawSearchBody>({
  content: "",
  isExactly: false,
} as LawSearchBody)

const publishTimeRange = ref<(number | undefined)[]>([])
const expiryTimeRange  = ref<(number | undefined)[]>([])

const onSearch = () => {
  if (publishTimeRange.value[0]) {
    lawSearchBody.value.publishStart = Math.floor((publishTimeRange.value[0] as Date).getTime() / 1000)
  }
  if (publishTimeRange.value[1]) {
    lawSearchBody.value.publishEnd = Math.floor((publishTimeRange.value[1] as Date).getTime() / 1000)
  }
  if (expiryTimeRange.value[0]) {
    lawSearchBody.value.expiryStart = Math.floor((expiryTimeRange.value[0] as Date).getTime() / 1000)
  }
  if (expiryTimeRange.value[1]) {
    lawSearchBody.value.expiryEnd = Math.floor((expiryTimeRange.value[1] as Date).getTime() / 1000)
  }

  getLawBySearch(lawSearchBody.value).then(res => {
    console.log(res)
  })
}

const switchLawSearchConfiguration = () => {
  const lawSearchConfiguration = document.getElementById("law-search-configuration")
  if (lawSearchConfiguration.style.display === "none" || lawSearchConfiguration.style.display === "") {
    lawSearchConfiguration.style.display = "block"
  } else {
    lawSearchConfiguration.style.display = "none"
  }
}

const handleLawClassCheckBoxClick = (data: any) => {
  if (data.value === '0200') {
    // 去掉修改、废止的决定（0211）
    lawSearchBody.value.lawClass = lawSearchBody.value.lawClass.filter(((item: string) => item !== '0211') as any)
  } else if (data.value === '0500') {
    // 去掉修改、废止的决定（0510）
    lawSearchBody.value.lawClass = lawSearchBody.value.lawClass.filter(((item: string) => item !== '0510') as any)
  }
}

onMounted(() => {
  getLawBySearch(lawSearchBody.value, 1, 10).then(res => {
    console.log(res.content)
  })
})
</script>

<template>
  <div id="law">
    <div style="display: flex; justify-content: center; align-items: center">
      <input id="law-search-input" v-model="lawSearchBody.content"/>
      <button id="law-search-button" @click="onSearch">
        <font-awesome-icon icon="magnifying-glass"/>
      </button>

      <button id="law-search-configuration-button" @click="switchLawSearchConfiguration">
        高级搜索
        <font-awesome-icon icon="caret-down" style="margin-left: 8px"/>
      </button>

      <div id="law-search-configuration">
        <el-row>
          <el-col :span="11">
            <el-form-item label="查找方式">
              <el-radio-group v-model="lawSearchBody.isExactly">
                <el-radio :label="true">精确查找</el-radio>
                <el-radio :label="false">模糊查找</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="检索范围">
              <el-checkbox :label="1" v-model="lawSearchBody.title">标题</el-checkbox>
              <el-checkbox :label="2" v-model="lawSearchBody.html">正文</el-checkbox>
            </el-form-item>
            <el-form-item label="发布日期">
              <el-date-picker
                  v-model="publishTimeRange"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="起始时间"
                  end-placeholder="终止时间"
                  unlink-panels
              />
            </el-form-item>
            <el-form-item label="执行日期">
              <el-date-picker
                  v-model="expiryTimeRange"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="起始时间"
                  end-placeholder="终止时间"
                  unlink-panels
              />
            </el-form-item>
          </el-col>
          <el-col :span="11" :offset="2">
            <el-form-item label="时效性">
              <el-checkbox-group v-model="lawSearchBody.status">
                <el-checkbox label="1">已有效</el-checkbox>
                <el-checkbox label="3">未生效</el-checkbox>
                <el-checkbox label="5">已修改</el-checkbox>
                <el-checkbox label="7">无时效</el-checkbox>
                <el-checkbox label="9">已废止</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item label="法规类型" style="margin-top: 36px">
              <el-tree-select
                  v-model="lawSearchBody.lawClass"
                  :data="LAW_CLASS_TREE_SELECT_LIST"
                  multiple
                  show-checkbox
                  @check="handleLawClassCheckBoxClick"
                  clearable
                  collapse-tags
                  placeholder="请选择法规类型"
              />
            </el-form-item>
            <el-form-item label="指定机关">
              <el-tree-select
                  v-model="lawSearchBody.officeClass"
                  :data="OFFICE_CLASS_TREE_SELECT_LIST"
                  multiple
                  show-checkbox
                  clearable
                  collapse-tags
                  placeholder="请选择指定机关"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>
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
  width: 120px !important;
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
  width: 824px;
  background: #ffffff;
  padding: 24px;
  border: 1px solid #ebebeb;
  border-radius: 12px;
  box-sizing: border-box;
  font-size: 14px;
  position: absolute;
  top: 160px;
  display: none;
}

#law-search-configuration::before {
  content: '';
  position: absolute;
  top: -6px;
  right: 54px;
  width: 12px;
  height: 12px;
  transform: rotate(45deg);
  border: 1px solid #ebebeb;
  border-bottom: none;
  border-right: none;
  background: #ffffff;
}
</style>
