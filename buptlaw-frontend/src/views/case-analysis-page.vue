<script setup lang="ts">
import { getCaseList }   from "../services/case";
import { ref }           from "vue";
import { Case }          from "../types/case";
import { getFormatTime } from "../utils/utils";

const caseList = ref<(Case & {
  formatOpenTime: string
})[]>([])

getCaseList(1, 10).then(res => {
  caseList.value = res.map(item => {
    return {
      ...item,
      formatOpenTime: getFormatTime(item.openTime)
    }
  })
})
</script>

<template>
  <div id="case-analysis">
    <div id="case-analysis-list-box" class="card">
      <el-table :data="caseList" stripe>
        <el-table-column prop="caseName" label="案件名称" width="240"/>
        <el-table-column prop="caseDescription" label="案情概要" width="540"/>
        <el-table-column prop="caseReason" label="案由" width="360"/>
        <el-table-column prop="formatOpenTime" label="最近打开时间" width="180"/>
        <el-table-column label="操作">
          <template #default>
            <el-button plain>查看</el-button>
            <el-button type="primary" plain>分析</el-button>
            <el-button type="danger" plain>删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "case-analysis-page"
}
</script>

<style scoped>
#case-analysis {
  width: 100%;
}

#case-analysis-list-box {
  width: 100%;
}
</style>
