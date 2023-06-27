<script setup lang="ts">
import { History }                from "../types/history";
import { ref }                    from "vue";
import { getRecordHistoryByUser } from "../services/history";
import { getFormatTime }                     from "../utils/utils";
import { RECORD_ACTION_MAP } from "../consts/action";

const recordHistoryList = ref<(History & {
  formatAction: string,
  formatCreateTime: string,
})[]>([])

const getRecordHistoryList = () => {
  getRecordHistoryByUser(1, 10).then(res => {
    recordHistoryList.value = res.content.map(item => ({
      ...item,
      formatAction: RECORD_ACTION_MAP[item.action as keyof typeof RECORD_ACTION_MAP],
      formatCreateTime: item.createTime ? getFormatTime(item.createTime) : '未知',
    })).sort((a, b) => b.createTime - a.createTime)
  })
}

getRecordHistoryList()
</script>

<template>
  <div id="history">
    <div id="history-list" class="card">
      <div class="card-title">工作记录操作历史</div>
      <el-table :data="recordHistoryList" style="width: 100%; margin-top: 16px">
        <el-table-column prop="title" label="记录标题" width="720"/>
        <el-table-column prop="formatAction" label="操作" width="80"/>
        <el-table-column prop="formatCreateTime" label="操作时间"/>
      </el-table>
    </div>

  </div>
</template>

<script lang="ts">
export default {
  name: "history-page"
}
</script>

<style scoped>
#history {
  padding: 0 240px;
  min-width: 1200px;
}

#history-list {
  margin-top: 32px;
  width: 100%;
}
</style>
