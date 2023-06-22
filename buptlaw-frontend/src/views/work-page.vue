<script setup lang="ts">
import { ref }           from "vue";
import { Record }        from "../types/record";
import { getRecordList } from "../services/record";
import { getFormatTime } from "../utils/utils";
import { router }        from "../router/index";

const recordList = ref<(Record & {
  formatUpdateTime: string
})[]>([])

getRecordList().then(res => {
  recordList.value = res.content.map(i => ({
    ...i,
    formatUpdateTime: getFormatTime(i.updateDateTime)
  }))
})

const toRecordEditPage = () => {
  router.push({ name: 'record' })
}
</script>

<template>
  <div id="work">
    <div class="card" style="width: 100%; margin-top: 32px">
      <div class="card-title">记录列表</div>
      <el-button type="primary" style="float: right; margin-top: -32px" @click="toRecordEditPage">添加记录</el-button>
      <el-table :data="recordList" stripe style="width: 100%; margin-top: 24px">
        <el-table-column prop="title" label="标题" width="180" />
        <el-table-column prop="content" label="内容" width="320" />
        <el-table-column prop="type" label="类型" width="320"/>
        <el-table-column prop="status" label="状态" width="120"/>
        <el-table-column prop="formatUpdateTime" label="更新时间" width="180"/>
        <el-table-column label="操作">
          <template #default>
            <el-button plain>查看</el-button>
            <el-button type="primary" plain>编辑</el-button>
            <el-button type="danger" plain>删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "work-page"
}
</script>

<style scoped>
#work {
  padding: 0 240px 64px;
}
</style>
