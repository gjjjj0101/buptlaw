<script setup lang="ts">
import { History }               from "../types/history";
import { ref }                   from "vue";
import { getLawHistoryByAction } from "../services/history";
import { LAW_ACTION_MAP }        from "../consts/action";
import { getFormatTime }         from "../utils/utils";
import { router }                from "../router/index";

const favoriteList = ref<(History & {
  formatAction: string,
  formatCreateTime: string,
})[]>([])

const getFavoriteList = () => {
  getLawHistoryByAction('02', 1, 10).then(res => {
    favoriteList.value = res.content.map(item => ({
      ...item,
      formatAction: LAW_ACTION_MAP[item.action as keyof typeof LAW_ACTION_MAP],
      formatCreateTime: item.createTime ? getFormatTime(item.createTime) : '未知',
    })).sort((a, b) => b.createTime - a.createTime)
  })
}

const toLawDetailPage = (regulationId: number) => {
  router.push({name: 'law-detail', params: {id: regulationId}})
}

getFavoriteList()
</script>

<template>
  <div id="favorite">
    <div id="favorite-list" class="card">
      <div class="card-title">法律法规收藏列表</div>
      <el-table :data="favoriteList" style="width: 100%; margin-top: 16px">
        <el-table-column prop="title" label="法律法规标题" width="720"/>
        <el-table-column prop="formatAction" label="操作" width="80"/>
        <el-table-column prop="formatCreateTime" label="操作时间" width="180"/>
        <el-table-column fixed="right" label="操作" min-width="180">
          <template #default="scope">
            <el-button type="primary" plain @click="toLawDetailPage(scope.row.regulationId)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

  </div>
</template>

<script lang="ts">
export default {
  name: "favorite-page"
}
</script>

<style scoped>
#favorite {
  padding: 0 240px;
  min-width: 1200px;
}

#favorite-list {
  margin-top: 32px;
  width: 100%;
}
</style>
